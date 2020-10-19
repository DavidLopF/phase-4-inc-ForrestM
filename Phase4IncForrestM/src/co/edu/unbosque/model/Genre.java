package co.edu.unbosque.model;
/**
 * 
 * @author David L�pez, Andres Marin, Esteban Uribe, Yensy Gonzalez, Camilo Gomez
 *
 */

public abstract class Genre {

	public static final String Technohouse = "Techno House";  //CREACION DE CONSTANTES CON LOS NOMBRES
	public static final String Merengue = "Merengue";
	public static final String Reggaeton = "Reggaeton";
	public static final String Bachata = "Bachata";
	private static final String TechnoHouse = null;
	
	/**
	 * M�todo que sirve para obtener el genero de una canci�n 
	 * @param g n�mero del genero 
	 * @return la constante del genero
	 */
	
	public String getGenero(int g) {
		switch (g) {
		case 0: return TechnoHouse;
		case 1: return Merengue;
		case 2: return Reggaeton;
		case 3: return Bachata;
		}
		return null;  //SELECCION NO ENCONTRADA  
	}

}
