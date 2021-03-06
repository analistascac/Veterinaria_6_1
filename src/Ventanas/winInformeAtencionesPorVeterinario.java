package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clases.Atencion;
import Clases.Empleado;
import Conexion.Conexion;
import Main.Main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.Color;

public class winInformeAtencionesPorVeterinario extends JFrame {

	private static final long serialVersionUID = 7878778577956640625L;
	private JPanel contentPane;
	private JComboBox cmbVeterinarios;
	private DefaultListModel ides = new DefaultListModel();
	private DefaultListModel clientes = new DefaultListModel();
	private DefaultListModel tipoconsultas = new DefaultListModel();
	private DefaultListModel fechas = new DefaultListModel();
	private DefaultComboBoxModel veterinarios = new DefaultComboBoxModel();
	private JTextField textFieldCantidad;

	public winInformeAtencionesPorVeterinario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(winInformeAtencionesPorVeterinario.class.getResource("/Images/logo.jpg")));
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent arg0) {
				Main ventana = new Main();
				ventana.setVisible(true);
				dispose();
			}
		});
		setResizable(false);
		setTitle("Veterinaria Godzilla - Informe - Atenciones por veterinario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 129);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblVeterinarios = new JLabel("Veterinario:");
		lblVeterinarios.setHorizontalAlignment(SwingConstants.LEFT);
		lblVeterinarios.setBounds(10, 27, 100, 14);
		contentPane.add(lblVeterinarios);
		
		JLabel lblIdentificador = new JLabel("Cantidad Atenciones:");
		lblIdentificador.setBounds(10, 61, 138, 14);
		contentPane.add(lblIdentificador);
		
		textFieldCantidad = new JTextField();
		textFieldCantidad.setBounds(158, 52, 276, 23);
		contentPane.add(textFieldCantidad);
		textFieldCantidad.setColumns(10);
		
		cmbVeterinarios = new JComboBox();
		cmbVeterinarios.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				int cantidad;

				Empleado emp = new Empleado();
				Conexion cn = new Conexion();
				if (cn.conectarDB()){
					emp = cn.devolverEmpleados().get(cmbVeterinarios.getSelectedIndex());
					cantidad = pedirAtenciones(emp);
					textFieldCantidad.setText(String.valueOf(cantidad));
					cn.close();
				} else {
					JOptionPane.showMessageDialog(null, "Error en la conexion de base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
			
			}
		});
		cmbVeterinarios.setModel(veterinarios);
		llenarVeterinarios();
		cmbVeterinarios.setBounds(158, 24, 276, 17);
		contentPane.add(cmbVeterinarios);
	}

	private void llenarVeterinarios() {
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			ArrayList<Empleado> aVeterinario = new ArrayList<Empleado>();
			aVeterinario = cn.devolverVeterinarios();
			Iterator<Empleado> it = aVeterinario.iterator();
			Empleado tmp = new Empleado();
			while(it.hasNext()){
				tmp = it.next();
				veterinarios.addElement(tmp.getDocumento());
			}
		}else{
			JOptionPane.showMessageDialog(null, "Error en la conexion de base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private int pedirAtenciones(Empleado emp) {
		int cantidad = 0;
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			cantidad = cn.informeMascotasPorVeterinario(emp);
		}

		return cantidad;

	}
}
