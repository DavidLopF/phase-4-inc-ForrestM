package co.edu.unbosque.model;

/**
 * 
 * @author David L�pez, Andres Marin, Esteban Uribe, Yensy Gonzalez, Camilo Gomez
 *
 */

public class Artista {
	
	private String nombre;
	
	/**
	 * M�todo constructor de la clase artista
	 * @param a String que recibe para darle nombre al artista
	 */

	public Artista(String a) {
		nombre = a;
	}
	
	/**
	 * M�todo que sirve para poder obtener el atributo nombre 
	 * @return devuelve un String nombre 
	 */

	public String getNombre() {
		return nombre;
	}
	/**
	 * M�todo que sirve para modificar el valor del atributo nombre
	 * @param nombre retornna un String nombre modificado
	 */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
