package exception;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(Long id) {
		super("Benutzer mit der Id " + id + " wurde nicht gefunden!");
	}
	
	public UserNotFoundException() {
		super("Benutzer können nicht aufgelistet werden!");
	}
}
