package sistemaOlvera;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class UsersAdmin extends JFrame implements ActionListener {
	Conexion datos;
	//Variables------------------------------------------------------------------------------
	
	private String[] tiposUsuario = {"Administrador", "Empleado"};
	private String[] tipoUsuarios1 = {"Empleado"};
	private int idUsuario =0;
	private int tipousuario;
	//Components-----------------------------------------------------------------------------
	JPanel panelDatos = new JPanel();
	JPanel panelBotones = new JPanel();
	JPanel panelUsuarios = new JPanel();
	JLabel lblNombres = new JLabel("Nombres:");
	JLabel lblApellidos = new JLabel("Apellidos:");
	JLabel lblUsuario = new JLabel("Usuario:");
	JLabel lblPassword = new JLabel("Contraseña:");
	JLabel lblTelefono = new JLabel("Teléfono:");
	JLabel lblCorreo = new JLabel("Correo:");
	JLabel lblTipo = new JLabel("Tipo:");
	JLabel lblDireccion = new JLabel("Dirección:");
	public JTextField txtNombres = new JTextField();
	public JTextField txtApellidos = new JTextField();
	public JTextField txtUsuario = new JTextField();
	public JTextField txtPassword = new JTextField();
	public JTextField txtTelefono = new JTextField();
	public JTextField txtDireccion = new JTextField();
	public JTextField txtCorreo = new JTextField();
	JTable tablaUsuario;
	DefaultTableModel modelUsuarios;
	JScrollPane scrollUsuarios;
	JButton btnAgregar = new JButton("Agregar");
	JButton btnEliminar = new JButton ("Eliminar");
	JButton btnLimpiar = new JButton ("Limpiar");
	JButton btnModificar = new JButton ("Modificar");
	JComboBox tipoUsuario;
	private JTableHeader th = new JTableHeader();
	
	//Method constructor----------------------------------------------------------------------
	public UsersAdmin(Conexion x, int tipo, int id, String fech){
		//Configure window--------------------------------------------------------------------
		super("Empleados - Sistema Olvera");
		datos=x;
		idUsuario=id;
		tipousuario=tipo;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		if(tipo==3){
			tipoUsuario = new JComboBox(tiposUsuario);
		}
		else{
			tipoUsuario = new JComboBox(tipoUsuarios1);
		}
		//Table users-------------------------------------------------------------------------
		modelUsuarios = new DefaultTableModel(){
			public boolean isCellEditable(int fila, int columna){
				return false;
			}
		};
		tablaUsuario = new JTable();
		tablaUsuario.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent mouse){
				JTable table = (JTable)mouse.getSource();
				Point point = mouse.getPoint();
				int fila = table.rowAtPoint(point);
				if(mouse.getClickCount()==1){
					Info mensaje = new Info("Datos del Usuario");
					String datos="  Nombre: ";
					datos+=tablaUsuario.getValueAt(tablaUsuario.getSelectedRow(), 0);
					mensaje.setMensaje(datos);
					datos="  Ususario: "+tablaUsuario.getValueAt(tablaUsuario.getSelectedRow(), 1)+"	Contraseña: "+tablaUsuario.getValueAt(tablaUsuario.getSelectedRow(), 2);
					mensaje.setMensaje(datos);
					datos="  Teléfono: "+tablaUsuario.getValueAt(tablaUsuario.getSelectedRow(), 3)+" Correo: "+tablaUsuario.getValueAt(tablaUsuario.getSelectedRow(), 5);
					mensaje.setMensaje(datos);
					datos="  Dirección: "+tablaUsuario.getValueAt(tablaUsuario.getSelectedRow(), 4);
					mensaje.setMensaje(datos);
					datos="  Tipo de usuario: "+tablaUsuario.getValueAt(tablaUsuario.getSelectedRow(), 6)+"";
					mensaje.setMensaje(datos);
					mensaje.showDialog();
					//JOptionPane.showMessageDialog(null, datos, "Datos del usuario.", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		scrollUsuarios = new JScrollPane(tablaUsuario);
		llenarTabla();
		
		
		//Configure components----------------------------------------------------------------
		txtNombres.setFont(new Font("Arial", 0, 14));
		txtApellidos.setFont(new Font("Arial", 0, 14));
		txtUsuario.setFont(new Font("Arial", 0, 14));
		txtPassword.setFont(new Font("Arial", 0, 14));
		txtTelefono.setFont(new Font("Arial", 0, 14));
		txtTelefono.setFont(new Font("Arial", 0, 14));
		txtDireccion.setFont(new Font("Arial", 0, 14));
		txtCorreo.setFont(new Font("Arial", 0, 14));
		lblNombres.setFont(new Font("Arial", 0, 14));
		lblApellidos.setFont(new Font("Arial", 0, 14));
		lblUsuario.setFont(new Font("Arial", 0, 14));
		lblPassword.setFont(new Font("Arial", 0, 14));
		lblTelefono.setFont(new Font("Arial", 0, 14));
		lblCorreo.setFont(new Font("Arial", 0, 14));
		lblTipo.setFont(new Font("Arial", 0, 14));
		lblDireccion.setFont(new Font("Arial", 0, 14));
		btnAgregar.setFont(new Font("Arial", 0, 14));
		btnEliminar.setFont(new Font("Arial", 0, 14));
		btnLimpiar.setFont(new Font("Arial", 0, 14));
		btnModificar.setFont(new Font("Arial", 0, 14));
		
		//Panel data--------------------------------------------------------------------------
		GroupLayout layoutButtons = new GroupLayout(panelDatos);
		layoutButtons.setAutoCreateContainerGaps(true);
		layoutButtons.setAutoCreateGaps(true);
		layoutButtons.setHorizontalGroup(
				layoutButtons.createParallelGroup()
				.addGroup(
						layoutButtons.createSequentialGroup()
						.addComponent(lblNombres)
						.addComponent(txtNombres,250,250,250)
						.addComponent(lblApellidos)
						.addComponent(txtApellidos,250,250,250)
				)
				.addGroup(
						layoutButtons.createSequentialGroup()
						.addComponent(lblUsuario)
						.addComponent(txtUsuario,150,150,150)
						.addComponent(lblPassword)
						.addComponent(txtPassword,150,150,150)
						.addComponent(lblTipo)
						.addComponent(tipoUsuario, 110, 110, 110)
				)
				.addGroup(
						layoutButtons.createSequentialGroup()
						.addComponent(lblTelefono)
						.addComponent(txtTelefono,130,130,130)
						.addComponent(lblCorreo)
						.addComponent(txtCorreo,200,200,200)
				)
				.addGroup(
						layoutButtons.createSequentialGroup()
						.addComponent(lblDireccion)
						.addComponent(txtDireccion)
				)
		);
		layoutButtons.setVerticalGroup(
				layoutButtons.createSequentialGroup()
				.addGroup(
						layoutButtons.createParallelGroup()
						.addComponent(lblNombres)
						.addComponent(txtNombres,22,22,22)
						.addComponent(lblApellidos)
						.addComponent(txtApellidos,22,22,22)
				)
				.addGroup(
						layoutButtons.createParallelGroup()
						.addComponent(lblUsuario)
						.addComponent(txtUsuario,22,22,22)
						.addComponent(lblPassword)
						.addComponent(txtPassword,22,22,22)
						.addComponent(lblTipo)
						.addComponent(tipoUsuario)
				)
				.addGroup(
						layoutButtons.createParallelGroup()
						.addComponent(lblTelefono)
						.addComponent(txtTelefono,22,22,22)
						.addComponent(lblCorreo)
						.addComponent(txtCorreo,22,22,22)
				)
				.addGroup(
						layoutButtons.createParallelGroup()
						.addComponent(lblDireccion)
						.addComponent(txtDireccion,22,22,22)
				)
		);
		panelDatos.setLayout(layoutButtons);
		panelDatos.setBorder(new TitledBorder(null, "Datos", 0 ,0, new Font("Arial", 0 ,17)));
		
		//Panel Buttons-----------------------------------------------------------------------
		GroupLayout layoutBotones = new GroupLayout(panelBotones);
		layoutBotones.setAutoCreateContainerGaps(true);
		layoutBotones.setAutoCreateGaps(true);
		layoutBotones.setHorizontalGroup(
				layoutBotones.createParallelGroup()
				.addComponent(btnAgregar,95,95,95)
				.addComponent(btnEliminar,95,95,95)
				.addComponent(btnLimpiar,95,95,95)
				.addComponent(btnModificar,95,95,95)
		);
		layoutBotones.setVerticalGroup(
				layoutBotones.createSequentialGroup()
				.addComponent(btnAgregar)
				.addComponent(btnEliminar)
				.addComponent(btnLimpiar)
				.addComponent(btnModificar)
		);
		panelBotones.setLayout(layoutBotones);
		
		//Panel users---------------------------------------------------------------------------
		GroupLayout layoutUsuarios = new GroupLayout(panelUsuarios);
		layoutUsuarios.setAutoCreateContainerGaps(true);
		layoutUsuarios.setAutoCreateGaps(true);
		layoutUsuarios.setHorizontalGroup(
				layoutUsuarios.createSequentialGroup()
				.addComponent(scrollUsuarios)
		);
		layoutUsuarios.setVerticalGroup(
				layoutUsuarios.createParallelGroup()
				.addComponent(scrollUsuarios)
		);
		panelUsuarios.setLayout(layoutUsuarios);
		panelUsuarios.setBorder(new TitledBorder(null, "Usuarios", 0, 0, new Font("Arial", 0, 17)));
		
		//Principal-------------------------------------------------------------------------------
		GroupLayout layoutPrincipal = new GroupLayout(this.getContentPane());
		layoutPrincipal.setAutoCreateContainerGaps(true);
		layoutPrincipal.setAutoCreateGaps(true);
		layoutPrincipal.setHorizontalGroup(
				layoutPrincipal.createParallelGroup()
				.addGroup(
						layoutPrincipal.createSequentialGroup()
						.addComponent(panelDatos)
						.addComponent(panelBotones)
				)
				.addComponent(panelUsuarios)
		);
		layoutPrincipal.setVerticalGroup(
				layoutPrincipal.createSequentialGroup()
				.addGroup(
						layoutPrincipal.createParallelGroup()
						.addComponent(panelDatos)
						.addComponent(panelBotones)
				)
				.addComponent(panelUsuarios)
		);
		this.setLayout(layoutPrincipal);
		pack();
		
		//Actions-----------------------------------------------------------------------------
		btnLimpiar.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnEliminar.addActionListener(this);
		btnModificar.addActionListener(this);
		
	}
	//Method action---------------------------------------------------------------------------
	public void actionPerformed(ActionEvent a) {
		boolean existe = false;
		if(a.getSource()==btnLimpiar){
			txtNombres.setText("");
			txtApellidos.setText("");
			txtUsuario.setText("");
			txtPassword.setText("");
			txtTelefono.setText("");
			txtDireccion.setText("");
			txtCorreo.setText("");
		}
		else if(a.getSource()==btnAgregar){
			boolean bandera = false;
			String nombre = txtNombres.getText();
			String apellidos = txtApellidos.getText();
			String usuario = txtUsuario.getText();
			String password = txtPassword.getText();
			String telefono = txtTelefono.getText();
			String direccion = txtDireccion.getText();
			String correo = txtCorreo.getText();
			String empleado = (String)tipoUsuario.getSelectedItem();
			bandera = revisarDatos(nombre, apellidos, usuario, password, telefono, direccion, correo);
			if(bandera){
				String sqlExiste="SELECT usuario FROM usuarios WHERE usuario='"+txtUsuario.getText()+"'";
				if(!(existe = datos.getUsuarioExiste(sqlExiste))){
					if(empleado.equals("Empleado")){
						String sql = "INSERT INTO usuarios (nombres, apellidos, usuario, password, telefono, direccion,  tipo, correo, eliminado, idUsuario) VALUES ('"+nombre+"', '"+ apellidos+"', '"+usuario+"', '"+password+"', '"+telefono+"', '"+direccion+"',  "+2+", '"+correo+"', "+0+", "+idUsuario+")";
						datos.Modificaciones(sql);
						limpiarTextos();
						llenarTabla();
					}
					else{
						String sql = "INSERT INTO usuarios (nombres, apellidos, usuario, password, telefono, direccion,  tipo, correo, eliminado, idUsuario) VALUES ('"+nombre+"', '"+ apellidos+"', '"+usuario+"', '"+password+"', '"+telefono+"', '"+direccion+"',  "+1+", '"+correo+"', "+0+", "+idUsuario+")";
						datos.Modificaciones(sql);
						limpiarTextos();
						llenarTabla();
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "El Usuario no esta disponible para usarse, por favor eliga otro.", "Usuario ya existente.", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		else if(a.getSource()==btnModificar){
			String modificar;
			int conModi=0;
			if(!(txtUsuario.getText().trim().equals(""))){
				String sqlExiste="SELECT usuario FROM usuarios WHERE usuario='"+txtUsuario.getText()+"'";
				if(datos.getUsuarioExiste(sqlExiste)){
					if(!(txtNombres.getText().trim().equals(""))){
						modificar="UPDATE usuarios SET nombres='"+txtNombres.getText()+"' WHERE usuario ='"+txtUsuario.getText()+"'";
						conModi++;
						datos.Modificaciones(modificar);
					}
					if(!(txtApellidos.getText().trim().equals(""))){
						modificar="UPDATE usuarios SET apellidos='"+txtApellidos.getText()+"' WHERE usuario ='"+txtUsuario.getText()+"'";
						conModi++;
						datos.Modificaciones(modificar);
					}
					if(!(txtPassword.getText().trim().equals(""))){
						modificar = "UPDATE usuarios SET password ='"+txtPassword.getText()+"' WHERE usuario ='"+txtUsuario.getText()+"'";
						conModi++;
						datos.Modificaciones(modificar);
					}
					if(!(txtTelefono.getText().trim().equals(""))){
						modificar="UPDATE usuarios SET telefono='"+txtTelefono.getText()+"' WHERE usuario ='"+txtUsuario.getText()+"'";
						conModi++;
						datos.Modificaciones(modificar);
					}
					if(!(txtDireccion.getText().trim().equals(""))){
						modificar="UPDATE usuarios SET direccion='"+txtDireccion.getText()+"' WHERE usuario ='"+txtUsuario.getText()+"'";
						conModi++;
						datos.Modificaciones(modificar);
					}
					if(!(txtCorreo.getText().trim().equals(""))){
						modificar="UPDATE usuarios SET correo='"+txtCorreo.getText()+"' WHERE usuario ='"+txtUsuario.getText()+"'";
						conModi++;
						datos.Modificaciones(modificar);
					}
					if(tipoUsuario.getSelectedIndex()==0){
						String tipo = datos.getDato("SELECT tipo FROM usuarios WHERE usuario ='"+txtUsuario.getText()+"'");
						if(!tipo.equals("1")){
							int result = JOptionPane.showConfirmDialog(null, "¿Desea pasar este usuario de Empleado a Administrador?", "Atención", JOptionPane.YES_NO_OPTION);
							if(result==JOptionPane.YES_OPTION){
								modificar="UPDATE usuarios SET tipo='"+1+"' WHERE usuario ='"+txtUsuario.getText()+"'";
								conModi++;
								datos.Modificaciones(modificar);
							}
						}
					}
					else{
						String tipo = datos.getDato("SELECT tipo FROM usuarios WHERE usuario ='"+txtUsuario.getText()+"'");
						if(!tipo.equals("2")){
							int result = JOptionPane.showConfirmDialog(null, "¿Desea pasar este usuario de Administardor a Empleado?", "Atención", JOptionPane.YES_NO_OPTION);
							if(result==JOptionPane.YES_OPTION){
								modificar="UPDATE usuarios SET tipo='"+2+"' WHERE usuario ='"+txtUsuario.getText()+"'";
								conModi++;
								datos.Modificaciones(modificar);
							}
						}
					}
					if(conModi>0){
						JOptionPane.showMessageDialog(null, "El usuario: "+txtUsuario.getText()+", ha sido modificado correctamente.", "Correcto", JOptionPane.PLAIN_MESSAGE);
						limpiarTextos();
						llenarTabla();
					}
					else{
						JOptionPane.showMessageDialog(null, "Debe ingresar alguno de los campos a modificar.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "El usuaior ingresado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Para modificar un usuario debe ingresar el nombre de usuario.", "Error, ingrese un usuario.", JOptionPane.ERROR_MESSAGE);
			}
		}
		else{//Accion de Eliminar------------------------------------------------------
			if(!(txtUsuario.getText().trim().equals(""))){
				String sql="SELECT usuario FROM usuarios WHERE usuario='"+txtUsuario.getText()+"'";
				if(datos.getUsuarioExiste(sql)){
					String borrar = "UPDATE usuarios SET eliminado = 1 WHERE usuario = '"+txtUsuario.getText()+"'";
					int result = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar este usuario?\nNo podra acceder esta persona nuevamente al sistema.", "Atención", JOptionPane.YES_NO_OPTION);
					if(result==JOptionPane.YES_OPTION){
						datos.Modificaciones(borrar);
						JOptionPane.showMessageDialog(null, "Usuario: "+txtUsuario.getText()+", ha sido eliminado correctamente.", "Exito", JOptionPane.PLAIN_MESSAGE);
						llenarTabla();
					}
					limpiarTextos();
				}
				else{
					JOptionPane.showMessageDialog(null, "El ususario que ingreso no existe, intentelo de nuevo", "Error con el usuario", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de usuario para poder eliminarlo.", "Error, ingrese usuario.", JOptionPane.ERROR_MESSAGE);
			}
			limpiarTextos();
		}
		
	}
	//Metodo para revisar información----------------------------------------------------------
	private boolean revisarDatos(String nombre, String apellido, String usuario, String password, String telefono, String direccion, String correo){
		boolean retorno = true;
		boolean resultado = false;
		String expresion = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		String eTelefono = "^\\d+$";
		String mensaje ="Debe llenar todos los campos correctamente.\n Campos con error:\n";
		if(nombre.trim().equals("")){
			retorno = false;
			mensaje += "Nombres.\n";
		}
		if(apellido.trim().equals("")){
			retorno = false;
			mensaje += "Apellidos.\n";
		}
		if(usuario.trim().equals("")){
			retorno=false;
			mensaje+="Usuario.\n";
		}
		if(password.trim().equals("")){
			retorno = false;
			mensaje+="Contraseña.\n";
		}
		if(telefono.trim().equals("")){
			retorno = false;
			mensaje+="Teléfono.\n";
		}
		else{
			Pattern pattern = Pattern.compile(eTelefono);
			Matcher matcher = pattern.matcher(telefono);
			if(!(resultado = matcher.matches())){
				JOptionPane.showMessageDialog(null, "Debe ingresar solamente números en el campo teléfono.", "Teléfono Invalido", JOptionPane.ERROR_MESSAGE);
				retorno = false;
				mensaje+="Teléfono.\n";
			}
		}
		if(!retorno){
			JOptionPane.showMessageDialog(null, mensaje, "Atención.", JOptionPane.INFORMATION_MESSAGE);
		}
		return retorno;
	}
	//Método para limpiar los textos-----------------------------------------------------------------------------------
	private void limpiarTextos(){
		txtNombres.setText("");
		txtApellidos.setText("");
		txtUsuario.setText("");
		txtPassword.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtCorreo.setText("");
	}
	//Método para llenar la tabla----------------------------------------------------------------------------------------
	private void llenarTabla(){
		String sql="";
		if(tipousuario==3){
			sql = "SELECT nombres, apellidos, usuario, password, telefono, direccion, correo, tipo FROM usuarios WHERE eliminado = 0";
		}
		else{
			sql = "SELECT nombres, apellidos, usuario, password, telefono, direccion, correo, tipo FROM usuarios WHERE eliminado = 0 AND idUsuario="+idUsuario;
		}
		modelUsuarios = datos.getModelUsers(sql);
		tablaUsuario.setModel(modelUsuarios);
		th = tablaUsuario.getTableHeader();
		th.setFont(new Font("Arial", 1, 16));
		tablaUsuario.setFont(new Font("Arial", 0, 17));
		tablaUsuario.setTableHeader(th);
		scrollUsuarios.getViewport().add(tablaUsuario);
	}

}
