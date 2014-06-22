package Clases;

import java.util.ArrayList;

public class Venta {
	private String tipo_factura;
	private String numero_factura;
	private String fecha;
	private String cliente;
	private String vendedor;
	private float importe_total;
	private ArrayList<String> detalle;
	private ArrayList<String> cantidad_articulos;
	private String estado_operacion; 
	
	public Venta(){
		tipo_factura = "";
		numero_factura = "";
		fecha = "";
		cliente = "";
		vendedor = "";
		importe_total = 0;
		detalle = new ArrayList();
		cantidad_articulos = new ArrayList();
		estado_operacion = "";
	}

	public String getTipo_factura() {
		return tipo_factura;
	}

	public void setTipo_factura(String tipo_factura) {
		this.tipo_factura = tipo_factura;
	}

	public String getNumero_factura() {
		return numero_factura;
	}

	public void setNumero_factura(String numero_factura) {
		this.numero_factura = numero_factura;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public float getImporte_total() {
		return importe_total;
	}

	public void setImporte_total(float importe_total) {
		this.importe_total = importe_total;
	}
	
	public void agregarDetalle(String esto, int cant){
		detalle.add(esto);
		cantidad_articulos.add(cant+"");
	}
	
	public void borrarDetalle(int i){
		detalle.remove(i);
		cantidad_articulos.remove(i);
	}
	
	public String elemento(int i){
		return detalle.get(i);
	}
	
	public ArrayList getDetalle(){
		return detalle;
	}

	public String getEstado_operacion() {
		return estado_operacion;
	}

	public void setEstado_operacion(String estado_operacion) {
		this.estado_operacion = estado_operacion;
	}

	@Override
	public String toString() {
		return "Venta [tipo_factura=" + tipo_factura + ", \nnumero_factura="
				+ numero_factura + ", \nfecha=" + fecha + ", \ncliente=" + cliente
				+ ", \nvendedor=" + vendedor + ", \nimporte_total=" + importe_total
				+ ", \ndetalle=" + detalle + ", \nestado_operacion="
				+ estado_operacion + "]";
	}
	
	
}
