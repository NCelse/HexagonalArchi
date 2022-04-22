package sghexaddd.domain.exception;

public class AccountNotFoundException extends Exception { 
	
	private static final long serialVersionUID = -457462028195138967L;

	public AccountNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}