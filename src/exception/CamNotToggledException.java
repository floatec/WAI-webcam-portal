package exception;

public class CamNotToggledException extends RuntimeException {
	
	public CamNotToggledException(Long id) {
		super("Kamera mit der Id " + id + " konnte nicht ge�ndert werden!");
	}
}
