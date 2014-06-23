package Ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Clases.*;
import Conexion.Conexion;
import Main.Main;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class frmNuevaVenta extends JFrame {

	private static final long serialVersionUID = 1177401976702383846L;
	private JPanel contentPane;
	private JTextField txtCantidad;

	private JList<Item> lstArticulos;
	private JList<Item> lstArticulosEnCarrito;

	private DefaultListModel<Item> modArticulos;
	private DefaultListModel<Item> modArticulosEnCarrito = null;

	private JButton btnQuitar;
	private JButton btnAgregar;
	private JTextField txtImporteTotal;

	private JComboBox<Item> cmbVendedor;

	private JComboBox<Item> cmbCliente;

	private JComboBox<String> cmbEstadoOperacion;
	private Venta venta = new Venta();

	public frmNuevaVenta() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Main v = new Main();
				v.setVisible(true);
				dispose();
			}
		});
		setTitle("Nueva venta - Veterinaria CAC");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 477, 414);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblCliente.setBounds(94, 14, 60, 14);
		contentPane.add(lblCliente);

		JLabel lblVendedor = new JLabel("Vendedor:");
		lblVendedor.setHorizontalAlignment(SwingConstants.LEFT);
		lblVendedor.setBounds(94, 47, 60, 14);
		contentPane.add(lblVendedor);

		JLabel lblImporteTotal = new JLabel("Importe total:");
		lblImporteTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImporteTotal.setBounds(279, 305, 100, 14);
		contentPane.add(lblImporteTotal);

		JLabel lblArticulosVendidos = new JLabel("Articulos disponibles:");
		lblArticulosVendidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblArticulosVendidos.setBounds(10, 121, 170, 14);
		contentPane.add(lblArticulosVendidos);

		JLabel lblEstadoOperacion = new JLabel("Estado operacion:");
		lblEstadoOperacion.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstadoOperacion.setBounds(94, 81, 119, 14);
		contentPane.add(lblEstadoOperacion);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setBounds(190, 148, 87, 14);
		contentPane.add(lblCantidad);

		cmbCliente = new JComboBox<Item>();
		cmbCliente.setBounds(164, 11, 197, 20);
		contentPane.add(cmbCliente);

		cmbVendedor = new JComboBox<Item>();
		cmbVendedor.setBounds(164, 44, 197, 20);
		contentPane.add(cmbVendedor);

		txtImporteTotal = new JTextField();
		txtImporteTotal.setText("0");
		txtImporteTotal.setColumns(10);
		txtImporteTotal.setBounds(389, 302, 60, 20);
		contentPane.add(txtImporteTotal);

		txtCantidad = new JTextField();

		txtCantidad.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent arg0) {

				if (Auxiliar.isInteger(txtCantidad.getText())) {

					txtCantidad.setForeground(Color.black);
					chequearEstadoBotones();
				} else {
					txtCantidad.setForeground(Color.red);
					chequearEstadoBotones();
				}

			}
		});
		txtCantidad.setBounds(190, 173, 87, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);

		lstArticulos = new JList<Item>();
		lstArticulos.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent arg0) {

				chequearEstadoBotones();

			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 146, 170, 130);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(lstArticulos);

		modArticulosEnCarrito = new DefaultListModel<Item>();

		btnAgregar = new JButton("Agregar");
		btnAgregar.setEnabled(false);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Item item;
				float suma;

				if ((!txtCantidad.getText().trim().isEmpty())
						&& (!lstArticulos.isSelectionEmpty())) {

					item = lstArticulos.getSelectedValue();
					item.setDescripcion3(txtCantidad.getText());
					modArticulosEnCarrito.addElement(item);
					lstArticulosEnCarrito.setModel(modArticulosEnCarrito);

					modArticulos.removeElement(item);
					lstArticulos.setModel(modArticulos);

					lstArticulos.setSelectedValue(null, true);

					suma = Float.parseFloat(txtImporteTotal.getText())
							+ (

							Float.parseFloat(item.getDescripcion1()) * Integer
									.parseInt(txtCantidad.getText()));

					txtImporteTotal.setText(String.valueOf(suma));

					txtCantidad.setText(null);

				}
			}
		});
		btnAgregar.setBounds(190, 204, 87, 23);
		contentPane.add(btnAgregar);

		btnQuitar = new JButton("Quitar");
		btnQuitar.setEnabled(false);
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Item item;

				if (!lstArticulosEnCarrito.isSelectionEmpty()) {

					item = lstArticulosEnCarrito.getSelectedValue();

					modArticulos.addElement(item);
					lstArticulos.setModel(modArticulos);

					modArticulosEnCarrito.removeElement(item);
					lstArticulosEnCarrito.setModel(modArticulosEnCarrito);

					lstArticulosEnCarrito.setSelectedValue(null, true);

				}

			}

		});
		btnQuitar.setBounds(190, 238, 87, 23);
		contentPane.add(btnQuitar);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(287, 148, 170, 130);
		contentPane.add(scrollPane_1);

		lstArticulosEnCarrito = new JList<Item>();
		lstArticulosEnCarrito
				.addListSelectionListener(new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent arg0) {

						chequearEstadoBotones();

					}
				});
		lstArticulosEnCarrito
				.addListSelectionListener(new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent arg0) {

						lstArticulos.setSelectedValue(null, true);

						chequearEstadoBotones();

					}
				});
		scrollPane_1.setViewportView(lstArticulosEnCarrito);
		lstArticulosEnCarrito.setModel(modArticulosEnCarrito);

		DefaultComboBoxModel<String> estados = new DefaultComboBoxModel<String>();
		cmbEstadoOperacion = new JComboBox<String>();
		cmbEstadoOperacion.setModel(estados);
		estados.addElement("Abonado");
		estados.addElement("Pendiente de pago");
		cmbEstadoOperacion.setBounds(223, 75, 138, 20);
		contentPane.add(cmbEstadoOperacion);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					int i;
					Item item = new Item();

					ArrayList<Venta> aVentas = new ArrayList<Venta>();

					if (lstArticulosEnCarrito.getModel().getSize() > 0) {

						Venta venta = new Venta();

						Conexion conexion = new Conexion();

						if (conexion.conectarDB()) {

							for (i = 0; i < lstArticulosEnCarrito.getModel()
									.getSize(); i++) {

								venta.setCantidad(lstArticulosEnCarrito
										.getModel().getElementAt(i)
										.getDescripcion3());

								venta.setEstadoOperacion(cmbEstadoOperacion
										.getSelectedItem().toString());

								item = (Item) cmbCliente.getSelectedItem();
								venta.setIdCliente(item.getId());

								venta.setIdProducto(lstArticulosEnCarrito
										.getModel().getElementAt(i).getId());

								venta.setIdProveedor(lstArticulosEnCarrito
										.getModel().getElementAt(i)
										.getDescripcion2());

								item = (Item) cmbVendedor.getSelectedItem();
								venta.setIdVendedor(item.getId());

								venta.setPrecio(lstArticulosEnCarrito
										.getModel().getElementAt(i)
										.getDescripcion1());

								venta.setTipoFactura(cmbEstadoOperacion
										.getSelectedItem().toString());

								aVentas.add(venta);

							}

							conexion.altaVenta(aVentas);

							conexion.close();

							dispose();

						}

					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Error en la carga del importe.", "Error",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnAceptar.setBounds(137, 352, 89, 23);
		contentPane.add(btnAceptar);

		JLabel lblNewLabel = new JLabel("Art\u00EDculos en el carrito:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(287, 121, 170, 14);
		contentPane.add(lblNewLabel);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(248, 352, 89, 23);
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
				Producto producto;
				Item item = new Item();

				DefaultComboBoxModel<Item> modeloC = new DefaultComboBoxModel<Item>();
				DefaultComboBoxModel<Item> modeloE = new DefaultComboBoxModel<Item>();

				modArticulos = new DefaultListModel<Item>();

				Iterator<Cliente> itC = conexion.devolverClientes().iterator();

				while (itC.hasNext()) {
					cliente = itC.next();
					modeloC.addElement(new Item(cliente.getId(), cliente
							.getApellido() + " " + cliente.getNombre()));
				}
				cmbCliente.setModel(modeloC);

				Iterator<Empleado> itE = conexion.devolverEmpleados()
						.iterator();

				while (itE.hasNext()) {
					empleado = itE.next();
					modeloE.addElement(new Item(empleado.getId(), empleado
							.getApellido() + " " + empleado.getNombre()));
				}
				cmbVendedor.setModel(modeloE);

				Iterator<Producto> itP = conexion.devolverProductos()
						.iterator();

				while (itP.hasNext()) {

					producto = itP.next();

					temp = producto.getNombre() + " "
							+ producto.getDescripcion() + " $"
							+ producto.getPrecioVenta();

					item.setId(producto.getId());
					item.setTexto(temp);
					item.setDescripcion1(String.valueOf(producto
							.getPrecioVenta()));
					item.setDescripcion2(producto.getIdProveedor());

					modArticulos.addElement(item);
				}
				lstArticulos.setModel(modArticulos);

			} else {
				JOptionPane.showMessageDialog(null,
						"Error en la conexion de base de datos.", "Error",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (Exception e) {

			System.out.println(e.getStackTrace());

			JOptionPane.showMessageDialog(null, e, "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void chequearEstadoBotones() {

		if ((txtCantidad.getText().trim().length() == 0)
				|| (lstArticulos.isSelectionEmpty())
				|| (!Auxiliar.isInteger(txtCantidad.getText()))) {
			btnAgregar.setEnabled(false);
		} else {

			btnAgregar.setEnabled(true);
		}

		if (lstArticulosEnCarrito.isSelectionEmpty()) {
			btnQuitar.setEnabled(false);
		} else {

			btnQuitar.setEnabled(true);
		}

	}
}
