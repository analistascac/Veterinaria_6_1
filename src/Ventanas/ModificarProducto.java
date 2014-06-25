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
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;

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
	private JLabel lblNewLabel;
	
	public ModificarProducto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarProducto.class.getResource("/Images/logo.jpg")));
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent arg0) {
				Main m = new Main ();
				m.setVisible(true);
				dispose();
			}
		});
		setTitle("Veterinaria Godzilla - Modificacion - Producto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 381, 224);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Productos:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(10, 12, 105, 14);
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
		cmbProducto.setBounds(125, 11, 213, 17);
		contentPane.add(cmbProducto);
		
		JLabel label_2 = new JLabel("Nombre:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(10, 37, 105, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Medida:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(10, 62, 105, 14);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Descripcion:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(10, 85, 105, 14);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Nombre cientifico:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(10, 110, 105, 14);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("Nombre vulgar:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(10, 135, 105, 14);
		contentPane.add(label_6);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(125, 35, 213, 17);
		contentPane.add(txtNombre);
		
		txtMedida = new JTextField();
		txtMedida.setEnabled(false);
		txtMedida.setColumns(10);
		txtMedida.setBounds(125, 60, 213, 17);
		contentPane.add(txtMedida);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setEnabled(false);
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(125, 83, 213, 17);
		contentPane.add(txtDescripcion);
		
		txtNombreCientifico = new JTextField();
		txtNombreCientifico.setEnabled(false);
		txtNombreCientifico.setColumns(10);
		txtNombreCientifico.setBounds(125, 108, 213, 17);
		contentPane.add(txtNombreCientifico);
		
		txtNombreVulgar = new JTextField();
		txtNombreVulgar.setEnabled(false);
		txtNombreVulgar.setColumns(10);
		txtNombreVulgar.setBounds(125, 133, 213, 17);
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
		bynModificar.setBounds(249, 160, 89, 23);
		contentPane.add(bynModificar);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ModificarProducto.class.getResource("/Images/image_marca_agua_small.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 375, 196);
		contentPane.add(lblNewLabel);
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
