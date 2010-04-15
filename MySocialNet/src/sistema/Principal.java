package sistema;

import java.util.Scanner;

import usuario.Usuario;

public class Principal {

	private static Sistema s;

	public static void main(String[] args) throws Exception{

		s = new Sistema();
		inicio();
	}

	private static void inicio() throws Exception {
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("********** MySocialNet **********");
		System.out.println("1 - Fazer Login");
		System.out.println("2 - Novo Usuário");
		System.out.println("3 - Sair");

		switch(sc.nextInt()){

		case 1:
			login();

		case 2:
			createUser();
		}
	}

	private static void createUser() throws Exception{

		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println(">>>>> NOVO USUARIO <<<<<");
		System.out.print("_Nome: ");
		String nome = sc.nextLine();
		System.out.print("_Sobrenome: ");
		String sobrenome = sc.nextLine();
		System.out.print("_Email: ");
		String email = sc.nextLine();
		System.out.print("_Senha: ");
		String senha = sc.nextLine();

		s.createUser(nome, sobrenome, email, senha);
		inicio();
	}

	public static void login() throws Exception{

		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println(">>>>> LOGIN <<<<<");
		System.out.print("_Email: ");
		String email = sc.nextLine();
		System.out.print("_Senha: ");
		String senha = sc.nextLine();

		s.login(email, senha);
		beginProfile(email);
	}

	private static void beginProfile(String email) throws Exception{
		Scanner sc = new Scanner(System.in);
		Usuario user = s.findUser(email);

		System.out.println();
		System.out.println(">>>>> Olá, " + user.getNome() + "! <<<<<");
		System.out.println("1_Procurar amigo");
		System.out.println("2_Enviar convite de amizade");
		System.out.println("3_Visualizar convites pendentes");
		System.out.println("4_Visualizar convites recebidos");
		System.out.println("5_Logoff");

		switch(sc.nextInt()){

		case 1:
			conviteAmizade(email);
		case 2:
			procurarAmigo(email);
		case 3:
			verConvitesEnviados(email);
		case 4:
			verConvitesRecebidos(email);
		case 5:
			logoff(email);
		}


	}

	private static void logoff(String email) throws Exception {
		s.logoff(email);
		inicio();
	}

	private static void verConvitesRecebidos(String email) throws Exception {
		System.out.println(s.viewPendingFriendship(email));
		beginProfile(email);
	}

	private static void verConvitesEnviados(String email) throws Exception {
		System.out.println(s.viewSentFriendship(email));
		beginProfile(email);
	}

	private static void procurarAmigo(String login) throws Exception {
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println(">>>>> BUSCAR AMIGO <<<<<");
		System.out.print("_Amigo: ");
		String friend = sc.nextLine();
		
		System.out.println(s.findNewFriend(login, friend));
		beginProfile(login);

	}

	private static void conviteAmizade(String email) throws Exception{
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println(">>>>> NOVO CONVITE <<<<<");
		System.out.print("_Email do convidado: ");
		String user = sc.nextLine();
		System.out.print("_Mensagem: ");
		String mensagem = sc.nextLine();
		System.out.print("_Grupo: ");
		String grupo = sc.nextLine();

		s.sendFriendshipRequest(email, user, mensagem, grupo);
		beginProfile(email);
	}
}
