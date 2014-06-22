package Clases;

public class Proveedor {
	private String id;
	private String razonSocial;
	private String cuit;
	private String direccion;
	private String telefono;
	private String fax;
	private String email;
	private String fechaUltimaCompra;

	public Proveedor() {
		id = "";
		razonSocial = "";
		cuit = "";
		direccion = "";
		telefono = "";
		fax = "";
		email = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaUltimaCompra() {
		return fechaUltimaCompra;
	}

	public void setFechaUltimaCompra(String fechaUltimaCompra) {
		this.fechaUltimaCompra = fechaUltimaCompra;
	}

	public String toString() {
		String texto;
		texto = "id = " + id + "\n razon_social = " + razonSocial
				+ "\n cuit = " + cuit + "\n direccion = " + direccion
				+ "\n telefono = " + telefono + "\n fax = " + fax
				+ "\n email = " + email;
		return texto;
	}

}
