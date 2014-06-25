package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;



import Clases.Empleado;
import Conexion.Conexion;
import Main.Main;
import Main.TFecha;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class ModificarEmpleado extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDocumento;
	private JTextField txtDomicilio;
	private JTextField txtTelefono;
	private JTextField txtMatricula;
	private JCheckBox chkbxMatricula;
	private JButton btnModificar;
	private JComboBox cmbIdentificador;
	private JComboBox cmbTipoDocumento;
	private DefaultComboBoxModel<String> id = new DefaultComboBoxModel();
	private DefaultComboBoxModel<String> tipodoc = new DefaultComboBoxModel();
	private JComboBox cmbAnio;
	private JComboBox cmbMes;
	private JComboBox cmbDia;
	private TFecha fecha;
	private JLabel lblNewLabel;

	public ModificarEmpleado() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarEmpleado.class.getResource("/Images/logo.jpg")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent arg0) {
				Main v = new Main();
				v.setVisible(true);
				dispose();
			}
		});
		setTitle("Veterinaria Godzilla - Modificacion - Empleado");
		setBounds(100, 100, 379, 313);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdentificador = new JLabel("Identificador:");
		lblIdentificador.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdentificador.setBounds(10, 10, 110, 15);
		contentPane.add(lblIdentificador);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(10, 36, 110, 15);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellido.setBounds(10, 62, 110, 15);
		contentPane.add(lblApellido);
		
		JLabel lblTipoDeDocumeto = new JLabel("Tipo de documeto:");
		lblTipoDeDocumeto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDeDocumeto.setBounds(10, 88, 110, 15);
		contentPane.add(lblTipoDeDocumeto);
		
		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDocumento.setBounds(10, 114, 110, 15);
		contentPane.add(lblDocumento);
		
		JLabel lblDomicilio = new JLabel("Domicilio:");
		lblDomicilio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDomicilio.setBounds(10, 140, 110, 15);
		contentPane.add(lblDomicilio);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setBounds(10, 166, 110, 15);
		contentPane.add(lblTelefono);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaDeNacimiento.setBounds(10, 192, 110, 14);
		contentPane.add(lblFechaDeNacimiento);
		
		llenarCombos();
		
		cmbIdentificador = new JComboBox();
		cmbIdentificador.setModel(id);
		cmbIdentificador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNombre.setEnabled(true);
				txtApellido.setEnabled(true);
				txtDomicilio.setEnabled(true);
				txtTelefono.setEnabled(true);
				cmbTipoDocumento.setEnabled(true);
				txtDocumento.setEnabled(true);
				chkbxMatricula.setEnabled(true);
				cmbDia.setEnabled(true);
				cmbMes.setEnabled(true);
				cmbAnio.setEnabled(true);
				btnModificar.setEnabled(true);
				
				llenarCampos();
			}
		});
		cmbIdentificador.setBounds(130, 7, 195, 18);
		contentPane.add(cmbIdentificador);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setBounds(130, 33, 195, 18);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setEnabled(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(130, 59, 195, 18);
		contentPane.add(txtApellido);
		
		cmbTipoDocumento = new JComboBox();
		cmbTipoDocumento.setModel(tipodoc);
		cmbTipoDocumento.setEnabled(false);
		cmbTipoDocumento.setBounds(130, 85, 195, 18);
		contentPane.add(cmbTipoDocumento);
		
		txtDocumento = new JTextField();
		txtDocumento.setEnabled(false);
		txtDocumento.setColumns(10);
		txtDocumento.setBounds(130, 111, 195, 18);
		contentPane.add(txtDocumento);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setEnabled(false);
		txtDomicilio.setColumns(10);
		txtDomicilio.setBounds(130, 137, 195, 18);
		contentPane.add(txtDomicilio);
		
		txtTelefono = new JTextField();
		txtTelefono.setEnabled(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(130, 164, 195, 18);
		contentPane.add(txtTelefono);
		
		cmbDia = new JComboBox();
		cmbDia.setEnabled(false);
		cmbDia.setBounds(130, 189, 50, 18);
		contentPane.add(cmbDia);
		
		cmbMes = new JComboBox();
		cmbMes.setEnabled(false);
		cmbMes.setBounds(190, 189, 50, 18);
		contentPane.add(cmbMes);
		
		cmbAnio = new JComboBox();
		cmbAnio.setEnabled(false);
		cmbAnio.setBounds(250, 189, 75, 18);
		contentPane.add(cmbAnio);
		
		fecha = new TFecha(cmbDia,cmbMes,cmbAnio);
		
		chkbxMatricula = new JCheckBox("Matricula:");
		chkbxMatricula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chkbxMatricula.isSelected()){
					txtMatricula.setEnabled(true);
				}else{
					txtMatricula.setEnabled(false);
					txtMatricula.setText("");
				}
			}
		});
		chkbxMatricula.setEnabled(false);
		chkbxMatricula.setBounds(31, 216, 89, 15);
		contentPane.add(chkbxMatricula);
		
		txtMatricula = new JTextField();
		txtMatricula.setEnabled(false);
		txtMatricula.setColumns(10);
		txtMatricula.setBounds(130, 214, 195, 18);
		contentPane.add(txtMatricula);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!txtNombre.getText().trim().isEmpty() &&
				!txtApellido.getText().trim().isEmpty() &&
				!txtDocumento.getText().trim().isEmpty() &&
				!txtDomicilio.getText().trim().isEmpty() &&
				!txtTelefono.getText().trim().isEmpty()){
					
					Conexion cn = new Conexion();
					if(cn.conectarDB()){
						Empleado empl = new Empleado();
						empl.setApellido(txtApellido.getText());
						empl.setDocumento(txtDocumento.getText());
						empl.setDomicilio(txtDomicilio.getText());
						empl.setFechaNacimiento(fecha.getFechaString());
						empl.setId(cn.devolverEmpleados().get(cmbIdentificador.getSelectedIndex()).getId());
						empl.setNombre(txtNombre.getText());
						empl.setTelefono(txtTelefono.getText());
						empl.setTipoDoc(cmbTipoDocumento.getSelectedItem()+"");
						
						if(chkbxMatricula.isSelected()){
							if(txtMatricula.getText().trim().isEmpty()){
								empl.setMatricula(null);
							}else{
								empl.setMatricula(txtMatricula.getText());
							}
						}else{
							empl.setMatricula(null);
						}

						try {
							cn.modificacionEmpleado(empl);
							JOptionPane.showMessageDialog(null, "Empleado modificado correctamente","Información",JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Error al tratar de hacer la modificacion en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
						}
					
					}else{
						JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "Datos incompletos.","Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnModificar.setEnabled(false);
		btnModificar.setBounds(236, 243, 89, 23);
		contentPane.add(btnModificar);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ModificarEmpleado.class.getResource("/Images/image_marca_agua.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 373, 285);
		contentPane.add(lblNewLabel);
	}
	
	public void llenarCombos(){
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			ArrayList<Empleado> empl = new ArrayList();
			empl = cn.devolverEmpleados();
			
			for(int i = 0; i<empl.size();i++) id.addElement(empl.get(i).getId() + " - ("+empl.get(i).getNombre()+" "+empl.get(i).getApellido()+")");
			
			cn.close();
		}else{
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		tipodoc.addElement("DNI");
		tipodoc.addElement("CI");
		tipodoc.addElement("LC");
	}
	
	private void llenarCampos(){
		
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			Empleado este = new Empleado();
			este = cn.devolverEmpleados().get(cmbIdentificador.getSelectedIndex());
			String fecha_nac = este.getFechaNacimiento();
			int dia, mes, anio;
			
			txtNombre.setText(este.getNombre());
			txtApellido.setText(este.getApellido());
			txtDomicilio.setText(este.getDocumento());
			txtTelefono.setText(este.getTelefono());
			cmbTipoDocumento.setSelectedItem(este.getTipoDoc());
			txtDocumento.setText(este.getDocumento());		
			if(este.getMatricula()==null){
				chkbxMatricula.setSelected(false);
				txtMatricula.setEnabled(false);
				txtMatricula.setText("");
			}else{
				chkbxMatricula.setSelected(true);
				txtMatricula.setEnabled(true);
				txtMatricula.setText(este.getMatricula());
			}
			
			fecha_nac = fecha_nac.replace(" 00:00:00.0","");
			
			StringTokenizer tokens = new StringTokenizer(fecha_nac,"-");
			anio = Integer.parseInt(tokens.nextToken());
			mes = Integer.parseInt(tokens.nextToken());
			dia = Integer.parseInt(tokens.nextToken());
			
			cmbAnio.setSelectedItem(anio);
			cmbMes.setSelectedIndex(mes-1);
			cmbDia.setSelectedIndex(dia-1);
			
			
		}else{
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}

	}
}
