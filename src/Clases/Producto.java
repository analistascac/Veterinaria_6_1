package Clases;

public class Producto {
	private String id;
	private String nombre;
	private String medida;
	private String nombreCientifico;
	private String nombreVulgar;
	private String descripcion;
	private float precioCosto;
	private float precioVenta;
	private int limiteInferior;
	private int cantidad;
	private String idProveedor;

	public Producto() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public String getNombreCientifico() {
		return nombreCientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}

	public String getNombreVulgar() {
		return nombreVulgar;
	}

	public void setNombreVulgar(String nombreVulgar) {
		this.nombreVulgar = nombreVulgar;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecioCosto() {
		return precioCosto;
	}

	public void setPrecioCosto(float precioCosto) {
		this.precioCosto = precioCosto;
	}

	public float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getLimiteInferior() {
		return limiteInferior;
	}

	public void setLimiteInferior(int limiteInferior) {
		this.limiteInferior = limiteInferior;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", \nnombre=" + nombre + ", \nmedida="
				+ medida + ", \nnombre_cientifico=" + nombreCientifico
				+ ", \nnombre_vulgar=" + nombreVulgar + ", \ndescripcion="
				+ descripcion + ", \nprecio_costo=" + precioCosto
				+ ", \nprecio_venta=" + precioVenta + ", \nlimite_inferior="
				+ limiteInferior + ", \nid del proveedor=" + idProveedor + "]";
	}

}
