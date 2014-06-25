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
import java.awt.Toolkit;

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
	private JLabel lblNewLabel;

	public frmNuevoProveedor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmNuevoProveedor.class.getResource("/Images/logo.jpg")));
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent arg0) {
				Main v = new Main();
				v.setVisible(true);
				dispose();
			}
		});

		setTitle("Veterinaria Godzilla - Alta - Proveedor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 419, 305);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(35, 95, 72, 21);
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblDireccion);

		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(0, 127, 107, 21);
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblTelefono);

		lblFax = new JLabel("Fax:");
		lblFax.setBounds(0, 160, 107, 21);
		lblFax.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblFax);

		lblCUIT = new JLabel("CUIT:");
		lblCUIT.setBounds(0, 61, 107, 21);
		lblCUIT.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblCUIT);

		lblNombre = new JLabel("Razon social:");
		lblNombre.setBounds(0, 29, 107, 21);
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNombre);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(0, 192, 107, 21);
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblEmail);

		txtRazonSocial = new JTextField();
		txtRazonSocial.setBounds(117, 27, 246, 23);
		txtRazonSocial.setColumns(10);
		contentPane.add(txtRazonSocial);

		txtCUIT = new JTextField();
		txtCUIT.setBounds(117, 60, 246, 23);
		txtCUIT.setColumns(10);
		contentPane.add(txtCUIT);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(117, 93, 246, 23);
		txtDireccion.setColumns(10);
		contentPane.add(txtDireccion);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(117, 125, 246, 23);
		txtTelefono.setColumns(10);
		contentPane.add(txtTelefono);

		txtFax = new JTextField();
		txtFax.setBounds(117, 158, 246, 23);
		txtFax.setColumns(10);
		contentPane.add(txtFax);

		txtEmail = new JTextField();
		txtEmail.setBounds(117, 190, 246, 23);
		txtEmail.setColumns(10);
		contentPane.add(txtEmail);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(176, 233, 89, 23);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = JOptionPane.showConfirmDialog(null, "¿Confirma dar de alta el nuevo proveedor?", "Confirmación",JOptionPane.YES_NO_OPTION);
				if(x == JOptionPane.YES_OPTION){
					ArrayList<String> errores = new ArrayList<String>();
					if (!Auxiliar.isValidCUIT(txtCUIT.getText()))
						errores.add("Numero de CUIT invalido.");
					if (!Auxiliar.isValidTelephone(txtTelefono.getText()))
						errores.add("Numero de Telefono invalido.");
					if (!Auxiliar.isValidTelephone(txtFax.getText()))
						errores.add("Numero de Fax invalido.");
					if (!Auxiliar.isValidEmail(txtEmail.getText()))
						errores.add("Email invalido.");
					if (errores.size() > 0)
						JOptionPane.showMessageDialog(null,
								Auxiliar.contenarArrayList(errores));
					else {
	
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
								JOptionPane.showMessageDialog(null, "El alta al proveedor neuvo ha sido exitosa","Información",JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}
	
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e, "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		btnAceptar.setEnabled(false);
		contentPane.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(275, 233, 89, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		contentPane.add(btnCancelar);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmNuevoProveedor.class.getResource("/Images/image_marca_agua.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 413, 277);
		contentPane.add(lblNewLabel);

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
