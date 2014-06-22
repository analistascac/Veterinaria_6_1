package Clases;

public class Atencion {

	private String id;
	private String fecha;
	private String idVeterinario;
	private String idCliente;
	private String idMascota;
	private String tipoConsulta;
	private String diagnostico;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdVeterinario() {
		return idVeterinario;
	}

	public void setIdVeterinario(String idVeterinario) {
		this.idVeterinario = idVeterinario;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(String idMascota) {
		this.idMascota = idMascota;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		String texto;

		texto = "Id = " + id + "\nVeterinario = " + idVeterinario
				+ "\nCliente = " + idCliente + "\nMascota = " + idMascota
				+ "\nTipo de consulta = " + tipoConsulta + "\nDiagnostico = "
				+ diagnostico + "\nFecha = " + fecha;

		return texto;
	}

}
