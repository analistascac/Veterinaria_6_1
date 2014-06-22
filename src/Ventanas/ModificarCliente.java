package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import java.awt.GridLayout;

import javax.swing.JComboBox;

import java.awt.FlowLayout;

import Clases.Cliente;
import Main.Main;

import java.awt.CardLayout;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JRadioButton;

public class ModificarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtNumDoc;
	private JTextField txtDireccion;
	private JTextField txtOcupacion;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JButton btnModificar;
	private JComboBox cmbTipoDoc;
	private JComboBox cmdId;
	private DefaultComboBoxModel tipodoc = new DefaultComboBoxModel();
	private DefaultComboBoxModel id = new DefaultComboBoxModel();
	private JRadioButton rdbtnCuentaCorriente;
	private ButtonGroup radio = new ButtonGroup();
	private JRadioButton rdbtnAlContado;

	public ModificarCliente() {

		llenarcombos();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				int x = JOptionPane.showConfirmDialog(null,
						"¿Realmente desea cancelar la modificación",
						"Confirmar", JOptionPane.YES_NO_OPTION);
				if (x == JOptionPane.YES_OPTION) {
					Main ven = new Main();
					ven.setVisible(true);
					dispose();
				}
			}
		});
		setTitle("Modificar Cliente - Centro veterinario CAC");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 310, 308);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("Identificador:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(10, 11, 119, 15);
		contentPane.add(lblId);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 31, 119, 15);
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(10, 51, 119, 15);
		contentPane.add(lblApellido);

		JLabel lblTipoDocumento = new JLabel("Tipo Documento:");
		lblTipoDocumento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDocumento.setBounds(10, 71, 119, 15);
		contentPane.add(lblTipoDocumento);

		JLabel lblNumDoc = new JLabel("Documento:");
		lblNumDoc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumDoc.setBounds(10, 91, 119, 15);
		contentPane.add(lblNumDoc);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setBounds(10, 111, 119, 15);
		contentPane.add(lblDireccion);

		JLabel lblOcupacion = new JLabel("Ocupacion:");
		lblOcupacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOcupacion.setBounds(10, 131, 119, 15);
		contentPane.add(lblOcupacion);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(10, 151, 119, 15);
		contentPane.add(lblTelefono);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(10, 171, 119, 15);
		contentPane.add(lblEmail);

		JLabel lblTipoDePago = new JLabel("Tipo de pago:");
		lblTipoDePago.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDePago.setBounds(10, 191, 119, 15);
		contentPane.add(lblTipoDePago);

		cmdId = new JComboBox();
		cmdId.setModel(id);
		cmdId.setBounds(135, 11, 150, 17);
		contentPane.add(cmdId);
		cmdId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNombre.setEnabled(true);
				txtApellido.setEnabled(true);
				txtNumDoc.setEnabled(true);
				txtDireccion.setEnabled(true);
				txtOcupacion.setEnabled(true);
				txtTelefono.setEnabled(true);
				txtEmail.setEnabled(true);
				cmbTipoDoc.setEnabled(true);
				rdbtnCuentaCorriente.setEnabled(true);
				rdbtnAlContado.setEnabled(true);
			}
		});

		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setBounds(135, 31, 150, 17);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setEnabled(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(135, 51, 150, 17);
		contentPane.add(txtApellido);

		txtNumDoc = new JTextField();
		txtNumDoc.setEnabled(false);
		txtNumDoc.setColumns(10);
		txtNumDoc.setBounds(135, 91, 150, 17);
		contentPane.add(txtNumDoc);

		txtDireccion = new JTextField();
		txtDireccion.setEnabled(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(135, 111, 150, 17);
		contentPane.add(txtDireccion);

		txtOcupacion = new JTextField();
		txtOcupacion.setEnabled(false);
		txtOcupacion.setColumns(10);
		txtOcupacion.setBounds(135, 131, 150, 17);
		contentPane.add(txtOcupacion);

		txtTelefono = new JTextField();
		txtTelefono.setEnabled(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(135, 151, 150, 17);
		contentPane.add(txtTelefono);

		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(135, 171, 150, 17);
		contentPane.add(txtEmail);

		cmbTipoDoc = new JComboBox();
		cmbTipoDoc.setEnabled(false);
		cmbTipoDoc.setModel(tipodoc);
		cmbTipoDoc.setBounds(135, 71, 150, 17);
		contentPane.add(cmbTipoDoc);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!txtNombre.getText().trim().isEmpty()
						&& !txtApellido.getText().trim().isEmpty()
						&& !txtNumDoc.getText().trim().isEmpty()
						&& !txtDireccion.getText().trim().isEmpty()
						&& !txtOcupacion.getText().trim().isEmpty()
						&& !txtTelefono.getText().trim().isEmpty()
						&& !txtEmail.getText().trim().isEmpty()
						&& (rdbtnAlContado.isSelected() || rdbtnCuentaCorriente
								.isSelected())) {
					Cliente clie = new Cliente();
					clie.setId((String) cmdId.getSelectedItem());
					clie.setNombre(txtNombre.getText());
					clie.setApellido(txtApellido.getText());
					clie.setTipoDocumento((String) cmbTipoDoc.getSelectedItem());
					clie.setDocumento(txtNumDoc.getText());
					clie.setDireccion(txtDireccion.getText());
					clie.setOcupacion(txtOcupacion.getText());
					clie.setTelefono(txtTelefono.getText());
					clie.setEmail(txtEmail.getText());

					if (rdbtnCuentaCorriente.isSelected()) {
						clie.setTipoPago("Cuenta corriente");
					} else {

						clie.setTipoPago("Al contado");
					}

					JOptionPane.showMessageDialog(null, clie.toString());
				} else {
					JOptionPane.showMessageDialog(null,
							"Algún campo está incompleto", "Error",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnModificar.setBounds(196, 245, 89, 23);
		contentPane.add(btnModificar);

		rdbtnCuentaCorriente = new JRadioButton("Cuenta Corriente");
		rdbtnCuentaCorriente.setEnabled(false);
		radio.add(rdbtnCuentaCorriente);
		rdbtnCuentaCorriente.setBounds(135, 191, 150, 17);
		contentPane.add(rdbtnCuentaCorriente);

		rdbtnAlContado = new JRadioButton("Al contado");
		rdbtnAlContado.setEnabled(false);
		radio.add(rdbtnAlContado);
		rdbtnAlContado.setBounds(135, 211, 150, 17);
		contentPane.add(rdbtnAlContado);

	}

	public void llenarcombos() {
		id.addElement("Prueba");
		id.addElement("Prueba2");
		id.addElement("Prueba3");

		tipodoc.addElement("DNI");
		tipodoc.addElement("Carta d'Identità");
		tipodoc.addElement("Cartão de Cidadão");
		tipodoc.addElement("Carte d'identité");
		tipodoc.addElement("Carte nationale d'identité");
		tipodoc.addElement("CC");
		tipodoc.addElement("Cedula de identidad civil");
		tipodoc.addElement("Cedula de identidad personal");
		tipodoc.addElement("CI");
		tipodoc.addElement("CIE");
		tipodoc.addElement("CURP");
		tipodoc.addElement("Dowód osobisty");
		tipodoc.addElement("DPI");
		tipodoc.addElement("DUI");
		tipodoc.addElement("Personalausweis");
		tipodoc.addElement("RG");
		tipodoc.addElement("Tarjeta de identidad");
	}
}
