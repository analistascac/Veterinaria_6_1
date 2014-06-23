package Clases;

public class Item {

	private String id;
	private String texto;
	private String descripcion1;
	private String descripcion2;
	private String descripcion3;

	public Item() {
		// TODO Auto-generated constructor stub
	}

	public String getDescripcion3() {
		return descripcion3;
	}

	public void setDescripcion3(String descripcion3) {
		this.descripcion3 = descripcion3;
	}

	public Item(String id, String name) {
		this.id = id;
		this.texto = name;
	}

	public Item(String id, String texto, String descripcion) {
		super();
		this.id = id;
		this.texto = texto;
		this.descripcion1 = descripcion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getDescripcion1() {
		return descripcion1;
	}

	public void setDescripcion1(String descripcion1) {
		this.descripcion1 = descripcion1;
	}

	public String getDescripcion2() {
		return descripcion2;
	}

	public void setDescripcion2(String descripcion2) {
		this.descripcion2 = descripcion2;
	}

	public String toString() {
		return this.texto;
	}
}