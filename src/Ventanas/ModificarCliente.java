package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import java.awt.GridLayout;

import javax.swing.JComboBox;

import java.awt.FlowLayout;

import Clases.Auxiliar;
import Clases.Cliente;
import Conexion.Conexion;
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
import java.util.ArrayList;

import javax.swing.JRadioButton;

import java.awt.Toolkit;

import javax.swing.ImageIcon;

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
	private ButtonGroup radio = new ButtonGroup();
	private JComboBox cmbTipoPago;
	private DefaultComboBoxModel tipopago = new DefaultComboBoxModel();
	private JLabel lblNewLabel;

	public ModificarCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarCliente.class.getResource("/Images/logo.jpg")));

		llenarcombos();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Main v = new Main();
				v.setVisible(true);
				dispose();
			}
		});
		setTitle("Veterinaria Godzilla - Modificacion - Cliente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 373, 350);
		setLocationRelativeTo(null);
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
		lblNombre.setBounds(10, 39, 119, 15);
		contentPane.add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(10, 67, 119, 15);
		contentPane.add(lblApellido);

		JLabel lblTipoDocumento = new JLabel("Tipo Documento:");
		lblTipoDocumento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDocumento.setBounds(10, 95, 119, 15);
		contentPane.add(lblTipoDocumento);

		JLabel lblNumDoc = new JLabel("Documento:");
		lblNumDoc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumDoc.setBounds(10, 123, 119, 15);
		contentPane.add(lblNumDoc);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setBounds(10, 151, 119, 15);
		contentPane.add(lblDireccion);

		JLabel lblOcupacion = new JLabel("Ocupacion:");
		lblOcupacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOcupacion.setBounds(10, 179, 119, 15);
		contentPane.add(lblOcupacion);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(10, 207, 119, 15);
		contentPane.add(lblTelefono);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(10, 235, 119, 15);
		contentPane.add(lblEmail);

		JLabel lblTipoDePago = new JLabel("Tipo de pago:");
		lblTipoDePago.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDePago.setBounds(10, 263, 119, 15);
		contentPane.add(lblTipoDePago);

		cmdId = new JComboBox();
		cmdId.setModel(id);
		cmdId.setBounds(135, 11, 195, 17);
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
				cmbTipoPago.setEnabled(true);
				btnModificar.setEnabled(true);
				
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					Cliente este = new Cliente();
					este = cn.devolverClientes().get(cmdId.getSelectedIndex());
					
					txtNombre.setText(este.getNombre());
					txtApellido.setText(este.getApellido());
					txtNumDoc.setText(este.getDocumento());
					txtDireccion.setText(este.getDireccion());
					txtOcupacion.setText(este.getOcupacion());
					txtTelefono.setText(este.getTelefono());
					txtEmail.setText(este.getEmail());
					cmbTipoDoc.setSelectedItem(este.getTipoDocumento().toString());
					cmbTipoPago.setSelectedItem(este.getTipoPago().toString());
					
					cn.close();
					
				}else{
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});

		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setBounds(135, 39, 195, 17);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {
				if (testearCamposDeTexto()) {
					btnModificar.setEnabled(true);
				} else {
					btnModificar.setEnabled(false);
				}

			}
		});

		txtApellido = new JTextField();
		txtApellido.setEnabled(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(135, 67, 195, 17);
		contentPane.add(txtApellido);
		txtApellido.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {
				if (testearCamposDeTexto()) {
					btnModificar.setEnabled(true);
				} else {
					btnModificar.setEnabled(false);
				}

			}
		});
		
		cmbTipoDoc = new JComboBox();
		cmbTipoDoc.setEnabled(false);
		cmbTipoDoc.setModel(tipodoc);
		cmbTipoDoc.setBounds(135, 95, 195, 17);
		contentPane.add(cmbTipoDoc);

		txtNumDoc = new JTextField();
		txtNumDoc.setEnabled(false);
		txtNumDoc.setColumns(10);
		txtNumDoc.setBounds(135, 123, 195, 17);
		contentPane.add(txtNumDoc);
		txtNumDoc.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {

				if (Auxiliar.isValidDNI(txtNumDoc.getText())) {

					txtNumDoc.setForeground(Color.black);
					estadoDeLosBotones();

				}

				else {

					txtNumDoc.setForeground(Color.red);
					btnModificar.setEnabled(false);

				}
			}
		});

		txtDireccion = new JTextField();
		txtDireccion.setEnabled(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(135, 151, 195, 17);
		contentPane.add(txtDireccion);
		txtDireccion.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {

				testearCamposDeTexto();

			}
		});

		txtOcupacion = new JTextField();
		txtOcupacion.setEnabled(false);
		txtOcupacion.setColumns(10);
		txtOcupacion.setBounds(135, 179, 195, 17);
		contentPane.add(txtOcupacion);
		txtOcupacion.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {

				testearCamposDeTexto();

			}
		});

		txtTelefono = new JTextField();
		txtTelefono.setEnabled(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(135, 207, 195, 17);
		contentPane.add(txtTelefono);
		txtTelefono.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {

				if (Auxiliar.isValidTelephone(txtTelefono.getText())) {
					txtTelefono.setForeground(Color.black);
					estadoDeLosBotones();
				} else {

					txtTelefono.setForeground(Color.red);
					btnModificar.setEnabled(false);
				}

			}
		});

		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(135, 235, 195, 17);
		contentPane.add(txtEmail);
		txtEmail.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {

				if (Auxiliar.isValidEmail(txtEmail.getText())) {
					txtEmail.setForeground(Color.black);
					estadoDeLosBotones();
				} else {
					txtEmail.setForeground(Color.red);
					btnModificar.setEnabled(false);
				}

			}
		});

		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					Cliente nuevo = new Cliente();
					
					nuevo.setId(cn.devolverClientes().get(cmdId.getSelectedIndex()).getId());
					nuevo.setNombre(txtNombre.getText());
					nuevo.setApellido(txtApellido.getText());
					nuevo.setDocumento(txtNumDoc.getText());
					nuevo.setDireccion(txtDireccion.getText());
					nuevo.setOcupacion(txtOcupacion.getText());
					nuevo.setTelefono(txtTelefono.getText());
					nuevo.setEmail(txtEmail.getText());
					nuevo.setTipoDocumento(cmbTipoDoc.getSelectedItem()+"");
					nuevo.setTipoPago(cmbTipoPago.getSelectedItem()+"");
					
					int X = JOptionPane.showConfirmDialog(null,"¿Confirma modificar el cliente? Los datos son los siguientes:\n"+nuevo.toString(),"Confirmar",JOptionPane.YES_NO_OPTION);
					if(X == JOptionPane.YES_OPTION){
						try{
							cn.modificacionCliente(nuevo);
							JOptionPane.showMessageDialog(null, "Cliente correctamente modificado");
							cn.close();
							dispose();
						}catch(Exception e){
							JOptionPane.showMessageDialog(null, "Error al intentar modificar un cliente.","Error",JOptionPane.ERROR_MESSAGE);
						}
					}			
					
					
				}else{
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
					
			}
		});
		
		cmbTipoPago = new JComboBox();
		tipopago.addElement("Efectivo");
		tipopago.addElement("Tarjeta de credito");
		tipopago.addElement("Tarjeta de debito");
		cmbTipoPago.setModel(tipopago);
		cmbTipoPago.setEnabled(false);
		cmbTipoPago.setBounds(135, 262, 195, 17);
		contentPane.add(cmbTipoPago);
		btnModificar.setBounds(241, 290, 89, 23);
		contentPane.add(btnModificar);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ModificarCliente.class.getResource("/Images/image_marca_agua.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 367, 322);
		contentPane.add(lblNewLabel);

	}

	public void llenarcombos() {
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			ArrayList<Cliente> clis = new ArrayList();
			clis = cn.devolverClientes();
			
			for(int i = 0; i < clis.size(); i++){
				id.addElement(clis.get(i).getId() + " : ("+ clis.get(i).getNombre()+" "+clis.get(i).getApellido()+")");
			}
			
			cn.close();
		}else{
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}

		tipodoc.addElement("DNI");
		tipodoc.addElement("CI");
		tipodoc.addElement("LC");
	}
	
	private boolean testearCamposDeTexto() {

		return (txtNombre.getText().length() > 0)
				&& (txtApellido.getText().length() > 0)
				&& (txtNumDoc.getText().length() > 0)
				&& (txtDireccion.getText().length() > 0)
				&& (txtOcupacion.getText().length() > 0)
				&& (txtTelefono.getText().length() > 0)
				&& (txtEmail.getText().length() > 0)
				&& Auxiliar.isInteger(txtNumDoc.getText())
				&& Auxiliar.isValidTelephone(txtTelefono.getText())
				&& Auxiliar.isValidEmail(txtEmail.getText());
	}

	private void estadoDeLosBotones() {

		if (testearCamposDeTexto()) {
			btnModificar.setEnabled(true);
		} else {
			btnModificar.setEnabled(false);
		}
	}
}
