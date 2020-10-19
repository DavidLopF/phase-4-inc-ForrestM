package co.edu.unbosque.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;
import javazoom.jl.player.PlayerApplet;

/**
 * 
 * @author David L�pez, Andres Marin, Esteban Uribe, Yensy Gonzalez
 *
 */
public interface Reproductor {
	
	 /**
	  * M�todo para reproducir una cancion 
	  */

	public void reproducir(); // REPRODUCIR MP3
	
	/**
	 * M�todo para pasar a siguiente canci�n
	 */

	public void siguiente(); // PASAR A LA SIGUIENTE CANCION
	
	/**
	 * M�todo para detener la reproducci�n 
	 */

	public void detener(); // DETENER LA REPRODUCCION

}
