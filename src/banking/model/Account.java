package banking.model;

public class Account {
    private String accountNumber;
    private String accountHolderName;
    private String phone;
    private double balance;

    public Account(String accountNumber, String accountHolderName, double balance){
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public Account(String accountNumber, String accountHolderName, double balance, String phone){
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.phone = phone;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString(){
        return "Account Number : "+ this.accountNumber+"\n"+
                "Account Holder Name : "+ this.accountHolderName+"\n"+
                "Balance : "+ this.balance+"\n"+
                "Phone :"+ this.phone+"\n";


    }
}
