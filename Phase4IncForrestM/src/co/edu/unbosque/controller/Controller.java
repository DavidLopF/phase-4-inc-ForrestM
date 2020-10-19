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
 * @author David López, Andres Marin, Esteban Uribe, Yensy Gonzalez, Camilo
 *         Gomez
 *
 */

public class Controller implements ActionListener, Reproductor {

	private Emisora emisora;
	private View view;
	static boolean play = false; // INICIA LA REPRODUCCION
	static boolean stop = false; // PERMITE DETENER LA REPRODUCCION DE LA CANCION
	static int Ndj = -1;
	static int NPar = -1;
	static int NCan = -1;
	static String ruta;
	private Player player;

	/**
	 * Método constructor del Controller, encargado de inicializar todos los
	 * atributos que tiene la clase Controlller
	 */

	public Controller() {

		emisora = new Emisora();
		emisora.getPropiedades().leerPropiedades();
		view = new View(this);
		view.setVisible(true);
		view.setTitle(emisora.cargaNombreVentana());
		emisora.asignarDjs();
		emisora.asignarParrillas();
		emisora.getPersistence().leerCanciones(emisora.getDjs());
		cargarDjs();

		ruta = "./data/DBs/ForrestM_DB_Canciones.csv";

		this.view.getPanelReproduccion().getLstDJs().addMouseListener(new MouseAdapter() { // ACCION CUANDO HACE CLIC
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

		this.view.getPanelReproduccion().getLstParrillas().addMouseListener(new MouseAdapter() { // ACCION CUANDO HACE UN
																								// CLIC
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 1) {
					JList target = (JList) me.getSource();
					int index = target.locationToIndex(me.getPoint());
					if (index >= 0 && view.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0) { // CARGA LOS
																										// DATOS
																										// SELECCIONADOS
						cargarCanciones(view.getPanelReproduccion().getLstDJs().getSelectedIndex(), index); // ACTUALIZA
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
		this.view.getPanelReproduccion().getLstCanciones().addMouseListener(new MouseAdapter() { // ACCION CUANDO HACE
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
	 * Método encargado de cargar los Djs en un Jlist
	 */

	// Carga los Djs en Jlist
	public void cargarDjs() {

		// TODO Auto-generated method stub
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (int i = 0; i < emisora.getDjs().size(); i++) {
			listModel.addElement(emisora.getDjs().get(i).getNombre());
		}
		view.getPanelReproduccion().getLstDJs().setModel(listModel);

	}

	/**
	 * Método encargado de actualizar las canciones cada vez mouse listener
	 * encargado de las canciones sea activado
	 * 
	 * @param index : recibe el elemento de la JList de las canciones esta activo
	 */

	private void actualizaCancion(int index) {
		// TODO Auto-generated method stub
		if (index >= 0 && view.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0
				&& view.getPanelReproduccion().getLstParrillas().getSelectedIndex() >= 0) { // CARGA LOS DATOS DEL
																							// SELECCIONADO
			Song c = emisora.getDjs().get(view.getPanelReproduccion().getLstDJs().getSelectedIndex()).getParrrillas()
					.get(view.getPanelReproduccion().getLstParrillas().getSelectedIndex()).getCanciones()
					.get(view.getPanelReproduccion().getLstCanciones().getSelectedIndex());
			view.getPanelReproduccion().getTxtArtista().setText(c.artista.getNombre());
			view.getPanelReproduccion().getTxtTitulo().setText(c.titulo);
			view.getPanelReproduccion().getCbbGeneros().setSelectedIndex(c.getGenero());
			view.getPanelReproduccion().getTxtDuracion().setText(c.durString());
			view.getPanelReproduccion().getTxtRuta().setText(c.ruta);
			view.getPanelReproduccion().getTxtLetra().setText(c.letra);
			view.getPanelReproduccion().getTxtLirica().setText(""); // LIMPIA EL ESPACIO DE LA LETRA
			view.cambiarImagen(c.getTitulo(), view.getPanelReproduccion().getImagenCan()); // CARGA LA IMAGEN

			try {
				System.out.println("Leyendo letra de Cancion seleccionada " + c.letra);
				BufferedReader reader = new BufferedReader(new FileReader(c.letra));
				String line = reader.readLine() + "\n";
				while (reader.ready()) {

					line += reader.readLine() + "\n";
				}
				reader.close(); // CIERRA EL ARCHIVO
				view.getPanelReproduccion().getTxtLirica().setText(line);
			} catch (Exception e) {
			}
		}
	}

	// SE CARGAN LAS PARRILLAS PARA QUE SE PUEDA LEER CON EL MOUSEADAPTER

	/**
	 * Método encargado de cargar las parrilas en el Jlist de las parrillas
	 *
	 * @param a : Recibe el número del elemento de la Jlist de djs que se ha
	 *          seleccionado
	 */
	public void cargarParrillas(int a) {

		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (int i = 0; i < emisora.getDjs().get(a).getParrrillas().size(); i++) {
			listModel.addElement(emisora.getDjs().get(a).getParrrillas().get(i).getNombre());
		}
		view.getPanelReproduccion().getLstParrillas().setModel(listModel);
		view.getPanelReproduccion().getLstCanciones().setModel(new DefaultListModel<String>()); // LIMPIA LISTA DE
																								// CANCIONES
	}

	// CARGAN LAS CANCIONES EN EL JLIST

	/**
	 * Método encargado de cargar las canciones en el Jlist de las canciones
	 * 
	 * @param m : Recibe el número del elemento seleccionado de la Jlist de djs
	 * @param n : Recibe el número del elemento seleccionado de la Jlist de
	 *          parrillas
	 */

	private void cargarCanciones(int m, int n) {
		// TODO Auto-generated method stub
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		for (int i = 0; i < emisora.getDjs().get(m).getParrrillas().get(n).getCanciones().size(); i++) {
			listModel.addElement(emisora.getDjs().get(m).getParrrillas().get(n).getCanciones().get(i).toString());

		}
		view.getPanelReproduccion().getLstCanciones().setModel(listModel);
		view.getPanelReproduccion().getLblTotal().setText(emisora.getDjs().get(m).getParrrillas().get(n).getDuracion());

	}

	/**
	 * Método implementado de la interface ActionListener utilizado para darle
	 * acción a los botones
	 */
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

//MENSAJE PARA EL USUARIO CUANDO SELECCIONE EL MODO DE TRANSMISION		
		if (command.contentEquals(view.getPanelReproduccion().Modo)) {

			if (view.getPanelReproduccion().getCbbModo().getSelectedIndex() == 0) {
				view.imprimirMensaje("ForestM Trasmitiendo en frecuencia AM ", "Modo transmisión");

				view.getPanelReproduccion().getModoStre().setVisible(false);
				view.getPanelReproduccion().getModoAM().setVisible(true);
				view.getPanelReproduccion().getModoFM().setVisible(false);
				view.getPanelReproduccion().getModoAll().setVisible(false);
				view.getPanelReproduccion().repaint();

				view.getPanelReproduccion().repaint();

			} else if (view.getPanelReproduccion().getCbbModo().getSelectedIndex() == 1) {
				view.imprimirMensaje("ForestM Trasmitiendo en frecuencia FM", "Modo transmisión");

				view.getPanelReproduccion().getModoStre().setVisible(false);
				view.getPanelReproduccion().getModoAM().setVisible(false);
				view.getPanelReproduccion().getModoFM().setVisible(true);
				view.getPanelReproduccion().getModoAll().setVisible(false);
				view.getPanelReproduccion().repaint();
				view.getPanelReproduccion().repaint();

			} else if (view.getPanelReproduccion().getCbbModo().getSelectedIndex() == 2) {

				view.imprimirMensaje("ForestM Tansmitiendo en Streaming :)", "Modo transmisión");

				view.getPanelReproduccion().getModoAll().setVisible(false);
				view.getPanelReproduccion().getModoAM().setVisible(false);
				view.getPanelReproduccion().getModoFM().setVisible(false);
				view.getPanelReproduccion().getModoStre().setVisible(true);
				view.getPanelReproduccion().repaint();

			} else if (view.getPanelReproduccion().getCbbModo().getSelectedIndex() == 3) {
				view.imprimirMensaje("ForestM Tansmitiendo trasmitiendo en todas las frecuencias y en streaming",
						"Modo transmisión");

				view.getPanelReproduccion().getModoStre().setVisible(false);
				view.getPanelReproduccion().getModoAM().setVisible(false);
				view.getPanelReproduccion().getModoFM().setVisible(false);
				view.getPanelReproduccion().getModoAll().setVisible(true);
				view.getPanelReproduccion().repaint();

				view.getPanelReproduccion().repaint();

			}

			// ACCION BOTON CREAR

		} else if (command.contentEquals(view.getPanelReproduccion().Crear)) {

			if (view.getPanelReproduccion().getRadDJ().isSelected()) {
				String name = view.pedirDato("Ingrese el nombre del nuevo DJ de la emisora ForrestM", "Nuevo DJ");
				try {
					if (name.length() > 4) {

						emisora.getDjs().add(new Dj(emisora.getDjs().size() + "; " + name)); // CREA UN NUEVO DJ
						cargarDjs();
					} else {
						view.imprimirMensaje("El nombre del DJ es muy corto por favor intenta de nuevo :) ", "Error");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block

				}

			} else if (view.getPanelReproduccion().getRadPa().isSelected()) {

				String nombre = view.pedirDato("Ingrese el nombre de la nueva parrilla", "Nueva parrilla");
				int inxDj = view.getPanelReproduccion().getLstDJs().getSelectedIndex();

				emisora.getDjs().get(inxDj).getParrrillas().add(new Parrilla(nombre));

				cargarParrillas(inxDj);

			} else if (view.getPanelReproduccion().getRadCa().isSelected()) {

				if (view.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0
						&& view.getPanelReproduccion().getLstParrillas().getSelectedIndex() >= 0) {

					Ndj = view.getPanelReproduccion().getLstDJs().getSelectedIndex();
					NPar = view.getPanelReproduccion().getLstParrillas().getSelectedIndex();
					emisora.getDjs().get(Ndj).getParrrillas().get(NPar).insertarCanciones(ruta); // ENVIA LA RUTA
																									// INICIAL
					cargarCanciones(view.getPanelReproduccion().getLstDJs().getSelectedIndex(),
							view.getPanelReproduccion().getLstParrillas().getSelectedIndex());
				} else {
					JOptionPane.showMessageDialog(view.getPanelReproduccion(),
							"No seleccionó DJ y Parrilla para agregar canción!");
				}

			}

			// ACCION BOTON BORRAR
		} else if (command.contentEquals(view.getPanelReproduccion().Borrar)) {

			if (view.getPanelReproduccion().getRadDJ().isSelected()) {

				int a = Integer.parseInt(view.pedirDato(
						"Ingrese el número del dj que quiere borrar (siendo el primer nombre la posicion 0): ",
						"Borrar Dj"));
				emisora.getDjs().remove(a);

				cargarDjs();

			} else if (view.getPanelReproduccion().getRadPa().isSelected()
					&& view.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0
					&& view.getPanelReproduccion().getLstParrillas().getSelectedIndex() >= 0) {

				int a = JOptionPane.showConfirmDialog(view, "Esta Seguro que desea borrar el Parrilla seleccionada ?");
				if (a == JOptionPane.YES_OPTION) {
					emisora.getDjs().get(view.getPanelReproduccion().getLstDJs().getSelectedIndex()).getParrrillas()
							.remove(view.getPanelReproduccion().getLstParrillas().getSelectedIndex());
					cargarParrillas(view.getPanelReproduccion().getLstDJs().getSelectedIndex());
				}
			} else if (view.getPanelReproduccion().getRadCa().isSelected()
					&& view.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0
					&& view.getPanelReproduccion().getLstParrillas().getSelectedIndex() >= 0
					&& view.getPanelReproduccion().getLstCanciones().getSelectedIndex() >= 0) {

				int a = JOptionPane.showConfirmDialog(view.getPanelReproduccion(),
						"Esta seguro que desea remover la cancion de la parrilla ForrestM seleccionada?");
				if (a == JOptionPane.YES_OPTION)
					emisora.getDjs().get(view.getPanelReproduccion().getLstDJs().getSelectedIndex()).getParrrillas()
							.get(view.getPanelReproduccion().getLstParrillas().getSelectedIndex()).getCanciones()
							.remove(view.getPanelReproduccion().getLstCanciones().getSelectedIndex());
				cargarCanciones(view.getPanelReproduccion().getLstDJs().getSelectedIndex(),
						view.getPanelReproduccion().getLstParrillas().getSelectedIndex());
			}

			// ACCION DEL COMANDO EDITAR

		} else if (command.contentEquals(view.getPanelReproduccion().Editar)) {

			if (view.getPanelReproduccion().getRadDJ().isSelected()) {
				String name = view.pedirDato(
						"Modifique el nombre del DJ " + view.getPanelReproduccion().getLstDJs().getSelectedValue(),
						"Editar Dj");

				emisora.getDjs().get(view.getPanelReproduccion().getLstDJs().getSelectedIndex()).setNombre(name); // METODO
																													// ABSTRACTO
				cargarDjs();

			} else if (view.getPanelReproduccion().getRadPa().isSelected()) {

				String name = view.pedirDato(
						"Modifique el nombre de la parrilla "
								+ view.getPanelReproduccion().getLstParrillas().getSelectedValue(),
						"Modificar parrillas");

				emisora.getDjs().get(view.getPanelReproduccion().getLstDJs().getSelectedIndex()).getParrrillas()
						.get(view.getPanelReproduccion().getLstParrillas().getSelectedIndex()).setNombre(name);

				cargarParrillas(view.getPanelReproduccion().getLstDJs().getSelectedIndex());

			} else if (view.getPanelReproduccion().getRadCa().isSelected()) {

				String nombre = view.pedirDato("Ingrese dato del nuevo nombre de la canción: ",
						"Cambio de nombre canción");
				int indxDjs = view.getPanelReproduccion().getLstDJs().getSelectedIndex();
				int indxPa = view.getPanelReproduccion().getLstParrillas().getSelectedIndex();
				int indxCa = view.getPanelReproduccion().getLstCanciones().getSelectedIndex();

				emisora.getDjs().get(indxDjs).getParrrillas().get(indxPa).getCanciones().get(indxCa).setTitulo(nombre);

				cargarCanciones(indxDjs, indxPa);
			}

			// ACION A EJECUTAR BOTON PLAY

		} else if (command.contentEquals(view.getPanelReproduccion().Play))

		{
			stop = true;
			try {
				player.close();
				play = false;
			} catch (Exception e2) {
			}

			Ndj = view.getPanelReproduccion().getLstDJs().getSelectedIndex();
			NPar = view.getPanelReproduccion().getLstParrillas().getSelectedIndex();
			NCan = view.getPanelReproduccion().getLstCanciones().getSelectedIndex();
			if (Ndj >= 0 && NPar >= 0 && NCan >= 0) {
				stop = false;
				reproducir();
			}

			// ACCION SIGUIENTE DEL BOTON
		} else if (command.contentEquals(view.getPanelReproduccion().Next)) {

			// REPRODUCIR LISTA
			Ndj = view.getPanelReproduccion().getLstDJs().getSelectedIndex();
			NPar = view.getPanelReproduccion().getLstParrillas().getSelectedIndex();
			if (Ndj >= 0 && NPar >= 0) {
				stop = true;
				siguiente();
			}
			// ACCION DETENER EL BOTON
		} else if (command.contentEquals(view.getPanelReproduccion().Stop)) {
			stop = true;
			detener();

		} else if (command.contentEquals(view.getPanelReproduccion().Subir)) {
			if (view.getPanelReproduccion().getRadDJ().isSelected()
					&& view.getPanelReproduccion().getLstDJs().getSelectedIndex() > 0) {
				int i = view.getPanelReproduccion().getLstDJs().getSelectedIndex();
				Dj temp = emisora.getDjs().get(i); // INTERCAMBIA POSICIONES
				emisora.getDjs().set(i, emisora.getDjs().get(i - 1));
				emisora.getDjs().set(i - 1, temp);
				cargarDjs(); // ACTUALIZAR

			} else if (view.getPanelReproduccion().getRadPa().isSelected()
					&& view.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0
					&& view.getPanelReproduccion().getLstParrillas().getSelectedIndex() > 0) {

				int i = view.getPanelReproduccion().getLstParrillas().getSelectedIndex();
				int j = view.getPanelReproduccion().getLstDJs().getSelectedIndex();
				Parrilla temp = emisora.getDjs().get(j).getParrrillas().get(i); // INTERCAMBIA POSICIONES
				emisora.getDjs().get(j).getParrrillas().set(i, emisora.getDjs().get(j).getParrrillas().get(i - 1));
				emisora.getDjs().get(j).getParrrillas().set(i - 1, temp);
				cargarParrillas(j); // ACTUALIZAR

			} else if (view.getPanelReproduccion().getRadCa().isSelected()
					&& view.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0
					&& view.getPanelReproduccion().getLstParrillas().getSelectedIndex() >= 0
					&& view.getPanelReproduccion().getLstCanciones().getSelectedIndex() > 0) {

				int i = view.getPanelReproduccion().getLstCanciones().getSelectedIndex();
				int j = view.getPanelReproduccion().getLstParrillas().getSelectedIndex();
				int k = view.getPanelReproduccion().getLstDJs().getSelectedIndex();
				Song temp = emisora.getDjs().get(k).getParrrillas().get(j).getCanciones().get(i); // INTERCAMBIO DE
																									// POSICIONES
				emisora.getDjs().get(k).getParrrillas().get(j).getCanciones().set(i,
						emisora.getDjs().get(k).getParrrillas().get(j).getCanciones().get(i - 1));
				emisora.getDjs().get(k).getParrrillas().get(j).getCanciones().set(i - 1, temp);
				cargarCanciones(k, j); // ACTUALIZAR

			}
		} else if (command.contentEquals(view.getPanelReproduccion().Bajar)) {
			if (view.getPanelReproduccion().getRadDJ().isSelected()
					&& view.getPanelReproduccion().getLstDJs().getSelectedIndex() < emisora.getDjs().size() - 1) {
				int i = view.getPanelReproduccion().getLstDJs().getSelectedIndex();
				Dj temp = emisora.getDjs().get(i); // INTERCAMBIA POSICIONES
				emisora.getDjs().set(i, emisora.getDjs().get(i + 1));
				emisora.getDjs().set(i + 1, temp);
				cargarDjs(); // ACTUALIZAR
			}
			if (view.getPanelReproduccion().getRadPa().isSelected()
					&& view.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0
					&& view.getPanelReproduccion().getLstParrillas().getSelectedIndex() < view.getPanelReproduccion()
							.getLstParrillas().getModel().getSize() - 1) {

				int i = view.getPanelReproduccion().getLstParrillas().getSelectedIndex();
				int j = view.getPanelReproduccion().getLstDJs().getSelectedIndex();
				Parrilla temp = emisora.getDjs().get(j).getParrrillas().get(i); // INTERCAMBIA POSICIONES
				emisora.getDjs().get(j).getParrrillas().set(i, emisora.getDjs().get(j).getParrrillas().get(i + 1));
				emisora.getDjs().get(j).getParrrillas().set(i + 1, temp);
				cargarParrillas(j); // ACTUALIZAR
			}
			if (view.getPanelReproduccion().getRadCa().isSelected()
					&& view.getPanelReproduccion().getLstDJs().getSelectedIndex() >= 0
					&& view.getPanelReproduccion().getLstParrillas().getSelectedIndex() >= 0
					&& view.getPanelReproduccion().getLstCanciones().getSelectedIndex() < view.getPanelReproduccion()
							.getLstCanciones().getModel().getSize() - 1) {
				int i = view.getPanelReproduccion().getLstCanciones().getSelectedIndex();
				int j = view.getPanelReproduccion().getLstParrillas().getSelectedIndex();
				int k = view.getPanelReproduccion().getLstDJs().getSelectedIndex();
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
	 * Método que permite la reproducción de un objeto tipo player
	 */
	public void reproducir() {
		if (!play) {
			try {
				view.getPanelReproduccion().getLstDJs().setSelectedIndex(Ndj);
				view.getPanelReproduccion().getLstParrillas().setSelectedIndex(NPar);
				view.getPanelReproduccion().getLstCanciones().setSelectedIndex(NCan);
				actualizaCancion(0); // ACTUALIZA LOS DATOS DE LA CANCION
				File cancion = new File(
						emisora.getDjs().get(Ndj).getParrrillas().get(NPar).getCanciones().get(NCan).ruta);
				FileInputStream fis = new FileInputStream(cancion);
				BufferedInputStream bis = new BufferedInputStream(fis);
				player = new Player(bis);
				play = true;
			} catch (Exception e) {
				System.out.println(
						"Problema reproduciendo la canción! :(  Valida la ruta del archivo mp3 o que el archivo mp3 no este corrupto ");
				stop = true;

			}

			new Thread() {
				@Override
				public void run() {
					try {
						player.play();
						System.out.println("Cancion finalizada (hilo) :) ");

						if (NCan < view.getPanelReproduccion().getLstCanciones().getModel().getSize() - 1 && !stop) {
							NCan++;
							cargarParrillas(Ndj);
							cargarCanciones(Ndj, NPar);
							view.getPanelReproduccion().getLstCanciones().setSelectedIndex(NCan);
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
	 * Método que da la opción de cambiar de cancion que se esta reproduciendo
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
				System.out.println("Problema reproduciendo la canción! :( ");
				stop = true;

			}
		} else {
			player.close();
			play = false;
			siguiente();
		}

	}

	/**
	 * Metodo encargado de detener la reproducción de una canción
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
