package Ventanas;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import Clases.*;
import Conexion.Conexion;
import Main.Main;
import Main.TFecha;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class frmNuevaAtencion extends JFrame {

	private static final long serialVersionUID = -5756491180125470195L;
	private JPanel contentPane;
	private JLabel lblTipoConsulta;
	private JLabel lblDiagnostico;
	private JComboBox<String> cmbTipoConsulta;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private TFecha fecha;
	private JComboBox cmbCliente;
	private JComboBox cmbVeterinario;
	private JComboBox cmbMascota;
	private DefaultComboBoxModel clientes = new DefaultComboBoxModel();
	private DefaultComboBoxModel veterinarios = new DefaultComboBoxModel();
	private DefaultComboBoxModel mascotas = new DefaultComboBoxModel();
			

	private JTextArea txtDiagnostico;

	public frmNuevaAtencion() {
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent arg0) {
				Main v = new Main();
				v.setVisible(true);
				dispose();
			}
		});

		setResizable(false);
		setTitle("Nueva Atencion - Veterinaria CAC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 422, 263);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCliente.setBounds(80, 11, 53, 21);
		contentPane.add(lblCliente);

		JLabel lblVeterinario = new JLabel("Veterinario");
		lblVeterinario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVeterinario.setBounds(35, 43, 96, 21);
		contentPane.add(lblVeterinario);

		lblTipoConsulta = new JLabel("Tipo de consulta");
		lblTipoConsulta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoConsulta.setBounds(10, 75, 120, 21);
		contentPane.add(lblTipoConsulta);

		JLabel lblMascota = new JLabel("Mascota");
		lblMascota.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMascota.setBounds(13, 102, 120, 21);
		contentPane.add(lblMascota);

		lblDiagnostico = new JLabel("Diagnostico");
		lblDiagnostico.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiagnostico.setBounds(-8, 134, 141, 24);
		contentPane.add(lblDiagnostico);

		cmbCliente = new JComboBox();
		cmbCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion cn = new Conexion();
				
				if(cn.conectarDB()){
					Cliente clie = new Cliente();
					clie = (Cliente) cn.devolverClientes().get(cmbCliente.getSelectedIndex());
					
					ArrayList<Mascota> mas = new ArrayList();
					mas = cn.devolverClienteMascotas(clie);
					mascotas.removeAllElements();
					
					for(int i = 0; i < mas.size(); i++){
						mascotas.addElement(mas.get(i).getNombreVulgar());
					}
						
					
				}else{
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		cmbCliente.setModel(clientes);
		cmbCliente.setBounds(150, 11, 247, 21);
		contentPane.add(cmbCliente);

		cmbVeterinario = new JComboBox();
		cmbVeterinario.setModel(veterinarios);
		cmbVeterinario.setBounds(150, 43, 247, 21);
		contentPane.add(cmbVeterinario);

		cmbTipoConsulta = new JComboBox<String>();
		cmbTipoConsulta
				.setModel(new DefaultComboBoxModel<String>(new String[] {
						"Consulta General", "Operacion",
						"Aplicacion de medicamentos" }));
		cmbTipoConsulta.setBounds(151, 75, 246, 21);
		contentPane.add(cmbTipoConsulta);

		cmbMascota = new JComboBox();
		cmbMascota.setModel(mascotas);
		cmbMascota.setBounds(151, 104, 246, 21);
		contentPane.add(cmbMascota);

		txtDiagnostico = new JTextArea();
		txtDiagnostico.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {
				if (txtDiagnostico.getText().length() > 0) {
					btnAceptar.setEnabled(true);
				} else {
					btnAceptar.setEnabled(false);
				}

			}
		});
		txtDiagnostico.setLineWrap(true);
		txtDiagnostico.setBounds(151, 134, 246, 53);
		contentPane.add(txtDiagnostico);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setEnabled(false);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					Atencion ate = new Atencion();
					String clie = cn.devolverClientes().get(cmbCliente.getSelectedIndex()).getId();
					String mascota = cn.devolverClienteMascotas(cn.devolverClientes().get(cmbCliente.getSelectedIndex()))
							.get(cmbMascota.getSelectedIndex()).
							getId();
					
					
					ate.setDiagnostico(txtDiagnostico.getText());
					ate.setIdCliente(clie);
					ate.setIdMascota(mascota);
					ate.setIdVeterinario(cn.devolverVeterinarios().get(cmbVeterinario.getSelectedIndex()).getId());
					ate.setTipoConsulta(cmbTipoConsulta.getSelectedItem()+"");
					
					try {
						cn.altaAtencion(ate);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Error al dar de alta una atencion","Error",JOptionPane.ERROR_MESSAGE);
					}
					
					JOptionPane.showMessageDialog(null, "El alta ha sido dado correctamente.","Información",JOptionPane.INFORMATION_MESSAGE);
					
				}else{
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAceptar.setBounds(308, 198, 89, 23);
		contentPane.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		btnCancelar.setBounds(209, 198, 89, 23);
		contentPane.add(btnCancelar);

		llenarCombos();

	}

	private void llenarCombos() {
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			ArrayList<Cliente> clie = new ArrayList();
			ArrayList<Empleado> vete = new ArrayList();
			
			
			clie = cn.devolverClientes();
			vete = cn.devolverVeterinarios();
			
			for(int i = 0; i < clie.size();i++) clientes.addElement(clie.get(i).getNombre() + " " + clie.get(i).getApellido());
			for(int i = 0; i < vete.size();i++) veterinarios.addElement(vete.get(i).getNombre() + " " + vete.get(i).getApellido());
			
			
		}else{
			JOptionPane.showMessageDialog(null, "Error en la conexion de base de datos", "Error",JOptionPane.ERROR_MESSAGE);
		}
		
		cn.close();
		
	}
}
