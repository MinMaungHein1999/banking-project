package banking.exception;

public class InvalidAmountException extends BankException{
    public InvalidAmountException(String str){
        super(str);
    }
}
