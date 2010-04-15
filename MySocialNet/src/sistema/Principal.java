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
		System.out.println("*********************************");
		System.out.println("********** MySocialNet **********");
		System.out.println("*********************************");
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
		System.out.println("9_Ver amigos recomendados");
		System.out.println("10_Logoff");

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
			editarPerfil(user);
		case 8:
			listarAmigos(email);
		case 9:
			verAmigosRecomendados(email);
		case 10:
			logoff(email);
		}


	}

	private static void verAmigosRecomendados(String email) throws Exception {
		System.out.println(s.getRecommendFriends(email));
		beginProfile(email);
	}

	private static void listarAmigos(String email) throws Exception {
		s.listFriends(email);
		beginProfile(email);

	}

	private static void editarPerfil(Usuario user) throws Exception {
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println(">>>>> EDITAR PERFIL <<<<<");
		System.out.println("1_Editar descrição");
		System.out.println("2_Editar email para contato");
		System.out.println("3_Editar idade");
		System.out.println("4_Editar foto");
		System.out.println("5_Editar sexo");
		System.out.println("6_Editar cidade");
		System.out.println("7_Editar país");
		System.out.println("8_Voltar");

		switch(sc.nextInt()){

		case 1:
			editarDescricao(user);
		case 2:
			editarEmail(user);
		case 3:
			editarIdade(user);
		case 4:
			editarFoto(user);
		case 5:
			editarSexo(user);
		case 6:
			editarCidade(user);
		case 7:
			editarPais(user);
		case 8:
			beginProfile(user.getEmail());
		}
	}

	private static void editarPais(Usuario user) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println(">>>>> Editar País <<<<<");
		System.out.println("País: ");
		String country = sc.nextLine();
		System.out.println("Visibilidade: (ALL, JUST_ME ou FRIENDS)");
		String visibilidade = sc.nextLine();
		
		user.perfil.country.setPermission(visibilidade);
		user.perfil.setCountry(country);		
		editarPerfil(user);
	}

	private static void editarCidade(Usuario user) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println(">>>>> Editar Cidade <<<<<");
		System.out.println("Cidade: ");
		String city = sc.nextLine();
		System.out.println("Visibilidade: (ALL, JUST_ME ou FRIENDS)");
		String visibilidade = sc.nextLine();
		
		user.perfil.city.setPermission(visibilidade);
		user.perfil.setCity(city);
		editarPerfil(user);
	}

	private static void editarSexo(Usuario user) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println(">>>>> Editar Sexo <<<<<");
		System.out.println("Sexo: ");
		String gender = sc.nextLine();
		System.out.println("Visibilidade: (ALL, JUST_ME ou FRIENDS)");
		String visibilidade = sc.nextLine();
		
		user.perfil.gender.setPermission(visibilidade);
		user.perfil.setGender(gender);
		editarPerfil(user);
	}

	private static void editarFoto(Usuario user) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println(">>>>> Editar Foto <<<<<");
		System.out.println("Nova foto: ");
		String photo = sc.nextLine();
		System.out.println("Visibilidade: (ALL, JUST_ME ou FRIENDS)");
		String visibilidade = sc.nextLine();
		
		user.perfil.photo.setPermission(visibilidade);
		user.perfil.setPhoto(photo);
		editarPerfil(user);
	}

	private static void editarIdade(Usuario user) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println(">>>>> Editar Idade <<<<<");
		System.out.println("Idade: ");
		String idade = sc.nextLine();
		System.out.println("Visibilidade: (ALL, JUST_ME ou FRIENDS)");
		String visibilidade = sc.nextLine();
		
		user.perfil.age.setPermission(visibilidade);
		user.perfil.setAge(idade);
		editarPerfil(user);
	}

	private static void editarEmail(Usuario user) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println(">>>>> Editar Email <<<<<");
		System.out.println("Novo email: ");
		String email = sc.nextLine();
		System.out.println("Visibilidade: (ALL, JUST_ME ou FRIENDS)");
		String visibilidade = sc.nextLine();
		
		user.perfil.contactEmail.setPermission(visibilidade);
		user.perfil.setContactEmail(email);
		editarPerfil(user);
	}

	private static void editarDescricao(Usuario user) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println(">>>>> Editar Descricao <<<<<");
		System.out.println("Nova descricao: ");
		String about = sc.nextLine();
		System.out.println("Visibilidade: (ALL, JUST_ME ou FRIENDS)");
		String visibilidade = sc.nextLine();
		
		user.perfil.aboutMe.setPermission(visibilidade);
		user.perfil.setAboutMe(about);
		editarPerfil(user);
	}

	private static void rejeitarConvite(String email) throws Exception {
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println(">>>>> REJEITAR PEDIDO DE AMIZADE <<<<<");
		System.out.print("_Amigo: ");
		String contact = sc.nextLine();

		s.declineFriendshipRequest(email, contact);
		beginProfile(email);
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
		beginProfile(login);
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
