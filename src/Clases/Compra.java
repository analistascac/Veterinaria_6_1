package Clases;


public class Compra {

	private String tipo_factura;
	private String idEmpleado;
	private String idProveedor;
	private String idProducto;
	private Double precio_costo;
	private Double precio_venta;
	private int cantidad;
	private String estadoOperacion;

	
	public Compra(){
		
	}
	
	public Compra(String tipo_factura, String idEmpleado, String idProveedor, String idProducto, Double precio_costo, Double precio_venta, int cantidad, String estadoOperacion){
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

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public String getEstadoOperacion() {
		return estadoOperacion;
	}

	public void setTipo_factura(String tipo_factura) {
		this.tipo_factura = tipo_factura;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public void setEstadoOperacion(String estadoOperacion) {
		this.estadoOperacion = estadoOperacion;
	}

	public String getIdProveedor() {
		return idProveedor;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public Double  getPrecio_costo() {
		return precio_costo;
	}

	public Double getPrecio_venta() {
		return precio_venta;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public void setPrecio_costo(Double precio_costo) {
		this.precio_costo = precio_costo;
	}

	public void setPrecio_venta(Double precio_venta) {
		this.precio_venta = precio_venta;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Compra [tipo_factura=" + tipo_factura + ", \nidEmpleado="
				+ idEmpleado + ", \nidProveedor=" + idProveedor + ", \nidProducto="
				+ idProducto + ", \nprecio_costo=" + precio_costo
				+ ", \nprecio_venta=" + precio_venta + ", \ncantidad=" + cantidad
				+ ", \nestadoOperacion=" + estadoOperacion + "]";
	}
	
	
	
}

