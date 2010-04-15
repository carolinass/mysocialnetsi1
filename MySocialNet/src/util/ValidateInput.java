package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

import exception.FileNotExportedException;
import exception.InvalidFieldException;
import exception.InvalidFileException;
import exception.RequiredFieldException;

/**
 * Classe reponsavel por validar os inputs
 * @author Ana Carolina Cabral
 * @author Pedro Saraiva
 * @author Tiago
 * @author Ettore
 * @author Emerson
 */
public class ValidateInput{

	protected void validateUser(String name, String lastName, String email, String passwd) throws Exception{
		if (name == null || name.isEmpty()) throw new RequiredFieldException("Nome do usuário deve ser informado");
		if (lastName == null || lastName.isEmpty()) throw new RequiredFieldException("Sobrenome do usuário deve ser informado");
		if (email == null || email.isEmpty()) throw new RequiredFieldException("E-mail do usuário deve ser informado");
		if (passwd == null || passwd.isEmpty()) throw new RequiredFieldException("Senha deve ser informada");

		if(passwd.length() < 6) throw new InvalidFieldException("A senha deve ter pelo menos 6 dígitos");
		validateEmail(email);
	}

	protected void validateEmail(String email)throws Exception{
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

	protected void validateFile(String fileName, String message) throws Exception{
		if (fileName == null || fileName.isEmpty()) throw new FileNotExportedException(message);
		if(!fileName.substring(0,6).equals("export")) throw new FileNotExportedException(message);
	}

	protected void validateFileReader(String file) throws Exception{
		BufferedReader reader = new BufferedReader(new FileReader(file));

		String[] anterior = reader.readLine().split(",");
		
		while(true){
			String atualString = reader.readLine();
			if(atualString == null)
				break;
			String[] atual = atualString.split(",");
			if(atual.length != anterior.length) throw new InvalidFileException("Arquivo não suportado pelo sistema");
		}
		
		reader.close();
	}
}
