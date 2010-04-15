package Data;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import usuario.Usuario;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;




public class DataBase{

	private static final File USUARIOS_BD = new File("./dadosXML/usuariosBD.xml");
	private List<String> xml;
	private List<Usuario> usuarios;


	public DataBase() throws FileNotFoundException, IOException, ClassNotFoundException {
		atualizaListas();
	}


	private void atualizaListas() {
		xml = getUsuarios();
		usuarios = xmlParaObjeto(xml);
	}


	public void cleanBd() {
		ArrayList<String> vazio = new ArrayList<String>();
		try {
			Serializar.salvarObjeto(USUARIOS_BD, vazio);

		} catch (FileNotFoundException e) {} catch (IOException e) {}
		finally {
			atualizaListas();
		}
	}	


	private List<Usuario> xmlParaObjeto(List<String> xml) {

		List<Usuario> usuarios = new ArrayList<Usuario>();

		XStream xStream = new XStream(new DomDriver());
		Iterator<String> it = xml.iterator();

		while(it.hasNext()) {
			String umXML = it.next();

			Usuario user = (Usuario) xStream.fromXML(umXML);
			usuarios.add(user);
		}

		return usuarios;
	}


	private List<String> objetoParaXML(List<Usuario> xml) {

		List<String> usuarios = new ArrayList<String>();

		XStream xStream = new XStream(new DomDriver());
		Iterator<Usuario> it = xml.iterator();

		while(it.hasNext()) {
			Usuario umUsuario = it.next();

			String usuarioXML = xStream.toXML(umUsuario);
			usuarios.add(usuarioXML);
		}

		return usuarios;
	}


	private static List<String> getUsuarios() {
		try {
			List<String> users = (List) Serializar.recuperarObjeto(USUARIOS_BD);
			return users;

		} catch (Exception e1) {
			return new ArrayList<String>();
		}
	}


	public void deletarUsuarioDoBd(String login) throws Exception {

		Iterator<Usuario> it = usuarios.iterator();

		while(it.hasNext()) {
			Usuario user = it.next();
			if (user.getEmail().equals(login)) {
				usuarios.remove(user);

				Serializar.salvarObjeto(USUARIOS_BD, objetoParaXML(usuarios));
				atualizaListas();			
			}
		}
	}


	public Usuario recuperaUsuarioNoBd(String login, String senha) throws Exception {

		Iterator<Usuario> it = usuarios.iterator();
		while (it.hasNext()) {
			Usuario usr = it.next();
			if (usr.getEmail().equals(login) && usr.getSenha().equals(senha))
				return usr;
		}

		throw new Exception("Login inválido ou senha incorreta");
	}


	public Usuario recuperaUsuarioNoBd(String login) throws Exception {

		Iterator<Usuario> it = usuarios.iterator();
		while (it.hasNext()) {
			Usuario usr = it.next();
			if (usr.getEmail().equalsIgnoreCase(login.trim()))
				return usr;
		}

		throw new Exception("Perfil inexistente");
	}


	public boolean autentica(String email, String senha) {

		Iterator<Usuario> it = usuarios.iterator();
		while(it.hasNext()) {
			Usuario user = it.next();

			if (user.getEmail().equals(email) & user.getSenha().equals(senha))
				return true;
		}
		return false;
	}


	public void addNewUser(Usuario usuario) throws FileNotFoundException, IOException , Exception{

		if (usuario != null & searchExistingLogin(usuario.getEmail()) == false) {	
			usuarios.add(usuario);			
			Serializar.salvarObjeto(USUARIOS_BD, objetoParaXML(usuarios));
			atualizaListas();
		}
		else {
			throw new Exception("Usuario já cadastrado");
		}

	}


	public boolean searchExistingLogin(String email) {

		Iterator<Usuario> it = usuarios.iterator();
		while(it.hasNext()) {
			Usuario user = it.next();

			if (user.getEmail().equals(email))
				return true;
		}
		return false;
	}



	//	public static void main(String[] args) throws Exception {
	//		
	//		DataBaseInXML rep = new DataBaseInXML();
	//		Usuario user1 = new Usuario("Luan", "Barbosa", "luan@gmail.com", "123456");
	//		Usuario user2 = new Usuario("Luan", "Barbosa", "luan2@gmail.com", "1234565");
	//		Usuario user3 = new Usuario("maria", "Barbosa", "maria@gmail.com", "e456845");
	//		Usuario user4 = new Usuario("jose", "Barbosa", "jose@gmail.com", "123456sdda3");
	//
	//		
	//		rep.cleanBd();
	//		
	//		System.out.println("Autenticou: " + rep.autentica("luan@gmail.com", "123456") );
	//		
	//		System.out.println("Autenticou: " + rep.autentica("anna@gmail.com", "213453554") );
	//		System.out.println("Autenticou: " + rep.autentica("maria@gmail.com", "e456845") );
	//		System.out.println("Autenticou: " + rep.autentica("jose@gmail.com", "123456sdda3") );
	//
	//	}


}