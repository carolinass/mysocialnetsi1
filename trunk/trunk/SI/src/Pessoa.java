public class Pessoa { 
	
	private String nome;  
	private String email;  
	private Telefone foneComercial;
	
	public String getNome(){
		return this.nome;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public Telefone getFoneComercial(){
		return this.foneComercial;
	}
	public void setFoneComercial(Telefone fone){
		this.foneComercial = fone;
	}
	
 }  