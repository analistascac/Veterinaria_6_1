package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Clases.Venta;
import Main.Main;
import Main.TFecha;

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

public class frmNuevaVenta extends JFrame {

	private JPanel contentPane;
	private JTextField txtCantidad;
	private JList lstArticulos;
	private DefaultListModel<String> todoslosarticulos = new DefaultListModel();
	private JList lstArticulosVendidos;
	private DefaultListModel<String> artvendidos = new DefaultListModel();
	private JButton btnQuitar;
	private JButton btnAgregar;
	private JTextField txtImporteTotal;
	
	private JComboBox cmbVendedor;
	private DefaultComboBoxModel<String> vendedores = new DefaultComboBoxModel();
	private JComboBox cmbCliente;
	private DefaultComboBoxModel<String> clientes = new DefaultComboBoxModel();
	
	private JComboBox cmbEstadoOperacion;
	private Venta venta = new Venta();
	private TFecha fecha;

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
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCliente.setBounds(10, 11, 100, 14);
		contentPane.add(lblCliente);
		
		JLabel lblVendedor = new JLabel("Vendedor:");
		lblVendedor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVendedor.setBounds(10, 36, 100, 14);
		contentPane.add(lblVendedor);
		
		JLabel lblImporteTotal = new JLabel("Importe total:");
		lblImporteTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImporteTotal.setBounds(10, 111, 100, 14);
		contentPane.add(lblImporteTotal);
		
		JLabel lblArticulosVendidos = new JLabel("Articulos:");
		lblArticulosVendidos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArticulosVendidos.setBounds(10, 136, 100, 14);
		contentPane.add(lblArticulosVendidos);
		
		JLabel lblEstadoOperacion = new JLabel("Estado operacion:");
		lblEstadoOperacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstadoOperacion.setBounds(10, 61, 100, 14);
		contentPane.add(lblEstadoOperacion);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(182, 163, 46, 14);
		contentPane.add(lblCantidad);
		
		cmbCliente = new JComboBox();
		cmbCliente.setModel(clientes);
		llenarClientes();
		cmbCliente.setBounds(120, 8, 281, 20);
		contentPane.add(cmbCliente);
		
		cmbVendedor = new JComboBox();
		cmbVendedor.setModel(vendedores);
		llenarVendedores();
		cmbVendedor.setBounds(120, 33, 281, 20);
		contentPane.add(cmbVendedor);
		
		txtImporteTotal = new JTextField();
		txtImporteTotal.setColumns(10);
		txtImporteTotal.setBounds(120, 108, 229, 17);
		contentPane.add(txtImporteTotal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 161, 162, 129);
		contentPane.add(scrollPane);
		
		lstArticulos = new JList();
		lstArticulos.setModel(todoslosarticulos);
		llenarArticulos();
		scrollPane.setViewportView(lstArticulos);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(182, 184, 56, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!txtCantidad.getText().trim().isEmpty()){
					venta.agregarDetalle(todoslosarticulos.get(lstArticulos.getSelectedIndex()), Integer.parseInt(txtCantidad.getText()));
					artvendidos.addElement(txtCantidad.getText()+" - "+todoslosarticulos.get(lstArticulos.getSelectedIndex()));
					todoslosarticulos.removeElementAt(lstArticulos.getSelectedIndex());
					if(todoslosarticulos.isEmpty()){
						btnAgregar.setEnabled(false);
					}
					btnQuitar.setEnabled(true);
				}
			}
		});
		btnAgregar.setBounds(182, 213, 87, 23);
		contentPane.add(btnAgregar);
		
		btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String esto = venta.elemento(lstArticulosVendidos.getSelectedIndex());
				
				todoslosarticulos.addElement(esto);				
				venta.borrarDetalle(lstArticulosVendidos.getSelectedIndex());
				artvendidos.removeElementAt(lstArticulosVendidos.getSelectedIndex());
		
				
				if(artvendidos.isEmpty()){
					btnQuitar.setEnabled(false);
				}
				btnAgregar.setEnabled(true);
			}
			
		});
		btnQuitar.setBounds(182, 247, 87, 23);
		contentPane.add(btnQuitar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(279, 161, 162, 129);
		contentPane.add(scrollPane_1);
		
		lstArticulosVendidos = new JList();
		lstArticulosVendidos.setModel(artvendidos);
		scrollPane_1.setViewportView(lstArticulosVendidos);
		
		
		DefaultComboBoxModel<String> estados = new DefaultComboBoxModel();
		cmbEstadoOperacion = new JComboBox();
		cmbEstadoOperacion.setModel(estados);
		estados.addElement("Abonado");
		estados.addElement("Pendiente de pago");
		cmbEstadoOperacion.setBounds(120, 60, 155, 17);
		contentPane.add(cmbEstadoOperacion);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	
				
				try{
					venta.setCliente((String)cmbCliente.getSelectedItem());
					venta.setEstado_operacion((String)cmbEstadoOperacion.getSelectedItem());
					venta.setImporte_total(Float.parseFloat(txtImporteTotal.getText()));
					venta.setVendedor((String)cmbVendedor.getSelectedItem());
					
					JOptionPane.showMessageDialog(null, venta.toString(), "Informacion", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error en la carga del importe.","Error",JOptionPane.WARNING_MESSAGE);
				}
				
				
			}
		});
		btnAceptar.setBounds(345, 328, 89, 23);
		contentPane.add(btnAceptar);
	}
	
	private void llenarClientes(){
		clientes.addElement("Cliente prueba 1");
		clientes.addElement("Cliente prueba 2");
		clientes.addElement("Cliente prueba 3");
	}
	private void llenarVendedores(){
		vendedores.addElement("Vendedor prueba 1");
		vendedores.addElement("Vendedor prueba 2");
		vendedores.addElement("Vendedor prueba 3");
	}
	private void llenarArticulos(){
		todoslosarticulos.addElement("Articulo prueba 1");
		todoslosarticulos.addElement("Articulo prueba 2");
		todoslosarticulos.addElement("Articulo prueba 3");
		todoslosarticulos.addElement("Articulo prueba 4");
	}
}
