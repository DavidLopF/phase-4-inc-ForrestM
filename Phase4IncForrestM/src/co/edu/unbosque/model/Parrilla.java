package co.edu.unbosque.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author David López, Andres Marin, Esteban Uribe, Yensy Gonzalez, Camilo
 *         Gomez
 *
 */
public class Parrilla extends PlayList {

	private String nombre;
	private ArrayList<Song> canciones;

	public Parrilla(String nombre) {

		this.nombre = nombre;
		canciones = new ArrayList<Song>();

	}

	/**
	 * Método que optiene la duración un elemento de la arrayLsit canción
	 * 
	 * @return
	 */
	public String getDuracion() {
		long duracion = 0;
		for (int i = 0; i < canciones.size(); i++) {
			duracion += canciones.get(i).duracion;
		}

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

	// METODO PARA INSERTAR NUEVAS CANCIONES EN LA PARRILLA ACTUAL.
	// "ruta" - RUTA DEL DIRECTORIO POR DEFECTO PARA BUSCAR CANCIONES

	/**
	 * Método que perimite insertar con jFileChooser
	 * 
	 * @param String ruta que tiene la dirección del archivo
	 */

	public void insertarCanciones(String ruta) {

		JFileChooser jfc = new JFileChooser(); // LANZA CUADRO PARA SELECCIONAR ARCHIVO
		jfc.setMultiSelectionEnabled(true);
		jfc.setFileFilter(new FileNameExtensionFilter("Cargar musica a ForrestM -PowerBy Phase 4 Inc (*.mp3)", "mp3"));
		jfc.setCurrentDirectory(new File(ruta));
		int retjfc = jfc.showOpenDialog(null);

		if (retjfc == JFileChooser.APPROVE_OPTION) {
			File[] files = jfc.getSelectedFiles();
			for (int i = 0; i < files.length; i++) {
				this.canciones.add(new Song(new Artista(files[i].toString()), "", (int) 0, (long) 0,
						files[i].toString(), files[i].toString().replace(".mp3", ".txt")));
			}
		}
	}

	/**
	 * Carga las parrillas con un jfileChooser
	 */

	protected List<File> loadPlaylist() {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // CUADRO DE DIALOGO NATIVO
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		JFileChooser jfc = new JFileChooser(); // LANZA CUADRO PARA SELECCIONAR ARCHIVO
		jfc.setMultiSelectionEnabled(true);
		jfc.setFileFilter(new FileNameExtensionFilter("Archivo de música (*.mp3)", "mp3"));
		int retjfc = jfc.showOpenDialog(null);
		if (retjfc == JFileChooser.APPROVE_OPTION) {
			File[] files = jfc.getSelectedFiles();
			return Arrays.asList(files);
		} else {
			return null;
		}
	}

	/**
	 * Obtener el atributo nombre de la clase Parrilla
	 * 
	 * @return String nombre
	 */

	public String getNombre() {
		return nombre;
	}

	/**
	 * Modificar el atributo nombre de la clase parrila
	 * 
	 * @param nombre
	 */

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtener el atributo canciones de la clase Parrilla
	 * 
	 * @return ArrayList <Song> canciones
	 */

	public ArrayList<Song> getCanciones() {
		return canciones;
	}

	/**
	 * Modificar el atributo canciones de la clase Parrilla
	 * 
	 * @param ArrayLists <song>canciones
	 */

	public void setCanciones(ArrayList<Song> canciones) {
		this.canciones = canciones;
	}

}
