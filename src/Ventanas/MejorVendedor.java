package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clases.Empleado;
import Conexion.Conexion;
import Main.Main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class MejorVendedor extends JFrame {

	private JPanel contentPane;
	private JLabel lblNombre;
	private JTextField txtNombre;

	public MejorVendedor() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Main ventana = new Main();
				ventana.setVisible(true);
				dispose();
			}
		});
		setResizable(false);
		setTitle("Mejor vendedor - Veterinaria CAC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 384, 103);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNombre = new JLabel("El mejor vendedor es:");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(10, 11, 357, 14);
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			txtNombre.setText("Vendedor de prueba");
		}else{
			JOptionPane.showMessageDialog(null, "Error en la conexion de base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
		txtNombre.setBounds(10, 36, 357, 20);
		contentPane.add(txtNombre);
	}

	private Empleado obtenerElMejor() {
		
		Empleado este = new Empleado();
		
		este.setNombre("Juanito");
		este.setApellido("Prueba");
		este.setDomicilio("Prueba 1234");
		este.setId("0001");
		este.setTelefono("42Prueba19");
		return este;
	}

	private String obtenerVentas() {
		return "123012390";
	}

}
