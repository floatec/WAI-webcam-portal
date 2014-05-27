package exception;

public class GroupNotFoundException extends RuntimeException {
	
	public GroupNotFoundException(Long id) {
		super("Grouppe mit der Id " + id + " wurde nicht gefunden!");
	}
	
	public GroupNotFoundException() {
		super("Gruppen können nicht aufgelistet werden!");
	}
}
