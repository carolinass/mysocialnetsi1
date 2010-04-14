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
	
	public Permission(String name){	
		this.name = name;
	}
	
	public void addCampo(Field campo){
		campos.add(campo);
	}
	
	public String getName(){
		return this.name;
	}
	 

}
