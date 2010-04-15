package exception;
/**
 * @author Ana Carolina
 * @author Pedro Saraiva
 * @author Tiago
 * @author Ettore
 * @author Emerson
 */
public class WrongPasswdException extends Exception{

	public WrongPasswdException(String motivo){
		super(motivo);
	}
}
