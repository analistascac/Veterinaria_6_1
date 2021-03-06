package Conexion;

import Clases.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import Clases.*;

public class Conexion {

	String url = "jdbc:sqlserver://DIGITALHARDCORE\\SQLEXPRESS2008R;databaseName=db_Veterinaria;integratedSecurity=true;";
	// String url =
	// "jdbc:sqlserver://localhost:1433;databaseName=db_Veterinaria;integratedSecurity=true;";
	Connection con;
	Statement select;
	Statement st;
	ResultSet rs;
	String user = "";
	String pass = "";

	public Conexion() {

	}

	public boolean conectarDB() {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// con = DriverManager.getConnection(url);
			con = DriverManager
					.getConnection(
							"jdbc:sqlserver://localhost:1433;databaseName=db_Veterinaria;integratedSecurity=true;",
							"", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		if (con != null) {
			return true;
		} else
			return false;

	}

	public ArrayList<Cliente> devolverClientes (){

		ArrayList<Cliente> a_Cliente = new ArrayList<Cliente>();
		
		try {
			CallableStatement cs = con.prepareCall("{call sp_return_clientes()}");
			rs = cs.executeQuery();
			while (rs.next()){
				Cliente p = new Cliente();
				p.setId(rs.getString(1));
				p.setNombre(rs.getString(2));
				p.setApellido(rs.getString(3));
				p.setTipoDocumento(rs.getString(4));
				p.setDocumento(rs.getString(5));
				p.setDireccion(rs.getString(6));
				p.setOcupacion(rs.getString(7));
				p.setTelefono(rs.getString(8));
				p.setEmail(rs.getString(9));
				p.setTipoPago(rs.getString(10));
				a_Cliente.add(p);
			}
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		return a_Cliente;
	}

	public ArrayList<Proveedor> devolverProveedores (){
		
		ArrayList<Proveedor> a_Proveedor = new ArrayList<Proveedor>();
		
		try {
			CallableStatement cs = con.prepareCall("{call sp_return_proveedores}");
			rs = cs.executeQuery();
			while (rs.next()){
				Proveedor p = new Proveedor();
				p.setId(rs.getString(1));
				p.setRazonSocial(rs.getString(2));
				p.setCuit(rs.getString(3));
				p.setDireccion(rs.getString(4));
				p.setTelefono(rs.getString(5));
				p.setFax(rs.getString(6));
				p.setEmail(rs.getString(7));
				p.setFechaUltimaCompra(rs.getString(8));
				a_Proveedor.add(p);
			}
		} catch (SQLException e){
			System.out.println(e.getStackTrace());
		}
		
		return a_Proveedor;
	}

	public ArrayList<Producto> devolverProductos (){

		ArrayList<Producto> a_Producto = new ArrayList<Producto>();
		
		try {
			CallableStatement cs = con.prepareCall("{call sp_return_productos}");
			rs = cs.executeQuery();
			while (rs.next()){
				Producto a = new Producto();
				a.setId(rs.getString(1));
				a.setNombre(rs.getString(2));
				a.setMedida(rs.getString(3));
				a.setNombreCientifico(rs.getString(4));
				a.setNombreVulgar(rs.getString(5));
				a.setDescripcion(rs.getString(6));
				a.setIdProveedor(rs.getString(7));
				a.setPrecioVenta(Float.valueOf(rs.getString(8)));
				a.setCantidad(Integer.valueOf(rs.getString(9)));
				a_Producto.add(a);
			}
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		
		return a_Producto;
	}

	public ArrayList<Empleado> devolverVeterinarios (){

		ArrayList<Empleado> a_Veterinario = new ArrayList<Empleado>();
		
		try {
			CallableStatement cs = con.prepareCall("{call sp_return_veterinarios}");
			rs = cs.executeQuery();
			while (rs.next()){
				Empleado v = new Empleado();
				v.setId(rs.getString(1));
				v.setNombre(rs.getString(2));
				v.setApellido(rs.getString(3));
				v.setTipoDoc(rs.getString(4));
				v.setDocumento(rs.getString(5));
				v.setDomicilio(rs.getString(6));
				v.setTelefono(rs.getString(7));
				v.setEmail(rs.getString(8));
				v.setMatricula(rs.getString(9));
				a_Veterinario.add(v);
			}
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
		
		return a_Veterinario;
	}

	public ArrayList<Empleado> devolverEmpleados(){

		ArrayList<Empleado> a_Empleado = new ArrayList<Empleado>();
		
		try {
			CallableStatement cs = con.prepareCall("{call sp_return_empleados}");
			rs = cs.executeQuery();
			while (rs.next()){
				Empleado e = new Empleado();
				e.setId(rs.getString(1));
				e.setNombre(rs.getString(2));
				e.setApellido(rs.getString(3));
				e.setTipoDoc(rs.getString(4));
				e.setDocumento(rs.getString(5));
				e.setDomicilio(rs.getString(6));
				e.setTelefono(rs.getString(7));
				e.setFechaNacimiento(rs.getString(8));
				e.setMatricula(rs.getString(9));
				a_Empleado.add(e);
			}
		} catch (SQLException e){
			System.out.println(e.getStackTrace());
		}
		
		return a_Empleado;
	}

	public ArrayList<Mascota> devolverClienteMascotas(Cliente clie){

		ArrayList<Mascota> a_ClienteMascota = new ArrayList<Mascota>();

		try{
			CallableStatement cs = con.prepareCall("{call sp_return_cliente_mascotas(?)}");
			cs.setString(1, clie.getId());
			rs = cs.executeQuery();
			while(rs.next()){
				Mascota m = new Mascota();
				m.setId(rs.getString(1));
				m.setNombreCientifico(rs.getString(2));
				m.setNombreVulgar(rs.getString(3));
				m.setDescripcion(rs.getString(4));
				m.setIdCliente(rs.getString(5));
				a_ClienteMascota.add(m);
			}
		} catch(SQLException e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
		
		return a_ClienteMascota;
	}

	public ArrayList<Producto> devolverProveedorProductos(Proveedor prov){
		
		ArrayList<Producto> a_Producto = new ArrayList<Producto>();
		
		try {
			CallableStatement cs = con.prepareCall("{call sp_return_proveedor_productos(?)}");
			cs.setString(1, String.valueOf(prov.getId()));
			rs = cs.executeQuery();
			while (rs.next()){
				Producto p = new Producto();
				p.setId(rs.getString(1));
				p.setNombre(rs.getString(2));
				p.setMedida(rs.getString(3));
				p.setNombreCientifico(rs.getString(4));
				p.setNombreVulgar(rs.getString(5));
				p.setDescripcion(rs.getString(6));
				p.setPrecioVenta(Float.valueOf(rs.getString(7)));
				p.setCantidad(Integer.valueOf(rs.getString(8)));
				a_Producto.add(p);
			}
		} catch(SQLException e) {
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
		
		return a_Producto;
	}

	public void altaEmpleado(Empleado em) throws insertDBException{

		try {
			CallableStatement cs = con.prepareCall("{call sp_insert_empleado(?,?,?,?,?,?,?,?)}");
			cs.setString(1, em.getNombre());
			cs.setString(2, em.getApellido());
			cs.setString(3, em.getTipoDoc());
			cs.setString(4, em.getDocumento());
			cs.setString(5, em.getDomicilio());
			cs.setString(6, em.getTelefono());
			cs.setString(7, em.getFechaNacimiento());
			cs.setString(8, em.getMatricula());
			int i = cs.executeUpdate();
			if(i<0){
				throw new insertDBException("Error al querer ingresar un registro");
			}
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
	}

	public void altaCliente(Cliente c) throws insertDBException{

		try{
			CallableStatement cs = con.prepareCall("{call sp_insert_cliente(?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, c.getNombre());
			cs.setString(2, c.getApellido());
			cs.setString(3, c.getTipoDocumento());
			cs.setString(4, c.getDocumento());
			cs.setString(5, c.getDireccion());
			cs.setString(6, c.getOcupacion());
			cs.setString(7, c.getTelefono());
			cs.setString(8, c.getEmail());
			cs.setString(9, c.getTipoPago());
			int i = cs.executeUpdate();	
			if(i<0){
				throw new insertDBException("Error al querer ingresar un registro");
			}
			
		} catch (SQLException e) {
			System.out.println( e.getMessage());
		}
		
	}

	public void altaClienteMascota(Mascota m) throws insertDBException{

		try {
			CallableStatement cs = con.prepareCall("{call sp_insert_mascota(?,?,?,?)}");
			cs.setString(1, m.getNombreCientifico());
			cs.setString(2, m.getNombreVulgar());
			cs.setString(3, m.getDescripcion());
			cs.setString(4, String.valueOf(m.getIdCliente()));
			int i = cs.executeUpdate();
			if(i<0){
				throw new insertDBException("Error al querer ingresar un registro");
			}
		} catch (SQLException e){
			System.out.println( e.getMessage());
		}
	}

	public void altaProveedor(Proveedor p) throws insertDBException{

		try {
			CallableStatement cs = con.prepareCall("{call sp_insert_proveedor(?,?,?,?,?,?,?)}");
			cs.setString(1, p.getRazonSocial());
			cs.setString(2, p.getCuit());
			cs.setString(3, p.getDireccion());
			cs.setString(4, p.getTelefono());
			cs.setString(5, p.getFax());
			cs.setString(6, p.getEmail());
			cs.setString(7, p.getFechaUltimaCompra());
			int i = cs.executeUpdate();
			if(i<0){
				throw new insertDBException("Error al querer ingresar un registro");
			}
		} catch (SQLException e){
			System.out.println(e.getStackTrace());
		}
	}

	public void altaProducto(Producto p) throws insertDBException{
		
		try {
			CallableStatement cs = con.prepareCall("{call sp_insert_producto(?,?,?,?,?,?)}");
			cs.setString(1, p.getNombre());
			cs.setString(2, p.getMedida());
			cs.setString(3, p.getNombreCientifico());
			cs.setString(4, p.getNombreVulgar());
			cs.setString(5, p.getDescripcion());
			cs.setString(6, p.getIdProveedor());
			int i = cs.executeUpdate();
			if(i<0){
				throw new insertDBException("Error al hacer update en tabla dbo.Producto. ");
			}
		} catch(SQLException e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
	}

	public void altaAtencion(Atencion at) throws insertDBException{

		try {
			CallableStatement cs = con.prepareCall("{call sp_insert_atencion(?,?,?,?)}");
			cs.setString(1, String.valueOf(at.getIdVeterinario()));
			cs.setString(2, String.valueOf(at.getIdMascota()));
			cs.setString(3, at.getTipoConsulta());
			cs.setString(4, at.getDiagnostico());
			int i = cs.executeUpdate();
			if(i<0){
				throw new insertDBException("Error al querer ingresar un registro");
			}
		} catch (SQLException e){
			System.out.println(e.getStackTrace());
		}
	}

	public void altaVenta(ArrayList<Venta> v) throws insertDBException{

		try {

			CallableStatement cs_ = con.prepareCall("{call sp_insert_log_ventas(?,?,?,?,?,?,?,?)}");
			Iterator<Venta> it = v.iterator();
			Venta tmp;
			while(it.hasNext()){
				tmp = it.next();
				cs_.setString(1, tmp.getTipoFactura());
				cs_.setString(2, String.valueOf(tmp.getIdCliente()));
				cs_.setString(3, String.valueOf(tmp.getIdVendedor()));
				cs_.setString(4, String.valueOf(tmp.getIdProducto()));
				cs_.setString(5, String.valueOf(tmp.getIdProveedor()));
				cs_.setString(6, String.valueOf(tmp.getCantidad()));
				cs_.setString(7, String.valueOf(tmp.getPrecio()));
				cs_.setString(8, tmp.getEstadoOperacion());
				int j = cs_.executeUpdate();
				if(j<0){
					throw new insertDBException("Error al querer ingresar un registro");
				}
			}
			cs_.close();

			CallableStatement cs = con.prepareCall("{call sp_insert_compra_cliente}");
			int i = cs.executeUpdate();
			if(i<=0){
				throw new insertDBException("Error al querer ingresar un registro");
			}
			cs.close();

		} catch (SQLException e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
			System.out.println(e.getNextException());
			}
	}

	public void altaCompra(ArrayList<Compra> com) throws insertDBException{
		
		try {
			CallableStatement cs = con.prepareCall("{call sp_insert_log_compras(?,?,?,?,?,?,?,?)}");
			Iterator<Compra> it = com.iterator();
			Compra tmp;
			while (it.hasNext()){
				tmp = it.next();
				cs.setString(1, tmp.getTipo_factura());
				cs.setString(2, String.valueOf(tmp.getIdEmpleado()));
				cs.setString(3, String.valueOf(tmp.getIdProveedor()));
				cs.setString(4, String.valueOf(tmp.getIdProducto()));
				cs.setString(5, String.valueOf(tmp.getPrecio_costo()));
				cs.setString(6, String.valueOf(tmp.getPrecio_venta()));
				cs.setString(7, String.valueOf(tmp.getCantidad()));
				cs.setString(8, tmp.getEstadoOperacion());
				int i = cs.executeUpdate();
				if (i<0){
					throw new insertDBException("Error al querer ingresar un registro");
				}

			}
			cs.close();
				
			CallableStatement cs_ = con.prepareCall("{call sp_insert_compra_proveedor}");
			int j = cs_.executeUpdate();
			if (j<0){
				throw new insertDBException("Error al querer ingresar un registro");
			}
			cs_.close();
							
		} catch (SQLException e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
	}

	public void modificacionCliente(Cliente clie) throws insertDBException{

		try {
			CallableStatement cs = con.prepareCall("{call sp_update_cliente(?,?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, clie.getId());
			cs.setString(2, clie.getNombre());
			cs.setString(3, clie.getApellido());
			cs.setString(4, clie.getTipoDocumento());
			cs.setString(5, clie.getDocumento());
			cs.setString(6, clie.getDireccion());
			cs.setString(7, clie.getOcupacion());
			cs.setString(8, clie.getTelefono());
			cs.setString(9, clie.getEmail());
			cs.setString(10, clie.getTipoPago());
			int i = cs.executeUpdate();
			if(i<0){
				throw new insertDBException("Error al hacer update en tabla dbo.Cliente. ");
			}
		} catch (SQLException e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
	}

	public void modificacionProveedor(Proveedor prov) throws insertDBException{

		try {
			CallableStatement cs = con.prepareCall("{call sp_update_proveedor(?,?,?,?,?,?,?)}");
			cs.setString(1, String.valueOf(prov.getId()));
			cs.setString(2, prov.getRazonSocial());
			cs.setString(3, prov.getCuit());
			cs.setString(4, prov.getDireccion());
			cs.setString(5, prov.getTelefono());
			cs.setString(6, prov.getFax());
			cs.setString(7, prov.getEmail());
			int i = cs.executeUpdate();
			if(i<0){
				throw new insertDBException("Error al hacer update en tabla dbo.Proveedor. ");
			}
		} catch(SQLException e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
	}

	public void modificacionEmpleado(Empleado emp) throws insertDBException{

		try {
			CallableStatement cs = con.prepareCall("{call sp_update_empleado(?,?,?,?,?,?,?,?,?)}");
			cs.setString(1, String.valueOf(emp.getId()));
			cs.setString(2, emp.getNombre());
			cs.setString(3, emp.getApellido());
			cs.setString(4, emp.getTipoDoc());
			cs.setString(5, emp.getDocumento());
			cs.setString(6, emp.getDomicilio());
			cs.setString(7, emp.getTelefono());
			cs.setString(8, emp.getFechaNacimiento());
			cs.setString(9, emp.getMatricula());
			int i = cs.executeUpdate();
			if(i<0){
				throw new insertDBException("Error al hacer update en tabla dbo.Empleado. ");
			}
		} catch(SQLException e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
	}

	public void modificacionMascota(Mascota m) throws insertDBException{

		try {
			CallableStatement cs = con.prepareCall("{call sp_update_mascota(?,?,?)}");
			cs.setString(1, String.valueOf(m.getId()));
			cs.setString(2, m.getNombreVulgar());
			cs.setString(3, m.getDescripcion());
			int i = cs.executeUpdate();
			if(i<0){
				throw new insertDBException("Error al hacer update en tabla dbo.Mascota. ");
			}
		} catch (SQLException e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
	}

	public void modificacionProducto(Producto p) throws insertDBException{

		try {
			CallableStatement cs = con.prepareCall("{call sp_update_producto(?,?,?,?,?,?)}");
			cs.setString(1, p.getId());
			cs.setString(2, p.getNombre());
			cs.setString(3, p.getMedida());
			cs.setString(4, p.getNombreCientifico());
			cs.setString(5, p.getNombreVulgar());
			cs.setString(6, p.getDescripcion());
			int i = cs.executeUpdate();
			if(i<0){
				throw new insertDBException("Error al hacer update en tabla dbo.Producto. ");
			}
		} catch(SQLException e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
	}

	public void bajaProveedor(Proveedor prov) throws insertDBException{

		try{
			CallableStatement cs = con.prepareCall("{call sp_delete_proveedor(?)}");
			cs.setString(1, String.valueOf(prov.getId()));
			int i = cs.executeUpdate();
			if(i<0){
				throw new insertDBException("Error al hacer update en tabla dbo.Proveedor. ");
			}
		} catch (SQLException e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
	}

	public void bajaCliente(Cliente clie) throws insertDBException{

		try {
			CallableStatement cs = con.prepareCall("{call sp_delete_cliente(?)}");
			cs.setString(1, clie.getId());
			int i = cs.executeUpdate();
			if(i<0){
				throw new insertDBException("Error al hacer update en tabla dbo.Proveedor. ");
			}
		} catch(SQLException e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
	}

	public void bajaEmpleado(Empleado emp) throws insertDBException{

		try{
			CallableStatement cs = con.prepareCall("{call sp_delete_empleado(?)}");
			cs.setString(1, String.valueOf(emp.getId()));
			int i = cs.executeUpdate();
			if(i<0){
				throw new insertDBException("Error al hacer update en tabla dbo.Proveedor. ");
			}
		} catch(SQLException e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
	}

	public void bajaMascotaCliente(Mascota m) throws insertDBException{

		try {
			CallableStatement cs = con.prepareCall("{call sp_delete_mascota_cliente(?,?)}");
			cs.setString(1, m.getId());
			cs.setString(2, String.valueOf(m.getIdCliente()));
			int i = cs.executeUpdate();
			if(i<0){
				throw new insertDBException("Error al hacer update en tabla dbo.Proveedor. ");
			}
		} catch(SQLException e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
	}

	public String informeClienteFrecuente(){
		String cliente = null;
		
		try {
			CallableStatement cs = con.prepareCall("{call sp_report_cliente_frecuente}");
			rs = cs.executeQuery();
			while (rs.next()){
				cliente = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
		return cliente;
	}

	public int informeMascotasPorVeterinario(Empleado emp){
		int cantidad = 0;

		try {
			CallableStatement cs = con.prepareCall("{call sp_report_mascotas_atendidas_por_veterinario(?)}");
			cs.setString(1, emp.getDocumento());
			rs = cs.executeQuery();
			while (rs.next()){
				cantidad = Integer.valueOf(rs.getString(1));
			}
		} catch(SQLException e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}

		return cantidad;
	}

	
	public String informeMejorVendedor(){
		String nombyape = null;
		try {
			CallableStatement cs = con.prepareCall("{call sp_report_mejor_vendedor}");
			rs = cs.executeQuery();
			while(rs.next()){
				nombyape = rs.getString(1);
			}
		} catch (SQLException e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
		
		return nombyape;
		
	}
	
	public ArrayList<Producto> informeAnimalesPorRaza(){
		ArrayList<Producto> a_Producto = new ArrayList<Producto>();

		try {
			CallableStatement cs = con.prepareCall("{call sp_report_animales_por_raza}");
			rs = cs.executeQuery();
			while(rs.next()){
				Producto p = new Producto();
				p.setNombreCientifico(rs.getString(1));
				p.setCantidad(Integer.valueOf(rs.getString(2)));
				a_Producto.add(p);
			}
		} catch(SQLException e){
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}
		return a_Producto;
	}

	public void cerrarBusqueda() {
		try {
			rs.close();
			select.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
