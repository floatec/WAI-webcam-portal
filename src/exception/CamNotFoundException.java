package exception;

public class CamNotFoundException extends RuntimeException {
	
	public CamNotFoundException(Long id) {
		super("Cam mit der Id " + id + " wurde nicht gefunden!");
	}
	
	public CamNotFoundException() {
		super("Cams k�nnen nicht aufgelistet werden!");
	}
}
