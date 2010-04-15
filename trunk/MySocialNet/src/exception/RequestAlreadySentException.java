package exception;

/**
 * @author Ana Carolina
 * @author Pedro Saraiva
 * @author Tiago
 * @author Ettore
 * @author Emerson
 */
public class RequestAlreadySentException extends Exception{

	public RequestAlreadySentException(String motivo){
		super(motivo);
	}
	
}
