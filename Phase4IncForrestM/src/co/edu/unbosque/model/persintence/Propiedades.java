package co.edu.unbosque.model.persintence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * 
 * @author David López, Andres Marin, Esteban Uribe, Yensy Gonzalez, Camilo Gomez
 *
 */

public class Propiedades {
	private Properties prop = new Properties();
	private String archivoProp = "./External/config.properties";
	
	/**
	 * lee las propiedades del programa
	 * @return String linea
	 */

	public String leerPropiedades() {
		String linea = "";

		try {
			prop.load(new FileInputStream(archivoProp));

			prop.list(System.out);
			
			linea += "Modo de transmision :" + prop.getProperty("ModoTransmision") + "\n";
			linea += "Tipo de musica : " + prop.getProperty("TipoMusica") + "\n";
			linea += "Titulo de la ventana" + prop.getProperty("TituloVentana");

		} catch (IOException e) {

			e.printStackTrace();
		}
		
	
		return linea;
	}
	/**
	 * obtiene el atributo prop de la clase Properties
	 * @return  Properties prop
	 */

	public Properties getProp() {
		return prop;
	}
	
	/**
	 * Modifica el atributo prop de la clase Properties
	 * @param prop
	 */

	public void setProp(Properties prop) {
		this.prop = prop;
	}
	/**
	 * Obtiene el archivo donde estan las propiedades
	 * @return archivprop
	 */

	public String getArchivoProp() {
		return archivoProp;
	}
	/**
	 * Modifica el archivo donde estan las propiedades
	 * @param archivoProp
	 */

	public void setArchivoProp(String archivoProp) {
		this.archivoProp = archivoProp;
	}
	
	
}