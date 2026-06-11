package banking.exception;

public class DuplicateAccountException extends BankException {
    public DuplicateAccountException(String message){
        super(message);
    }
}
