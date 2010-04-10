package util;

import exception.InvalidFieldException;
import exception.RequiredFieldException;

/**
 * Classe reponsavel por validar os inputs
 * @author Ana Carolina Cabral
 * @author Pedro Saraiva
 * @author Tiago
 * @author Ettore
 */
public class ValidateInput{

	public void validateUser(String name, String lastName, String email, String passwd) throws Exception{
		if (name == null || name.isEmpty()) throw new RequiredFieldException("Nome do usuário deve ser informado");
		if (lastName == null || lastName.isEmpty()) throw new RequiredFieldException("Sobrenome do usuário deve ser informado");
		if (email == null || email.isEmpty()) throw new RequiredFieldException("E-mail do usuário deve ser informado");
		if (passwd == null || passwd.isEmpty()) throw new RequiredFieldException("Senha deve ser informada");
		
		if(passwd.length() < 6) throw new InvalidFieldException("A senha deve ter pelo menos 6 dígitos");
		validateEmail(email);
	}
	
	public void validateEmail(String email)throws Exception{
		boolean achouArroba = false;
		if(email.charAt(0) == '@') throw new InvalidFieldException("E-mail inválido");
		
		for (int i = 0; i < email.length(); i++) {
			if(email.charAt(i) == '@'){
				achouArroba = true;
				if(email.substring(i, email.length()).equals("@email.com"))
					break;
				else
					throw new InvalidFieldException("E-mail inválido");
			}
		}
		if (achouArroba == false) throw new InvalidFieldException("E-mail inválido");
	}
	
}
