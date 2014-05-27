package exception;

public class UserNotSavedException extends RuntimeException {
	
	public UserNotSavedException() {
		super("Benutzer konnte nicht gespeichert werden!");
	}
}
