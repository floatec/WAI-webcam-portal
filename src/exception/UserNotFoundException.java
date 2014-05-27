package exception;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(Long id) {
		super("Benutzer mit der Id " + id + " wurde nicht gefunden!");
	}
	
	public UserNotFoundException(String name) {
		super("Benutzer mit der Id " + name + " wurde nicht gefunden!");
	}
	
	public UserNotFoundException() {
		super("Benutzer k�nnen nicht aufgelistet werden!");
	}
}
