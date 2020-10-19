package co.edu.unbosque.model;

import java.util.ArrayList;

/**
 * 
 * @author David López, Andres Marin, Esteban Uribe, Yensy Gonzalez, Camilo Gomez
 *
 */

public class Dj {

	private String nombre;
	private ArrayList<Parrilla> parrrillas;

	/**
	 * Método construcor de la clase DJ
	 * 
	 * @param a Recibe un Stirng a para darle valor el atributo nombre
	 */

	public Dj(String a) {

		nombre = a;
		parrrillas = new ArrayList<Parrilla>();

	}

	/**
	 * Método que obtiener el atributo nombre
	 * 
	 * @return String nombre
	 */

	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que modifica el string nombre
	 * 
	 * @param String nombre
	 */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método que obtiene el aributo ArrayList parrillas
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
