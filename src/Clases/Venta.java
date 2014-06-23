package Clases;

public class Venta {
	private String tipoFactura;
	private String idCliente;
	private String idVendedor;
	private String idProducto;
	private String idProveedor;
	private String cantidad;
	private String precio;
	private String estadoOperacion;

	public Venta() {

	}

	public String getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(String idVendedor) {
		this.idVendedor = idVendedor;
	}

	public String getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	public String getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getEstadoOperacion() {
		return estadoOperacion;
	}

	public void setEstadoOperacion(String estadoOperacion) {
		this.estadoOperacion = estadoOperacion;
	}

	@Override
	public String toString() {
		return "Venta [tipoFactura=" + tipoFactura + ", idCliente=" + idCliente
				+ ", idVendedor=" + idVendedor + ", idProducto=" + idProducto
				+ ", idProveedor=" + idProveedor + ", cantidad=" + cantidad
				+ ", precio=" + precio + ", estadoOperacion=" + estadoOperacion
				+ "]";
	}

}
