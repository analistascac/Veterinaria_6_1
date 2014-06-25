package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import Clases.Compra;
import Clases.Empleado;
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

import jdk.nashorn.internal.scripts.JO;

public class frmNuevaCompra extends JFrame {

	private JPanel contentPane;
	private JTextField txtCantidad;
	private JList lstProductos;
	private DefaultListModel canasto = new DefaultListModel();
	private JButton btnAgregarAlCanasto;
	private JButton btnQuitarDelCanasto;
	private JComboBox cmbProveedor;
	private DefaultComboBoxModel proveedores = new DefaultComboBoxModel();
	private DefaultComboBoxModel productos = new DefaultComboBoxModel();
	private JComboBox cmbTipoFactura;
	private JComboBox cmbProducto;
	private JComboBox cmbEmpleado;
	private DefaultComboBoxModel empleados = new DefaultComboBoxModel();
	private JTextField txtPrecioCompra;
	private JTextField txtPrecioVenta;
	
	private ArrayList<Compra> compra = new ArrayList();
	private JComboBox cmbEstadoOperacion;
	private DefaultComboBoxModel estadooperacion = new DefaultComboBoxModel();
	private JButton btnAceptar;

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
		setTitle("Veterinaria Godzilla - Alta - Compra");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 359, 517);
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
		
		cmbEmpleado = new JComboBox();
		cmbEmpleado.setModel(empleados);
		llenarEmpleados();
		cmbEmpleado.setBounds(127, 63, 205, 21);
		contentPane.add(cmbEmpleado);
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProducto.setBounds(10, 95, 107, 21);
		contentPane.add(lblProducto);
		
		cmbProducto = new JComboBox();
		cmbProducto.setModel(productos);
		cmbProducto.setBounds(127, 95, 205, 21);
		contentPane.add(cmbProducto);
		
		JLabel label_2 = new JLabel("Cantidad");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(10, 127, 107, 15);
		contentPane.add(label_2);
		
		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(127, 124, 205, 21);
		contentPane.add(txtCantidad);
		
		btnAgregarAlCanasto = new JButton("Agregar al canasto");
		btnAgregarAlCanasto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

							
					if(!txtPrecioCompra.getText().trim().isEmpty() && !txtPrecioVenta.getText().trim().isEmpty() && !txtCantidad.getText().trim().isEmpty()){
						Conexion cn = new Conexion();
						cmbProveedor.setEnabled(false);
						cmbEmpleado.setEnabled(false);
						if(cn.conectarDB()){

							
							Compra com = new Compra();
							Proveedor p = new Proveedor();
							Empleado e = new Empleado();
							Producto prod = new Producto();
							boolean error = false;
							
							if(Integer.parseInt(txtCantidad.getText()) < 0) error = true;
							if(Integer.parseInt(txtPrecioCompra.getText()) < 0) error = true; 
							if(Integer.parseInt(txtPrecioVenta.getText()) < 0) error = true;
								
							if(!error){
								p = (Proveedor) cn.devolverProveedores().get(cmbProveedor.getSelectedIndex());
								e = (Empleado) cn.devolverEmpleados().get(cmbEmpleado.getSelectedIndex());
								prod = (Producto) cn.devolverProveedorProductos(p).get(cmbProducto.getSelectedIndex());
		
								try{
									com.setCantidad(Integer.parseInt(txtCantidad.getText()));
									com.setPrecio_costo(Double.parseDouble(txtPrecioCompra.getText()));
									com.setPrecio_venta(Double.parseDouble(txtPrecioVenta.getText()));
									if(prod.getNombre() != null){
										canasto.addElement(prod.getNombre() + " x " + txtCantidad.getText());
									}else{
										canasto.addElement(prod.getNombreCientifico() + " x " + txtCantidad.getText());
									}
									com.setIdProveedor(p.getId());
									com.setIdProducto(prod.getId());
									com.setIdEmpleado(e.getId());
									com.setTipo_factura(cmbTipoFactura.getSelectedItem().toString());
									com.setEstadoOperacion(cmbEstadoOperacion.getSelectedItem()+"");
									txtCantidad.setText("");
									txtPrecioCompra.setText("");
									txtPrecioVenta.setText("");
									if(!error){
										compra.add(com);
										btnQuitarDelCanasto.setEnabled(true);
										btnAceptar.setEnabled(true);
									}
									
								}catch (Exception e1){
									error = true;
									JOptionPane.showMessageDialog(null, "Error en la carga de datos.","Error",JOptionPane.ERROR_MESSAGE);
								}
							}else{
								JOptionPane.showMessageDialog(null, "Error en la carga de datos.","Error",JOptionPane.ERROR_MESSAGE);
							}
							
						}
					}else{
						JOptionPane.showMessageDialog(null, "Error en la carga de datos.","Error",JOptionPane.ERROR_MESSAGE);
					}
					 
			}
		});
		btnAgregarAlCanasto.setBounds(188, 211, 153, 34);
		contentPane.add(btnAgregarAlCanasto);
		
		btnQuitarDelCanasto = new JButton("Quitar del canasto");
		btnQuitarDelCanasto.setEnabled(false);
		btnQuitarDelCanasto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				compra.remove(lstProductos.getSelectedIndex());
				canasto.remove(lstProductos.getSelectedIndex());
				
				if(canasto.isEmpty()){
					btnQuitarDelCanasto.setEnabled(false);
					btnAceptar.setEnabled(false);
				}
			}
		});
		btnQuitarDelCanasto.setBounds(19, 211, 138, 34);
		contentPane.add(btnQuitarDelCanasto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 251, 324, 147);
		contentPane.add(scrollPane);
		
		lstProductos = new JList();
		lstProductos.setModel(canasto);
		scrollPane.setViewportView(lstProductos);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setEnabled(false);
		btnAceptar.setBounds(252, 455, 89, 23);
		contentPane.add(btnAceptar);
		
		JLabel lblTipoFactura = new JLabel("Tipo factura:");
		lblTipoFactura.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoFactura.setBounds(10, 38, 107, 21);
		contentPane.add(lblTipoFactura);
		
		JLabel lblEmpleado = new JLabel("Empleado:");
		lblEmpleado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmpleado.setBounds(10, 63, 107, 21);
		contentPane.add(lblEmpleado);
		
		JLabel lblPrecioCompra = new JLabel("Precio compra:");
		lblPrecioCompra.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioCompra.setBounds(10, 156, 107, 15);
		contentPane.add(lblPrecioCompra);
		
		txtPrecioCompra = new JTextField();
		txtPrecioCompra.setColumns(10);
		txtPrecioCompra.setBounds(127, 153, 205, 21);
		contentPane.add(txtPrecioCompra);
		
		JLabel lblPrecioventa = new JLabel("Precio venta");
		lblPrecioventa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioventa.setBounds(10, 185, 107, 15);
		contentPane.add(lblPrecioventa);
		
		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setColumns(10);
		txtPrecioVenta.setBounds(127, 182, 205, 21);
		contentPane.add(txtPrecioVenta);
		
		JLabel lblEstadoOperacion = new JLabel("Estado operacion:");
		lblEstadoOperacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstadoOperacion.setBounds(10, 409, 107, 15);
		contentPane.add(lblEstadoOperacion);
		
		cmbEstadoOperacion = new JComboBox();
		estadooperacion.addElement("Abonado");
		estadooperacion.addElement("Pendiente de pago");
		cmbEstadoOperacion.setModel(estadooperacion);
		cmbEstadoOperacion.setBounds(127, 409, 205, 21);
		contentPane.add(cmbEstadoOperacion);
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = JOptionPane.showConfirmDialog(null, "¿Confirma dar de alta la nueva compra?","Confirmación",JOptionPane.YES_NO_OPTION);
				if(x == JOptionPane.YES_OPTION){
					Conexion cn = new Conexion();
					
					if(cn.conectarDB()){
						try {
							cn.altaCompra(compra);
							JOptionPane.showMessageDialog(null, "Compra cargada exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Error en la alta de la compra.","Error",JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Error en la conexion de la base de datos","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				}
		});
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
			cn.close();
			
		}else{
			JOptionPane.showMessageDialog(null, "Error en la conexion de base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void llenarEmpleados(){
		Conexion cn = new Conexion();
		empleados.removeAllElements();
		if(cn.conectarDB()){
			ArrayList<Empleado> empleaados = new ArrayList();
			empleaados = cn.devolverEmpleados();
			
			for(int i = 0; i<empleaados.size();i++){
				empleados.addElement(empleaados.get(i).getNombre() + " " + empleaados.get(i).getApellido() + " ~ " + empleaados.get(i).getId());
			}
		}else{
			JOptionPane.showMessageDialog(null, "Error en la conexion de base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}
