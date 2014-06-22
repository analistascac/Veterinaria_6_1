package Clases;

import java.util.ArrayList;

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
	private ArrayList<String> proveedores = null;

	public Producto() {
		id = "";
		nombre = "";
		medida = "";
		nombreCientifico = "";
		nombreVulgar = "";
		descripcion = "";
		precioCosto = 0;
		precioVenta = 0;
		limiteInferior = 0;
		proveedores = new ArrayList<String>();

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

	public ArrayList<String> getProveedores() {
		return proveedores;
	}

	public void setProveedores(ArrayList<String> proveedores) {
		this.proveedores = proveedores;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", \nnombre=" + nombre + ", \nmedida="
				+ medida + ", \nnombre_cientifico=" + nombreCientifico
				+ ", \nnombre_vulgar=" + nombreVulgar + ", \ndescripcion="
				+ descripcion + ", \nprecio_costo=" + precioCosto
				+ ", \nprecio_venta=" + precioVenta + ", \nlimite_inferior="
				+ limiteInferior + ", \nproveedores=" + proveedores + "]";
	}

}
