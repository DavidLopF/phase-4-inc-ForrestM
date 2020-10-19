package co.edu.unbosque.model.persintence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import co.edu.unbosque.model.Artista;
import co.edu.unbosque.model.Dj;
import co.edu.unbosque.model.Song;

/**
 * 
 * @author David López, Andres Marin, Esteban Uribe, Yensy Gonzalez, Camilo
 *         Gomez
 *
 */
public class Persistence {

	private String archivoDjs;
	private String archivoParillas;
	private String archivoCanciones;
	

	/**
	 * Constructor de la clase persitence
	 * 
	 * @param a Ruta del csv de djs
	 * @param b Ruta del csv de las parrillas
	 * @param c Ruta del csv de las canciones
	 */

	public Persistence() {

		
	}

	/**
	 * Lee las parrillas del csv
	 * 
	 * @return Arraylist parrillas
	 */

	public ArrayList<String> leerParrillas() {

		ArrayList<String> parrillas = new ArrayList<>();
		try {

			BufferedReader reader = new BufferedReader(new FileReader(new File("./Data/DBs/ForrestM_DB_Parrillas.csv")));
			String linea = reader.readLine();

			while (linea != null) {

				parrillas.add(linea);
				linea = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return parrillas;
	}

	/**
	 * lee los djs del csv de djs
	 * 
	 * @return Arraylist djs
	 */

	public ArrayList<String> leerDjs() {
		ArrayList<String> djs = new ArrayList<>();
		try {

			BufferedReader reader = new BufferedReader(new FileReader(new File("./Data/DBs/ForrestM_DB_DJs.csv")));
			String linea = reader.readLine();

			while (linea != null) {

				djs.add(linea);
				linea = reader.readLine();
			}
			reader.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return djs;

	}

	/**
	 * Lee las canciones del csv de canciones
	 * 
	 * @param Recibe el dj que esta leyendo
	 */

	public void leerCanciones(ArrayList<Dj> djs) {

		try {
	
			BufferedReader reader = new BufferedReader(new FileReader("./Data/DBs/ForrestM_DB_Canciones.csv"));
			String line = reader.readLine();
			while (line != null) { // LEE LINEA POR LINEA Y LAS MUESTRA
				try {
					String[] parts = line.split("\\" + ";"); // ;"); // SEPARAR POR ";"
					djs.get(Integer.parseInt(parts[0])).getParrrillas().get(Integer.parseInt(parts[1])).getCanciones()
							.add(new Song(new Artista(parts[2]), parts[3], Integer.parseInt(parts[4]),
									Long.parseLong(parts[5]), parts[6], parts[7])); // CREA CANCION
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Linea omitida");
				}
				line = reader.readLine();
			}
			reader.close(); // CIERRA EL ARCHIVO
		} catch (Exception e) {

		}
	}
}
