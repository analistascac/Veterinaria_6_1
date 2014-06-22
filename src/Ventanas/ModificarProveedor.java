package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import Clases.Proveedor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificarProveedor extends JFrame {
	private JPanel panelPrincipal;
	private JTextField txtRazonSOcial;
	private JTextField txtCuit;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtFax;
	private JTextField txtEmail;
	private JButton btnModificar;
	private JComboBox cmbId;
	private DefaultComboBoxModel<String> id = new DefaultComboBoxModel();

	public ModificarProveedor() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Modificar proveedor - Veterinaria CAC");
		setBounds(100, 100, 315, 260);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		JLabel lblNewLabel = new JLabel("Identificador:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 11, 115, 14);
		panelPrincipal.add(lblNewLabel);

		JLabel lblRazonSocial = new JLabel("Razon social:");
		lblRazonSocial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRazonSocial.setBounds(10, 36, 115, 14);
		panelPrincipal.add(lblRazonSocial);

		JLabel lblCuit = new JLabel("Cuit:");
		lblCuit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCuit.setBounds(10, 61, 115, 14);
		panelPrincipal.add(lblCuit);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setBounds(10, 86, 115, 14);
		panelPrincipal.add(lblDireccion);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(10, 111, 115, 14);
		panelPrincipal.add(lblTelefono);

		JLabel lblFax = new JLabel("Fax:");
		lblFax.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFax.setBounds(10, 136, 115, 14);
		panelPrincipal.add(lblFax);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(10, 161, 115, 14);
		panelPrincipal.add(lblEmail);

		cargarId();

		cmbId = new JComboBox();
		cmbId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtRazonSOcial.setEnabled(true);
				txtCuit.setEnabled(true);
				txtDireccion.setEnabled(true);
				txtTelefono.setEnabled(true);
				txtFax.setEnabled(true);
				txtEmail.setEnabled(true);
				btnModificar.setEnabled(true);
				cargarDatos();
			}
		});
		cmbId.setBounds(135, 8, 150, 17);
		cmbId.setModel(id);
		panelPrincipal.add(cmbId);

		txtRazonSOcial = new JTextField();
		txtRazonSOcial.setEnabled(false);
		txtRazonSOcial.setText("");
		txtRazonSOcial.setBounds(135, 33, 150, 20);
		panelPrincipal.add(txtRazonSOcial);
		txtRazonSOcial.setColumns(10);

		txtCuit = new JTextField();
		txtCuit.setEnabled(false);
		txtCuit.setBounds(135, 58, 150, 20);
		panelPrincipal.add(txtCuit);
		txtCuit.setColumns(10);

		txtDireccion = new JTextField();
		txtDireccion.setEnabled(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(135, 83, 150, 20);
		panelPrincipal.add(txtDireccion);

		txtTelefono = new JTextField();
		txtTelefono.setEnabled(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(135, 108, 150, 20);
		panelPrincipal.add(txtTelefono);

		txtFax = new JTextField();
		txtFax.setEnabled(false);
		txtFax.setColumns(10);
		txtFax.setBounds(135, 133, 150, 20);
		panelPrincipal.add(txtFax);

		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(135, 158, 150, 20);
		panelPrincipal.add(txtEmail);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txtCuit.getText().trim().isEmpty()
						&& !txtRazonSOcial.getText().trim().isEmpty()
						&& !txtDireccion.getText().trim().isEmpty()
						&& !txtTelefono.getText().trim().isEmpty()
						&& !txtFax.getText().trim().isEmpty()
						&& !txtEmail.getText().trim().isEmpty()) {
					Proveedor prov = new Proveedor();
					prov.setCuit(txtCuit.getText());
					prov.setRazonSocial(txtRazonSOcial.getText());
					prov.setDireccion(txtDireccion.getText());
					prov.setTelefono(txtTelefono.getText());
					prov.setFax(txtFax.getText());
					prov.setEmail(txtEmail.getText());
					prov.setId(cmbId.getSelectedItem() + "");

					JOptionPane.showMessageDialog(null, prov.toString(),
							"Información", JOptionPane.INFORMATION_MESSAGE);
					// pasarle el objeto a la capa de datos

					JOptionPane.showMessageDialog(null,
							"Proveedor correctamente modificado.",
							"Información", JOptionPane.INFORMATION_MESSAGE);

				} else {
					JOptionPane.showMessageDialog(null,
							"Error, algún campo está vacio.", "Error",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnModificar.setEnabled(false);
		btnModificar.setBounds(196, 189, 89, 23);
		panelPrincipal.add(btnModificar);
	}

	private void cargarId() {
		id.addElement("Prueba proveedor 1");
		id.addElement("Prueba proveedor 2");
		id.addElement("Prueba proveedor 3");
	}

	public void cargarDatos() {
		txtRazonSOcial.setText("Razon social prueba");
		txtCuit.setText("Cuit prueba");
		txtDireccion.setText("Direccion prueba");
		txtTelefono.setText("Telefono prueba");
		txtFax.setText("Fax prueba");
		txtEmail.setText("Email prueba");
	}
}
