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
        String flag;
        do {
            String accountNumber = getAccountNumber();
            String accountHolderName = getHolderName();
            System.out.print("Enter Phone Number :");
            String phone = br.readLine();
            double initialBalance = getInitialBalance();

            Account account = new Account(accountNumber, accountHolderName, initialBalance, phone);
            masterAccountDB.add(account);
            System.out.print(account);
            System.out.print("Do You Want to Create New Account YES/NO?");
            flag = br.readLine();
        }while(flag.equalsIgnoreCase("yes"));

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

    private static String getAccountNumber() throws IOException {
        Account exAcc;
        String accountNumber ;
        do{
            System.out.print("Enter Account Number :");
            accountNumber = br.readLine();
            exAcc = findByAccountNumber(accountNumber);
            if (exAcc != null) {
                System.out.print("Your Account Number is Already Exit!!");
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
}