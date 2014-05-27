package exception;

public class GroupNotSavedException extends RuntimeException {
	
	public GroupNotSavedException() {
		super("Benutzer konnten der Gruppe nicht zugeordnet werden!");
	}
}
