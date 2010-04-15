package exception;
/**
 * @author Ana Carolina
 * @author Pedro Saraiva
 * @author Tiago
 * @author Ettore
 * @author Emerson
 */
public class UserNotFoundException extends Exception {

	public UserNotFoundException(String motivo){
		super(motivo);
	}
}
