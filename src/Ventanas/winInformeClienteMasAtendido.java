package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import Clases.Cliente;
import Conexion.Conexion;
import Main.Main;
import java.awt.Toolkit;
import java.awt.Color;

public class winInformeClienteMasAtendido extends JFrame {

	private static final long serialVersionUID = 1074887114872037256L;
	private JPanel contentPane;
	private JLabel lblNombre;
	private JTextField txtNombre;

	public winInformeClienteMasAtendido() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(winInformeClienteMasAtendido.class.getResource("/Images/logo.jpg")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Main ventana = new Main();
				ventana.setVisible(true);
				dispose();
			}
		});
		setResizable(false);
		setTitle("Veterinaria Godzilla - Informe - Cliente Frecuente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 395, 103);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNombre = new JLabel("Cliente mas atendido");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBounds(10, 11, 369, 14);
		contentPane.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		Conexion cn = new Conexion();
		if(cn.conectarDB()){
			txtNombre.setText(cn.informeClienteFrecuente());
			txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		}else{
			JOptionPane.showMessageDialog(null, "Error en la conexion de base de datos","Error",JOptionPane.ERROR_MESSAGE);
		}
		txtNombre.setBounds(10, 36, 369, 20);
		contentPane.add(txtNombre);
	}
}
