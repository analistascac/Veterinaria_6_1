package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import Clases.Proveedor;
import Conexion.Conexion;
import Conexion.insertDBException;
import Main.Main;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class winEliminarProveedor extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDireccion;
	private JTextField textFieldCuit;
	private JTextField textFieldRazonSocial;
	private JTextField textFieldUltimaCompra;
	private JTextField textFieldEmail;
	private JTextField textFieldFax;
	private JTextField textFieldTelefono;
	private JButton btnEliminar;
	private DefaultComboBoxModel proveedores = new DefaultComboBoxModel();
	private JComboBox comboBoxProveedor = new JComboBox(); 
	private JLabel lblNewLabel;
	
	public winEliminarProveedor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(winEliminarProveedor.class.getResource("/Images/logo.jpg")));
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				 Main v = new Main();
				 v.setVisible(true);
				 dispose();
			}
		});
		setTitle("Veterinaria Godzilla - Baja - Proveedor");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 412, 331);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelProveedor = new JLabel("Proveedor:");
		labelProveedor.setBounds(37, 14, 66, 14);
		labelProveedor.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(labelProveedor);
		
		JLabel labelRazonSocial = new JLabel("Razon Social:");
		labelRazonSocial.setBounds(-3, 41, 106, 21);
		labelRazonSocial.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(labelRazonSocial);
		
		JLabel labelCuit = new JLabel("Cuit:");
		labelCuit.setBounds(-3, 73, 106, 21);
		labelCuit.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(labelCuit);
		
		JLabel labelDireccion = new JLabel("Direccion:");
		labelDireccion.setBounds(-3, 105, 106, 21);
		labelDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(labelDireccion);
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setEditable(false);
		textFieldDireccion.setBounds(116, 105, 246, 21);
		textFieldDireccion.setColumns(10);
		contentPane.add(textFieldDireccion);
		
		textFieldCuit = new JTextField();
		textFieldCuit.setEditable(false);
		textFieldCuit.setBounds(116, 73, 246, 21);
		textFieldCuit.setColumns(10);
		contentPane.add(textFieldCuit);
		
		textFieldRazonSocial = new JTextField();
		textFieldRazonSocial.setEditable(false);
		textFieldRazonSocial.setBounds(116, 41, 246, 21);
		textFieldRazonSocial.setColumns(10);
		contentPane.add(textFieldRazonSocial);
		
		JLabel labelTelefono = new JLabel("Telefono:");
		labelTelefono.setBounds(-28, 137, 131, 24);
		labelTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(labelTelefono);
		
		JLabel labelFax = new JLabel("Fax:");
		labelFax.setBounds(7, 166, 96, 21);
		labelFax.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(labelFax);
		
		JLabel labelEmail = new JLabel("Email:");
		labelEmail.setBounds(-3, 200, 106, 21);
		labelEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(labelEmail);
		
		JLabel labelFechaUltCompra = new JLabel("Ultima Compra:");
		labelFechaUltCompra.setBounds(7, 239, 96, 21);
		labelFechaUltCompra.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(labelFechaUltCompra);
		
		textFieldUltimaCompra = new JTextField();
		textFieldUltimaCompra.setEditable(false);
		textFieldUltimaCompra.setBounds(116, 239, 246, 21);
		textFieldUltimaCompra.setColumns(10);
		contentPane.add(textFieldUltimaCompra);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		textFieldEmail.setBounds(116, 200, 246, 21);
		textFieldEmail.setColumns(10);
		contentPane.add(textFieldEmail);
		
		textFieldFax = new JTextField();
		textFieldFax.setEditable(false);
		textFieldFax.setBounds(116, 166, 246, 21);
		textFieldFax.setColumns(10);
		contentPane.add(textFieldFax);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setEditable(false);
		textFieldTelefono.setBounds(116, 134, 246, 21);
		textFieldTelefono.setColumns(10);
		contentPane.add(textFieldTelefono);
		
		comboBoxProveedor = new JComboBox();
		llenarProveedores();
		comboBoxProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion cn = new Conexion();
				
				if(cn.conectarDB()){
					Proveedor prov = new Proveedor();
					prov = cn.devolverProveedores().get(comboBoxProveedor.getSelectedIndex());
					textFieldRazonSocial.setText(prov.getRazonSocial());
					textFieldCuit.setText(prov.getCuit());
					textFieldDireccion.setText(prov.getDireccion());
					textFieldEmail.setText(prov.getEmail());
					textFieldFax.setText(prov.getFax());
					textFieldTelefono.setText(prov.getTelefono());
					textFieldUltimaCompra.setText(prov.getFechaUltimaCompra());
					btnEliminar.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				cn.close();
			}
		});
		comboBoxProveedor.setBounds(116, 11, 246, 20);
		contentPane.add(comboBoxProveedor);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					try {
						Proveedor prov = new Proveedor();
						prov = (Proveedor) cn.devolverProveedores().get(comboBoxProveedor.getSelectedIndex());
						int i = JOptionPane.showConfirmDialog(null, "Confirma la baja ?", "Baja Proveedor",JOptionPane.YES_NO_OPTION);
						if (i == JOptionPane.YES_OPTION){
							cn.bajaProveedor(prov);
							JOptionPane.showMessageDialog(null, "La baja ha sido correcta.","Información",JOptionPane.INFORMATION_MESSAGE);
							llenarProveedores();
						}
					} catch (insertDBException e) {
						JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				cn.close();
				
				textFieldCuit.setText("");
				textFieldDireccion.setText("");
				textFieldEmail.setText("");
				textFieldFax.setText("");
				textFieldRazonSocial.setText("");
				textFieldTelefono.setText("");
				textFieldUltimaCompra.setText("");
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(273, 271, 89, 23);
		contentPane.add(btnEliminar);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(winEliminarProveedor.class.getResource("/Images/image_marca_agua.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(-3, 0, 409, 303);
		contentPane.add(lblNewLabel);
	}
	
	public void llenarProveedores(){
		Conexion cn = new Conexion();
		ArrayList<Proveedor> aProveedor = new ArrayList<Proveedor>();
		if(cn.conectarDB()){
			aProveedor = cn.devolverProveedores();
			Iterator<Proveedor> it = aProveedor.iterator();
			Proveedor tmp = new Proveedor();
			proveedores = new DefaultComboBoxModel();
			comboBoxProveedor.setModel(proveedores);
			while(it.hasNext()){
				tmp = it.next();
				proveedores.addElement(tmp.getRazonSocial() + " - " + tmp.getCuit());
			}
			if(proveedores.getSize() == 0){
				btnEliminar.setEnabled(false);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		cn.close();
	}

}
