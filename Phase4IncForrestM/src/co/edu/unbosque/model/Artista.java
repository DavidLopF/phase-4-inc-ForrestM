package co.edu.unbosque.model;

/**
 * 
 * @author David López, Andres Marin, Esteban Uribe, Yensy Gonzalez, Camilo Gomez
 *
 */

public class Artista {
	
	private String nombre;
	
	/**
	 * Método constructor de la clase artista
	 * @param a String que recibe para darle nombre al artista
	 */

	public Artista(String a) {
		nombre = a;
	}
	
	/**
	 * Método que sirve para poder obtener el atributo nombre 
	 * @return devuelve un String nombre 
	 */

	public String getNombre() {
		return nombre;
	}
	/**
	 * Método que sirve para modificar el valor del atributo nombre
	 * @param nombre retornna un String nombre modificado
	 */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
