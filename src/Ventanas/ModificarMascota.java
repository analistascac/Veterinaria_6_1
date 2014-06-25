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
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

import Clases.Cliente;
import Clases.Mascota;
import Conexion.Conexion;
import Main.Main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import jdk.nashorn.internal.scripts.JO;

public class ModificarMascota extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombreCientifico;
	private JTextField txtNombreVulgar;
	private JTextField txtDescripcion;
	private JButton btnModificar;
	private JComboBox cmbDueno;
	private DefaultComboBoxModel duenos = new DefaultComboBoxModel();
	private JComboBox cmbId;
	private DefaultComboBoxModel ides = new DefaultComboBoxModel();

	public ModificarMascota() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Main ventana = new Main();
				ventana.setVisible(true);
				dispose();
			}
		});
		setTitle("Modificar mascota - Veterinaria CAC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 330, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIdentificador = new JLabel("Identificador:");
		lblIdentificador.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdentificador.setBounds(10, 39, 122, 14);
		contentPane.add(lblIdentificador);

		cmbId = new JComboBox();
		cmbId.setEnabled(false);
		cmbId.setModel(ides);
		cmbId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNombreCientifico.setEnabled(true);
				txtNombreVulgar.setEnabled(true);
				txtDescripcion.setEnabled(true);
				btnModificar.setEnabled(true);
				
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					Mascota esta = new Mascota();
					Cliente este = new Cliente();
					este = cn.devolverClientes().get(cmbDueno.getSelectedIndex());
					
					int id = cmbId.getSelectedIndex();
					if(id != -1){
						esta = cn.devolverClienteMascotas(este).get(id);
						txtNombreVulgar.setText(esta.getNombreVulgar());
						txtNombreCientifico.setText(esta.getNombreCientifico());
						txtDescripcion.setText(esta.getDescripcion());
					}else{
						txtNombreVulgar.setText("");
						txtNombreCientifico.setText("");
						txtDescripcion.setText("");
					}
				}else{
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		cmbId.setBounds(142, 36, 162, 17);
		contentPane.add(cmbId);

		JLabel lblDueno = new JLabel("Due\u00F1o:");
		lblDueno.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDueno.setBounds(10, 11, 122, 14);
		contentPane.add(lblDueno);

		cmbDueno = new JComboBox();
		cmbDueno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmbId.setEnabled(true);
				Cliente este = new Cliente();
				
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					este = cn.devolverClientes().get(cmbDueno.getSelectedIndex());
					llenaridmascotas(este);
				}else{
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		llenarClientes();
		cmbDueno.setModel(duenos);
		cmbDueno.setBounds(142, 11, 162, 17);
		contentPane.add(cmbDueno);
		
				JLabel lblNombreCientifico = new JLabel("Nombre cientifico:");
				lblNombreCientifico.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNombreCientifico.setBounds(10, 61, 122, 14);
				contentPane.add(lblNombreCientifico);

		txtNombreCientifico = new JTextField();
		txtNombreCientifico.setEnabled(false);
		txtNombreCientifico.setBounds(142, 58, 162, 17);
		contentPane.add(txtNombreCientifico);
		txtNombreCientifico.setColumns(10);
		
				JLabel lblNombreVulgar = new JLabel("Nombre vulgar:");
				lblNombreVulgar.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNombreVulgar.setBounds(10, 86, 122, 14);
				contentPane.add(lblNombreVulgar);

		txtNombreVulgar = new JTextField();
		txtNombreVulgar.setEnabled(false);
		txtNombreVulgar.setColumns(10);
		txtNombreVulgar.setBounds(142, 83, 162, 17);
		contentPane.add(txtNombreVulgar);
		
				JLabel lblDescripcion = new JLabel("Descripcion:");
				lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
				lblDescripcion.setBounds(10, 111, 122, 14);
				contentPane.add(lblDescripcion);

		txtDescripcion = new JTextField();
		txtDescripcion.setEnabled(false);
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(142, 108, 162, 17);
		contentPane.add(txtDescripcion);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Conexion cn = new Conexion();
				if(cn.conectarDB()){
					Mascota mas = new Mascota();
					Cliente dueno = new Cliente();
					dueno = cn.devolverClientes().get(cmbDueno.getSelectedIndex());
					String idDueno = dueno.getId();
					Mascota paraIdMas = new Mascota();
					paraIdMas = cn.devolverClienteMascotas(dueno).get(cmbId.getSelectedIndex());
					String idMascota = paraIdMas.getId();
					
					
					mas.setId(idMascota);
					mas.setDescripcion(txtDescripcion.getText());
					mas.setIdCliente(idDueno);
					mas.setNombreCientifico(txtNombreCientifico.getText());
					mas.setNombreVulgar(txtNombreVulgar.getText());

					try {
						cn.modificacionMascota(mas);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Error al intentar de dar de modificar la mascota.","Error",JOptionPane.ERROR_MESSAGE);
					}
					JOptionPane.showMessageDialog(null, "Modificación de mascota correcta","Información",JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnModificar.setEnabled(false);
		btnModificar.setBounds(215, 136, 89, 23);
		contentPane.add(btnModificar);
	}

	private void llenarClientes() {
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			ArrayList<Cliente> estos = new ArrayList();
			estos = cn.devolverClientes();
			
			for(int i = 0; i < estos.size(); i++) duenos.addElement(estos.get(i).getId()+" - ("+estos.get(i).getNombre()+" "+estos.get(i).getApellido()+")");
			
		}else{
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	private Mascota obtenerMascota() {
		Mascota mas = new Mascota();
		mas.setDescripcion("Descripcion OP de prueba");
		mas.setIdCliente("Dueño prueba 1");
		mas.setId("Identificador 1");
		mas.setNombreCientifico("Nombre cientifico de pruebas");
		mas.setNombreVulgar("Nombre vulgar de pruebas");
		return mas;
	}
	
	private void llenaridmascotas(Cliente deEste){
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			ArrayList<Mascota> mas = new ArrayList();
			mas = cn.devolverClienteMascotas(deEste);
			
			ides.removeAllElements();
			for(int i = 0; i < mas.size();i++) ides.addElement(mas.get(i).getId() + " - "+mas.get(i).getNombreVulgar());
			
		}else{
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

}
