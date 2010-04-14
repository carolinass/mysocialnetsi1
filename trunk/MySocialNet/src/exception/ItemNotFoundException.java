package exception;

public class ItemNotFoundException extends Exception {

	public ItemNotFoundException(String motivo){
		super(motivo);
	}
}
