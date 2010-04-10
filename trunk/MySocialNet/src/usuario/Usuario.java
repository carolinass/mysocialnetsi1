package usuario;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe que implementa as funcoes de um usuario
 * @author Ana Carolina Cabral
 * @author Pedro Saraiva
 * @author Tiago
 * @author Ettore
 * @version 1.0
 */
public class Usuario {
	
	public String nome, sobrenome, email, senha;
	public Perfil perfil;
	public boolean isLogado;
	public ArrayList<String> preferences = new ArrayList<String>();
	public HashMap<Usuario, Grupo> amigos = new HashMap<Usuario, Grupo>();
	
	
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


	/***************************
	 * GERENCIAMENTO DE AMIGOS *
	 ***************************/
	
//	public void addAmigo(Usuario amigo, Grupo grupo){
//		grupo.addUser(amigo);
//	}
	
	public void removerAmigo(Usuario amigo){
		
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
	
	/**************************
	 * INFORMACOES ADICIONAIS *
	 **************************/
	
	public void changeFieldPermission(String field, String type){
		this.perfil.changeFieldPermission(field, type);
	}
	
	public boolean getLoginStatus(){
		return this.isLogado;
	}
	
	public void setLoginStatus(boolean status){
		this.isLogado = status;
	}

	public ArrayList<Field> checkProfile(String visibility) {
		
		return perfil.checkProfile(visibility);
		
	}

	public void addPreference(String preference) {
		this.preferences.add(preference);
		
	}

	public void removePreference(String preference) {
		for (int i = 0; i < this.preferences.size(); i++){
			if(this.preferences.get(i).equals(preference))
				this.preferences.remove(i);
		}
	}
	
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
	
}