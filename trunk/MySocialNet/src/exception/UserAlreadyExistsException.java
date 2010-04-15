package exception;
/**
 * @author Ana Carolina
 * @author Pedro Saraiva
 * @author Tiago
 * @author Ettore
 * @author Emerson
 */
public class UserAlreadyExistsException extends Exception {
	
	public UserAlreadyExistsException(String motivo){
		super(motivo);
	}

}
