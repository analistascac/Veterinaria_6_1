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
	private JTextField txtImporteTotal;
	private JComboBox<String> cmbVendedor;
	private DefaultComboBoxModel vendedores = new DefaultComboBoxModel();
	private JComboBox<String> cmbCliente;
	private DefaultComboBoxModel clientes = new DefaultComboBoxModel();
	private JComboBox<String> cmbEstadoOperacion;
	private JList lstCarrito;
	private JList lstProductos;
	private DefaultListModel<String> productos = new DefaultListModel();
	private DefaultListModel<String> carrito = new DefaultListModel();
	private JButton btnAgregar;
	private JButton btnQuitar;
	
	private ArrayList<Venta> estoSeVende = new ArrayList();
	private JComboBox<String> cmbTipoFactura;
	
	ArrayList<Producto> prod = new ArrayList();
	
	public frmNuevaVenta() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Main v = new Main();
				v.setVisible(true);
				dispose();
			}
		});
		setTitle("Veterinaria Godzilla - Alta - Venta");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 477, 425);
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
		lblImporteTotal.setBounds(279, 316, 100, 14);
		contentPane.add(lblImporteTotal);

		JLabel lblArticulosVendidos = new JLabel("Articulos disponibles:");
		lblArticulosVendidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblArticulosVendidos.setBounds(10, 132, 170, 14);
		contentPane.add(lblArticulosVendidos);

		JLabel lblEstadoOperacion = new JLabel("Estado operacion:");
		lblEstadoOperacion.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstadoOperacion.setBounds(94, 81, 119, 14);
		contentPane.add(lblEstadoOperacion);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setBounds(190, 159, 87, 14);
		contentPane.add(lblCantidad);

		cmbCliente = new JComboBox();
		cmbCliente.setModel(clientes);
		cmbCliente.setBounds(164, 11, 197, 20);
		contentPane.add(cmbCliente);

		cmbVendedor = new JComboBox();
		cmbVendedor.setModel(vendedores);
		cmbVendedor.setBounds(164, 44, 197, 20);
		contentPane.add(cmbVendedor);

		txtImporteTotal = new JTextField();
		txtImporteTotal.setText("0");
		txtImporteTotal.setColumns(10);
		txtImporteTotal.setBounds(389, 313, 60, 20);
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
		txtCantidad.setBounds(190, 184, 87, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 157, 170, 130);
		contentPane.add(scrollPane);
		
		lstProductos = new JList();
		lstProductos.setModel(productos);
		scrollPane.setViewportView(lstProductos);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(287, 159, 170, 130);
		contentPane.add(scrollPane_1);
		
		lstCarrito = new JList();
		lstCarrito.setModel(carrito);
		scrollPane_1.setViewportView(lstCarrito);

		DefaultComboBoxModel<String> estados = new DefaultComboBoxModel<String>();
		cmbEstadoOperacion = new JComboBox<String>();
		cmbEstadoOperacion.setModel(estados);
		estados.addElement("Abonado");
		estados.addElement("Pendiente de pago");
		cmbEstadoOperacion.setBounds(190, 78, 171, 20);
		contentPane.add(cmbEstadoOperacion);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = JOptionPane.showConfirmDialog(null, "¿Confirma dar de alta la nueva venta?","Confirmación",JOptionPane.YES_NO_OPTION);
				if(x == JOptionPane.YES_OPTION){
					Conexion cn = new Conexion();
					
					if(cn.conectarDB()){
						try {
							cn.altaVenta(estoSeVende);
							JOptionPane.showMessageDialog(null,"La venta ha sido cargada exitosamente","Informacion",JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Error al tratar de dar alta a la venta","Error",JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		btnAceptar.setBounds(137, 363, 89, 23);
		contentPane.add(btnAceptar);

		JLabel lblNewLabel = new JLabel("Art\u00EDculos en el carrito:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(287, 132, 170, 14);
		contentPane.add(lblNewLabel);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(248, 363, 89, 23);
		contentPane.add(btnCancelar);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Conexion cn = new Conexion();
				
				if(cn.conectarDB()){
					Venta venta = new Venta();
					
					String idCliente = cn.devolverClientes().get(cmbCliente.getSelectedIndex()).getId();
					String idVendedor = cn.devolverEmpleados().get(cmbVendedor.getSelectedIndex()).getId();
					String idProducto = prod.get(lstProductos.getSelectedIndex()).getId(); 
					String idProveedor = prod.get(lstProductos.getSelectedIndex()).getIdProveedor();
					String precio = prod.get(lstProductos.getSelectedIndex()).getPrecioVenta()+"";

					if(prod.get(lstProductos.getSelectedIndex()).getCantidad() >= Integer.parseInt(txtCantidad.getText())){
						venta.setCantidad(txtCantidad.getText());
						venta.setEstadoOperacion(cmbEstadoOperacion.getSelectedItem().toString());
						venta.setIdCliente(idCliente);
						venta.setIdProducto(idProducto);
						venta.setIdProveedor(idProveedor);
						venta.setIdVendedor(idVendedor);
						venta.setTipoFactura(cmbTipoFactura.getSelectedItem()+"");
						venta.setPrecio(precio);
						
						estoSeVende.add(venta);
						
						if(prod.get(lstProductos.getSelectedIndex()).getNombre() != null){
							carrito.addElement(prod.get(lstProductos.getSelectedIndex()).getNombre() + " x " + txtCantidad.getText());
							prod.get(lstProductos.getSelectedIndex()).setCantidad(prod.get(lstProductos.getSelectedIndex()).getCantidad() - Integer.parseInt(txtCantidad.getText()));
							productos.setElementAt("x"+ prod.get(lstProductos.getSelectedIndex()).getCantidad() + " " + prod.get(lstProductos.getSelectedIndex()).getNombre() +  " ($ " + prod.get(lstProductos.getSelectedIndex()).getPrecioVenta()+")", lstProductos.getSelectedIndex());
						}else{
							carrito.addElement(prod.get(lstProductos.getSelectedIndex()).getNombreCientifico() + " x " + txtCantidad.getText());
							prod.get(lstProductos.getSelectedIndex()).setCantidad(prod.get(lstProductos.getSelectedIndex()).getCantidad() - Integer.parseInt(txtCantidad.getText()));
							productos.setElementAt("x"+ prod.get(lstProductos.getSelectedIndex()).getCantidad() + " " + prod.get(lstProductos.getSelectedIndex()).getNombreCientifico() +  " ($ " + prod.get(lstProductos.getSelectedIndex()).getPrecioVenta()+")", lstProductos.getSelectedIndex());
						}
						
						float impor = Float.parseFloat(txtImporteTotal.getText());
						impor = impor + cn.devolverProductos().get(lstProductos.getSelectedIndex()).getPrecioVenta() * Integer.parseInt(txtCantidad.getText());
						txtImporteTotal.setText(impor+"");

						btnQuitar.setEnabled(true);
					}else{
						JOptionPane.showMessageDialog(null, "Hay menos articulos de los deseados","Error",JOptionPane.ERROR_MESSAGE);
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Error al intentar conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnAgregar.setEnabled(false);
		btnAgregar.setBounds(188, 215, 89, 23);
		contentPane.add(btnAgregar);
		
		btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				float impor = Float.parseFloat(txtImporteTotal.getText());
				impor = impor - Float.parseFloat(estoSeVende.get(lstCarrito.getSelectedIndex()).getPrecio()) * Float.parseFloat(estoSeVende.get(lstCarrito.getSelectedIndex()).getCantidad());
				int i = 0;
				int cantprod = 0;
				
				txtImporteTotal.setText(impor+"");
				
				String idProductoCarrito = estoSeVende.get(lstCarrito.getSelectedIndex()).getIdProducto();
				
				while(idProductoCarrito != prod.get(i).getId()){
					i++;
				}
				prod.get(i).setCantidad(Integer.parseInt(estoSeVende.get(lstCarrito.getSelectedIndex()).getCantidad())+prod.get(i).getCantidad());

				if(prod.get(i).getNombre() == null){
					productos.setElementAt("x"+ prod.get(lstProductos.getSelectedIndex()).getCantidad() + " " + prod.get(lstProductos.getSelectedIndex()).getNombreCientifico() +  " ($ " + prod.get(lstProductos.getSelectedIndex()).getPrecioVenta()+")", lstProductos.getSelectedIndex());
				}else{
					productos.setElementAt("x"+ prod.get(lstProductos.getSelectedIndex()).getCantidad() + " " + prod.get(lstProductos.getSelectedIndex()).getNombre() +  " ($ " + prod.get(lstProductos.getSelectedIndex()).getPrecioVenta()+")", lstProductos.getSelectedIndex());
				}
				
				
				estoSeVende.remove(lstCarrito.getSelectedIndex());
				carrito.removeElementAt(lstCarrito.getSelectedIndex());
				
				if(carrito.isEmpty()){
					btnQuitar.setEnabled(false);
				}
			}
		});
		btnQuitar.setEnabled(false);
		btnQuitar.setBounds(188, 249, 89, 23);
		contentPane.add(btnQuitar);
		
		cmbTipoFactura = new JComboBox<String>();
		cmbTipoFactura.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C"}));
		cmbTipoFactura.setBounds(200, 106, 171, 20);
		contentPane.add(cmbTipoFactura);
		
		JLabel lblTipoFactura = new JLabel("Tipo factura:");
		lblTipoFactura.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoFactura.setBounds(94, 107, 119, 14);
		contentPane.add(lblTipoFactura);

		llenarCombos();
	}

	private void llenarCombos() {
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			ArrayList<Cliente> cs = new ArrayList();
			cs = cn.devolverClientes();
			for(int i = 0; i<cs.size();i++) clientes.addElement(cs.get(i).getNombre() + " " +cs.get(i).getApellido());
			
			ArrayList<Empleado> ce = new ArrayList();
			ce = cn.devolverEmpleados();
			for(int i = 0;i<ce.size();i++) vendedores.addElement(ce.get(i).getNombre() + " " + ce.get(i).getApellido());
			
			prod = cn.devolverProductos();
			
			for(int i = 0; i < prod.size(); i++){
				if(prod.get(i).getNombre() != null){
					productos.addElement("x"+ prod.get(i).getCantidad() + " " + prod.get(i).getNombre() +  " ($ " + prod.get(i).getPrecioVenta()+")");
				}else{
					productos.addElement("x"+ prod.get(i).getCantidad() + " " + prod.get(i).getNombreCientifico() + " ($ " + prod.get(i).getPrecioVenta()+")");
				}
				
			}
			
		}else{
			JOptionPane.showMessageDialog(null, "Error al intentar conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	private void chequearEstadoBotones() {

		if ((txtCantidad.getText().trim().length() == 0)
				|| (lstProductos.isSelectionEmpty())
				|| (!Auxiliar.isInteger(txtCantidad.getText()))) {
			btnAgregar.setEnabled(false);
		} else {

			btnAgregar.setEnabled(true);
		}

		if (lstCarrito.isSelectionEmpty()) {
			btnQuitar.setEnabled(false);
		} else {

			btnQuitar.setEnabled(true);
		}

	}
}
