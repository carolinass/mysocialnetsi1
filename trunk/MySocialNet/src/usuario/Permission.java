package usuario;

import java.util.ArrayList;

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
