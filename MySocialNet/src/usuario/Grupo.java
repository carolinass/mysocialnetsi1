package usuario;

import java.util.ArrayList;

/**
 * Classe que implemena as funcoes de um grupo
 * @author Ana Carolina Cabral
 * @author Pedro Saraiva
 * @author Tiago
 * @author Ettore
 * @author Emerson
 */
public class Grupo {

	private ArrayList<Usuario> usuarios; 
	private String nome;
	
	/**
	 * Metodo Construtor
	 * @param nome
	 */
	public Grupo(String nome){
		this.nome = nome;
		this.usuarios = new ArrayList<Usuario>();
	}
	
	/**
	 * Adiciona amigo no grupo
	 * @param user
	 */
	public void addFriend(Usuario user){
		for(int i = 0; i<usuarios.size(); i++){
			if(usuarios.get(i).getNome().compareTo(user.getNome()) > 0){
				usuarios.add(i,user);
				return;
			}
		}
		this.usuarios.add(user);
	}
	
	/**
	 * Remove amigo do grupo
	 * @param user
	 */
	public void removeFriend(Usuario user){
		this.usuarios.remove(user);
	}
	
	/**
	 * Exibe nome do grupo
	 * @return nome do grupo
	 */
	public String getName(){
		return this.nome;
	}
	
	/**
	 * Exibe quantidade de pessoas o grupo
	 * @return tamanho do grupo
	 */
	public int size(){
		return this.usuarios.size();
	}
	
	/**
	 * Muda o nome do grupo
	 * @param name
	 */
	public void setName(String name){
		this.nome = name;
	}
	
	/**
	 * Verifica se um usuario esta no grupo
	 * @param user
	 * @return true or false
	 */
	public boolean isInGrupo(Usuario user){
		for(int i= 0; i< this.usuarios.size(); i++){
			if(this.usuarios.get(i) == user){
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica se o grupo esta vazio
	 * @return true or false
	 */
	public boolean isEmpty() {
		if(this.usuarios.size() == 0){
			return true;
		}
		return false;
	}

	/**
	 * Pega um usuario do grupo
	 * @param i
	 * @return usuario
	 */
	public Usuario getUser(int i) {
		return this.usuarios.get(i);
		}
	

	/**
	 * Lista os usuarios do grupo
	 * @return lista de usuarios do grupo
	 */
	public ArrayList<Usuario> listGrupo() {
		
		ArrayList<Usuario> userList = new ArrayList<Usuario>();
		for(int i = 0; i< usuarios.size();i++){
			userList.add(usuarios.get(i));
		}
		return userList;
	}
	
}
