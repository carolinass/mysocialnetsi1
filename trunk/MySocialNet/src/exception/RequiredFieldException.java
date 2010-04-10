package exception;

public class RequiredFieldException extends Exception {
	
	public RequiredFieldException(String motivo){
		super(motivo);
	}

}
