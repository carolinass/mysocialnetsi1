package exception;

public class WrongPasswdException extends Exception{

	public WrongPasswdException(String motivo){
		super(motivo);
	}
}
