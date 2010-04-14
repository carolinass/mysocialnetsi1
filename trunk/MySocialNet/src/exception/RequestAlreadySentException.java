package exception;

public class RequestAlreadySentException extends Exception{

	public RequestAlreadySentException(String motivo){
		super(motivo);
	}
	
}
