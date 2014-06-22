package Clases;

public class Empleado {
	private String id;
	private String apellido;
	private String nombre;
	private String tipoDoc;
	private String documento;
	private String domicilio;
	private String telefono;
	private String fechaNacimiento;
	private String matricula;
	private String email;

	public Empleado() {
		id = "";
		apellido = "";
		nombre = "";
		tipoDoc = "";
		documento = "";
		domicilio = "";
		telefono = "";
		fechaNacimiento = "";
		matricula = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String doc) {
		this.documento = doc;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		String texto;

		texto = "id = " + id + "\napellido = " + apellido + "\nnombre = "
				+ nombre + "\ntipo_doc = " + tipoDoc + "\ndoc = " + documento
				+ "\ndomicilio = " + domicilio + "\ntelefono = " + telefono
				+ "\nfecha_nacimiento = " + fechaNacimiento + "\nmatricula = "
				+ matricula;

		return texto;
	}

}
