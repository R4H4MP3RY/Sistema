package sistemaOlvera;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class Administrador extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private int tipoUsuario=0;
	private int idUsuario=0;
	private String fecha="";
	Conexion datos;
	//Componentes----------------------------------------------------------------------------------
	JButton verProduct = new JButton("Visualizar");
	JButton addProduct = new JButton("Agregar");
	JButton verEmpleado = new JButton("Visualizar");
	JButton addEmpleado = new JButton("Agregar");
	JButton verCLiente = new JButton("Visualizar");
	JButton btnModificar = new JButton("Modificar");
	JLabel empleados = new JLabel("Empleados");
	JLabel clientes = new JLabel("Clientes");
	JPanel producto = new JPanel();
	JPanel usuarios = new JPanel();
	JMenuBar menu = new JMenuBar();
	JMenu historial = new JMenu("Historial");
	//Constructor-----------------------------------------------------------------------------------
	public Administrador(Conexion x, int tipo,Calendar hoy, String fecha, int id){
		tipoUsuario=tipo;
		datos = x;
		this.fecha = fecha;
		idUsuario=id;
		//Ventana-----------------------------------------------------------------------------------
		setTitle("Administrador - Sistema Olvera");
		this.setSize(490, 220);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		//Diseño de los componenetes-----------------------------------------------------------------------------------------
		verProduct.setFont(new Font("Arial", 1, 16));
		verProduct.addActionListener(this);
		addProduct.setFont(new Font("Arial", 1, 16));
		addProduct.addActionListener(this);
		btnModificar.setFont(new Font("Arial",1,16));
		verEmpleado.setFont(new Font("Arial", 1, 16));
		addEmpleado.setFont(new Font("Arial", 1, 16));
		addEmpleado.addActionListener(this);
		verCLiente.setFont(new Font("Arial", 1, 16));
		verCLiente.addActionListener(this);
		empleados.setFont(new Font("Arial", 1, 16));
		clientes.setFont(new Font("Arial", 1, 16));
		usuarios.setPreferredSize(new Dimension(225, 125));
		menu.add(historial);
		historial.addMouseListener(new MouseAdapter()
					{
						public void mousePressed(MouseEvent e){
							Historial window = new Historial(datos);
							window.setVisible(true);
						}
					}
				);
		historial.setFont(new Font("Arial", 0, 12));
		//Es el primer contenedor de la ventana-------------------------------------------------------------------------------
		GroupLayout contiene;
		contiene = new GroupLayout(producto);
		contiene.setAutoCreateContainerGaps(true);
		contiene.setAutoCreateGaps(true);
		contiene.setHorizontalGroup(
				contiene.createParallelGroup(Alignment.CENTER)
				.addGap(230)
				.addComponent(verProduct)
				.addComponent(addProduct)
		);
		contiene.setVerticalGroup(
				contiene.createSequentialGroup()
				.addComponent(verProduct)
				.addComponent(addProduct)
		);
		//producto.setPreferredSize(new Dimension(200, 150)); esto se usa para demifinir una dimension preferida-------------
		producto.setLayout(contiene);
		producto.add(verProduct);
		producto.add(addProduct);
				
		//Es el segundo contenedor de la ventana-------------------------------------------------------------------------------
		//Como hacer un nuevo layout-------------------------------------------------------------------------------------------
		GroupLayout contenedor;
		contenedor=new GroupLayout(usuarios);
		contenedor.setAutoCreateGaps(true);
		contenedor.setAutoCreateContainerGaps(true);
		//Se debe debe de programar los gupos de forma horizotal y vertical----------------------------------------------------
		//programar de forma horizontal----------------------------------------------------------------------------------------
		contenedor.setHorizontalGroup(
			contenedor.createSequentialGroup()
						.addGroup(
								contenedor.createParallelGroup()
									.addComponent(empleados)
									.addComponent(addEmpleado)
									.addComponent(clientes)
									.addComponent(verCLiente)
								)						
			);
		//Programar de forma vertical-------------------------------------------------------------------------------------------
		contenedor.setVerticalGroup(
					contenedor.createSequentialGroup()
								.addComponent(empleados)
								.addComponent(addEmpleado)
								.addComponent(clientes)
								.addComponent(verCLiente)
					);
		//Debe de tener los mismo componenetes en los dos vertical y horizontal-------------------------------------------------
		usuarios.setLayout(contenedor);
		this.setJMenuBar(menu);
		this.setLayout(new GridLayout(1,2));
		this.add(producto);
		this.add(usuarios);
				
		producto.setBorder(new TitledBorder(null, "Productos", 0, 0, new Font("Arial", 1, 25)));
		usuarios.setBorder(new TitledBorder(null, "Usuarios", 0, 0, new Font("Arial", 1, 25)));
		
		
	}
	//Método action listener------------------------------------------------------------------------------------------------
	public void actionPerformed(ActionEvent a){
			if(a.getSource()==verProduct){
				SeeProductsAdmin winow = new SeeProductsAdmin(datos);
				winow.setVisible(true);
			}
			else if(a.getSource()==addProduct){
				AddProductsAdmin window = new AddProductsAdmin(idUsuario, fecha, datos);
				window.setVisible(true);
			}
			else if(a.getSource()==verCLiente){
				SeeClientsAdmin window = new SeeClientsAdmin(datos);
				window.setVisible(true);
			}
			else if(a.getSource()==addEmpleado){
				UsersAdmin window = new UsersAdmin(datos, tipoUsuario, idUsuario, fecha);
				window.setVisible(true);
			}
	}
}
