package exception;

public class CamNotSavedException extends RuntimeException {
	
	public CamNotSavedException() {
		super("Kamera konnte nicht gespeichert werden!");
	}
}
