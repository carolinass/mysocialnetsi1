package exception;

public class UserNotFoundException extends Exception {

	public UserNotFoundException(String motivo){
		super(motivo);
	}
}
