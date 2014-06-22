package Clases;

public class Cliente {
	private String id;
	private String nombre;
	private String apellido;
	private String tipoDocumento;
	private String documento;
	private String direccion;
	private String ocupacion;
	private String telefono;
	private String email;
	private String tipoPago;

	public Cliente(String identificador, String nombre, String apellido,
			String tipo_documento, String documento, String direccion,
			String ocupacion, String telefono, String email, String tipo_pago) {
		super();
		this.id = identificador;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDocumento = tipo_documento;
		this.documento = documento;
		this.direccion = direccion;
		this.ocupacion = ocupacion;
		this.telefono = telefono;
		this.email = email;
		this.tipoPago = tipo_pago;
	}

	public Cliente() {
		id = "";
		nombre = "";
		apellido = "";
		tipoDocumento = "";
		documento = "";
		direccion = "";
		ocupacion = "";
		telefono = "";
		email = "";
		tipoPago = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String identificador) {
		this.id = identificador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	@Override
	public String toString() {
		String texto;

		texto = "\nid = " + id + "\nnombre = " + nombre + "\napellido = "
				+ apellido + "\ntipo_documento = " + tipoDocumento
				+ "\ndocumento = " + documento + "\ndireccion = " + direccion
				+ "\nocupacion = " + ocupacion + "\ntelefono = " + telefono
				+ "\nemail = " + email + "\ntipo_pago = " + tipoPago;

		return texto;
	}

}
