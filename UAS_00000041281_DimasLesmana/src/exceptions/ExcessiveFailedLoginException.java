package exceptions;

public class ExcessiveFailedLoginException extends Exception {
	
	public ExcessiveFailedLoginException() {
		super("Login attempt limit exceeded!");
	}
	
	public ExcessiveFailedLoginException(String message) {
		super(message);
	}

}
