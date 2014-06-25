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

	public ModificarCliente() {

		llenarcombos();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Main v = new Main();
				v.setVisible(true);
				dispose();
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
		txtNombre.setBounds(135, 31, 150, 17);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setEnabled(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(135, 51, 150, 17);
		contentPane.add(txtApellido);
		
				cmbTipoDoc = new JComboBox();
				cmbTipoDoc.setEnabled(false);
				cmbTipoDoc.setModel(tipodoc);
				cmbTipoDoc.setBounds(135, 71, 150, 17);
				contentPane.add(cmbTipoDoc);

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
		cmbTipoPago.setBounds(135, 190, 150, 17);
		contentPane.add(cmbTipoPago);
		btnModificar.setBounds(196, 245, 89, 23);
		contentPane.add(btnModificar);

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
}
