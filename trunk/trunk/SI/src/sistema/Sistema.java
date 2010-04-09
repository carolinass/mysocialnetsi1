package sistema;

import usuario.Usuario;
import util.ValidateInput;
import java.util.ArrayList;

import exception.RequiredFieldException;
import exception.UserAlreadyOnException;
import exception.UserNotFoundException;
import exception.UserOffException;
import exception.UserAlreadyExistsException;
import exception.WrongPasswdException;

/**
 * MySocialNet
 * @author Ana Carolina Cabral
 * @author Pedro Saraiva
 * @author Tiago
 * @author Ettore
 * @author Emerson
 * @version 1.0
 */
public class Sistema extends ValidateInput{

	public ArrayList<Usuario> users = new ArrayList<Usuario>();

	/**
	 * Cria um novo usuario
	 * @param name
	 * @param lastName
	 * @param email
	 * @param passwd
	 * @throws RequiredFieldException 
	 */
	public void createUser(String name, String lastName, String email, String passwd) throws Exception{
		validateUser(name, lastName, email, passwd);
		validateLogin(email);
		Usuario user = new Usuario(name, lastName, email, passwd);
		this.users.add(user);
	}

	/**
	 * @param login
	 * @return nome e sobrenome
	 */
	public String getUser(String login) throws Exception{
		Usuario user = findUser(login);
		//		if(user == null) throw new UserNotFoundException("Login inexistente");
		if(user.getLoginStatus() == false) throw new UserOffException("Usu�rio n�o logado");
		return "Nome=" + user.getNome() + ",Sobrenome=" + user.getSobrenome();
	}

	/**
	 * Realiza a busca de um usuario
	 * @param email
	 * @return Usuario user
	 * @throws Exception
	 */
	private Usuario findUser(String email) throws Exception{
		Usuario user = null;
		for (Usuario u : users){
			if(u.email.equals(email)){
				user = u;
				return user;
			}
		}
		return user;
	}

	/**
	 * verifica se a senha dada e a mesma do usuario 
	 * @param usuario
	 * @param passwd
	 * @return true or false
	 */
	private boolean senhaCorreta(String senha, Usuario user){
		if(user.getSenha().equals(senha)){
			return true;
		}
		return false;
	}

	/**
	 * Faz o login de um usuario
	 * @param email
	 * @param passwd
	 * @throws Exception
	 */
	public void login(String email, String senha) throws Exception{
		Usuario user = findUser(email);

		if(user == null){
			throw new UserNotFoundException("Login inv�lido ou senha incorreta");
		}

		if(user.isLogado){
			throw new UserAlreadyOnException("Usu�rio j� logado");
		}

		if(senhaCorreta(senha, user)){
			user.setLoginStatus(true);
		}else{
			throw new WrongPasswdException("Login inv�lido ou senha incorreta");
		}
	}

	/**
	 * Faz o logoff de um usuario
	 * @param email
	 * @param passwd
	 * @throws Exception
	 */
	public void logoff(String login) throws Exception{
		Usuario user = findUser(login);
		if(user == null) throw new UserNotFoundException("Login inv�lido");
		if(!userIsLogado(user)) throw new UserOffException("Usu�rio n�o logado");

		user.setLoginStatus(false);

	}

	/**
	 * Verifica se o login solicitado ja existe
	 * @param email
	 * @throws Exception
	 */
	private void validateLogin(String email) throws Exception{
		for (Usuario user : users){
			if(user.email.equals(email)) throw new UserAlreadyExistsException("Login indispon�vel");
		}
	}

	/**
	 * Verifica se um usuario esta logado
	 * @param user
	 * @return true or false
	 */
	private boolean userIsLogado(Usuario user){
		return user.getLoginStatus();
	}

	/**
	 * Atualiza o profile do usuario
	 * @param login
	 * @param aboutMe
	 * @param age
	 * @param photo
	 * @param country
	 * @param city
	 * @param gender
	 * @param contactEmail
	 * @throws Exception
	 */
	public void updateUserProfile(String login, String aboutMe, String age, String photo, String country, 
			String city, String gender, String contactEmail) throws Exception{
		Usuario user = findUser(login);
		if(!userIsLogado(user)) throw new UserOffException("Usu�rio n�o logado");
		if(aboutMe == null) aboutMe = "";

		user.perfil.setAboutMe(aboutMe);
		user.perfil.setAge(age);
		user.perfil.setPhoto(photo);
		user.perfil.setCountry(country);
		user.perfil.setCity(city);
		user.perfil.setGender(gender);
		user.perfil.setContactEmail(contactEmail);

	}

	/**
	 * Muda a permissao do campo
	 * @param login
	 * @param field
	 * @param type
	 * @throws Exception
	 */
	public void setFieldPrivacy(String login, String field, String type) throws Exception{
		Usuario user = findUser(login);
		if(!userIsLogado(user)) throw new UserOffException("Usu�rio n�o logado");

		user.changeFieldPermission(field, type);
	}

	//	checkProfile login="dev@email.com" visibility="ALL"

	public String checkProfile(String login, String visibility) throws Exception{
		Usuario user = findUser(login);
		if(!userIsLogado(user)) throw new UserOffException("Usu�rio n�o logado");

		return user.checkProfile(visibility);		

	}

	//	/**
	//	 * Remove um usuario do sistema
	//	 * @param user
	//	 */
	//	public void deleteUser(Usuario user){
	//		boolean achouUsuario = false;
	//		for (int i = 0; i < users.size(); i++){
	//			if(users.get(i).equals(user)){
	//				users.remove(i);
	//				achouUsuario = true;
	//				break;
	//			}
	//		}
	//	}


}
