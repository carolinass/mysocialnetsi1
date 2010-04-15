package usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import exception.AlreadyFriendsException;
import exception.RequestAlreadySentException;

/**
 * Classe que implementa as funcoes de um usuario
 * @author Ana Carolina
 * @author Pedro Saraiva
 * @author Tiago
 * @author Ettore
 * @author Emerson
 */
public class Usuario {

	public String nome, sobrenome, email, senha;
	public Perfil perfil;
	public boolean isLogado;
	public ArrayList<String> preferences;
	public HashMap<Usuario, Grupo> amigos = new HashMap<Usuario, Grupo>();
	public ArrayList<Grupo> groups = new ArrayList<Grupo>();
	public Grupo conhecidos;
	public Grupo familia;
	public Grupo escola;
	public Grupo melhoresAmigos;
	public Grupo trabalho;
	public ArrayList<Pedido> convitesPendentesRecebidos = new ArrayList<Pedido>();
	public ArrayList<Pedido> convitesPendentesEnviados = new ArrayList<Pedido>();
	public ArrayList<String> mensagens = new ArrayList<String> ();


	/**
	 * Metodo Construtor
	 * @param nome
	 * @param sobrenome
	 * @param email
	 * @param senha
	 */
	public Usuario(String nome, String sobrenome, String email, String senha){
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
		this.isLogado = false;
		this.perfil = new Perfil();
		conhecidos = new Grupo("conhecidos");
		familia = new Grupo("familia");
		escola = new Grupo("escola");
		melhoresAmigos = new Grupo("melhores amigos");
		trabalho = new Grupo("trabalho");
		this.preferences = new ArrayList<String>();
		this.groups.add(conhecidos);
		this.groups.add(familia);
		this.groups.add(escola);
		this.groups.add(melhoresAmigos);
		this.groups.add(trabalho);
	}

	/**
	 * Retorna o nome do usuario
	 * @return String nome
	 */
	public String getNome(){
		return this.nome;
	}

	/**
	 * Retorna o sobrenome do usuario
	 * @return String sobrenome
	 */
	public String getSobrenome(){
		return this.sobrenome;
	}

	/**
	 * Retorna o email do usuario
	 * @return String email
	 */
	public String getEmail(){
		return this.email;
	}

	/**
	 * Retorna a senha do usuario
	 * @return String senha
	 */
	public String getSenha(){
		return this.senha;
	}

	/**
	 * Altera o nome do usuario
	 * @param nome
	 */
	public void setNome(String nome){
		this.nome = nome;
	}

	/**
	 * Altera o sobrenome do usuario
	 * @param sobrenome
	 */
	public void setSobrenome(String sobrenome){
		this.sobrenome = sobrenome;
	}

	/**
	 * Altera o email do usuario
	 * @param email
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * Altera a senha do usuario
	 * @param senha
	 */
	public void setSenha(String senha){
		this.senha = senha;
	}


	//***************************
	//* GERENCIAMENTO DE AMIGOS *
	//***************************

	/**
	 * Aceita convite de amizade
	 * @param amigo
	 * @param group
	 */
	public void acceptRequest(Usuario amigo, String group){
		Grupo grupo = getGrupo(group);
		grupo.addFriend(amigo);
		this.amigos.put(amigo, grupo);
		this.deleteAcceptReceiveRequest(amigo, this.convitesPendentesRecebidos);
		this.deleteAcceptReceiveRequest(amigo, this.convitesPendentesEnviados);
	}
	
	/**
	 * Deleta um convite recebido aceito
	 * @param amigo
	 * @param convites
	 */
	public void deleteAcceptReceiveRequest(Usuario amigo, ArrayList<Pedido> convites){
		for (int i = 0; i < convites.size(); i++) {
			if(convites.get(i).getPedinte().equals(amigo))
				convites.remove(i);
			break;
		}
	}

	/**
	 * Remove um amigo da lista de amigos
	 * @param amigo
	 */
	public void removerAmigo(Usuario amigo){		
		this.amigos.remove(amigo);
	}

	/**
	 * Verifica se um usuario eh amigo do outro
	 * @param visualizador
	 * @return true of false
	 */
	public boolean isFriend(Usuario visualizador) {
		for(Usuario user : amigos.keySet()){
			if(user.equals(visualizador)) return true;
		}
		return false;
	}
	
	//***************************************
	//* GERENCIAMENTO DE PEDIDOS DE AMIZADE *
	//***************************************
	
	/**
	 * Envia um pedido de amizade
	 * @param user
	 * @param friend
	 * @param message
	 * @param group
	 */
	public void sendRequest(Usuario user, Usuario friend, String message, String group){
		Grupo grupo = this.getGrupo(group);
		
		Pedido newRequest = new Pedido(user, friend, message, grupo);
		this.convitesPendentesEnviados.add(newRequest);
		friend.convitesPendentesRecebidos.add(newRequest);
	}
	
	/**
	 * Valida um pedido de amizade
	 * @param requisitado
	 * @throws RequestAlreadySentException
	 */
	public void validateRequest(Usuario requisitado) throws Exception{		
		if(this.isFriend(requisitado)) throw new AlreadyFriendsException("Usuários " + this.nome + " " + this.sobrenome +
				  								" e " + requisitado.getNome() + " " + requisitado.getSobrenome() + " já são amigos");
	
		for(Pedido pedido : this.convitesPendentesEnviados){
			if(pedido.getRequisitado().getEmail().equals(requisitado.getEmail()))
				throw new RequestAlreadySentException("Você já enviou um convite para o usuário " +
						  requisitado.getNome() + " " + requisitado.getSobrenome());
		}
	}
	
	/**
	 * Deleta um pedido
	 * @param contato
	 */
	public void removeRequest(Usuario contato){
		for (int i = 0; i < this.convitesPendentesRecebidos.size(); i++) {
			if(this.convitesPendentesRecebidos.get(i).getPedinte().getEmail().equals(contato.getEmail()))
				this.convitesPendentesRecebidos.remove(i);
				break;
		}	
	}
	
	/**
	 * Deleta um pedido de amizade enviado
	 * @param contato
	 */
	public void removeSend(Usuario contato){
		for (int i = 0; i < this.convitesPendentesEnviados.size(); i++) {
			if(this.convitesPendentesEnviados.get(i).getRequisitado().getEmail().equals(contato.getEmail()))
				this.convitesPendentesEnviados.remove(i);
				break;
		}	
	}

	//**************************
	//* INFORMACOES ADICIONAIS *
	//**************************

	/**
	 * Muda a permissao de visibilidade de um determinado campo do profile
	 * @param field
	 * @param type
	 */
	public void changeFieldPermission(String field, String type){
		this.perfil.changeFieldPermission(field, type);
	}

	/**
	 * @return status de login do usuario
	 */
	public boolean getLoginStatus(){
		return this.isLogado;
	}

	/**
	 * Muda o status do usuario
	 * @param status
	 */
	public void setLoginStatus(boolean status){
		this.isLogado = status;
	}

	public ArrayList<Field> checkProfile(String visibility) {
		return perfil.checkProfile(visibility);
	}

	/**
	 * Adiciona uma preferencia a lista de preferencias do usuario
	 * @param preference
	 */
	public void addPreference(String preference) {

		for(String p : this.preferences){
			if(p.equals(preference)){
				return;
			}
		}

		this.preferences.add(preference);
	}

	/**
	 * Remove uma preferencia da lista de preferencias do usuario
	 * @param preference
	 */
	public void removePreference(String preference) {

		for (int i = 0; i < this.preferences.size(); i++){
			if(this.preferences.get(i).equals(preference))
				this.preferences.remove(i);
		}

	}
	
	/**
	 * Encontra um membro do grupo
	 * @param amigo
	 * @param grupo
	 * @return
	 */
	public String findGroupMember(Usuario amigo, String grupo) {
		Grupo group = getGrupo(grupo);
		
		if(group.isInGrupo(amigo)){
			return "Nome=" + amigo.getNome() + ",Sobrenome=" + amigo.getSobrenome();
		}
		
		return null;
	}
	
	/**
	 * Lista as preferencias do usuario
	 * @return lista de preferencias
	 */
	public String listPreferences() {
		String saida = "";
		if(this.preferences.size() > 0)
			saida = this.preferences.get(0);
		for(String preference : this.preferences){
			if(!preference.equals(this.preferences.get(0)))
				saida = saida + "," + preference;
		}
		return saida;
	}

	/**
	 * Lista os amigos do usuario
	 * @return lista de amigos
	 */
	public String listFriends() {
		String saida = "";
		ArrayList<String> nomes = new ArrayList<String>();
		for(Usuario amigo : this.amigos.keySet()){
			nomes.add(amigo.getNome()+ " "+ amigo.getSobrenome());
		}
		Collections.sort(nomes);
		for(String nome: nomes){
			saida += nome + ",";
		}
		if(saida.equals("")){
			return saida;
		}
		return saida.substring(0, saida.length()-1);
	}
	
	//****************
	//*     UTIL     *
	//****************
	
	/**
	 * @param group
	 * @return grupo solicitado
	 */
	public Grupo getGrupo(String group){
		for(Grupo g : this.groups){
			if (g.getName().equals(group))
				return g;
		}
		return null;
	}

	/**
	 * Exibe os campos disponiveis para uma determinada visibilidade recebida como parametro
	 * @param visibilidade
	 * @return campos do perfil
	 */
	public ArrayList<Field> getCampos(String visibilidade) {
		ArrayList<Field> campos;
		if(visibilidade.equals("FRIENDS")){
			ArrayList<Field> camposF = this.checkProfile(visibilidade);
			ArrayList<Field> camposA = this.checkProfile(visibilidade);
			campos = camposF;
			for(Field f1 : camposA){
				boolean add = true;
				for(Field f2 : campos){
					if(f1.equals(f2)) add = false;
				}
				if(add) campos.add(f1);
			}
		}else{
			campos = this.checkProfile(visibilidade); 
		}

		return campos;
	}
	
	/**
	 * Retorna o campo passado por parametro como string
	 * @param campo
	 * @return field
	 */
	public Field getCampo(String campo){
		return this.perfil.getCampo(campo);
	}
}