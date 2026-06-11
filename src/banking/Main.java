package banking;

import banking.exception.AccountNotFoundException;
import banking.exception.BankException;
import banking.exception.DuplicateAccountException;
import banking.exception.InvalidAmountException;
import banking.model.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Account> masterAccountDB = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String menu;
    public static void main(String[] args) throws IOException {

        do {
            System.out.println("(1) Create Account !");
            System.out.println("(2) View Account !");
            System.out.println("(3) Deposit Amount !");
            System.out.println("(4) Withdraw Amount !");
            System.out.println("(5) Transfer Money !");
            System.out.println("(6) Check Balance !");
            System.out.println("(7) Exit !");
            System.out.print("Enter Menu Number ?");
            menu = br.readLine();
            if (menu.equalsIgnoreCase("1")) {
                createAccount();
            } else if (menu.equalsIgnoreCase("2")) {
                viewAccount();
            }else if (menu.equalsIgnoreCase("3")) {
                depositAmount();
            }
            else if (menu.equalsIgnoreCase("4")) {
                withdrawAmount();
            }
            else if (menu.equalsIgnoreCase("5")) {
                transferMoney();
            }
            else if (menu.equalsIgnoreCase("6")) {
                checkBalance();
            }
            else{
                System.out.println("Invalid Menu Number!!!");
            }
        }while (!menu.equals("7"));
    }

    private static void viewAccount() throws IOException {
        try {
            String accountNumber = getAccountNumber();
            for (Account acc : masterAccountDB) {
                if (acc.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                    System.out.println("========Account Information======");
                    System.out.println(acc);
                    return;
                }
            }
            throw new AccountNotFoundException("Invalid Account Number!!!!");
        }catch (AccountNotFoundException ex){
            System.out.print(ex.getMessage());
            viewAccount();
        }
    }

    private static String getAccountNumber() throws IOException {
        System.out.print("Enter Account Number :");
        String   accountNumber = br.readLine();
        return accountNumber;
    }

    private static String getSenderAccountNumber() throws IOException {
        System.out.print("Enter Sender Account Number :");
        String   accountNumber = br.readLine();
        return accountNumber;
    }

    private static String getReveiverAccountNumber() throws IOException {
        System.out.print("Enter Receiver Account Number :");
        String   accountNumber = br.readLine();
        return accountNumber;
    }

    private static void createAccount() throws IOException {
        String flag = "";
        do {
            try {
                String accountNumber = getAccountNumber();
                if (findByAccountNumber(accountNumber) != null) {
                    throw new DuplicateAccountException("Account Number already exists.");
                }
                String accountHolderName = getHolderName();
                if (accountHolderName.trim().isEmpty()) {
                    throw new BankException("Account Holder Name Cannot be empty");
                }
                String phone = getPhoneNumber();
                double initialBalance = getBalance();
                if (initialBalance < 0) {
                    throw new InvalidAmountException("Amount Cannot be negative");
                }
                Account account = new Account(accountNumber, accountHolderName, initialBalance, phone);
                System.out.println("Account Create Successful!!!");
                masterAccountDB.add(account);
                System.out.print(account);
                System.out.print("Do You Want to Create New Account YES/NO?");
                flag = br.readLine();
            } catch (BankException ex){
                System.out.println(ex.getMessage());
                createAccount();
            }
        }while(flag.equalsIgnoreCase("yes"));
    }

    private static String getPhoneNumber() throws IOException {
        System.out.print("Enter Phone Number :");
        String phone = br.readLine();
        return phone;
    }

    private static double getBalance() throws IOException {
        System.out.print("Enter Balance :");
        double balance = Double.parseDouble(br.readLine());
        return balance;
    }

    private static String getHolderName() throws IOException {
            System.out.print("Enter Account Holder Name :");
            String accountHolderName = br.readLine();
            return accountHolderName;
    }

    public static Account findByAccountNumber(String accountNumber){
        for(Account account : masterAccountDB){
            if(account.getAccountNumber().equalsIgnoreCase(accountNumber)){
                return account;
            }
        }
        return null;
    }

    private static void depositAmount() throws IOException {
        try {
            System.out.println("========Deposit Amount======");
            String accountNumber = getAccountNumber();
            Account account = findByAccountNumber(accountNumber);

            if (account == null) {
                throw new AccountNotFoundException("Account Not Found Exception");
            }

            System.out.print("Enter Deposit Amount : ");
            double depositAmount = Double.parseDouble(br.readLine());
            if (depositAmount <= 0) {
                throw new InvalidAmountException("Deposit Amount must be greater than zero!");
            }

            account.deposit(depositAmount);
            System.out.println("Deposit Successful!!!");
            System.out.println("Updated Balance : " + account.getBalance());
        }catch (BankException ex1){
            System.out.println(ex1.getMessage());
            depositAmount();
        }
    }

    private static void withdrawAmount() throws IOException {
        System.out.println("======Withdraw Amount======");
        String accountNumber = getAccountNumber();
        Account account = findByAccountNumber(accountNumber);
        if(account == null){
            System.out.println("========Invalid Account======");
            return;
        }

        double withdrawAmount;
        do {
            System.out.print("Enter Withdraw Amount : ");
            withdrawAmount = Double.parseDouble(br.readLine());
            if (withdrawAmount <= 0) {
                System.out.println("Deposit Amount must be greater than zero!");
                return;
            }
            if(withdrawAmount >= account.getBalance()){
                System.out.println("Insufficient balance.!");
                return;
            }
        } while (withdrawAmount <= 0);
        account.withdraw(withdrawAmount);
        System.out.println("WithdrawAmount Successful!!!");
        System.out.println("Updated Balance : " + account.getBalance());

    }

    private static void transferMoney() throws IOException {
        System.out.println("======Transfer Monery======");
        String senderAccountNumber = getSenderAccountNumber();
        Account senderaccount = findByAccountNumber(senderAccountNumber);
        String receiverAccountNumber = getReveiverAccountNumber();
        Account receiveraccount = findByAccountNumber(receiverAccountNumber);

        if(senderaccount == null || receiveraccount == null){
            System.out.println("========Invalid Account======");
            return;
        }
        if(senderaccount.equals(receiveraccount)){
            System.out.println("========Can't transfer to the same account======");
            return;
        }

        double transferAmount = 0;
        do{
            System.out.print("Enter Transfer Amount : ");
            transferAmount = Double.parseDouble(br.readLine());
            if(transferAmount <= 0){
                System.out.println("Transfer Amount must be greater than zero!");
            }
            if(transferAmount > senderaccount.getBalance()){
                System.out.println("Sender account must have sufficient balance");
                return;
            }
        }while (transferAmount <= 0);
        senderaccount.withdraw(transferAmount);
        receiveraccount.deposit(transferAmount);
        System.out.println("Transferred Successfully!!!");
        System.out.println("Sender Account Updated Balance : " + senderaccount.getBalance());
        System.out.println("Receiver Acount Updated Balance : " + receiveraccount.getBalance());
    }

    private static void checkBalance() throws IOException {
        System.out.println("========Check Balance======");
        String accountNumber = getAccountNumber();
        Account account = findByAccountNumber(accountNumber);
        if(account != null){
            System.out.println("Current Balance : " + account.getBalance());
        }else {
            System.out.println("Invalid Account");
        }
    }
}
