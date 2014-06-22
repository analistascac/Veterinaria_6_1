package Ventanas;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
	private JLabel lblFecha;
	private JLabel lblDia;
	private JLabel lblMes;
	private JLabel lblAnio;
	private JComboBox<String> cmbDia;
	private JComboBox<String> cmbMes;
	private JComboBox<String> cmbAnio;
	private JComboBox<String> cmbTipoConsulta;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private TFecha fecha;
	private JComboBox<Item> cmbCliente;
	private JComboBox<Item> cmbVeterinario;
	private JComboBox<Item> cmbMascota;

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
		setBounds(100, 100, 422, 319);
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

		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(10, 181, 131, 21);
		contentPane.add(lblFecha);

		lblDia = new JLabel("Dia");
		lblDia.setBounds(10, 212, 23, 21);
		contentPane.add(lblDia);

		lblMes = new JLabel("Mes");
		lblMes.setBounds(139, 213, 23, 21);
		contentPane.add(lblMes);

		lblAnio = new JLabel("Año");
		lblAnio.setBounds(282, 213, 23, 21);
		contentPane.add(lblAnio);

		cmbCliente = new JComboBox<Item>();
		cmbCliente.setBounds(150, 11, 247, 21);
		contentPane.add(cmbCliente);

		cmbVeterinario = new JComboBox<Item>();
		cmbVeterinario.setBounds(150, 43, 247, 21);
		contentPane.add(cmbVeterinario);

		cmbTipoConsulta = new JComboBox<String>();
		cmbTipoConsulta
				.setModel(new DefaultComboBoxModel<String>(new String[] {
						"Consulta General", "Operacion",
						"Aplicacion de medicamentos" }));
		cmbTipoConsulta.setBounds(151, 75, 246, 21);
		contentPane.add(cmbTipoConsulta);

		cmbMascota = new JComboBox<Item>();
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

		cmbDia = new JComboBox<String>();
		cmbDia.setBounds(35, 213, 82, 21);
		contentPane.add(cmbDia);

		cmbMes = new JComboBox<String>();
		cmbMes.setBounds(172, 213, 82, 21);
		contentPane.add(cmbMes);

		cmbAnio = new JComboBox<String>();
		cmbAnio.setBounds(315, 213, 82, 21);
		contentPane.add(cmbAnio);

		fecha = new TFecha(cmbDia, cmbMes, cmbAnio);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setEnabled(false);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Item item;
				Atencion atencion = new Atencion();

				item = (Item) cmbCliente.getSelectedItem();
				atencion.setIdCliente(item.getId());

				item = (Item) cmbVeterinario.getSelectedItem();
				atencion.setIdVeterinario(item.getId());
				
				item = (Item) cmbMascota.getSelectedItem();
				atencion.setIdMascota(item.getId());

				atencion.setTipoConsulta((String) cmbTipoConsulta
						.getSelectedItem());
				
				atencion.setDiagnostico(txtDiagnostico.getText());
				
				atencion.setFecha(fecha.getFechaString());
				
				try {
					Conexion conexion = new Conexion();
					if (conexion.conectarDB()) {

						conexion.altaAtencion(atencion);
						conexion.close();

						Main frame = new Main();
						frame.setVisible(true);

						dispose();
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e, "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnAceptar.setBounds(308, 245, 89, 23);
		contentPane.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main frame = new Main();
				frame.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(209, 245, 89, 23);
		contentPane.add(btnCancelar);

		llenarCombos();

	}

	private void llenarCombos() {

		try {

			Conexion conexion = new Conexion();

			if (conexion.conectarDB()) {
				String temp;
				Cliente cliente;
				Empleado empleado;
				Mascota mascota;

				DefaultComboBoxModel<Item> modeloC = new DefaultComboBoxModel<Item>();
				DefaultComboBoxModel<Item> modeloE = new DefaultComboBoxModel<Item>();
				DefaultComboBoxModel<Item> modeloM = new DefaultComboBoxModel<Item>();

				Iterator<Cliente> itC = conexion.devolverClientes().iterator();

				while (itC.hasNext()) {
					cliente = itC.next();
					modeloC.addElement(new Item(cliente.getId(), cliente
							.getApellido() + " " + cliente.getNombre()));
				}
				cmbCliente.setModel(modeloC);

				Iterator<Empleado> itE = conexion.devolverVeterinarios()
						.iterator();

				while (itE.hasNext()) {
					empleado = itE.next();
					modeloE.addElement(new Item(empleado.getId(), empleado
							.getApellido() + " " + empleado.getNombre()));
				}
				cmbVeterinario.setModel(modeloE);

				Iterator<Mascota> itM = conexion.devolverClienteMascotas()
						.iterator();

				while (itM.hasNext()) {
					mascota = itM.next();

					temp = "Id: " + mascota.getId() + ", Nombre: "
							+ mascota.getDescripcion();

					modeloM.addElement(new Item(mascota.getId(), temp));
				}
				cmbMascota.setModel(modeloM);

			}else{
				JOptionPane.showMessageDialog(null, "Error en la conexion de base de datos.", "Error",JOptionPane.WARNING_MESSAGE);
			}

		} catch (Exception e) {

			System.out.println(e.getStackTrace());

			JOptionPane.showMessageDialog(null, e, "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
