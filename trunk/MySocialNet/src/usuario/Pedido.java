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
	

	public Pedido(Usuario pedinte, Usuario requisitado, String mensagem, Grupo grupo){
		this.pedinte = pedinte;
		this.requisitado = requisitado;
		this.mensagem = mensagem;
		this.grupo = grupo;
	}
	
	public Usuario getPedinte(){
		return this.pedinte;
	}
	
	public void setPedinte(Usuario pedinte){
		this.pedinte = pedinte;
	}
	
	public Usuario getRequisitado(){
		return this.requisitado;
	}
	
	public void setRequisitado(Usuario requisitado){
		this.requisitado = requisitado;
	}
	
	public String getMessage(){
		return this.mensagem;
	}
	
	public void setMessage(String mensagem){
		this.mensagem = mensagem;
	}
	
	public Grupo getGrupo(){
		return this.grupo;
	}
	
	public void setGrupo(Grupo grupo){
		this.grupo = grupo;
	}
}
