package exception;

public class CamNotDeletedException extends RuntimeException {
	
	public CamNotDeletedException(Long id) {
		super("Cam mit der Id " + id + " konnte nicht ge�ndert werden!");
	}
}
