package Testes;

import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;

import facade.Facade;

public class testEasyAccept {


	public static void main(String[] args) {
		List<String> arquivo = new ArrayList<String>();
		// Adiciona o arquivo us1-cadastrarUsuario.txt na lista de Scripts de teste


		//arquivo.add("US01.txt");		//ta dando nullPointer
		//arquivo.add("US02.txt");  	//Ok
		//arquivo.add("US03.txt");		//OK
		//arquivo.add("US04.txt");		//faltam 53 
		//arquivo.add("US05.txt");		//faltam 41
		//arquivo.add("US06.txt");		//faltam 49
		//arquivo.add("US07.txt");		//faltam 10
		//arquivo.add("US08.txt");		//faltam 17

		// Instancia a fachada do seu sotfware
		Facade facadeUS1 = new Facade();

		// Instancia a fachada do EasyAccept
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(facadeUS1, arquivo);

		// Executa o teste
		eaFacade.executeTests();

		// Imprimir o resultado do teste
		System.out.println(eaFacade.getCompleteResults());
	}
}

