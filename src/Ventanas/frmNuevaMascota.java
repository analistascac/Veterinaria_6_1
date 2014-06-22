package Ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import Clases.*;
import Conexion.Conexion;
import Main.Main;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class frmNuevaMascota extends JFrame {

	private static final long serialVersionUID = -8866901335554847264L;
	private JPanel contentPane;
	private JTextField txtNombreCientifico;
	private JTextField txtNombreVulgar;
	private JTextField txtDescripcion;
	private JComboBox<Item> cmbClientes;

	JButton btnAgregar;

	// private DefaultComboBoxModel<String> duenos = new
	// DefaultComboBoxModel<String>();

	public frmNuevaMascota() {
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent arg0) {
				Main v = new Main();
				v.setVisible(true);
				dispose();
			}
		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Nueva mascota - Veterinaria CAC");
		setBounds(100, 100, 317, 199);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Due\u00F1o:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 21, 105, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNombreCientifico = new JLabel("Nombre cientifico:");
		lblNombreCientifico.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreCientifico.setBounds(10, 46, 105, 14);
		contentPane.add(lblNombreCientifico);

		JLabel lblNombreVulgar = new JLabel("Nombre vulgar:");
		lblNombreVulgar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreVulgar.setBounds(10, 71, 105, 14);
		contentPane.add(lblNombreVulgar);

		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setBounds(10, 96, 105, 14);
		contentPane.add(lblDescripcion);

		cmbClientes = new JComboBox<Item>();
		// cmbClientes.setModel(duenos); TODO
		llenarCombos();
		cmbClientes.setBounds(125, 18, 166, 20);
		contentPane.add(cmbClientes);

		txtNombreCientifico = new JTextField();
		txtNombreCientifico.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				estadoDeLosBotones();
			}
		});
		txtNombreCientifico.setBounds(125, 46, 166, 20);
		contentPane.add(txtNombreCientifico);
		txtNombreCientifico.setColumns(10);

		txtNombreVulgar = new JTextField();
		txtNombreVulgar.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				estadoDeLosBotones();
			}
		});
		txtNombreVulgar.setColumns(10);
		txtNombreVulgar.setBounds(125, 71, 166, 20);
		contentPane.add(txtNombreVulgar);

		txtDescripcion = new JTextField();
		txtDescripcion.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				estadoDeLosBotones();
			}
		});
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(125, 96, 166, 20);
		contentPane.add(txtDescripcion);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setEnabled(false);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Item item = (Item) cmbClientes.getSelectedItem();
				Mascota mascota = new Mascota();

				mascota.setIdCliente(item.getId());
				mascota.setNombreCientifico(txtNombreCientifico.getText());
				mascota.setNombreVulgar(txtNombreVulgar.getText());
				mascota.setDescripcion(txtDescripcion.getText());

				try {

					Conexion conexion = new Conexion();
					if (conexion.conectarDB()) {
						conexion.altaClienteMascota(mascota);

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
		btnAgregar.setBounds(43, 127, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main frame = new Main();
				frame.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(156, 127, 89, 23);
		contentPane.add(btnCancelar);
	}

	private void llenarCombos() {

		Conexion conexion = new Conexion();

		if (conexion.conectarDB()) {
			Cliente cliente;
			Iterator<Cliente> iterator = conexion.devolverClientes().iterator();
			DefaultComboBoxModel<Item> modelo = new DefaultComboBoxModel<Item>();

			while (iterator.hasNext()) {
				cliente = iterator.next();
				modelo.addElement(new Item(cliente.getId(), cliente
						.getApellido() + " " + cliente.getNombre()));
			}
			cmbClientes.setModel(modelo);
		}
	}

	private boolean testearCamposDeTexto() {

		return (txtNombreCientifico.getText().length() > 0)
				&& (txtNombreVulgar.getText().length() > 0)
				&& (txtDescripcion.getText().length() > 0);

	}

	private void estadoDeLosBotones() {

		if (testearCamposDeTexto()) {
			btnAgregar.setEnabled(true);
		} else {
			btnAgregar.setEnabled(false);
		}
	}
}
