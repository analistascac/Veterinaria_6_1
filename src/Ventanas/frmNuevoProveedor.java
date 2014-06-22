// 
// Envia  a la otra capa  : Obejto Proveedor

package Ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import Clases.Auxiliar;
import Clases.Proveedor;
import Conexion.Conexion;
import Main.Main;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class frmNuevoProveedor extends JFrame {

	private static final long serialVersionUID = -5465919996142338826L;
	private JPanel contentPane;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JLabel lblDireccion;
	private JTextField txtFax;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JLabel lblTelefono;
	private JLabel lblFax;
	private JLabel lblCUIT;
	private JTextField txtCUIT;
	private JLabel lblNombre;
	private JTextField txtRazonSocial;
	private JTextField txtEmail;

	public frmNuevoProveedor() {
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent arg0) {
				Main v=new Main();
				v.setVisible(true);
				dispose();
			}
		});

		setTitle("Nuevo Proveedor - Veterinaria CAC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 419, 305);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDireccion = new JLabel("Direccion");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setBounds(55, 78, 72, 21);
		contentPane.add(lblDireccion);

		lblTelefono = new JLabel("Telefono");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(20, 110, 107, 21);
		contentPane.add(lblTelefono);

		lblFax = new JLabel("Fax");
		lblFax.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFax.setBounds(20, 143, 107, 21);
		contentPane.add(lblFax);

		lblCUIT = new JLabel("CUIT");
		lblCUIT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCUIT.setBounds(20, 44, 107, 21);
		contentPane.add(lblCUIT);

		lblNombre = new JLabel("Razon social");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(20, 12, 107, 21);
		contentPane.add(lblNombre);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(20, 175, 107, 21);
		contentPane.add(lblEmail);

		txtRazonSocial = new JTextField();
		txtRazonSocial.setColumns(10);
		txtRazonSocial.setBounds(141, 11, 246, 23);
		contentPane.add(txtRazonSocial);

		txtCUIT = new JTextField();
		txtCUIT.setColumns(10);
		txtCUIT.setBounds(141, 44, 246, 23);
		contentPane.add(txtCUIT);

		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(141, 77, 246, 23);
		contentPane.add(txtDireccion);

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(141, 109, 246, 23);
		contentPane.add(txtTelefono);

		txtFax = new JTextField();
		txtFax.setColumns(10);
		txtFax.setBounds(141, 142, 246, 23);
		contentPane.add(txtFax);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(141, 174, 246, 23);
		contentPane.add(txtEmail);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// validaciones

				ArrayList<String> errores = new ArrayList<String>();
				if (!Auxiliar.isValidCUIT(txtCUIT.getText()))
					errores.add("Numero de CUIT invalido.");
				if (!Auxiliar.isValidTelephone(txtTelefono.getText()))
					errores.add("Numero de Telefono invalido.");
				if (!Auxiliar.isValidTelephone(txtFax.getText()))
					errores.add("Numero de Fax invalido.");
				if (!Auxiliar.isValidEmail(txtEmail.getText()))
					errores.add("Email invalido.");
				// si hay errores los muestra
				if (errores.size() > 0)
					JOptionPane.showMessageDialog(null,
							Auxiliar.contenarArrayList(errores));
				else {
					// si no hubo errores,crea el objeto proveedor

					Proveedor proveedor = new Proveedor();
					proveedor.setCuit(txtCUIT.getText());
					proveedor.setEmail(txtEmail.getText());
					proveedor.setDireccion(txtDireccion.getText());
					proveedor.setFax(txtFax.getText());
					proveedor.setTelefono(txtTelefono.getText());
					proveedor.setRazonSocial(txtRazonSocial.getText());

					try {

						Conexion conexion = new Conexion();

						if (conexion.conectarDB()) {

							conexion.altaProveedor(proveedor);

							conexion.close();

							Main frame = new Main();
							frame.setVisible(true);

							dispose();
						}

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e, "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		btnAceptar.setBounds(267, 233, 89, 23);
		btnAceptar.setEnabled(false);
		contentPane.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main frame = new Main();
				frame.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(153, 233, 89, 23);
		contentPane.add(btnCancelar);

		txtCUIT.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {

				estadoDeLosBotones();

				if (Auxiliar.isInteger(txtCUIT.getText())) {
					txtCUIT.setForeground(Color.black);
				} else {
					txtCUIT.setForeground(Color.red);
				}

			}
		});

		txtDireccion.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				estadoDeLosBotones();
			}
		});

		txtEmail.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				estadoDeLosBotones();
				
				if (Auxiliar.isValidEmail(txtEmail.getText())) {
					txtEmail.setForeground(Color.black);
				} else {
					txtEmail.setForeground(Color.red);
				}
			}
		});

		txtFax.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				estadoDeLosBotones();
				
				if (Auxiliar.isValidTelephone(txtFax.getText())) {
					txtFax.setForeground(Color.black);
				} else {
					txtFax.setForeground(Color.red);
				}
				
			}
		});

		txtRazonSocial.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				estadoDeLosBotones();
			}
		});

		txtTelefono.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				estadoDeLosBotones();
				
				if (Auxiliar.isValidTelephone(txtTelefono.getText())) {
					txtTelefono.setForeground(Color.black);
				} else {
					txtTelefono.setForeground(Color.red);
				}
			}
		});

	}

	private boolean testearCamposDeTexto() {

		return (txtCUIT.getText().length() > 0)
				&& (txtDireccion.getText().length() > 0)
				&& (txtEmail.getText().length() > 0)
				&& (txtFax.getText().length() > 0)
				&& (txtRazonSocial.getText().length() > 0)
				&& (txtTelefono.getText().length() > 0)
				&& Auxiliar.isInteger(txtCUIT.getText())
				&& Auxiliar.isValidEmail(txtEmail.getText())
				&& Auxiliar.isValidTelephone(txtFax.getText())
				&& Auxiliar.isValidTelephone(txtTelefono.getText());

	}

	private void estadoDeLosBotones() {

		if (testearCamposDeTexto()) {
			btnAceptar.setEnabled(true);
		} else {
			btnAceptar.setEnabled(false);
		}
	}
}
