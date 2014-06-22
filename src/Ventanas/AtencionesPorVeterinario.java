package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clases.Atencion;
import Main.Main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AtencionesPorVeterinario extends JFrame {

	private static final long serialVersionUID = 7878778577956640625L;
	private JPanel contentPane;
	private JList lstFechas;
	private JList lstIds;
	private JList lstClientes;
	private JList lstTipoConsultas;
	private JComboBox cmbVeterinarios;
	private DefaultListModel ides = new DefaultListModel();
	private DefaultListModel clientes = new DefaultListModel();
	private DefaultListModel tipoconsultas = new DefaultListModel();
	private DefaultListModel fechas = new DefaultListModel();
	private DefaultComboBoxModel veterinarios = new DefaultComboBoxModel();

	public AtencionesPorVeterinario() {
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent arg0) {
				Main ventana = new Main();
				ventana.setVisible(true);
				dispose();
			}
		});
		setResizable(false);
		setTitle("Atenciones de un veterinario - Veterinaria CAC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblVeterinarios = new JLabel("Veterinarios:");
		lblVeterinarios.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVeterinarios.setBounds(10, 11, 100, 14);
		contentPane.add(lblVeterinarios);

		cmbVeterinarios = new JComboBox();
		cmbVeterinarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ides.clear();
				clientes.clear();
				tipoconsultas.clear();
				fechas.clear();
				ArrayList<Atencion> atenciones = new ArrayList();

				atenciones = pedirAtenciones((String) cmbVeterinarios
						.getSelectedItem());

				for (int i = 0; i < atenciones.size(); i++) {
					fechas.addElement(atenciones.get(i).getFecha());
					tipoconsultas.addElement(atenciones.get(i)
							.getTipoConsulta());
					clientes.addElement(atenciones.get(i).getIdCliente());
					ides.addElement(atenciones.get(i).getId());
				}
			}
		});
		cmbVeterinarios.setModel(veterinarios);
		llenarVeterinarios();
		cmbVeterinarios.setBounds(120, 8, 314, 17);
		contentPane.add(cmbVeterinarios);

		JLabel lblIdentificador = new JLabel("Identificador");
		lblIdentificador.setBounds(10, 36, 100, 14);
		contentPane.add(lblIdentificador);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(120, 36, 100, 14);
		contentPane.add(lblFecha);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(230, 36, 100, 14);
		contentPane.add(lblCliente);

		JLabel lblTipoDeConsulta = new JLabel("Tipo de consulta");
		lblTipoDeConsulta.setBounds(340, 36, 100, 14);
		contentPane.add(lblTipoDeConsulta);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 110, 243);
		contentPane.add(scrollPane);

		lstIds = new JList();
		lstIds.setModel(ides);
		scrollPane.setViewportView(lstIds);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(120, 52, 110, 243);
		contentPane.add(scrollPane_1);

		lstFechas = new JList();
		lstFechas.setModel(fechas);
		scrollPane_1.setViewportView(lstFechas);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(230, 52, 110, 243);
		contentPane.add(scrollPane_2);

		lstClientes = new JList();
		lstClientes.setModel(clientes);
		scrollPane_2.setViewportView(lstClientes);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(340, 52, 100, 243);
		contentPane.add(scrollPane_3);

		lstTipoConsultas = new JList();
		lstTipoConsultas.setModel(tipoconsultas);
		scrollPane_3.setViewportView(lstTipoConsultas);
	}

	private void llenarVeterinarios() {
		veterinarios.addElement("Veterinario prueba 1");
		veterinarios.addElement("Veterinario prueba 2");
		veterinarios.addElement("Veterinario prueba 3");
		veterinarios.addElement("Veterinario prueba 4");
		veterinarios.addElement("Veterinario prueba 5");
	}

	private ArrayList<Atencion> pedirAtenciones(String idVeterinario) {
		ArrayList<Atencion> atenciones = new ArrayList();
		for (int i = 0; i < 5; i++) {
			Atencion ate = new Atencion();
			ate.setIdCliente("Cliente prueba " + i + 1);
			ate.setDiagnostico("Diagnostico prueba " + i + 1);
			ate.setFecha("1/1/2014");
			ate.setId(i + "");
			ate.setIdMascota("Mascota prueba" + i + 1);
			ate.setTipoConsulta("Tipo consulta prueba");
			atenciones.add(ate);
		}

		return atenciones;

	}
}
