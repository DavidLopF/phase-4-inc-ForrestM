package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import co.edu.unbosque.model.Genre;

/**
 * 
 * @author David López, Andres Marin, Esteban Uribe, Yensy Gonzalez, Camilo
 *         Gomez
 *
 */
public class PanelReproduccion extends JPanel {

	public static final long serialVersionUID = 1L; // ASIGNA SERIAL A LA CLASE
	public static final String Subir = "subir", Stop = "stop", Bajar = "bajar", Editar = "editar", Borrar = "borrar",
			Play = "play", Next = "next", Modo = "Modo", Crear = "Crear", RadioGr = "RadioGr";

	private JLabel logo, cancion, modoAM, modoFM, modoStre, modoAll, imagenCan; // JLABELS PARA CARGAR IMAGENES

	private JList<String> lstDJs, lstParrillas, lstCanciones;
	private JComboBox<String> cbbGeneros, cbbModo, cbbTipo; // SELECCION DE TIPOS

	private JLabel lblModo, lblTipo; // CARACTERISTICAS DE LA EMISORA
	private JLabel lblDJs, lblParrillas, lblCanciones, lblTotal;
	private JLabel lblArtista, lblTitulo, lblGenero, lblDuracion, lblRuta, lblLetra, lblLirica; // ETIQUETAS DE TEXTO
	private JTextField txtArtista, txtTitulo, txtDuracion, txtRuta, txtLetra;
	private JTextArea txtLirica; // CUADROS DE TEXTO
	private JRadioButton radDJ, radPa, radCa; // RADIO BOTONES PARA SELECCIONAR
	private JButton btnSubir, btnBajar, btnCrear, btnEditar, btnBorrar, btnPlay, btnSiguiente, btnStop; // BOTON DE
																										// COMANDOS

	/**
	 * Constructor de la clase PanelReproduccion
	 */

	public PanelReproduccion() {

		setLayout(null);
		setVisible(true);
		setBackground(Color.WHITE);
		setBorder(new TitledBorder("Emisora"));
		inicializarComponentes();

	}

	/**
	 * inicializa y añade todos los elementos graficos del JPanel
	 */
	public void inicializarComponentes() {

		lblModo = new JLabel("Modo de transmisión: ");
		lblModo.setBounds(20, 20, 150, 20);

		cbbModo = new JComboBox<>(new String[] { "AM", "FM", "Stream", "Todas" });
		cbbModo.setBounds(150, 20, 120, 20);
		cbbModo.setActionCommand(Modo);

		lblTipo = new JLabel("Genero: ");
		lblTipo.setBounds(380, 20, 100, 20);
		cbbTipo = new JComboBox<>(new String[] { Genre.Bachata, Genre.Merengue, Genre.Reggaeton, Genre.Technohouse });
		cbbTipo.setBounds(430, 20, 100, 20);

		lblDJs = new JLabel("DJs: "); // ETIQUETA DEL COMBOBOX
		lblDJs.setBounds(20, 80, 80, 20); // UBICACION
		lstDJs = new JList<>();
		JScrollPane sp1 = new JScrollPane(lstDJs); // TAMANO CON SLIDER
		sp1.setBounds(20, 100, 120, 200);

		lblParrillas = new JLabel("Parrillas: "); // ETIQUETA DEL COMBO BOX
		lblParrillas.setBounds(160, 80, 120, 20); // UBICACION
		lstParrillas = new JList<>();
		JScrollPane sp2 = new JScrollPane(lstParrillas); // TAMANO CON SLIDER
		sp2.setBounds(160, 100, 120, 200);

		lblCanciones = new JLabel("Canciones: "); // ETIQUETA DEL COMBO BOX
		lblCanciones.setBounds(300, 80, 120, 20); // UBICACION
		lblTotal = new JLabel("00:00:00");
		lblTotal.setBounds(380, 80, 50, 20); // DURACION DE LA PARRILLA
		lstCanciones = new JList<String>();
		JScrollPane sp3 = new JScrollPane(lstCanciones); // TAMANO CON SLIDER
		sp3.setBounds(300, 100, 250, 200);

		radDJ = new JRadioButton("DJs");
		radDJ.setBackground(Color.WHITE);
		radDJ.setBounds(300, 340, 80, 20);
		radPa = new JRadioButton("Parrillas");
		radPa.setBackground(Color.WHITE);
		radPa.setBounds(300, 360, 90, 20);
		radCa = new JRadioButton("Canciones", true);
		radCa.setBackground(Color.WHITE);
		radCa.setBounds(300, 380, 100, 20);
		ButtonGroup grupo = new ButtonGroup(); // SOLO PERMITE 1 RADIO B SELECCIONADO

		grupo.add(radDJ);
		grupo.add(radPa);
		grupo.add(radCa);

		btnSubir = new JButton("Subir");
		btnSubir.setBounds(420, 320, 130, 20);
		btnSubir.setActionCommand(Subir);

		btnBajar = new JButton("Bajar");
		btnBajar.setBounds(420, 340, 130, 20);
		btnBajar.setActionCommand(Bajar);

		btnCrear = new JButton("Crear o Cargar");
		btnCrear.setBounds(420, 360, 130, 20);
		btnCrear.setActionCommand(Crear);

		btnEditar = new JButton("Editar & Guardar");
		btnEditar.setBounds(420, 380, 130, 20);
		btnEditar.setActionCommand(Editar);

		btnBorrar = new JButton("Remover");
		btnBorrar.setBounds(420, 400, 130, 20);
		btnBorrar.setActionCommand(Borrar);

		lblArtista = new JLabel("Artista: ");
		lblArtista.setBounds(20, 320, 120, 20); // UBICACION

		lblTitulo = new JLabel("Titulo: ");
		lblTitulo.setBounds(20, 340, 120, 20); // UBICACION
		lblGenero = new JLabel("Genero: ");

		lblGenero.setBounds(20, 360, 120, 20); // UBICACION

		lblDuracion = new JLabel("Duración: ");
		lblDuracion.setBounds(20, 380, 120, 20); // UBICACION

		lblRuta = new JLabel("Ruta MP3: ");
		lblRuta.setBounds(20, 400, 120, 20); // UBICACION

		lblLetra = new JLabel("Ruta Letra: ");
		lblLetra.setBounds(20, 420, 120, 20); // UBICACION

		lblLirica = new JLabel("Letra: ");
		lblLirica.setBounds(20, 440, 120, 20); // UBICACION

		btnPlay = new JButton(new ImageIcon("./Data/Imagenes/play.png"));
		btnPlay.setBounds(15, 480, 55, 55);
		btnPlay.setActionCommand(Play);

		btnSiguiente = new JButton(new ImageIcon("./Data/Imagenes/Next.png"));
		btnSiguiente.setBounds(15, 540, 55, 55);
		btnSiguiente.setActionCommand(Next);

		btnStop = new JButton(new ImageIcon("./Data/Imagenes/Stop.png"));
		btnStop.setBounds(15, 600, 55, 55);
		btnStop.setActionCommand(Stop);

		txtArtista = new JTextField();
		txtArtista.setBounds(100, 320, 200, 20);
		txtTitulo = new JTextField();
		txtTitulo.setBounds(100, 340, 200, 20);
		cbbGeneros = new JComboBox<>(
				new String[] { Genre.Bachata, Genre.Merengue, Genre.Reggaeton, Genre.Technohouse, });
		cbbGeneros.setBounds(100, 360, 200, 20);
		txtDuracion = new JTextField();
		txtDuracion.setBounds(100, 380, 200, 20);
		txtRuta = new JTextField();
		txtRuta.setBounds(100, 400, 200, 20);
		txtLetra = new JTextField();
		txtLetra.setBounds(100, 420, 200, 20);

		txtLirica = new JTextArea();
		txtLirica.setEditable(false); // SOLO LECTURA
		JScrollPane sp4 = new JScrollPane(txtLirica); // TAMANO CON SLIDER
		sp4.setBounds(100, 490, 680, 150); // PANEL PARA LAS LETRAS DE LAS CANCIONES

		logo = new JLabel(new ImageIcon("./Data/Imagenes/log.png"));
		logo.setBounds(550, -10, 290, 100);

		modoAM = new JLabel(new ImageIcon("./Data/Imagenes/AM.png"));
		modoAM.setBounds(650, 350, 90, 90);
		modoAM.setVisible(false);
		add(modoAM);

		modoFM = new JLabel(new ImageIcon("./Data/Imagenes/FM.png"));
		modoFM.setBounds(650, 350, 90, 90);
		modoFM.setVisible(false);
		add(modoFM);

		modoStre = new JLabel(new ImageIcon("./Data/Imagenes/Streaming.png"));
		modoStre.setBounds(650, 350, 90, 90);
		modoStre.setVisible(false);
		add(modoStre);

		modoAll = new JLabel(new ImageIcon("./Data/Imagenes/All.png"));
		modoAll.setBounds(600, 350, 190, 90);
		modoAll.setVisible(false);
		add(modoAll);

		imagenCan = new JLabel(new ImageIcon());
		imagenCan.setBounds(560, 100, 250, 200);
		imagenCan.setVisible(true);
		add(imagenCan);

		add(logo);

		add(lblModo);
		add(cbbModo);
		add(lblTipo);
		add(cbbTipo);

		add(lblDJs); // AGREGA LOS ELEMENTOS AL PANEL
		add(sp1);
		add(lblParrillas);
		add(sp2);
		add(lblCanciones);
		add(sp3);
		add(lblTotal);

		add(radDJ);
		add(radPa);
		add(radCa);
		add(btnSubir);
		add(btnBajar);
		add(btnCrear);
		add(btnEditar);
		add(btnBorrar);

		add(lblArtista);
		add(lblTitulo);
		add(lblGenero);
		add(lblDuracion);
		add(lblRuta);
		add(lblLetra);
		add(lblLirica);
		add(btnPlay);
		add(btnSiguiente);
		add(btnStop);

		add(txtArtista);
		add(txtTitulo);
		add(cbbGeneros);
		add(txtDuracion);
		add(txtRuta);
		add(txtLetra);
		add(sp4);
	}

	/**
	 * Obtiene el atributo LstDJS de la clase PanelReproducción
	 * 
	 * @return JList<String> LstDJS
	 */

	public JList<String> getLstDJs() {
		return lstDJs;
	}

	/**
	 * Obtiene el atributo logo de la clase PanelReproducción
	 * 
	 * @return Jlabel logo
	 */

	public JLabel getLogo() {
		return logo;
	}

	/**
	 * Modifica el atributo logo de la clase PanelReproducción
	 * 
	 * @param jLabel logo
	 */

	public void setLogo(JLabel logo) {
		this.logo = logo;
	}

	/**
	 * Obtiene el Jlabel cancion de la clase PanelReproduccion
	 * 
	 * @return Jlabel canción
	 */

	public JLabel getCancion() {
		return cancion;
	}

	/**
	 * Modifica el Jlabel cancion de la clase PanelReproduccion
	 * 
	 * @param Jlabel cancion
	 */

	public void setCancion(JLabel cancion) {
		this.cancion = cancion;
	}

	/**
	 * Modifica el atributo LstDJS de la clase PanelReproducción
	 * 
	 * @param JList<String> lstDJs
	 */

	public void setLstDJs(JList<String> lstDJs) {
		this.lstDJs = lstDJs;
	}

	/**
	 * Obtiene el atributo lstParrillas de la clas PanelReproduccion
	 * 
	 * @return JList<String> lstParrillas
	 */

	public JList<String> getLstParrillas() {
		return lstParrillas;
	}

	/**
	 * Modifica el atributo lstParrillas de la clase PanelReproduccion
	 * 
	 * @param JList<String> lstParrillas
	 */

	public void setLstParrillas(JList<String> lstParrillas) {
		this.lstParrillas = lstParrillas;
	}

	/**
	 * Obtiene el atriburo lstCanciones de la clase PanelReproduccion
	 * 
	 * @return Jlist<String> canciones
	 */

	public JList<String> getLstCanciones() {
		return lstCanciones;
	}

	/**
	 * Modifica el atributo lstCanciones de la clase PanelReproduccion
	 * 
	 * @param lstCanciones
	 */

	public void setLstCanciones(JList<String> lstCanciones) {
		this.lstCanciones = lstCanciones;
	}

	/**
	 * Obtiene el atributo cbbGeneros de la clase PanelReproducción
	 * 
	 * @return JComboBox cbbGeneros
	 */

	public JComboBox<String> getCbbGeneros() {
		return cbbGeneros;
	}

	/**
	 * Modifica el atributo cbbGeneros de la clase PanelReproduccion
	 * 
	 * @param JCombobox cbbGeneros
	 */

	public void setCbbGeneros(JComboBox<String> cbbGeneros) {
		this.cbbGeneros = cbbGeneros;
	}

	/**
	 * Obtieme el atributo CbbModod de la clase PanelReproduccion
	 * 
	 * @return
	 */

	public JComboBox<String> getCbbModo() {
		return cbbModo;
	}

	/**
	 * Modifica el atributo cbbModo de la calse PanelReproduccion
	 * 
	 * @param JComboBox cbbModo
	 */

	public void setCbbModo(JComboBox<String> cbbModo) {
		this.cbbModo = cbbModo;
	}

	/**
	 * Obtiene el atributo cbbTipo de la clase PanelReproduccion
	 * 
	 * @return JCombobox cbbTipo
	 */

	public JComboBox<String> getCbbTipo() {
		return cbbTipo;
	}

	/**
	 * Modifica el cbbTipo de la clase PanelReproduccion
	 * 
	 * @param JComboBox cbbTipo
	 * 
	 */

	public void setCbbTipo(JComboBox<String> cbbTipo) {
		this.cbbTipo = cbbTipo;
	}

	/**
	 * Obtiene el atributo lblModo de la clase PanelReproduccion
	 * 
	 * @return Jlabel lblModo
	 */

	public JLabel getLblModo() {
		return lblModo;
	}

	/**
	 * Modifica el atribtuo lblModo de la clase PanelReproduccion
	 * 
	 * @param Jlabel lblModo
	 */

	public void setLblModo(JLabel lblModo) {
		this.lblModo = lblModo;
	}

	/**
	 * Obtiene el atributo lblTipo de la clase PanelReproduccion
	 * 
	 * @return Jlabel lblTipo
	 */

	public JLabel getLblTipo() {
		return lblTipo;
	}

	/**
	 * Modifica el atributo lblTipo de la clase PanelReproduccion
	 * 
	 * @param Jlabel lblTipo
	 */
	public void setLblTipo(JLabel lblTipo) {
		this.lblTipo = lblTipo;
	}

	/**
	 * Obtiene el atributo lbldjs de la clase PanelReproduccion
	 * 
	 * @return Jlabel lblDjs
	 */

	public JLabel getLblDJs() {
		return lblDJs;
	}

	/**
	 * Modifica el atributo lblDjs de la clase PanelReproduccion
	 * 
	 * @param Jlabel lblDJs
	 */

	public void setLblDJs(JLabel lblDJs) {
		this.lblDJs = lblDJs;
	}

	/**
	 * Obtiene el atributo lblParrillas de la clase PanelReproduccion
	 * 
	 * @return Jlabel lblParrilas
	 */
	public JLabel getLblParrillas() {
		return lblParrillas;
	}

	/**
	 * Modifica el atributo lblParrillas de la clase PanelReproduccion
	 * 
	 * @param Jlabel lblParrillas
	 */

	public void setLblParrillas(JLabel lblParrillas) {
		this.lblParrillas = lblParrillas;
	}

	/**
	 * Obtiene el atributo lblCanciones de la calse PanelReproduccion
	 * 
	 * @return Jlabel lblCanciones
	 */

	public JLabel getLblCanciones() {
		return lblCanciones;
	}

	/**
	 * Modifica el atributo lblCanciones de la clase panelReproduccion
	 * 
	 * @param Jlabel lblCanciones
	 */

	public void setLblCanciones(JLabel lblCanciones) {
		this.lblCanciones = lblCanciones;
	}

	/**
	 * Obtiene el atributo lbltotal de la clase panelReproduccion
	 * 
	 * @return jLabel lblTotla
	 */

	public JLabel getLblTotal() {
		return lblTotal;
	}

	/**
	 * Modifica el atributo lblTotal de la clase panelReproduccion
	 * 
	 * @param lblTotal
	 */

	public void setLblTotal(JLabel lblTotal) {
		this.lblTotal = lblTotal;
	}

	/**
	 * Obtiene el atributo lblArtista de la clase PanelReproduccion
	 * 
	 * @return Jlabel lblArtista
	 */

	public JLabel getLblArtista() {
		return lblArtista;
	}

	/**
	 * Modifica el atributo lblArtista de la clase panelReproduccion
	 * 
	 * @param lblArtista
	 */

	public void setLblArtista(JLabel lblArtista) {
		this.lblArtista = lblArtista;
	}

	/**
	 * Obtiene el atributo lblTitulo de la clase paneReproduccion
	 * 
	 * @return Jlabel lblTitulo
	 */

	public JLabel getLblTitulo() {
		return lblTitulo;
	}

	/**
	 * modifica el atributo lblTitulo de la clase PanelReproduccion
	 *
	 * @param Jlabel lblTitulo
	 */

	public void setLblTitulo(JLabel lblTitulo) {
		this.lblTitulo = lblTitulo;
	}

	/**
	 * Obtiene el atributo lblGenero de la clase PanelReproducción
	 * 
	 * @return
	 */

	public JLabel getLblGenero() {
		return lblGenero;
	}

	/**
	 * Modifica el atributo de la clase PanelReproducción
	 * 
	 * @param lblGenero
	 */

	public void setLblGenero(JLabel lblGenero) {
		this.lblGenero = lblGenero;
	}

	/**
	 * Obtiene el atibuto de la clase PanelReproducción
	 * 
	 * @return
	 */

	public JLabel getLblDuracion() {
		return lblDuracion;
	}

	/**
	 * Modifica el atributo de la clase PanelReproducción
	 * 
	 * @param lblDuracion
	 */

	public void setLblDuracion(JLabel lblDuracion) {
		this.lblDuracion = lblDuracion;
	}

	/**
	 * Obtiene le atributo de la clase PanelReproducción
	 * 
	 * @return JLabel jlbruta
	 */

	public JLabel getLblRuta() {
		return lblRuta;
	}

	/**
	 * Modifica el atributo de la clase PanelReproducción
	 * 
	 * @param lblRuta Jlabel jlbRuta
	 */

	public void setLblRuta(JLabel lblRuta) {
		this.lblRuta = lblRuta;
	}

	/**
	 * Obtiene el atributo lblLetra de la clase PanelReproducción
	 * 
	 * @return JLabel lblLetra
	 */

	public JLabel getLblLetra() {
		return lblLetra;
	}

	/**
	 * Modifica el atributo lblLertra de la clase PanelReproducción
	 * 
	 * @param JLabel lblLetra
	 */

	public void setLblLetra(JLabel lblLetra) {
		this.lblLetra = lblLetra;
	}

	/**
	 * Obtiene el atributo lblLirica de la clase PanelReproducción
	 * 
	 * @return JLabel lblLirica
	 */

	public JLabel getLblLirica() {
		return lblLirica;
	}

	/**
	 * Modifica el atributo lblLirica de la clase PanelReproducción
	 * 
	 * @param jlabel lblLirica
	 */

	public void setLblLirica(JLabel lblLirica) {
		this.lblLirica = lblLirica;
	}

	/**
	 * Obtiene el atributo txtArtista de la calse PanelReproducción
	 * 
	 * @return JTextField txtArtista
	 */

	public JTextField getTxtArtista() {
		return txtArtista;
	}

	/**
	 * Modifica el atributo txtArtista de la clase PanelReproducción
	 * 
	 * @param JTextField txtArtista
	 */

	public void setTxtArtista(JTextField txtArtista) {
		this.txtArtista = txtArtista;
	}

	/**
	 * Obtiene el atributo txtTitulo de la clase PanelReproducción
	 * 
	 * @return JTextField txtTitlo
	 */

	public JTextField getTxtTitulo() {
		return txtTitulo;
	}

	/**
	 * Modifica el atributo txtTitulo de la clase PanelReproducción
	 * 
	 * @param JtextField txtTitulo
	 */

	public void setTxtTitulo(JTextField txtTitulo) {
		this.txtTitulo = txtTitulo;
	}

	/**
	 * Obtiene el atributo txtDuracion de la clase PanelReproducción
	 * 
	 * @return JTextField txtDuracion
	 */

	public JTextField getTxtDuracion() {
		return txtDuracion;
	}

	/**
	 * Modifica el atributo txtDuracion de la clase PanelReproducción
	 * 
	 * @param jTextField txtDuracion
	 */

	public void setTxtDuracion(JTextField txtDuracion) {
		this.txtDuracion = txtDuracion;
	}

	/**
	 * Obtiene el atributo txtRuta de la clase PanelReproducción
	 * 
	 * @return JTextField txtRuta
	 */

	public JTextField getTxtRuta() {
		return txtRuta;
	}

	/**
	 * Modifica el atributo txtruta de la clase PanelReproducción
	 * 
	 * @param jTextfield txtRuta
	 */

	public void setTxtRuta(JTextField txtRuta) {
		this.txtRuta = txtRuta;
	}

	/**
	 * Obtiene el atributo txtLetra de la clase PanelReproducción
	 * 
	 * @return JTextfield txtLetra
	 */

	public JTextField getTxtLetra() {
		return txtLetra;
	}

	/**
	 * Modifica el atributo txtLEtra de la clase PanelReproducción
	 * 
	 * @param JtextField txtLetra
	 */

	public void setTxtLetra(JTextField txtLetra) {
		this.txtLetra = txtLetra;
	}

	/**
	 * Obtiene el atributo txtLirica de la clase PanelReproducción
	 * 
	 * @return JTtextArea txtLirica
	 */

	public JTextArea getTxtLirica() {
		return txtLirica;
	}

	/**
	 * Modifica el atributo de la clase PanelReproducción
	 * 
	 * @param JTextArea txtLirica
	 */

	public void setTxtLirica(JTextArea txtLirica) {
		this.txtLirica = txtLirica;
	}

	/**
	 * Obtiene el atributo raDJ de la clase PanelReproducción
	 * 
	 * @return JRadioButton raDJ
	 */

	public JRadioButton getRadDJ() {
		return radDJ;
	}

	/**
	 * Modifica el atributo raDj de la clase PanelReproducción
	 * 
	 * @param JRadioButton radDJ
	 */

	public void setRadDJ(JRadioButton radDJ) {
		this.radDJ = radDJ;
	}

	/**
	 * Obtiene el atributo raPa de la clase PanelReproducción
	 * 
	 * @return JradioButton radPa
	 */

	public JRadioButton getRadPa() {
		return radPa;
	}

	/**
	 * Modifica el atributo raPa de la clase PanelReproducción
	 * 
	 * @param JRadioButton radPa
	 */

	public void setRadPa(JRadioButton radPa) {
		this.radPa = radPa;
	}

	/**
	 * Obteniene el atributo raCa de la clase PanelReproducción
	 * 
	 * @return JRadioButton raCa
	 */

	public JRadioButton getRadCa() {
		return radCa;

	}

	/**
	 * 
	 * Modifica el atributo raCa de la clase PanelReproducción
	 * 
	 * @param JRadioButton radCa
	 */

	public void setRadCa(JRadioButton radCa) {
		this.radCa = radCa;
	}

	/**
	 * Obtiene el atributo btnSubir de la clase PanelReproducción
	 * 
	 * @return JButton btnSubir
	 */

	public JButton getBtnSubir() {
		return btnSubir;
	}

	/**
	 * Modifica el atributo btnSubir de la clase PanelReproducción
	 * 
	 * @param JButton btnSubir
	 */

	public void setBtnSubir(JButton btnSubir) {
		this.btnSubir = btnSubir;
	}

	/**
	 * Obtiene el atributo btnBajar de la clase PanelReproducción
	 * 
	 * @return JButton btnBajar
	 */

	public JButton getBtnBajar() {
		return btnBajar;
	}

	/**
	 * Modifica el atributo btnBajar de la clase PanelReproducción
	 * 
	 * @param Jbutton btnBajar
	 */

	public void setBtnBajar(JButton btnBajar) {
		this.btnBajar = btnBajar;
	}

	/**
	 * Obtiene el atributo btnCrear de la clase PanelReproducción
	 * 
	 * @return JButton btnCrar
	 */

	public JButton getBtnCrear() {
		return btnCrear;
	}

	/**
	 * Modifica el atributo btnCrear de la clase PanelReproducción
	 * 
	 * @param Jbutton btnCrear
	 */

	public void setBtnCrear(JButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	/**
	 * Obtiene el atributo btnEditar de la clase PanelReproducción
	 * 
	 * @return JButton btnEditar
	 */

	public JButton getBtnEditar() {
		return btnEditar;
	}

	/**
	 * Modifica el atributo btnEditar de la clase PanelReproducción
	 * 
	 * @param Jbutton btnEditar
	 */

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	/**
	 * Obtiene el atributo btnBorrar de la clase PanelReproducción
	 * 
	 * @return JButton btnEditar
	 */

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	/**
	 * Modifica el atributo btnBorrar de la clase PanelReproducción
	 * 
	 * @param Jbutton btnBorrar
	 */

	public void setBtnBorrar(JButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	/**
	 * Obtiene el atributo btnPlay de la clase PanelReproducción
	 * 
	 * @return JButton btnPlay
	 */

	public JButton getBtnPlay() {
		return btnPlay;
	}

	/**
	 * Modifica el atributo btnPlay de la clase PanelReproducción
	 * 
	 * @param Jbutton btPlay
	 */

	public void setBtnPlay(JButton btnPlay) {
		this.btnPlay = btnPlay;
	}

	/**
	 * Obtiene el atributo btnSiguiente de la clase PanelReproducción
	 * 
	 * @return JButton btnSiguiente
	 */

	public JButton getBtnSiguiente() {
		return btnSiguiente;
	}

	/**
	 * Modifica el atributo btnSiguiente de la clase PanelReproducción
	 * 
	 * @param Jbutton btnSiguiente
	 */

	public void setBtnSiguiente(JButton btnSiguiente) {
		this.btnSiguiente = btnSiguiente;
	}

	/**
	 * Obtiene el atributo btnStop de la clase PanelReproducción
	 * 
	 * @return JButton btnStop
	 */

	public JButton getBtnStop() {
		return btnStop;
	}

	/**
	 * Modifica el atributo btnStop de la clase PanelReproducción
	 * 
	 * @param Jbutton btnStop
	 */

	public void setBtnStop(JButton btnStop) {
		this.btnStop = btnStop;
	}

	/**
	 * Obtiene el atributo modoAm de la clase PanelReproducción
	 * 
	 * @return JLabel modoAm
	 */

	public JLabel getModoAM() {
		return modoAM;
	}

	/**
	 * Modifica el atributo modoAM de la clase PanelReproducción
	 * 
	 * @param JLabel modoaAM
	 */

	public void setModoAM(JLabel modoAM) {
		this.modoAM = modoAM;
	}

	/**
	 * Obtiene el atributo modoFm de la clase PanelReproducción
	 * 
	 * @return JLabel modoFm
	 */

	public JLabel getModoFM() {
		return modoFM;
	}

	/**
	 * Modifica el atributo modoFM de la clase PanelReproducción
	 * 
	 * @param JLabel modoaFM
	 */

	public void setModoFM(JLabel modoFM) {
		this.modoFM = modoFM;
	}

	/**
	 * Obtiene el atributo modoStre de la clase PanelReproducción
	 * 
	 * @return JLabel modoStre
	 */

	public JLabel getModoStre() {
		return modoStre;
	}

	/**
	 * Modifica el atributo modoStre de la clase PanelReproducción
	 * 
	 * @param JLabel modoaStre
	 */

	public void setModoStre(JLabel modoStre) {
		this.modoStre = modoStre;
	}

	/**
	 * Obtiene el atributo modoAll de la clase PanelReproducción
	 * 
	 * @return JLabel modoAll
	 */

	public JLabel getModoAll() {
		return modoAll;
	}

	/**
	 * Modifica el atributo modoAll de la clase PanelReproducción
	 * 
	 * @param JLabel modoAll
	 */

	public void setModoAll(JLabel modoAll) {
		this.modoAll = modoAll;
	}

	/**
	 * Obtiene el atributo imagenCan de la clase PanelReproducción
	 * 
	 * @return JLabel imageCan
	 */

	public JLabel getImagenCan() {
		return imagenCan;
	}

	/**
	 * Modifica el atributo modoAll de la clase PanelReproducción
	 * 
	 * @param JLabel modoAll
	 */

	public void setImagenCan(JLabel imagenCan) {
		this.imagenCan = imagenCan;
	}

}
