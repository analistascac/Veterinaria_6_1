package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clases.Producto;
import Main.Main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class ModificarProducto extends JFrame {

	private JPanel contentPane;
	private DefaultComboBoxModel idproductos = new DefaultComboBoxModel();
	private DefaultComboBoxModel idpmascotas = new DefaultComboBoxModel();
	private JComboBox cmbId;
	private JTextField txtNombre;
	private JTextField txtMedida;
	private JTextField txtNombreCientifico;
	private JTextField txtNombreVulgar;
	private JTextField txtDescripcion;
	private JTextField txtPrecioCosto;
	private JTextField txtPrecioVenta;
	private JTextField txtLimiteInferior;
	private JList lstProveedores;
	private DefaultListModel listaProvedores = new DefaultListModel();
	private JRadioButton rdbtnMascota;
	private JRadioButton rdbtnArticulo;
	private JButton btnAgregar;
	private JButton btnQuitar;
	private JButton btnModificar;
	private Producto prod = new Producto();
	private JComboBox cmbProveedores;
	private DefaultComboBoxModel listaProveedores = new DefaultComboBoxModel();

	public ModificarProducto() {
		setResizable(false);
		setTitle("Modificar un producto - Veterinaria CAC");
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
				} else {
					ModificarProducto ventana = new ModificarProducto();
					ventana.setVisible(true);
					dispose();
				}
			}
		});

		llenaridmascotas();
		llenaridproductos();
		llenarproveedores();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 339, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIdentificador = new JLabel("Identificador:");
		lblIdentificador.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdentificador.setBounds(10, 36, 140, 14);
		contentPane.add(lblIdentificador);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 61, 140, 14);
		contentPane.add(lblNombre);

		JLabel lblMedida = new JLabel("Medida:");
		lblMedida.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMedida.setBounds(10, 86, 140, 14);
		contentPane.add(lblMedida);

		JLabel lblNombreCientifico = new JLabel("Nombre Cientifico:");
		lblNombreCientifico.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreCientifico.setBounds(10, 111, 140, 14);
		contentPane.add(lblNombreCientifico);

		JLabel lblNombreVulgar = new JLabel("Nombre vulgar:");
		lblNombreVulgar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreVulgar.setBounds(10, 136, 140, 14);
		contentPane.add(lblNombreVulgar);

		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setBounds(10, 161, 140, 14);
		contentPane.add(lblDescripcion);

		JLabel lblPrecioCosto = new JLabel("Precio costo:");
		lblPrecioCosto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioCosto.setBounds(10, 186, 140, 14);
		contentPane.add(lblPrecioCosto);

		JLabel lblPrecioVenta = new JLabel("Precio venta:");
		lblPrecioVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioVenta.setBounds(10, 211, 140, 14);
		contentPane.add(lblPrecioVenta);

		JLabel lblLimiteInferior = new JLabel("Limite inferior:");
		lblLimiteInferior.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLimiteInferior.setBounds(10, 236, 140, 14);
		contentPane.add(lblLimiteInferior);

		JLabel lblProveedores = new JLabel("Proveedores:");
		lblProveedores.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProveedores.setBounds(10, 261, 140, 14);
		contentPane.add(lblProveedores);

		JLabel lblTipoProducto = new JLabel("Tipo Producto:");
		lblTipoProducto.setBounds(25, 11, 70, 14);
		contentPane.add(lblTipoProducto);

		ButtonGroup botonesradio = new ButtonGroup();

		rdbtnArticulo = new JRadioButton("Articulo");
		rdbtnArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmbId.setEnabled(true);
				llenarProductoCombo();
				txtNombre.setEnabled(true);
				txtMedida.setEnabled(true);
				txtNombreCientifico.setEnabled(false);
				txtNombreVulgar.setEnabled(false);
				txtDescripcion.setEnabled(true);
				txtPrecioCosto.setEnabled(true);
				txtPrecioVenta.setEnabled(true);
				txtLimiteInferior.setEnabled(true);

			}
		});
		rdbtnArticulo.setBounds(107, 7, 89, 23);
		botonesradio.add(rdbtnArticulo);
		contentPane.add(rdbtnArticulo);

		rdbtnMascota = new JRadioButton("Mascota");
		rdbtnMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmbId.setEnabled(true);
				llenarMascotaCombo();
				txtNombre.setEnabled(true);
				txtMedida.setEnabled(false);
				txtNombreCientifico.setEnabled(true);
				txtNombreVulgar.setEnabled(true);
				txtDescripcion.setEnabled(false);
				txtPrecioCosto.setEnabled(true);
				txtPrecioVenta.setEnabled(true);
				txtLimiteInferior.setEnabled(true);

			}
		});
		rdbtnMascota.setBounds(198, 7, 89, 23);
		botonesradio.add(rdbtnMascota);
		contentPane.add(rdbtnMascota);

		cmbId = new JComboBox();
		cmbId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnModificar.setEnabled(true);
				btnAgregar.setEnabled(true);
				btnQuitar.setEnabled(true);

				prod = pedirProducto((String) cmbId.getSelectedItem());
				txtNombre.setText(prod.getNombre());
				txtMedida.setText(prod.getMedida());
				txtNombreCientifico.setText(prod.getNombreCientifico());
				txtNombreVulgar.setText(prod.getNombreVulgar());
				txtDescripcion.setText(prod.getDescripcion());
				txtPrecioCosto.setText(prod.getPrecioCosto() + "");
				txtPrecioVenta.setText(prod.getPrecioVenta() + "");
				txtLimiteInferior.setText(prod.getLimiteInferior() + "");

				listaProvedores.clear();
				for (int i = 0; i < prod.getProveedores().size() - 1; i++)
					listaProvedores.addElement(prod.getProveedores().get(i));
			}
		});
		cmbId.setEnabled(false);
		cmbId.setBounds(160, 36, 156, 17);
		contentPane.add(cmbId);

		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setBounds(160, 59, 156, 17);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtMedida = new JTextField();
		txtMedida.setEnabled(false);
		txtMedida.setColumns(10);
		txtMedida.setBounds(160, 83, 156, 17);
		contentPane.add(txtMedida);

		txtNombreCientifico = new JTextField();
		txtNombreCientifico.setEnabled(false);
		txtNombreCientifico.setColumns(10);
		txtNombreCientifico.setBounds(160, 108, 156, 17);
		contentPane.add(txtNombreCientifico);

		txtNombreVulgar = new JTextField();
		txtNombreVulgar.setEnabled(false);
		txtNombreVulgar.setColumns(10);
		txtNombreVulgar.setBounds(160, 133, 156, 17);
		contentPane.add(txtNombreVulgar);

		txtDescripcion = new JTextField();
		txtDescripcion.setEnabled(false);
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(160, 158, 156, 17);
		contentPane.add(txtDescripcion);

		txtPrecioCosto = new JTextField();
		txtPrecioCosto.setEnabled(false);
		txtPrecioCosto.setColumns(10);
		txtPrecioCosto.setBounds(160, 183, 156, 17);
		contentPane.add(txtPrecioCosto);

		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setEnabled(false);
		txtPrecioVenta.setColumns(10);
		txtPrecioVenta.setBounds(160, 208, 156, 17);
		contentPane.add(txtPrecioVenta);

		txtLimiteInferior = new JTextField();
		txtLimiteInferior.setEnabled(false);
		txtLimiteInferior.setColumns(10);
		txtLimiteInferior.setBounds(160, 233, 156, 17);
		contentPane.add(txtLimiteInferior);

		cmbProveedores = new JComboBox();
		cmbProveedores.setBounds(10, 278, 140, 23);
		cmbProveedores.setModel(listaProveedores);
		contentPane.add(cmbProveedores);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(160, 261, 156, 108);
		contentPane.add(scrollPane);

		lstProveedores = new JList();
		lstProveedores.setModel(listaProvedores);
		scrollPane.setViewportView(lstProveedores);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!listaProvedores.contains(listaProveedores
						.getSelectedItem())) {
					listaProvedores.addElement(listaProveedores
							.getSelectedItem());
				}
			}
		});
		btnAgregar.setEnabled(false);
		btnAgregar.setBounds(61, 312, 89, 23);
		contentPane.add(btnAgregar);

		btnQuitar = new JButton("Quitar");
		btnQuitar.setEnabled(false);
		btnQuitar.setBounds(61, 346, 89, 23);
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listaProvedores.getSize() > 0
						&& lstProveedores.getSelectedIndex() > -1) {
					listaProvedores.remove(lstProveedores.getSelectedIndex());
				}
			}
		});
		contentPane.add(btnQuitar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Producto prod = new Producto();
				ArrayList prov = new ArrayList();
				try {

					prod.setNombre(txtNombre.getText());
					prod.setId((String) cmbId.getSelectedItem());
					if (rdbtnArticulo.isSelected()) {
						prod.setDescripcion(txtDescripcion.getText());
						prod.setMedida(txtMedida.getText());
					} else {
						prod.setNombreCientifico(txtNombreCientifico.getText());
						prod.setNombreVulgar(txtNombreVulgar.getText());
					}

					prod.setLimiteInferior(Integer.parseInt(txtLimiteInferior
							.getText()));
					prod.setPrecioCosto(Integer.parseInt(txtPrecioCosto
							.getText()));
					prod.setPrecioVenta(Integer.parseInt(txtPrecioVenta
							.getText()));

					for (int i = 0; i < listaProvedores.size(); i++) {
						prov.add(listaProvedores.get(i));
					}
					prod.setProveedores(prov);

					JOptionPane.showMessageDialog(null, prod.toString());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Error en la carga de datos", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnModificar.setEnabled(false);
		btnModificar.setBounds(227, 380, 89, 23);
		contentPane.add(btnModificar);
	}

	private void llenarProductoCombo() {
		cmbId.setModel(idproductos);
	}

	private void llenarMascotaCombo() {
		cmbId.setModel(idpmascotas);
	}

	private void llenaridmascotas() {
		for (int i = 0; i < 5; i++)
			idpmascotas.addElement("Mascota venta prueba" + i);
	}

	private void llenaridproductos() {
		for (int i = 0; i < 5; i++)
			idproductos.addElement("producto venta prueba" + i);
	}

	private void llenarproveedores() {
		listaProveedores.addElement("Proveedor prueba 1");
		listaProveedores.addElement("Proveedor prueba 2");
		listaProveedores.addElement("Proveedor prueba 3");
	}

	private Producto pedirProducto(String id) {
		Producto prod = new Producto();
		ArrayList<String> proveedores = new ArrayList();

		// pedirle a la base de datos
		prod.setDescripcion("Descripcion prueba");
		prod.setId("Campo prueba");
		prod.setNombre("Campo prueba");
		prod.setMedida("Campo prueba");
		prod.setNombreCientifico("Campo prueba");
		prod.setNombreVulgar("Campo prueba");
		prod.setDescripcion("Campo prueba");
		prod.setPrecioCosto(12);
		prod.setPrecioVenta(12);
		prod.setLimiteInferior(5);
		proveedores.add("Provedor prueba 1");
		proveedores.add("Provedor prueba 2");
		proveedores.add("Provedor prueba 3");
		prod.setProveedores(proveedores);

		return prod;
	}
}
