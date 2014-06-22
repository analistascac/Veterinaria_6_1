package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Clases.Atencion;
import Main.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class ModificarAtencion extends JFrame {

	private JPanel panelPrincipal;
	private JLabel lblNewLabel;
	private JLabel lblEmpleadoResponsable;
	private JLabel lblMascota;
	private JLabel lblTipoDeConsulta;
	private JLabel lblDiagnostico;
	private JComboBox cmbId;
	private JTextField txtDiagnostico;
	private JButton btnModificar;
	private DefaultComboBoxModel<String> id = new DefaultComboBoxModel();
	private DefaultComboBoxModel<String> veterinario = new DefaultComboBoxModel();
	private DefaultComboBoxModel<String> mascota = new DefaultComboBoxModel();
	private DefaultComboBoxModel<String> tipoconsulta = new DefaultComboBoxModel();
	private DefaultComboBoxModel<String> todoslosmed = new DefaultComboBoxModel();
	private DefaultComboBoxModel<String> clientes = new DefaultComboBoxModel();
	private JComboBox cmdTipoConsulta;
	private JComboBox cmbMascota;
	private JComboBox cmbVeterinario;
	private JComboBox cmbAnio;
	private JComboBox cmbMes;
	private JComboBox cmbDia;
	private DefaultListModel<String> medicamentos = new DefaultListModel();
	private TFecha fecha = null;
	private JComboBox cmbCliente;

	public ModificarAtencion() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				int x = JOptionPane.showConfirmDialog(null,
						"¿Realmente desea salir del panel de modificaciones?",
						"Confirmar", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (x == JOptionPane.YES_OPTION) {
					Main ventana = new Main();
					ventana.setVisible(true);
					dispose();
				}
			}
		});
		setTitle("Modificar Atencion - Centro veterinario CAC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 309, 267);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		lblNewLabel = new JLabel("Identificador:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 11, 133, 14);
		panelPrincipal.add(lblNewLabel);

		lblEmpleadoResponsable = new JLabel("Veterinario:");
		lblEmpleadoResponsable.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmpleadoResponsable.setBounds(10, 36, 133, 14);
		panelPrincipal.add(lblEmpleadoResponsable);

		lblMascota = new JLabel("Mascota:");
		lblMascota.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMascota.setBounds(10, 86, 133, 14);
		panelPrincipal.add(lblMascota);

		lblTipoDeConsulta = new JLabel("Tipo de consulta:");
		lblTipoDeConsulta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDeConsulta.setBounds(10, 111, 133, 14);
		panelPrincipal.add(lblTipoDeConsulta);

		lblDiagnostico = new JLabel("Diagnostico");
		lblDiagnostico.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiagnostico.setBounds(10, 136, 133, 14);
		panelPrincipal.add(lblDiagnostico);

		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Atencion atencion = new Atencion();

				atencion.setId((String) cmbId.getSelectedItem());
				atencion.setIdVeterinario((String) cmbVeterinario
						.getSelectedItem());
				atencion.setIdCliente((String) cmbCliente.getSelectedItem());
				atencion.setIdMascota((String) cmbMascota.getSelectedItem());
				atencion.setTipoConsulta((String) cmdTipoConsulta
						.getSelectedItem());
				atencion.setDiagnostico(txtDiagnostico.getText());
				atencion.setFecha(cmbDia.getSelectedItem() + "/"
						+ cmbMes.getSelectedItem() + "/"
						+ cmbAnio.getSelectedItem());

				JOptionPane.showMessageDialog(null, atencion.toString(),
						"Información", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnModificar.setBounds(197, 197, 89, 23);
		panelPrincipal.add(btnModificar);

		txtDiagnostico = new JTextField();
		txtDiagnostico.setEnabled(false);
		txtDiagnostico.setBounds(153, 136, 133, 17);
		panelPrincipal.add(txtDiagnostico);
		txtDiagnostico.setColumns(10);

		cmbId = new JComboBox();
		llenarIdentificadores();
		cmbId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// PASARLE LA ID SELECCIONADA A LOS DATOS Y QUE ESTE ME DEVUELVA
				// UN OBJETO CON LO QUE HACE FALTA PARA LLENAR LOS TEXT.
				cmbVeterinario.setEnabled(true);
				cmbMascota.setEnabled(true);
				cmdTipoConsulta.setEnabled(true);
				txtDiagnostico.setEnabled(true);
				btnModificar.setEnabled(true);
				cmbDia.setEnabled(true);
				cmbMes.setEnabled(true);
				cmbAnio.setEnabled(true);
				cmbCliente.setEnabled(true);

				llenarVeterinarios();
				llenarMascotas();
				llenarClientes();

			}
		});
		cmbId.setBounds(153, 8, 133, 20);
		cmbId.setModel(id);
		panelPrincipal.add(cmbId);

		cmbVeterinario = new JComboBox();
		cmbVeterinario.setEnabled(false);
		cmbVeterinario.setBounds(153, 33, 133, 20);
		cmbVeterinario.setModel(veterinario);
		panelPrincipal.add(cmbVeterinario);

		cmbMascota = new JComboBox();
		cmbMascota.setEnabled(false);
		cmbMascota.setBounds(153, 86, 133, 20);
		cmbMascota.setModel(mascota);
		panelPrincipal.add(cmbMascota);

		cmdTipoConsulta = new JComboBox();
		cmdTipoConsulta.setEnabled(false);
		cmdTipoConsulta.setBounds(153, 111, 133, 20);
		cmdTipoConsulta.setModel(tipoconsulta);
		tipoconsulta.addElement("Atencion en consultorio");
		tipoconsulta.addElement("Atencion particular");
		panelPrincipal.add(cmdTipoConsulta);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCliente.setBounds(10, 60, 133, 14);
		panelPrincipal.add(lblCliente);

		cmbCliente = new JComboBox();
		cmbCliente.setEnabled(false);
		cmbCliente.setBounds(153, 60, 133, 20);
		cmbCliente.setModel(clientes);
		panelPrincipal.add(cmbCliente);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(39, 161, 46, 14);
		panelPrincipal.add(lblFecha);

		cmbDia = new JComboBox();
		cmbDia.setEnabled(false);
		cmbDia.setBounds(80, 162, 50, 23);
		panelPrincipal.add(cmbDia);

		cmbMes = new JComboBox();
		cmbMes.setEnabled(false);
		cmbMes.setBounds(140, 162, 50, 23);
		panelPrincipal.add(cmbMes);

		cmbAnio = new JComboBox();
		cmbAnio.setEnabled(false);
		cmbAnio.setBounds(200, 162, 85, 23);
		panelPrincipal.add(cmbAnio);

		fecha = new TFecha(cmbDia, cmbMes, cmbAnio);
		llenarMedicamentos();
	}

	private void llenarIdentificadores() {
		id.addElement("Prueba 1");
		id.addElement("Prueba 2");
		id.addElement("Prueba 3");
	}

	private void llenarVeterinarios() {
		veterinario.addElement("Veterinario prueba");
	}

	private void llenarMascotas() {
		mascota.addElement("Mascota prueba");
	}

	private void llenarClientes() {
		clientes.addElement("Cliente prueba");
	}

	private void llenarMedicamentos() {
		todoslosmed.addElement("Medicamento prueba 1");
		todoslosmed.addElement("Medicamento prueba 2");
		todoslosmed.addElement("Medicamento prueba 3");
	}
}
