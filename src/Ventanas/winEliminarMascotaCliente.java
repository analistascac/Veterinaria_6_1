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

import Clases.Cliente;
import Clases.Empleado;
import Clases.Mascota;
import Conexion.Conexion;
import Conexion.insertDBException;
import Main.Main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class winEliminarMascotaCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldEspecie;
	private JTextField textFieldDenominacion;
	private JTextField textFieldDescripcion;
	private DefaultComboBoxModel clientes = new DefaultComboBoxModel();
	private JComboBox comboBoxCliente = new JComboBox();
	private DefaultComboBoxModel clienteMascotas = new DefaultComboBoxModel();
	private JComboBox comboBoxClienteMascotas = new JComboBox();
	private JButton btnEliminar;
			
	public winEliminarMascotaCliente() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				 Main v = new Main();
				 v.setVisible(true);
				 dispose();
			}
		});
		setTitle("Veterinaria Godzilla - Baja - Mascota");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 412, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Baja de Mascota");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(79, 11, 246, 14);
		contentPane.add(lblNewLabel);
		
		JLabel labelCliente = new JLabel("Cliente:");
		labelCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCliente.setBounds(53, 45, 46, 14);
		contentPane.add(labelCliente);
		
		comboBoxCliente = new JComboBox();
		llenarClientes();
		comboBoxCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					Cliente clie = new Cliente();
					clie = cn.devolverClientes().get(comboBoxCliente.getSelectedIndex());
					int aux = comboBoxCliente.getSelectedIndex();
					if(aux!=-1){
						llenarClienteMascota(clie);
						Mascota mas = new Mascota();
						mas = cn.devolverClienteMascotas(clie).get(comboBoxClienteMascotas.getSelectedIndex());
						textFieldEspecie.setText(mas.getNombreCientifico());
						textFieldDenominacion.setText(mas.getNombreVulgar());
						textFieldDescripcion.setText(mas.getDescripcion());
						btnEliminar.setEnabled(true);
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				cn.close();
			}
		});
		comboBoxCliente.setBounds(112, 42, 246, 20);
		contentPane.add(comboBoxCliente);
		
		JLabel labelNombCientifico = new JLabel("Especie:");
		labelNombCientifico.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNombCientifico.setBounds(-7, 109, 106, 21);
		contentPane.add(labelNombCientifico);
		
		textFieldEspecie = new JTextField();
		textFieldEspecie.setColumns(10);
		textFieldEspecie.setBounds(112, 109, 246, 21);
		contentPane.add(textFieldEspecie);
		
		JLabel labelNombVulgar = new JLabel("Denominacion:");
		labelNombVulgar.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNombVulgar.setBounds(-7, 141, 106, 21);
		contentPane.add(labelNombVulgar);
		
		textFieldDenominacion = new JTextField();
		textFieldDenominacion.setColumns(10);
		textFieldDenominacion.setBounds(112, 141, 246, 21);
		contentPane.add(textFieldDenominacion);
		
		JLabel labelDescripcion = new JLabel("Descripcion");
		labelDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDescripcion.setBounds(-7, 173, 106, 21);
		contentPane.add(labelDescripcion);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setColumns(10);
		textFieldDescripcion.setBounds(112, 173, 246, 21);
		contentPane.add(textFieldDescripcion);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					try {
						Mascota m = new Mascota();
						Cliente clie = new Cliente();
						clie = cn.devolverClientes().get(comboBoxCliente.getSelectedIndex());
						m = cn.devolverClienteMascotas(clie).get(comboBoxClienteMascotas.getSelectedIndex());
						int i = JOptionPane.showConfirmDialog(null, "Confirma la baja ?", "Baja Mascota",JOptionPane.YES_NO_OPTION);
						if (i == JOptionPane.YES_OPTION){
							cn.bajaMascotaCliente(m);
							JOptionPane.showMessageDialog(null, "La baja ha sido correcta.","Información",JOptionPane.INFORMATION_MESSAGE);
							llenarClientes();
						}
					} catch (insertDBException e) {
						JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				cn.close();
				
				textFieldDenominacion.setText("");
				textFieldDescripcion.setText("");
				textFieldEspecie.setText("");
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(269, 205, 89, 23);
		contentPane.add(btnEliminar);
		
		JLabel labelMascota = new JLabel("Mascota:");
		labelMascota.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMascota.setBounds(20, 81, 79, 14);
		contentPane.add(labelMascota);
		
		comboBoxClienteMascotas = new JComboBox();
		comboBoxClienteMascotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					Cliente clie = cn.devolverClientes().get(comboBoxCliente.getSelectedIndex());
					llenarClienteMascota(clie);	
					Mascota mas = cn.devolverClienteMascotas(clie).get(comboBoxClienteMascotas.getSelectedIndex());
					textFieldEspecie.setText(mas.getNombreCientifico());
					textFieldDenominacion.setText(mas.getNombreVulgar());
					textFieldDescripcion.setText(mas.getDescripcion());
				} else {
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				cn.close();
			}
		});
		comboBoxClienteMascotas.setBounds(112, 78, 246, 20);
		contentPane.add(comboBoxClienteMascotas);
	}

	public void llenarClientes(){
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
		} else {
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		cn.close();
		
	}
	
	public void llenarClienteMascota(Cliente clie){
		Conexion cn = new Conexion();
		ArrayList<Mascota> aMascota = new ArrayList<Mascota>();
		if(cn.conectarDB()){
			aMascota = cn.devolverClienteMascotas(clie);
			Iterator<Mascota> it = aMascota.iterator();
			Mascota tmp = new Mascota();
			clienteMascotas.removeAllElements(); //= new DefaultComboBoxModel();
			//comboBoxClienteMascotas.setModel(clienteMascotas);
			while(it.hasNext()){
				tmp = it.next();
				System.out.println(tmp.toString());
				clienteMascotas.addElement(tmp.getNombreVulgar());
			}
			comboBoxClienteMascotas.setModel(clienteMascotas);
		} else {
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
		cn.close();
	}
}
