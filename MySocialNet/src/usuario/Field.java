package usuario;

import java.util.ArrayList;

/**
 * Classe que representa um campo do perfil do usuario
 * @author Ana Carolina
 * @author Pedro Saraiva
 * @author Tiago
 * @author Ettore
 * @author Emerson
 */
public class Field {
	
	ArrayList<Permission> permissoes = new ArrayList<Permission>();
	private Permission permission;
	private String conteudo;
	private String nome;
	
	/**
	 * Metodo Construtor
	 * @param nome
	 */
	public Field(String nome){
		Permission all = new Permission("ALL");
		Permission justMe = new Permission("JUST_ME");
		Permission friends = new Permission("FRIENDS");
		permissoes.add(all);
		permissoes.add(justMe);
		permissoes.add(friends);
		
		this.permission = justMe;
		this.conteudo = "";
		this.nome = nome;
	}
	
	/**
	 * @return String conteudo do campo
	 */
	public String getField(){
		return this.conteudo;
	}
	
	/**
	 * Altera o conteudo do campo
	 * @param conteudo
	 */
	public void setField(String conteudo){
		this.conteudo = conteudo;
	}
	
	/**
	 * @return String permissao do campo
	 */
	public Permission getPermission(){
		return this.permission;
	}
	
	/**
	 * Altera a permissao de visualizacao do campo
	 * @param permission
	 */
	public void setPermission(String permission){
		for (Permission p : this.permissoes){
			if(p.getName().equals(permission)) this.permission = p;
		}
	}
	
	/**
	 * @return String - nome do campo
	 */
	public String getName(){
		return this.nome;
	}
	
	/**
	 * Altera o nome do campo
	 * @param name
	 */
	public void setName(String name){
		this.nome = name;
	}

}
