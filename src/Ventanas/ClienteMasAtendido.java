package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import Clases.Cliente;

public class ClienteMasAtendido extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtCantAtenciones;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtDireccion;

	public ClienteMasAtendido() {
		setResizable(false);
		setTitle("Cliente mas atendido - Veterinaria CAC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 401, 190);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIdentificador = new JLabel("Identificador:");
		lblIdentificador.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdentificador.setBounds(10, 11, 150, 14);
		contentPane.add(lblIdentificador);

		JLabel lblCantidadDeAtenciones = new JLabel("Cantidad de atenciones:");
		lblCantidadDeAtenciones.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidadDeAtenciones.setBounds(10, 36, 150, 14);
		contentPane.add(lblCantidadDeAtenciones);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 61, 150, 14);
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(10, 86, 150, 14);
		contentPane.add(lblApellido);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(10, 111, 150, 14);
		contentPane.add(lblTelefono);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setBounds(10, 136, 150, 14);
		contentPane.add(lblDireccion);

		Cliente c = new Cliente();

		obtenerCliente(c);

		txtId = new JTextField();
		txtId.setText(c.getId());
		txtId.setEditable(false);
		txtId.setBounds(170, 8, 208, 17);
		contentPane.add(txtId);
		txtId.setColumns(10);

		txtCantAtenciones = new JTextField();
		txtCantAtenciones.setEditable(false);
		txtCantAtenciones.setColumns(10);
		txtCantAtenciones.setText(obtenerAtenciones(c.getId()) + "");
		txtCantAtenciones.setBounds(170, 33, 208, 17);
		contentPane.add(txtCantAtenciones);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setText(c.getNombre());
		txtNombre.setColumns(10);
		txtNombre.setBounds(170, 58, 208, 17);
		contentPane.add(txtNombre);

		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setText(c.getApellido());
		txtApellido.setColumns(10);
		txtApellido.setBounds(170, 83, 208, 17);
		contentPane.add(txtApellido);

		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setText(c.getTelefono());
		txtTelefono.setBounds(170, 108, 208, 17);
		contentPane.add(txtTelefono);

		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setText(c.getDireccion());
		txtDireccion.setBounds(170, 133, 208, 17);
		contentPane.add(txtDireccion);
	}

	private void obtenerCliente(Cliente este) {
		este.setId("Prueba");
		este.setApellido("Prueba");
		este.setNombre("Prueba");
		este.setDocumento("21371723");
		este.setDireccion("Direccion prueba");
		este.setEmail("Prueba@prueba.com");
		este.setOcupacion("Probador");
		este.setTelefono("123123123");
		este.setTipoDocumento("DNI");
		este.setTipoPago("Comun");
	}

	private int obtenerAtenciones(String id) {
		return 140;
	}
}
