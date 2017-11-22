package sistemaOlvera;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class SeeProductsAdmin extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Conexion datos;
	//Componentes-------------------------------------------------------
	private JPanel productosPanel = new JPanel();
	private JPanel paquetesPanel = new JPanel();
	private JPanel buscarProducto = new JPanel();
	private JPanel buscarPaquetes = new JPanel();
	private JButton btnRegresar = new JButton("Regresar");
	private JButton btnBuscar = new JButton("Visualizar");
	JTextField txtBuscar = new JTextField();
	JTextField txtNomPaquete = new JTextField();
	JTextField txtNomProducto = new JTextField();
	private JLabel lblBuscar = new JLabel("Buscar: ");
	private JLabel lblProducto = new JLabel("Producto: ");
	private JLabel lblPaquete = new JLabel("Paquete: ");
	private JTable paquetesTable;
	private JScrollPane scrollPanePack;
	private DefaultTableModel modeloTabla = new DefaultTableModel();
	private DefaultTableModel modeloPaquete = new DefaultTableModel();
	private JTable productosTable;
	private JScrollPane scrollPaneProducts;
	private JTableHeader thProductos;
	private JTableHeader thPaquetes;
	private DefaultTableCellRenderer alineaCentro = new DefaultTableCellRenderer();
	
	//Constructor----------------------------------------------------------------------------------------
	public SeeProductsAdmin(Conexion x){
		super("Ver Productos/Paquetes - Sistema Olvera");
		datos=x;
		//Configuración de la ventana----------------------------------
		this.setSize(1500, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setVisible(true);
		
		//Configurar componenetes-----------------------------------------------------------------------
		
		//Botones, textfiel y labels----------------------------------
		btnRegresar.setFont(new Font("Arial",0, 16));
		txtBuscar.setFont(new Font("Arial",0,16));
		btnBuscar.setFont(new Font("Arial", 0, 16));
		lblBuscar.setFont(new Font("Arial",0,16));
		txtNomPaquete.setFont(new Font("Arial",0,16));
		lblPaquete.setFont(new Font("Arial",0,16));
		lblProducto.setFont(new Font("Arial",0,16));
		txtNomProducto.setFont(new Font("Arial",0,16));
		alineaCentro.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Tablas-------------------------------------------------------
		paquetesTable = new JTable();
		paquetesTable.addMouseListener(new MouseAdapter(){
						public void mousePressed(MouseEvent e){
							JTable aux = (JTable)e.getSource();
							Point p = e.getPoint();
							int fila = aux.rowAtPoint(p);
							if(e.getClickCount()==1){
								Info mensaje = new Info("Datos del paquete");
								String datos = "  Registro: ";
								datos+= paquetesTable.getValueAt(paquetesTable.getSelectedRow(), 0);
								datos += "    Nombre: "+paquetesTable.getValueAt(paquetesTable.getSelectedRow(), 1);
								mensaje.setMensaje(datos);
								datos = "  Descripción:\n"+paquetesTable.getValueAt(paquetesTable.getSelectedRow(), 2);
								mensaje.setMensaje(datos);
								datos = "  Precio: "+paquetesTable.getValueAt(paquetesTable.getSelectedRow(), 3);
								mensaje.setMensaje(datos);
								mensaje.showDialog();
							}
						}
					}
				);
		scrollPanePack = new JScrollPane(paquetesTable);
		llenarTablaPaquetes();
		productosTable= new JTable();
		productosTable.addMouseListener(new MouseAdapter(){
					public void mousePressed(MouseEvent e){
							JTable aux = (JTable)e.getSource();
							Point p = e.getPoint();
							int fila = aux.rowAtPoint(p);
							if(e.getClickCount()==1){
								Info mensaje = new Info("Datos del paquete");
								String datos = "  Registro: ";
								datos+= productosTable.getValueAt(productosTable.getSelectedRow(), 0);
								datos = "    Nombre: "+productosTable.getValueAt(productosTable.getSelectedRow(), 1);
								mensaje.setMensaje(datos);
								datos = "  Descripción: "+productosTable.getValueAt(productosTable.getSelectedRow(), 2);
								mensaje.setMensaje(datos);
								datos = "  Precio: "+productosTable.getValueAt(productosTable.getSelectedRow(), 3)+"";
								mensaje.setMensaje(datos);
								mensaje.showDialog();
							}
						}
					}
				);
		scrollPaneProducts = new JScrollPane(productosTable);
		llenarTablaProductos();
		
		//Paneles------------------------------------------------------
		//Panel buscar productos-------------------------------
		/*GroupLayout layProduBuscar = new GroupLayout(buscarProducto);
		layProduBuscar.setHorizontalGroup(
				layProduBuscar.createSequentialGroup()
					.addComponent(lblProducto)
					.addComponent(txtNomProducto)
		);
		layProduBuscar.setVerticalGroup(
				layProduBuscar.createParallelGroup(Alignment.CENTER)
				.addGap(55)
				.addComponent(lblProducto)
				.addComponent(txtNomProducto,20,30,30)
		);
		buscarProducto.setLayout(layProduBuscar);*/
		
		//Panel buscar paquetes--------------------------------
		/*GroupLayout groupPaquetesBuscar = new GroupLayout(buscarPaquetes);
		groupPaquetesBuscar.setAutoCreateContainerGaps(true);
		groupPaquetesBuscar.setAutoCreateGaps(true);
		groupPaquetesBuscar.setHorizontalGroup(
				groupPaquetesBuscar.createSequentialGroup()
					.addComponent(lblPaquete)
					.addComponent(txtNomPaquete)
					.addComponent(btnBuscar)
		);
		groupPaquetesBuscar.setVerticalGroup(
				groupPaquetesBuscar.createParallelGroup(Alignment.CENTER)
					.addComponent(lblPaquete)
					.addComponent(txtNomPaquete,20,30,30)
					.addComponent(btnBuscar)
		);
		buscarPaquetes.setLayout(groupPaquetesBuscar);*/
		
		//Panel productos-------------------------------------
		GroupLayout panelProductos = new GroupLayout(productosPanel);
		panelProductos.setAutoCreateContainerGaps(true);
		panelProductos.setAutoCreateGaps(true);
		panelProductos.setHorizontalGroup(
				panelProductos.createParallelGroup()
					.addComponent(scrollPaneProducts)
					//.addComponent(buscarProducto)
		);
		panelProductos.setVerticalGroup(
				panelProductos.createSequentialGroup()
					.addComponent(scrollPaneProducts)
					//.addComponent(buscarProducto,50,80,80)
		);
		productosPanel.setLayout(panelProductos);
		productosPanel.setBorder(new TitledBorder(null, "Productos", 0, 0, new Font("Arial", 1, 25)));
		
		//Panel paquetes---------------------------------------
		GroupLayout panelPaquetes = new GroupLayout(paquetesPanel);
		panelPaquetes.setAutoCreateContainerGaps(true);
		panelPaquetes.setAutoCreateGaps(true);
		panelPaquetes.setHorizontalGroup(
				panelPaquetes.createParallelGroup()
					.addComponent(scrollPanePack)
					//.addComponent(buscarPaquetes)
		);
		panelPaquetes.setVerticalGroup(
				panelPaquetes.createSequentialGroup()
					.addComponent(scrollPanePack)
					//.addComponent(buscarPaquetes,50,80,80)
		);
		paquetesPanel.setLayout(panelPaquetes);
		paquetesPanel.setBorder(new TitledBorder(null, "Paquetes", 0, 0, new Font("Arial", 1, 25)));
		
		//Panel principal--------------------------------------
		GroupLayout layPrincipal = new GroupLayout(this.getContentPane());
		layPrincipal.setAutoCreateContainerGaps(true);
		layPrincipal.setAutoCreateGaps(true);
		layPrincipal.setHorizontalGroup(
				layPrincipal.createSequentialGroup()
				.addComponent(paquetesPanel)
				.addComponent(productosPanel)
		);
		layPrincipal.setVerticalGroup(
				layPrincipal.createParallelGroup()
				.addComponent(paquetesPanel)
				.addComponent(productosPanel)
		);
		this.setLayout(layPrincipal);
	}
	
	//Método action para eventos-------------------------------------------------------
	public void actionPerformed(ActionEvent a) {
		
	}
	//Método para llenar tablas-------------------------------------------------------
	public void llenarTablaProductos(){
		modeloTabla = datos.getModelProductos("SELECT registro, nombre, descripcion, precio FROM productos WHERE tipo=1 AND eliminado=0");
		productosTable.setModel(modeloTabla);
		productosTable.getColumnModel().getColumn(0).setMinWidth(100);
		productosTable.getColumnModel().getColumn(0).setMaxWidth(100);
		productosTable.getColumnModel().getColumn(0).setCellRenderer(alineaCentro);
		productosTable.getColumnModel().getColumn(1).setMinWidth(180);
		productosTable.getColumnModel().getColumn(1).setMaxWidth(180);
		productosTable.getColumnModel().getColumn(1).setCellRenderer(alineaCentro);
		productosTable.getColumnModel().getColumn(3).setMinWidth(75);
		productosTable.getColumnModel().getColumn(3).setMaxWidth(75);
		productosTable.getColumnModel().getColumn(3).setCellRenderer(alineaCentro);
		productosTable.setFont(new Font("Arial", 0, 15));
		thProductos = productosTable.getTableHeader();
		thProductos.setFont(new Font("Arial", 1, 16));
		productosTable.setTableHeader(thProductos);
		scrollPaneProducts.getViewport().add(productosTable);
	}
	public void llenarTablaPaquetes(){
		modeloPaquete = datos.getModelProductos("SELECT registro, nombre, descripcion, precio FROM productos WHERE tipo=2 AND eliminado=0");
		paquetesTable.setModel(modeloPaquete);
		paquetesTable.getColumnModel().getColumn(0).setMinWidth(100);
		paquetesTable.getColumnModel().getColumn(0).setMaxWidth(100);
		paquetesTable.getColumnModel().getColumn(0).setCellRenderer(alineaCentro);
		paquetesTable.getColumnModel().getColumn(1).setMinWidth(180);
		paquetesTable.getColumnModel().getColumn(1).setMaxWidth(180);
		paquetesTable.getColumnModel().getColumn(1).setCellRenderer(alineaCentro);
		paquetesTable.getColumnModel().getColumn(3).setMinWidth(75);
		paquetesTable.getColumnModel().getColumn(3).setMaxWidth(75);
		paquetesTable.getColumnModel().getColumn(3).setCellRenderer(alineaCentro);
		paquetesTable.setFont(new Font("Arial", 0, 15));
		thPaquetes = paquetesTable.getTableHeader();
		thPaquetes.setFont(new Font("Arial", 1, 16));
		paquetesTable.setTableHeader(thPaquetes);
		scrollPanePack.getViewport().add(paquetesTable);
	}
}
