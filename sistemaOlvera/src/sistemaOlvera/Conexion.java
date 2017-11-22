package sistemaOlvera;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Conexion {
	Connection con = null;
	int usuarioRegistro=0;
	//Method for connection-------------------------------------------------------------------------------
	public void conectar() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//con = DriverManager.getConnection("jdbc:mysql://31.220.104.1/u708683649_datos?user=u708683649_datos&password=olveradatos");
			con = DriverManager.getConnection("jdbc:mysql://localhost/olvera?user=root&password=");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Error:"+e.toString(), "Error en la conexión", JOptionPane.ERROR_MESSAGE);
		}
	}
	//Metodos para ingresar al sistema--------------------------------------------------------------------------------------------
	//Method for admin----------------------------------------------------------------------------------------
	public int iniciaAdmin(String usuario, String password){
		String sql = "SELECT id, tipo FROM usuarios WHERE usuario = '"+usuario+"' AND password='"+password+"'";
		int resultado=0;
		try{
			con.close();
			conectar();
			Statement cmd = con.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			while(rs.next()){
				usuarioRegistro = rs.getInt(1);
				resultado = rs.getInt(2);
			}
			rs.close();
			cmd.close();
		}
		catch(Exception e){
			resultado=0;
			JOptionPane.showMessageDialog(null, "Error: "+e.toString(), "Error con base de datos.", JOptionPane.ERROR_MESSAGE);
		}
		
		return resultado;
	}
	
	//Métodos para el administrador-------------------------------------------------------------------------------------------------
	//Métodos para la clase de UserAdmin--------------------------------------------------------------------------------
	//Método para buscar si el usuario ya existe------------------------------
	public boolean getUsuarioExiste(String sql){
		boolean resultado = false;
		try{
			con.close();
			conectar();
			Statement cmd = con.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			while(rs.next()){
				if(!(rs.getString(1).equals(""))){
					resultado = true;
				}
			}
			cmd.close();
			rs.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return resultado;
	}
	//Método para hacer todas las modificaciones de empleados-----------------
	public void Modificaciones(String sql){
		try{
			con.close();
			conectar();
			Statement cmd = con.createStatement();
			cmd.executeUpdate(sql);
			cmd.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	//Método para sacar todos empleados registrados--------------------------
	public DefaultTableModel getModelUsers(String sql){
		String[] titulosUsuario = {"Nombre", "Usuario", "Password", "Teléfono", "Dirección", "Correo", "Tipo" };
		DefaultTableModel modelo = new DefaultTableModel(titulosUsuario,0);
		Object []resultado = new Object[7];
		int columna =1;
		try{
			con.close();
			conectar();
			Statement cmd = con.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			while(rs.next()){
				while(columna<9){
					if(columna==1){
						resultado[columna-1]=rs.getString(columna);
						resultado[columna-1] += " "+rs.getString(columna+1);
						columna++;
					}
					else if(columna==8){
						if(rs.getInt(columna)==1){
							resultado[columna-2] = "Administrador";
						}
						else{
							resultado[columna-2] = "Empleado";
						}
					}
					else{
						resultado[columna-2] = rs.getString(columna);
					}
					columna++;
				}
				columna=1;
				modelo.addRow(resultado);
			}
			rs.close();
			cmd.close();			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return modelo;
	}
	//Método para sacar los productos registrados----------------------------------
	public DefaultTableModel getModelProductos(String sql){
		String[] titulos={"Código", "Nombre","Descripcion", "Precio"};
		DefaultTableModel modelo = new DefaultTableModel(titulos,0);
		Object []objeto = new Object[4];
		int columna=0;
		try{
			con.close();
			conectar();
			Statement cmd = con.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			while(rs.next()){
				while(columna<4){
					if(columna==0){
						objeto[columna]=rs.getString(columna+1);
					}
					else if(columna==1){
						objeto[columna]=rs.getString(columna+1);
					}
					else if(columna==2){
						objeto[columna]=rs.getString(columna+1);
					}
					else {
						objeto[columna]="$"+rs.getString(columna+1);
					}
					columna++;
				}
					modelo.addRow(objeto);
				columna=0;
			}	
			rs.close();
			cmd.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
			return modelo;
	}
	public DefaultTableModel getModelo(String sql, String[] titulos, int columnas){
		DefaultTableModel aux = new DefaultTableModel(titulos, 0);
		Object []objeto = new Object[columnas];
		int columna=0;
		try{
			con.close();
			conectar();
			Statement cmd = con.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			while(rs.next()){
				while(columna<columnas){
					try{	
						objeto[columna]=rs.getString(columna+1).toString();
					}
					catch(Exception e){
						objeto[columna]="";
					}
					columna++;
				}
				aux.addRow(objeto);
				columna=0;
			}
			rs.close();
			cmd.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return aux;
	}
	//Metodo para obtener un dato en especifico------------------------------------
	public String getDato(String sql){
		String resultado="";
		try{
			con.close();
			conectar();
			Statement cmd = con.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			while(rs.next()){
				resultado = rs.getString(1);
			}
			rs.close();
			cmd.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return resultado;
	}
	public ArrayList<String> getDatoArray(String sql){
		ArrayList<String> retorno = new ArrayList<String>();
		try{
			con.close();
			conectar();
			Statement cmd = con.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			while(rs.next()){
				retorno.add(rs.getString(1));
			}
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return retorno;
	}
	//Método para obtener productos y meterlos a una lista------------------------------
	public DefaultListModel<String> getLista(String sql){
		DefaultListModel<String> modelo = new DefaultListModel<String>();
		String datos;
		try{
			con.close();
			conectar();
			Statement cmd = con.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			while(rs.next()){
				datos = rs.getString(1);
				modelo.addElement(datos);
			}
			rs.close();
			cmd.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return modelo;
	}
	//-------------------------------------------------------------------------------------------------
	public ArrayList<String> getArray(String sql, int columnas){
		ArrayList<String> array = new ArrayList<String>();
		int col =1;
		try{
			con.close();
			conectar();
			Statement cmd = con.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			while(rs.next()){
				while (col<columnas+1){
					array.add(rs.getString(col));
					col++;
				}
				col=1;
			}
			cmd.close();
			rs.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return array;
	}
	public DefaultTableModel getModeloClientes(String sql, String[] titulos, int columnas){
		DefaultTableModel aux = new DefaultTableModel(titulos, 0);
		Object []objeto = new Object[columnas-1];
		int columna=0;
		try{
			con.close();
			conectar();
			Statement cmd = con.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			while(rs.next()){
				while(columna<columnas){
					if(columna>=2){
						if(columna==2){
							objeto[columna-1]+=rs.getString(columna+1).toString();
						}
						else{
							objeto[columna-1]=rs.getString(columna+1).toString();
						}
					}
					else{
						objeto[columna]=rs.getString(columna+1).toString()+" ";
					}
					columna++;
				}
				aux.addRow(objeto);
				columna=0;
			}
			rs.close();
			cmd.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return aux;
	}
	//Metodo para retornr objetos----------------------------------------------------------------------
	public Object[][] getObjeto(String sql,int filas, int columna){
		Object[][] objeto = new Object[filas][columna];
		int x=0;
		try{
			con.close();
			conectar();
			Statement cmd = con.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			while(rs.next()){
					for(int y =0; y<columna; y++){
						objeto[x][y]=rs.getString(y+1).toString();
					}
					x++;
			}
			rs.close();
			cmd.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return objeto;
	}
	//Método para tener el modelo de historial---------------------------------------------------------
	public DefaultTableModel getModeloHistorial(String sql){
		String[] titulosTrabajos = {"Número", "Descripción", "Estatus"};
		Object[] objeto = new Object[3];
		DefaultTableModel retornar = new DefaultTableModel(titulosTrabajos,0);
		int count=0;
		try{
			con.close();
			conectar();
			Statement cmd =con.createStatement();
			Statement cmd1 = con.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			while(rs.next()){
				ResultSet rs1 = cmd1.executeQuery("SELECT COUNT(*) FROM contenido WHERE idTrabajos="+rs.getInt(5));
				rs1.next();
				count = rs1.getInt(1);
				for(int row=0; row<count; row++){
						for(int y=0; y<4; y++){
							if(y==1 && row==0){
								objeto[y]=rs.getString(y+1).toString()+"- ";
							}
							else if(y==1 && row!=0){
								objeto[y]+=rs.getString(y+1).toString()+"- ";
							}
							else if(y==2){
								objeto[y-1] += rs.getString(y+1).toString()+"\n";
							}
							else if(y==3){
								int estado=rs.getInt(y+1);
								objeto[y-1] = this.estado(estado);
							}
							else if(y==0){
								String tipo="";
								if(rs.getInt(6)==0){
									tipo="Nota - ";
								}
								else{
									tipo="Orden de Compra - ";
								}
								objeto[y] = tipo + rs.getString(y+1).toString();
							}
							else{
								objeto[y] = rs.getString(y+1).toString();
							}
					}
						rs.next();
				}
				retornar.addRow(objeto);
				rs1.close();
				rs.previous();
			}
			cmd1.close();
			cmd.close();
			rs.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return retornar;
	}
	//Método para sacar el estado del trabajo---------------------------------------------------------------
	private String estado(int a){
		if(a==1){
			return"Proesando sin liquidar.";
		}
		else if(a==2){
			return"Liquidado en proceso";
		}
		else if(a==4){
			return"Almacen sin pagar";
		}
		else if(a==5){
			return"Almacen liquidado";
		}
		else if(a==6){
			return"Cancelado por devolución";
		}
		else if(a==7){
			return"Cancelado por modificación";
		}
		else if(a==8){
			return"Cancelado por factura";
		}
		else{
			return"Entregado";
		}
	}
	//Modelo llamadas---------------------------------------------------------------------------------------
	public DefaultTableModel getModeloLlamadas(String sql, String[] titulos, int columnas){
		DefaultTableModel aux = new DefaultTableModel(titulos, 0);
		Object []objeto = new Object[columnas];
		int columna=0;
		try{
			con.close();
			conectar();
			Statement cmd = con.createStatement();
			ResultSet rs = cmd.executeQuery(sql);
			while(rs.next()){
				while(columna<columnas+1){
					if(columna==1){
						objeto[columna-1]+="-"+rs.getString(columna+1).toString();
					}
					else if(columna<6 && columna>0){
						objeto[columna-1]=rs.getString(columna+1).toString();
					}
					else if(columna==6){
						String estatus = rs.getString(columna+1).toString();
						if(estatus.equals("1")){
							estatus="Procesando sin liquidar";
						}
						else if(estatus.equals("2")){
							estatus="Liquidado en proceso";
						}
						else if(estatus.equals("4")){
							estatus="Almacen sin pagar";
						}
						else if(estatus.equals("5")){
							estatus="Almacen sin pagar";
						}
						objeto[columna-1]=estatus;
					}
					else{
						if(rs.getString(columna+1).toString().equals("0")){
							objeto[columna]="NOTA";
						}
						else{
							objeto[columna]="ORDEN DE COMPRA";
						}
						
					}
					columna++;
				}
				aux.addRow(objeto);
				columna=0;
			}
			rs.close();
			cmd.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return aux;
	}
	//Modelo llamadas realizadas---------------------------------------------------------------------------------------
		public DefaultTableModel getModeloRealizadas(String sql, String[] titulos, int columnas){
			DefaultTableModel aux = new DefaultTableModel(titulos, 0);
			Object []objeto = new Object[columnas];
			int columna=0;
			try{
				con.close();
				conectar();
				Statement cmd = con.createStatement();
				ResultSet rs = cmd.executeQuery(sql);
				while(rs.next()){
					while(columna<columnas+1){
						if(columna==1){
							objeto[columna-1]+="-"+rs.getString(columna+1).toString();
						}
						else if(columna<columna+1 && columna>0){
							objeto[columna-1]=rs.getString(columna+1).toString();
						}
						else{
							if(rs.getString(columna+1).toString().equals("0")){
								objeto[columna]="NOTA";
							}
							else{
								objeto[columna]="ORDEN DE COMPRA";
							}
							
						}
						columna++;
					}
					aux.addRow(objeto);
					columna=0;
				}
				rs.close();
				cmd.close();
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			return aux;
		}
}
