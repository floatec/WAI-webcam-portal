package exception;

public class CamNotFoundException extends RuntimeException {
	
	public CamNotFoundException(Long id) {
		super("Kamera mit der Id " + id + " wurde nicht gefunden!");
	}
	
	public CamNotFoundException() {
		super("Kamera k�nnen nicht aufgelistet werden!");
	}
}
