package sistemaOlvera;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class AddProductsAdmin extends JFrame implements ActionListener {
	private Conexion datos;
	private int id;
	private String fecha;
	private ArrayList listaContenidoPaquete = new ArrayList();
	//Components--------------------------------------------------------------------------------
	private JButton btnCrearProducto = new JButton("Crear");
	private JButton btnClear = new JButton("Limpiar");
	private JButton btnCrearPaquete = new JButton("Crear");
	private JButton btnEliminarPaquete = new JButton("Eliminar");
	private JButton btnModificarPaquete = new JButton("Modificar");
	private JButton btnClearLista = new JButton("Limpiar");
	private JButton btnAgregarProducto = new JButton("Agregar");
	private JButton btnQuitarProducto = new JButton("Quitar");
	private JButton btnEliminarProducto = new JButton("Eliminar");
	private JButton btnModificarProducto = new JButton("Modificar");
	private JButton btnClearProductos = new JButton("Limpiar");
	JLabel lblNombre = new JLabel("Nombre: ");
	JLabel lblDescripcion = new JLabel("Descripción: ");
	JLabel lblPrecio = new JLabel("Precio: $");
	JLabel lblRegistro = new JLabel("Registro: ");
	JLabel lblContiene = new JLabel("Contiene:");
	JLabel lblProductos = new JLabel("Productos:");
	JLabel lblNombreProductos = new JLabel("Nombre:");
	JLabel lblDescripcionProductos = new JLabel("Descripción: ");
	JLabel lblPrecioProductos = new JLabel("Precio: $");
	JLabel lblRegistroProductos = new JLabel("Registro: ");
	private JTextField txtRegistro = new JTextField();
	private JTextField txtRegistroProducto = new JTextField();
	private JTextField txtNombreProducto = new JTextField();
	private JTextField txtNombrePaquete = new JTextField();
	private JTextArea txtDescripcionProducto = new JTextArea(2,5);
	private JTextField txtPrecioProducto = new JTextField();
	private JTextField txtPrecioPaquete = new JTextField();
	private JTabbedPane tabs;
	private JPanel panelProductos = new JPanel();
	private JPanel panelPaquetes = new JPanel();
	private JPanel panelDatosPaquetes = new JPanel();
	private JPanel panelDatosProductos = new JPanel();
	private JPanel panelProductosExistencia = new JPanel();
	private JPanel panelPaquetesExistencia = new JPanel();
	private JPanel panelCrearPaquetes = new JPanel();
	private JPanel panelBotones = new JPanel();
	private JPanel panelBotonesPaquetes = new JPanel();
	private JTable tablaProductos;
	private JTable tablaPaquetes;
	private JScrollPane scrollProductos;
	private JScrollPane scrollPaquetes;
	private JScrollPane scrollDescripcionProductos;
	private JScrollPane scrollLista;
	private JScrollPane scrollLista2;
	private DefaultTableModel modelProductos;
	private DefaultTableModel modelPaquetes;
	private JList listaProducto;
	private JList listaContenido;
	private JTableHeader thProducto;
	private JTableHeader thPaquetes;
	private DefaultTableCellRenderer alineaCentro = new DefaultTableCellRenderer();
	private DefaultListModel modeloProducto = new DefaultListModel();
	private DefaultListModel modeloPaquete = new DefaultListModel();
	
	//Constructor----------------------------------------------------------------------------
	public AddProductsAdmin(int idUsuario, String fecha, Conexion x){
		//Window configure--------------------------------------------------------
		super("Productos/Paquetes - Sistema Olvera");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		datos=x;
		id=idUsuario;
		this.fecha=fecha;
		
		//Components configure-----------------------------------------------------------------
		//Buttons, labels, text fields y text areas-------------------------------
		btnCrearProducto.setFont(new Font("Arial", 0, 14));
		btnCrearProducto.addActionListener(this);
		btnCrearPaquete.setFont(new Font("Arial", 0, 14));
		btnCrearPaquete.addActionListener(this);
		btnClearLista.setFont(new Font("Arial", 0, 14));
		btnClearLista.addActionListener(this);
		btnClearProductos.setFont(new Font("Arial", 0, 14));
		btnClearProductos.addActionListener(this);
		btnEliminarPaquete.setFont(new Font("Arial", 0, 14));
		btnEliminarPaquete.addActionListener(this);
		btnModificarPaquete.setFont(new Font("Arial", 0, 14));
		btnModificarPaquete.addActionListener(this);
		btnClear.setFont(new Font("Arial", 0, 14));
		btnClear.addActionListener(this);
		btnAgregarProducto.setFont(new Font("Arial", 0, 14));
		btnAgregarProducto.addActionListener(this);
		btnQuitarProducto.setFont(new Font("Arial", 0, 14));
		btnQuitarProducto.addActionListener(this);
		btnEliminarProducto.setFont(new Font("Arial", 0, 14));
		btnEliminarProducto.addActionListener(this);
		btnModificarProducto.setFont(new Font("Arial", 0, 14));
		btnModificarProducto.addActionListener(this);
		lblNombre.setFont(new Font("Arial", 0 ,14));
		lblDescripcion.setFont(new Font("Arial", 0, 14));
		lblPrecio.setFont(new Font("Arial", 0, 14));
		lblRegistro.setFont(new Font("Arial", 0, 14));
		lblContiene.setFont(new Font("Arial", 0, 14));
		lblProductos.setFont(new Font("Arial", 0, 14));
		txtRegistro.setFont(new Font("Arial", 0, 14));
		txtRegistroProducto.setFont(new Font("Arial",0,14));
		txtNombreProducto.setFont(new Font("Arial", 0, 14));
		txtNombrePaquete.setFont(new Font("Arial",0,14));
		txtDescripcionProducto.setFont(new Font("Arial",0,14));
		txtDescripcionProducto.setWrapStyleWord(true);
		txtDescripcionProducto.setLineWrap(true);
		txtPrecioProducto.setFont(new Font("Arial",0,14));
		txtPrecioPaquete.setFont(new Font("Arial",0,14));
		alineaCentro.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Tables------------------------------------------------------------------
		modelProductos= new DefaultTableModel();
		tablaProductos= new JTable();
		scrollProductos = new JScrollPane(tablaProductos);
		llenarTablaProductos();
		scrollDescripcionProductos = new JScrollPane(txtDescripcionProducto);
		
		//Panels------------------------------------------------------------------
		//Panel buttons add and clear for products-------------------
		GroupLayout botones = new GroupLayout(panelBotones);
		botones.setAutoCreateContainerGaps(true);
		botones.setAutoCreateGaps(true);
		botones.setHorizontalGroup(
				botones.createParallelGroup()
					.addComponent(btnCrearProducto,95,95,95)
					.addComponent(btnClearProductos,95,95,95)
					.addComponent(btnEliminarProducto,95,95,95)
					.addComponent(btnModificarProducto,95,95,95)
		);
		botones.setVerticalGroup(
				botones.createSequentialGroup()
				.addComponent(btnCrearProducto)
				.addComponent(btnClearProductos)
				.addComponent(btnEliminarProducto)
				.addComponent(btnModificarProducto)
		);
		panelBotones.setLayout(botones);
		//Panel data products----------------------------------------
		GroupLayout datosProductos = new GroupLayout(panelDatosProductos);
		datosProductos.setAutoCreateContainerGaps(true);
		datosProductos.setAutoCreateGaps(true);
		datosProductos.setHorizontalGroup(
				datosProductos.createSequentialGroup()
					.addGroup(
							datosProductos.createParallelGroup()
							.addComponent(lblNombreProductos)
							.addComponent(lblDescripcionProductos)
					)
					.addGroup(
							datosProductos.createParallelGroup()
							.addComponent(txtNombreProducto,300,300,300)
							.addComponent(scrollDescripcionProductos,400,400,400)
					)
					.addGroup(
							datosProductos.createParallelGroup()
							.addComponent(lblRegistroProductos)
							.addComponent(lblPrecioProductos)
					)
					.addGroup(
							datosProductos.createParallelGroup()
							.addComponent(txtRegistroProducto,100,100,100)
							.addComponent(txtPrecioProducto,100,100,100)
					)
		);
		datosProductos.setVerticalGroup(
				datosProductos.createSequentialGroup()
				.addGroup(
						datosProductos.createParallelGroup()
						.addComponent(lblNombreProductos)
						.addComponent(txtNombreProducto,22,22,22)
						.addComponent(lblRegistroProductos)
						.addComponent(txtRegistroProducto,22,22,22)
				)
				.addGroup(
						datosProductos.createParallelGroup()
						.addComponent(lblDescripcionProductos)
						.addComponent(scrollDescripcionProductos,45,45,45)
						.addComponent(lblPrecioProductos)
						.addComponent(txtPrecioProducto,22,22,22)
				)
		);
		panelDatosProductos.setLayout(datosProductos);
		panelDatosProductos.setBorder(new TitledBorder(null, "Datos", 0, 0, new Font("Arial",0,17)));
		
		//Panel list products-------------------------------------------------
		GroupLayout listaProductos = new GroupLayout(panelProductosExistencia);
		listaProductos.setAutoCreateContainerGaps(true);
		listaProductos.setAutoCreateGaps(true);
		listaProductos.setHorizontalGroup(
				listaProductos.createSequentialGroup()
					.addComponent(scrollProductos)
		);
		listaProductos.setVerticalGroup(
				listaProductos.createSequentialGroup()
				.addComponent(scrollProductos)
		);
		panelProductosExistencia.setLayout(listaProductos);
		panelProductosExistencia.setBorder(new TitledBorder(null, "Existencia", 0, 0, new Font("Arial",0,17)));
		
		//Panel products-------------------------------------------------------
		GroupLayout productos = new GroupLayout(panelProductos);
		productos.setAutoCreateContainerGaps(true);
		productos.setAutoCreateGaps(true);
		productos.setHorizontalGroup(
				productos.createParallelGroup(Alignment.CENTER)
					.addGroup(
							productos.createSequentialGroup()
							.addComponent(panelDatosProductos)
							.addComponent(panelBotones)
					)
					.addComponent(panelProductosExistencia)
		);
		productos.setVerticalGroup(
				productos.createSequentialGroup()
					.addGroup(
							productos.createParallelGroup()
							.addComponent(panelDatosProductos)
							.addComponent(panelBotones)
					)
					.addComponent(panelProductosExistencia)
		);
		panelProductos.setLayout(productos);
		
		//Tab of products-------------------------------------------------------
		ChangeListener cambioTab = new ChangeListener(){
			public void stateChanged(ChangeEvent e){
				llenarListaProductos();
				llenarTablaPaquetes();
				llenarTablaProductos();
			}
		};
		tabs = new JTabbedPane();
		tabs.addTab("Productos", panelProductos);
		tabs.addChangeListener(cambioTab);
		
		//Tables package-------------------------------------------------------
		modelPaquetes = new DefaultTableModel();
		tablaPaquetes = new JTable();
		tablaPaquetes.addMouseListener(new MouseAdapter(){
						public void mousePressed(MouseEvent a){
							JTable aux = (JTable)a.getSource();
							Point p = a.getPoint();
							int fila = aux.rowAtPoint(p);
							if(a.getClickCount()==1){
								Info mensaje = new Info("Datos del paquete.");
								String datos = "  Registro: ";
								datos+= tablaPaquetes.getValueAt(tablaPaquetes.getSelectedRow(), 0);
								datos += "    Nombre: "+tablaPaquetes.getValueAt(tablaPaquetes.getSelectedRow(), 1);
								mensaje.setMensaje(datos);
								datos = "  Descripción: \n"+tablaPaquetes.getValueAt(tablaPaquetes.getSelectedRow(), 2);
								mensaje.setMensaje(datos);
								datos = "  Precio:"+tablaPaquetes.getValueAt(tablaPaquetes.getSelectedRow(), 3)+"";
								mensaje.setMensaje(datos);
								mensaje.showDialog();
							}
						}
					}	
				);
		scrollPaquetes = new JScrollPane(tablaPaquetes);
		llenarTablaPaquetes();
		listaProducto = new JList(modeloProducto);
		listaProducto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaProducto.setLayoutOrientation(JList.VERTICAL);
		listaProducto.setVisibleRowCount(-1);
		listaContenido = new JList(modeloPaquete);
		listaContenido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaContenido.setLayoutOrientation(JList.VERTICAL);
		listaContenido.setVisibleRowCount(-1);
		scrollLista = new JScrollPane(listaContenido);
		scrollLista2 = new JScrollPane(listaProducto);
		llenarListaProductos();
		
		//Panel data packs-----------------------------------------------------
		GroupLayout datosPaquetes = new GroupLayout(panelDatosPaquetes);
		datosPaquetes.setAutoCreateContainerGaps(true);
		datosPaquetes.setAutoCreateGaps(true);
		datosPaquetes.setHorizontalGroup(
				datosPaquetes.createSequentialGroup()
				.addGroup(
						datosPaquetes.createParallelGroup()
						.addComponent(lblNombre)
				)
				.addGroup(
						datosPaquetes.createParallelGroup()
						.addComponent(txtNombrePaquete,300,300,300)
				)
				.addGroup(
						datosPaquetes.createParallelGroup()
						.addComponent(lblRegistro)
						.addComponent(lblPrecio)
				)
				.addGroup(
						datosPaquetes.createParallelGroup()
						.addComponent(txtRegistro,100,100,100)
						.addComponent(txtPrecioPaquete,100,100,100)
				)
		);
		datosPaquetes.setVerticalGroup(
				datosPaquetes.createSequentialGroup()
				.addGroup(
						datosPaquetes.createParallelGroup()
						.addComponent(lblNombre)
						.addComponent(txtNombrePaquete,22,22,22)
						.addComponent(lblRegistro)
						.addComponent(txtRegistro,22,22,22)
				)
				.addGroup(
						datosPaquetes.createParallelGroup()
						.addComponent(lblPrecio)
						.addComponent(txtPrecioPaquete,22,22,22)
				)
		);
		panelDatosPaquetes.setLayout(datosPaquetes);
		panelDatosPaquetes.setBorder(new TitledBorder(null, "Datos", 0, 0, new Font("Arial",0,17)));
		
		//Buttons for packages-------------------------------------------------
		GroupLayout botonesPaquetes = new GroupLayout(panelBotonesPaquetes);
		botonesPaquetes.setAutoCreateContainerGaps(true);
		botonesPaquetes.setAutoCreateGaps(true);
		botonesPaquetes.setHorizontalGroup(
				botonesPaquetes.createParallelGroup()
				.addComponent(btnAgregarProducto,95,95,95)
				.addComponent(btnQuitarProducto,95,95,95)
				.addComponent(btnClearLista,95,95,95)
		);
		botonesPaquetes.setVerticalGroup(
				botonesPaquetes.createSequentialGroup()
				.addComponent(btnAgregarProducto)
				.addComponent(btnQuitarProducto)
				.addComponent(btnClearLista)
		);
		panelBotonesPaquetes.setLayout(botonesPaquetes);
		
		//Panel packs description----------------------------------------------		
		GroupLayout descripcionPaquetes = new GroupLayout(panelCrearPaquetes);
		descripcionPaquetes.setAutoCreateContainerGaps(true);
		descripcionPaquetes.setAutoCreateGaps(true);
		descripcionPaquetes.setHorizontalGroup(
				descripcionPaquetes.createSequentialGroup()
				.addGroup(
						descripcionPaquetes.createParallelGroup()
						.addComponent(lblProductos)
						.addComponent(scrollLista2,300,300,300)
				)
				.addComponent(panelBotonesPaquetes)
				.addGroup(
						descripcionPaquetes.createParallelGroup()
						.addComponent(lblContiene)
						.addComponent(scrollLista,300,300,300)
				)
		);
		descripcionPaquetes.setVerticalGroup(
				descripcionPaquetes.createParallelGroup(Alignment.CENTER)
				.addGroup(
						descripcionPaquetes.createSequentialGroup()
						.addComponent(lblProductos)
						.addComponent(scrollLista2)
				)
				.addComponent(panelBotonesPaquetes)
				.addGroup(
						descripcionPaquetes.createSequentialGroup()
						.addComponent(lblContiene)
						.addComponent(scrollLista)
				)
		);
		panelCrearPaquetes.setLayout(descripcionPaquetes);
		panelCrearPaquetes.setBorder(new TitledBorder(null, "Crear", 0, 0, new Font("Arial", 0, 17)));
	
		
		//Panel existencia-----------------------------------------------------
		GroupLayout tablaExiste = new GroupLayout(panelPaquetesExistencia);
		tablaExiste.setAutoCreateContainerGaps(true);
		tablaExiste.setAutoCreateGaps(true);
		tablaExiste.setHorizontalGroup(
				tablaExiste.createSequentialGroup()
				.addComponent(scrollPaquetes)
		);
		tablaExiste.setVerticalGroup(
				tablaExiste.createSequentialGroup()
				.addComponent(scrollPaquetes)
		);
		panelPaquetesExistencia.setLayout(tablaExiste);
		panelPaquetesExistencia.setBorder(new TitledBorder(null, "Existencia",0,0, new Font("Arial",0,17)));
		
		//Panel packages-------------------------------------------------------
		GroupLayout principalPaquetes = new GroupLayout(panelPaquetes);
		principalPaquetes.setAutoCreateContainerGaps(true);
		principalPaquetes.setAutoCreateGaps(true);
		principalPaquetes.setHorizontalGroup(
				principalPaquetes.createSequentialGroup()
				.addGroup(
						principalPaquetes.createParallelGroup()
						.addComponent(panelDatosPaquetes)
						.addComponent(panelCrearPaquetes)
				)
				.addGroup(
						principalPaquetes.createParallelGroup()
						.addComponent(btnCrearPaquete,95,95,95)
						.addComponent(btnClear,95,95,95)
						.addComponent(btnEliminarPaquete,95,95,95)
						.addComponent(btnModificarPaquete,95,95,95)
				)
				.addComponent(panelPaquetesExistencia)
		);
		principalPaquetes.setVerticalGroup(
				principalPaquetes.createParallelGroup()
				.addGroup(
						principalPaquetes.createSequentialGroup()
						.addComponent(panelDatosPaquetes)
						.addComponent(panelCrearPaquetes)
				)
				.addGroup(
						principalPaquetes.createSequentialGroup()
						.addComponent(btnCrearPaquete)
						.addComponent(btnClear)
						.addComponent(btnEliminarPaquete)
						.addComponent(btnModificarPaquete)
				)
				.addComponent(panelPaquetesExistencia)
		);
		panelPaquetes.setLayout(principalPaquetes);
		
		//Add new tab----------------------------------------------------------
		tabs.addTab("Paquetes", panelPaquetes);
				
		//Panel principal------------------------------------------------------
		GroupLayout principal = new GroupLayout(this.getContentPane());
		principal.setAutoCreateContainerGaps(true);
		principal.setAutoCreateGaps(true);
		principal.setHorizontalGroup(
				principal.createSequentialGroup()
				.addComponent(tabs)
		);
		principal.setVerticalGroup(
				principal.createParallelGroup()
				.addComponent(tabs)
		);
		this.setLayout(principal);
		this.add(tabs);
		pack();
	}
	//Method actionAbstract------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent a){
		if(a.getSource()==btnClear){
			modeloProducto.removeAllElements();
			modeloPaquete.removeAllElements();
			listaContenidoPaquete.clear();
			llenarListaProductos();
			limpiarTextos();
		}
		else if(a.getSource()==btnClearProductos){
			limpiarTextos();
		}
		else if(a.getSource()==btnCrearProducto){
			String registro = txtRegistroProducto.getText().toUpperCase();
			String nombre = txtNombreProducto.getText().toUpperCase();
			String descripcion = txtDescripcionProducto.getText().toUpperCase();
			String precio = txtPrecioProducto.getText();
			boolean bandera = verificaCampos(registro, nombre, descripcion, precio);
			if(bandera){
				String dnombre = datos.getDato("SELECT COUNT(*) FROM productos WHERE nombre='"+nombre+"'");
				String dregistro = datos.getDato("SELECT COUNT(*) FROM productos WHERE registro='"+registro+"'");
				if(!dnombre.equals("1"))
				{
					if(!dregistro.equals("1")){
						try{
							double preciof = Double.parseDouble(precio);
							preciof+=.00;
							descripcion = descripcion(descripcion);
							String sql ="INSERT INTO productos (registro, nombre, descripcion, precio, idUsuarios, tipo, fechaAlta, eliminado) VALUES('"+registro+"', '"+nombre+"', '"+descripcion+"', '"+preciof+"', "+id+", 1, '"+fecha+"', 0)";
							datos.Modificaciones(sql);
							limpiarTextos();
							llenarTablaProductos();
						}
						catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null, "Debe ingresar solamente números en el campo precio", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "El registro que ingreso ya existe para otro producto", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "El nombre que ingreso ya existe para otro producto", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Llene correctamente mente todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(a.getSource()==btnEliminarProducto){
			String reg = txtRegistroProducto.getText().toUpperCase();
			if(!(reg.trim().equals(""))){
				String eliminar ="DELETE FROM productos WHERE registro='"+reg+"'";
				String existe = "SELECT COUNT(*) FROM productos WHERE registro='"+reg+"' AND eliminado = 0";
				if((existe=datos.getDato(existe)).equals("1")){
					int acepto= JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar este producto?\nEste productos no podra ser vendido nuevamente.", "¿SEGURO?", JOptionPane.YES_NO_OPTION);
					if(acepto==JOptionPane.YES_OPTION){
						String idProducto = datos.getDato("SELECT id FROM productos WHERE registro='"+reg+"'");
						ArrayList<String> idPaquete = datos.getDatoArray("SELECT idPaquete FROM contiene WHERE idProductos="+Integer.parseInt(idProducto));
						//ArrayList<String> nombre = new ArrayList<String>();
						if(idPaquete.size()>0){
							String nombres="";
							String nombreId="";
							for(int i =0; i<=idPaquete.size()-1; i++){
								nombreId = (String)idPaquete.get(i);
								nombres += datos.getDato("SELECT nombre FROM productos WHERE id="+Integer.parseInt(nombreId))+"\n";
							}
							JOptionPane.showMessageDialog(this, "Los siguientes paquetes serán modificados:\n"+nombres, "INFORMACIÓN", JOptionPane.DEFAULT_OPTION);
							acepto = JOptionPane.showConfirmDialog(this, "Al eliminar este producto se modificarán los paquetes anteriormente mencionados.\n¿Desea continuar?", "¿SEGURO?", JOptionPane.YES_NO_OPTION);
							if(acepto==JOptionPane.YES_OPTION){
								datos.Modificaciones(eliminar);
								eliminaDePaquete(reg, idPaquete);
								limpiarTextos();
								llenarTablaProductos();
								JOptionPane.showMessageDialog(null, "El producto con registro: "+txtRegistroProducto.getText()+", ha sido eliminado correctamente.", "Exito", JOptionPane.PLAIN_MESSAGE);
							}
							else{
								JOptionPane.showMessageDialog(this, "A cancelado la eliminación del producto.");
							}
						}
						else{
							datos.Modificaciones(eliminar);
							limpiarTextos();
							llenarTablaProductos();
							JOptionPane.showMessageDialog(null, "El producto con registro: "+txtRegistroProducto.getText()+", ha sido eliminado correctamente.", "Exito", JOptionPane.PLAIN_MESSAGE);
						}
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "No existe un producto con el registro que ha ingresado.\nIntentelo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Debe ingresar un registro existente para poder utilizar esta función.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(a.getSource()==btnModificarProducto){
			String nombre = txtNombreProducto.getText().toUpperCase();
			String registro = txtRegistroProducto.getText().toUpperCase();
			String descripcion = txtDescripcionProducto.getText();
			String precio = txtPrecioProducto.getText();
			String modificar="";
			String aux="";
			int numeros=0;
			float precioD=0;
			int modificados=0;
			String idProducto = datos.getDato("SELECT id FROM productos WHERE registro='"+registro+"'");
			if(!(registro.trim().equals(""))){
				boolean existe = datos.getUsuarioExiste("SELECT registro FROM productos WHERE registro ='"+registro+"'");
				if(existe){
					if(!(nombre.trim().equals(""))){
						modificados+=1;
					}
					if(!(descripcion.trim().equals(""))){
						modificados+=2;
					}
					if(!(precio.trim().equals(""))){
						try{
							precioD = Float.parseFloat(precio);
							precioD+=.00;
							modificados+=4;
						}
						catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null, "Debe ingresar solamente números en el campo precio.", "ERROR", JOptionPane.ERROR_MESSAGE);
							modificados=8;
						}
					}
					if(modificados==1){
						modificar="UPDATE productos SET nombre ='"+nombre+"', idUsuarios="+id+", fechaAlta='"+fecha+"' WHERE registro='"+registro+"'";
						datos.Modificaciones(modificar);
						JOptionPane.showMessageDialog(null, "El producto con registro: "+registro+" ha sido modificado correctamente.", "Correcto", JOptionPane.PLAIN_MESSAGE);
					}
					else if(modificados==2){
						descripcion=descripcion(descripcion);
						modificar="UPDATE productos SET descripcion ='"+descripcion+"', idUsuarios="+id+", fechaAlta='"+fecha+"' WHERE registro='"+registro+"'";
						datos.Modificaciones(modificar);
						ArrayList<String> idPaquete = datos.getDatoArray("SELECT idPaquete FROM contiene WHERE idProductos="+Integer.parseInt(idProducto));
						if(idPaquete.size()>0){
							modificarDePaquete(descripcion, registro);
						}
						JOptionPane.showMessageDialog(null, "El producto con registro: "+registro+" ha sido modificado correctamente.", "Correcto", JOptionPane.PLAIN_MESSAGE);
					}
					else if(modificados == 3){
						descripcion=descripcion(descripcion);
						modificar="UPDATE productos SET nombre ='"+nombre+"', descripcion ='"+descripcion+"', idUsuarios="+id+", fechaAlta='"+fecha+"' WHERE registro='"+registro+"'";
						datos.Modificaciones(modificar);
						ArrayList<String> idPaquete = datos.getDatoArray("SELECT idPaquete FROM contiene WHERE idProductos="+Integer.parseInt(idProducto));
						if(idPaquete.size()>0){
							modificarDePaquete(descripcion, registro);
						}
						JOptionPane.showMessageDialog(null, "El producto con registro: "+registro+" ha sido modificado correctamente.", "Correcto", JOptionPane.PLAIN_MESSAGE);
					}
					else if(modificados == 4){
						modificar = "UPDATE productos SET precio ='"+precioD+"', idUsuarios="+id+", fechaAlta='"+fecha+"' WHERE registro ='"+registro+"'";
						datos.Modificaciones(modificar);
						JOptionPane.showMessageDialog(null, "El producto con registro: "+registro+" ha sido modificado correctamente.", "Correcto", JOptionPane.PLAIN_MESSAGE);
					}
					else if(modificados == 5){
						modificar="UPDATE productos SET nombre ='"+nombre+"', precio ='"+precioD+"', idUsuarios="+id+", fechaAlta='"+fecha+"' WHERE registro='"+registro+"'";
						datos.Modificaciones(modificar);
						JOptionPane.showMessageDialog(null, "El producto con registro: "+registro+" ha sido modificado correctamente.", "Correcto", JOptionPane.PLAIN_MESSAGE);
					}
					else if(modificados == 6){
						descripcion=descripcion(descripcion);
						modificar="UPDATE productos SET descripcion ='"+descripcion+"', precio ='"+precioD+"', idUsuarios="+id+", fechaAlta='"+fecha+"' WHERE registro='"+registro+"'";
						datos.Modificaciones(modificar);
						ArrayList<String> idPaquete = datos.getDatoArray("SELECT idPaquete FROM contiene WHERE idProductos="+Integer.parseInt(idProducto));
						if(idPaquete.size()>0){
							modificarDePaquete(descripcion, registro);
						}
						JOptionPane.showMessageDialog(null, "El producto con registro: "+registro+" ha sido modificado correctamente.", "Correcto", JOptionPane.PLAIN_MESSAGE);
					}
					else if(modificados == 7){
						descripcion=descripcion(descripcion);
						modificar="UPDATE productos SET nombre ='"+nombre+"', descripcion ='"+descripcion+"', precio ='"+precioD+"', idUsuarios="+id+", fechaAlta='"+fecha+"' WHERE registro='"+registro+"'";
						datos.Modificaciones(modificar);
						ArrayList<String> idPaquete = datos.getDatoArray("SELECT idPaquete FROM contiene WHERE idProductos="+Integer.parseInt(idProducto));
						if(idPaquete.size()>0){
							modificarDePaquete(descripcion, registro);
						}
						JOptionPane.showMessageDialog(null, "El producto con registro: "+registro+" ha sido modificado correctamente.", "Correcto", JOptionPane.PLAIN_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Debe ingresar todos los campos correctamente.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					limpiarTextos();
					llenarTablaProductos();
					llenarListaProductos();
				}
				else{
					JOptionPane.showMessageDialog(null, "El registro ingresado no existe, revise la información ingresada.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Debe ingresar un registro existente para poder utilizar esta función.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(a.getSource()==btnAgregarProducto){	
			int seleccion = listaProducto.getSelectedIndex();
			if(seleccion!=-1){
				String opcion = listaProducto.getSelectedValue().toString();
				listaContenidoPaquete.add(opcion);
				modeloProducto.removeElementAt(seleccion);
				modeloPaquete.addElement(opcion);
			}
			else{
				JOptionPane.showMessageDialog(null, "Debe seleccionar un producto para agregarlo al contenido de este paquete.", "ATENCION", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(a.getSource()==btnQuitarProducto){
			int seleccion = listaContenido.getSelectedIndex();
			if(seleccion!=-1){
				String opcion = listaContenido.getSelectedValue().toString();
				modeloProducto.addElement(opcion);
				listaContenidoPaquete.remove(seleccion);
				modeloPaquete.removeElementAt(seleccion);
			}
			else{
				JOptionPane.showMessageDialog(null, "Debe seleccionar un producto para quitarlo del contenido de este paquete.", "ATENCION", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(a.getSource()==btnClearLista){
			modeloProducto.removeAllElements();
			modeloPaquete.removeAllElements();
			listaContenidoPaquete.clear();
			llenarListaProductos();
		}
		else if(a.getSource()==btnCrearPaquete){
			String nombre = txtNombrePaquete.getText().toUpperCase();
			String registro = txtRegistro.getText().toUpperCase();
			String precio = txtPrecioPaquete.getText();
			boolean bandera = true;
			if(nombre.trim().equals("")){
				bandera = false;
			}
			if(registro.trim().equals("")){
				bandera = false;
			}
			if(precio.trim().equals("")){
				bandera = false;
			}
			else{
				try{
					Float.parseFloat(precio);
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Debe de ingresar sólo números en el campo precio.", "Error", JOptionPane.ERROR_MESSAGE);
					bandera = false;
				}
			}
			if(bandera){
				if(listaContenidoPaquete.size()>0){
					String count = datos.getDato("SELECT COUNT(*) FROM productos WHERE tipo = 2 AND nombre='"+nombre+"'");
					if(count.equals("0")){
						String valida = datos.getDato("SELECT COUNT(*) FROM productos WHERE tipo = 2 AND registro ='"+registro+"'");
						if(valida.equals("0")){
							boolean funciona = crearPaquete(nombre, registro, precio, listaContenidoPaquete);
							if(funciona){
								JOptionPane.showMessageDialog(null, "El paquete:"+nombre+" ha sido creado correctamente.", "CORRECTO", JOptionPane.PLAIN_MESSAGE);
								limpiarTextos();
								modeloProducto.removeAllElements();
								modeloPaquete.removeAllElements();
								listaContenidoPaquete.clear();
								llenarListaProductos();
								llenarTablaPaquetes();
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Ya existe un paquete con este registro, por favor ingrese un nuevo nombre", "ATENCION", JOptionPane.WARNING_MESSAGE);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Ya existe un paquete con este nombre, por favor ingrese un nuevo registro", "ATENCION", JOptionPane.WARNING_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Debe de ingresar productos a la lista de contenido.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Debe llenar todos los campos correctamente", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(a.getSource()==btnEliminarPaquete){
			String registro = txtRegistro.getText().toUpperCase();
			if(!registro.trim().equals("")){
					String existe = datos.getDato("SELECT COUNT(*) FROM productos WHERE registro = '"+registro+"'");
				if(existe.equals("1")){
					datos.Modificaciones("DELETE FROM contiene WHERE idPaquete="+Integer.parseInt(datos.getDato("SELECT id FROM productos WHERE registro='"+registro+"'")));
					datos.Modificaciones("DELETE FROM productos WHERE registro = '"+registro+"'");
					limpiarTextos();
					llenarTablaPaquetes();
					JOptionPane.showMessageDialog(null, "El producto con registro: "+registro+" ha sido eliminado", "CORRECTO", JOptionPane.PLAIN_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "Debe ingresar un registro existente.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Debe ingresar un registro existente para poder utilizar esta función", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(a.getSource()==btnModificarPaquete){
			String registro = txtRegistro.getText().toUpperCase();
			String nombre = txtNombrePaquete.getText().toUpperCase();
			String precio = txtPrecioPaquete.getText();
			String idPaquete = "";
			int modificados=0;
			float prec = 0;
			if(!registro.trim().equals("")){
				idPaquete = datos.getDato("SELECT id FROM productos WHERE registro='"+registro+"'");
				if(listaContenidoPaquete.size()>0){
					if(!nombre.trim().equals("")){
						modificados+=1;
					}
					if(!precio.trim().equals("")){
						try{
							modificados+=2;
							prec = Float.parseFloat(precio);							
						}
						catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null, "Debe ingresar números solamente en el campo precio", "ATENCION", JOptionPane.WARNING_MESSAGE);
							modificados=4;
						}
					}
					if(modificados==0){
						modificarPaquete(idPaquete,listaContenidoPaquete);
						JOptionPane.showMessageDialog(null, "El pauqete con registro: "+registro+" ha sido modificado.", "CORRECTO", JOptionPane.PLAIN_MESSAGE);
					}
					else if(modificados==1){
						datos.Modificaciones("UPDATE productos SET nombre = '"+nombre+"', idUsuarios="+id+", fechaAlta='"+fecha+"' WHERE registro = '"+registro+"'");
						modificarPaquete(idPaquete, listaContenidoPaquete);
						JOptionPane.showMessageDialog(null, "El pauqete con registro: "+registro+" ha sido modificado.", "CORRECTO", JOptionPane.PLAIN_MESSAGE);
					}
					else if(modificados == 2){
						datos.Modificaciones("UPDATE productos SET precio = "+prec+", idUsuarios="+id+", fechaAlta='"+fecha+"' WHERE registro = '"+registro+"'");
						JOptionPane.showMessageDialog(null, "El pauqete con registro: "+registro+" ha sido modificado.", "CORRECTO", JOptionPane.PLAIN_MESSAGE);
						modificarPaquete(idPaquete, listaContenidoPaquete);
					}
					else if(modificados == 3){
						datos.Modificaciones("UPDATE productos SET nombre = '"+nombre+"', precio = "+prec+", idUsuarios="+id+", fechaAlta='"+fecha+"' WHERE registro = '"+registro+"'");
						modificarPaquete(idPaquete, listaContenidoPaquete);
					}
					else{
						JOptionPane.showMessageDialog(null, "Debe ingresar todos los campos correctamente.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					limpiarTextos();
					modeloProducto.removeAllElements();
					modeloPaquete.removeAllElements();
					listaContenidoPaquete.clear();
					llenarListaProductos();
					llenarTablaPaquetes();
				}
				else{
					if(!nombre.trim().equals("")){
						modificados+=1;
					}
					if(!precio.trim().equals("")){
						try{
							modificados+=2;
							prec = Float.parseFloat(precio);
						}
						catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null, "Debe ingresar números solamente en el campo precio", "ATENCION", JOptionPane.WARNING_MESSAGE);
							modificados=4;
						}
					}
					if(modificados==1){
						datos.Modificaciones("UPDATE productos SET nombre = '"+nombre+"', idUsuarios="+id+", fechaAlta='"+fecha+"' WHERE registro = '"+registro+"'");
						JOptionPane.showMessageDialog(null, "El pauqete con registro: "+registro+" ha sido modificado.", "CORRECTO", JOptionPane.PLAIN_MESSAGE);
					}
					else if(modificados == 2){
						datos.Modificaciones("UPDATE productos SET precio = "+prec+", idUsuarios="+id+", fechaAlta='"+fecha+"' WHERE registro = '"+registro+"'");
						JOptionPane.showMessageDialog(null, "El pauqete con registro: "+registro+" ha sido modificado.", "CORRECTO", JOptionPane.PLAIN_MESSAGE);
					}
					else if(modificados == 3){
						datos.Modificaciones("UPDATE productos SET nombre = '"+nombre+"', precio = "+prec+", idUsuarios="+id+", fechaAlta='"+fecha+"' WHERE registro = '"+registro+"'");
					}
					else{
						JOptionPane.showMessageDialog(null, "Debe ingresar todos los campos correctamente.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					limpiarTextos();
					modeloProducto.removeAllElements();
					modeloPaquete.removeAllElements();
					listaContenidoPaquete.clear();
					llenarListaProductos();
					llenarTablaPaquetes();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Debe ingresar un registro del producto que desee modificar.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//Método para verificar los campos----------------------------------------------------------
	private boolean verificaCampos(String registro, String nombre, String descripcion, String precio){
		boolean resultado = true;
		@SuppressWarnings("unused")
		String mensaje = "Por favor llene todos los campos correctamente.\n Verifique los siguientes campos:\n";
		if(registro.trim().equals(""))
		{
			resultado=false;
			mensaje+="-Resgistro.\n";
		}
		if(nombre.trim().equals("")){
			resultado=false;
			mensaje+="-Nombre.\n";
		}
		if(descripcion.trim().equals("")){
			resultado=false;
			mensaje+="-Descripción.\n";
		}
		if(precio.trim().equals("")){
			resultado=false;
			mensaje+="-Precio.\n";
		}
		else{
			String ePrecio = "^\\d+$";
			Pattern pattern = Pattern.compile(ePrecio);
			Matcher matcher = pattern.matcher(precio);
			if(!matcher.matches()){
				JOptionPane.showMessageDialog(null, "Debe de ingresar sólo números en el campo precio.", "Error", JOptionPane.ERROR_MESSAGE);
				resultado=false;
				mensaje+="-Precio.\n";
			}
		}
		return resultado;
	}
	
	//Metodo para limpiar textos-----------------------------------------------------------------
	private void limpiarTextos(){
		txtRegistro.setText("");
		txtRegistroProducto.setText("");
		txtNombreProducto.setText("");
		txtNombrePaquete.setText("");
		txtDescripcionProducto.setText("");
		txtPrecioProducto.setText("");
		txtPrecioPaquete.setText("");
	}
	
	//Metodo para lenar tabal de productos--------------------------------------------------------
	private void llenarTablaProductos(){
		String sql;
		//if(id==0){
			sql="SELECT registro, nombre, descripcion, precio FROM productos WHERE tipo=1 AND eliminado=0";
		/*}
		else{
			sql="SELECT registro, nombre, descripcion, precio FROM productos WHERE tipo=1 AND eliminado=0 AND idUsuarios="+id;
		}*/
		modelProductos=datos.getModelProductos(sql);
		tablaProductos.setModel(modelProductos);	
		thProducto = tablaProductos.getTableHeader();
		thProducto.setFont(new Font("Arial", 1, 17));
		tablaProductos.getColumnModel().getColumn(0).setMaxWidth(130);
		tablaProductos.getColumnModel().getColumn(0).setMinWidth(130);
		tablaProductos.getColumnModel().getColumn(0).setCellRenderer(alineaCentro);
		tablaProductos.getColumnModel().getColumn(1).setMaxWidth(410);
		tablaProductos.getColumnModel().getColumn(1).setMinWidth(410);
		tablaProductos.getColumnModel().getColumn(1).setCellRenderer(alineaCentro);
		tablaProductos.getColumnModel().getColumn(3).setMaxWidth(100);
		tablaProductos.getColumnModel().getColumn(3).setMinWidth(100);
		tablaProductos.getColumnModel().getColumn(3).setCellRenderer(alineaCentro);
		tablaProductos.setFont(new Font("Arial", 0, 15));
		tablaProductos.setTableHeader(thProducto);
		scrollProductos.getViewport().add(tablaProductos);
	}
	//Método para llenar la tabla paquetes---------------------------------------------------------------------------------
		public void llenarTablaPaquetes(){
			String sql;
			//if(id==0){
				sql="SELECT registro, nombre, descripcion, precio FROM productos WHERE tipo=2 AND eliminado=0";
			/*}
			else{
				sql="SELECT registro, nombre, descripcion, precio FROM productos WHERE tipo=2 AND eliminado=0 AND idUsuarios="+id;
			}*/
			modelPaquetes = datos.getModelProductos(sql);
			tablaPaquetes.setModel(modelPaquetes);
			thPaquetes = tablaPaquetes.getTableHeader();
			thPaquetes.setFont(new Font("Arial", 1, 16));
			tablaPaquetes.setFont(new Font("Arial", 0, 15));
			tablaPaquetes.getColumnModel().getColumn(0).setMinWidth(80);
			tablaPaquetes.getColumnModel().getColumn(0).setMaxWidth(80);
			tablaPaquetes.getColumnModel().getColumn(0).setCellRenderer(alineaCentro);
			tablaPaquetes.getColumnModel().getColumn(1).setMinWidth(110);
			//tablaPaquetes.getColumnModel().getColumn(1).setMaxWidth(110);
			tablaPaquetes.getColumnModel().getColumn(2).setMinWidth(130);
			tablaPaquetes.getColumnModel().getColumn(3).setMinWidth(100);
			tablaPaquetes.getColumnModel().getColumn(3).setCellRenderer(alineaCentro);
			tablaPaquetes.setTableHeader(thPaquetes);
			scrollPaquetes.getViewport().add(tablaPaquetes);
			
		}
	//Método para llenar lista de productos---------------------------------------------------------------
	@SuppressWarnings("unchecked")
	private void llenarListaProductos(){
		String sql="SELECT nombre FROM productos WHERE tipo =1 AND eliminado=0";
		modeloProducto = datos.getLista(sql);
		listaProducto.setModel(modeloProducto);
		listaProducto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaProducto.setLayoutOrientation(JList.VERTICAL);
		scrollLista2.getViewport().add(listaProducto);
	}
	//Método para crear paquete-----------------------------------------------------------------------------
	private boolean crearPaquete(String nombre, String registro, String precio, ArrayList<String> lista){
		int posicion=0;
		boolean bandera = true;
		int parar = lista.size();
		String descripcion="";
		ArrayList<String> descripciones = new ArrayList<String>();
		ArrayList<String> idProducto= new ArrayList<String>();
		String dato;
		try{
			//int i =0;
			while(posicion<parar){
				dato = lista.get(posicion).toString();
				descripciones.add(datos.getDato("SELECT descripcion FROM productos WHERE nombre='"+dato+"'"));
				descripcion +="->"+descripciones.get(posicion);
				if(posicion!=parar-1){
					descripcion += "\n";
				}
				idProducto.add(datos.getDato("SELECT id FROM productos WHERE nombre ='"+dato+"'"));
				posicion++;
			}
			datos.Modificaciones("INSERT INTO productos (registro, nombre, descripcion, precio, idUsuarios, tipo, fechaAlta, eliminado) VALUES('"+registro+"', '"+nombre+"', '"+descripcion+"', "+Float.parseFloat(precio)+","+id+", 2,'"+fecha+"' ,0)");
			String idPaquete = datos.getDato("SELECT id FROM productos WHERE registro = '"+registro+"'");
			posicion=0;
			while(posicion<parar){
				datos.Modificaciones("INSERT INTO contiene(idPaquete, idProductos, descripcion, idUsuario) VALUES ("+Integer.parseInt(idPaquete)+", "+Integer.parseInt(idProducto.get(posicion))+",'"+descripciones.get(posicion)+"', "+id+")");
				posicion++;
			}
		}
		catch(Exception e){
			bandera = false;
			JOptionPane.showMessageDialog(null, e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return bandera;
	}
	//Método para modificar los paquetes------------------------------------------------------------------------
	private boolean modificarPaquete(String idPaquete, ArrayList <String> lista){
		boolean retorno = false;
		String descripcion ="";
		String descripciones ="";
		String id="";
		try{
			datos.Modificaciones("DELETE FROM contiene WHERE idPaquete="+idPaquete);
			for(int i = 0; i<=lista.size()-1; i++){
				id = datos.getDato("SELECT id FROM productos WHERE nombre='"+lista.get(i)+"'");
				descripciones = datos.getDato("SELECT descripcion FROM productos WHERE id="+Integer.parseInt(id));
				descripcion += "->"+descripciones;
				if(i!=lista.size()-1){
					descripcion += "\n";
				}
				datos.Modificaciones("INSERT INTO contiene(idPaquete, idProductos, descripcion, idUsuario ) VALUES ("+idPaquete+", "+id+", '"+descripciones+"', "+this.id+") ");
			}
			datos.Modificaciones("UPDATE productos SET descripcion = '"+descripcion+"', idUsuarios ="+this.id+", fechaAlta='"+fecha+"' WHERE id="+idPaquete);
			retorno = true;
		}
		catch(Exception e){
			retorno = false;
			JOptionPane.showMessageDialog(this, e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return retorno;
	}
	//Método para modificar paquetes-----------------------------------------------------------------------------
	private void modificarDePaquete(String descripcion, String registro){
		String id = datos.getDato("SELECT id FROM productos WHERE registro = '"+registro+"'");
		datos.Modificaciones("UPDATE contiene SET descripcion ='"+descripcion+"' WHERE idProductos="+Integer.parseInt(id));
		ArrayList<String> listaPaquetes = datos.getDatoArray("SELECT idPaquete FROM contiene WHERE idProductos="+Integer.parseInt(id));
		ArrayList<String> descripciones;
		descripcion ="";
		for(int i =0; i<=listaPaquetes.size()-1; i++){
			descripciones = datos.getDatoArray("SELECT descripcion FROM contiene WHERE idPaquete="+Integer.parseInt((String)listaPaquetes.get(i)));
			for(int a=0; a<=descripciones.size()-1; a++){
				descripcion +="->"+descripciones.get(a);
				if(i!=listaPaquetes.size()-1){
					descripcion += "\n";
				}
			}
			datos.Modificaciones("UPDATE productos set descripcion='"+descripcion+"', fechaAlta='"+fecha+"' WHERE id="+Integer.parseInt((String)listaPaquetes.get(i)));
			descripciones.clear();
			descripcion="";
		}
		
	}
	//Método para modificar un producto de un paquete Description only---------------------------------------------
	private void eliminaDePaquete(String registro, ArrayList<String> listaPaquetes){
		String descripcion="";
		ArrayList<String> descripciones;
		String id = datos.getDato("SELECT id FROM productos WHERE registro='"+registro+"'");
		try{
			datos.Modificaciones("DELETE FROM contiene WERE idProducto="+Integer.parseInt(id));
			for(int i =0; i<=listaPaquetes.size()-1; i++){
				descripciones = datos.getDatoArray("SELECT descripcion FROM contiene WHERE idPaquete="+Integer.parseInt((String)listaPaquetes.get(i)));
				for(int a=0; a<=descripciones.size()-1; a++){
					descripcion +="->"+descripciones.get(a)+"\n";
				}
				datos.Modificaciones("UPDATE productos set descripcion='"+descripcion+"', fechaAlta='"+fecha+"' WHERE id="+Integer.parseInt((String)listaPaquetes.get(i)));
			}
		}
		catch(NumberFormatException e){
				JOptionPane.showMessageDialog(this, e.toString());
		}
	}
	//Método para buscar numeros----------------------------------------------------------------------------------
	private boolean numero(char car){
		boolean retorno = false;
		if(String.valueOf(car).equals(" ")){
			retorno=true;
		}
		else{
			retorno = false;
		}
		return retorno;
	}
	//Metodo para tener la descripcion con salto de linea---------------------------------------------------------------
	private String descripcion(String descripcion){
		String aux="";
		int numeros=0;
		for(int x=0; x<descripcion.length();x++){
			if(numero(descripcion.charAt(x))){
				numeros++;
				if(numeros==4){
					aux += "\n"+descripcion.charAt(x);
				}
				else{
					aux+=descripcion.charAt(x);
				}
			}
			else{
				aux+=descripcion.charAt(x);
			}
		}
		return aux;
	}
}
