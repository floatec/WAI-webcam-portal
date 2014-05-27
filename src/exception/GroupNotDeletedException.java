package exception;

public class GroupNotDeletedException extends RuntimeException {
	
	public GroupNotDeletedException(Long id) {
		super("User in der Gruppe mit der Id " + id + " konnte die Berechtigung nicht entzogen werden werden!");
	}
}
