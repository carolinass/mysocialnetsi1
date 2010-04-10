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
	
	public String getUser(String login) throws Exception{
		return sistema.getUser(login);
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
	
	public String checkProfile(String login, String visibility) throws Exception{
		return sistema.checkProfile(login, visibility);
	}
	
	public void addUserPreference(String login, String preference) throws Exception{
		sistema.addUserPreference(login, preference);
	}
	
	public void removeUserPreference(String login, String preference) throws Exception{
		sistema.removeUserPreference(login, preference);
	}
	
//	listUserPreferences login=dev@email.com
	
	public String listUserPreferences(String login) throws Exception{
		return sistema.listUserPreferences(login);
	}
	
	public String viewProfile(String viewer, String profileOwner) throws Exception{
		return sistema.viewProfile(viewer, profileOwner);
	}
}