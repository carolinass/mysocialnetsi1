package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializar {


	/**
	 * Esse m�todo recebe um objeto arquivo e o objeto que ser� serializado. 
	 * O m�todo ir� serializar o objeto passado no arquivo (referenciado pelo objeto file).
	 */
	public static void salvarObjeto(File file, Object usuarios) throws FileNotFoundException, IOException {

		if (usuarios == null) {
			throw new NullPointerException("Objeto passado � nulo");
		}
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut = null;


		try {
			fileOut = new FileOutputStream(file.getPath());                    
			objOut = new ObjectOutputStream(fileOut);

			objOut.writeObject(usuarios);

		} 
		catch (FileNotFoundException e) {
			throw new FileNotFoundException("O arquivo n�o pode ser encontrado");
		} 
		catch (IOException e) {
			throw new IOException("Erro de entrada e sa�da");
		}
		finally {
			if (fileOut != null) {
				fileOut.close();
			}

			if (objOut != null) {
				objOut.close();
			}
		}

	}


	public static Object recuperarObjeto(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		Object obj = null;

		if (file == null) {
			throw new NullPointerException("Objeto FILE � Nulo.");
		}

		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;

		try {
			fileIn = new FileInputStream(file);
			objIn = new ObjectInputStream(fileIn);

			obj = objIn.readObject();
		}
		catch (FileNotFoundException e) {
			throw new FileNotFoundException("Arquivo n�o encontrado");
		}
		catch (IOException e) {
			throw new IOException("Erro de entrada e sa�da");
		}
		catch (ClassNotFoundException e) {
			throw new ClassNotFoundException("Erro ao recuperar objeto do arquivo");
		}
		finally {
			if (fileIn != null) {
				fileIn.close();
			}

			if (objIn != null) {
				objIn.close();
			}
		}

		return obj;
	}


}


