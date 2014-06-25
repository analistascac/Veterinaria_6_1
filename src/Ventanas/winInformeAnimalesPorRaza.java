package Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Conexion.Conexion;
import Clases.*;
import Main.Main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JList;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class winInformeAnimalesPorRaza extends JFrame {

	private JPanel contentPane;
	private JList lstAnimalesPorRaza;
	private DefaultListModel animales = new DefaultListModel();
	private JLabel lblNewLabel;

	public winInformeAnimalesPorRaza() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(
						winInformeAnimalesPorRaza.class
								.getResource("/Images/logo.jpg")));
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Main v = new Main();
				v.setVisible(true);
				dispose();
			}
		});

		setTitle("Veterinaria Godzilla - Informe - Animales por Raza");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAnimalesPorRaza = new JLabel("Animales por Raza");
		lblAnimalesPorRaza.setBounds(142, 11, 141, 14);
		lblAnimalesPorRaza.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblAnimalesPorRaza);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 34, 414, 217);
		contentPane.add(scrollPane);

		lstAnimalesPorRaza = new JList();
		lstAnimalesPorRaza.setModel(animales);
		scrollPane.setViewportView(lstAnimalesPorRaza);

		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 444, 272);
		contentPane.add(lblNewLabel);

		Conexion cn = new Conexion();

		if (cn.conectarDB()) {
			ArrayList<Producto> anim = new ArrayList();
			anim = cn.informeAnimalesPorRaza();

			for (int i = 0; i < anim.size(); i++)
				animales.addElement(anim.get(i).getNombreCientifico()
						+ ", cantidad: " + anim.get(i).getCantidad());
		} else {
			JOptionPane.showMessageDialog(null,
					"Error al conectar con la base de datos", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
