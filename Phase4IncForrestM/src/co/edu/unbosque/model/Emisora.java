package co.edu.unbosque.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import co.edu.unbosque.model.persintence.Persistence;
import co.edu.unbosque.model.persintence.Propiedades;

/**
 * 
 * @author David López, Andres Marin, Esteban Uribe, Yensy Gonzalez, Camilo
 *         Gomez
 *
 */
public class Emisora {

	private Propiedades propiedades;
	private Persistence persistence;
	private ArrayList<Dj> djs;

	/**
	 * Metodo Constructor de la clase Emisora encargado de inicializar todos los
	 * atributos que contiene la clase
	 * 
	 */

	public Emisora() {

		propiedades = new Propiedades();
		persistence = new Persistence();

		djs = new ArrayList<Dj>();
	}

	/**
	 * Obtiene la propiedad TituloVentana
	 * 
	 * @return retorna la propiedad TituloVentana como un Stirng
	 */

	public String cargaNombreVentana() {
		return propiedades.getProp().getProperty("TituloVentana");
	}

	/**
	 * Carga el nombre de los djs el arrayList de djs
	 */

	public void asignarDjs() {

		djs.add(new Dj(persistence.leerDjs().get(0)));
		djs.add(new Dj(persistence.leerDjs().get(1)));
		djs.add(new Dj(persistence.leerDjs().get(2)));
		djs.add(new Dj(persistence.leerDjs().get(3)));
	}

	/**
	 * Carga un objeto parrilla en el arraylist que tiene cada dj
	 * 
	 */
	public void asignarParrillas() {
		for (int i = 0; i < djs.size(); i++) {

			djs.get(i).getParrrillas().add(new Parrilla(persistence.leerParrillas().get(i)));
		}

	}

	/**
	 * Metodo obtiene el objeto persistence de la clase Persistence
	 * 
	 * @return objeto persitence
	 */

	public Persistence getPersistence() {
		return persistence;
	}

	/**
	 * Método que modifica el objeto persistence de la clase Persistence
	 * 
	 * @param objeto persistence de la clase Persistence modificado
	 */

	public void setPersistence(Persistence persistence) {
		this.persistence = persistence;
	}

	/**
	 * Mtodo obtiene el objeto propiedade de la clase Propiedades
	 * 
	 * @return objeto propiedades
	 *
	 */

	public Propiedades getPropiedades() {
		return propiedades;
	}

	/**
	 * Método que modifica el objeto propiedades de la clase Propiedades
	 * 
	 * @param objeto propiedades de la clase Propiedades modificado
	 */

	public void setPropiedades(Propiedades propiedades) {
		this.propiedades = propiedades;
	}

	/**
	 * Metodo que obtiene el arrayList Djs
	 * 
	 * @return arrayList Djs
	 */

	public ArrayList<Dj> getDjs() {
		return djs;
	}

	/**
	 * Método que modifica a arrayList Djs
	 * 
	 * @param arrayList djs
	 */

	public void setDjs(ArrayList<Dj> djs) {
		this.djs = djs;
	}

}
