package sistemaOlvera;

public class NotaData {
	private String codigo;
	private String nombre;
	private String direccion;
	private String telefono;
	private String nota;
	private String entrega;
	private String hora;
	private String fecha;
	private String rfc;
	private String cantidad;
	private String unitario;
	private String importe;
	private String descripcion;
	private String subTotal;
	private String anticipo;
	private String total;
	private String letras;
	private String observaciones;
	private String elaboro;
	private String barras;
	private String blanco;
	private String color;
	public NotaData(String nombre, String direccion, String telefono, String nota, String elaboro, String entrega, String hora, String fecha, String rfc,String codigo, String cantidad, String unitario, String importe, String descripcion, String subTotal, String anticipo, String total, String letras, String observaciones, String barras, String color, String blanco){
		this.nombre = nombre;
		this.direccion = direccion;
		this.barras = barras;
		this.telefono = telefono;
		this.nota = nota;
		this.entrega = entrega;
		this.hora = hora;
		this.rfc = rfc;
		this.cantidad = cantidad;
		this.unitario = unitario;
		this.importe = importe;
		this.descripcion = descripcion;
		this.subTotal = subTotal;
		this.anticipo = anticipo;
		this.total = total;
		this.letras = letras;
		this.codigo = codigo;
		this.fecha = fecha;
		this.observaciones = observaciones;
		this.elaboro = elaboro;
		this.color=color;
		this.blanco=blanco;
	}
	public String getElaboro(){
		return elaboro;
	}
	public String getNombre(){
		return nombre;
	}
	public String getDomicilio(){
		return direccion;
	}
	public String getTelefono(){
		return telefono;
	}
	public String getNumero(){
		return nota;
	}
	public String getEntrega(){
		return entrega;
	}
	public String getHora(){
		return hora;
	}
	public String getRfc(){
		return rfc;
	}
	public String getCantidad(){
		return cantidad;
	}
	public String getUnitario(){
		return unitario;
	}
	public String getImporte(){
		return importe;
	}
	public String getDescripcion(){
		return descripcion;
	}
	public String getSub(){
		return subTotal;
	}
	public String getAnticipo(){
		return anticipo;
	}
	public String getSaldo(){
		return total;
	}
	public String getLetra(){
		return this.letras;
	}
	public String getCodigo(){
		return this.codigo;
	}
	public String getFecha(){
		return this.fecha;
	}
	public String getObservaciones(){
		return this.observaciones;
	}
	public String getBarras(){
		return this.barras;
	}
	public String getBlanco(){
		return this.blanco;
	}
	public String getColor(){
		return this.color;
	}
	public void setBarras(String barras){
		this.barras = barras;
	}
}
