package Clases;


public class Compra {

	private String tipo_factura;
	private int idEmpleado;
	private int idProveedor;
	private int idProducto;
	private Float precio_costo;
	private Float precio_venta;
	private int cantidad;
	private String estadoOperacion;

	
	public Compra(){
		
	}
	
	public Compra(String tipo_factura, int idEmpleado, int idProveedor, int idProducto, Float precio_costo, Float precio_venta, int cantidad, String estadoOperacion){
		this.tipo_factura = tipo_factura;
		this.idEmpleado = idEmpleado;
		this.idProveedor = idProveedor;
		this.idProducto = idProducto;
		this.precio_costo = precio_costo;
		this.precio_venta = precio_venta;
		this.cantidad = cantidad;
		this.estadoOperacion = estadoOperacion;
	}

	public String getTipo_factura() {
		return tipo_factura;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public String getEstadoOperacion() {
		return estadoOperacion;
	}

	public void setTipo_factura(String tipo_factura) {
		this.tipo_factura = tipo_factura;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public void setEstadoOperacion(String estadoOperacion) {
		this.estadoOperacion = estadoOperacion;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public Float getPrecio_costo() {
		return precio_costo;
	}

	public Float getPrecio_venta() {
		return precio_venta;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public void setPrecio_costo(Float precio_costo) {
		this.precio_costo = precio_costo;
	}

	public void setPrecio_venta(Float precio_venta) {
		this.precio_venta = precio_venta;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}

