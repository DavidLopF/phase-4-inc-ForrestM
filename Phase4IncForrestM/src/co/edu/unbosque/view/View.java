package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import co.edu.unbosque.controller.Controller;

/**
 * 
 * @author David López, David López, Andres Marin, Esteban Uribe, Yensy Gonzalez
 *
 */

public class View extends JFrame {

	private PanelReproduccion panelReproduccion;
	private FrameDatos frameDatos;
	private String titulo;

	/**
	 * Constructor de la clase View
	 * 
	 * @param Control control
	 */

	public View(Controller control) {

		setTitle(titulo);
		setSize(850, 700);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializarComponentes();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(false);

		// listeners botones panel reproducir

		panelReproduccion.getBtnBajar().addActionListener(control);
		panelReproduccion.getBtnBorrar().addActionListener(control);
		panelReproduccion.getBtnCrear().addActionListener(control);
		panelReproduccion.getBtnEditar().addActionListener(control);
		panelReproduccion.getBtnPlay().addActionListener(control);
		panelReproduccion.getBtnSiguiente().addActionListener(control);
		panelReproduccion.getBtnStop().addActionListener(control);
		panelReproduccion.getBtnStop().addActionListener(control);
		panelReproduccion.getBtnSubir().addActionListener(control);
		panelReproduccion.getCbbModo().addActionListener(control);
		frameDatos.getGuardar().addActionListener(control);

	}

	/**
	 * inicializa los paneles del Jframe
	 */

	public void inicializarComponentes() {

		panelReproduccion = new PanelReproduccion();
		add(panelReproduccion, BorderLayout.CENTER);

		frameDatos = new FrameDatos();

	}

	/**
	 * Obtiene atributo panelReproduccion de la clase view
	 * 
	 * @return panelReproduccion
	 */

	public PanelReproduccion getPanelReproduccion() {
		return panelReproduccion;
	}

	/**
	 * Modifica el atributo PanelReproducción de la clase view
	 * 
	 * @param panelReproducción
	 */

	public void setPanelReproduccion(PanelReproduccion panelReproducción) {
		this.panelReproduccion = panelReproducción;
	}

	// METODO PARA IMPRIMIR MENSAJES

	public FrameDatos getFrameDatos() {
		return frameDatos;
	}

	public void setFrameDatos(FrameDatos frameDatos) {
		this.frameDatos = frameDatos;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Imprime un Stirng en JOptionPane
	 * 
	 * @param String a
	 * @param String titulo
	 */

	public void imprimirMensaje(String a, String titulo) {

		JOptionPane.showInternalMessageDialog(null, a, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	// METODO PARA PERDIR DATOS

	/**
	 * Pide un dato en un jOptionPane
	 * 
	 * @param a
	 * @param titulo
	 * @return JoptionPane
	 */

	public String pedirDato(String a, String titulo) {
		return (JOptionPane.showInputDialog(null, a, titulo, JOptionPane.INFORMATION_MESSAGE));
	}

	/**
	 * Cambia las imagen que se carga en jLabel
	 * 
	 * @param String d
	 * @param JLabel a
	 */

	public void cambiarImagen(String d, JLabel a) {
		ImageIcon im = new ImageIcon("./Data/Imagenes/" + d + ".png");
		ImageIcon icono = new ImageIcon(
				im.getImage().getScaledInstance(this.getWidth() - 500, this.getHeight() - 500, Image.SCALE_DEFAULT));
		a.setIcon(icono);

	}

}
