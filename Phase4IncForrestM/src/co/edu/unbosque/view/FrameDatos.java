package co.edu.unbosque.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FrameDatos extends JFrame {

	private JLabel l1, l2;
	private JTextField txta, txttit;
	private JButton guardar;
	public static final String Save = "save";

	public FrameDatos() {

		setTitle("Editar Cancion");
		setSize(300,300);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		inicializarComponentes();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(false);
	}

	private void inicializarComponentes() {

		l1 = new JLabel("Ingrese nuevo nombre del artista de la canción: ");
		l1.setBounds(5, 5, 300, 25);
		add(l1);

		txta = new JTextField();
		txta.setBounds(5, 30, 260, 30);
		add(txta);

		l2 = new JLabel("ingrese nuevo nombre de la canción: ");
		l2.setBounds(5, 70, 300, 25);
		add(l2);

		txttit = new JTextField();
		txttit.setBounds(5, 95, 260, 30);
		add(txttit);
			

		guardar = new JButton(new ImageIcon("./Data/Imagenes/Save.png"));
		guardar.setBounds(120, 190, 60, 60);
		guardar.setActionCommand(Save);
		add(guardar);
	}

	public JLabel getL1() {
		return l1;
	}

	public void setL1(JLabel l1) {
		this.l1 = l1;
	}

	public JLabel getL2() {
		return l2;
	}

	public void setL2(JLabel l2) {
		this.l2 = l2;
	}

	public JTextField getTxta() {
		return txta;
	}

	public void setTxta(JTextField txta) {
		this.txta = txta;
	}

	public JTextField getTxttit() {
		return txttit;
	}

	public void setTxttit(JTextField txttit) {
		this.txttit = txttit;
	}

	public JButton getGuardar() {
		return guardar;
	}

	public void setGuardar(JButton guardar) {
		this.guardar = guardar;
	}

	public static String getSave() {
		return Save;
	}
	
}
