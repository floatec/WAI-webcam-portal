package exception;

public class CamNotSavedException extends RuntimeException {
	
	public CamNotSavedException() {
		super("Cam konnte nicht gespeichert werden!");
	}
}
