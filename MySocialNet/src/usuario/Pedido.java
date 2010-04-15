package usuario;

import java.util.ArrayList;

/**
 * Classe que implementa as funcoes de um pedido de amizade
 * @author Ana Carolina
 * @author Pedro Saraiva
 * @author Ettore
 * @author Tiago
 * @author Emerson
 */
public class Pedido {

	private Usuario pedinte;
	private Usuario requisitado;
	private String mensagem;
	private Grupo grupo;
	

	/**
	 * Metodo Construtor
	 * @param pedinte
	 * @param requisitado
	 * @param mensagem
	 * @param grupo
	 */
	public Pedido(Usuario pedinte, Usuario requisitado, String mensagem, Grupo grupo){
		this.pedinte = pedinte;
		this.requisitado = requisitado;
		this.mensagem = mensagem;
		this.grupo = grupo;
	}
	
	/**
	 * @return pedinte
	 */
	public Usuario getPedinte(){
		return this.pedinte;
	}
	
	/**
	 * Edita o pedinte
	 * @param pedinte
	 */
	public void setPedinte(Usuario pedinte){
		this.pedinte = pedinte;
	}
	
	/**
	 * 
	 * @return requisitado
	 */
	public Usuario getRequisitado(){
		return this.requisitado;
	}
	
	/**
	 * Edita o requisitado
	 * @param requisitado
	 */
	public void setRequisitado(Usuario requisitado){
		this.requisitado = requisitado;
	}
	
	/**
	 * 
	 * @return message
	 */
	public String getMessage(){
		return this.mensagem;
	}
	
	/**
	 * Edita a mensagem
	 * @param mensagem
	 */
	public void setMessage(String mensagem){
		this.mensagem = mensagem;
	}
	
	/**
	 * 
	 * @return grupo
	 */
	public Grupo getGrupo(){
		return this.grupo;
	}
	
	/**
	 * Edita o grupo
	 * @param grupo
	 */
	public void setGrupo(Grupo grupo){
		this.grupo = grupo;
	}
}
