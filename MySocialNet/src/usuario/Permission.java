package usuario;

import java.util.ArrayList;

/**
 * Classe que implementa as permissoes de visualizacao do campo
 * @author Ana Carolina
 * @author Pedro Saraiva
 * @author Ettore
 * @author Tiago
 * @author Emerson
 */
public class Permission {
	
	private ArrayList<Field> campos = new ArrayList<Field>();
	private String name;
	
	/**
	 * Metodo Construtor
	 * @param name
	 */
	public Permission(String name){	
		this.name = name;
	}
	
	/**
	 * Adiciona Campo
	 * @param campo
	 */
	public void addCampo(Field campo){
		campos.add(campo);
	}
	
	/**
	 * 
	 * @return name
	 */
	public String getName(){
		return this.name;
	}
	 

}
