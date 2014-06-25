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
import javax.swing.ImageIcon;

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
	private JLabel lblNewLabel;

	public frmNuevoCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmNuevoCliente.class.getResource("/Images/logo.jpg")));
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent arg0) {
				Main v = new Main();
				v.setVisible(true);
				dispose();
			}
		});
		setTitle("Veterinaria Godzilla - Alta - Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 398);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		txtNombre = new JTextField();
		txtNombre.setBounds(151, 27, 246, 21);
		txtNombre.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {
				if (testearCamposDeTexto()) {
					btnAceptar.setEnabled(true);
				} else {
					btnAceptar.setEnabled(false);
				}

			}
		});
		contentPane.setLayout(null);

		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(25, 27, 106, 21);
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNombre);

		txtApellido = new JTextField();
		txtApellido.setBounds(151, 59, 246, 21);
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
		contentPane.add(txtApellido);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(25, 59, 106, 21);
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblApellido);

		lblTipoDoc = new JLabel("Tipo de documento:");
		lblTipoDoc.setBounds(10, 91, 121, 21);
		lblTipoDoc.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblTipoDoc);

		cmbTipoDoc = new JComboBox<String>();
		cmbTipoDoc.setBounds(151, 91, 114, 21);
		cmbTipoDoc.setModel(new DefaultComboBoxModel<String>(new String[] {
				"DNI", "CI", "LC" }));
		contentPane.add(cmbTipoDoc);

		lblNumeroDoc = new JLabel("Documento:");
		lblNumeroDoc.setBounds(0, 123, 131, 24);
		lblNumeroDoc.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNumeroDoc);

		txtNumDoc = new JTextField();
		txtNumDoc.setBounds(151, 123, 246, 21);
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
		contentPane.add(txtNumDoc);

		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(35, 158, 96, 21);
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(151, 155, 246, 21);
		txtDireccion.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {

				testearCamposDeTexto();

			}
		});

		txtDireccion.setColumns(10);
		contentPane.add(txtDireccion);

		lblFecha = new JLabel("Ocupacion:");
		lblFecha.setBounds(25, 190, 106, 21);
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblFecha);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(209, 321, 89, 23);

		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = JOptionPane.showConfirmDialog(null, "¿Confirma dar de alta el nuevo cliente?", "Confirmación",JOptionPane.YES_NO_OPTION);
				if(x == JOptionPane.YES_OPTION){
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
								
								JOptionPane.showMessageDialog(null, "Cliente dado de alta exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
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
		contentPane.add(btnAceptar);
		btnAceptar.setEnabled(false);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(308, 321, 89, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		contentPane.add(btnCancelar);

		cmbTipoDePago = new JComboBox<String>();
		cmbTipoDePago.setBounds(151, 280, 114, 21);
		cmbTipoDePago.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Efectivo", "Tarjeta de credito", "Tarjeta de debito" }));
		contentPane.add(cmbTipoDePago);

		txtOcupacion = new JTextField();
		txtOcupacion.setBounds(151, 187, 246, 21);
		txtOcupacion.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {

				testearCamposDeTexto();

			}
		});

		txtOcupacion.setColumns(10);
		contentPane.add(txtOcupacion);

		lblTipodepago = new JLabel("Tipo de Pago:");
		lblTipodepago.setBounds(52, 280, 79, 21);
		lblTipodepago.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblTipodepago);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(151, 219, 246, 21);
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
		contentPane.add(txtTelefono);

		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(35, 219, 96, 21);
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblTelefono);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(85, 251, 46, 14);
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblEmail);

		txtMail = new JTextField();
		txtMail.setBounds(151, 251, 246, 20);
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
		contentPane.add(txtMail);
		txtMail.setColumns(10);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frmNuevoCliente.class.getResource("/Images/image_marca_agua.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 464, 370);
		contentPane.add(lblNewLabel);
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
