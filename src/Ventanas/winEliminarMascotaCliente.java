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
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(winEliminarMascotaCliente.class.getResource("/Images/logo.jpg")));
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
		setBounds(100, 100, 412, 238);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelCliente = new JLabel("Cliente:");
		labelCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		labelCliente.setBounds(60, 14, 46, 14);
		contentPane.add(labelCliente);
		
		comboBoxCliente = new JComboBox();
		llenarClientes();
		comboBoxCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					Cliente clie = new Cliente();
					clie = cn.devolverClientes().get(comboBoxCliente.getSelectedIndex());
					
					llenarClienteMascota(clie);
				} else {
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				cn.close();
			}
		});
		comboBoxCliente.setBounds(119, 11, 246, 20);
		contentPane.add(comboBoxCliente);
		
		JLabel labelNombCientifico = new JLabel("Especie:");
		labelNombCientifico.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNombCientifico.setBounds(0, 78, 106, 21);
		contentPane.add(labelNombCientifico);
		
		textFieldEspecie = new JTextField();
		textFieldEspecie.setEditable(false);
		textFieldEspecie.setColumns(10);
		textFieldEspecie.setBounds(119, 78, 246, 21);
		contentPane.add(textFieldEspecie);
		
		JLabel labelNombVulgar = new JLabel("Denominacion:");
		labelNombVulgar.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNombVulgar.setBounds(0, 110, 106, 21);
		contentPane.add(labelNombVulgar);
		
		textFieldDenominacion = new JTextField();
		textFieldDenominacion.setEditable(false);
		textFieldDenominacion.setColumns(10);
		textFieldDenominacion.setBounds(119, 110, 246, 21);
		contentPane.add(textFieldDenominacion);
		
		JLabel labelDescripcion = new JLabel("Descripcion");
		labelDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDescripcion.setBounds(0, 142, 106, 21);
		contentPane.add(labelDescripcion);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setEditable(false);
		textFieldDescripcion.setColumns(10);
		textFieldDescripcion.setBounds(119, 142, 246, 21);
		contentPane.add(textFieldDescripcion);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = JOptionPane.showConfirmDialog(null, "¿Realmente quiere dar de baja esta mascota?", "Confirmacion",JOptionPane.YES_NO_OPTION);
				if(x == JOptionPane.YES_OPTION){
				
					Conexion cn = new Conexion();
					if(cn.conectarDB()){
						Mascota esta = new Mascota();
						Cliente este = new Cliente();
						este = cn.devolverClientes().get(comboBoxCliente.getSelectedIndex());
						esta = cn.devolverClienteMascotas(este).get(comboBoxClienteMascotas.getSelectedIndex());
						
						try {
							cn.bajaMascotaCliente(esta);
							textFieldDenominacion.setText("");
							textFieldDescripcion.setText("");
							textFieldEspecie.setText("");
							llenarClienteMascota(este);
							JOptionPane.showMessageDialog(null, "La mascota ha sido dada de baja correctamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
						} catch (insertDBException e) {
							JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
					}
					
					cn.close();
					
				
				}
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(276, 174, 89, 23);
		contentPane.add(btnEliminar);
		
		JLabel labelMascota = new JLabel("Mascota:");
		labelMascota.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMascota.setBounds(27, 50, 79, 14);
		contentPane.add(labelMascota);
		
		comboBoxClienteMascotas = new JComboBox();
		comboBoxClienteMascotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					Cliente clie = new Cliente(); 
					clie = cn.devolverClientes().get(comboBoxCliente.getSelectedIndex());
					
					Mascota mas = new Mascota();
					int id = comboBoxClienteMascotas.getSelectedIndex();
					
					if(id != -1){
						mas = cn.devolverClienteMascotas(clie).get(id);
						textFieldEspecie.setText(mas.getNombreCientifico());
						textFieldDenominacion.setText(mas.getNombreVulgar());
						textFieldDescripcion.setText(mas.getDescripcion());
						btnEliminar.setEnabled(true);
					}else{
						textFieldEspecie.setText("");
						textFieldDenominacion.setText("");
						textFieldDescripcion.setText("");
						btnEliminar.setEnabled(false);
					}
					
				} else {
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				cn.close();
			}
		});
		comboBoxClienteMascotas.setBounds(119, 47, 246, 20);
		contentPane.add(comboBoxClienteMascotas);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(winEliminarMascotaCliente.class.getResource("/Images/image_marca_agua_small.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 406, 210);
		contentPane.add(lblNewLabel);
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
			clienteMascotas.removeAllElements();
			while(it.hasNext()){
				tmp = it.next();
				clienteMascotas.addElement(tmp.getNombreVulgar());
			}
			comboBoxClienteMascotas.setModel(clienteMascotas);
		} else {
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
		cn.close();
	}
}
