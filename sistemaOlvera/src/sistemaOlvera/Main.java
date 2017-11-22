package sistemaOlvera;



public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Conexion datos = new Conexion();
		datos.conectar();
		InicioSesion window1 = new InicioSesion(datos);
		window1.setVisible(true);
	}

}
