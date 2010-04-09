package facade;

public class Facade {

	private static Facade instance;

	public static Facade getInstance(){
		if(instance == null){
			instance = new Facade();
		}
		return instance;
	}

	private sistema.Sistema sistema = new sistema.Sistema();

	public void createUser(String name, String lastName, String email, String passwd) throws Exception{
		sistema.createUser(name, lastName, email, passwd);
	}

	public void getUser(String login) throws Exception{
		sistema.getUser(login);
	}

	public void login(String email, String passwd) throws Exception{
		sistema.login(email, passwd);
	}

	public void logoff(String email) throws Exception{
		sistema.logoff(email);
	}

	public void updateUserProfile(String login, String aboutMe, String age, String photo, String country, 
			String city, String gender, String contactEmail) throws Exception{
		sistema.updateUserProfile(login, aboutMe, age, photo, country, city, gender, contactEmail);
	}

	public void setFieldPrivacy(String login, String field, String type) throws Exception{
		sistema.setFieldPrivacy(login, field, type);
	}

	public void checkProfile(String login, String visibility) throws Exception{
		sistema.checkProfile(login, visibility);
	}
}