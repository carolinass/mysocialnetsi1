import sistema.Sistema;
import usuario.Usuario;

public class Mainzinha {
	
	public static void main(String[] args) throws Exception{
		Sistema sistema = new Sistema();
		sistema.createUser("Carol", "Cabral", "carol@email.com", "123456");
		sistema.login("carol@email.com", "123456");
		System.out.println(sistema.getUser("carol@email.com"));
		
		sistema.updateUserProfile("carol@email.com", "yay", "19", "p", "brasil", "cg", "f", "ana@email.com");
		
		sistema.setFieldPrivacy("carol@email.com", "age", "ALL");
		System.out.println(sistema.checkProfile("carol@email.com", "ALL"));
		
	}
	
}
