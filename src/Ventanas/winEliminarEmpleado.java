package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import Clases.Cliente;
import Clases.Empleado;
import Clases.Proveedor;
import Conexion.Conexion;
import Conexion.insertDBException;
import Main.Main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class winEliminarEmpleado extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldMatricula;
	private JTextField textFieldFechaNacimiento;
	private JTextField textFieldTelefono;
	private JTextField textFieldDireccion;
	private JTextField textFieldDoc;
	private JTextField textFieldTipoDoc;
	private JTextField textFieldApellido;
	private JTextField textFieldNombre;
	private DefaultComboBoxModel empleados = new DefaultComboBoxModel();
	private JButton btnEliminar;
	private JComboBox comboBoxEmpleado;

	public winEliminarEmpleado() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				 Main v = new Main();
				 v.setVisible(true);
				 dispose(); 
			}
		});
		setTitle("Veterinaria Godzilla - Baja - Empleado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelBajeEmpleado = new JLabel("Baja de Empleado");
		labelBajeEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		labelBajeEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelBajeEmpleado.setBounds(124, 11, 162, 17);
		contentPane.add(labelBajeEmpleado);
		
		JLabel labelEmpleado = new JLabel("Empleado:");
		labelEmpleado.setHorizontalAlignment(SwingConstants.RIGHT);
		labelEmpleado.setBounds(44, 42, 67, 14);
		contentPane.add(labelEmpleado);
		
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNombre.setBounds(5, 69, 106, 21);
		contentPane.add(labelNombre);
		
		JLabel labelApellido = new JLabel("Apellido:");
		labelApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		labelApellido.setBounds(5, 101, 106, 21);
		contentPane.add(labelApellido);
		
		JLabel labelTipoDoc = new JLabel("Tipo documento:");
		labelTipoDoc.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTipoDoc.setBounds(5, 133, 106, 21);
		contentPane.add(labelTipoDoc);
		
		JLabel labelDoc = new JLabel("Documento:");
		labelDoc.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDoc.setBounds(-20, 165, 131, 24);
		contentPane.add(labelDoc);
		
		JLabel labelDireccion = new JLabel("Direccion:");
		labelDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		labelDireccion.setBounds(15, 194, 96, 21);
		contentPane.add(labelDireccion);
		
		JLabel labelTelefono = new JLabel("Telefono:");
		labelTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTelefono.setBounds(5, 228, 106, 21);
		contentPane.add(labelTelefono);
		
		JLabel labelFechaNacimiento = new JLabel("Fecha Nacimiento:");
		labelFechaNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		labelFechaNacimiento.setBounds(5, 267, 106, 21);
		contentPane.add(labelFechaNacimiento);
		
		JLabel labelMatricula = new JLabel("Matricula:");
		labelMatricula.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMatricula.setBounds(44, 306, 67, 14);
		contentPane.add(labelMatricula);
		
		textFieldMatricula = new JTextField();
		textFieldMatricula.setColumns(10);
		textFieldMatricula.setBounds(124, 303, 246, 20);
		contentPane.add(textFieldMatricula);
		
		textFieldFechaNacimiento = new JTextField();
		textFieldFechaNacimiento.setColumns(10);
		textFieldFechaNacimiento.setBounds(124, 267, 246, 21);
		contentPane.add(textFieldFechaNacimiento);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(124, 228, 246, 21);
		contentPane.add(textFieldTelefono);
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setColumns(10);
		textFieldDireccion.setBounds(124, 194, 246, 21);
		contentPane.add(textFieldDireccion);
		
		textFieldDoc = new JTextField();
		textFieldDoc.setColumns(10);
		textFieldDoc.setBounds(124, 162, 246, 21);
		contentPane.add(textFieldDoc);
		
		textFieldTipoDoc = new JTextField();
		textFieldTipoDoc.setColumns(10);
		textFieldTipoDoc.setBounds(124, 133, 246, 21);
		contentPane.add(textFieldTipoDoc);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setColumns(10);
		textFieldApellido.setBounds(124, 101, 246, 21);
		contentPane.add(textFieldApellido);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(124, 69, 246, 21);
		contentPane.add(textFieldNombre);
		
		comboBoxEmpleado = new JComboBox();
		llenaEmpleados();
		comboBoxEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					Empleado emp = new Empleado();
					emp = cn.devolverEmpleados().get(comboBoxEmpleado.getSelectedIndex());
					textFieldNombre.setText(emp.getNombre());
					textFieldApellido.setText(emp.getApellido());
					textFieldTipoDoc.setText(emp.getTipoDoc());
					textFieldDoc.setText(emp.getDocumento());
					textFieldDireccion.setText(emp.getDomicilio());
					textFieldTelefono.setText(emp.getTelefono());
					btnEliminar.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				cn.close();
			}
		});
		comboBoxEmpleado.setBounds(124, 39, 246, 20);
		contentPane.add(comboBoxEmpleado);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					try {
						Empleado emp = new Empleado();
						emp = (Empleado) cn.devolverEmpleados().get(comboBoxEmpleado.getSelectedIndex());
						int i = JOptionPane.showConfirmDialog(null, "Confirma la baja ?", "Baja Empleado",JOptionPane.YES_NO_OPTION);
						if (i == JOptionPane.YES_OPTION){
							cn.bajaEmpleado(emp);;
							JOptionPane.showMessageDialog(null, "La baja ha sido correcta.","Información",JOptionPane.INFORMATION_MESSAGE);
							llenaEmpleados();
						}
					} catch (insertDBException e) {
						JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				cn.close();
				
				textFieldApellido.setText("");
				textFieldDoc.setText("");
				textFieldFechaNacimiento.setText("");
				textFieldMatricula.setText("");
				textFieldNombre.setText("");
				textFieldTipoDoc.setText("");
				textFieldDireccion.setText("");
				textFieldTelefono.setText("");
				
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(281, 334, 89, 23);
		contentPane.add(btnEliminar);
	}
	
	public void llenaEmpleados(){
		ArrayList<Empleado> aEmpleado = new ArrayList<Empleado>();
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			aEmpleado = cn.devolverEmpleados();
			Iterator<Empleado> it = aEmpleado.iterator();
			Empleado tmp = new Empleado();
			empleados = new DefaultComboBoxModel();
			comboBoxEmpleado.setModel(empleados);
			while(it.hasNext()){
				tmp = it.next();
				empleados.addElement(tmp.getId() + " - " + tmp.getNombre() + " " + tmp.getApellido());
			}
		}else {
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
		cn.close();
	}

}
