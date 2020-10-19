package co.edu.unbosque.model;

import java.util.ArrayList;

/**
 * 
 * @author David L�pez, Andres Marin, Esteban Uribe, Yensy Gonzalez, Camilo Gomez
 *
 */

public class Dj {

	private String nombre;
	private ArrayList<Parrilla> parrrillas;

	/**
	 * M�todo construcor de la clase DJ
	 * 
	 * @param a Recibe un Stirng a para darle valor el atributo nombre
	 */

	public Dj(String a) {

		nombre = a;
		parrrillas = new ArrayList<Parrilla>();

	}

	/**
	 * M�todo que obtiener el atributo nombre
	 * 
	 * @return String nombre
	 */

	public String getNombre() {
		return nombre;
	}

	/**
	 * M�todo que modifica el string nombre
	 * 
	 * @param String nombre
	 */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * M�todo que obtiene el aributo ArrayList parrillas
	 * 
	 * @return Arraylist parrillas
	 */

	public ArrayList<Parrilla> getParrrillas() {
		return parrrillas;
	}

	/**
	 * Metodo que permite modificar el atributo de ArrayList parrillas
	 * 
	 * @param ArrayList parrillas
	 */
	public void setParrrillas(ArrayList<Parrilla> parrrillas) {
		this.parrrillas = parrrillas;
	}

}
