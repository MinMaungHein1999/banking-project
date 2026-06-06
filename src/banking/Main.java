package banking;

import banking.model.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Account> masterAccountDB = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String flag;
        do {
            Account exAcc;
            String accountNumber;
            do{
                System.out.print("Enter Account Number :");
                accountNumber = br.readLine();
                exAcc = findByAccountNumber(accountNumber);
                if (exAcc != null) {
                    System.out.print("Your Account Number is Already Exit!!");
                }
            }while (exAcc != null);
            System.out.print("Enter Account Holder Name :");
            String accountHolderName = br.readLine();
            System.out.print("Enter Phone Number :");
            String phone = br.readLine();
            System.out.print("Enter Balance :");
            double initialBalance = Double.parseDouble(br.readLine());

            Account account = new Account(accountNumber, accountHolderName, initialBalance, phone);
            masterAccountDB.add(account);
            System.out.print(account);
            System.out.print("Do You Want to Create New Account YES/NO?");
            flag = br.readLine();
        }while(flag.equalsIgnoreCase("yes"));

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