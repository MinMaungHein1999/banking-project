package banking;

import banking.model.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Account> masterAccountDB = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        String menu;
        do {
            System.out.println("(1) Create Account !");
            System.out.println("(2) View Account !");
            System.out.println("(3) Deposit Amount !");
            System.out.println("(4) Exit !");
            System.out.print("Enter Menu Number ?");
            menu = br.readLine();
            if (menu.equalsIgnoreCase("1")) {
                createAccount();
            } else if (menu.equalsIgnoreCase("2")) {
                viewAccount();
            }else if (menu.equalsIgnoreCase("3")) {
                depositAmount();
            }
            else{
                System.out.println("Invalid Menu Number!!!");
            }
        }while (!menu.equals("4"));
    }

    private static void viewAccount() throws IOException {
        String accountNumber = getAccountNumber();
        for(Account acc : masterAccountDB){
            if(acc.getAccountNumber().equalsIgnoreCase(accountNumber)){
                System.out.println("========Account Information======");
                System.out.println(acc);
                return;
            }
        }
        System.out.println("Invalid Account Number!!!!");
    }

    private static String getAccountNumber() throws IOException {
        System.out.print("Enter Account Number :");
        String accountNumber = br.readLine();
        return accountNumber;
    }

    private static void createAccount() throws IOException {
        String flag;
        do {
            String accountNumber = getNewAccountNumber();
            String accountHolderName = getHolderName();
            String phone = getPhoneNumber();
            double initialBalance = getInitialBalance();
            Account account = new Account(accountNumber, accountHolderName, initialBalance, phone);
            System.out.println("Account Create Successful!!!");
            masterAccountDB.add(account);
            System.out.print(account);
            System.out.print("Do You Want to Create New Account YES/NO?");
            flag = br.readLine();
        }while(flag.equalsIgnoreCase("yes"));
    }

    private static String getPhoneNumber() throws IOException {
        System.out.print("Enter Phone Number :");
        String phone = br.readLine();
        return phone;
    }

    private static double getInitialBalance() throws IOException {
        double initialBalance;
        do{
            System.out.print("Enter Balance :");
            initialBalance = Double.parseDouble(br.readLine());
            if(initialBalance < 0){
                System.out.println("Initial deposit amount cannot be negative!");
            }
        }while (initialBalance < 0);
        return initialBalance;
    }

    private static String getHolderName() throws IOException {
        String accountHolderName;
        do{
            System.out.print("Enter Account Holder Name :");
            accountHolderName = br.readLine();
            if(accountHolderName.isEmpty()){
                System.out.println("Account holder name cannot be empty!");
            }
        }while (accountHolderName.isEmpty());
        return accountHolderName;
    }

    private static String getNewAccountNumber() throws IOException {
        Account exAcc;
        String accountNumber ;
        do{
            accountNumber = getAccountNumber();
            exAcc = findByAccountNumber(accountNumber);
            if (exAcc != null) {
                System.out.println("Your Account Number is Already Exit!!");
            }
        }while (exAcc != null);

        return accountNumber;
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
        System.out.println("========Deposit Amount======");
        String accountNumber = getAccountNumber();
        Account account = findByAccountNumber(accountNumber);

        if(!account.getAccountNumber().equalsIgnoreCase(accountNumber)){
            System.out.println("========Invalid Account======");
            return;
        }

            double depositAmount = 0;
            do {
                System.out.print("Enter Deposit Amount : ");
                depositAmount = Double.parseDouble(br.readLine());
                if (depositAmount <= 0) {
                    System.out.println("Deposit Amount must be greater than zero!");
                }
            } while (depositAmount <= 0);
            account.deposit(depositAmount);
            System.out.println("Deposit Successful!!!");
            System.out.println("Updated Balance : " + account.getBalance());

        }
    }
