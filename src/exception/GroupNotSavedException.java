package exception;

public class GroupNotSavedException extends RuntimeException {
	
	public GroupNotSavedException() {
		super("Kamera konnte nicht gespeichert werden!");
	}
}
