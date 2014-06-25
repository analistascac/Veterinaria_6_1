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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import Clases.Cliente;
import Clases.Mascota;
import Conexion.Conexion;
import Conexion.insertDBException;
import Main.Main;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class winEliminarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldDoc;
	private JTextField textFieldDireccion;
	private JTextField textFieldOcupacion;
	private JTextField textFieldTelefono;
	private JTextField textFieldEmail;
	private JTextField textFieldTipoDoc;
	private DefaultComboBoxModel clientes = new DefaultComboBoxModel();
	private JComboBox comboBoxCliente;
	private JButton btnEliminar;
	private JLabel lblNewLabel;

	public winEliminarCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(winEliminarCliente.class.getResource("/Images/logo.jpg")));
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				 Main v = new Main();
				 v.setVisible(true);
				 dispose();
			}
		});
		setTitle("Veterinaria Godzilla - Baja - Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 407, 366);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			
		}
		
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNombre.setBounds(-7, 41, 106, 21);
		contentPane.add(labelNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(112, 41, 246, 21);
		contentPane.add(textFieldNombre);
		
		JLabel labelApellido = new JLabel("Apellido:");
		labelApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		labelApellido.setBounds(-7, 73, 106, 21);
		contentPane.add(labelApellido);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setEditable(false);
		textFieldApellido.setColumns(10);
		textFieldApellido.setBounds(112, 73, 246, 21);
		contentPane.add(textFieldApellido);
		
		JLabel labelTipoDoc = new JLabel("Tipo documento:");
		labelTipoDoc.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTipoDoc.setBounds(-7, 105, 106, 21);
		contentPane.add(labelTipoDoc);
		
		JLabel labelDoc = new JLabel("Documento:");
		labelDoc.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDoc.setBounds(-32, 137, 131, 24);
		contentPane.add(labelDoc);
		
		textFieldDoc = new JTextField();
		textFieldDoc.setEditable(false);
		textFieldDoc.setColumns(10);
		textFieldDoc.setBounds(112, 134, 246, 21);
		contentPane.add(textFieldDoc);
		
		JLabel labelDireccion = new JLabel("Direccion:");
		labelDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDireccion.setBounds(3, 166, 96, 21);
		contentPane.add(labelDireccion);
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setEditable(false);
		textFieldDireccion.setColumns(10);
		textFieldDireccion.setBounds(112, 166, 246, 21);
		contentPane.add(textFieldDireccion);
		
		JLabel labelOcupacion = new JLabel("Ocupacion:");
		labelOcupacion.setHorizontalAlignment(SwingConstants.RIGHT);
		labelOcupacion.setBounds(-7, 200, 106, 21);
		contentPane.add(labelOcupacion);
		
		textFieldOcupacion = new JTextField();
		textFieldOcupacion.setEditable(false);
		textFieldOcupacion.setColumns(10);
		textFieldOcupacion.setBounds(112, 200, 246, 21);
		contentPane.add(textFieldOcupacion);
		
		JLabel labelTelefono = new JLabel("Telefono:");
		labelTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTelefono.setBounds(3, 239, 96, 21);
		contentPane.add(labelTelefono);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setEditable(false);
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(112, 239, 246, 21);
		contentPane.add(textFieldTelefono);
		
		JLabel labelEmail = new JLabel("E-Mail:");
		labelEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEmail.setBounds(53, 278, 46, 14);
		contentPane.add(labelEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(112, 275, 246, 20);
		contentPane.add(textFieldEmail);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					try {
						Cliente clie = new Cliente();
						clie = (Cliente) cn.devolverClientes().get(comboBoxCliente.getSelectedIndex());
						int i = JOptionPane.showConfirmDialog(null, "Confirma la baja ?", "Baja Cliente",JOptionPane.YES_NO_OPTION);
						if (i == JOptionPane.YES_OPTION){
							cn.bajaCliente(clie);
							JOptionPane.showMessageDialog(null, "La baja ha sido correcta.","Información",JOptionPane.INFORMATION_MESSAGE);
							llenarComboBox();
						}
					} catch (insertDBException e) {
						JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				cn.close();
				
				textFieldApellido.setText("");
				textFieldNombre.setText("");
				textFieldApellido.setText("");
				textFieldTipoDoc.setText("");
				textFieldDoc.setText("");
				textFieldDireccion.setText("");
				textFieldOcupacion.setText("");
				textFieldTelefono.setText("");
				textFieldEmail.setText("");
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(269, 306, 89, 23);
		contentPane.add(btnEliminar);
		
		textFieldTipoDoc = new JTextField();
		textFieldTipoDoc.setEditable(false);
		textFieldTipoDoc.setColumns(10);
		textFieldTipoDoc.setBounds(112, 105, 246, 21);
		contentPane.add(textFieldTipoDoc);
		
		comboBoxCliente = new JComboBox();
		llenarComboBox();
		comboBoxCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					Cliente clie = new Cliente();
					clie = cn.devolverClientes().get(comboBoxCliente.getSelectedIndex());
					textFieldNombre.setText(clie.getNombre());
					textFieldApellido.setText(clie.getApellido());
					textFieldTipoDoc.setText(clie.getTipoDocumento());
					textFieldDoc.setText(clie.getDocumento());
					textFieldDireccion.setText(clie.getDireccion());
					textFieldOcupacion.setText(clie.getOcupacion());
					textFieldTelefono.setText(clie.getTelefono());
					textFieldEmail.setText(clie.getEmail());
					btnEliminar.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				cn.close();
				
			}
		});
		comboBoxCliente.setBounds(112, 11, 246, 20);
		comboBoxCliente.setModel(clientes);
		contentPane.add(comboBoxCliente);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCliente.setBounds(53, 14, 46, 14);
		contentPane.add(lblCliente);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(winEliminarCliente.class.getResource("/Images/image_marca_agua.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 401, 338);
		contentPane.add(lblNewLabel);
		
	}
	
	private void llenarComboBox(){
		Conexion cn = new Conexion();
		ArrayList<Cliente> aCliente = new ArrayList<Cliente>();
		if(cn.conectarDB()){
			aCliente = cn.devolverClientes();
			Iterator<Cliente> it = aCliente.iterator();
			Cliente tmp = new Cliente();
			clientes = new DefaultComboBoxModel();
			comboBoxCliente.setModel(clientes);
			while(it.hasNext()){
				tmp = it.next();
				clientes.addElement(tmp.getNombre() + " " + tmp.getApellido());
			}
			if(clientes.getSize() == 0){
				btnEliminar.setEnabled(false);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
		cn.close();
	}
}
