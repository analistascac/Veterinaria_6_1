package Ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.*;

import Clases.Auxiliar;
import Clases.Cliente;
import Conexion.Conexion;
import Main.Main;
// TODO
//import Main.TFecha;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class frmNuevoCliente extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JLabel lblTipoDoc;
	private JLabel lblNumeroDoc;
	private JLabel lblDireccion;
	private JLabel lblFecha;
	private JLabel lblTipodepago;
	private JLabel lblTelefono;

	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtNumDoc;
	private JTextField txtOcupacion;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtMail;

	private JComboBox<String> cmbTipoDoc;
	private JComboBox<String> cmbTipoDePago;

	private JButton btnCancelar;
	private JButton btnAceptar;

	public frmNuevoCliente() {
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent arg0) {
				Main v = new Main();
				v.setVisible(true);
				dispose();
			}
		});
		setTitle("Nuevo Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 420);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtNombre = new JTextField();
		txtNombre.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {
				if (testearCamposDeTexto()) {
					btnAceptar.setEnabled(true);
				} else {
					btnAceptar.setEnabled(false);
				}

			}
		});

		txtNombre.setBounds(151, 11, 246, 21);

		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(25, 11, 106, 21);
		contentPane.add(lblNombre);

		txtApellido = new JTextField();
		txtApellido.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {
				if (testearCamposDeTexto()) {
					btnAceptar.setEnabled(true);
				} else {
					btnAceptar.setEnabled(false);
				}

			}
		});

		txtApellido.setColumns(10);
		txtApellido.setBounds(151, 43, 246, 21);
		contentPane.add(txtApellido);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(25, 43, 106, 21);
		contentPane.add(lblApellido);

		lblTipoDoc = new JLabel("Tipo de documento:");
		lblTipoDoc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDoc.setBounds(10, 75, 121, 21);
		contentPane.add(lblTipoDoc);

		cmbTipoDoc = new JComboBox<String>();
		cmbTipoDoc.setModel(new DefaultComboBoxModel<String>(new String[] {
				"DNI", "CI", "LC" }));
		cmbTipoDoc.setBounds(151, 75, 114, 21);
		contentPane.add(cmbTipoDoc);

		lblNumeroDoc = new JLabel("Documento:");
		lblNumeroDoc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumeroDoc.setBounds(0, 107, 131, 24);
		contentPane.add(lblNumeroDoc);

		txtNumDoc = new JTextField();
		txtNumDoc.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {

				if (Auxiliar.isValidDNI(txtNumDoc.getText())) {

					txtNumDoc.setForeground(Color.black);
					estadoDeLosBotones();

				}

				else {

					txtNumDoc.setForeground(Color.red);
					btnAceptar.setEnabled(false);

				}
			}
		});

		txtNumDoc.setColumns(10);
		txtNumDoc.setBounds(151, 104, 246, 21);
		contentPane.add(txtNumDoc);

		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setBounds(35, 134, 96, 21);
		contentPane.add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {

				testearCamposDeTexto();

			}
		});

		txtDireccion.setColumns(10);
		txtDireccion.setBounds(151, 136, 246, 21);
		contentPane.add(txtDireccion);

		lblFecha = new JLabel("Ocupacion:");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setBounds(25, 170, 106, 21);
		contentPane.add(lblFecha);

		btnAceptar = new JButton("Aceptar");

		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ArrayList<String> errores = new ArrayList<String>();
				if (!Auxiliar.isValidDNI(txtNumDoc.getText()))
					errores.add("Numero de documento invalido.");
				if (errores.size() > 0)
					JOptionPane.showMessageDialog(null,
							Auxiliar.contenarArrayList(errores));
				else {
					Cliente cliente = new Cliente();
					cliente.setNombre(txtNombre.getText());
					cliente.setApellido(txtApellido.getText());
					cliente.setTipoDocumento((String) cmbTipoDoc
							.getSelectedItem());
					cliente.setDocumento(txtNumDoc.getText());
					cliente.setDireccion(txtDireccion.getText());
					cliente.setOcupacion(txtOcupacion.getText());
					cliente.setTelefono(txtTelefono.getText());
					cliente.setEmail(txtMail.getText());
					cliente.setTipoPago((String) cmbTipoDePago
							.getSelectedItem());

					try {
						Conexion conexion = new Conexion();

						if (conexion.conectarDB()) {
							conexion.altaCliente(cliente);
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
		btnAceptar.setBounds(308, 316, 89, 23);
		contentPane.add(btnAceptar);
		btnAceptar.setEnabled(false);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main frame = new Main();
				frame.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(195, 316, 89, 23);
		contentPane.add(btnCancelar);

		cmbTipoDePago = new JComboBox<String>();
		cmbTipoDePago.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Efectivo", "Tarjeta de credito", "Tarjeta de debito" }));
		cmbTipoDePago.setBounds(151, 284, 114, 21);
		contentPane.add(cmbTipoDePago);

		txtOcupacion = new JTextField();
		txtOcupacion.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {

				testearCamposDeTexto();

			}
		});

		txtOcupacion.setColumns(10);
		txtOcupacion.setBounds(151, 170, 246, 21);
		contentPane.add(txtOcupacion);

		lblTipodepago = new JLabel("Tipo de Pago");
		lblTipodepago.setBounds(52, 284, 79, 21);
		contentPane.add(lblTipodepago);

		txtTelefono = new JTextField();
		txtTelefono.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {

				if (Auxiliar.isValidTelephone(txtTelefono.getText())) {
					txtTelefono.setForeground(Color.black);
					estadoDeLosBotones();
				} else {

					txtTelefono.setForeground(Color.red);
					btnAceptar.setEnabled(false);
				}

			}
		});

		txtTelefono.setColumns(10);
		txtTelefono.setBounds(151, 209, 246, 21);
		contentPane.add(txtTelefono);

		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(35, 209, 96, 21);
		contentPane.add(lblTelefono);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(85, 248, 46, 14);
		contentPane.add(lblEmail);

		txtMail = new JTextField();
		txtMail.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {

				if (Auxiliar.isValidEmail(txtMail.getText())) {

					txtMail.setForeground(Color.black);
					estadoDeLosBotones();
				} else {
					txtMail.setForeground(Color.red);
					btnAceptar.setEnabled(false);
				}

			}
		});
		txtMail.setBounds(151, 245, 246, 20);
		contentPane.add(txtMail);
		txtMail.setColumns(10);
	}

	private boolean testearCamposDeTexto() {

		return (txtNombre.getText().length() > 0)
				&& (txtApellido.getText().length() > 0)
				&& (txtNumDoc.getText().length() > 0)
				&& (txtDireccion.getText().length() > 0)
				&& (txtOcupacion.getText().length() > 0)
				&& (txtTelefono.getText().length() > 0)
				&& (txtMail.getText().length() > 0)
				&& Auxiliar.isInteger(txtNumDoc.getText())
				&& Auxiliar.isValidTelephone(txtTelefono.getText())
				&& Auxiliar.isValidEmail(txtMail.getText());
	}

	private void estadoDeLosBotones() {

		if (testearCamposDeTexto()) {
			btnAceptar.setEnabled(true);
		} else {
			btnAceptar.setEnabled(false);
		}
	}
}
