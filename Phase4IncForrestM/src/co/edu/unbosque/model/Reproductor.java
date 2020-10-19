package co.edu.unbosque.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;
import javazoom.jl.player.PlayerApplet;

/**
 * 
 * @author David López, Andres Marin, Esteban Uribe, Yensy Gonzalez
 *
 */
public interface Reproductor {
	
	 /**
	  * Método para reproducir una cancion 
	  */

	public void reproducir(); // REPRODUCIR MP3
	
	/**
	 * Método para pasar a siguiente canción
	 */

	public void siguiente(); // PASAR A LA SIGUIENTE CANCION
	
	/**
	 * Método para detener la reproducción 
	 */

	public void detener(); // DETENER LA REPRODUCCION

}
