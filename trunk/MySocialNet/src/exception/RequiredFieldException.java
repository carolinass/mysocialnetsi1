package exception;
/**
 * @author Ana Carolina
 * @author Pedro Saraiva
 * @author Tiago
 * @author Ettore
 * @author Emerson
 */
public class RequiredFieldException extends Exception {
	
	public RequiredFieldException(String motivo){
		super(motivo);
	}

}
