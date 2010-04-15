package sistema;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import usuario.Field;
import usuario.Grupo;
import usuario.Pedido;
import usuario.Usuario;
import util.ValidateInput;
import exception.AlreadyFriendsException;
import exception.EmptyGroupException;
import exception.InvalidFileException;
import exception.ItemNotFoundException;
import exception.RequestAlreadySentException;
import exception.RequiredFieldException;
import exception.UserAlreadyExistsException;
import exception.UserAlreadyOnException;
import exception.UserOffException;
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
		Usuario user = trataUsuario(login, "Login inexistente", "Usuário não logado");
		return "Nome=" + user.getNome() + ",Sobrenome=" + user.getSobrenome();
	}

	/**
	 * Realiza a busca de um usuario
	 * @param email
	 * @return Usuario user
	 * @throws Exception
	 */
	public Usuario findUser(String email) throws Exception{
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

		if(user == null)
			throw new ItemNotFoundException("Login inválido ou senha incorreta");

		if(user.isLogado)
			throw new UserAlreadyOnException("Usuário já logado");

		if(senhaCorreta(senha, user)){
			user.setLoginStatus(true);
		}else{
			throw new WrongPasswdException("Login inválido ou senha incorreta");
		}
	}


	/**
	 * Faz o logoff de um usuario
	 * @param email
	 * @param passwd
	 * @throws Exception
	 */
	public void logoff(String login) throws Exception{
		Usuario user = trataUsuario(login, "Login inválido", "Usuário não logado");
		user.setLoginStatus(false);
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
		Usuario user = trataUsuario(login, "Login inexistente", "Usuário não logado");
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
		Usuario user = trataUsuario(login, "Login inexistente", "Usuário não logado");	
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
		Usuario user = trataUsuario(login, "Perfil inexistente", "Usuário não logado");

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
		Usuario user = trataUsuario(login, "Login inexistente", "Usuário não logado");
		user.addPreference(preference);
	}

	/**
	 * Exibe o profile de um usuario
	 * @param viewer
	 * @param profileOwner
	 * @return profile do usuario
	 * @throws Exception
	 */
	public String viewProfile(String viewer, String profileOwner) throws Exception{

		Usuario visualizado = findUser(profileOwner);
		if(visualizado == null) 
			throw new ItemNotFoundException("Perfil inexistente");

		Usuario visualizador = trataUsuario(viewer, "Login do viewer não existente no sistema", "Usuário não logado");

		//verifica permissao
		String saida = "";
		ArrayList<Field> campos = new ArrayList<Field>();

		if(visualizado.isFriend(visualizador)){
			campos = visualizado.getCampos("FRIENDS");
		}else{
			campos = visualizado.getCampos("ALL");
		}

		for(Field campo : campos){
			saida += campo.getName() + "=" + campo.getField();
			if(!campo.equals(campos.get(campos.size()-1)))
				saida += ",";
		}		
		return saida;

	}

	/**
	 * Remove uma preferencia da lista de preferencias do usuario
	 * @param login
	 * @param preference
	 * @throws Exception
	 */
	public void removeUserPreference(String login, String preference) throws Exception {
		Usuario user = trataUsuario(login, "Login inexistente", "Usuário não logado");
		user.removePreference(preference);

	}

	/**
	 * Lista as preferencias do usuario
	 * @param login
	 * @return lista de preferencias do usuario
	 * @throws Exception
	 */
	public String listUserPreferences(String login) throws Exception {
		Usuario user = trataUsuario(login, "Login inexistente", "Usuário não logado");		
		return user.listPreferences();	
	}

	/**
	 * Remove um usuario do sistema
	 * @param user
	 */
	public void deleteUser(String login)throws Exception{
		if(login.equals(""))
			throw new RequiredFieldException("Login não pode ser vazio");

		Usuario user = trataUsuario(login, "Login inexistente", "Usuário não logado");		
		users.remove(user);
	}

	public String listFriends(String email) throws Exception {
		Usuario user = trataUsuario(email, "Login inexistente", "Usuário não logado");

		return user.listFriends();
	}

	/**
	 * Encontra um novo amigo
	 * @param login
	 * @param friend
	 * @return profile do usuario procurado
	 * @throws Exception
	 */
	public String findNewFriend(String login, String friend)throws Exception {

		try{
			validateEmail(friend);
		}catch(Exception e){
			return searchFriend(login, friend);
		}

		Usuario visualizador = trataUsuario(login, "Login inexistente", "Usuário não logado");
		Usuario visualizado = findUser(friend);

		return visualizado.getNome() + " " + visualizado.getSobrenome() + " - Profile: " + 
		viewProfile(visualizador.getEmail(), visualizado.getEmail());

	}

	/**
	 * Procura um novo amigo atraves do nome e do sobrenome
	 * @param login
	 * @param friend
	 * @return Profile do amigo
	 * @throws Exception
	 */
	private String searchFriend(String login, String friend) throws Exception {
		String saida = "";

		for(Usuario user : this.users){
			if(((user.getNome() + " " + user.getSobrenome()).toLowerCase()).equals(friend.toLowerCase())){
				saida = user.getNome() + " " + user.getSobrenome() + " - Profile: " + 
				viewProfile(login, user.getEmail());
			}
		}

		return saida;
	}

	/**
	 * Envia um pedido de amizade a um usuario
	 * @param login
	 * @param user
	 * @param mensagem
	 * @param grupo
	 * @throws Exception
	 */
	public void sendFriendshipRequest(String login, String user, String mensagem, String grupo) throws Exception {

		Usuario convidado = findUser(user);
		if(convidado == null){
			throw new ItemNotFoundException("Contato inexistente");
		}

		Usuario usuario = this.trataUsuario(login, "Login inexistente", "Usuário não logado");

		if(login.equals(user))
			throw new ItemNotFoundException("Operação não permitida");

		if(convidado.isFriend(usuario)){
			throw new AlreadyFriendsException("Usuários "+ usuario.getNome()+ " " 
					+ usuario.getSobrenome()+ " e " + convidado.getNome() + " " 
					+ convidado.getSobrenome()+ " já são amigos");
		}

		usuario.validateRequest(convidado);
		usuario.sendRequest(usuario, convidado, mensagem, grupo);
	}

	/**
	 * Exibe os pedidos de amizade pendentes
	 * @param login
	 * @return pedidos pendentes
	 * @throws Exception
	 */
	public String viewPendingFriendship(String login) throws Exception {

		Usuario usuario = trataUsuario(login, "Login inexistente", "Usuário não logado");
		String saida = "";

		if(usuario.convitesPendentesRecebidos.isEmpty()){
			return "Não há nenhuma solicitação de amizade pendente";
		}

		for(Pedido pedido : usuario.convitesPendentesRecebidos){
			Usuario pedinte = pedido.getPedinte();
			String informacao = pedinte.getNome() + " " + pedinte.getSobrenome() +
			" <" + pedinte.getEmail() + "> - mensagem: " + pedido.getMessage();
			saida += informacao;
		}

		return saida;
	}

	/**
	 * Exibe os pedidos de amizade enviados
	 * @param login
	 * @return pedidos de amizade enviados
	 * @throws Exception
	 */
	public String viewSentFriendship(String login) throws Exception {

		Usuario usuario = trataUsuario(login, "Login inexistente", "Usuário não logado");
		String saida = "";

		if(usuario.convitesPendentesEnviados.isEmpty())
			return "Não há nenhuma solicitação de amizade pendente";

		for(Pedido pedido : usuario.convitesPendentesEnviados){
			String pendente = pedido.getRequisitado().getEmail();
			saida += pendente + ",";
		}

		return saida.substring(0,saida.length() - 1);
	}

	/**
	 * 
	 * @param login
	 * @param contact
	 * @throws Exception
	 */
	public void declineFriendshipRequest(String login, String contact) throws Exception {

		Usuario usuario = trataUsuario(login, "Login inexistente", "Usuário não logado");
		Usuario contato = findUser(contact);

		if(usuario.convitesPendentesRecebidos.isEmpty())
			throw new EmptyGroupException("Não há nenhuma solicitação de amizade pendente");

		usuario.removeRequest(contato);
		contato.removeSend(usuario);
	}

	/**
	 * Aceita um pedido de amizade
	 * @param login
	 * @param contact
	 * @param group
	 * @throws Exception
	 */
	public void acceptFriendshipRequest(String login, String contact, String group) throws Exception{
		Usuario usuario = trataUsuario(login, "Login inexistente", "Usuário não logado");
		Usuario contato = findUser(contact);
		String grupo = "";


		for(int i = 0; i< contato.convitesPendentesEnviados.size(); i++){
			if(contato.convitesPendentesEnviados.get(i).getRequisitado().equals(usuario)){
				grupo = contato.convitesPendentesEnviados.get(i).getGrupo().getName();
			}

		}
		contato.acceptRequest(usuario,grupo);
		usuario.acceptRequest(contato, group);
		usuario.removeRequest(contato);
		contato.removeSend(usuario);
	}

	public String findGroupMember(String login, String friend, String group) throws Exception{

		try{
			validateEmail(friend);
		}catch(Exception e){
			return searchGroupMember(login, friend, group);
		}

		Usuario usuario = trataUsuario(login, "Login inexistente", "Usuário não logado");
		Usuario amigo = findUser(friend);
		return usuario.findGroupMember(amigo, group);
	}

	public String searchGroupMember(String login,String friend,String group) throws Exception{


		Usuario usuario = trataUsuario(login, "Login inexistente", "Usuário não logado");
		String saida = "Nome=";

		for(Usuario user : this.users){

			if(((user.getNome() + " " + user.getSobrenome()).toLowerCase()).equals(friend.toLowerCase())){
				saida = usuario.findGroupMember(user, group);
			}
		}
		return saida;
	}

	public void removeFriend(String email, String friend) throws Exception {
		Usuario usuario = trataUsuario(email, "Login inexistente", "Usuário não logado");;
		Usuario amigo = findUser(friend);

		if (amigo == null){
			throw new ItemNotFoundException("Amigo não existente no sistema");
		}
		if(!usuario.isFriend(amigo)){
			throw new ItemNotFoundException("Amigo não encontrado em nenhum grupo");
		}

		usuario.removerAmigo(amigo);
		amigo.removerAmigo(usuario);
	}

	/**
	 * Lista membros do grupo
	 * @param email
	 * @param group
	 * @return lista de membros do grupo
	 * @throws Exception
	 */
	public String listGroupMembers(String email, String group) throws Exception {
		Usuario usuario = trataUsuario(email, "Login inexistente", "Usuário não logado");
		String saida = "";

		if(usuario.getGrupo(group) == null){
			throw new ItemNotFoundException("Grupo " + group + " não existe");
		}
		ArrayList<Usuario>lista = usuario.getGrupo(group).listGrupo();

		for(int i =0; i< lista.size();i++){
			saida += lista.get(i).getNome() + " " + lista.get(i).getSobrenome()+ ",";
		}
		if(saida.equals("")){
			return "";
		}
		return saida.substring(0, saida.length()-1);
	}

	/**
	 * Adiciona um membro a um grupo
	 * @param email
	 * @param group
	 * @param user
	 * @throws Exception
	 */
	public void addGroupMember(String email, String group, String user) throws Exception {
		Usuario usuario = trataUsuario(email, "Login inexistente", "Usuário não logado");
		Usuario amigo = findUser(user);

		if(usuario.getGrupo(group) == null){
			throw new ItemNotFoundException("Grupo " + group + " não existe");
		}
		if (amigo == null){
			throw new ItemNotFoundException("Usuário a ser adicionado inexistente no sistema");
		}

		ArrayList<Usuario>lista = usuario.getGrupo(group).listGrupo();

		for(int i =0; i< lista.size();i++){
			if(user.equals(lista.get(i).getEmail())){
				throw new UserAlreadyExistsException("Contato já existente no grupo " + group);
			}
		}
		usuario.amigos.get(amigo).removeFriend(amigo);
		usuario.amigos.put(amigo, usuario.getGrupo(group));
		usuario.getGrupo(group).addFriend(amigo);
	}

	/**
	 * Remove um membro do grupo
	 * @param email
	 * @param group
	 * @param user
	 * @throws Exception
	 */
	public void removeGroupMember(String email, String group, String user) throws Exception {

		Usuario usuario = trataUsuario(email, "Login inexistente", "Usuário não logado");
		Usuario amigo = findUser(user);

		if(amigo == null){
			throw new ItemNotFoundException("Usuário a ser removido inexistente no sistema");
		}

		if(usuario.getGrupo(group) == null){
			throw new ItemNotFoundException("Grupo " + group + " não existe");
		}

		Grupo grupoaRemover = usuario.getGrupo(group);
		Grupo grupoaAdicionar = usuario.conhecidos;

		if(!grupoaRemover.isInGrupo((amigo))){
			throw new ItemNotFoundException("Contato não existente no grupo " + grupoaRemover.getName());
		}


		grupoaRemover.removeFriend(amigo);
		grupoaAdicionar.addFriend(amigo);


	}

	/**
	 * Recomemda amigos a um usuario
	 * @param login
	 * @return amigos recomendados
	 * @throws Exception
	 */
	public String getRecommendFriends(String login)throws Exception {
		String saida = "";
		Usuario usuario = trataUsuario(login, "Login inexistente", "Usuário não logado");

		ArrayList<Usuario> maiorAfinidade = new ArrayList<Usuario>();
		ArrayList<Usuario> recomendado = new ArrayList<Usuario>();

		//grupo de afinidade com familia
		for(int i = 0; i<usuario.familia.size(); i++){
			Usuario melhores = usuario.familia.getUser(i);

			for(int j = 0; j <melhores.familia.size() ; j++ ){				
				maiorAfinidade.add(melhores.familia.getUser(j));
			}

			for(int j = 0; j <melhores.melhoresAmigos.size() ; j++ ){
				maiorAfinidade.add(melhores.melhoresAmigos.getUser(j));
			}					
		}

		//grupo de afinidade com melhoresAmigos
		for(int i = 0; i<usuario.melhoresAmigos.size(); i++){
			Usuario melhores = usuario.melhoresAmigos.getUser(i);

			for(int j = 0; j <melhores.familia.size() ; j++ ){
				maiorAfinidade.add(melhores.familia.getUser(j));
			}

			for(int j = 0; j <melhores.melhoresAmigos.size() ; j++ ){
				maiorAfinidade.add(melhores.melhoresAmigos.getUser(j));
			}					
		}

		for(int u = 0; u < maiorAfinidade.size(); u ++){
			if(ehRecomendado(usuario, maiorAfinidade.get(u))){
				recomendado.add(maiorAfinidade.get(u));
			}
		}


		for(int u = 0; u< recomendado.size(); u++){
			saida += recomendado.get(u).getNome() + " " + recomendado.get(u).getSobrenome() + ",";
		}

		if(saida == ""){
			return "";
		}
		return saida.substring(0, saida.length()-1);
	}


	//*******************
	//*   UTILITARIOS   *
	//*******************

	/**
	 * Implementacao do BubbleSort
	 * @param amigos
	 */
	public Object[] ordenaBubble(Object[] amigos) {
		for (int i = 0; i < amigos.length-1; i++) {
			for (int j = 0; j < amigos.length-1; j++) {
				if (((Usuario) amigos[j]).getNome().compareTo(((Usuario) amigos[j+1]).getNome()) > 0) {
					swap(amigos, j, j+1);
				}
			}
		}
		return amigos;
	}

	/**
	 * Troca posicao de elementos num vetor
	 * @param amigos
	 * @param i
	 * @param j
	 */
	public void swap(Object[] amigos, int i, int j){
		Object temp = amigos[i];
		amigos[i] = amigos[j];
		amigos[j] = temp;
	}

	/**
	 * Verifica se existe alguma irregularidade no usuario
	 * @param login
	 * @param message
	 * @param message2
	 * @return Usuario usuario
	 * @throws Exception
	 */
	private Usuario trataUsuario(String login, String message, 
			String message2) throws Exception{

		Usuario user = findUser(login);
		if(user == null) throw new ItemNotFoundException(message);
		if(!userIsLogado(user)) throw new UserOffException(message2);

		return user;
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

	public String getFriend(String email, String friend) throws Exception {

		Usuario usuario = trataUsuario(email, "Login inexistente", "Usuário não logado");

		try{
			validateEmail(friend);
		}catch(Exception e){
			return findFriend(usuario, friend);
		}

		Usuario amigo = findUser(friend);

		if (!usuario.isFriend(amigo)){
			return null;
		}
		return view(email,friend);
	}

	private String view(String email, String friend) throws Exception{

		Usuario amigo = findUser(friend);
		return "Nome=" + amigo.getNome()+ ",Sobrenome=" + amigo.getSobrenome()+ " " 
		+ viewProfile(email,friend);
	}

	private String findFriend(Usuario usuario, String friend) throws Exception {
		String saida = "";

		for(Usuario user : usuario.amigos.keySet()){
			if(((user.getNome() + " " + user.getSobrenome()).toLowerCase()).equals(friend.toLowerCase())){
				saida = "Nome=" + user.getNome()+ ",Sobrenome=" + user.getSobrenome()+ " " 
				+ viewProfile(usuario.getEmail(), user.getEmail());
			}
		}

		if(saida.equals("")) return null;
		return saida;
	}

	/**
	 * Verifica se um usuario eh recomendado para ser amigo de outro
	 * @param usuario
	 * @param usuario2
	 * @return true or false
	 */
	private boolean ehRecomendado(Usuario usuario, Usuario usuario2) {

		if (usuario.isFriend(usuario2)){
			return false;
		}
		if(usuario.equals(usuario2)){
			return false;
		}

		ArrayList<String> uniao = usuario2.preferences;
		ArrayList<String> interseccao = new ArrayList<String>();

		for( int i = 0; i< usuario.preferences.size(); i++){
			if(usuario2.preferences.contains(usuario.preferences.get(i))){
				interseccao.add(usuario.preferences.get(i));
			}else{
				uniao.add(usuario.preferences.get(i));
			}
		}
		if(uniao.size()==0){
			return true;
		}

		if(interseccao.size()/uniao.size() >= 0.35){
			return true;
		}

		return false;
	}

	public void clean() {
		users.clear();	
	}


	//****************************
	//*   MANIPULACAO DE DADOS   *
	//****************************

	/**
	 * Exporta usuarios
	 * @param login
	 * @param fileName
	 * @param exportedFields
	 * @throws Exception
	 */
	public void exportFriendList(String login, String fileName,
			String exportedFields) throws Exception {

		Usuario usuario = trataUsuario(login, "Login inexistente", "Usuário não logado");
		validateFile(fileName, "Falha na exportação do arquivo");

		if(exportedFields.isEmpty()){
			exportFriendList(usuario, fileName);
			return;
		}

		String[] fields = exportedFields.split(",");

		Object[] amigos = usuario.amigos.keySet().toArray();
		amigos = ordenaBubble(amigos);

		exportar(fileName, fields, amigos);
	}

	/**
	 * Exporta usuarios
	 * @param login
	 * @param fileName
	 * @param exportedFields
	 * @throws Exception
	 */
	public void exportFriendList(Usuario usuario, String fileName) throws IOException{
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));

		String linha = "name" + "," + "lastName";
		out.write(linha + "\n");
		Object[] amigos = usuario.amigos.keySet().toArray();
		amigos = ordenaBubble(amigos);

		for(Object user : amigos){
			linha = ((Usuario)user).getNome() + "," + ((Usuario)user).getSobrenome();
			out.write(linha + "\n");
		}

		out.close();
	}

	/**
	 * Exporta usuarios
	 * @param fileName
	 * @param fields
	 * @param amigos
	 * @throws Exception
	 */
	private void exportar(String fileName, String[] fields, Object[] amigos) throws Exception{		
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));

		String linha = "name" + "," + "lastName";
		for(String field : fields){
			linha += "," + field;
		}

		out.write(linha + "\n");

		for(Object user : amigos){
			linha = ((Usuario)user).getNome() + "," + ((Usuario)user).getSobrenome();
			for(String field : fields){
				Field campo = ((Usuario)user).getCampo(field);
				linha += "," + campo.getField();
			}
			out.write(linha + "\n");
			linha = "";
		}

		out.close();
	}

	public String restoreFriendList(String login, String file) throws Exception {
		Usuario usuario = trataUsuario(login, "Login inexistente", "Usuário não logado");
		validateFile(file, "Arquivo não encontrado");
		validateFileReader(file);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		String linha = reader.readLine();
		String saida = "Contatos não importados: ";
		
		try {
			while((linha=reader.readLine()) != null) {
				String[] linhaLista = linha.split(",");
				String amigoString = linhaLista[0] + " " + linhaLista[1];
				
				Usuario amigo = getFriend(amigoString);
				
				if(amigo == null) saida += amigoString + ",";
				else{
					try{
						usuario.validateRequest(amigo);
						String message = "Olá, " + amigo.getEmail() + " me adicione como seu amigo.";
						usuario.sendRequest(usuario, amigo, message, "conhecidos");
					}catch(Exception e){
						saida = saida + e.getMessage() + ",";
					}
				}
			}
		}
		finally {
			reader.close();
		}
		if(saida.equals("Contatos não importados: ")) return "Todos os contatos foram importados";
		return saida.substring(0, saida.length()-1);
	}

	private Usuario getFriend(String amigoString) {
		for(Usuario user : this.users){
			if((user.getNome() + " " + user.getSobrenome()).equals(amigoString))
				return user;
		}
		return null;
	}

}
