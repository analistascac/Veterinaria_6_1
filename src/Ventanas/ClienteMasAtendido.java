package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import Clases.Cliente;
import Conexion.Conexion;
import Main.Main;

public class ClienteMasAtendido extends JFrame {

	private JPanel contentPane;
	private JLabel lblNombre;
	private JTextField txtNombre;

	public ClienteMasAtendido() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Main ventana = new Main();
				ventana.setVisible(true);
				dispose();
			}
		});
		setResizable(false);
		setTitle("Cliente frecuente - Veterinaria CAC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 384, 103);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNombre = new JLabel("El cliente mas frecuente es:");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(10, 11, 357, 14);
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			txtNombre.setText(cn.informeClienteFrecuente());
		}else{
			JOptionPane.showMessageDialog(null, "Error en la conexion de base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
		txtNombre.setBounds(10, 36, 357, 20);
		contentPane.add(txtNombre);
	}
}
