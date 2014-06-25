package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.JobAttributes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import Clases.Proveedor;
import Conexion.Conexion;
import Main.Main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;

public class ModificarProveedor extends JFrame {
	private JPanel panelPrincipal;
	private JTextField txtRazonSOcial;
	private JTextField txtCuit;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtFax;
	private JTextField txtEmail;
	private JButton btnModificar;
	private JComboBox cmbId;
	private DefaultComboBoxModel<String> id = new DefaultComboBoxModel();
	private JLabel lblNewLabel_1;

	public ModificarProveedor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarProveedor.class.getResource("/Images/logo.jpg")));
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent arg0) {
				Main v = new Main();
				v.setVisible(true);
				dispose();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Veterinaria Godzilla - Modificacion - Proveedor");
		setBounds(100, 100, 388, 260);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.WHITE);
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		JLabel lblNewLabel = new JLabel("Identificador:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(38, 14, 83, 14);
		panelPrincipal.add(lblNewLabel);

		JLabel lblRazonSocial = new JLabel("Razon social:");
		lblRazonSocial.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRazonSocial.setBounds(38, 39, 83, 14);
		panelPrincipal.add(lblRazonSocial);

		JLabel lblCuit = new JLabel("Cuit:");
		lblCuit.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCuit.setBounds(38, 64, 83, 14);
		panelPrincipal.add(lblCuit);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setBounds(38, 89, 83, 14);
		panelPrincipal.add(lblDireccion);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(38, 114, 83, 14);
		panelPrincipal.add(lblTelefono);

		JLabel lblFax = new JLabel("Fax:");
		lblFax.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFax.setBounds(38, 139, 83, 14);
		panelPrincipal.add(lblFax);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(38, 164, 83, 14);
		panelPrincipal.add(lblEmail);

		cargarId();

		cmbId = new JComboBox();
		cmbId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtRazonSOcial.setEnabled(true);
				txtCuit.setEnabled(true);
				txtDireccion.setEnabled(true);
				txtTelefono.setEnabled(true);
				txtFax.setEnabled(true);
				txtEmail.setEnabled(true);
				btnModificar.setEnabled(true);
				cargarDatos();
			}
		});
		cmbId.setBounds(131, 11, 173, 17);
		cmbId.setModel(id);
		panelPrincipal.add(cmbId);

		txtRazonSOcial = new JTextField();
		txtRazonSOcial.setEnabled(false);
		txtRazonSOcial.setText("");
		txtRazonSOcial.setBounds(131, 36, 173, 20);
		panelPrincipal.add(txtRazonSOcial);
		txtRazonSOcial.setColumns(10);

		txtCuit = new JTextField();
		txtCuit.setEnabled(false);
		txtCuit.setBounds(131, 61, 173, 20);
		panelPrincipal.add(txtCuit);
		txtCuit.setColumns(10);

		txtDireccion = new JTextField();
		txtDireccion.setEnabled(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(131, 86, 173, 20);
		panelPrincipal.add(txtDireccion);

		txtTelefono = new JTextField();
		txtTelefono.setEnabled(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(131, 111, 173, 20);
		panelPrincipal.add(txtTelefono);

		txtFax = new JTextField();
		txtFax.setEnabled(false);
		txtFax.setColumns(10);
		txtFax.setBounds(131, 136, 173, 20);
		panelPrincipal.add(txtFax);

		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(131, 161, 173, 20);
		panelPrincipal.add(txtEmail);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txtCuit.getText().trim().isEmpty()
						&& !txtRazonSOcial.getText().trim().isEmpty()
						&& !txtDireccion.getText().trim().isEmpty()
						&& !txtTelefono.getText().trim().isEmpty()
						&& !txtFax.getText().trim().isEmpty()
						&& !txtEmail.getText().trim().isEmpty()) {
					
					
					Conexion cn = new Conexion();
					if(cn.conectarDB()){
						Proveedor prov = new Proveedor();
						prov.setCuit(txtCuit.getText());
						prov.setRazonSocial(txtRazonSOcial.getText());
						prov.setDireccion(txtDireccion.getText());
						prov.setTelefono(txtTelefono.getText());
						prov.setFax(txtFax.getText());
						prov.setEmail(txtEmail.getText());
						prov.setId(cn.devolverProveedores().get(cmbId.getSelectedIndex()).getId());

						try{
							cn.modificacionProveedor(prov);
						}catch(Exception e){
							JOptionPane.showMessageDialog(null, "Error al tratar de modificar un proveedor","Error",JOptionPane.ERROR_MESSAGE);
						}

						JOptionPane.showMessageDialog(null,"Proveedor correctamente modificado.","Información", JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
					}
					

				} else {
					JOptionPane.showMessageDialog(null,"Error, algún campo está vacio.", "Error",JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnModificar.setEnabled(false);
		btnModificar.setBounds(215, 188, 89, 23);
		panelPrincipal.add(btnModificar);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ModificarProveedor.class.getResource("/Images/image_marca_agua_small.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 382, 232);
		panelPrincipal.add(lblNewLabel_1);
	}

	private void cargarId() {
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			ArrayList<Proveedor> estos = new ArrayList();
			estos = cn.devolverProveedores();
			
			for(int i = 0; i < estos.size(); i++) id.addElement(estos.get(i).getCuit() +" ("+ estos.get(i).getRazonSocial()+")");
			cn.close();
		}else{
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	public void cargarDatos() {
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			Proveedor este = new Proveedor();
			este = cn.devolverProveedores().get(cmbId.getSelectedIndex());
			
			txtRazonSOcial.setText(este.getRazonSocial());
			txtCuit.setText(este.getCuit());
			txtDireccion.setText(este.getDireccion());
			txtTelefono.setText(este.getTelefono());
			txtFax.setText(este.getFax());
			txtEmail.setText(este.getEmail());
		}else{
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
}
