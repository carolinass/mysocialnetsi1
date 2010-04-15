package usuario;

import java.util.ArrayList;

/**
 * Classe que implementa as funcoes do perfil de um usuario
 * @author Ana Carolina
 * @author Pedro Saraiva
 * @author Tiago
 * @author Ettore
 * @author Emerson
 */
public class Perfil {
	
	public ArrayList<Field> fields = new ArrayList<Field>();
	public Field aboutMe = new Field("aboutMe");
	public Field contactEmail = new Field("contactEmail");
	public Field country = new Field("country");
	public Field city = new Field("city");
	public Field photo = new Field("photo");
	public Field gender = new Field("gender");
	public Field age = new Field("age");
	
	/**
	 * Metodo Construtor
	 */
	public Perfil(){;
		this.fields.add(this.contactEmail);
		this.fields.add(this.age);
		this.fields.add(this.photo);
		this.fields.add(this.aboutMe);
		this.fields.add(this.gender);
		this.fields.add(this.city);
		this.fields.add(this.country);
		
		this.aboutMe.setPermission("ALL");
		this.country.setPermission("ALL");
		this.city.setPermission("FRIENDS");
		this.photo.setPermission("ALL");
	}
	
	/**
	 * Exibe o aboutMe do usuario
	 * @return aboutMe
	 */
	public String getAboutMe(){
		return this.aboutMe.getField();
	}
	
	/**
	 * Edita o aboutMe
	 * @param about
	 */
	public void setAboutMe(String about){
		this.aboutMe.setField(about);
	}
	
	/**
	 * Exibe a idade do usuario
	 * @return age
	 */
	public String getAge(){
		return this.age.getField();
	}
	
	/**
	 * Edita a idade
	 * @param age
	 */
	public void setAge(String age){
		this.age.setField(age);
	}
	
	/**
	 * Exibe a foto do usuario
	 * @return photo
	 */
	public String getPhoto(){
		return this.photo.getField();
	}
	
	/**
	 * Edita a photo
	 * @param photo
	 */
	public void setPhoto(String photo){
		this.photo.setField(photo);
	}
	
	/**
	 * Exibe o país do usuário
	 * @return country
	 */
	public String getCounty(){
		return this.country.getField();
	}
	
	/**
	 * Edita o país
	 * @param country
	 */
	public void setCountry(String country){
		this.country.setField(country);
	}
	
	/**
	 * Exibe a cidade do usuario
	 * @return city
	 */
	public String getCity(){
		return this.city.getField();
	}
	
	/**
	 * Edita a cidade
	 * @param city
	 */
	public void setCity(String city){
		this.city.setField(city);
	}
	
	/**
	 * Exibe o sexo do usuario
	 * @return gender
	 */
	public String getGender(){
		return this.gender.getField();
	}
	
	/**
	 * Edita o sexo do usuario
	 * @param gender
	 */
	public void setGender(String gender){
		this.gender.setField(gender);
	}
	
	/**
	 * Exibe o email do usuario
	 * @return contactEmail
	 */
	public String getContactEmail(){
		return this.contactEmail.getField();
	}
	
	/**
	 * Edita o email
	 * @param contactEmail
	 */
	public void setContactEmail(String contactEmail){
		this.contactEmail.setField(contactEmail);
	}

	/**
	 * Muda a permissao de visualizacao do campo
	 * @param field
	 * @param type
	 */
	public void changeFieldPermission(String field, String type) {
		Field fieldToChange = null;
		for (Field f : this.fields){
			if(f.getName().equals(field)) fieldToChange = f;
		}
		
		fieldToChange.setPermission(type);
		
	}

	/**
	 * Exibe os campos com a visibilidade passada como parametro
	 * @param visibility
	 * @return array de campos
	 */
	public ArrayList<Field> checkProfile(String visibility) {
		
		ArrayList<Field> saida = new ArrayList<Field>();
		for(Field f : this.fields){
			if(f.getPermission().getName().compareTo(visibility) <= 0){
				saida.add(f); 
			}
		}
		return saida;
	}

	/**
	 * Retorna o campo passado por parametro como string
	 * @param campo
	 * @return field
	 */
	public Field getCampo(String campo) {
		for(Field field : this.fields){
			if(field.getName().equals(campo))
				return field;
		}
		return null;
	}

}
