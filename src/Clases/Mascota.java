package Clases;

public class Mascota {
	private String id;
	private String idCliente;
	private String nombreCientifico;
	private String nombreVulgar;
	private String descripcion;

	public Mascota() {
		id = "";
		idCliente = "";
		nombreCientifico = "";
		nombreVulgar = "";
		descripcion = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String dueno) {
		this.idCliente = dueno;
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

	@Override
	public String toString() {
		return "Mascota [id=" + id + ",\ndueno=" + idCliente
				+ ",\nnombre_cientifico=" + nombreCientifico
				+ ",\nnombre_vulgar=" + nombreVulgar + ",\ndescripcion="
				+ descripcion + "]";
	}

}
