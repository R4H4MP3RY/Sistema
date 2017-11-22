package sistemaOlvera;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.toedter.calendar.JDateChooser;

import net.sf.jasperreports.engine.export.oasis.CellStyle;
//Clase para buscar el historial de los cortes, buscarlos por fechas y tambien buscar las observaciones de los trabajos-----------------------------------------------
public class Historial extends JFrame implements ActionListener{
	private Conexion datos;
	private String tipo="";
	DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
	private JDateChooser fechaInicio = new JDateChooser();
	private JLabel lblDesde = new JLabel("Desde:");
	private JDateChooser fechaFinal = new JDateChooser();
	private JLabel lblHasta = new JLabel("Hasta:");
	private DefaultTableModel modelo = new DefaultTableModel();
	private JTable tabla = new JTable(modelo);
	private JScrollPane scroll = new JScrollPane(tabla);
	private JButton btnBuscar = new JButton("Buscar");
	private JButton btnImprimir = new JButton("Imprimir");
	private JButton btnLimpiar = new JButton("Limpiar");
	private JRadioButton rdBtnNotas = new JRadioButton("Notas");
	private JRadioButton rdBtnOrdenes = new JRadioButton("Ordenes");
	private JRadioButton rdBtnAmbos = new JRadioButton("Ambos");
	private JRadioButton rdBtnObservaciones = new JRadioButton("Observaciones");
	private JRadioButton rdBtnGastos = new JRadioButton("Gastos");
	private ButtonGroup grupoBotones = new ButtonGroup();
	private JPanel panelBotones = new JPanel();
	private JPanel panelInfo = new JPanel();
	private Date fecha = new Date();
	private Object[][] notas = {{new Object(), new Object(), ""}};
	private Object[][] ordenes = {{new Object(), new Object(), ""}};
	private Object[][] notasNum = {{new Object(), new Object(), ""}};
	private Object[][] ordenesNum = {{new Object(), new Object(), ""}};
	private Object[][] vacio = new Object[0][0];
	private JLabel lblFiltro = new JLabel("Filtrar:");
	private JRadioButton rdBtnFecha = new JRadioButton("Fecha.");
	private JRadioButton rdBtnNumero = new JRadioButton("Número.");
	private ButtonGroup grpFiltro = new ButtonGroup();
	private String numero = "t.numero";
	private String fFecha = "t.fechaAlta";
	private String fAmbos="fechaAlta";
	private String nAmbos="numero";
	private String oFecha = "o.fechaAlta";
	private String ordenar=numero;
	private String nombre="";
	private SimpleDateFormat f = new SimpleDateFormat("EE dd-MMMMM-yy");
	public Historial(Conexion x)  {
		super("Sistema Olvera-Historial");
		datos=x;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setVisible(true);
		//datos.conectar();
		//Iniciar los componenetes con sus caracteristicas---------------------------------------------------------------
		centro.setHorizontalAlignment(SwingConstants.CENTER);
		btnLimpiar.setFont(new Font("Arial", 0, 17));
		btnLimpiar.addActionListener(this);
		btnBuscar.setFont(new Font("Arial", 0, 17));
		btnBuscar.addActionListener(this);
		btnImprimir.setFont(new Font("Arial", 0, 20));
		btnImprimir.addActionListener(this);
		btnImprimir.setEnabled(false);
		lblDesde.setFont(new Font("Arial", 1, 16));
		lblHasta.setFont(new Font("Arial", 1, 16));
		lblFiltro.setFont(new Font("Arial", 1, 16));
		fechaInicio.setFont(new Font("Arial", 0, 15));
		fechaInicio.setDate(fecha);
		fechaFinal.setFont(new Font("Arial", 0, 15));
		fechaFinal.setDate(fecha);
		rdBtnNotas.setFont(new Font("Arial", 0, 16));
		rdBtnAmbos.setFont(new Font("Arial", 0, 16));
		rdBtnOrdenes.setFont(new Font("Arial", 0, 16));
		rdBtnObservaciones.setFont(new Font("Arial", 0, 16));
		rdBtnGastos.setFont(new Font("Arial", 0, 16));
		rdBtnFecha.setFont(new Font("Arial", 0, 16));
		rdBtnNumero.setFont(new Font("Arial", 0, 16));
		grupoBotones.add(rdBtnNotas);
		grupoBotones.add(rdBtnOrdenes);
		grupoBotones.add(rdBtnAmbos);
		grupoBotones.add(rdBtnObservaciones);
		grupoBotones.add(rdBtnGastos);
		grpFiltro.add(rdBtnFecha);
		grpFiltro.add(rdBtnNumero);
		/*rdBtnNumero.setSelected(true);
		rdBtnNotas.setSelected(true);*/
		//Botones de filtro----------------------------------------------------------------------------------------------
		rdBtnNumero.addMouseListener(new MouseAdapter(){
						public void mousePressed(MouseEvent e){
							if(rdBtnAmbos.isSelected()){
								ordenar=nAmbos;
							}
							else{
								ordenar=numero;
							}
						}
					}
				);
		rdBtnFecha.addMouseListener(new MouseAdapter(){
						public void mousePressed(MouseEvent e){
							if(rdBtnObservaciones.isSelected()){
								ordenar=oFecha;
							}
							else if(rdBtnAmbos.isSelected()){
								ordenar=fAmbos;
							}
							else{
								ordenar = fFecha;
							}
						}
					}
				);
		rdBtnGastos.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				rdBtnNumero.setEnabled(false);
			}
		});
		//Panel de información-------------------------------------------------------------------------------------------
		GroupLayout layoutInfo = new GroupLayout(panelInfo);
		layoutInfo.setAutoCreateContainerGaps(true);
		layoutInfo.setAutoCreateGaps(true);
		layoutInfo.setHorizontalGroup(
					layoutInfo.createSequentialGroup()
					.addComponent(scroll)
				);
		layoutInfo.setVerticalGroup(
					layoutInfo.createParallelGroup(Alignment.CENTER)
					.addComponent(scroll)
				);
		panelInfo.setLayout(layoutInfo);
		panelInfo.setBorder(new TitledBorder(null, "Información", 0,0,new Font("Arial", 1, 20)));
		//Panel de botons-----------------------------------------------------------------------------------------------
		GroupLayout layoutBotones = new GroupLayout(panelBotones);
		layoutBotones.setAutoCreateContainerGaps(true);
		layoutBotones.setAutoCreateGaps(true);
		layoutBotones.setHorizontalGroup(
					layoutBotones.createParallelGroup()
					.addComponent(btnBuscar,100,100,100)
					.addComponent(btnLimpiar,100,100,100)
				);
		layoutBotones.setVerticalGroup(
					layoutBotones.createSequentialGroup()
					.addComponent(btnBuscar,30,30,30)
					.addComponent(btnLimpiar,30,30,30)
				);
		panelBotones.setLayout(layoutBotones);
		//Layout que contiene todos los componentes----------------------------------------------------------------------
		GroupLayout layout = new GroupLayout(this.getContentPane());
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(
					layout.createParallelGroup()
					.addGroup(
								layout.createSequentialGroup()
								.addGroup(
											layout.createParallelGroup()
											.addGroup(	
														layout.createSequentialGroup()
														.addComponent(lblDesde)
														.addComponent(fechaInicio, 150,150,150)
														.addComponent(lblHasta)
														.addComponent(fechaFinal, 150, 150, 150)
													)
											.addGroup(
														layout.createSequentialGroup()
														.addComponent(rdBtnNotas)
														.addComponent(rdBtnOrdenes)
														.addComponent(rdBtnAmbos)
														.addComponent(rdBtnObservaciones)
														.addComponent(rdBtnGastos)
													)
											.addGroup(
														layout.createSequentialGroup()
														.addComponent(lblFiltro)
														.addComponent(rdBtnNumero)
														.addComponent(rdBtnFecha)
													)
										)
								.addGroup(
											layout.createSequentialGroup()
											.addComponent(panelBotones)
										)
							)
					
					.addComponent(panelInfo,800,800,800)
					.addComponent(btnImprimir,125,125,125)
					
				);
		layout.setVerticalGroup(
					layout.createSequentialGroup()
					.addGroup(
								layout.createParallelGroup()
								.addGroup(
										layout.createSequentialGroup()
										.addGroup(
												layout.createParallelGroup()
												.addComponent(lblDesde)
												.addComponent(fechaInicio, 20, 20, 20)
												.addComponent(lblHasta)
												.addComponent(fechaFinal, 20, 20, 20)
											)
										.addGroup(
												layout.createParallelGroup()
												.addComponent(rdBtnNotas)
												.addComponent(rdBtnOrdenes)
												.addComponent(rdBtnAmbos)
												.addComponent(rdBtnObservaciones)
												.addComponent(rdBtnGastos)
												)
										.addGroup(
													layout.createParallelGroup()
													.addComponent(lblFiltro)
													.addComponent(rdBtnNumero)
													.addComponent(rdBtnFecha)
												)
										)
								.addGroup(
										layout.createSequentialGroup()
										.addComponent(panelBotones)
										)
							)
					.addComponent(panelInfo, 400, 400, 400)
					.addComponent(btnImprimir, 50, 50, 50)
				);
		this.setLayout(layout);
		pack();
	}
	public void actionPerformed(ActionEvent a) {
		if(a.getSource()==btnBuscar){
			try{
			if(!(fechaInicio.getDateFormatString().equals(""))){
				if(!(fechaFinal.getDateFormatString().equals(""))){
					SimpleDateFormat formatoInicio = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat formatoFinal = new SimpleDateFormat("yyy-MM-dd");
					String fechaIni = formatoInicio.format(fechaInicio.getDate());
					String fechaFin = formatoFinal.format(fechaFinal.getDate());
					String []titulosNO={"Fecha", "Numero", "Nombre", "Total", "Anticipo", "Debe", "Pagó"};
					String []titulosAm={"Numero", "Fecha",  "Nombre", "Total", "Anticipo", "Debe", "Pagó"};
					String []titulosOb={"Numero", "Fecha", "Descripción", "Observaciones", "Usuario", "Estatus"};
					String []titulosGa={"Fecha", "Usuario", "Monto", "Descripción"};
					String sqlAmbos ="SELECT * FROM(SELECT t.orden,  t.numero, t.fechaAlta, concat(nombres, ' ', apellidoP, ' ', apellidoM), subtotal, (SELECT monto FROM pagos WHERE id=(SELECT MIN(id) FROM pagos WHERE idTrabajos=t.id)), p.debe, p.monto FROM clientes c INNER JOIN trabajos t ON c.id = t.idCliente RIGHT JOIN pagos p ON t.id = p.idTrabajos WHERE orden=0 AND t.cancelacion=0 AND t.fechaAlta BETWEEN '"+fechaIni+"' AND '"+fechaFin+"'  UNION ALL SELECT t.orden,  t.numero, t.fechaAlta, concat(nombres, ' ', apellidoP, ' ', apellidoM), subtotal, (SELECT monto FROM pagos WHERE id=(SELECT MIN(id) FROM pagos WHERE idTrabajos=t.id)), p.debe, p.monto FROM clientes c INNER JOIN trabajos t ON c.id = t.idCliente RIGHT JOIN pagos p ON t.id = p.idTrabajos WHERE orden=1 AND t.cancelacion=0 AND t.fechaAlta BETWEEN '"+fechaIni+"' AND '"+fechaFin+"')tabla ORDER BY orden ASC, "+ordenar+" ASC";
					String sqlNotas ="SELECT t.fechaAlta, t.numero, concat(nombres, ' ', apellidoP, ' ', apellidoM), subtotal, (SELECT monto FROM pagos WHERE id=(SELECT MIN(id) FROM pagos WHERE idTrabajos=t.id)), p.debe, p.monto FROM clientes c INNER JOIN trabajos t ON c.id = t.idCliente RIGHT JOIN pagos p ON t.id = p.idTrabajos WHERE orden=0 AND t.cancelacion=0 AND t.fechaAlta BETWEEN '"+fechaIni+"' AND '"+fechaFin+"' ORDER BY "+ordenar+" ASC";
					String sqlOrden ="SELECT t.fechaAlta, t.numero, concat(nombres, ' ', apellidoP, ' ', apellidoM), subtotal, (SELECT monto FROM pagos WHERE id=(SELECT MIN(id) FROM pagos WHERE idTrabajos=t.id)), p.debe, p.monto FROM clientes c INNER JOIN trabajos t ON c.id = t.idCliente RIGHT JOIN pagos p ON t.id = p.idTrabajos WHERE orden=1 AND t.cancelacion=0 AND t.fechaAlta BETWEEN '"+fechaIni+"' AND '"+fechaFin+"' ORDER BY "+ordenar+" ASC";
					String sqlGastos = "SELECT fecha, usuario, monto, descripcion FROM gastos g INNER JOIN usuarios u ON g.idUsuarios = u.id WHERE fecha BETWEEN '"+fechaIni+"' AND '"+fechaFin+"'";
					String sqlObservaciones="SELECT t.orden, t.numero, o.fechaAlta, concat(c.cantidad,'-',c.descripcion), o.descripcion, u.usuario, c.realizado FROM trabajos t INNER JOIN contenido c ON t.id = c.idTrabajos RIGHT JOIN obsrvaciones o ON c.id = o.idContenido INNER JOIN usuarios u ON o.idUsuario = u.id WHERE  o.fechaAlta BETWEEN '"+fechaIni+"' AND '"+fechaFin+"' ORDER BY t.orden ASC, "+ordenar+" ASC";
					String sqlObNo ="SELECT o.fechaAlta, t.numero, concat(c.cantidad,'-',c.descripcion), o.descripcion, u.usuario, c.realizado FROM trabajos t INNER JOIN contenido c ON t.id = c.idTrabajos RIGHT JOIN obsrvaciones o ON c.id = o.idContenido INNER JOIN usuarios u ON o.idUsuario = u.id WHERE t.orden=0 AND o.fechaAlta BETWEEN '"+fechaIni+"' AND '"+fechaFin+"' ORDER BY t.orden ASC, "+ordenar+" ASC";
					String sqlObOr ="SELECT o.fechaAlta, t.numero, concat(c.cantidad,'-',c.descripcion), o.descripcion, u.usuario, c.realizado FROM trabajos t INNER JOIN contenido c ON t.id = c.idTrabajos RIGHT JOIN obsrvaciones o ON c.id = o.idContenido INNER JOIN usuarios u ON o.idUsuario = u.id WHERE t.orden=1 AND o.fechaAlta BETWEEN '"+fechaIni+"' AND '"+fechaFin+"' ORDER BY t.orden ASC, "+ordenar+" ASC";
					String sqlNotasNum ="SELECT t.fechaAlta, t.numero, concat(nombres, ' ', apellidoP, ' ', apellidoM), subtotal, (SELECT monto FROM pagos WHERE id=(SELECT MIN(id) FROM pagos WHERE idTrabajos=t.id)), p.debe, p.monto FROM clientes c INNER JOIN trabajos t ON c.id = t.idCliente RIGHT JOIN pagos p ON t.id = p.idTrabajos WHERE orden=0 AND t.cancelacion=0 AND t.fechaAlta BETWEEN '"+fechaIni+"' AND '"+fechaFin+"' ORDER BY t.numero ASC";
					String sqlOrdenNum ="SELECT t.fechaAlta, t.numero, concat(nombres, ' ', apellidoP, ' ', apellidoM), subtotal, (SELECT monto FROM pagos WHERE id=(SELECT MIN(id) FROM pagos WHERE idTrabajos=t.id)), p.debe, p.monto FROM clientes c INNER JOIN trabajos t ON c.id = t.idCliente RIGHT JOIN pagos p ON t.id = p.idTrabajos WHERE orden=1 AND t.cancelacion=0 AND t.fechaAlta BETWEEN '"+fechaIni+"' AND '"+fechaFin+"' ORDER BY t.numero ASC";
					if(rdBtnNotas.isSelected()){
						tipo="CORTE_HISTO";
						modelo = datos.getModelo(sqlNotas, titulosNO, 7);
						tabla.setModel(modelo);
						vistaNotasOrden();
						notas = datos.getObjeto("SELECT t.fechaAlta, t.numero, concat(nombres, ' ', apellidoP, ' ', apellidoM), subtotal, (SELECT monto FROM pagos WHERE id=(SELECT MIN(id) FROM pagos WHERE idTrabajos=t.id)), p.debe, p.monto, t.observaciones FROM clientes c INNER JOIN trabajos t ON c.id = t.idCliente RIGHT JOIN pagos p ON t.id = p.idTrabajos WHERE orden=0 AND t.cancelacion=0 AND t.fechaAlta BETWEEN '"+fechaIni+"' AND '"+fechaFin+"' ORDER BY "+ordenar+" ASC", tabla.getRowCount(), 8);
						notasNum = datos.getObjeto(sqlNotasNum, tabla.getRowCount(), 7);
						ordenes = vacio;
						ordenesNum=vacio;
						nombre="NOTAS";
					}
					else if(rdBtnOrdenes.isSelected()){
						tipo="CORTE_HISTO";
						modelo = datos.getModelo(sqlOrden, titulosNO, 7);
						tabla.setModel(modelo);
						ordenes = datos.getObjeto("SELECT t.fechaAlta, t.numero, concat(nombres, ' ', apellidoP, ' ', apellidoM), subtotal, (SELECT monto FROM pagos WHERE id=(SELECT MIN(id) FROM pagos WHERE idTrabajos=t.id)), p.debe, p.monto, t.observaciones FROM clientes c INNER JOIN trabajos t ON c.id = t.idCliente RIGHT JOIN pagos p ON t.id = p.idTrabajos WHERE orden=1 AND t.cancelacion=0 AND t.fechaAlta BETWEEN '"+fechaIni+"' AND '"+fechaFin+"' ORDER BY  "+ordenar+" ASC", tabla.getRowCount(), 8);
						ordenesNum = datos.getObjeto(sqlOrdenNum, tabla.getRowCount(), 7);
						vistaNotasOrden();
						notas = vacio;
						notasNum=vacio;
						nombre="ORDENES";
					}
					else if(rdBtnAmbos.isSelected()){
						tipo="CORTE_HISTO";
						int filasNotas=Integer.parseInt(datos.getDato("SELECT COUNT(*) FROM clientes c INNER JOIN trabajos t ON c.id = t.idCliente RIGHT JOIN pagos p ON t.id = p.idTrabajos WHERE orden=0 AND t.cancelacion=0 AND t.fechaAlta BETWEEN '"+fechaIni+"' AND '"+fechaFin+"'"));
						int filasOrden=Integer.parseInt(datos.getDato("SELECT COUNT(*) FROM clientes c INNER JOIN trabajos t ON c.id = t.idCliente RIGHT JOIN pagos p ON t.id = p.idTrabajos WHERE orden=1 AND t.cancelacion=0 AND t.fechaAlta BETWEEN '"+fechaIni+"' AND '"+fechaFin+"'"));
						ordenes = datos.getObjeto("SELECT t.fechaAlta, t.numero, concat(nombres, ' ', apellidoP, ' ', apellidoM), subtotal, (SELECT monto FROM pagos WHERE id=(SELECT MIN(id) FROM pagos WHERE idTrabajos=t.id)), p.debe, p.monto, t.observaciones FROM clientes c INNER JOIN trabajos t ON c.id = t.idCliente RIGHT JOIN pagos p ON t.id = p.idTrabajos WHERE orden=1 AND t.cancelacion=0 AND t.fechaAlta BETWEEN '"+fechaIni+"' AND '"+fechaFin+"' ORDER BY  "+ordenar+" ASC", filasOrden, 8);
						notas = datos.getObjeto("SELECT t.fechaAlta, t.numero, concat(nombres, ' ', apellidoP, ' ', apellidoM), subtotal, (SELECT monto FROM pagos WHERE id=(SELECT MIN(id) FROM pagos WHERE idTrabajos=t.id)), p.debe, p.monto, t.observaciones FROM clientes c INNER JOIN trabajos t ON c.id = t.idCliente RIGHT JOIN pagos p ON t.id = p.idTrabajos WHERE orden=0 AND t.cancelacion=0 AND t.fechaAlta BETWEEN '"+fechaIni+"' AND '"+fechaFin+"'  ORDER BY "+ordenar+" ASC", filasNotas, 8);
						ordenesNum = datos.getObjeto(sqlOrdenNum, filasOrden, 7);
						notasNum = datos.getObjeto(sqlNotasNum, filasNotas, 7);
						modelo = datos.getModeloRealizadas(sqlAmbos, titulosAm, 7);
						tabla.setModel(modelo);
						vistaNotasOrden();
						nombre="AMBOS";
					}
					else if(rdBtnObservaciones.isSelected()){
						tipo="CORTE_OBS";
						ordenes=datos.getObjeto(sqlObOr, Integer.parseInt(datos.getDato("SELECT COUNT(*) FROM trabajos t INNER JOIN contenido c ON t.id = c.idTrabajos RIGHT JOIN obsrvaciones o ON c.id = o.idContenido INNER JOIN usuarios u ON o.idUsuario = u.id WHERE t.orden=1 AND o.fechaAlta BETWEEN '"+fechaIni+"' AND '"+fechaFin+"' ORDER BY t.orden ASC, "+ordenar+" ASC")), 6);
						notas=datos.getObjeto(sqlObNo, Integer.parseInt(datos.getDato("SELECT COUNT(*) FROM trabajos t INNER JOIN contenido c ON t.id = c.idTrabajos RIGHT JOIN obsrvaciones o ON c.id = o.idContenido INNER JOIN usuarios u ON o.idUsuario = u.id WHERE t.orden=0 AND o.fechaAlta BETWEEN '"+fechaIni+"' AND '"+fechaFin+"' ORDER BY t.orden ASC, "+ordenar+" ASC")), 6);
						modelo = datos.getModeloRealizadas(sqlObservaciones, titulosOb, 6);
						tabla.setModel(modelo);
						vistaObservaciones();
					}
					else{
						tipo="CORTE_GAST";
						modelo = datos.getModelo(sqlGastos, titulosGa, 4);
						tabla.setModel(modelo);
						vistaGastos();
					}
					scroll.getViewport().add(tabla);
					btnImprimir.setEnabled(true);
				}
				else{
					JOptionPane.showMessageDialog(this, "Debe elegir una fecha de inicio para buscar.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(this, "Debe elegir una fecha de inicio para buscar.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			}catch(Exception e){
				
			}
		}
		else if(a.getSource()==btnLimpiar){
			limpiar();
		}
		else if(a.getSource()==btnImprimir){
			imprimir(tipo);
		}
	}
	private void limpiar(){
		notas = vacio;
		ordenes = vacio;
		modelo = new DefaultTableModel();
		/*rdBtnNotas.setSelected(false);
		rdBtnNumero.setSelected(false);
		rdBtnOrdenes.setSelected(false);
		rdBtnAmbos.setSelected(false);
		rdBtnObservaciones.setSelected(false);
		rdBtnGastos.setSelected(false);
		rdBtnFecha.setSelected(false);*/
		grupoBotones.clearSelection();
		grpFiltro.clearSelection();
		tabla.setModel(modelo);
		fechaFinal.setDate(fecha);
		fechaInicio.setDate(fecha);
		btnImprimir.setEnabled(false);
		ordenar=numero;
		rdBtnNumero.setEnabled(true);
	}
	private void vistaNotasOrden(){
		tabla.getColumnModel().getColumn(0).setMinWidth(90);
		tabla.getColumnModel().getColumn(0).setMaxWidth(90);
		tabla.getColumnModel().getColumn(0).setCellRenderer(centro);
		tabla.getColumnModel().getColumn(0).setResizable(false);
		tabla.getColumnModel().getColumn(1).setMinWidth(90);
		tabla.getColumnModel().getColumn(1).setMaxWidth(90);
		tabla.getColumnModel().getColumn(1).setCellRenderer(centro);
		tabla.getColumnModel().getColumn(1).setResizable(false);
		tabla.getColumnModel().getColumn(3).setMinWidth(90);
		tabla.getColumnModel().getColumn(3).setMaxWidth(90);
		tabla.getColumnModel().getColumn(3).setCellRenderer(centro);
		tabla.getColumnModel().getColumn(3).setResizable(false);
		tabla.getColumnModel().getColumn(4).setMinWidth(90);
		tabla.getColumnModel().getColumn(4).setMaxWidth(90);
		tabla.getColumnModel().getColumn(4).setCellRenderer(centro);
		tabla.getColumnModel().getColumn(4).setResizable(false);
		tabla.getColumnModel().getColumn(5).setMinWidth(90);
		tabla.getColumnModel().getColumn(5).setMaxWidth(90);
		tabla.getColumnModel().getColumn(5).setCellRenderer(centro);
		tabla.getColumnModel().getColumn(5).setResizable(false);
		tabla.getColumnModel().getColumn(6).setMinWidth(90);
		tabla.getColumnModel().getColumn(6).setMaxWidth(90);
		tabla.getColumnModel().getColumn(6).setCellRenderer(centro);
		tabla.getColumnModel().getColumn(6).setResizable(false);
		tabla.setFont(new Font("Arial", 0, 14));
		JTableHeader th = tabla.getTableHeader();
		th.setFont(new Font("Arial", 0, 14));
		tabla.setTableHeader(th);
	}
	private void vistaGastos(){
		tabla.getColumnModel().getColumn(0).setMinWidth(90);
		tabla.getColumnModel().getColumn(0).setMaxWidth(90);
		tabla.getColumnModel().getColumn(0).setCellRenderer(centro);
		tabla.getColumnModel().getColumn(0).setResizable(false);
		tabla.getColumnModel().getColumn(1).setCellRenderer(centro);
		tabla.getColumnModel().getColumn(1).setResizable(false);
		tabla.getColumnModel().getColumn(1).setMinWidth(90);
		tabla.getColumnModel().getColumn(1).setMaxWidth(90);
		tabla.getColumnModel().getColumn(2).setCellRenderer(centro);
		tabla.getColumnModel().getColumn(2).setResizable(false);
		tabla.getColumnModel().getColumn(2).setMinWidth(90);
		tabla.getColumnModel().getColumn(2).setMaxWidth(90);
		tabla.getColumnModel().getColumn(3).setCellRenderer(centro);
		tabla.getColumnModel().getColumn(3).setResizable(false);
		tabla.setFont(new Font("Arial", 0, 14));
		JTableHeader th = tabla.getTableHeader();
		th.setFont(new Font("Arial", 0, 14));
		tabla.setTableHeader(th);
	}
	private void vistaObservaciones(){
		tabla.getColumnModel().getColumn(0).setMinWidth(120);
		tabla.getColumnModel().getColumn(0).setMaxWidth(120);
		tabla.getColumnModel().getColumn(0).setCellRenderer(centro);
		tabla.getColumnModel().getColumn(0).setResizable(false);
		tabla.getColumnModel().getColumn(1).setMinWidth(90);
		tabla.getColumnModel().getColumn(1).setMaxWidth(90);
		tabla.getColumnModel().getColumn(1).setCellRenderer(centro);
		tabla.getColumnModel().getColumn(1).setResizable(false);
		tabla.getColumnModel().getColumn(4).setMinWidth(90);
		tabla.getColumnModel().getColumn(4).setMaxWidth(90);
		tabla.getColumnModel().getColumn(4).setCellRenderer(centro);
		tabla.getColumnModel().getColumn(4).setResizable(false);
		tabla.setFont(new Font("Arial", 0, 14));
		JTableHeader th = tabla.getTableHeader();
		th.setFont(new Font("Arial", 0, 14));
		tabla.setTableHeader(th);
	}
	private void imprimir(String tipo){
		try{
			int fila=1;
			int columna=0;
			File guardar = new File(System.getProperty("user.home") + "\\Desktop");
			//File src = new File("src");
			File archivo = new File(tipo+".xlsx");
			//OPCPackage pkg = OPCPackage.open(archivo);
			FileInputStream archivoEntrada = new FileInputStream(archivo);
			Workbook libro = WorkbookFactory.create(archivoEntrada);
			org.apache.poi.ss.usermodel.CellStyle derecha = libro.createCellStyle();
			derecha.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
			org.apache.poi.ss.usermodel.CellStyle centro = libro.createCellStyle();
			centro.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			org.apache.poi.ss.usermodel.CellStyle wrap = libro.createCellStyle();
			wrap.setWrapText(true);
			Sheet hoja = libro.getSheetAt(0);
			Row filas = hoja.getRow((short) fila);
			org.apache.poi.ss.usermodel.CellStyle style = libro.createCellStyle();
			if(rdBtnGastos.isSelected()){
				//Aqui comienza a escribir las Gastos-----------------------------------------------------------------------------
				columna=4;
				filas.createCell(columna).setCellValue(f.format(fechaInicio.getDate()));
				filas.getCell(columna).setCellStyle(centro);
				fila++;
				filas=hoja.getRow((short) fila);
				filas.createCell(columna).setCellValue(f.format(fechaFinal.getDate()));
				filas.getCell(columna).setCellStyle(centro);
				columna=0;
				fila=5;
				int x=0, y=0;
				for(y=0;y<tabla.getRowCount();y++){
					filas=hoja.createRow((short) fila);
					for(x=0;x<4;x++){
						//Pedimos los datos desde la tabla ya que no son muchos y se pueden manipular facil---------------------------
						filas.createCell(x).setCellValue((String)tabla.getModel().getValueAt(y, x));					
						if(x==3){
							filas.getCell(x).setCellStyle(centro);
						}
						else if(x==2){
							filas.getCell(x).setCellStyle(derecha);
						}
						//System.out.println(tabla.getModel().getValueAt(y, x));
					}
					fila++;
				}
				//Terminagastos-------------------------------------------------------------------------------------------------------
			}
			else{
				columna=5;
				filas.createCell(columna).setCellValue(f.format(fechaInicio.getDate()));
				if(ordenes.length>0){
					columna+=3;
					try{
							int num = Integer.parseInt(ordenes[0][1].toString());
							filas.createCell(columna).setCellValue(num);
							filas.getCell(columna).setCellStyle(centro);
							columna+=2;
							filas.createCell(columna).setCellValue(ordenes[ordenes.length-1][1].toString());
							filas.getCell(columna).setCellStyle(centro);
						}
						catch(NumberFormatException e){}
				}
				fila++;
				columna=5;
				filas=hoja.getRow((short) fila);
				filas.createCell(columna).setCellValue(f.format(fechaFinal.getDate()));
				columna=8;
				if(notas.length>0){
					try{
						filas = hoja.getRow((short) fila);
						int num = Integer.parseInt(notas[0][1].toString());
						filas.createCell(columna).setCellValue(num);
						filas.getCell(columna).setCellStyle(centro);
						columna+=2;
						filas.createCell(columna).setCellValue(notas[notas.length-1][1].toString());
						filas.getCell(columna).setCellStyle(centro);
					}
					catch(NumberFormatException e){}
				}
				if(rdBtnObservaciones.isSelected()){
					//Escribe el archivo de las observaciones--------------------------------------------------------------------------
					int count=0, count1=0, row=0;
					fila=5;
					columna=0;
					CellRangeAddress rango;
					CellRangeAddress rango2;
					while(row<ordenes.length){
						count1=0;
						filas=hoja.createRow((short) fila);
						while(count1<6){
							if(columna==1){
								columna++;
								filas.createCell(columna).setCellStyle(centro);
								filas.getCell(columna).setCellValue(ordenes[count][count1].toString());
							}
							else if(columna==3 ||columna==5){
								filas.createCell(columna).setCellValue(ordenes[count][count1].toString());
								filas.getCell(columna).setCellStyle(wrap);
								rango = new CellRangeAddress(fila,fila,columna,columna+1);
								hoja.addMergedRegion(rango);
								filas.setHeight((short) 600);
								columna++;
							}
							else{
								filas.createCell(columna);
								filas.getCell(columna).setCellValue(ordenes[count][count1].toString());
							}
							columna++;
							count1++;
						}
						row++;
						fila++;
						columna=0;
						count++;
					}
					columna=0;
					row=0;
					count=0;
					while(row<notas.length){
						filas=hoja.createRow((short) fila);
						count1=0;
						while(count1<6){
							if(columna==1){
								filas.createCell(columna).setCellStyle(centro);
								filas.getCell(columna).setCellValue(notas[count][count1].toString());
								columna++;
							}
							else if(columna==3 || columna==5){
								filas.createCell(columna).setCellStyle(wrap);
								rango2 = new CellRangeAddress(fila,fila,columna,columna+1);
								hoja.addMergedRegion(rango2);
								filas.getCell(columna).setCellValue(notas[count][count1].toString());
								filas.setHeight((short) 600);
								columna++;
							}
							else{
								filas.createCell(columna);
								filas.getCell(columna).setCellValue(notas[count][count1].toString());
							}
							columna++;
							count1++;
						}
						row++;
						count++;
						columna=0;
						fila++;
					}
					//Termina el archivo de observaciones------------------------------------------------------------------------------
				}
				else{
					//Escribe el archivo de historiales--------------------------------------------------------------------------------
					int count=0, count1=0, row=0;
					fila=5;
					columna=0;
					while(row<ordenes.length){
						count1=0;
						filas=hoja.createRow((short) fila);
						while(count1<8){
							filas.createCell(columna);
							if(columna==1){
								columna++;
								filas.createCell(columna).setCellStyle(centro);
							}
							if(columna==3){
								columna++;
								filas.createCell(columna);
							}
							if(columna==8){
								filas.getCell(columna).setCellStyle(derecha);
								if(ordenes[count][4].toString().equals(ordenes[count][3].toString())){
									columna++;
								}
								else{
									filas.getCell(columna).setCellValue(ordenes[count][count1].toString());
									columna++;
								}
							}
							filas.createCell(columna).setCellValue(ordenes[count][count1].toString());
							if(columna>=5 && columna<10){
								filas.getCell(columna).setCellStyle(derecha);
							}
							columna++;
							count1++;
						}
						row++;
						fila++;
						columna=0;
						count++;
					}
					columna=0;
					row=0;
					count=0;
					while(row<notas.length){
						filas=hoja.createRow((short) fila);
						count1=0;
						while(count1<8){
							filas.createCell(columna);
							if(columna==1){
								filas.getCell(columna).setCellStyle(centro);
							}
							if(columna==2){	
								columna+=2;
							}
							if(columna==8){
								filas.getCell(columna).setCellStyle(derecha);
								if(notas[count][4].toString().equals(notas[count][3].toString())){
									columna++;
								}
								else{
									filas.getCell(columna).setCellValue(notas[count][count1].toString());
									columna++;
								}
							}
							filas.createCell(columna).setCellValue(notas[count][count1].toString());
							if(columna>=5 && columna<10){
								filas.getCell(columna).setCellStyle(derecha);
							}
							columna++;
							count1++;
						}
						row++;
						count++;
						columna=0;
						fila++;
					}
				}
			}
			//Guarda el archivo---------------------------------------------------------------------------------------------------------
			FileOutputStream salida = new FileOutputStream(new File(guardar+"\\"+tipo+"-"+nombre+"-"+f.format(fecha)+".xlsx"));
			libro.write(salida);
			salida.close();		
			JOptionPane.showMessageDialog(null, "Listo", "El proceso ha terminado", JOptionPane.INFORMATION_MESSAGE);
			salir();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void salir(){
		this.dispose();
	}
	//Talvez no lo necesites-----------------------------------------------------------------------------------------------
	/*private String fecha(Date fech){
		String fecha="";
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fech);
		SimpleDateFormat fAno = new SimpleDateFormat("yy");
		int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
		int mes = calendario.get(Calendar.MONTH);
		int dia = calendario.get(Calendar.DAY_OF_MONTH);
		String year = fAno.format(calendario.getTime());
		switch(diaSemana){
		case 0:
			fecha ="DOM "+dia;
			break;
		case 1:
			fecha ="LUN "+dia;
			break;
		case 2:
			fecha ="MAR "+dia;
			break;
		case 3:
			fecha ="MIE "+dia;
			break;
		case 4:
			fecha ="JUE "+dia;
			break;
		case 5:
			fecha ="VIE "+dia;
			break;
		case 6:
			fecha ="SAB "+dia;
			break;
		}
		switch(mes){
		case Calendar.JANUARY:
			fecha+="-ENE-";
			break;
		case 1:
			fecha+="-FEB-";
			break;
		case 2:
			fecha+="-MAR-";
			break;
		case 3:
			fecha+="-ABR-";
			break;
		case 4:
			fecha+="-MAY-";
			break;
		case 5:
			fecha+="-JUN-";
			break;
		case 6:
			fecha+="-JUL-";
			break;
		case 7:
			fecha+="-AGO-";
			break;
		case 8:
			fecha+="-SEP-";
			break;
		case 9:
			fecha+="-OCT-";
			break;
		case 10:
			fecha+="-NOV-";
			break;
		case 11:
			fecha+="-DIC-";
			break;
		}
		fecha+=year;
		
		return fecha;
	}*/

}
