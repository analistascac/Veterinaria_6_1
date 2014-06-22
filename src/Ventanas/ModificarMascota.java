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

import Clases.Mascota;
import Main.Main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ModificarMascota extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreCientifico;
	private JTextField txtNombreVulgar;
	private JTextField txtDescripcion;
	private JButton btnModificar;
	private JComboBox cmbDueno;
	private DefaultComboBoxModel duenos = new DefaultComboBoxModel();
	private JComboBox cmbId;
	private DefaultComboBoxModel ides = new DefaultComboBoxModel();

	public ModificarMascota() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Main ventana = new Main();
				ventana.setVisible(true);
				dispose();
			}
		});
		setTitle("Modificar mascota - Veterinaria CAC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 330, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIdentificador = new JLabel("Identificador:");
		lblIdentificador.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdentificador.setBounds(10, 11, 122, 14);
		contentPane.add(lblIdentificador);

		cmbId = new JComboBox();
		llenarids();
		cmbId.setModel(ides);
		cmbId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtDescripcion.setEnabled(true);
				txtNombreCientifico.setEnabled(true);
				txtNombreVulgar.setEnabled(true);
				cmbDueno.setEnabled(true);
				btnModificar.setEnabled(true);
				llenarduenos();

				Mascota mas = new Mascota();
				mas = obtenerMascota();

				txtDescripcion.setText(mas.getDescripcion());
				txtNombreCientifico.setText(mas.getNombreCientifico());
				txtNombreVulgar.setText(mas.getNombreVulgar());
				duenos.setSelectedItem(duenos.getElementAt(duenos
						.getIndexOf(mas.getIdCliente())));

			}
		});
		cmbId.setBounds(142, 8, 162, 17);
		contentPane.add(cmbId);

		JLabel lblNombre = new JLabel("Due\u00F1o:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 36, 122, 14);
		contentPane.add(lblNombre);

		JLabel lblNombreCientifico = new JLabel("Nombre cientifico:");
		lblNombreCientifico.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreCientifico.setBounds(10, 61, 122, 14);
		contentPane.add(lblNombreCientifico);

		JLabel lblNombreVulgar = new JLabel("Nombre vulgar:");
		lblNombreVulgar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreVulgar.setBounds(10, 86, 122, 14);
		contentPane.add(lblNombreVulgar);

		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setBounds(10, 111, 122, 14);
		contentPane.add(lblDescripcion);

		cmbDueno = new JComboBox();
		cmbDueno.setModel(duenos);
		cmbDueno.setEnabled(false);
		cmbDueno.setBounds(142, 36, 162, 17);
		contentPane.add(cmbDueno);

		txtNombreCientifico = new JTextField();
		txtNombreCientifico.setEnabled(false);
		txtNombreCientifico.setBounds(142, 58, 162, 17);
		contentPane.add(txtNombreCientifico);
		txtNombreCientifico.setColumns(10);

		txtNombreVulgar = new JTextField();
		txtNombreVulgar.setEnabled(false);
		txtNombreVulgar.setColumns(10);
		txtNombreVulgar.setBounds(142, 83, 162, 17);
		contentPane.add(txtNombreVulgar);

		txtDescripcion = new JTextField();
		txtDescripcion.setEnabled(false);
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(142, 108, 162, 17);
		contentPane.add(txtDescripcion);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mascota mas = new Mascota();

				mas.setId((String) cmbId.getSelectedItem());
				mas.setDescripcion(txtDescripcion.getText());
				mas.setIdCliente((String) cmbDueno.getSelectedItem());
				mas.setNombreCientifico(txtNombreCientifico.getText());
				mas.setNombreVulgar(txtNombreVulgar.getText());

				JOptionPane.showMessageDialog(null, mas.toString(),
						"Informacion", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnModificar.setEnabled(false);
		btnModificar.setBounds(215, 136, 89, 23);
		contentPane.add(btnModificar);
	}

	private void llenarids() {
		ides.addElement("Id prueba 1");
		ides.addElement("Id prueba 2");
		ides.addElement("Id prueba 3");
	}

	private Mascota obtenerMascota() {
		Mascota mas = new Mascota();
		mas.setDescripcion("Descripcion OP de prueba");
		mas.setIdCliente("Dueño prueba 1");
		mas.setId("Identificador 1");
		mas.setNombreCientifico("Nombre cientifico de pruebas");
		mas.setNombreVulgar("Nombre vulgar de pruebas");
		return mas;
	}

	private void llenarduenos() {
		duenos.addElement("Dueño prueba 1");
		duenos.addElement("Dueño prueba 2");
		duenos.addElement("Dueño prueba 3");
	}
}
