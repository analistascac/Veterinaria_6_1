// Necesita Lista de Proveedores
// Envia  a la otra capa  : ObejtoMascotaEnVenta , Cantidad,  

package Ventanas;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;

public class frmNuevoProducto extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescirpcion;
	private JLabel lblEspecie;
	private JTextField txtPrecioCosto;
	private JLabel lblDescripcion;
	private JTextField txtPrecioVenta;
	private DefaultComboBoxModel tipodoc = null;
	private DefaultComboBoxModel<String> dia = new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
	private DefaultComboBoxModel<String> mes = new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
	private DefaultComboBoxModel<String> anio = null;
	private JComboBox cmbEspecie;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JLabel lblPrecioCosto;
	private JLabel lblPrecioDeVenta;
	private JLabel lblCantidad;
	private JTextField textCantidad;
	private JLabel txtNombre;
	private JTextField textNombre;
	private JList list_2;
	private JTextField textField;
	private JTextField textField_1;
	
	

	public frmNuevoProducto() {
		setTitle("Nuevo Producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtDescirpcion = new JTextField();
		txtDescirpcion.setColumns(10);
		txtDescirpcion.setBounds(141, 75, 246, 23);
		contentPane.add(txtDescirpcion);
		
		lblEspecie = new JLabel("Especie");
		lblEspecie.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEspecie.setBounds(24, 98, 107, 21);
		contentPane.add(lblEspecie);
		
		cmbEspecie = new JComboBox();
		cmbEspecie.setBounds(141, 98, 114, 21);
		contentPane.add(cmbEspecie);
		
		txtPrecioCosto = new JTextField();
		txtPrecioCosto.setColumns(10);
		txtPrecioCosto.setBounds(141, 121, 246, 21);
		contentPane.add(txtPrecioCosto);
		
		lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setBounds(55, 77, 72, 21);
		contentPane.add(lblDescripcion);
		
		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setColumns(10);
		txtPrecioVenta.setBounds(141, 143, 246, 21);
		contentPane.add(txtPrecioVenta);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(328, 311, 89, 23);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(229, 311, 89, 23);
		contentPane.add(btnCancelar);
		
		lblPrecioCosto = new JLabel("Precio de costo");
		lblPrecioCosto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioCosto.setBounds(24, 121, 107, 21);
		contentPane.add(lblPrecioCosto);
		
		lblPrecioDeVenta = new JLabel("Precio de Venta");
		lblPrecioDeVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrecioDeVenta.setBounds(24, 143, 107, 21);
		contentPane.add(lblPrecioDeVenta);
		
		JComboBox cbxProveedores = new JComboBox();
		cbxProveedores.setBounds(45, 189, 72, 15);
		contentPane.add(cbxProveedores);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProveedor.setBounds(10, 173, 107, 21);
		contentPane.add(lblProveedor);
		
		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidad.setBounds(24, 45, 107, 21);
		contentPane.add(lblCantidad);
		
		textCantidad = new JTextField();
		textCantidad.setColumns(10);
		textCantidad.setBounds(141, 48, 246, 21);
		contentPane.add(textCantidad);
		
		JCheckBox cbxEsMascota = new JCheckBox("Es mascota?");
		cbxEsMascota.setBounds(141, -2, 89, 21);
		contentPane.add(cbxEsMascota);
		
		txtNombre = new JLabel("Nombre");
		txtNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNombre.setBounds(24, 26, 107, 21);
		contentPane.add(txtNombre);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(141, 26, 246, 21);
		contentPane.add(textNombre);
		
		JList list = new JList();
		list.setBounds(141, 175, 246, 60);
		contentPane.add(list);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(24, 205, 93, 15);
		contentPane.add(btnAgregar);
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.setBounds(24, 220, 93, 15);
		contentPane.add(btnQuitar);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(141, 238, 246, 23);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(141, 263, 246, 23);
		contentPane.add(textField_1);
		
		
	}
}
