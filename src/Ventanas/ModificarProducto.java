package Ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;







import Clases.Producto;
import Clases.Proveedor;
import Conexion.Conexion;
import Main.Main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JButton;

public class ModificarProducto extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultComboBoxModel<String> productos = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> proveedores = new DefaultComboBoxModel<String>();
	private JTextField txtNombre;
	private JTextField txtMedida;
	private JTextField txtDescripcion;
	private JTextField txtNombreCientifico;
	private JTextField txtNombreVulgar;
	private JComboBox<String> cmbProducto;
	
	public ModificarProducto() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent arg0) {
				Main m = new Main ();
				m.setVisible(true);
				dispose();
			}
		});
		setTitle("Modificar Producto - Veterinaria CAC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 381, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Productos:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(10, 39, 122, 14);
		contentPane.add(label);
		
		cmbProducto = new JComboBox<String>();
		cmbProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = cmbProducto.getSelectedIndex();
				Conexion cn = new Conexion();
				
				if(cn.conectarDB()){
					if(index > -1){
						Producto prod = new Producto();

						prod = cn.devolverProductos().get(cmbProducto.getSelectedIndex());
						
						txtNombre.setText(prod.getNombre());
						txtDescripcion.setText(prod.getDescripcion());
						txtMedida.setText(prod.getMedida());
						txtNombreCientifico.setText(prod.getNombreCientifico());
						txtNombreVulgar.setText(prod.getNombreVulgar());
						
						if(prod.getNombre() == null){
							txtNombre.setEnabled(false);
							txtMedida.setEnabled(false);
							txtDescripcion.setEnabled(true);
							txtNombreCientifico.setEnabled(true);
							txtNombreVulgar.setEnabled(true);
						}else{
							txtNombre.setEnabled(true);
							txtMedida.setEnabled(true);
							txtDescripcion.setEnabled(true);
							txtNombreCientifico.setEnabled(false);
							txtNombreVulgar.setEnabled(false);
						}
					}
					
						
				}else{
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				
				}
		});
		llenarProductos();
		cmbProducto.setModel(productos);
		cmbProducto.setBounds(142, 38, 213, 17);
		contentPane.add(cmbProducto);
		
		JLabel label_2 = new JLabel("Nombre:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(10, 64, 122, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Medida:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(10, 89, 122, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Descripcion:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(10, 114, 122, 14);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Nombre cientifico:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(10, 139, 122, 14);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("Nombre vulgar:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(10, 164, 122, 14);
		contentPane.add(label_6);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(142, 61, 213, 17);
		contentPane.add(txtNombre);
		
		txtMedida = new JTextField();
		txtMedida.setEnabled(false);
		txtMedida.setColumns(10);
		txtMedida.setBounds(142, 86, 213, 17);
		contentPane.add(txtMedida);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setEnabled(false);
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(142, 111, 213, 17);
		contentPane.add(txtDescripcion);
		
		txtNombreCientifico = new JTextField();
		txtNombreCientifico.setEnabled(false);
		txtNombreCientifico.setColumns(10);
		txtNombreCientifico.setBounds(142, 137, 213, 17);
		contentPane.add(txtNombreCientifico);
		
		txtNombreVulgar = new JTextField();
		txtNombreVulgar.setEnabled(false);
		txtNombreVulgar.setColumns(10);
		txtNombreVulgar.setBounds(142, 161, 213, 17);
		contentPane.add(txtNombreVulgar);
		
		JButton bynModificar = new JButton("Modificar");
		bynModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					Producto este = new Producto();
					este = cn.devolverProductos().get(cmbProducto.getSelectedIndex());
					
					este.setDescripcion(txtDescripcion.getText());
					
					if(txtNombreCientifico.getText() != ""){
						este.setNombreCientifico(txtNombreCientifico.getText());
						este.setNombreVulgar(txtNombreVulgar.getText());
					}else{
						este.setNombreCientifico(null);
						este.setNombreVulgar(null);
					}
					
					if(txtNombre.getText() != ""){
						este.setMedida(txtMedida.getText());
						este.setNombre(txtNombre.getText());
					}else{
						este.setMedida(null);
						este.setNombre(null);
					}
					
					
					try {
						cn.modificacionProducto(este);
						JOptionPane.showMessageDialog(null, "Modificación realizada correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				}else{
					JOptionPane.showMessageDialog(null,"Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		bynModificar.setBounds(266, 189, 89, 23);
		contentPane.add(bynModificar);
	}
	
	private void llenarProductos(){
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			ArrayList<Producto> prods = new ArrayList<Producto>();
			prods = cn.devolverProductos();
			
			for(int i = 0; i < prods.size();i++){
				if(prods.get(i).getNombre() != null){
					productos.addElement(prods.get(i).getNombre());
				}else{
					productos.addElement(prods.get(i).getNombreCientifico());
				}
			}
			
		}else{
			JOptionPane.showMessageDialog(null,"Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
		

}
