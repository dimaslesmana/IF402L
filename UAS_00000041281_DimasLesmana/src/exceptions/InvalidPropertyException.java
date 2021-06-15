package exceptions;

public class InvalidPropertyException extends Exception {

	public InvalidPropertyException() {
		super("Invalid Input!");
	}
	
	public InvalidPropertyException(String message) {
		super(message);
	}
	
}
