package exception;

public class CamNotSavedException extends RuntimeException {
	
	public CamNotSavedException() {
		super("Berechtigungen der User in der Gruppe konnten nicht gespeichert werden!");
	}
}
