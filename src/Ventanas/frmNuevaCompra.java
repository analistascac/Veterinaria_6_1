package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import Clases.Compra;
import Clases.Producto;
import Clases.Proveedor;
import Conexion.Conexion;
import Main.Main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;

public class frmNuevaCompra extends JFrame {

	private JPanel contentPane;
	private JTextField txtCantidad;
	private JList lstProductos;
	private JButton btnAgregarAlCanasto;
	private JButton btnQuitarDelCanasto;
	private JComboBox cmbProveedor;
	private DefaultComboBoxModel proveedores = new DefaultComboBoxModel();
	private DefaultComboBoxModel productos = new DefaultComboBoxModel();
	private JComboBox cmbTipoFactura;
	private JComboBox cmbProducto;


	public frmNuevaCompra() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Main v = new Main();
				v.setVisible(true);
				dispose();
			}
		});
		setResizable(false);
		setTitle("Nueva compra - Veterinaria CAC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 359, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Proveedor");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(10, 11, 107, 21);
		contentPane.add(label);
		
		cmbProveedor = new JComboBox();
		cmbProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Proveedor este = new Proveedor();
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					este = (Proveedor) cn.devolverProveedores().get(cmbProveedor.getSelectedIndex());
					llenarProductos(este);
				}else{
					JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos", "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		cmbProveedor.setModel(proveedores);
		llenarProveedores();	
		cmbProveedor.setBounds(127, 14, 205, 21);
		contentPane.add(cmbProveedor);
		
		cmbTipoFactura = new JComboBox();
		cmbTipoFactura.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C"}));
		cmbTipoFactura.setBounds(127, 38, 205, 21);
		contentPane.add(cmbTipoFactura);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProducto.setBounds(19, 150, 107, 21);
		contentPane.add(lblProducto);
		
		cmbProducto = new JComboBox();
		cmbProducto.setModel(productos);
		cmbProducto.setBounds(136, 154, 205, 21);
		contentPane.add(cmbProducto);
		
		JLabel label_2 = new JLabel("Cantidad");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(19, 182, 107, 15);
		contentPane.add(label_2);
		
		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(136, 179, 205, 21);
		contentPane.add(txtCantidad);
		
		btnAgregarAlCanasto = new JButton("Agregar al canasto");
		btnAgregarAlCanasto.setBounds(188, 211, 153, 34);
		contentPane.add(btnAgregarAlCanasto);
		
		btnQuitarDelCanasto = new JButton("Quitar del canasto");
		btnQuitarDelCanasto.setBounds(19, 211, 138, 34);
		contentPane.add(btnQuitarDelCanasto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 251, 324, 147);
		contentPane.add(scrollPane);
		
		lstProductos = new JList();
		scrollPane.setViewportView(lstProductos);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Conexion c = new Conexion();
				
				
				if(c.conectarDB()){
					ArrayList<Compra> compras = new ArrayList<Compra>();
					Compra compra = new Compra();
					
					compra.setCantidad(10);
					compra.setEstadoOperacion("PRUEBA");
					compra.setIdEmpleado(1);
					compra.setIdProducto(3);
					compra.setIdProveedor(1);
					compra.setPrecio_costo(100.0);
					compra.setPrecio_venta(100.0);
					compra.setTipo_factura("B");
									
					compras.add(compra);
					
					try {
						c.altaCompra(compras);
					} catch (Exception e) {
						System.out.println("Error en alta compra");
						e.printStackTrace();
					}
				}else{
					System.out.println("Error de conexion.");
				}
				
			}
		});
		btnAceptar.setBounds(252, 409, 89, 23);
		contentPane.add(btnAceptar);
		
		JLabel lblTipoFactura = new JLabel("Tipo factura:");
		lblTipoFactura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoFactura.setBounds(10, 38, 107, 21);
		contentPane.add(lblTipoFactura);
	}
	
	private void llenarProveedores(){
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			ArrayList<Proveedor> provedores = new ArrayList();
			provedores = cn.devolverProveedores();
			
			for(int i = 0; i<provedores.size();i++){
				proveedores.addElement(provedores.get(i).getRazonSocial());
			}
		}
		
		cn.close();
	}
	
	private void llenarProductos(Proveedor prov){
		Conexion cn = new Conexion();
		productos.removeAllElements();
		
		if(cn.conectarDB()){
			ArrayList<Producto> prodooctos = new ArrayList();
			prodooctos = cn.devolverProveedorProductos(prov);
				
			for(int i = 0; i<prodooctos.size();i++){
				if(prodooctos.get(i).getNombre()!=null){
					productos.addElement(prodooctos.get(i).getNombre());
				}else{
					productos.addElement(prodooctos.get(i).getNombreCientifico());
				}
				
			}
			
			
		}else{
			JOptionPane.showMessageDialog(null, "Error en la conexion de base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
}
