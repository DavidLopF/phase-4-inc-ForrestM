package co.edu.unbosque.model;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @author David L�pez, Andres Marin, Esteban Uribe, Yensy Gonzale, Camilo Gomez
 *
 */

public class Song {

	public Artista artista;
	public String titulo;
	public String genero;
	public long duracion;
	public String ruta;
	public String letra;
	public String imagen;

	/**
	 * Constructor de la clase song
	 * 
	 * @param art    nombre del artista
	 * @param titulo titulo de la canci�n
	 * @param genero genero de la canci�n
	 * @param dur    duraci�n de la canci�n
	 * @param ruta   donde esta guardada la canci�n
	 * @param letra  ruta de la letra de la canci�n
	 */

	public Song(Artista art, String titulo, String genero, long dur, String ruta, String letra) {

		this.artista = art;
		this.titulo = titulo;
		this.genero = genero;
		this.duracion = dur;
		this.ruta = ruta;
		this.letra = letra;

	}

	/**
	 * Sobrecarga del m�todo constructor de la clase song
	 * 
	 * @param art    nombre del artista
	 * @param titulo nombre de la canc�n
	 * @param genero genero de la canci�n
	 * @param dur    tiempo de duraci�n
	 * @param ruta   ruta donde esta almacenada
	 * @param letra  ruta de la letra de la canci�n
	 */

	public Song(Artista art, String titulo, int genero, long dur, String ruta, String letra) {

		this.artista = art;
		this.titulo = titulo;
		switch (genero) { // ASIGNA GENERO DEL NUMERO
		case 0:
			this.genero = Genre.Technohouse;
			break;
		case 1:
			this.genero = Genre.Merengue;
			break;
		case 2:
			this.genero = Genre.Reggaeton;
			break;
		case 3:
			this.genero = Genre.Bachata;
			break;
		}
		this.duracion = dur;
		this.ruta = ruta;
		this.letra = letra;
	}

	/**
	 * M�todo que guarda la duraci�n en un string
	 * 
	 * @return String linea
	 */

	public String durString() {
		String linea = "";
		if (duracion < 0) {
			linea = "00:00:00";
		} else {
			int h, m, s;
			h = (int) TimeUnit.SECONDS.toHours(duracion);
			m = (int) TimeUnit.SECONDS.toMinutes(duracion) - h * 60;
			s = (int) duracion - h * 60 * 60 - m * 60;
			linea = String.format("%02d:%02d:%02d", h, m, s);
		}
		return linea;
	}

	/**
	 * M�todo que convierte la duraci�n de la canci�n en un string
	 * @return String linea
	 */
	public String toString() {
		String linea;
		linea = durString();
		linea += " - " + artista.getNombre() + " - " + titulo;
		return linea;
	}

	/**
	 * Metodo que obtiene el genero de la canci�n
	 * @return n�mero del genero de la canci�n
	 */
	public int getGenero() {
		if (genero == Genre.Bachata)
			return 0;
		if (genero == Genre.Merengue)
			return 1;
		if (genero == Genre.Reggaeton)
			return 2;
		return 3;

	}
	
	/**
	 * M�todo que obtiene el atributo artista de la clase Song
	 * @return Artista artista
	 */

	public Artista getArtista() {
		return artista;
	}
	/**
	 * M�todo que modifica el atributo artista de la clase song
	 * @param Artista artista
	 */

	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	
	/**
	 * Metodo que obtiene el atributo titulo de la clase song 
	 * @return String song
	 */

	public String getTitulo() {
		return titulo;
	}

	/**
	 * M�todo que modifica el atributo titulo de la calse titulo
	 * @param Stirng titulo
	 */
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * M�todo que obtiene la duraci�n de la canci�n
	 * @return long duraci�n
	 */

	public long getDuracion() {
		return duracion;
	}
	
	/**
	 * M�todo que modifica la duraci�n de la canci�n
	 * @param Long duracion
	 */

	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}
	/**
	 * M�todo que obitene el atributo ruta de la clase song
	 * @return String ruta
	 */

	public String getRuta() {
		return ruta;
	}
	/**
	 * M�todo que modifica el atributo ruta de la clase song 
	 * @param String ruta
	 */

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	/**
	 * Metodo que obitene el atributo letra de la clase song
	 * @return String letra
	 */

	public String getLetra() {
		return letra;
	}
	/**
	 * M�todo que modifica el atributo letra de la calse song
	 * @param String letra
	 */

	public void setLetra(String letra) {
		this.letra = letra;
	}
	
	/**
	 * M�todo que obtiene el atributo de genero de la calse song
	 * @param String genero 
	 */

	public void setGenero(String genero) {
		this.genero = genero;
	}
}
