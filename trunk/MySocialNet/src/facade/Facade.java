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

	public void clean(){
		sistema.clean();
	}
	public void createUser(String name, String lastName, String email, String passwd) throws Exception{
		sistema.createUser(name, lastName, email, passwd);
	}

	public String getUser(String login) throws Exception{
		return sistema.getUser(login);
	}

	public void deleteUser(String login)throws Exception{
		sistema.deleteUser(login);
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

	public String listUserPreferences(String login) throws Exception{
		return sistema.listUserPreferences(login);
	}

	public String viewProfile(String viewer, String profileOwner) throws Exception{
		return sistema.viewProfile(viewer, profileOwner);
	}

	public String listFriends(String email) throws Exception{
		return sistema.listFriends(email);
	}

	public String findNewFriend(String login, String friend) throws Exception{
		return sistema.findNewFriend(login, friend);
	}

	public void sendFriendshipRequest(String login, String user, String mensagem, String grupo) throws Exception{
		sistema.sendFriendshipRequest(login, user, mensagem, grupo);
	}

	public String viewPendingFriendship(String login) throws Exception{
		return sistema.viewPendingFriendship(login);
	}

	public String viewSentFriendship(String login) throws Exception{
		return sistema.viewSentFriendship(login);
	}

	public void declineFriendshipRequest(String login, String contact)throws Exception{
		sistema.declineFriendshipRequest(login,contact);
	}

	public void acceptFriendshipRequest(String login, String contact, String group) throws Exception{
		sistema.acceptFriendshipRequest(login, contact, group);
	}

	public String findGroupMember(String login, String friend, String group) throws Exception{
		return sistema.findGroupMember(login, friend, group);
	}

	public String getFriend(String email, String friend) throws Exception{
		return sistema.getFriend(email,friend);
	}

	public void removeFriend(String email, String friend) throws Exception{
		sistema.removeFriend(email, friend);
	}

	public String listGroupMembers(String email , String group) throws Exception{
		return sistema.listGroupMembers(email, group);
	}

	public void addGroupMember(String email, String group, String user) throws Exception{
		sistema.addGroupMember(email, group, user);
	}

	public void removeGroupMember(String email, String group, String user) throws Exception{
		sistema.removeGroupMember(email, group, user);
	}

	public String getRecommendFriends(String login)throws Exception{
		return sistema.getRecommendFriends(login);
	}

	public void exportFriendList(String login, String fileName, String exportedFields) throws Exception{
		sistema.exportFriendList(login, fileName, exportedFields);
	}
}