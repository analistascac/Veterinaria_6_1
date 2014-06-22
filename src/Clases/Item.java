package Clases;

public class Item {

	private String id;
	private String texto;

	public Item(String id, String name) {
		this.id = id;
		this.texto = name;
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

	public void setTexto(String name) {
		this.texto = name;
	}

	public String toString() {
		return this.texto;
	}
}