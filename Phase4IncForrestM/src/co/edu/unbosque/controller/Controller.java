package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import co.edu.unbosque.model.Artista;
import co.edu.unbosque.model.Dj;
import co.edu.unbosque.model.Emisora;
import co.edu.unbosque.model.Parrilla;
import co.edu.unbosque.model.Reproductor;
import co.edu.unbosque.model.Song;
import co.edu.unbosque.model.persintence.Persistence;
import co.edu.unbosque.model.persintence.Propiedades;
import co.edu.unbosque.view.PanelReproduccion;
import co.edu.unbosque.view.View;
import javazoom.jl.player.Player;

/**
 * 
 * @author David L�pez, Andres Marin, Esteban Uribe, Yensy Gonzalez, Camilo
 *         Gomez
 *
 */

public class Controller implements ActionListener, Reproductor {

	private Emisora emisora;
	private View gui;
	static boolean play = false; // INICIA LA REPRODUCCION
	static boolean stop = false; // PERMITE DETENER LA REPRODUCCION DE LA CANCION
	static int Ndj = -1;
	static int NPar = -1;
	static int NCan = -1;
	static String ruta;
	private Player player;

	/**
	 * M�todo constructor del Controller, encargado de inicializar todos los
	 * atributos que tiene la clase Controlller
	 */

	public Controller() {

		emisora = new Emisora();
		emisora.getPropiedades().leerPropiedades();
		gui = new View(this);
		gui.setVisible(true);
		gui.setTitle(emisora.cargaNombreVentana());
		emisora.asignarDjs();
		emisora.asignarParrillas();
		emisora.getPersistence().leerCanciones(emisora.getDjs());
		cargarDjs();

		ruta = "./data/DBs/ForrestM_DB_Canciones.csv";

		this.gui.getPanelReproduccion().getLstDJs().addMouseListener(new MouseAdapter() { // ACCION CUANDO HACE CLIC
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0) { // CARGA LOS DATOS SELECCIONADOS
						cargarParrillas(index); // ACTUALIZA LA LISTA DE PARRIJAS O PROGRAMAS RADIALES DEL DJ
					}

				}
			}
		});

		this.gui.getPanelReproduccion().getLstParrillas().addMouseListener(new MouseAdapter() { // ACCION CUANDO HACE UN
																								// CLIC
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0 && gui.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0) { // CARGA LOS
																										// DATOS
																										// SELECCIONADOS
						cargarCanciones(gui.getPanelReproduccion().getLstDJs().getSelectedIndex(), index); // ACTUALIZA
																											// LA LISTA
																											// DE
																											// CANCIONES
																											// DE LA
																											// PARRILLA
																											// Y DJ
					}
				}
			}
		});

		// ACTUALIZA DATOS DE CANCIONES
		this.gui.getPanelReproduccion().getLstCanciones().addMouseListener(new MouseAdapter() { // ACCION CUANDO HACE
																								// CLIC
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					actualizaCancion(index);
				}
			}
		});

	}

	/**
	 * M�todo encargado de cargar los Djs en un Jlist
	 */

	// Carga los Djs en Jlist
	public void cargarDjs() {

		// TODO Auto-generated method stub
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (int i = 0; i < emisora.getDjs().size(); i++) {
			listModel.addElement(emisora.getDjs().get(i).getNombre());
		}
		gui.getPanelReproduccion().getLstDJs().setModel(listModel);

	}

	/**
	 * M�todo encargado de actualizar las canciones cada vez mouse listener
	 * encargado de las canciones sea activado
	 * 
	 * @param index : recibe el elemento de la JList de las canciones esta activo
	 */

	private void actualizaCancion(int index) {
		// TODO Auto-generated method stub
		if (index >= 0 && gui.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0
				&& gui.getPanelReproduccion().getLstParrillas().getSelectedIndex() >= 0) { // CARGA LOS DATOS DEL
																							// SELECCIONADO
			Song c = emisora.getDjs().get(gui.getPanelReproduccion().getLstDJs().getSelectedIndex()).getParrrillas()
					.get(gui.getPanelReproduccion().getLstParrillas().getSelectedIndex()).getCanciones()
					.get(gui.getPanelReproduccion().getLstCanciones().getSelectedIndex());
			gui.getPanelReproduccion().getTxtArtista().setText(c.artista.getNombre());
			gui.getPanelReproduccion().getTxtTitulo().setText(c.titulo);
			gui.getPanelReproduccion().getCbbGeneros().setSelectedIndex(c.getGenero());
			gui.getPanelReproduccion().getTxtDuracion().setText(c.durString());
			gui.getPanelReproduccion().getTxtRuta().setText(c.ruta);
			gui.getPanelReproduccion().getTxtLetra().setText(c.letra);
			gui.getPanelReproduccion().getTxtLirica().setText(""); // LIMPIA EL ESPACIO DE LA LETRA
			gui.cambiarImagen(c.getTitulo(), gui.getPanelReproduccion().getImagenCan()); // CARGA LA IMAGEN

			try {
				System.out.println("Leyendo letra de Cancion seleccionada " + c.letra);
				BufferedReader reader = new BufferedReader(new FileReader(c.letra));
				String line = reader.readLine() + "\n";
				while (reader.ready()) {

					line += reader.readLine() + "\n";
				}
				reader.close(); // CIERRA EL ARCHIVO
				gui.getPanelReproduccion().getTxtLirica().setText(line);
			} catch (Exception e) {
			}
		}
	}

	// SE CARGAN LAS PARRILLAS PARA QUE SE PUEDA LEER CON EL MOUSEADAPTER

	/**
	 * M�todo encargado de cargar las parrilas en el Jlist de las parrillas
	 *
	 * @param a : Recibe el n�mero del elemento de la Jlist de djs que se ha
	 *          seleccionado
	 */
	public void cargarParrillas(int a) {

		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (int i = 0; i < emisora.getDjs().get(a).getParrrillas().size(); i++) {
			listModel.addElement(emisora.getDjs().get(a).getParrrillas().get(i).getNombre());
		}
		gui.getPanelReproduccion().getLstParrillas().setModel(listModel);
		gui.getPanelReproduccion().getLstCanciones().setModel(new DefaultListModel<String>()); // LIMPIA LISTA DE
																								// CANCIONES
	}

	// CARGAN LAS CANCIONES EN EL JLIST

	/**
	 * M�todo encargado de cargar las canciones en el Jlist de las canciones
	 * 
	 * @param m : Recibe el n�mero del elemento seleccionado de la Jlist de djs
	 * @param n : Recibe el n�mero del elemento seleccionado de la Jlist de
	 *          parrillas
	 */

	private void cargarCanciones(int m, int n) {
		// TODO Auto-generated method stub
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (int i = 0; i < emisora.getDjs().get(m).getParrrillas().get(n).getCanciones().size(); i++) {
			listModel.addElement(emisora.getDjs().get(m).getParrrillas().get(n).getCanciones().get(i).toString());

		}
		gui.getPanelReproduccion().getLstCanciones().setModel(listModel);
		gui.getPanelReproduccion().getLblTotal().setText(emisora.getDjs().get(m).getParrrillas().get(n).getDuracion());

	}

	/**
	 * M�todo implementado de la interface ActionListener utilizado para darle
	 * acci�n a los botones
	 */
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

//MENSAJE PARA EL USUARIO CUANDO SELECCIONE EL MODO DE TRANSMISION		
		if (command.contentEquals(gui.getPanelReproduccion().Modo)) {

			if (gui.getPanelReproduccion().getCbbModo().getSelectedIndex() == 0) {
				gui.imprimirMensaje("ForestM Trasmitiendo en frecuencia AM ", "Modo transmisi�n");

				gui.getPanelReproduccion().getModoStre().setVisible(false);
				gui.getPanelReproduccion().getModoAM().setVisible(true);
				gui.getPanelReproduccion().getModoFM().setVisible(false);
				gui.getPanelReproduccion().getModoAll().setVisible(false);
				gui.getPanelReproduccion().repaint();

				gui.getPanelReproduccion().repaint();

			} else if (gui.getPanelReproduccion().getCbbModo().getSelectedIndex() == 1) {
				gui.imprimirMensaje("ForestM Trasmitiendo en frecuencia FM", "Modo transmisi�n");

				gui.getPanelReproduccion().getModoStre().setVisible(false);
				gui.getPanelReproduccion().getModoAM().setVisible(false);
				gui.getPanelReproduccion().getModoFM().setVisible(true);
				gui.getPanelReproduccion().getModoAll().setVisible(false);
				gui.getPanelReproduccion().repaint();
				gui.getPanelReproduccion().repaint();

			} else if (gui.getPanelReproduccion().getCbbModo().getSelectedIndex() == 2) {

				gui.imprimirMensaje("ForestM Tansmitiendo en Streaming :)", "Modo transmisi�n");

				gui.getPanelReproduccion().getModoAll().setVisible(false);
				gui.getPanelReproduccion().getModoAM().setVisible(false);
				gui.getPanelReproduccion().getModoFM().setVisible(false);
				gui.getPanelReproduccion().getModoStre().setVisible(true);
				gui.getPanelReproduccion().repaint();

			} else if (gui.getPanelReproduccion().getCbbModo().getSelectedIndex() == 3) {
				gui.imprimirMensaje("ForestM Tansmitiendo trasmitiendo en todas las frecuencias y en streaming",
						"Modo transmisi�n");

				gui.getPanelReproduccion().getModoStre().setVisible(false);
				gui.getPanelReproduccion().getModoAM().setVisible(false);
				gui.getPanelReproduccion().getModoFM().setVisible(false);
				gui.getPanelReproduccion().getModoAll().setVisible(true);
				gui.getPanelReproduccion().repaint();

				gui.getPanelReproduccion().repaint();

			}

			// ACCION BOTON CREAR

		} else if (command.contentEquals(gui.getPanelReproduccion().Crear)) {

			if (gui.getPanelReproduccion().getRadDJ().isSelected()) {
				String name = gui.pedirDato("Ingrese el nombre del nuevo DJ de la emisora ForrestM", "Nuevo DJ");
				try {
					if (name.length() > 4) {

						emisora.getDjs().add(new Dj(emisora.getDjs().size() + "; " + name)); // CREA UN NUEVO DJ
						cargarDjs();
					} else {
						gui.imprimirMensaje("El nombre del DJ es muy corto por favor intenta de nuevo :) ", "Error");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block

				}

			} else if (gui.getPanelReproduccion().getRadPa().isSelected()) {

				String nombre = gui.pedirDato("Ingrese el nombre de la nueva parrilla", "Nueva parrilla");
				int inxDj = gui.getPanelReproduccion().getLstDJs().getSelectedIndex();

				emisora.getDjs().get(inxDj).getParrrillas().add(new Parrilla(nombre));

				cargarParrillas(inxDj);

			} else if (gui.getPanelReproduccion().getRadCa().isSelected()) {

				if (gui.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0
						&& gui.getPanelReproduccion().getLstParrillas().getSelectedIndex() >= 0) {

					Ndj = gui.getPanelReproduccion().getLstDJs().getSelectedIndex();
					NPar = gui.getPanelReproduccion().getLstParrillas().getSelectedIndex();
					emisora.getDjs().get(Ndj).getParrrillas().get(NPar).insertarCanciones(ruta); // ENVIA LA RUTA
																									// INICIAL
					cargarCanciones(gui.getPanelReproduccion().getLstDJs().getSelectedIndex(),
							gui.getPanelReproduccion().getLstParrillas().getSelectedIndex());
				} else {
					JOptionPane.showMessageDialog(gui.getPanelReproduccion(),
							"No seleccionó DJ y Parrilla para agregar canción!");
				}

			}

			// ACCION BOTON BORRAR
		} else if (command.contentEquals(gui.getPanelReproduccion().Borrar)) {

			if (gui.getPanelReproduccion().getRadDJ().isSelected()) {

				int a = Integer.parseInt(gui.pedirDato(
						"Ingrese el n�mero del dj que quiere borrar (siendo el primer nombre la posicion 0): ",
						"Borrar Dj"));
				emisora.getDjs().remove(a);

				cargarDjs();

			} else if (gui.getPanelReproduccion().getRadPa().isSelected()
					&& gui.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0
					&& gui.getPanelReproduccion().getLstParrillas().getSelectedIndex() >= 0) {

				int a = JOptionPane.showConfirmDialog(gui, "Esta Seguro que desea borrar el Parrilla seleccionada ?");
				if (a == JOptionPane.YES_OPTION) {
					emisora.getDjs().get(gui.getPanelReproduccion().getLstDJs().getSelectedIndex()).getParrrillas()
							.remove(gui.getPanelReproduccion().getLstParrillas().getSelectedIndex());
					cargarParrillas(gui.getPanelReproduccion().getLstDJs().getSelectedIndex());
				}
			} else if (gui.getPanelReproduccion().getRadCa().isSelected()
					&& gui.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0
					&& gui.getPanelReproduccion().getLstParrillas().getSelectedIndex() >= 0
					&& gui.getPanelReproduccion().getLstCanciones().getSelectedIndex() >= 0) {

				int a = JOptionPane.showConfirmDialog(gui.getPanelReproduccion(),
						"Esta seguro que desea remover la cancion de la parrilla ForrestM seleccionada?");
				if (a == JOptionPane.YES_OPTION)
					emisora.getDjs().get(gui.getPanelReproduccion().getLstDJs().getSelectedIndex()).getParrrillas()
							.get(gui.getPanelReproduccion().getLstParrillas().getSelectedIndex()).getCanciones()
							.remove(gui.getPanelReproduccion().getLstCanciones().getSelectedIndex());
				cargarCanciones(gui.getPanelReproduccion().getLstDJs().getSelectedIndex(),
						gui.getPanelReproduccion().getLstParrillas().getSelectedIndex());
			}

			// ACCION DEL COMANDO EDITAR

		} else if (command.contentEquals(gui.getPanelReproduccion().Editar)) {

			if (gui.getPanelReproduccion().getRadDJ().isSelected()) {
				String name = gui.pedirDato(
						"Modifique el nombre del DJ " + gui.getPanelReproduccion().getLstDJs().getSelectedValue(),
						"Editar Dj");

				emisora.getDjs().get(gui.getPanelReproduccion().getLstDJs().getSelectedIndex()).setNombre(name); // METODO
																													// ABSTRACTO
				cargarDjs();

			} else if (gui.getPanelReproduccion().getRadPa().isSelected()) {

				String name = gui.pedirDato(
						"Modifique el nombre de la parrilla "
								+ gui.getPanelReproduccion().getLstParrillas().getSelectedValue(),
						"Modificar parrillas");

				emisora.getDjs().get(gui.getPanelReproduccion().getLstDJs().getSelectedIndex()).getParrrillas()
						.get(gui.getPanelReproduccion().getLstParrillas().getSelectedIndex()).setNombre(name);

				cargarParrillas(gui.getPanelReproduccion().getLstDJs().getSelectedIndex());

			} else if (gui.getPanelReproduccion().getRadCa().isSelected()) {

				String nombre = gui.pedirDato("Ingrese dato del nuevo nombre de la canci�n: ",
						"Cambio de nombre canci�n");
				int indxDjs = gui.getPanelReproduccion().getLstDJs().getSelectedIndex();
				int indxPa = gui.getPanelReproduccion().getLstParrillas().getSelectedIndex();
				int indxCa = gui.getPanelReproduccion().getLstCanciones().getSelectedIndex();

				emisora.getDjs().get(indxDjs).getParrrillas().get(indxPa).getCanciones().get(indxCa).setTitulo(nombre);

				cargarCanciones(indxDjs, indxPa);
			}

			// ACION A EJECUTAR BOTON PLAY

		} else if (command.contentEquals(gui.getPanelReproduccion().Play))

		{
			stop = true;
			try {
				player.close();
				play = false;
			} catch (Exception e2) {
			}

			Ndj = gui.getPanelReproduccion().getLstDJs().getSelectedIndex();
			NPar = gui.getPanelReproduccion().getLstParrillas().getSelectedIndex();
			NCan = gui.getPanelReproduccion().getLstCanciones().getSelectedIndex();
			if (Ndj >= 0 && NPar >= 0 && NCan >= 0) {
				stop = false;
				reproducir();
			}

			// ACCION SIGUIENTE DEL BOTON
		} else if (command.contentEquals(gui.getPanelReproduccion().Next)) {

			// REPRODUCIR LISTA
			Ndj = gui.getPanelReproduccion().getLstDJs().getSelectedIndex();
			NPar = gui.getPanelReproduccion().getLstParrillas().getSelectedIndex();
			if (Ndj >= 0 && NPar >= 0) {
				stop = true;
				siguiente();
			}
			// ACCION DETENER EL BOTON
		} else if (command.contentEquals(gui.getPanelReproduccion().Stop)) {
			stop = true;
			detener();

		} else if (command.contentEquals(gui.getPanelReproduccion().Subir)) {
			if (gui.getPanelReproduccion().getRadDJ().isSelected()
					&& gui.getPanelReproduccion().getLstDJs().getSelectedIndex() > 0) {
				int i = gui.getPanelReproduccion().getLstDJs().getSelectedIndex();
				Dj temp = emisora.getDjs().get(i); // INTERCAMBIA POSICIONES
				emisora.getDjs().set(i, emisora.getDjs().get(i - 1));
				emisora.getDjs().set(i - 1, temp);
				cargarDjs(); // ACTUALIZAR

			} else if (gui.getPanelReproduccion().getRadPa().isSelected()
					&& gui.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0
					&& gui.getPanelReproduccion().getLstParrillas().getSelectedIndex() > 0) {

				int i = gui.getPanelReproduccion().getLstParrillas().getSelectedIndex();
				int j = gui.getPanelReproduccion().getLstDJs().getSelectedIndex();
				Parrilla temp = emisora.getDjs().get(j).getParrrillas().get(i); // INTERCAMBIA POSICIONES
				emisora.getDjs().get(j).getParrrillas().set(i, emisora.getDjs().get(j).getParrrillas().get(i - 1));
				emisora.getDjs().get(j).getParrrillas().set(i - 1, temp);
				cargarParrillas(j); // ACTUALIZAR

			} else if (gui.getPanelReproduccion().getRadCa().isSelected()
					&& gui.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0
					&& gui.getPanelReproduccion().getLstParrillas().getSelectedIndex() >= 0
					&& gui.getPanelReproduccion().getLstCanciones().getSelectedIndex() > 0) {

				int i = gui.getPanelReproduccion().getLstCanciones().getSelectedIndex();
				int j = gui.getPanelReproduccion().getLstParrillas().getSelectedIndex();
				int k = gui.getPanelReproduccion().getLstDJs().getSelectedIndex();
				Song temp = emisora.getDjs().get(k).getParrrillas().get(j).getCanciones().get(i); // INTERCAMBIO DE
																									// POSICIONES
				emisora.getDjs().get(k).getParrrillas().get(j).getCanciones().set(i,
						emisora.getDjs().get(k).getParrrillas().get(j).getCanciones().get(i - 1));
				emisora.getDjs().get(k).getParrrillas().get(j).getCanciones().set(i - 1, temp);
				cargarCanciones(k, j); // ACTUALIZAR

			}
		} else if (command.contentEquals(gui.getPanelReproduccion().Bajar)) {
			if (gui.getPanelReproduccion().getRadDJ().isSelected()
					&& gui.getPanelReproduccion().getLstDJs().getSelectedIndex() < emisora.getDjs().size() - 1) {
				int i = gui.getPanelReproduccion().getLstDJs().getSelectedIndex();
				Dj temp = emisora.getDjs().get(i); // INTERCAMBIA POSICIONES
				emisora.getDjs().set(i, emisora.getDjs().get(i + 1));
				emisora.getDjs().set(i + 1, temp);
				cargarDjs(); // ACTUALIZAR
			}
			if (gui.getPanelReproduccion().getRadPa().isSelected()
					&& gui.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0
					&& gui.getPanelReproduccion().getLstParrillas().getSelectedIndex() < gui.getPanelReproduccion()
							.getLstParrillas().getModel().getSize() - 1) {

				int i = gui.getPanelReproduccion().getLstParrillas().getSelectedIndex();
				int j = gui.getPanelReproduccion().getLstDJs().getSelectedIndex();
				Parrilla temp = emisora.getDjs().get(j).getParrrillas().get(i); // INTERCAMBIA POSICIONES
				emisora.getDjs().get(j).getParrrillas().set(i, emisora.getDjs().get(j).getParrrillas().get(i + 1));
				emisora.getDjs().get(j).getParrrillas().set(i + 1, temp);
				cargarParrillas(j); // ACTUALIZAR
			}
			if (gui.getPanelReproduccion().getRadCa().isSelected()
					&& gui.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0
					&& gui.getPanelReproduccion().getLstParrillas().getSelectedIndex() >= 0
					&& gui.getPanelReproduccion().getLstCanciones().getSelectedIndex() < gui.getPanelReproduccion()
							.getLstCanciones().getModel().getSize() - 1) {
				int i = gui.getPanelReproduccion().getLstCanciones().getSelectedIndex();
				int j = gui.getPanelReproduccion().getLstParrillas().getSelectedIndex();
				int k = gui.getPanelReproduccion().getLstDJs().getSelectedIndex();
				Song temp = emisora.getDjs().get(k).getParrrillas().get(j).getCanciones().get(i); // INTERCAMBIA
																									// POSICIONES
				emisora.getDjs().get(k).getParrrillas().get(j).getCanciones().set(i,
						emisora.getDjs().get(k).getParrrillas().get(j).getCanciones().get(i + 1));
				emisora.getDjs().get(k).getParrrillas().get(j).getCanciones().set(i + 1, temp);
				cargarCanciones(k, j); // ACTUALIZAR
			}
		}
	}

	/**
	 * M�todo que permite la reproducci�n de un objeto tipo player
	 */
	public void reproducir() {
		if (!play) {
			try {
				gui.getPanelReproduccion().getLstDJs().setSelectedIndex(Ndj);
				gui.getPanelReproduccion().getLstParrillas().setSelectedIndex(NPar);
				gui.getPanelReproduccion().getLstCanciones().setSelectedIndex(NCan);
				actualizaCancion(0); // ACTUALIZA LOS DATOS DE LA CANCION
				File cancion = new File(
						emisora.getDjs().get(Ndj).getParrrillas().get(NPar).getCanciones().get(NCan).ruta);
				FileInputStream fis = new FileInputStream(cancion);
				BufferedInputStream bis = new BufferedInputStream(fis);
				player = new Player(bis);
				play = true;
			} catch (Exception e) {
				System.out.println(
						"Problema reproduciendo la canci�n! :(  Valida la ruta del archivo mp3 o que el archivo mp3 no este corrupto ");
				stop = true;

			}

			new Thread() {
				@Override
				public void run() {
					try {
						player.play();
						System.out.println("Cancion finalizada (hilo) :) ");

						if (NCan < gui.getPanelReproduccion().getLstCanciones().getModel().getSize() - 1 && !stop) {
							NCan++;
							cargarParrillas(Ndj);
							cargarCanciones(Ndj, NPar);
							gui.getPanelReproduccion().getLstCanciones().setSelectedIndex(NCan);
							actualizaCancion(0); // ACTUALIZA LOS DATOS DE LA CANCION
							reproducir();
						}
					} catch (Exception e) {
					}
				}
			}.start();
		} else {
			try {
				player.close();
				play = false;
				reproducir();
			} catch (Exception e) {
			}
		}

	}

	/**
	 * M�todo que da la opci�n de cambiar de cancion que se esta reproduciendo
	 */
	public void siguiente() {
		if (!play) {
			try {
				cargarParrillas(Ndj);
				cargarCanciones(Ndj, NPar);

				NCan++;

				play = true;
				stop = false;

				reproducir();
			} catch (Exception e) {
				System.out.println("Problema reproduciendo la canci�n! :( ");
				stop = true;

			}
		} else {
			player.close();
			play = false;
			siguiente();
		}

	}

	/**
	 * Metodo encargado de detener la reproducci�n de una canci�n
	 */

	public void detener() {
		try {
			stop = true;

			player.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block

		} // TERMINA LA REPRODUCCION

	}

}