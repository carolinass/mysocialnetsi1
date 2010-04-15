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
		case 3:
			System.exit(0);
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
		System.out.println("5_Aceitar um convite de amizade");
		System.out.println("6_Rejeitar um convite de amizade");
		System.out.println("7_Editar perfil");
		System.out.println("8_Listar amigos");
		System.out.println("9_Logoff");

		switch(sc.nextInt()){

		case 1:
			procurarAmigo(email);
		case 2:
			conviteAmizade(email);
		case 3:
			verConvitesEnviados(email);
		case 4:
			verConvitesRecebidos(email);
		case 5:
			aceitarConvite(email);
		case 6:
			rejeitarConvite(email);
		case 7:
			editarPerfil(email);
		case 8:
			listarAmigos(email);
		case 9:
			logoff(email);
		}


	}

	private static void listarAmigos(String email) throws Exception {
		s.listFriends(email);
		beginProfile(email);

	}

	private static void editarPerfil(String email) {
		System.out.println();
		System.out.println(">>>>> EDITAR PERFIL <<<<<");
		System.out.println("1_Editar descrição");
		System.out.println("2_Editar email para contato");
		System.out.println("3_Editar idade");
		System.out.println("4_Editar foto");
		System.out.println("5_Editar sexo");
		System.out.println("6_Editar cidade");
		System.out.println("7_Editar país");
	}

	private static void rejeitarConvite(String email) throws Exception {
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println(">>>>> REJEITAR PEDIDO DE AMIZADE <<<<<");
		System.out.print("_Amigo: ");
		String contact = sc.nextLine();

		s.declineFriendshipRequest(email, contact);
	}

	private static void aceitarConvite(String login) throws Exception {
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println(">>>>> ACEITAR PEDIDO DE AMIZADE <<<<<");
		System.out.print("_Amigo: ");
		String contact = sc.nextLine();
		System.out.print("_Grupo: ");
		String group = sc.nextLine();

		s.acceptFriendshipRequest(login, contact, group);

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
