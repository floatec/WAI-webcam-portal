package exception;

public class UserNotDeletedException extends RuntimeException {
	
	public UserNotDeletedException(Long id) {
		super("Benutzer mit der Id " + id + " konnte nicht gelöscht werden!");
	}
}
