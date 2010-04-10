package sistema;

import usuario.Field;
import usuario.Usuario;
import util.ValidateInput;
import java.util.ArrayList;

import exception.RequiredFieldException;
import exception.UserNotFoundException;
import exception.UserOffException;
import exception.UserAlreadyExistsException;

/**
 * MySocialNet
 * @author Ana Carolina Cabral
 * @author Pedro Saraiva
 * @author Tiago
 * @author Ettore
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
		if(user == null) throw new UserNotFoundException("Login inexistente");
		if(user.getLoginStatus() == false) throw new UserOffException("Usuário não logado");
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
//		if(user == null) throw new UserNotFoundException("Login inexistente");
		return user;
	}
	
	/**
	 * Faz o login de um usuario
	 * @param email
	 * @param passwd
	 * @throws Exception
	 */
	public void login(String email, String passwd) throws Exception{
		Usuario user = findUser(email);
		if(user == null) throw new UserNotFoundException("Login inexistente");
		
		if(user.getSenha().equals(passwd)){
			user.setLoginStatus(true);
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
		if(user == null) throw new UserNotFoundException("Login inexistente");
		if(!userIsLogado(user)) throw new UserOffException("Usuário não logado");
		
		user.setLoginStatus(false);
		
	}
	
	/**
	 * Verifica se o login solicitado ja existe
	 * @param email
	 * @throws Exception
	 */
	private void validateLogin(String email) throws Exception{
		for (Usuario user : users){
			if(user.email.equals(email)) throw new UserAlreadyExistsException("Login indisponível");
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
		if(user == null) throw new UserNotFoundException("Login inexistente");
		if(!userIsLogado(user)) throw new UserOffException("Usuário não logado");
		if(aboutMe == null) aboutMe = "";
		
		user.perfil.setContactEmail(contactEmail);
		user.perfil.setAboutMe(aboutMe);
		user.perfil.setAge(age);
		user.perfil.setPhoto(photo);
		user.perfil.setCountry(country);
		user.perfil.setCity(city);
		user.perfil.setGender(gender);
		
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
		if(user == null) throw new UserNotFoundException("Login inexistente");
		if(!userIsLogado(user)) throw new UserOffException("Usuário não logado");
		
		user.changeFieldPermission(field, type);
	}
	
	/**
	 * Checa os campos do profile que tem determinada visibilidade
	 * @param login
	 * @param visibility
	 * @return campos
	 * @throws Exception
	 */
	public String checkProfile(String login, String visibility) throws Exception{
		Usuario user = findUser(login);
		if(user == null) throw new UserNotFoundException("Perfil inexistente");
		if(!userIsLogado(user)) throw new UserOffException("Usuário não logado");
		
		String saida = "";
		ArrayList<Field> campos = user.checkProfile(visibility);
		for(Field campo : campos){
			saida += campo.getName() + "=" + campo.getField();
			if(!campo.equals(campos.get(campos.size()-1)))
				saida += ",";
		}
		return saida;		
		
	}
	
	/**
	 * Adiciona uma preferencia a lista de preferencias do usuario
	 * @param login
	 * @param preference
	 * @throws Exception
	 */
	public void addUserPreference(String login, String preference) throws Exception{
		Usuario user = findUser(login);
		if(user == null) throw new UserNotFoundException("Login inexistente");
		if(!userIsLogado(user)) throw new UserOffException("Usuário não logado");
		
		user.addPreference(preference);
	}
	
	public String viewProfile(String viewer, String profileOwner) throws Exception{
		
		Usuario visualizador = findUser(viewer);
		if(visualizador == null) throw new UserNotFoundException("Login inexistente");
		if(!userIsLogado(visualizador)) throw new UserOffException("Usuário não logado");
		
		Usuario visualizado = findUser(viewer);
		if(visualizado == null) throw new UserNotFoundException("Login inexistente");
		
		//verifica permissao
		
		if(visualizado.isFriend(visualizador)){
			ArrayList<Field> campos = visualizado.getCampos("FRIENDS");
		}
		
		return "";
		
	}

	public void removeUserPreference(String login, String preference) throws Exception {
		Usuario user = findUser(login);
		if(user == null) throw new UserNotFoundException("Login inexistente");
		if(!userIsLogado(user)) throw new UserOffException("Usuário não logado");
		
		user.removePreference(preference);
		
	}

	public String listUserPreferences(String login) throws Exception {
		Usuario user = findUser(login);
		if(user == null) throw new UserNotFoundException("Login inexistente");
		if(!userIsLogado(user)) throw new UserOffException("Usuário não logado");
		
		return user.listPreferences();	
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
