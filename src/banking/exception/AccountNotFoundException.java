package banking.exception;

public class AccountNotFoundException extends BankException{
    public AccountNotFoundException(String message){
        super(message);
    }
}
