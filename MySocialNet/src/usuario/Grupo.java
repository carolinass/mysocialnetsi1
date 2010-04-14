package usuario;

import java.util.ArrayList;

/**
 * Classe que implemena as funcoes de um grupo
 * @author Ana Carolina Cabral
 * @author Pedro Saraiva
 */
public class Grupo {

	private ArrayList<Usuario> usuarios; 
	private String nome;
	
	public Grupo(String nome){
		this.nome = nome;
		this.usuarios = new ArrayList<Usuario>();
	}
	
	public void addFriend(Usuario user){
		for(int i = 0; i<usuarios.size(); i++){
			if(usuarios.get(i).getNome().compareTo(user.getNome()) > 0){
				usuarios.add(i,user);
				return;
			}
		}
		this.usuarios.add(user);
	}
	
	public void removeFriend(Usuario user){
		this.usuarios.remove(user);
	}
	
	public String getName(){
		return this.nome;
	}
	
	public int size(){
		return this.usuarios.size();
	}
	
	public void setName(String name){
		this.nome = name;
	}
	
	public boolean isInGrupo(Usuario user){
		for(int i= 0; i< this.usuarios.size(); i++){
			if(this.usuarios.get(i) == user){
				return true;
			}
		}
		return false;
	}

	public boolean isEmpty() {
		if(this.usuarios.size() == 0){
			return true;
		}
		return false;
	}

	public Usuario getUser(int i) {
		return this.usuarios.get(i);
		}
	

	public ArrayList<Usuario> listGrupo() {
		
		ArrayList<Usuario> userList = new ArrayList<Usuario>();
		for(int i = 0; i< usuarios.size();i++){
			userList.add(usuarios.get(i));
		}
		return userList;
	}
	
}
