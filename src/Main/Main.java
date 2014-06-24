package Main;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Ventanas.ModificarAtencion;
import Ventanas.ModificarCliente;
import Ventanas.ModificarEmpleado;
import Ventanas.ModificarMascota;
import Ventanas.ModificarProducto;
import Ventanas.ModificarProveedor;
import Ventanas.frmNuevaAtencion;
import Ventanas.frmNuevaCompra;
import Ventanas.frmNuevaMascota;
import Ventanas.frmNuevaVenta;
import Ventanas.frmNuevoCliente;
import Ventanas.frmNuevoEmpleado;
import Ventanas.frmNuevoProducto;
import Ventanas.frmNuevoProveedor;
import Ventanas.winInformeAnimalesPorRaza;
import Ventanas.winInformeAtencionesPorVeterinario;
import Ventanas.winInformeClienteMasAtendido;
import Ventanas.winInformeMejorVendedor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Main extends JFrame {

	private JPanel panelPrincipal;
	private JMenuItem nueAtencion;
	private JMenuItem nueCliente;
	private JMenuItem nueEmpleado;
	private JMenuItem nueMascota;
	private JMenuItem nueProducto;
	private JMenuItem nueProveedor;
	private JMenu mnModificar;
	private JMenuItem modCliente;
	private JMenuItem modEmpleado;
	private JMenuItem modMascota;
	private JMenuItem morProducto;
	private JMenuItem morProveedor;
	private JMenu mnEliminar;
	private JMenuItem supCliente;
	private JMenuItem supEmpleado;
	private JMenuItem supMascota;
	private JMenuItem supProducto;
	private JMenuItem supProveedor;
	private JMenu mnInformes;
	private JMenu mnVeterinaria;
	private JMenuItem mntmEmpleados;
	private JMenuItem mntmClientes;
	private JMenuItem mntmArticulosParaLa;
	private JMenuItem mntmProveedores;
	private JMenuItem mntmAtencionesDeUn;
	private JMenuItem mntmVendedorConMas;
	private JMenuItem mntmAnimalesPorRaza;
	private JMenuItem mntmClienteMasAtendido;
	private JMenuBar menu;
	private JMenu mnNuevo;
	private JMenuItem nueVenta;
	private JMenuItem mntmCompra;


	public Main() {
		setResizable(false);
		setTitle("Centro veterinario - CAC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		menu = new JMenuBar();
		menu.setBounds(0, 0, 444, 21);
		panelPrincipal.add(menu);
		
		mnNuevo = new JMenu("Nuevo");
		menu.add(mnNuevo);
		
		nueAtencion = new JMenuItem("Atencion");
		nueAtencion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNuevaAtencion ventana = new frmNuevaAtencion();
				ventana.setVisible(true);
				dispose();
			}
		});
		mnNuevo.add(nueAtencion);
		
		nueCliente = new JMenuItem("Cliente");
		nueCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNuevoCliente ventana = new frmNuevoCliente();
				ventana.setVisible(true);
				dispose();
			}
		});
		mnNuevo.add(nueCliente);
		
		nueEmpleado = new JMenuItem("Empleado");
		nueEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNuevoEmpleado ventana = new frmNuevoEmpleado();
				ventana.setVisible(true);
				dispose();
			}
		});
		
		mntmCompra = new JMenuItem("Compra");
		mntmCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNuevaCompra ventana = new frmNuevaCompra();
				ventana.setVisible(true);
				dispose();
			}
		});
		mnNuevo.add(mntmCompra);
		mnNuevo.add(nueEmpleado);
		
		nueMascota = new JMenuItem("Mascota");
		nueMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNuevaMascota ventana = new frmNuevaMascota();
				ventana.setVisible(true);
				dispose();				
			}
		});
		mnNuevo.add(nueMascota);
		
		nueProducto = new JMenuItem("Producto");
		nueProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNuevoProducto ventana = new frmNuevoProducto();
				ventana.setVisible(true);
				dispose();
			}
		});
		mnNuevo.add(nueProducto);
		
		nueProveedor = new JMenuItem("Proveedor");
		nueProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNuevoProveedor ventana = new frmNuevoProveedor();
				ventana.setVisible(true);
				dispose();
			}
		});
		mnNuevo.add(nueProveedor);
		
		nueVenta = new JMenuItem("Venta");
		nueVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNuevaVenta ventana = new frmNuevaVenta();
				ventana.setVisible(true);
				dispose();
			}
		});
		mnNuevo.add(nueVenta);
		
		mnModificar = new JMenu("Modificar");
		menu.add(mnModificar);
		
		modCliente = new JMenuItem("Cliente");
		modCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModificarCliente ventana = new ModificarCliente();
				ventana.setVisible(true);
				dispose();				
			}
		});
		mnModificar.add(modCliente);
		
		modEmpleado = new JMenuItem("Empleado");
		modEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModificarEmpleado ventana = new ModificarEmpleado();
				ventana.setVisible(true);
				dispose();
			}
		});
		mnModificar.add(modEmpleado);
		
		modMascota = new JMenuItem("Mascota");
		modMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModificarMascota ventana = new ModificarMascota();
				ventana.setVisible(true);
				dispose();
			}
		});
		mnModificar.add(modMascota);
		
		morProducto = new JMenuItem("Producto");
		morProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModificarProducto ventana = new ModificarProducto();
				ventana.setVisible(true);
				dispose();
			}
		});
		mnModificar.add(morProducto);
		
		morProveedor = new JMenuItem("Proveedor");
		morProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModificarProveedor ventana = new ModificarProveedor();
				ventana.setVisible(true);
				dispose();
			}
		});
		mnModificar.add(morProveedor);
		
		mnEliminar = new JMenu("Eliminar");
		menu.add(mnEliminar);
		
		supCliente = new JMenuItem("Cliente");
		mnEliminar.add(supCliente);
		
		supEmpleado = new JMenuItem("Empleado");
		mnEliminar.add(supEmpleado);
		
		supMascota = new JMenuItem("Mascota");
		mnEliminar.add(supMascota);
		
		supProducto = new JMenuItem("Producto");
		mnEliminar.add(supProducto);
		
		supProveedor = new JMenuItem("Proveedor");
		mnEliminar.add(supProveedor);
		
		mnInformes = new JMenu("Informes");
		menu.add(mnInformes);
		
		mnVeterinaria = new JMenu("Centro veterinario");
		mnInformes.add(mnVeterinaria);
		
		mntmEmpleados = new JMenuItem("Empleados");
		mnVeterinaria.add(mntmEmpleados);
		
		mntmClientes = new JMenuItem("Clientes");
		mnVeterinaria.add(mntmClientes);
		
		mntmArticulosParaLa = new JMenuItem("Articulos para la venta");
		mnVeterinaria.add(mntmArticulosParaLa);
		
		mntmProveedores = new JMenuItem("Proveedores");
		mnVeterinaria.add(mntmProveedores);
		
		mntmAtencionesDeUn = new JMenuItem("Atenciones de un veterinario");
		mntmAtencionesDeUn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				winInformeAtencionesPorVeterinario ventana = new winInformeAtencionesPorVeterinario();
				ventana.setVisible(true);
				dispose();
			}
		});
		mnInformes.add(mntmAtencionesDeUn);
		
		mntmVendedorConMas = new JMenuItem("Vendedor con mas ventas realizadas");
		mntmVendedorConMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				winInformeMejorVendedor ventana = new winInformeMejorVendedor();
				ventana.setVisible(true);
				dispose();
			}
		});
		mnInformes.add(mntmVendedorConMas);
		
		mntmAnimalesPorRaza = new JMenuItem("Animales por raza existentes en el centro");
		mntmAnimalesPorRaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				winInformeAnimalesPorRaza ventana = new winInformeAnimalesPorRaza();
				ventana.setVisible(true);
				dispose();
			}
		});
		mnInformes.add(mntmAnimalesPorRaza);
		
		mntmClienteMasAtendido = new JMenuItem("Cliente m\u00E1s atendido en la veterinaria");
		mntmClienteMasAtendido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				winInformeClienteMasAtendido c = new winInformeClienteMasAtendido();
				c.setVisible(true);
				dispose();
			}
		});
		mnInformes.add(mntmClienteMasAtendido);
		
		
	}
}
