package usuario;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

/**
 * Classe que implementa as funcoes de um usuario
 * @author Ana Carolina Cabral
 * @author Pedro Saraiva
 * @author Tiago
 * @author Ettore
 * @author Emerson
 * @version 1.0
 */
public class Usuario {

	public String nome, sobrenome, email, senha;
	public Perfil perfil;
	public boolean isLogado;
	public List amigos;


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

	public String checkProfile(String visibility) {

		return perfil.checkProfile(visibility);

	}

}