import java.util.ArrayList;  
import java.util.List;  
import com.thoughtworks.xstream.XStream;  
import com.thoughtworks.xstream.io.xml.DomDriver;

public class TesteXStream {  
	public static void main(String[] args) {  

		// Criando um objeto XStream          
		XStream xstream = new XStream(new DomDriver());


		// Criando alguns dados  
		Pessoa vinci = new Pessoa();
		vinci.setNome("Vinci Pegoretti Amorim");  
		vinci.setEmail("vinci_Amorim@yahoo.com.br");  

		Telefone foneDoVinci = new Telefone();  
		foneDoVinci.setDdd(55); 
		foneDoVinci.setNumero("5555 5555");  

		vinci.setFoneComercial(foneDoVinci); 
		List contatos = new ArrayList(1);  
		contatos.add(vinci); 
		
		// Passando os dados de Objetos Java para XML
		String contatosEmXML = xstream.toXML(contatos);
		
		System.out.println("\nContatos em XML:"); 
		System.out.println(contatosEmXML);
		
		// Passando os dados de XML para Objetos Java  
		List amigos = (List) xstream.fromXML(contatosEmXML);  
		Pessoa amigo = (Pessoa) amigos.get(0);
		Telefone foneDoAmigo = amigo.getFoneComercial();
		
		System.out.println("\nAmigo como Objeto Java:");
		System.out.println("Nome: " + amigo.getNome());
		System.out.println("Fone Comercial: (" + foneDoAmigo.getDdd() + ") "
				+ foneDoAmigo.getNumero());
		
	}
}