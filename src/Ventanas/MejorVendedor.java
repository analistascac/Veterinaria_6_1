package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clases.Empleado;
import Main.Main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class MejorVendedor extends JFrame {

	private JPanel contentPane;
	private JTextField txtCantVentas;
	private JTextField txtId;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblTelefono;
	private JLabel lblDireccion;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtDireccion;

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
		setBounds(100, 100, 384, 195);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIdentificador = new JLabel("Identificador:");
		lblIdentificador.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdentificador.setBounds(43, 11, 90, 14);
		contentPane.add(lblIdentificador);

		JLabel lblCantidadDeVentas = new JLabel("Cantidad de ventas:");
		lblCantidadDeVentas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidadDeVentas.setBounds(10, 36, 123, 14);
		contentPane.add(lblCantidadDeVentas);

		Empleado elMejor = new Empleado();
		elMejor = obtenerElMejor();

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setText(elMejor.getId());
		txtId.setColumns(10);
		txtId.setBounds(139, 8, 228, 20);
		contentPane.add(txtId);

		txtCantVentas = new JTextField();
		txtCantVentas.setEditable(false);
		txtCantVentas.setText(obtenerVentas());
		txtCantVentas.setBounds(139, 36, 228, 20);
		contentPane.add(txtCantVentas);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(43, 61, 90, 14);
		contentPane.add(lblNombre);

		lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(43, 86, 90, 14);
		contentPane.add(lblApellido);

		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(43, 111, 90, 14);
		contentPane.add(lblTelefono);

		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setBounds(43, 136, 90, 14);
		contentPane.add(lblDireccion);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(139, 61, 228, 20);
		txtNombre.setText(elMejor.getNombre());
		contentPane.add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setBounds(139, 86, 228, 20);
		txtApellido.setText(elMejor.getApellido());
		contentPane.add(txtApellido);

		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setText(elMejor.getTelefono());
		txtTelefono.setBounds(139, 111, 228, 20);
		contentPane.add(txtTelefono);

		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setText(elMejor.getTelefono());
		txtDireccion.setBounds(139, 136, 228, 20);
		contentPane.add(txtDireccion);
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
