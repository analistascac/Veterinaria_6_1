
package Ventanas;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;

import Clases.Compra;
import Clases.Producto;
import Clases.Proveedor;
import Conexion.Conexion;
import Main.Main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class frmNuevoProducto extends JFrame {

	private JPanel contentPane;
	private JTextField txtDescirpcion;
	private JLabel lblDescripcion;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JTextField txtNombreCientifico;
	private JTextField txtNombreVulgar;
	private JTextField txtMedida;
	private JComboBox cmbProveedores;
	private DefaultComboBoxModel proveedores = new DefaultComboBoxModel();
	private JCheckBox cbxEsMascota;

	

	public frmNuevoProducto() {
		
		

		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Main v = new Main();
				v.setVisible(true);
				dispose();
			}
		});
		setTitle("Veterinaria Godzilla - Alta - Producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 412, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = JOptionPane.showConfirmDialog(null, "¿Confirma dar de alta el nuevo producto?", "Confirmación",JOptionPane.YES_NO_OPTION);
				if(x == JOptionPane.YES_OPTION){
					Producto p = new Producto();
					Conexion cn = new Conexion();
					Proveedor prov = new Proveedor();
					
					if(cn.conectarDB()){
						prov = (Proveedor) cn.devolverProveedores().get(cmbProveedores.getSelectedIndex());
						p.setIdProveedor(prov.getId());
					}else{
						JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
					}
	
					if(cbxEsMascota.isSelected()){
						p.setNombreCientifico(txtNombreCientifico.getText());
						p.setNombreVulgar(txtNombreVulgar.getText());
						p.setDescripcion(txtDescirpcion.getText());
						p.setNombre(null);
						p.setMedida(null);					
					}else{
						p.setNombre(txtNombre.getText());
						p.setDescripcion(txtDescirpcion.getText());
						p.setMedida(txtMedida.getText());
						p.setNombreCientifico(null);
						p.setNombreVulgar(null);
					}
					
					agregarProducto(p);
					
					JOptionPane.showMessageDialog(null, "Producto correctamente agregado","Información",JOptionPane.INFORMATION_MESSAGE);
					
					int x2 = JOptionPane.showConfirmDialog(null, "¿Desea agregar otro producto?","Confirme",JOptionPane.YES_NO_OPTION);
					if(x2 == JOptionPane.YES_OPTION){
						txtNombre.setText("");
						txtNombreCientifico.setText("");
						txtNombreVulgar.setText("");
						txtDescirpcion.setText("");
						txtMedida.setText("");
					}else{
						dispose();
					}
				}
			}
		});
		btnAceptar.setBounds(298, 227, 89, 23);
		contentPane.add(btnAceptar);
		
		cbxEsMascota = new JCheckBox("Es mascota?");
		cbxEsMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbxEsMascota.isSelected()){
					txtMedida.setEnabled(false);
					txtNombre.setEnabled(false);
					txtDescirpcion.setEnabled(true);
					txtNombreCientifico.setEnabled(true);
					txtNombreVulgar.setEnabled(true);
				}else{
					txtMedida.setEnabled(true);
					txtNombre.setEnabled(true);
					txtDescirpcion.setEnabled(true);
					txtNombreCientifico.setEnabled(false);
					txtNombreVulgar.setEnabled(false);
				}
			}
		});
		cbxEsMascota.setBounds(141, -2, 114, 21);
		contentPane.add(cbxEsMascota);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(141, 26, 246, 21);
		contentPane.add(txtNombre);
		
		txtMedida = new JTextField();
		txtMedida.setColumns(10);
		txtMedida.setBounds(141, 67, 246, 23);
		contentPane.add(txtMedida);
		
		txtDescirpcion = new JTextField();
		txtDescirpcion.setColumns(10);
		txtDescirpcion.setBounds(141, 101, 246, 23);
		contentPane.add(txtDescirpcion);
		
		cmbProveedores = new JComboBox();
		cmbProveedores.setModel(proveedores);
		cargarProveedores();
		cmbProveedores.setBounds(141, 136, 246, 21);
		contentPane.add(cmbProveedores);
		
		txtNombreCientifico = new JTextField();
		txtNombreCientifico.setEnabled(false);
		txtNombreCientifico.setColumns(10);
		txtNombreCientifico.setBounds(141, 168, 246, 21);
		contentPane.add(txtNombreCientifico);
		
		txtNombreVulgar = new JTextField();
		txtNombreVulgar.setEnabled(false);
		txtNombreVulgar.setColumns(10);
		txtNombreVulgar.setBounds(141, 195, 246, 21);
		contentPane.add(txtNombreVulgar);
		
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(199, 227, 89, 23);
		contentPane.add(btnCancelar);
		
		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescripcion.setBounds(55, 103, 72, 21);
		contentPane.add(lblDescripcion);
		
		JLabel lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProveedor.setBounds(24, 135, 107, 21);
		contentPane.add(lblProveedor);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(24, 26, 107, 21);
		contentPane.add(lblNombre);
		
		JLabel lblNombreCientifico = new JLabel("Nombre cientifico:");
		lblNombreCientifico.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreCientifico.setBounds(24, 168, 107, 21);
		contentPane.add(lblNombreCientifico);
		
		JLabel lblNombreVulgar = new JLabel("Nombre vulgar:");
		lblNombreVulgar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreVulgar.setBounds(24, 195, 107, 21);
		contentPane.add(lblNombreVulgar);
		
		JLabel lblMedida = new JLabel("Medida:");
		lblMedida.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMedida.setBounds(55, 69, 72, 21);
		contentPane.add(lblMedida);
		
		
	}
	
	private void cargarProveedores(){
		 Conexion cn = new Conexion();
		 ArrayList<Proveedor> provedores = new ArrayList<Proveedor>();

		if(cn.conectarDB()){
			provedores = cn.devolverProveedores();
			for(int i = 0; i<provedores.size();i++){
				proveedores.addElement(provedores.get(i).getRazonSocial());
			}
		}else{
			JOptionPane.showMessageDialog(null, "Error en la conexion de base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void agregarProducto(Producto este){
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			try {
				cn.altaProducto(este);
			} catch (Exception e) {
				System.out.println("Error al dar de alta un producto");
			}
		}else{
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}
