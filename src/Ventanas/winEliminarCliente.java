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

	public winEliminarCliente() {
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
		setBounds(100, 100, 407, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			
		}
		
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNombre.setBounds(-9, 82, 106, 21);
		contentPane.add(labelNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(110, 82, 246, 21);
		contentPane.add(textFieldNombre);
		
		JLabel labelApellido = new JLabel("Apellido:");
		labelApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		labelApellido.setBounds(-9, 114, 106, 21);
		contentPane.add(labelApellido);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setColumns(10);
		textFieldApellido.setBounds(110, 114, 246, 21);
		contentPane.add(textFieldApellido);
		
		JLabel labelTipoDoc = new JLabel("Tipo documento:");
		labelTipoDoc.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTipoDoc.setBounds(-9, 146, 106, 21);
		contentPane.add(labelTipoDoc);
		
		JLabel labelDoc = new JLabel("Documento:");
		labelDoc.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDoc.setBounds(-34, 178, 131, 24);
		contentPane.add(labelDoc);
		
		textFieldDoc = new JTextField();
		textFieldDoc.setColumns(10);
		textFieldDoc.setBounds(110, 175, 246, 21);
		contentPane.add(textFieldDoc);
		
		JLabel labelDireccion = new JLabel("Direccion:");
		labelDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDireccion.setBounds(1, 207, 96, 21);
		contentPane.add(labelDireccion);
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setColumns(10);
		textFieldDireccion.setBounds(110, 207, 246, 21);
		contentPane.add(textFieldDireccion);
		
		JLabel labelOcupacion = new JLabel("Ocupacion:");
		labelOcupacion.setHorizontalAlignment(SwingConstants.RIGHT);
		labelOcupacion.setBounds(-9, 241, 106, 21);
		contentPane.add(labelOcupacion);
		
		textFieldOcupacion = new JTextField();
		textFieldOcupacion.setColumns(10);
		textFieldOcupacion.setBounds(110, 241, 246, 21);
		contentPane.add(textFieldOcupacion);
		
		JLabel labelTelefono = new JLabel("Telefono:");
		labelTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTelefono.setBounds(1, 280, 96, 21);
		contentPane.add(labelTelefono);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(110, 280, 246, 21);
		contentPane.add(textFieldTelefono);
		
		JLabel labelEmail = new JLabel("E-Mail:");
		labelEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEmail.setBounds(51, 319, 46, 14);
		contentPane.add(labelEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(110, 316, 246, 20);
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
		btnEliminar.setBounds(267, 347, 89, 23);
		contentPane.add(btnEliminar);
		
		textFieldTipoDoc = new JTextField();
		textFieldTipoDoc.setColumns(10);
		textFieldTipoDoc.setBounds(110, 146, 246, 21);
		contentPane.add(textFieldTipoDoc);
		
		JLabel lblNewLabel = new JLabel("Baja de Cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(125, 11, 162, 21);
		contentPane.add(lblNewLabel);
		
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
		comboBoxCliente.setBounds(110, 52, 246, 20);
		comboBoxCliente.setModel(clientes);
		contentPane.add(comboBoxCliente);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCliente.setBounds(51, 55, 46, 14);
		contentPane.add(lblCliente);
		
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
