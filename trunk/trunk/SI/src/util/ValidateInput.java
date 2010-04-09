package util;

import exception.InvalidFieldException;
import exception.RequiredFieldException;

/**
 * Classe reponsavel por validar os inputs
 * @author Ana Carolina Cabral
 * @author Pedro Saraiva Verissimo
 * @author Tiago
 * @author Ettore
 * @author Emerson
 */
public class ValidateInput{

	public void validateUser(String name, String lastName, String email, String passwd) throws Exception{
		if (name == null || name.isEmpty()) throw new RequiredFieldException("Nome do usu�rio deve ser informado");
		if (lastName == null || lastName.isEmpty()) throw new RequiredFieldException("Sobrenome do usu�rio deve ser informado");
		if (email == null || email.isEmpty()) throw new RequiredFieldException("E-mail do usu�rio deve ser informado");
		if (passwd == null || passwd.isEmpty()) throw new RequiredFieldException("Senha deve ser informada");

		if(passwd.length() < 6) throw new InvalidFieldException("A senha deve ter pelo menos 6 d�gitos");
		validateEmail(email);
	}

	public void validateEmail(String email)throws Exception{
		boolean achouArroba = false;
		if(email.charAt(0) == '@') throw new InvalidFieldException("E-mail inv�lido");

		for (int i = 0; i < email.length(); i++) {
			if(email.charAt(i) == '@'){
				achouArroba = true;
				if(email.substring(i, email.length()).equals("@email.com"))
					break;
				else
					throw new InvalidFieldException("E-mail inv�lido");
			}
		}
		if (achouArroba == false) throw new InvalidFieldException("E-mail inv�lido");
	}

}
