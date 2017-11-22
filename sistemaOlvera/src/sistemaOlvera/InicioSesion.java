package sistemaOlvera;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Font;

public class InicioSesion extends JFrame implements ActionListener{
	Conexion datos;
	Calendar fecha = Calendar.getInstance();
	//Calendar fecha  = new GregorianCalendar(TimeZone.getTimeZone("America/Monterrey"));
	SimpleDateFormat formatoDia = new SimpleDateFormat("yyyy-MM-dd");
	private String fechaFormato;
	//Declaracion de objetos---------------------------------------------------------------------------------
	JButton btnCancelar = new JButton("Cancelar");
	JButton btnIniciar = new JButton("Entrar");
	JTextField txtBoxUsuario = new JTextField();
	JPasswordField txtBoxPassword = new JPasswordField();
	JLabel lbl2 = new JLabel("Contraseña:");
	JLabel lbl1 = new JLabel("Usuario: ");
	private static final long serialVersionUID = 1L;
	
	public InicioSesion(Conexion x){		
		datos = x;
		//fecha.setTimeZone(TimeZone.getTimeZone("America/Guadalajara"));
		fechaFormato = formatoDia.format(fecha.getTime());
		//JOptionPane.showMessageDialog(this, fechaFormato);
		setTitle("Sistema Olvera - Iniciar Sesion");
		Toolkit resolucion = Toolkit.getDefaultToolkit();
		Dimension size = resolucion.getScreenSize();
		int alto = size.height;
		int ancho = size.width;
		setBounds(ancho/3,alto/3,ancho/3,alto/3);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
			
		lbl1.setBounds(50, 30, 120, 25);
		lbl1.setFont(new Font("Arial", 0, 25));
		add(lbl1);
			
			
		lbl2.setBounds(50, 90, 140, 25);
		lbl2.setFont(new Font("Arial", 0, 25));
		add(lbl2);
			
			
		txtBoxUsuario.setBounds(200, 30, 200, 30);
		txtBoxUsuario.setFont(new Font("Arial", 0, 20));
		add(txtBoxUsuario);
			
			
		txtBoxPassword.setBounds(200, 90, 200, 30);	
		add(txtBoxPassword);
			
			
		btnIniciar.setBounds(70, 150, 130, 40);
		btnIniciar.setFont(new Font("Arial", 0, 25));
		btnIniciar.addActionListener(this);
		add(btnIniciar);
			
			
		btnCancelar.setBounds(230, 150, 140, 40);
		btnCancelar.setFont(new Font("Arial", 0, 25));
		btnCancelar.addActionListener(this);
		add(btnCancelar);
	}	
	
	public void actionPerformed(ActionEvent a){
		if(a.getSource()==btnIniciar){
			if(!(txtBoxUsuario.getText().trim().equals(""))){
				if(!(txtBoxPassword.getText().trim().equals(""))){
					if(txtBoxUsuario.getText().equals("admin")){
						if(txtBoxPassword.getText().equals("admin")){
							this.hide();
							//String fecha = datos.getDato("SELECT DATE_FORMAT(NOW(), '%Y-%m-%d')");
							Administrador window = new Administrador(datos, 3, fecha, fechaFormato, 0);
							window.setVisible(true);
						}
						else{
							JOptionPane.showMessageDialog(null, "Revise la información ingresada.", "Error", JOptionPane.ERROR_MESSAGE);
							txtBoxUsuario.setText("");
							txtBoxPassword.setText("");
						}
					}
					else{
						int tipo=this.sesionAdmin(txtBoxUsuario.getText(),txtBoxPassword.getText() );
						int id = 0;
						//String fecha = datos.getDato("SELECT DATE_FORMAT(NOW(), '%Y-%m-%d')");
						if(tipo==1){
							this.hide();
							id =  Integer.parseInt(datos.getDato("SELECT id FROM usuarios WHERE usuario='"+txtBoxUsuario.getText()+"'"));
							Administrador window = new Administrador(datos, 1, fecha, fechaFormato, id);
							window.setVisible(true);
						}
						else if(tipo==2){
							this.hide();
							id=  Integer.parseInt(datos.getDato("SELECT id FROM usuarios WHERE usuario='"+txtBoxUsuario.getText()+"'"));
							EmpleadoInicio window = new EmpleadoInicio(datos, fecha, fechaFormato, id);
							window.setVisible(true);
							
						}
						else{
							JOptionPane.showMessageDialog(null, "Revisa la información.\nEl usuario y contraseña no son correctos.", "Error al iniciar sesion", JOptionPane.ERROR_MESSAGE);
							txtBoxUsuario.setText("");
							txtBoxPassword.setText("");
						}
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Rivese la información.\nDebe ingresar una contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
					txtBoxUsuario.setText("");
					txtBoxPassword.setText("");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Rivese la información.\nDebe ingresar un usuario.", "Error", JOptionPane.ERROR_MESSAGE);
				txtBoxUsuario.setText("");
				txtBoxPassword.setText("");
			}
		}
		else if(a.getSource()==btnCancelar){
			System.exit(0);
		}
	}
	
	//Metodo para llamar a inicio de seion------------------------------------------------------------------------------
	private int sesionAdmin(String user, String pass){
		int resul=0;
		resul=datos.iniciaAdmin(user, pass);
		return resul;
	}
}

