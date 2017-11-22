package sistemaOlvera;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class EmpleadoInicio extends JFrame implements ActionListener {
	private Conexion datos;
	private Object[][] idTrabajos;
	private int filaEntrega=-1;
	private int countLlamar=0;
	private int filaHistorail=-1;
	private String nombrePaquete="";
	private String item;
	private String fMostrar;
	private boolean modificaFecha = false;
	private Calendar calendario;
	private int idCliente=0;
	private int idTrabajo=0;
	private boolean escuela = false;
	private int id;
	private String fecha;
	private String[] titulosContenidoAlta = {"Cant.", "Descripción", "P.Unit.", "Total"};
	private String[] titulosEntregar = {"Cant.", "Descripción", "Almacen", "Entregado"};
	private String[] titulosTrabajos = {"Número", "Descripción", "Estatus"};
	private String[] itemsCantidad = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	private String nombre;
	private String paterno;
	private String materno;
	private String telefono;
	private String direccion;
	private String instituto;
	private String rfc;
	private String correo;
	private String folio;
	private String carrera;
	private String plantel;
	private String grupo;
	private String turno;
	private String calendar;
	private ArrayList<String> codigos = new ArrayList<String>();
	private boolean mostrado=false;
	private ArrayList<String> datosTextos;
	private String expTelefono = "\\d{8,13}";
	private float totalPagar = 0;
	private float total =0;
	private float pago=0;
	private int filaSeleccionada = 0;
	private float restar = 0;
	//Datos JButtons---------------------------------------------------------------------
	private JButton btnBuscar = new JButton("Buscar");
	private JButton btnRegistrar = new JButton("Registrar");
	private JButton btnModificar = new JButton("Modificar");
	private JButton btnLimpiar = new JButton("Limpiar");
	//Alta JButton-----------------------------------------------------------------------
	private JButton btnAgregarProductos = new JButton("Agregar");
	private JButton btnVerProductos = new JButton("Ver");
	private JButton btnAgregarPaquete = new JButton("Agregar");
	private JButton btnVerPaquete = new JButton("Ver");
	private JButton btnQuitar = new JButton ("Quitar");
	private JButton btnValidar = new JButton("Validar");
	private JButton btnCobrar = new JButton("Cobrar");
	private JButton btnSaldo = new JButton("Saldos");
	//Entregar JButton------------------------------------------------------------------
	private JButton btnAbonar = new JButton("Abonar");
	private JButton btnEntregar = new JButton("Entregar");
	//Hstorial del cliente--------------------------------------------------------------
	private JButton btnCancelar = new JButton("Cancelar");
	private JRadioButton rdBtnFactura = new JRadioButton("Factura.");
	private JRadioButton rdBtnDevolucion = new JRadioButton("Devolución");
	private JRadioButton rdBtnModificacion = new JRadioButton("Modificación");
	private ButtonGroup grpCancelacion = new ButtonGroup();
	//Datos JTextField------------------------------------------------------------------
	private JTextField txtNombre = new JTextField();
	private JTextField txtApellidosP = new JTextField();
	private JTextField txtApellidosM = new JTextField();
	private JTextField txtDireccion = new JTextField();
	private JTextField txtTelefono = new JTextField();
	private JTextField txtCorreo = new JTextField();
	private JTextField txtRFC = new JTextField();
	private JTextField txtInstituto = new JTextField();
	private JTextField txtFolio = new JTextField();
	private JTextField txtCelular = new JTextField();
	private JTextField txtCarrera = new JTextField();
	private JTextField txtPlantel = new JTextField();
	private JTextField txtGrupo = new JTextField();
	private JTextField txtTurno = new JTextField();
	private JTextField txtCalendario = new JTextField();
	private JTextField txtObservaciones = new JTextField();
	//Alta JTextFields---------------------------------------------------------------
	private JTextField txtDescuento = new JTextField();
	private JTextField txtPago = new JTextField();
	private JLabel txtNumero = new JLabel();
	private JTextField txtRegistroProducto = new JTextField();
	private JTextField txtRegistroPaquetes = new JTextField();
	//Entregar JTextField-------------------------------------------------------------
	private JTextField txtAbono = new JTextField();
	//Datos JLabels-------------------------------------------------------------------
	private JLabel lblNombre = new JLabel("Nombre:");
	private JLabel lblApellidosP = new JLabel("Apellido Paterno:");
	private JLabel lblApellidosM = new JLabel("Apellido Materno:");
	private JLabel lblDireccion = new JLabel("Direccion: ");
	private JLabel lblTelefono = new JLabel("Teléfono: ");
	private JLabel lblCorreo = new JLabel("Correo: ");
	private JLabel lblRFC = new JLabel("RFC: ");
	private JLabel lblInstituto = new JLabel("Escuela: ");
	private JLabel lblFolio = new JLabel("Folio: ");
	private JLabel lblCelular = new JLabel("Celular: ");
	private JLabel lblCarrera = new JLabel("Carrera: ");
	private JLabel lblPlantel = new JLabel("Plantel: ");
	private JLabel lblGrupo = new JLabel("Grupo: ");
	private JLabel lblTurno = new JLabel("Turno: ");
	private JLabel lblCalendario = new JLabel("Calendario: ");
	private JLabel lblObservaciones = new JLabel("Observaciones");
	//Alta JLabel-----------------------------------------------------------------------
	private JLabel lblProductos = new JLabel("Paquetes: ");
	private JLabel lblCantidadProductos = new JLabel ("Cantidad:");
	private JLabel lblPaquetes = new JLabel("Escuelas:");
	private JLabel lblCantidadPaquetes = new JLabel("Cantidad:");
	private JLabel lblDescuento = new JLabel("Descuento: ");
	private JLabel lblPago = new JLabel("Anticipo:");
	private JLabel lblTotal = new JLabel("Total: $");
	private JLabel lblPesos = new JLabel("0.00", SwingConstants.RIGHT);
	private JLabel lblNumero = new JLabel("Nº: ");
	private JLabel lblRegistroProductos = new JLabel("Registro:");
	private JLabel lblRegistroPaquetes = new JLabel("Registro:");
	//Entregar JLabels-------------------------------------------------------------------
	private JLabel lblTotalEntrega = new JLabel("Total: $");
	private JLabel lblAbonadoEntrega = new JLabel("Debe: $");
	private JLabel lblPesosEntrega = new JLabel("0.00", SwingConstants.RIGHT);
	private JLabel lblPesosAbonadoEntrega = new JLabel("0.00", SwingConstants.RIGHT);
	private JLabel lblAbonaEntrega = new JLabel("Abona: ");
	private JLabel lblTrabjos = new JLabel("Trabajo: ");
	//Alta JTable-------------------------------------------------------------------------
	private DefaultTableModel modeloAlta = new DefaultTableModel(titulosContenidoAlta,0);
	private JTable tablaContenidoAlta = new JTable(modeloAlta);
	//Trabajos JTable----------------------------------------------------------------------
	private DefaultTableModel modelTrabajos = new DefaultTableModel(titulosTrabajos, 0);
	private JTable tablaTrabajos = new JTable(modelTrabajos);
	//Entregar JTable------------------------------------------------------------------------
	private DefaultTableModel modelEntrega = new DefaultTableModel(titulosEntregar,0);
	private JTable tablaEntrega = new JTable(modelEntrega);
	//Trabajos TextArea----------------------------------------------------------------------
		private JTextArea informacion = new JTextArea();
	//Alta Scroll----------------------------------------------------------------------------
	private JScrollPane scrollAlta = new JScrollPane(tablaContenidoAlta);
	//Entregar Scroll------------------------------------------------------------------------
	private JScrollPane scrollEntrega = new JScrollPane(tablaEntrega);
	//Trabajos Scroll-----------------------------------------------------------------------
	private JScrollPane scrollTrabajos = new JScrollPane(tablaTrabajos);
	private JScrollPane scrollInformacion = new JScrollPane(informacion);
	//Datos Panel----------------------------------------------------------------------------
	private JPanel panelDatos = new JPanel();
	private JPanel panelDatosText = new JPanel();
	private JPanel panelBotonesDatos = new JPanel();
	//Alta Panel-----------------------------------------------------------------------------
	private JPanel panelContenidoAlta = new JPanel();
	private JPanel panelAlta = new JPanel();
	//Entregas Panel-------------------------------------------------------------------------
	private JPanel panelContenidoEntrega = new JPanel();
	private JPanel panelEntrega = new JPanel();
	//Trabajos panel-------------------------------------------------------------------------
	private JPanel panelInformacionTrabajos = new JPanel();
	private JPanel panelTrabajos = new JPanel();
	//Numero de nota u orden panel-----------------------------------------------------------
	private JPanel panelNumero = new JPanel();
	//Alta JRadioButtons---------------------------------------------------------------------
	private JRadioButton rdBtnPorcentaje = new JRadioButton("%");
	private JRadioButton rdBtnPesos = new JRadioButton("$");
	private ButtonGroup grupoDescuento = new ButtonGroup();
	private JRadioButton rdBtnNota = new JRadioButton("Nota");
	private JRadioButton rdBtnOrden = new JRadioButton("Orden");
	private ButtonGroup grupoTipo = new ButtonGroup();
	
	private JTabbedPane tabs = new JTabbedPane();
	//Alta JComboBox--------------------------------------------------------------------------
	private JComboBox<Object> cbProductos = new JComboBox<Object>();
	private JComboBox<Object> cbPaquetes = new JComboBox<Object>();
	private JComboBox<Object> cantidadProductos = new JComboBox<Object>(itemsCantidad);
	private JComboBox<Object> cantidadPaquetes = new JComboBox<Object>(itemsCantidad);
	//Entregar JComboBox----------------------------------------------------------------------
	private JComboBox<Object> cbTrabajos = new JComboBox<Object>();
	//Auxiliares------------------------------------------------------------------------------
	//private JTable jAux;
	private JTableHeader thAux;
	private DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
	//Menu Bar---------------------------------------------------------------------------------
	private JMenuBar menu = new JMenuBar();
	private JMenu trabajos = new JMenu("Trabajos");
	private JMenu gastos = new JMenu("Gastos");
	private JMenu corte = new JMenu("Corte");
	private JMenu llamadas = new JMenu("Llamar");
	private JMenu ingresos = new JMenu("Ingresos");
	//--------------------------------------------------------------------------------------------
	private DecimalFormat formatoDecimal = new DecimalFormat("0.00");
	private SimpleDateFormat fechaDia = new SimpleDateFormat("dd   MMMM   yyyy");
	//--------------------------------------------------------------------------------------------
	private static String saldo="SALDO DE FOTOGRAFIA EGRESO.";
	
	public EmpleadoInicio(Conexion x, Calendar calendar, String fecha, int id){
		super("Sistema Olvera - Empleados");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		datos = x;
		calendario = calendar;
		this.fecha = fecha;
		fMostrar=String.valueOf(fechaDia.format(calendario.getTime()));
		this.id = id;
		centro.setHorizontalAlignment(SwingConstants.CENTER);
		//Menu Bar----------------------------------------------------------------------------
		trabajos.addMouseListener(new MouseAdapter()
				{
					public void mousePressed(MouseEvent e){
						Trabajos window = new Trabajos(fecha, datos, id);
						window.setVisible(true);
					}
				}
				);
		trabajos.setFont(new Font("Arial", 0, 12));
		gastos.setFont(new Font("Arial", 0, 12));
		gastos.addMouseListener(new MouseAdapter()
				{
					public void mousePressed(MouseEvent e){
						Gastos window = new Gastos(id, datos, fecha);
						window.setVisible(true);
					}
				}
				);
		corte.addMouseListener(new MouseAdapter(){
					public void mousePressed(MouseEvent e){
						Corte window = new Corte(id, fecha, datos);
						window.setVisible(true);
					}
				}
				);
		corte.setFont(new Font("Arial", 0, 12));
		llamadas.setFont(new Font("Arial", 0, 12));
		llamadas.addMouseListener(new MouseAdapter(){
					public void mousePressed(MouseEvent e){
						Llamadas window = new Llamadas(datos, fecha, id, "SELECT orden, numero, concat(apellidoP,'"+" ',apellidoM,'"+" ',nombres),  telefono, t.fechaAlta, datediff('"+fecha+"', t.fechaAlta), estatus FROM trabajos t RIGHT JOIN clientes c ON t.idCliente = c.id WHERE datediff('"+fecha+"', t.fechaAlta)>15 AND datediff('"+fecha+"', t.fechaAlta)<=30 AND t.estatus!=3 AND t.cancelacion=0", countLlamar);
						window.setVisible(true);
					}
				}
				);
		ingresos.setFont(new Font("Arial", 0, 12));
		ingresos.addMouseListener(new MouseAdapter(){
					public void mousePressed(MouseEvent e){
						Ingresos window = new Ingresos(fecha, id, datos);
						window.setVisible(true);
					}
				}
				);
		menu.add(trabajos);
		menu.add(gastos);
		menu.add(corte);
		menu.add(llamadas);
		menu.add(ingresos);
		//Datos-------------------------------------------------------------------------------
		lblNombre.setFont(new Font("Arial", 0, 14));
		lblApellidosP.setFont(new Font("Arial", 0, 14));
		lblApellidosM.setFont(new Font("Arial", 0, 14));
		lblDireccion.setFont(new Font("Arial", 0, 14));
		lblTelefono.setFont(new Font("Arial", 0, 14));
		lblCorreo.setFont(new Font("Arial", 0, 14));
		lblRFC.setFont(new Font("Arial", 0, 14));
		lblInstituto.setFont(new Font("Arial", 0, 14));
		lblFolio.setFont(new Font("Arial", 0, 14));
		lblCelular.setFont(new Font("Arial", 0, 14));
		lblCarrera.setFont(new Font("Arial", 0, 14));
		lblPlantel.setFont(new Font("Arial", 0, 14));
		lblGrupo.setFont(new Font("Arial", 0, 14));
		lblTurno.setFont(new Font("Arial", 0, 14));
		lblCalendario.setFont(new Font("Arial", 0, 14));
		lblObservaciones.setFont(new Font("Arial", 0, 14));
		txtNombre.setFont(new Font("Arial", 1, 14));
		txtApellidosP.setFont(new Font("Arial", 1, 14));
		txtApellidosM.setFont(new Font("Arial", 1, 14));
		txtDireccion.setFont(new Font("Arial", 1, 14));
		txtTelefono.setFont(new Font("Arial", 1, 14));
		txtCelular.setFont(new Font("Arial", 0, 14));
		txtCorreo.setFont(new Font("Arial", 0, 14));
		txtRFC.setFont(new Font("Arial", 0, 14));
		txtInstituto.setFont(new Font("Arial", 0, 14));
		txtCarrera.setFont(new Font("Arial", 0, 14));
		txtPlantel.setFont(new Font("Arial", 0, 14));
		txtGrupo.setFont(new Font("Arial", 0, 14));
		txtTurno.setFont(new Font("Arial", 0, 14));
		txtFolio.setFont(new Font("Arial", 0, 14));
		txtCalendario.setFont(new Font("Arial", 0, 14));
		txtObservaciones.setFont(new Font("Arial", 1, 14));
		btnBuscar.setFont(new Font("Arial", 0, 14));
		btnBuscar.addActionListener(this);
		btnRegistrar.setFont(new Font("Arial", 0, 14));
		btnRegistrar.addActionListener(this);
		btnModificar.setFont(new Font("Arial", 0, 14));
		btnModificar.addActionListener(this);
		btnModificar.setEnabled(false);
		//btnEliminar.setFont(new Font("Arial", 0, 14));
		//btnEliminar.addActionListener(this);
		btnLimpiar.setFont(new Font("Arial", 0, 14));
		btnLimpiar.addActionListener(this);
		panelDatos.setBorder(new TitledBorder(null, "Datos", 0, 0, new Font("Arial", 0, 17)));
		//Alta---------------------------------------------------------------------------------
		lblNumero.setFont(new Font("Arial", 0, 17));
		lblProductos.setFont(new Font("Arial", 0, 20));
		lblPaquetes.setFont(new Font("Arial", 0, 20));
		lblRegistroPaquetes.setFont(new Font("Arial", 0, 17));
		lblRegistroProductos.setFont(new Font("Arial", 0, 17));
		lblCantidadProductos.setFont(new Font("Arial", 0, 17));
		lblCantidadPaquetes.setFont(new Font("Arial", 0, 17));
		lblDescuento.setFont(new Font("Arial", 0, 17));
		lblPago.setFont(new Font("Arial", 0, 17));
		lblTotal.setFont(new Font("Arial", 0, 30));
		lblPesos.setFont(new Font("Arial", 0, 30));
		btnAgregarProductos.setFont(new Font("Arial", 0, 17));
		btnAgregarProductos.addActionListener(this);
		btnAgregarProductos.setEnabled(false);
		btnVerProductos.setFont(new Font("Arial", 0, 17));
		btnVerProductos.addActionListener(this);
		btnAgregarPaquete.setFont(new Font("Arial", 0, 17));
		btnAgregarPaquete.addActionListener(this);
		btnAgregarPaquete.setEnabled(false);
		btnVerPaquete.setFont(new Font("Arial", 0, 17));
		btnVerPaquete.addActionListener(this);
		btnQuitar.setFont(new Font("Arial", 0, 17));
		btnQuitar.addActionListener(this);
		btnQuitar.setEnabled(false);
		btnValidar.setFont(new Font("Arial", 0, 17));
		btnValidar.addActionListener(this);
		btnSaldo.setFont(new Font("Arial", 0, 17));
		btnSaldo.addActionListener(this);
		btnSaldo.setEnabled(false);
		btnValidar.setEnabled(false);
		btnCobrar.setFont(new Font("Arial", 0, 25));
		btnCobrar.addActionListener(this);
		btnCobrar.setEnabled(false);
		rdBtnPorcentaje.setFont(new Font("Arial", 0, 18));
		rdBtnPesos.setFont(new Font("Arial", 0, 18));
		grupoDescuento.add(rdBtnPorcentaje);
		grupoDescuento.add(rdBtnPesos);
		rdBtnNota.setFont(new Font("Arial", 0, 16));
		rdBtnNota.setSelected(true);
		rdBtnNota.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent a){
							txtNumero.setText(String.valueOf(Integer.parseInt(datos.getDato("SELECT numero FROM trabajos WHERE id = (SELECT MAX(id) FROM trabajos WHERE orden=0 AND anterior=0)"))+1));
						}
					}
				);
		txtNumero.setText(String.valueOf(Integer.parseInt(datos.getDato("SELECT numero FROM trabajos WHERE id = (SELECT MAX(id) FROM trabajos WHERE orden=0 AND anterior=0)"))+1));
		rdBtnOrden.setFont(new Font("Arial", 0, 16));
		rdBtnOrden.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent a){
							txtNumero.setText(String.valueOf(Integer.parseInt(datos.getDato("SELECT numero FROM trabajos WHERE id = (SELECT MAX(id) FROM trabajos WHERE orden=1 AND anterior=0)"))+1));
						}
					}
				);
		grupoTipo.add(rdBtnNota);
		grupoTipo.add(rdBtnOrden);
		cbProductos.setFont(new Font("Arial", 0, 16));
		cbProductos.setBackground(Color.white);
		cbPaquetes.setFont(new Font("Arial", 0, 16));
		txtRegistroPaquetes.setFont(new Font("Arial", 0, 16));
		txtRegistroProducto.setFont(new Font("Arial", 0, 16));
		cbPaquetes.setBackground(Color.white);
		cantidadProductos.setFont(new Font("Arial", 0, 16));
		cantidadProductos.setBackground(Color.white);
		cantidadPaquetes.setFont(new Font("Arial", 0, 16));
		cantidadPaquetes.setBackground(Color.white);
		txtDescuento.setFont(new Font("Arial", 0, 17));
		txtPago.setFont(new Font("Arial", 0, 17));
		txtPago.getDocument().addDocumentListener(new DocumentListener()
					{
						public void changedUpdate(DocumentEvent e){
							
						}
						public void insertUpdate(DocumentEvent e){
							try{
								if(Integer.parseInt(txtPago.getText())>=0){
									btnCobrar.setEnabled(true);
								}
							}
							catch(NumberFormatException n){
								JOptionPane.showMessageDialog(EmpleadoInicio.this, "Debe ingresar solamente números en este campo", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}
						public void removeUpdate(DocumentEvent e){
							if(txtPago.getText().equals("")){
								btnCobrar.setEnabled(false);
							}
							else{
								try{
									if(Integer.parseInt(txtPago.getText())>=0){
										btnCobrar.setEnabled(true);
									}
								}
								catch(NumberFormatException n){
									JOptionPane.showMessageDialog(EmpleadoInicio.this, "Debe ingresar solamente números en este campo", "ERROR", JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					}
				);
		txtNumero.setFont(new Font("Arial", 0, 17));
		tablaContenidoAlta.addMouseListener(
					new MouseAdapter(){
						public void mousePressed(MouseEvent e){
							JTable aux = (JTable)e.getSource();
							Point p = e.getPoint();
							filaSeleccionada = aux.rowAtPoint(p);
							restar = Float.parseFloat((String)(tablaContenidoAlta.getValueAt(filaSeleccionada, 3)));
						}
					}
				);
		vistaTablaAlta();
		llenarComBoxProductos();
		iniciarComboBoxPaquetes();
		panelContenidoAlta.setBorder(new TitledBorder(null, "Contenido", 0,0,new Font("Arial", 0, 16)));
		panelNumero.setBorder(new TitledBorder(null, "Número", 0,0,new Font("Arial", 0, 14)));
		//Entregar-------------------------------------------------------------------------------------------
		txtAbono.setFont(new Font("Arial", 0, 20));
		lblTotalEntrega.setFont(new Font("Arial", 0, 20));
		lblAbonadoEntrega.setFont(new Font("Arial", 0, 20));
		lblPesosEntrega.setFont(new Font("Arial", 0, 20));
		lblPesosAbonadoEntrega.setFont(new Font("Arial", 0, 20));
		lblAbonaEntrega.setFont(new Font("Arial", 0, 20));
		btnAbonar.setFont(new Font("Arial", 0, 25));
		btnAbonar.addActionListener(this);
		btnEntregar.setFont(new Font("Arial", 0, 25));
		btnEntregar.addActionListener(this);
		btnEntregar.setEnabled(false);
		panelContenidoEntrega.setBorder(new TitledBorder(null, "Contenido", 0,0,new Font("Arial", 0, 16)));
		this.lblTrabjos.setFont(new Font("Arial", 0, 18));
		cbTrabajos.setFont(new Font("Arial", 0, 16));
		cbTrabajos.setBackground(Color.WHITE);
		cbTrabajos.addActionListener( new ActionListener(){
						public void actionPerformed(ActionEvent e){
							item = (String)cbTrabajos.getItemAt(cbTrabajos.getSelectedIndex());
							int inde = cbTrabajos.getSelectedIndex();
							if(inde!=-1){
								int letras = item.length();
								String numero="", tipo="";
								for(int x =0; x<letras;x++){
									char car = item.charAt(x);
									if(numero(car)){
										numero+=car;
									}
									else{
										tipo+=car;
									}
								}
								if(tipo.toLowerCase().equals("nota - ")){
									for(int x =0; x<idTrabajos.length;x++){
										if(Integer.parseInt(idTrabajos[x][2].toString())==0){
											if(Integer.parseInt(idTrabajos[x][1].toString())==Integer.parseInt(numero)){
												llenarTablaEntregar(Integer.parseInt(idTrabajos[x][0].toString()));
												observaciones(Integer.parseInt(datos.getDato("SELECT id FROM trabajos WHERE orden=0 AND numero="+numero)));
												idTrabajo=Integer.parseInt(datos.getDato("SELECT id FROM trabajos WHERE orden=0 AND numero="+numero));
												break;
											}
										}
									}
								}
								else{
									for(int x =0; x<idTrabajos.length;x++){
										if(Integer.parseInt(idTrabajos[x][2].toString())==1){
											if(Integer.parseInt(idTrabajos[x][1].toString())==Integer.parseInt(numero)){
												llenarTablaEntregar(Integer.parseInt(idTrabajos[x][0].toString()));
												observaciones(Integer.parseInt(datos.getDato("SELECT id FROM trabajos WHERE orden=1 AND numero="+numero)));
												idTrabajo=Integer.parseInt(datos.getDato("SELECT id FROM trabajos WHERE orden=1 AND numero="+numero));
												break;
											}
										}
									}
								}
								cbTrabajos.setSelectedItem(item);
							}
						}
					}
				);
		
		this.tablaEntrega.addMouseListener(
					new MouseAdapter(){
						public void mousePressed(MouseEvent m){
							//JTable aux = (JTable)m.getSource();
							//Point p = m.getPoint();
							if(m.getClickCount()==1){
								filaEntrega=tablaEntrega.getSelectedRow();
								if(!(tablaEntrega.getValueAt(filaEntrega, 2).toString().equals(""))){
									btnEntregar.setEnabled(true);
								}
								else{
									btnEntregar.setEnabled(false);
								}
							}
						}
					}
				);
		vistaTablaEntrega();
		int count = Integer.parseInt(datos.getDato("SELECT COUNT(*) FROM caja WHERE fecha='"+fecha+"'"));
		if(count==0){
			int caja=0;
			do{
				caja = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el monto monetario de caja."));
				datos.Modificaciones("INSERT INTO caja(monto, fecha, idUsuario) VALUES("+caja+", '"+this.fecha+"', "+this.id+")");
			}while(caja==0);
		}
		//Trabajos---------------------------------------------------------------------------------------------
		informacion.setFont(new Font("Arial", 0, 16));
		informacion.setWrapStyleWord(true);
		btnCancelar.setFont(new Font("Arial", 0, 20));
		rdBtnFactura.setFont(new Font("Arial", 0, 18));
		rdBtnModificacion.setFont(new Font("Arial", 0, 18));
		rdBtnDevolucion.setFont(new Font("Arial", 0, 18));
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(this);
		grpCancelacion.add(rdBtnFactura);
		grpCancelacion.add(rdBtnModificacion);
		grpCancelacion.add(rdBtnDevolucion);
		tablaTrabajos.addMouseListener(new MouseAdapter(){
						public void mousePressed(MouseEvent m){
							//JTable aux = (JTable)m.getSource();
							//Point p = m.getPoint();
							if(m.getClickCount()==1){
								filaHistorail=tablaTrabajos.getSelectedRow();
								btnCancelar.setEnabled(true);
								Info mensaje = new Info("Información del trabajo");
								String datos = (String)tablaTrabajos.getValueAt(tablaTrabajos.getSelectedRow(), 0);
								datos+="\n\nDescripción:\n"+tablaTrabajos.getValueAt(tablaTrabajos.getSelectedRow(), 1);
								datos+="\nEstatus: "+tablaTrabajos.getValueAt(tablaTrabajos.getSelectedRow(), 2);
								mensaje.setMensaje(datos);
								mensaje.showDialog();
							}
						}
					}
				);
		panelInformacionTrabajos.setBorder(new TitledBorder(null, "Información", 0,0,new Font("Arial", 0, 16)));
		vistaTablaTrabajos();
		//Numeros----------------------------------------------------------------------------------------------
		GroupLayout layoutNumeros = new GroupLayout(panelNumero);
		layoutNumeros.setAutoCreateContainerGaps(true);
		layoutNumeros.setAutoCreateGaps(true);
		layoutNumeros.setHorizontalGroup(
					layoutNumeros.createParallelGroup()
					.addGroup(
								layoutNumeros.createSequentialGroup()
								.addComponent(lblNumero)
								.addComponent(txtNumero)
							)
					.addGroup(
								layoutNumeros.createSequentialGroup()
								.addComponent(rdBtnNota)
								.addComponent(rdBtnOrden)
							)
				);
		layoutNumeros.setVerticalGroup(
					layoutNumeros.createSequentialGroup()
					.addGroup(
								layoutNumeros.createParallelGroup()
								.addComponent(lblNumero)
								.addComponent(txtNumero)
							)
					.addGroup(
								layoutNumeros.createParallelGroup()
								.addComponent(rdBtnNota)
								.addComponent(rdBtnOrden)
							)
				);
		//Datos-------------------------------------------------------------------------------------------------
		GroupLayout layoutDatos = new GroupLayout(panelDatosText);
		layoutDatos.setAutoCreateContainerGaps(true);
		layoutDatos.setAutoCreateGaps(true);
		layoutDatos.setHorizontalGroup(
					layoutDatos.createParallelGroup()
					.addGroup(
								layoutDatos.createSequentialGroup()
								.addComponent(lblApellidosP)
								.addComponent(txtApellidosP, 200,200,200)
								.addComponent(lblApellidosM)
								.addComponent(txtApellidosM, 200,200,200)
								.addComponent(lblNombre)
								.addComponent(txtNombre, 200,200,200)
							)
					.addGroup(
								layoutDatos.createSequentialGroup()
								.addComponent(lblDireccion)
								.addComponent(txtDireccion)
							)
					.addGroup(
								layoutDatos.createSequentialGroup()
								.addComponent(lblTelefono)
								.addComponent(txtTelefono, 115, 115, 115)
								.addComponent(lblCorreo)
								.addComponent(txtCorreo,200,200,200)
								.addComponent(lblRFC)
								.addComponent(txtRFC,170,170,170)
								.addComponent(lblFolio)
								.addComponent(txtFolio, 130, 130, 130)
							)
					.addGroup(
								layoutDatos.createSequentialGroup()
								.addComponent(lblInstituto)
								.addComponent(txtInstituto, 180, 180, 180)
								.addComponent(lblPlantel)
								.addComponent(txtPlantel, 150, 150, 150)
								.addComponent(lblGrupo)
								.addComponent(txtGrupo, 40, 40, 40)
								.addComponent(lblTurno)
								.addComponent(txtTurno, 90, 90, 90)
								.addComponent(lblCalendario)
								.addComponent(txtCalendario, 120, 120, 120)
							)
					.addGroup(
								layoutDatos.createSequentialGroup()
								.addComponent(lblCarrera)
								.addComponent(txtCarrera, 200, 200, 200)
								.addComponent(lblObservaciones)
								.addComponent(txtObservaciones, 250, 250, 250)
							)
				);
		layoutDatos.setVerticalGroup(
					layoutDatos.createSequentialGroup()
					.addGroup(
								layoutDatos.createParallelGroup()
								.addComponent(lblApellidosP)
								.addComponent(txtApellidosP,22,22,22)
								.addComponent(lblApellidosM)
								.addComponent(txtApellidosM,22,22,22)
								.addComponent(lblNombre)
								.addComponent(txtNombre,22,22,22)
							)
					.addGroup(
								layoutDatos.createParallelGroup()
								.addComponent(lblDireccion)
								.addComponent(txtDireccion,22,22,22)
							)
					.addGroup(
								layoutDatos.createParallelGroup()
								.addComponent(lblTelefono)
								.addComponent(txtTelefono,22,22,22)
								.addComponent(lblCorreo)
								.addComponent(txtCorreo,22,22,22)
								.addComponent(lblRFC)
								.addComponent(txtRFC,22,22,22)
								.addComponent(lblFolio)
								.addComponent(txtFolio,22,22,22)
							)
					.addGroup(
								layoutDatos.createParallelGroup()
								.addComponent(lblInstituto)
								.addComponent(txtInstituto,22,22,22)
								.addComponent(lblPlantel)
								.addComponent(txtPlantel, 22,22,22)
								.addComponent(lblGrupo)
								.addComponent(txtGrupo, 22,22,22)
								.addComponent(lblTurno)
								.addComponent(txtTurno, 22,22,22)
								.addComponent(lblCalendario)
								.addComponent(txtCalendario, 22,22,22)
							)
					.addGroup(
								layoutDatos.createParallelGroup()
								.addComponent(lblCarrera)
								.addComponent(txtCarrera,22,22,22)
								.addComponent(lblObservaciones)
								.addComponent(txtObservaciones, 22, 22, 22)
							)
				);
		panelDatosText.setLayout(layoutDatos);
		//----------------------------------------------------------------------------------------------
		GroupLayout layoutBotonesDatos = new GroupLayout(panelBotonesDatos);
		layoutBotonesDatos.setAutoCreateContainerGaps(true);
		layoutBotonesDatos.setAutoCreateGaps(true);
		layoutBotonesDatos.setHorizontalGroup(
					layoutBotonesDatos.createSequentialGroup()
					.addGroup(
							layoutBotonesDatos.createParallelGroup()
							.addComponent(btnBuscar, Alignment.CENTER, 95, 95, 95)
							.addComponent(btnRegistrar, Alignment.CENTER, 95, 95, 95)
							.addComponent(btnModificar, Alignment.CENTER, 95, 95, 95)
							.addComponent(btnLimpiar, Alignment.CENTER, 95, 95, 95)
					)
				);
		layoutBotonesDatos.setVerticalGroup(
					layoutBotonesDatos.createSequentialGroup()
					.addComponent(btnBuscar)
					.addComponent(btnRegistrar)
					.addComponent(btnModificar)
					.addComponent(btnLimpiar)
				);
		panelBotonesDatos.setLayout(layoutBotonesDatos);
		//-------------------------------------------------------------------------------
		GroupLayout layoutDatosMain = new GroupLayout(panelDatos);
		layoutDatosMain.setAutoCreateContainerGaps(true);
		layoutDatosMain.setAutoCreateGaps(true);
		layoutDatosMain.setHorizontalGroup(
					layoutDatosMain.createSequentialGroup()
					.addComponent(panelDatosText)
					.addComponent(panelBotonesDatos)
				);
		layoutDatosMain.setVerticalGroup(
					layoutDatosMain.createParallelGroup()
					.addComponent(panelDatosText)
					.addComponent(panelBotonesDatos)
				);
		panelDatos.setLayout(layoutDatosMain);
		//---------------------------------------------------------------------------------
		GroupLayout layoutContenidoAlta = new GroupLayout(panelContenidoAlta);
		layoutContenidoAlta.setAutoCreateContainerGaps(true);
		layoutContenidoAlta.setAutoCreateGaps(true);
		layoutContenidoAlta.setHorizontalGroup(
					layoutContenidoAlta.createSequentialGroup()
					.addComponent(scrollAlta)
				);
		layoutContenidoAlta.setVerticalGroup(
					layoutContenidoAlta.createParallelGroup(Alignment.CENTER)
					.addComponent(scrollAlta)
				);
		panelContenidoAlta.setLayout(layoutContenidoAlta);
		//----------------------------------------------------------------------------------
		GroupLayout layoutAlta = new GroupLayout(panelAlta);
		layoutAlta.setAutoCreateContainerGaps(true);
		layoutAlta.setAutoCreateGaps(true);
		layoutAlta.setHorizontalGroup(
					layoutAlta.createParallelGroup()
					.addGroup(
								layoutAlta.createSequentialGroup()
								.addGroup(
											layoutAlta.createParallelGroup()
											.addComponent(lblPaquetes)
											.addGroup(
														layoutAlta.createSequentialGroup()
														.addComponent(lblRegistroPaquetes)
														.addComponent(txtRegistroPaquetes, 80, 80, 80)
														.addComponent(cbPaquetes, 290,290,290)
														.addComponent(btnVerPaquete, 60, 60, 60)
													)
											.addGroup(
														layoutAlta.createSequentialGroup()
														.addComponent(lblCantidadPaquetes)
														.addComponent(cantidadPaquetes, 70, 70, 70)
														.addComponent(btnAgregarPaquete)
													)
										)
								.addGroup(
											layoutAlta.createParallelGroup()
											.addComponent(lblProductos)
											.addGroup(
														layoutAlta.createSequentialGroup()
														.addComponent(lblRegistroProductos)
														.addComponent(txtRegistroProducto, 80, 80, 80)
														.addComponent(cbProductos, 290, 290, 290)
														.addComponent(btnVerProductos, 60, 60, 60)
													)
											.addGroup(
														layoutAlta.createSequentialGroup()
														.addComponent(lblCantidadProductos)
														.addComponent(cantidadProductos, 70, 70, 70)
														.addComponent(btnAgregarProductos)
													)
										)
							)
					.addGroup(
								layoutAlta.createSequentialGroup()
								.addComponent(panelContenidoAlta)
								.addGroup(
											layoutAlta.createParallelGroup()
											.addGroup(
													layoutAlta.createSequentialGroup()
													.addComponent(btnQuitar)
													.addComponent(btnSaldo)
													)
											.addGroup(
														layoutAlta.createSequentialGroup()
														.addGroup(
																	layoutAlta.createParallelGroup(Alignment.CENTER)
																	.addComponent(lblDescuento)
														)
														.addComponent(txtDescuento, 90, 90, 90)
														.addComponent(rdBtnPesos)
													)
											.addComponent(btnValidar,Alignment.CENTER, 95, 95, 95)
											.addGroup(
														layoutAlta.createSequentialGroup()
														.addComponent(lblPago)
														.addComponent(txtPago, 130, 130, 130)
													)
											.addComponent(btnCobrar, 130, 130, 130)
											.addGroup(
														layoutAlta.createSequentialGroup()
														.addComponent(lblTotal)
														.addComponent(lblPesos, 120, 120, 120)
													)
										)
							)
				);
		layoutAlta.setVerticalGroup(
					layoutAlta.createSequentialGroup()
					.addGroup(
								layoutAlta.createParallelGroup()
								.addGroup(
											layoutAlta.createSequentialGroup()
											.addComponent(lblPaquetes)
											.addGroup(
														layoutAlta.createParallelGroup()
														.addComponent(lblRegistroPaquetes)
														.addComponent(txtRegistroPaquetes,25,25,25)
														.addComponent(cbPaquetes,25,25,25)
														.addComponent(btnVerPaquete,25,25,25)
													)
											.addGroup(
														layoutAlta.createParallelGroup()
														.addComponent(lblCantidadPaquetes)
														.addComponent(cantidadPaquetes,25,25,25)
														.addComponent(btnAgregarPaquete)
													)
										)
								.addGroup(
											layoutAlta.createSequentialGroup()
											.addComponent(lblProductos)
											.addGroup(
														layoutAlta.createParallelGroup()
														.addComponent(lblRegistroProductos)
														.addComponent(txtRegistroProducto,25,25,25)
														.addComponent(cbProductos,25,25,25)
														.addComponent(btnVerProductos,25,25,25)
													)
											.addGroup(
														layoutAlta.createParallelGroup()
														.addComponent(lblCantidadProductos)
														.addComponent(cantidadProductos,25,25,25)
														.addComponent(btnAgregarProductos)
													)
										)
							)
					.addGroup(
								layoutAlta.createParallelGroup()
								.addComponent(panelContenidoAlta, 260, 260, 260)
								.addGroup(
											layoutAlta.createSequentialGroup()
											.addGroup(
													layoutAlta.createParallelGroup()
													.addComponent(btnQuitar)
													.addComponent(btnSaldo)
											)
											.addGroup(
														layoutAlta.createParallelGroup()
														.addComponent(lblDescuento)
														.addComponent(txtDescuento, 22, 22, 22)
														.addComponent(rdBtnPesos)
													)
											.addGroup(
													layoutAlta.createParallelGroup(Alignment.CENTER)
													.addComponent(btnValidar)
											)
											.addGroup(
														layoutAlta.createParallelGroup()
														.addComponent(lblPago)
														.addComponent(txtPago, 22, 22, 22)
													)
											.addGroup(
														layoutAlta.createParallelGroup(Alignment.CENTER)
														.addComponent(btnCobrar, 35, 35, 35)
													)
											.addGroup(
														layoutAlta.createParallelGroup()
														.addComponent(lblTotal)
														.addComponent(lblPesos)
													)
										)
							)
				);
		panelAlta.setLayout(layoutAlta);
		tabs.addTab("ALTA", panelAlta);
		//------------------------------------------------------------------------------
		GroupLayout layoutContenidoEntrega = new GroupLayout(panelContenidoEntrega);
		layoutContenidoEntrega.setAutoCreateContainerGaps(true);
		layoutContenidoEntrega.setAutoCreateGaps(true);
		layoutContenidoEntrega.setHorizontalGroup(
					layoutContenidoEntrega.createSequentialGroup()
					.addComponent(scrollEntrega)
				);
		layoutContenidoEntrega.setVerticalGroup(
					layoutContenidoEntrega.createSequentialGroup()
					.addComponent(scrollEntrega)
				);
		panelContenidoEntrega.setLayout(layoutContenidoEntrega);
		//-------------------------------------------------------------------------------
		GroupLayout layoutEntrega = new GroupLayout(panelEntrega);
		layoutEntrega.setAutoCreateContainerGaps(true);
		layoutEntrega.setAutoCreateGaps(true);
		layoutEntrega.setHorizontalGroup(
					layoutEntrega.createSequentialGroup()
					.addComponent(panelContenidoEntrega)
					.addGroup(
								layoutEntrega.createParallelGroup()
								.addGroup(
										layoutEntrega.createSequentialGroup()
										.addComponent(lblTrabjos)
										.addComponent(cbTrabajos,130,130,130)
										)
								.addGroup(
											layoutEntrega.createSequentialGroup()
											.addComponent(lblTotalEntrega)
											.addComponent(lblPesosEntrega, 100, 100, 100)
										)
								.addGroup(
											layoutEntrega.createSequentialGroup()
											.addComponent(lblAbonadoEntrega)
											.addComponent(lblPesosAbonadoEntrega, 100, 100, 100)
										)
								.addGroup(
											layoutEntrega.createSequentialGroup()
											.addComponent(lblAbonaEntrega)
											.addComponent(txtAbono, 100, 100, 100)
										)
								.addComponent(btnAbonar, 140, 140, 140)
								.addComponent(btnEntregar, 140, 140, 140)
							)
				);
		layoutEntrega.setVerticalGroup(
					layoutEntrega.createParallelGroup()
					.addComponent(panelContenidoEntrega,360,360,360)
					.addGroup(
								layoutEntrega.createSequentialGroup()
								.addGroup(
											layoutEntrega.createParallelGroup()
											.addComponent(lblTrabjos)
											.addComponent(cbTrabajos,25,25,25)
										)
								.addGroup(
											layoutEntrega.createParallelGroup()
											.addComponent(lblTotalEntrega)
											.addComponent(lblPesosEntrega)
										)
								.addGroup(
											layoutEntrega.createParallelGroup()
											.addComponent(lblAbonadoEntrega)
											.addComponent(lblPesosAbonadoEntrega)
										)
								.addGroup(
											layoutEntrega.createParallelGroup()
											.addComponent(lblAbonaEntrega)
											.addComponent(txtAbono,25,25,25)
										)
								.addComponent(btnAbonar, 40, 40, 40)
								.addComponent(btnEntregar, 40, 40, 40)
							)
				);
		panelEntrega.setLayout(layoutEntrega);
		tabs.addTab("ENTREGAR", panelEntrega);
		//---------------------------------------------------------------------------------
		GroupLayout layoutInformacionTrabajo = new GroupLayout(panelInformacionTrabajos);
		layoutInformacionTrabajo.setAutoCreateContainerGaps(true);
		layoutInformacionTrabajo.setAutoCreateGaps(true);
		layoutInformacionTrabajo.setHorizontalGroup(
					layoutInformacionTrabajo.createSequentialGroup()
					.addComponent(scrollInformacion)
				);
		layoutInformacionTrabajo.setVerticalGroup(
					layoutInformacionTrabajo.createSequentialGroup()
					.addComponent(scrollInformacion)
				);
		panelInformacionTrabajos.setLayout(layoutInformacionTrabajo);
		//------------------------------------------------------------------------------------
		GroupLayout layoutTrabajos = new GroupLayout(panelTrabajos);
		layoutTrabajos.setAutoCreateContainerGaps(true);
		layoutTrabajos.setAutoCreateGaps(true);
		layoutTrabajos.setHorizontalGroup(
					layoutTrabajos.createSequentialGroup()
					.addComponent(scrollTrabajos)
					//.addComponent(panelInformacionTrabajos)
					.addGroup(
								layoutTrabajos.createParallelGroup()
								.addComponent(rdBtnDevolucion)
								.addComponent(rdBtnModificacion)
								.addComponent(rdBtnFactura)
								.addComponent(btnCancelar)
							)
				);
		layoutTrabajos.setVerticalGroup(
					layoutTrabajos.createParallelGroup()
					.addComponent(scrollTrabajos)
					//.addComponent(panelInformacionTrabajos,360 ,360, 360)
					.addGroup(
								layoutTrabajos.createSequentialGroup()
								.addComponent(rdBtnDevolucion)
								.addComponent(rdBtnModificacion)
								.addComponent(rdBtnFactura)
								.addComponent(btnCancelar, 28, 28, 28)
							)
				);
		panelTrabajos.setLayout(layoutTrabajos);
		tabs.addTab("HISTORIAL DEL CLIENTE",	panelTrabajos);
		//----------------------------------------------------------------------------------
		GroupLayout layoutMain = new GroupLayout(this.getContentPane());
		layoutMain.setAutoCreateContainerGaps(true);
		layoutMain.setAutoCreateGaps(true);
		layoutMain.setHorizontalGroup(
					layoutMain.createParallelGroup()
					.addGroup(
								layoutMain.createParallelGroup(Alignment.LEADING)
								.addComponent(panelNumero, 300, 300, 300)
							)
					.addComponent(panelDatos, Alignment.CENTER)
					.addComponent(tabs, Alignment.CENTER)
				);
		layoutMain.setVerticalGroup(
					layoutMain.createSequentialGroup()
					.addComponent(panelNumero)
					.addComponent(panelDatos)
					.addComponent(tabs)
				);
		this.setJMenuBar(menu);
		this.setLayout(layoutMain);
		pack();
		tabs.setEnabled(false);
		try{
			countLlamar=Integer.parseInt(datos.getDato("SELECT COUNT(*) FROM trabajos t RIGHT JOIN clientes c ON t.idCliente = c.id WHERE datediff('"+fecha+"', t.fechaAlta)>15 AND datediff('"+fecha+"', t.fechaAlta)<=30 AND t.estatus!=3 AND t.cancelacion=0 "));
			if(countLlamar>0){
				JOptionPane.showMessageDialog(this, "Hay trabajos en espera desde hace 15 días o más, \ncomunicate con los clientes por favor.", "ATENCION", JOptionPane.INFORMATION_MESSAGE);
				Llamadas window = new Llamadas(datos, fecha, id, "SELECT orden, numero, concat(apellidoP,'"+" ',apellidoM,'"+" ',nombres),  telefono, t.fechaAlta, datediff('"+fecha+"', t.fechaAlta), estatus FROM trabajos t RIGHT JOIN clientes c ON t.idCliente = c.id WHERE datediff('"+fecha+"', t.fechaAlta)>15 AND datediff('"+fecha+"', t.fechaAlta)<=30 AND t.estatus!=3 AND t.cancelacion=0", countLlamar);
				window.setVisible(true);
				window.setFocusable(true);
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, e.toString());
		}
	}
	private void vistaTablaAlta(){
		thAux = tablaContenidoAlta.getTableHeader();
		thAux.setFont(new Font("Arial", 1, 15));
		tablaContenidoAlta.getColumnModel().getColumn(0).setResizable(false);
		tablaContenidoAlta.getColumnModel().getColumn(0).setMaxWidth(70);
		tablaContenidoAlta.getColumnModel().getColumn(0).setMinWidth(70);
		tablaContenidoAlta.getColumnModel().getColumn(0).setCellRenderer(centro);
		tablaContenidoAlta.getColumnModel().getColumn(1).setResizable(false);
		//tablaContenidoAlta.getColumnModel().getColumn(1).setMaxWidth(250);
		//tablaContenidoAlta.getColumnModel().getColumn(1).setMinWidth(250);
		tablaContenidoAlta.getColumnModel().getColumn(1).setCellRenderer(centro);
		tablaContenidoAlta.getColumnModel().getColumn(2).setResizable(false);
		tablaContenidoAlta.getColumnModel().getColumn(2).setMaxWidth(95);
		tablaContenidoAlta.getColumnModel().getColumn(2).setMinWidth(95);
		tablaContenidoAlta.getColumnModel().getColumn(2).setCellRenderer(centro);
		tablaContenidoAlta.getColumnModel().getColumn(3).setResizable(false);
		tablaContenidoAlta.getColumnModel().getColumn(3).setMaxWidth(95);
		tablaContenidoAlta.getColumnModel().getColumn(3).setMinWidth(95);
		tablaContenidoAlta.getColumnModel().getColumn(3).setCellRenderer(centro);
		tablaContenidoAlta.setTableHeader(thAux);
		scrollAlta.getViewport().add(tablaContenidoAlta);
		
	}
	private void vistaTablaEntrega(){
		thAux = tablaEntrega.getTableHeader();
		thAux.setFont(new Font("Arial", 1, 15));
		tablaEntrega.getColumnModel().getColumn(0).setResizable(false);
		tablaEntrega.getColumnModel().getColumn(0).setMaxWidth(70);
		tablaEntrega.getColumnModel().getColumn(0).setMinWidth(70);
		tablaEntrega.getColumnModel().getColumn(0).setCellRenderer(centro);
		tablaEntrega.getColumnModel().getColumn(1).setResizable(false);
		//tablaEntrega.getColumnModel().getColumn(1).setMaxWidth(250);
		//tablaEntrega.getColumnModel().getColumn(1).setMinWidth(250);
		tablaEntrega.getColumnModel().getColumn(1).setCellRenderer(centro);
		tablaEntrega.getColumnModel().getColumn(2).setResizable(false);
		tablaEntrega.getColumnModel().getColumn(2).setMaxWidth(95);
		tablaEntrega.getColumnModel().getColumn(2).setMinWidth(95);
		tablaEntrega.getColumnModel().getColumn(2).setCellRenderer(centro);
		tablaEntrega.getColumnModel().getColumn(3).setResizable(false);
		tablaEntrega.getColumnModel().getColumn(3).setMaxWidth(95);
		tablaEntrega.getColumnModel().getColumn(3).setMinWidth(95);
		tablaEntrega.getColumnModel().getColumn(3).setCellRenderer(centro);
		scrollEntrega.getViewport().add(tablaEntrega);
	}
	private void vistaTablaTrabajos(){
		thAux = tablaTrabajos.getTableHeader();
		thAux.setFont(new Font("Arial", 1, 15));
		tablaTrabajos.getColumnModel().getColumn(0).setResizable(false);
		tablaTrabajos.getColumnModel().getColumn(0).setMaxWidth(220);
		tablaTrabajos.getColumnModel().getColumn(0).setMinWidth(220);
		tablaTrabajos.getColumnModel().getColumn(0).setCellRenderer(centro);
		tablaTrabajos.getColumnModel().getColumn(1).setResizable(false);
		//tablaTrabajos.getColumnModel().getColumn(1).setMaxWidth(200);
		//tablaTrabajos.getColumnModel().getColumn(1).setMinWidth(200);
		tablaTrabajos.getColumnModel().getColumn(1).setCellRenderer(centro);
		tablaTrabajos.getColumnModel().getColumn(2).setResizable(false);
		tablaTrabajos.getColumnModel().getColumn(2).setMaxWidth(200);
		tablaTrabajos.getColumnModel().getColumn(2).setMinWidth(200);
		tablaTrabajos.getColumnModel().getColumn(2).setCellRenderer(centro);
		scrollTrabajos.getViewport().add(tablaTrabajos);
	}
	@SuppressWarnings("null")
	public void actionPerformed(ActionEvent e){
 		if(e.getSource()==btnRegistrar){
			iniciarDatos();
			Pattern p = Pattern.compile(expTelefono);
			Matcher m = p.matcher(telefono);
			if(m.matches()){
					String count;
					int resul;
					if(revisaDatosRegistro()){
						count = datos.getDato("SELECT COUNT(*) FROM clientes WHERE apellidoP ='"+paterno+"' AND apellidoM='f"+materno+"' AND nombres='"+nombre+"'");
						if(count.equals("0")){
							count = datos.getDato("SELECT COUNT(*) FROM clientes WHERE telefono ='"+telefono+"'");
							if(count.equals("0")){
								datos.Modificaciones("INSERT INTO clientes (nombres, apellidoP, apellidoM, direccion, telefono, correo, instituto, rfc, fechaRegistro, carrera, plantel, grupo, turno, calendario, bn, color, bn1, color1) VALUES('"+nombre+"', '"+paterno+"', '"+materno+"', '"+direccion+"', '"+telefono+"', '"+correo+"', '"+instituto+"', '"+rfc+"', '"+fecha+"', '"+carrera+"', '"+plantel+"', '"+grupo+"', '"+turno+"', '"+calendar+"', 0, 0, 0, 0)");
								datosTextos = datos.getArray("SELECT id, nombres, apellidoP, apellidoM, direccion, telefono, correo, instituto, rfc, carrera, plantel, grupo, turno, calendario FROM clientes WHERE telefono = '"+telefono+"'", 14);
								idCliente = Integer.parseInt(datosTextos.get(0));
								llenarDatos();
								habilitarAlta();
								mostrado=true;
								tabs.setEnabled(true);
							}
							else{
								resul = JOptionPane.showConfirmDialog(this, "Ya existe un usuario con este número de teléfono. \n¿Deseas ver su información?", "ATENCION", JOptionPane.YES_NO_OPTION);
								if(resul == JOptionPane.YES_OPTION){
									datosTextos = datos.getArray("SELECT id, nombres, apellidoP, apellidoM, direccion, telefono, correo, instituto, rfc, carrera, plantel, grupo, turno, calendario FROM clientes WHERE telefono = '"+telefono+"'", 14);
									idCliente = Integer.parseInt(datosTextos.get(0));
									llenarDatos();
									habilitarAlta();
									mostrado=true;
									tabs.setEnabled(true);
								}
								else{
									JOptionPane.showMessageDialog(this, "Modifique el número de telefóno para registrar a este usuario.", "MENSAJE", JOptionPane.PLAIN_MESSAGE);
								}
							}
						}
						else{
							if(count.equals("1")){
								resul = JOptionPane.showConfirmDialog(this, "Ya existe un usuario con este nombre y apellidos. \n¿Deseas ver su información?", "ATENCION", JOptionPane.YES_NO_OPTION);
								if(resul == JOptionPane.YES_OPTION){
									datosTextos = datos.getArray("SELECT id, nombres, apellidoP, apellidoM, direccion, telefono, correo, instituto, rfc, carrera, plantel, grupo, turno, calendario FROM clientes WHERE apellidoP ='"+paterno+"' AND apellidoM='"+materno+"' AND nombres='"+nombre+"'", 14);
									idCliente = Integer.parseInt(datosTextos.get(0));
									llenarDatos();
									habilitarAlta();
									mostrado=true;
									tabs.setEnabled(true);
								}
								else{
									JOptionPane.showMessageDialog(this, "Debe ingresar nuevos datos.", "ATENCIÓN", JOptionPane.PLAIN_MESSAGE);
									limpiarTextosDatos();
								}
							}
						}
					}
			}
			else{ 
				JOptionPane.showMessageDialog(this, "Debe ingresar sólo números en el campo teléfono.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource()==btnLimpiar){
			limpiar();
		}
		else if(e.getSource()==btnBuscar){
			iniciarDatos();
			String resul;			
			//String mesnaje="";
			if(!(paterno.trim().equals(""))){
				if(!(materno.trim().equals(""))&& !(nombre.trim().equals(""))){
					resul = datos.getDato("SELECT COUNT(*) FROM clientes WHERE apellidoP ='"+paterno+"' AND apellidoM='"+materno+"' AND nombres='"+nombre+"'");
					if(resul.equals("1")){
						datosTextos = datos.getArray("SELECT id, nombres, apellidoP, apellidoM, direccion, telefono, correo, instituto, rfc, carrera, plantel, grupo, turno, calendario FROM clientes WHERE apellidoP='"+paterno+"' AND apellidoM='"+materno+"' AND nombres='"+nombre+"'", 14);
						idCliente = Integer.parseInt(datosTextos.get(0));
						mostrarClaves();
					}
					else{
						JOptionPane.showMessageDialog(this, "Intente buscar al cliente con otro de los campos de busqueda:-Teléfono\n-Folio\n-Correo\n-RFC", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
					}
				}
				else{
					resul = datos.getDato("SELECT COUNT(*) FROM clientes WHERE apellidoP ='"+paterno+"'");
					if(resul.equals("1")){
						datosTextos = datos.getArray("SELECT id, nombres, apellidoP, apellidoM, direccion, telefono, correo, instituto, rfc, carrera, plantel, grupo, turno, calendario FROM clientes WHERE apellidoP='"+paterno+"'", 14);
						idCliente = Integer.parseInt(datosTextos.get(0));
						mostrarClaves();
					}
					else{
						JOptionPane.showMessageDialog(this, "Intente buscar al cliente con otro de los campos de busqueda:\n-Apellido Materno\n-Nombres\n-Teléfono\n-Folio\n-RFC", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
					}
				} 
			}
			else if(!(materno.trim().equals(""))){
					resul = datos.getDato("SELECT COUNT(*) FROM clientes WHERE apellidoM ='"+materno+"'");
					if(resul.equals("1")){
						datosTextos = datos.getArray("SELECT id, nombres, apellidoP, apellidoM, direccion, telefono, correo, instituto, rfc, carrera, plantel, grupo, turno, calendario FROM clientes WHERE apellidoM='"+materno+"'", 14);
						idCliente = Integer.parseInt(datosTextos.get(0));
						mostrarClaves();
					}
					else{
						JOptionPane.showMessageDialog(this, "Intente buscar al cliente con otro de los campos de busqueda:\n-Apellido Paterno\n-Nombres\n-Teléfono\n-Folio\n-Correo\n-RFC", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
					}
			}
			else if(!(nombre.trim().equals(""))){
					resul = datos.getDato("SELECT COUNT(*) FROM clientes WHERE nombres ='"+nombre+"'");
					if(resul.equals("1")){
						datosTextos = datos.getArray("SELECT id, nombres, apellidoP, apellidoM, direccion, telefono, correo, instituto, rfc, carrera, plantel, grupo, turno, calendario FROM clientes WHERE nombres='"+nombre+"'", 14);
						idCliente = Integer.parseInt(datosTextos.get(0));
						mostrarClaves();
					}
					else{
						JOptionPane.showMessageDialog(this, "Intente buscar al cliente con otro de los campos de busqueda:\n-Apellido Paterno\n-Apellido Materno\n-Teléfono\n-Folio\n-Correo\n-RFC", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
					}
			}
			else if(!(telefono.trim().equals(""))){
				resul = datos.getDato("SELECT COUNT(*) FROM clientes WHERE telefono ='"+telefono+"'");
				Pattern p = Pattern.compile(expTelefono);
				Matcher m = p.matcher(telefono);
				if(m.matches()){
					if(resul.equals("1")){
						datosTextos = datos.getArray("SELECT id, nombres, apellidoP, apellidoM, direccion, telefono, correo, instituto, rfc, carrera, plantel, grupo, turno, calendario FROM clientes WHERE telefono='"+telefono+"'", 14);
						idCliente = Integer.parseInt(datosTextos.get(0));
						mostrarClaves();
					}
					else{
						JOptionPane.showMessageDialog(this, "Intente buscar al cliente con otro de los campos de busqueda:\n-Apellido Paterno\n-Apellido Materno\n-Nombres\n-Folio\n-Correo\nRFC", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(this, "Debe ingresar sólo números en el campo teléfono.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(!(folio.trim().equals(""))){
				resul = datos.getDato("SELECT COUNT(*) FROM trabajos WHERE folio ='"+folio+"'");
				if(resul.equals("1")){
					datosTextos = datos.getArray("SELECT c.id, nombres, apellidoP, apellidoM, direccion, telefono, correo, instituto, rfc, carrera, plantel, grupo, turno, calendario FROM clientes c INNER JOIN trabajos t ON c.id = t.idCliente WHERE folio='"+folio+"'", 14);
					idCliente = Integer.parseInt(datosTextos.get(0));
					mostrarClaves();
				}
				else{
					JOptionPane.showMessageDialog(this, "Intente buscar al cliente con otro de los campos de busqueda:\n-Apellido Paterno\n-Apellido Materno\n-Nombres\n-Teléfono\n-Correo\n-RFC", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
				}
			}
			else if(!(rfc.trim().equals(""))){
				resul = datos.getDato("SELECT COUNT(*) FROM trabajos WHERE folio ='"+folio+"'");
				if(!(resul.equals("1"))){
					datosTextos = datos.getArray("SELECT id, nombres, apellidoP, apellidoM, direccion, telefono, correo, instituto, rfc, carrera, plantel, grupo, turno, calendario FROM clientes WHERE rfc='"+rfc+"'", 14);
					idCliente = Integer.parseInt(datosTextos.get(0));
					mostrarClaves();
				}
				else{
					JOptionPane.showMessageDialog(this, "Intente buscar al cliente con otro de los campos de busqueda:\n-Apellido Paterno\n-Apellido Materno\n-Nombres\n-Teléfono\n-Folio\n-Correo", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
				}
			}
			else if(!(correo.trim().equals(""))){
				resul = datos.getDato("SELECT COUNT(*) FROM trabajos WHERE celular ='"+correo+"'");
				if(resul.equals("1")){
					datosTextos = datos.getArray("SELECT id, nombres, apellidoP, apellidoM, direccion, telefono, correo, instituto, rfc, carrera, plantel, grupo, turno, calendario FROM clientes WHERE correo='"+correo+"'", 14);
					idCliente = Integer.parseInt(datosTextos.get(0));
					mostrarClaves();
				}
				else{
					JOptionPane.showMessageDialog(this, "Intente buscar al cliente con otro de los campos de busqueda:\n-Apellido Paterno\n-Apellido Materno\n-Nombres\n-Teléfono\n-Folio\n-RFC", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
 		//-----------------BOTON MODIFICAR--------------------------------------------------------------------------------------------------------------
		else if(e.getSource()== btnModificar){
			String mensaje="El usuario: "+nombre+" ha sido modificado correctamente con los siguientes campos:";
			iniciarDatos();
			int modificados=0;
			if(mostrado){
				if(!(direccion.equals(""))){
					datos.Modificaciones("UPDATE clientes SET direccion='"+direccion+"' WHERE nombres='"+nombre+"' AND apellidoP='"+paterno+"' AND apellidoM='"+materno+"'");
					mensaje+="\n- Dirección";
					modificados++;
				}
				if(!(telefono.trim().equals(""))){
					Pattern p = Pattern.compile(expTelefono);
					Matcher m = p.matcher(telefono);
					if(m.matches()){
						datos.Modificaciones("UPDATE clientes SET telefono='"+telefono+"' WHERE nombres='"+nombre+"' AND apellidoP = '"+paterno+"' AND apellidoM='"+materno+"'");
						mensaje+="\n- Teléfono";
						modificados++;
					}
					else{
						JOptionPane.showMessageDialog(this, "- Telefono: en este campo sólo puede ingresar números.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(!(grupo.trim().equals(""))){
						datos.Modificaciones("UPDATE clientes SET grupo='"+grupo+"' WHERE nombres='"+nombre+"' AND apellidoP = '"+paterno+"' AND apellidoM='"+materno+"'");
						mensaje+="\n- Grupo";
						modificados++;
				}
				if(!(rfc.trim().equals(""))){
					datos.Modificaciones("UPDATE clientes SET rfc='"+rfc+"' WHERE nombres='"+nombre+"' AND apellidoP='"+paterno+"' AND apellidoM='"+materno+"'");
					mensaje+="\n- RFC";
					modificados++;
				}
				if(!(correo.trim().equals(""))){
					datos.Modificaciones("UPDATE clientes SET correo='"+correo+"' WHERE nombres='"+nombre+"' AND apellidoP='"+paterno+"' AND apellidoM='"+materno+"'");
					mensaje+="\n- Correo";
					modificados++;
				}
				if(!(instituto.trim().equals(""))){
					datos.Modificaciones("UPDATE clientes SET instituto='"+instituto+"' WHERE nombres='"+nombre+"' AND apellidoP='"+paterno+"' AND apellidoM='"+materno+"'");
					mensaje+="\n- Instituto";
					modificados++;
				}
				if(!(carrera.trim().equals(""))){
					datos.Modificaciones("UPDATE clientes SET carrera='"+carrera+"' WHERE nombres='"+nombre+"' AND apellidoP='"+paterno+"' AND apellidoM='"+materno+"'");
					mensaje+="\n- Carrera";
					modificados++;
				}
				if(!(plantel.trim().equals(""))){
					datos.Modificaciones("UPDATE clientes SET plantel='"+plantel+"' WHERE nombres='"+nombre+"' AND apellidoP='"+paterno+"' AND apellidoM='"+materno+"'");
					mensaje+="\n- Plantel";
					modificados++;
				}
				if(!(turno.trim().equals(""))){
					datos.Modificaciones("UPDATE clientes SET turno='"+turno+"' WHERE nombres='"+nombre+"' AND apellidoP='"+paterno+"' AND apellidoM='"+materno+"'");
					mensaje+="\n- Turno";
					modificados++;
				}
				if(!(calendar.trim().equals(""))){
					datos.Modificaciones("UPDATE clientes SET calendario='"+calendar+"' WHERE nombres='"+nombre+"' AND apellidoP='"+paterno+"' AND apellidoM='"+materno+"'");
					mensaje+="\n- Calendario";
					modificados++;
				}
				if(modificados>0){
					JOptionPane.showMessageDialog(this, mensaje, "CORRECTO", JOptionPane.DEFAULT_OPTION);
				}
			}
			else{
				JOptionPane.showMessageDialog(this, "Debe buscar un cliente al cual quiere modificar los datos.", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
			}
			
		}
 		//-----------------------------BOTON PARA VER INFORMACION DEL PAQUETE--------------------------------------------------------- 
		else if(e.getSource().equals(btnVerPaquete)){
			Info mensaje = new Info("Datos del paquetes.");
			if(txtRegistroPaquetes.getText().trim().equals("")){
				if(cbPaquetes.getSelectedIndex()!=-1){
					String item = cbPaquetes.getSelectedItem().toString();
					ArrayList<String> producto = datos.getArray("SELECT registro, nombre, descripcion, precio FROM productos WHERE nombre='"+item+"'", 4);
					mensaje.setMensaje(" Registro: "+producto.get(0)+"   Nombre: "+producto.get(1));
					mensaje.setMensaje(" Descripción: \n"+producto.get(2));
					mensaje.setMensaje("Precio: $"+producto.get(3));
					mensaje.showDialog();
				}
				else{
					JOptionPane.showMessageDialog(this, "Debe seleccionar un paquete de la lista\n o escribir el registro.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				String count = datos.getDato("SELECT COUNT(*) FROM productos WHERE registro='"+txtRegistroPaquetes.getText().toUpperCase()+"' AND tipo=2");
				if(count.equals("1")){
					ArrayList<String> producto = datos.getArray("SELECT registro, nombre, descripcion, precio FROM productos WHERE registro='"+txtRegistroPaquetes.getText().toUpperCase()+"'", 4);
					mensaje.setMensaje(" Registro: "+producto.get(0)+"   Nombre: "+producto.get(1));
					mensaje.setMensaje(" Descripción: \n"+producto.get(2));
					mensaje.setMensaje("Precio: $"+producto.get(3));
					mensaje.showDialog();
				}
				else{
					JOptionPane.showMessageDialog(this, "Por favor ingrese un registro correcto.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			txtRegistroPaquetes.setText("");
		}
 		//----------------------------BOTON PARA VER INFORMACION DEL PRODUCTO-----------------------------------------------------------
		else if(e.getSource().equals(btnVerProductos)){
			Info mensaje = new Info("Datos del producto.");
			if(txtRegistroProducto.getText().trim().equals("")){
				if(cbProductos.getSelectedIndex()!=-1){
					String item = cbProductos.getSelectedItem().toString();
					ArrayList<String> producto = datos.getArray("SELECT registro, nombre, descripcion, precio FROM productos WHERE nombre='"+item+"'", 4);
					mensaje.setMensaje(" Registro: "+producto.get(0)+"   Nombre: "+producto.get(1));
					mensaje.setMensaje(" Descripción: \n"+producto.get(2));
					mensaje.setMensaje("Precio: $"+producto.get(3));
					mensaje.showDialog();
				}
				else{
					JOptionPane.showMessageDialog(this, "Debe seleccionar un producto de la lista\n o escribir el registro.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				String count = datos.getDato("SELECT COUNT(*) FROM productos WHERE registro='"+txtRegistroProducto.getText().toUpperCase()+"' AND tipo=1");
				if(count.equals("1")){
					ArrayList<String> producto = datos.getArray("SELECT registro, nombre, descripcion, precio FROM productos WHERE registro='"+txtRegistroProducto.getText().toUpperCase()+"'", 4);
					mensaje.setMensaje(" Registro: "+producto.get(0)+"   Nombre: "+producto.get(1));
					mensaje.setMensaje(" Descripción: \n"+producto.get(2));
					mensaje.setMensaje("Precio: $"+producto.get(3));
					mensaje.showDialog();
				}
				else{
					JOptionPane.showMessageDialog(this, "Por favor ingrese un registro correcto.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			txtRegistroProducto.setText("");
		}
 		//-------------------------------------BOTON AGREGAR PAQUETE-------------------------------------------------------------
		else if(e.getSource().equals(btnAgregarPaquete)){
			int cantidad = Integer.parseInt(cantidadPaquetes.getSelectedItem().toString());
			float precio=0;
			escuela = true;
			//DefaultTableModel aux = (DefaultTableModel)tablaContenidoAlta.getModel(); 
			if(txtRegistroPaquetes.getText().trim().equals("")){
				if(cbPaquetes.getSelectedIndex()!=-1){
					String item = cbPaquetes.getSelectedItem().toString();
					if(cantidad!=0){
						nombrePaquete = item;
						ArrayList<String> producto = datos.getArray("SELECT descripcion, precio FROM productos WHERE nombre='"+item+"'", 2);
						codigos.add(datos.getDato("SELECT registro from productos WHERE nombre='"+item+"'"));
						precio = Float.parseFloat(producto.get(1)) * cantidad;
						agregarTablaAlta(cantidad, producto, precio);
						btnAgregarProductos.setEnabled(false);
						btnAgregarPaquete.setEnabled(false);
						cbPaquetes.setSelectedIndex(-1);
					}
					else{
						JOptionPane.showMessageDialog(this, "Debe elegir una cantidad diferente de cero.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(this, "Debe seleccionar un paquete de la lista\n o escribir el registro.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				String count = datos.getDato("SELECT COUNT(*) FROM productos WHERE registro='"+txtRegistroPaquetes.getText().toUpperCase()+"' AND tipo=2");
				if(count.equals("1")){
					nombrePaquete = datos.getDato("SELECT nombre FROM productos WHERE registro='"+txtRegistroPaquetes.getText().toUpperCase()+"' AND tipo=2");
					ArrayList<String> producto = datos.getArray("SELECT descripcion, precio FROM productos WHERE registro='"+txtRegistroPaquetes.getText().toUpperCase()+"'", 2);
					precio = Float.parseFloat(producto.get(1)) * cantidad;
					agregarTablaAlta(cantidad, producto, precio);
					codigos.add(txtRegistroPaquetes.getText());
					btnAgregarProductos.setEnabled(false);
					btnAgregarPaquete.setEnabled(false);
					cbPaquetes.setSelectedIndex(-1);
				}
				else{
					JOptionPane.showMessageDialog(this, "Por favor ingrese un registro correcto.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			txtRegistroPaquetes.setText("");
		}//<--Termina boton-------------------------------------------------
 		//-----------------------------------BOTON AGREGAR PRODUCTO---------------------------------------------------------
		else if(e.getSource().equals(btnAgregarProductos)){
			int cantidad = Integer.parseInt(cantidadProductos.getSelectedItem().toString());
			float precio=0;
			//DefaultTableModel aux = (DefaultTableModel)tablaContenidoAlta.getModel();
			if(txtRegistroProducto.getText().trim().equals("")){
				if(cbProductos.getSelectedIndex()!=-1){
					String item = cbProductos.getSelectedItem().toString();
					ArrayList<String> producto = datos.getArray("SELECT descripcion, precio FROM productos WHERE nombre='"+item+"'", 2);
					codigos.add(datos.getDato("SELECT registro FROM productos WHERE nombre='"+item+"'"));
					precio = Float.parseFloat(producto.get(1)) * cantidad;
					agregarTablaAlta(cantidad, producto, precio);
					cbProductos.setSelectedIndex(-1);
					btnAgregarPaquete.setEnabled(false);
				}
				else{
					JOptionPane.showMessageDialog(this, "Debe seleccionar un producto de la lista\n o escribir el registro.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				String count = datos.getDato("SELECT COUNT(*) FROM productos WHERE registro='"+txtRegistroProducto.getText().toUpperCase()+"' AND tipo=1");
				if(count.equals("1")){
					ArrayList<String> producto = datos.getArray("SELECT descripcion, precio FROM productos WHERE registro='"+txtRegistroProducto.getText().toUpperCase()+"'", 2);
					precio = Float.parseFloat(producto.get(1)) * cantidad;
					codigos.add(txtRegistroProducto.getText());
					agregarTablaAlta(cantidad, producto, precio);
					cbProductos.setSelectedIndex(-1);
					btnAgregarPaquete.setEnabled(false);
				}
				else{
					JOptionPane.showMessageDialog(this, "Por favor ingrese un registro correcto.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			txtRegistroProducto.setText("");
		}
 		//-------------------Boton de Saldos--------------------------------------------------------------------------------------------------------
		else if(e.getSource()==btnSaldo){
			float precio = Float.parseFloat(JOptionPane.showInputDialog(this, "Cantidad a saldar: $"));
			if(precio>0){
				ArrayList<String> producto = new ArrayList<String>();
				producto.add(saldo);
				producto.add(String.valueOf(precio));
				codigos.add("SALD");
				agregarTablaAlta(1, producto, precio);
			}
			else{
				JOptionPane.showMessageDialog(this, "ATENCIÓN", "Debe de ingresar una canidas mayor a cero.", JOptionPane.INFORMATION_MESSAGE);
			}
		}
 		//---------------------------BOTON QUITAR----------------------------------------------------------------------------------
		else if(e.getSource().equals(btnQuitar)){
			this.modeloAlta.removeRow(filaSeleccionada);
			codigos.remove(filaSeleccionada);
			total = totalPagar-=restar;
			lblPesos.setText(formatoDecimal.format(totalPagar));
			nombrePaquete="";
			if(this.tablaContenidoAlta.getRowCount()==0){
				btnAgregarProductos.setEnabled(true);
				btnAgregarPaquete.setEnabled(true);
			}
			if(escuela){
				escuela=false;
			}
		}
 		//---------------------------BOTON VALIDAR DESCEUNTO-------------------------------------------------------------------------
		else if(e.getSource().equals(btnValidar)){
			float descuento = 0;
			try{
				Object[] arreglo = new Object[4];
				arreglo[1] = "Descuento";
				codigos.add("DESC");
				if(rdBtnPorcentaje.isSelected()){
					descuento = (Integer.parseInt(txtDescuento.getText())*totalPagar)/100;
					total = totalPagar -= descuento;
					arreglo[0] = 1;
					arreglo[2] = arreglo[3] = descuento;
					modeloAlta.addRow(arreglo);
					scrollAlta.getViewport().add(tablaContenidoAlta);
					txtDescuento.setEnabled(false);
					btnValidar.setEnabled(false);
					lblPesos.setText(formatoDecimal.format(totalPagar));
				}
				else if(rdBtnPesos.isSelected()){
					descuento = Float.parseFloat(txtDescuento.getText());
					arreglo[0] = 1;
					arreglo[2] = arreglo[3] = formatoDecimal.format(descuento);
					modeloAlta.addRow(arreglo);
					scrollAlta.getViewport().add(tablaContenidoAlta);
					//this.agregarTablaAlta(1, objeto, ((int)descuento));
					total = totalPagar = totalPagar - descuento;
					txtDescuento.setEnabled(false);
					btnValidar.setEnabled(false);
					lblPesos.setText(formatoDecimal.format(totalPagar));
				}
				else{
					JOptionPane.showMessageDialog(this, "Debe seleccionar algun tipo de descuento.","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(NumberFormatException n){
				JOptionPane.showMessageDialog(this, "Debe ingresar solamente números en el campo descuento.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
 		//-----------------------------BOTON COBRAR EL TRABAJO------------------------------------------------------------------------------
		else if(e.getSource().equals(btnCobrar)){
			String Nota="NOTA DE VENTA";
			if(rdBtnOrden.isSelected()){
				Nota="ORDEN DE COMPRA";
			}
			if(JOptionPane.showConfirmDialog(this, "¿Seguro que desea hacer una "+Nota+"?", "ATENCION", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
			if(!(txtObservaciones.getText().equals(("")))){
				String rows="";
				Object[][] contienePaquetes = {{new Object(), new Object(),""}};
				ArrayList<String> descripcionesBD = new ArrayList<String>();
				Convert NumLetra = new Convert();
				int estado =1;
				String path = System.getProperty("user.home")+"\\Desktop";
				SimpleDateFormat fechaEntrega = new SimpleDateFormat("dd-MMMM-yyyy");
				SimpleDateFormat fechaEntregarBD = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat fechaGuardar = new SimpleDateFormat("yyyyMMdd");
				int hrs = Integer.parseInt(String.valueOf(calendario.get(Calendar.HOUR_OF_DAY)));
				//System.out.println(hrs);
				NotaData datos;
				if(hrs>=12){
					if(!modificaFecha){
						if(calendario.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY){
							calendario.add(Calendar.DATE, 3);
							modificaFecha=true;
						}
						else if(calendario.get(Calendar.DAY_OF_WEEK)==Calendar.TUESDAY || calendario.get(Calendar.DAY_OF_WEEK)==Calendar.THURSDAY ||calendario.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY) {
							calendario.add(Calendar.DATE, 2);
							modificaFecha=true;
						}
						else{
							calendario.add(Calendar.DATE, 1);
							modificaFecha=true;
						}
					}
				}
				String entrega = String.valueOf(fechaEntrega.format(calendario.getTime()));
				String fechaBD = String.valueOf(fechaEntregarBD.format(calendario.getTime()));
				String fGuardar = String.valueOf(fechaGuardar.format(calendario.getTime()));
				String hora = "7:00 PM";//horaEntrega.format(calendario.getTime());
				int orden = 0;
				String numero = txtNumero.getText();
				String elaboro = this.datos.getDato("SELECT usuario FROM usuarios WHERE tipo = 2 AND id = "+ id);
				pago = Integer.parseInt(this.txtPago.getText());
				float tP = totalPagar;
				totalPagar-=pago;
				if(totalPagar==0){
					estado=2;
				}
				else if(totalPagar<0){
					JOptionPane.showMessageDialog(this, "Cambio: $"+totalPagar, "CAMBIO", JOptionPane.INFORMATION_MESSAGE);
					estado=2;
					totalPagar=tP;
				}
				String nombre="";
				ArrayList<NotaData> nota = new ArrayList<NotaData>();
				ArrayList<String> claves = this.datos.getArray("SELECT bn, color FROM clientes WHERE id="+this.idCliente, 2);
				int filas = tablaContenidoAlta.getRowCount();
				try{
					//File dir = new File("src");
					//File dir2 = new File("src");
					File file;
					File file2 = null;
					if(rdBtnNota.isSelected()){
						file = new File("NotaOlvera1_3.jasper");
						file2 = new File("NotaOlveraImprimir.jasper");
						nombre+="0"+numero+fGuardar;
						path+="\\NOTAS"+fecha;
					}
					else{
						file = new File("olveraOrden.jasper");
						file2 = new File("olveraOrdenImprimir.jasper");
						orden = 1;
						nombre+="1"+numero+fGuardar;
						path+="\\ORDENES"+fecha;
					}
					int datosEscuela=0;
					String descripcion="";
					if(!(txtInstituto.getText().equals(""))){
						descripcion+=txtInstituto.getText()+" ";
						datosEscuela++;
					}
					if(!(txtCarrera.getText().equals(""))){
						descripcion+=txtCarrera.getText()+" ";
						datosEscuela++;
					}
					if(!(txtPlantel.getText().equals(""))){
						descripcion+=txtPlantel.getText();
						datosEscuela++;
					}
					if(datosEscuela>0){
						if(rdBtnNota.isSelected()){
							descripcion+="\n";
						}
					}
					if(!(txtGrupo.getText().equals(""))){
						descripcion+=txtGrupo.getText()+" ";
						datosEscuela++;
					}
					if(!(txtTurno.getText().equals(""))){
						descripcion+=txtTurno.getText()+" ";
						datosEscuela++;
					}
					if(!(txtCalendario.getText().equals(""))){
						descripcion+=txtCalendario.getText();
						datosEscuela++;
					}
					if(datosEscuela>0){
						datos = new NotaData(datosTextos.get(2)+" "+datosTextos.get(3)+" "+datosTextos.get(1), datosTextos.get(4), datosTextos.get(5), numero, elaboro, entrega, hora, fMostrar, datosTextos.get(8), "", "", "", "", descripcion, String.valueOf(total)+"0", String.valueOf(pago)+"0", String.valueOf(totalPagar)+"0",NumLetra.Convertir(String.valueOf(total)+"0", true) , txtObservaciones.getText().toUpperCase(), nombre, claves.get(0), claves.get(1));
						nota.add(datos);
						descripcion="";
					}
					for(int pos = 0; pos<filas; pos++){
						String cantidad = tablaContenidoAlta.getValueAt(pos, 0).toString();
						descripcionesBD.add(cantidad);
						if(!escuela){
							if(tablaContenidoAlta.getValueAt(pos, 1).toString().equals("Descuento")){
								descripcionesBD.add(descripcion = tablaContenidoAlta.getValueAt(pos, 1).toString());
							}
							/*else if(tablaContenidoAlta.getValueAt(pos, 0).toString().equals("SALD")){
								//Aqui debe agregar a la descripcion que te dijo bertha--------------------------------------------
							}*/
							else{
								descripcion="PAQUETE DE ";
								if(!(rdBtnNota.isSelected())){
									descripcion+=descripcionEspaci(tablaContenidoAlta.getValueAt(pos, 1).toString());
								}
								else{
									descripcion+=tablaContenidoAlta.getValueAt(pos, 1).toString();
								}
								descripcionesBD.add(descripcion);
							}
							
						}
						else{
							if(tablaContenidoAlta.getValueAt(pos, 1).toString().equals("Descuento")){
								descripcionesBD.add(descripcion = tablaContenidoAlta.getValueAt(pos, 1).toString());
							}
							else if(tablaContenidoAlta.getValueAt(pos, 0).toString().equals("SALD")){
								descripcionesBD.add(descripcion=tablaContenidoAlta.getValueAt(pos, 1).toString());
							}
							else{
								String des = tablaContenidoAlta.getValueAt(pos, 1).toString();
								String aux="";
								if(!(rdBtnNota.isSelected())){
									aux = descripcionEscuela(des);
								}
								else{
									aux=des;
								}
								descripcion +=nombrePaquete+":\n"+ aux;
								String reg = codigos.get(0);
								String idPaquete = this.datos.getDato("SELECT id FROM productos WHERE registro='"+reg+"'");
								rows = this.datos.getDato("SELECT COUNT(*) FROM contiene WHERE idPaquete="+Integer.parseInt(idPaquete));
								contienePaquetes = this.datos.getObjeto("SELECT descripcion FROM contiene WHERE idPaquete="+Integer.parseInt(idPaquete), Integer.parseInt(rows), 1);
							}
						}
						String unitario="";
						String importe="";
						//if(codigos.get(pos).equals("DESC")){
							unitario = tablaContenidoAlta.getValueAt(pos, 2).toString();
							importe = tablaContenidoAlta.getValueAt(pos, 3).toString();
						/*}
						else{
							unitario = tablaContenidoAlta.getValueAt(pos, 2).toString();
							importe = tablaContenidoAlta.getValueAt(pos, 3).toString();
						}*/
						datos = new NotaData(datosTextos.get(2)+" "+datosTextos.get(3)+" "+datosTextos.get(1), datosTextos.get(4), datosTextos.get(5), numero, elaboro, entrega, hora, fMostrar, datosTextos.get(8), codigos.get(pos), cantidad, unitario, importe, descripcion, String.valueOf(total)+"0", String.valueOf(pago)+"0", String.valueOf(totalPagar)+"0",NumLetra.Convertir(String.valueOf(total)+"0", true) , txtObservaciones.getText().toUpperCase(), nombre, claves.get(0), claves.get(1));
						nota.add(datos);
						descripcion="";
					}
					File guardar = new File(path);
					JasperReport reporte = (JasperReport) JRLoader.loadObject(file);
					JasperReport imprimir = (JasperReport) JRLoader.loadObject(file2);
					JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(nota);
					JRBeanCollectionDataSource data1 = new JRBeanCollectionDataSource(nota);
					JasperPrint print = JasperFillManager.fillReport(reporte, null, data);
					JasperPrint print1 = JasperFillManager.fillReport(imprimir, null, data1);
					MyReport vista = new MyReport(this.datos, print, print1, false, guardar, nombre, numero, orden, this.id, this.idCliente, this.total, this.totalPagar, fechaBD, fecha, estado, txtObservaciones.getText(), idTrabajo, descripcionesBD, escuela, contienePaquetes, pago, rows);
					JDialog x = new JDialog();
					x.setModal(true);
					JPanel panel = (JPanel)vista.getContentPane();
					x.setSize(vista.getSize());
					x.setContentPane(panel);
					x.setVisible(true);
							if(vista.getDato()!=0){
								limpiar();
								txtNumero.setText(String.valueOf(Integer.parseInt(this.datos.getDato("SELECT numero FROM trabajos WHERE id = (SELECT MAX(id) FROM trabajos WHERE orden=0 AND anterior=0)"))+1));
								rdBtnNota.setSelected(true);
								escuela=false;
								codigos = new ArrayList<String>();
							}
							else{
								totalPagar=tP;
							}
				}
				catch(Exception j){
					JOptionPane.showMessageDialog(this, j.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(this, "Dedes ingresar observaciones para dar de alta el trabajo", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
			}
		}
		}
 		//-----------------------BOTON ABONAR------------------------------------------------------------------------------------------
		else if(e.getSource().equals(btnAbonar)){
			try{
				int estado = Integer.parseInt(datos.getDato("SELECT estatus FROM trabajos WHERE id="+idTrabajo));
				if(!(txtAbono.getText().trim().equals(""))){
					if(Float.parseFloat(this.lblPesosAbonadoEntrega.getText()) == Float.parseFloat(txtAbono.getText())){
						datos.Modificaciones("INSERT INTO pagos(idTrabajos, idUsuarios, monto, debe, fechaAlta) VALUES("+idTrabajo+", "+id+", "+Integer.parseInt(txtAbono.getText())+", 0, '"+fecha+"')");
						datos.Modificaciones("UPDATE trabajos SET total=0 WHERE id="+idTrabajo);
						this.lblPesosAbonadoEntrega.setText("0.00");
					}
					else if(Float.parseFloat(this.lblPesosAbonadoEntrega.getText()) > Integer.parseInt(txtAbono.getText())){
						float resul = Float.parseFloat(this.lblPesosAbonadoEntrega.getText()) - Float.parseFloat(txtAbono.getText());
						datos.Modificaciones("INSERT INTO pagos(idTrabajos, idUsuarios, monto, debe, fechaAlta) VALUES("+idTrabajo+", "+id+", "+Integer.parseInt(txtAbono.getText())+", "+resul+", '"+fecha+"')");
						datos.Modificaciones("UPDATE trabajos SET total="+resul+" WHERE id="+idTrabajo);
						this.lblPesosAbonadoEntrega.setText(datos.getDato("SELECT MIN(debe) FROM pagos WHERE idTrabajos="+idTrabajo).toString());
					}
					else if(0 == Float.parseFloat(txtAbono.getText())){
						JOptionPane.showMessageDialog(this, "No puede pagar $0.00", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					else{
						float cambio = Float.parseFloat(txtAbono.getText()) - Float.parseFloat(this.lblPesosAbonadoEntrega.getText());
						JOptionPane.showMessageDialog(this, "Cambio: $"+cambio, "CAMBIO", JOptionPane.INFORMATION_MESSAGE);
						datos.Modificaciones("INSERT INTO pagos(idTrabajos, idUsuarios, monto, debe, fechaAlta) VALUES("+idTrabajo+", "+id+", "+Integer.parseInt(txtAbono.getText())+", "+Float.parseFloat(this.lblPesosAbonadoEntrega.getText())+", '"+fecha+"')");
						datos.Modificaciones("UPDATE trabajos SET debe=0 WHERE id="+idTrabajo);
						this.lblPesosAbonadoEntrega.setText("0.00");
					}
					if(Float.parseFloat(this.lblPesosAbonadoEntrega.getText())==0){
						if(estado==1){
							datos.Modificaciones("UPDATE trabajos SET estatus=2 WHERE id="+idTrabajo);
						}
						else if(estado==4){
							datos.Modificaciones("UPDATE trabajos SET estatus=5 WHERE id="+idTrabajo);
						}
						this.habilitarAlta();
					}
					llenarTablaHisorial(idCliente);
					txtAbono.setText("");
				}
				else{
					JOptionPane.showMessageDialog(this, "Debe ingresar una cantidad al campo Abono.", "ATENCION", JOptionPane.WARNING_MESSAGE);
				}
			}
			catch(NumberFormatException n){
				JOptionPane.showMessageDialog(this, "No puede dejar el campo vacío y sólo puede ingresar números dentro de este campo", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource().equals(btnEntregar)){
			if(this.tablaEntrega.getValueAt(this.filaEntrega, 3).equals("")){
				int entregados=0;
				for(int x =0; x<this.tablaEntrega.getRowCount();x++){
					if(!(tablaEntrega.getValueAt(x, 3).equals(""))){
						entregados++;
					}
				}
				@SuppressWarnings("unused")
				String sql = "SELECT r.id FROM realizados r LEFT JOIN contenido c ON r.idContenido=c.id WHERE idTrabajos="+idTrabajo+" AND descripcion='"+tablaEntrega.getValueAt(filaEntrega, 1).toString()+"'";
				int idRealizado = Integer.parseInt(datos.getDato("SELECT r.id FROM realizados r LEFT JOIN contenido c ON r.idContenido=c.id WHERE idTrabajos="+idTrabajo+" AND descripcion='"+tablaEntrega.getValueAt(filaEntrega, 1)+"'"));
				//System.ou
				if(this.lblPesosAbonadoEntrega.getText().equals("0.00")){
					datos.Modificaciones("INSERT INTO entrega (idRealizados, idUsuarios, fecha) VALUES ("+idRealizado+", "+id+", '"+fecha+"')");
					llenarTablaEntregar(idTrabajo);
				}
				else{
					if(tablaEntrega.getRowCount()-entregados==1){
						JOptionPane.showMessageDialog(this, "Debe liquidar el trabajo antes de poder ser entregado por completo.", "ATENCION", JOptionPane.WARNING_MESSAGE);
					}
					else{
						datos.Modificaciones("INSERT INTO entrega (idRealizados, idUsuarios, fecha) VALUES ("+idRealizado+", "+id+", '"+fecha+"')");
						llenarTablaEntregar(idTrabajo);
					}
				}
				entregados=0;
				for(int x =0; x<this.tablaEntrega.getRowCount();x++){
					if(!(tablaEntrega.getValueAt(x, 3).equals(""))){
						entregados++;
					}
				}
				if(entregados==tablaEntrega.getRowCount()){
					datos.Modificaciones("UPDATE trabajos SET estatus=3 WHERE id="+idTrabajo);
					tablaEntrega.setModel(new DefaultTableModel(this.titulosEntregar, 0));
					this.vistaTablaEntrega();
					this.llenarTablaHisorial(idCliente);
					this.btnAgregarPaquete.setEnabled(true);
					this.btnAgregarProductos.setEnabled(true);
				}
			}
			else{
				JOptionPane.showMessageDialog(this, "Este paquete ya se ha entregado", "ATENCION", JOptionPane.INFORMATION_MESSAGE);
			}
			btnEntregar.setEnabled(false);
			tablaEntrega.clearSelection();
		}
		else if(e.getSource().equals(btnCancelar)){
			String cadena = (String)this.tablaTrabajos.getValueAt(filaHistorail, 0);
			String numero="";
			String tipo="";
			int orden=-1;
				for(int x = 0; x<cadena.length(); x++){
					char car = cadena.charAt(x);
					if(numero(car)){
						numero+=car;
					}
					else{
						tipo+=car;
					}
				}
				int num = Integer.parseInt(numero);
				if(tipo.toLowerCase().equals("nota - ")){
					orden=0;
				}
				else{
					orden=1;
				}
				int cancelado= Integer.parseInt(datos.getDato("SELECT cancelacion FROM trabajos WHERE numero="+num+" AND orden="+orden));
				int idTraba= Integer.parseInt(datos.getDato("SELECT id FROM trabajos WHERE numero="+num+" AND orden="+orden));
				float devolucion = Float.parseFloat(datos.getDato("SELECT t.subtotal - p.debe AS Devolver FROM trabajos t INNER JOIN pagos p ON t.id= p.idTrabajos WHERE p.id= (SELECT MAX(id) FROM pagos WHERE idTrabajos= "+idTraba+")"));
				if(cancelado==0){
					int cancelar=0;
					String mensaje="";
					if(this.rdBtnDevolucion.isSelected()){
						cancelar=6;
						mensaje="Devolución";
					}
					else if(this.rdBtnFactura.isSelected()){
						mensaje="Factura";
						cancelar=8;
					}
					else if(this.rdBtnModificacion.isSelected()){
						mensaje="Modificación";
						cancelar = 7;
					}
					if(cancelar!=0){
						int resul = JOptionPane.showConfirmDialog(this, "¿Seguro que desea cancelar el trabajo: "+tipo+numero+" \nComo: "+mensaje, "¿SEGURO?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if(resul==JOptionPane.YES_OPTION){
							if(cancelar!=8){
								JOptionPane.showMessageDialog(this, "Deberá devolver la catidad de: $"+devolucion, "ATENCION", JOptionPane.INFORMATION_MESSAGE);
								datos.Modificaciones("INSERT INTO gastos(idUsuarios, descripcion, monto, fecha) VALUES("+id+", '"+tipo+numero+"', "+devolucion+", '"+fecha+"')");
							}
							datos.Modificaciones("UPDATE trabajos SET estatus="+cancelar+", cancelacion=1 WHERE id="+idTraba);
							this.tablaEntrega.setModel(new DefaultTableModel(this.titulosEntregar, 0));
							this.vistaTablaEntrega();
							JOptionPane.showMessageDialog(this, "El trabajo: "+tipo+numero+" ha sido cancelado correctamente de modo: "+mensaje, "CORRECTO", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else{
						JOptionPane.showMessageDialog(this, "Debe elegir una forma de cancelación para ser cancelado este trabajo.", "ATENCION", JOptionPane.WARNING_MESSAGE);
					}
				this.btnCancelar.setEnabled(false);
				this.tablaTrabajos.clearSelection();
				this.rdBtnDevolucion.setSelected(false);
				this.rdBtnFactura.setSelected(false);
				this.rdBtnModificacion.setSelected(false);
				cbTrabajos.removeAllItems();
				llenarTablaHisorial(idCliente);
				llenarDatosBusqueda();
			}
			else{
				JOptionPane.showMessageDialog(this, "Este trbajo ya ha sido cancelado", "ATENCION", JOptionPane.INFORMATION_MESSAGE);
			}
		}
				
	}
	public boolean revisaDatosRegistro(){
		boolean resultado = true;
		String mensaje ="Debe llenar todos los campos necesarios para registrar un cliente.\n Revise los sieguientes datos:\n";
		if(nombre.trim().equals((""))){
			resultado = false;
			mensaje+="- Nombre.\n";
		}
		if(paterno.trim().equals("")){
			resultado=false;
			mensaje+="- Apellido Paterno.";
		}
		if(materno.trim().equals("")){
			resultado=false;
			mensaje+="- Apellido Materno.";
		}
		if(telefono.trim().equals("")){
			resultado = false;
			mensaje+="Telefono";
		}
		else{
			Pattern p = Pattern.compile(expTelefono);
			Matcher m = p.matcher(telefono);
			if(!(m.matches())){
				resultado = false;
				mensaje+="- Telefono: en este campo sólo puede ingresar números.";
			}
		}
		if(!resultado){
			JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return resultado;
	}
	private void habilitarAlta(){
		btnVerProductos.setEnabled(true);
		btnVerPaquete.setEnabled(true);
		this.btnAgregarPaquete.setEnabled(true);
		this.btnAgregarProductos.setEnabled(true);
		this.btnValidar.setEnabled(true);
		this.btnQuitar.setEnabled(true);
		btnSaldo.setEnabled(true);
	}
	private void limpiarTextosDatos(){
		txtNombre.setText("");
		txtApellidosP.setText("");
		txtApellidosM.setText("");
		txtDireccion.setText("");
		txtCorreo.setText("");
		txtRFC.setText("");
		txtInstituto.setText("");
		txtTelefono.setText("");
		txtFolio.setText("");
		txtCelular.setText("");
		txtCarrera.setText("");
		txtPlantel.setText("");
		txtGrupo.setText("");
		txtTurno.setText("");
		txtCalendario.setText("");
		limpiarTextosAlta();
		limpiarTextosEntregar();
		tabs.setSelectedIndex(0);
	}
	private void limpiarTextosAlta(){
		cbPaquetes.setSelectedIndex(-1);
		cbProductos.setSelectedIndex(-1);
		cantidadProductos.setSelectedIndex(0);
		cantidadPaquetes.setSelectedIndex(0);
		txtDescuento.setText("");
		txtPago.setText("");
		lblPesos.setText("0.00");
		tabs.setEnabled(false);
		rdBtnPesos.setSelected(false);
		rdBtnPorcentaje.setSelected(false);
	}
	private void limpiarTextosEntregar(){
		txtAbono.setText("");
	}
	private void iniciarDatos(){
		nombre = txtNombre.getText().toUpperCase();
		paterno = txtApellidosP.getText().toUpperCase();
		materno = txtApellidosM.getText().toUpperCase();
		telefono = txtTelefono.getText();
		direccion = txtDireccion.getText().toUpperCase();
		instituto = txtInstituto.getText().toUpperCase();
		rfc = txtRFC.getText().toUpperCase();
		correo = txtCorreo.getText();
		folio = txtFolio.getText();
		carrera = txtCarrera.getText().toUpperCase();
		plantel = txtPlantel.getText().toUpperCase();
		grupo = txtGrupo.getText().toUpperCase();
		turno = txtTurno.getText().toUpperCase();
		calendar = txtCalendario.getText().toUpperCase();
	}
	private void llenarDatos(){
		txtNombre.setText(datosTextos.get(1));
		txtApellidosP.setText(datosTextos.get(2));
		txtApellidosM.setText(datosTextos.get(3));
		txtDireccion.setText(datosTextos.get(4));
		txtTelefono.setText(datosTextos.get(5));
		txtCorreo.setText(datosTextos.get(6));
		txtInstituto.setText(datosTextos.get(7));
		txtRFC.setText(datosTextos.get(8));
		txtCarrera.setText(datosTextos.get(9));
		txtPlantel.setText(datosTextos.get(10));
		txtGrupo.setText(datosTextos.get(11));
		txtTurno.setText(datosTextos.get(12));
		txtCalendario.setText(datosTextos.get(13));
	}
	//LLENA EL COMBOBOX DE LOS PAQUETES NORMALES--------------------------------------
	private void llenarComBoxProductos(){
		ArrayList<String> lista = new ArrayList<String>();
		lista = datos.getDatoArray("SELECT nombre FROM productos WHERE tipo=1");
		cbProductos.setModel(new DefaultComboBoxModel<Object>(lista.toArray()));
		cbProductos.setSelectedIndex(-1);
	}
	//LLENA EL COMBOBOX DE LOS PAQUETES ESCOLARES--------------------------------------
	private void iniciarComboBoxPaquetes(){
		ArrayList<String> lista = new ArrayList<String>();
		lista = datos.getDatoArray("SELECT nombre FROM productos WHERE tipo=2");
		cbPaquetes.setModel(new DefaultComboBoxModel<Object>(lista.toArray()));
		cbPaquetes.setSelectedIndex(-1);
	}
	//AGREGA LO SELECCIONADO EN LOS JCOMBOBOX PARA AGREGAR AL TRABAJO-----------------------
	private void agregarTablaAlta(int cantidad, ArrayList<String> producto, float precio){
		Object[] contenido = new Object[4];
		contenido[0] = String.valueOf(cantidad);
		contenido[1] = producto.get(0);
		contenido[2] = producto.get(1);
		contenido[3] = String.valueOf(precio)+"0";
		modeloAlta.addRow(contenido);
		scrollAlta.getViewport().add(tablaContenidoAlta);
		total = totalPagar = totalPagar + precio;
		lblPesos.setText(String.valueOf(totalPagar)+"0");
	}
	//LIMPIA LA PANTALLA PRINCIPAL Y DESACTIVA EL TABEDPANE-------------------------------
	private void limpiar(){
		DefaultTableModel altaEmpty = new DefaultTableModel(this.titulosContenidoAlta, 0);
		DefaultTableModel entregarEmpty = new DefaultTableModel(this.titulosEntregar, 0);
		DefaultTableModel trabajosEmpty = new DefaultTableModel(this.titulosTrabajos, 0);
		this.limpiarTextosAlta();
		this.limpiarTextosDatos();
		btnAgregarPaquete.setEnabled(false);
		btnAgregarProductos.setEnabled(false);
		btnQuitar.setEnabled(false);
		btnCobrar.setEnabled(false);
		btnValidar.setEnabled(false);
		tabs.setEnabled(false);
		btnRegistrar.setEnabled(true);
		mostrado=false;
		modeloAlta = altaEmpty;
		tablaContenidoAlta.setModel(modeloAlta);
		modelEntrega = entregarEmpty;
		tablaEntrega.setModel(modelEntrega);
		modelTrabajos = trabajosEmpty;
		tablaTrabajos.setModel(modelTrabajos);
		totalPagar=0;
		txtDescuento.setEnabled(true);
		txtDescuento.setText("");
		rdBtnPorcentaje.setSelected(false);
		rdBtnPesos.setSelected(false);
		tabs.setSelectedIndex(0);
		lblPesosEntrega.setText("0.00");
		lblPesosAbonadoEntrega.setText("0.00");
		txtPlantel.setText("");
		txtGrupo.setText("");
		txtTurno.setText("");
		txtCarrera.setText("");
		txtObservaciones.setText("");
		btnBuscar.setEnabled(true);
		cbTrabajos.removeAllItems();
		vistaTablaAlta();
		vistaTablaEntrega();
		vistaTablaTrabajos();
		txtNombre.setEnabled(true);
		txtApellidosP.setEnabled(true);
		txtApellidosM.setEnabled(true);
		btnSaldo.setEnabled(false);
	}
	//BUSCA EL HISTORIAL DE LOS TRABAJOS QUE HA PEDIDO-------------------------------------------------------------------------
	private void llenarTablaEntregar(int idTrabajo){
		this.modelEntrega = datos.getModelo("SELECT c.cantidad , c.descripcion , r.fechaAlta , e.fecha  FROM contenido c LEFT JOIN realizados r ON c.id = r.idContenido LEFT JOIN entrega e ON r.id=e.idRealizados WHERE  c.idTrabajos= "+idTrabajo, titulosEntregar, 4);
		ArrayList<String >debe = datos.getArray("SELECT subtotal, total FROM trabajos WHERE id="+idTrabajo, 2);
		this.lblPesosEntrega.setText(debe.get(0));
		this.lblPesosAbonadoEntrega.setText(debe.get(1));
		this.tablaEntrega.setModel(modelEntrega);
		this.vistaTablaEntrega();
		this.tablaContenidoAlta.setFont(new Font("Arial", 1, 15));
		this.scrollEntrega.getViewport().add(this.tablaEntrega);
		if(!(this.lblPesosAbonadoEntrega.getText().equals("0.00"))){
			btnAbonar.setEnabled(true);
		}
		else{
			btnAbonar.setEnabled(false);
		}
	}
	private void llenarTablaHisorial(int idCliente){
		this.modelTrabajos= datos.getModeloHistorial("SELECT numero, c.cantidad, c.descripcion, estatus, idTrabajos, orden  FROM trabajos t INNER JOIN contenido c ON t.id = c.idTrabajos WHERE t.idCliente="+idCliente);
		this.tablaTrabajos.setModel(modelTrabajos);
		this.vistaTablaTrabajos();
		this.tablaTrabajos.setFont(new Font("Arial", 1, 15));
		this.scrollTrabajos.getViewport().add(this.tablaTrabajos);
	}
	//SACARA LAS OBSERVACIONES DE LOS TRABAJOS----------------------------------------------------------------
	private void observaciones(int idTrabajo){
		int f = Integer.parseInt(datos.getDato("SELECT COUNT(*) FROM obsrvaciones r LEFT JOIN contenido c on c.id = r.idContenido WHERE c.idTrabajos="+idTrabajo));
		if(f>=1){
			ArrayList<String> observaciones = datos.getArray("SELECT c.descripcion, r.descripcion, r.fechaAlta FROM obsrvaciones r LEFT JOIN contenido c on c.id = r.idContenido WHERE idTrabajos="+idTrabajo, 3);
			String mensaje ="";
			if(observaciones.size()>0){
				Info window = new Info("Observaciones");
				int x=0;
				while(x<observaciones.size()){
					mensaje+= observaciones.get(x);
					x++;
					mensaje+="\nObservación: "+observaciones.get(x);
					x++;
					mensaje+="	Fecha: "+observaciones.get(x)+"\n\n";
					x++;
				}
				window.setMensaje(mensaje);
				window.showDialog();
			}
		}
	}
	//IDENTEIFICA LOS CARACTERES DE TIPO NUMERICO--------------------------------------------------------------------
	private boolean numero(char car){
		boolean retorno = false;
		try{
			Integer.parseInt(String.valueOf(car));
			retorno=true;
		}
		catch(NumberFormatException e){
			retorno = false;
		}
		return retorno;
	}
	//CREA LA DESCRIPCION PARA CUALQUIER TRABAJO NORMAL---------------------------------------------------------------
	private String descripcionEspaci(String descripcion){
		String aux="";
		for(int x=0; x<descripcion.length();x++){
			if(String.valueOf(descripcion.charAt(x)).equals("\n")){
					aux +=descripcion.charAt(x+1);
			}
			else{
				aux+=descripcion.charAt(x);
			}
		}
		return aux;
	}
	//CREAR LA DESCRIPCION PARA LAS ESCUELAS-------------------------------------------
	private String descripcionEscuela(String descripcion){
		String aux="";
		int count =1;
		for(int x=0; x<descripcion.length();x++){
			if(String.valueOf(descripcion.charAt(x)).equals("\n")){
				if(count%2!=0){
					aux +=descripcion.charAt(x+1);
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
	//BUSCA TODOS LOS TRABAJOS PARA PODER VISUALIZARLOS EN LA VISTA PRINCIPAL--------------------------------------------------------------------------------
	private void llenarDatosBusqueda(){
		int filas = Integer.parseInt(datos.getDato("SELECT COUNT(*) FROM trabajos WHERE cancelacion = 0 AND estatus!=3 AND estatus<6 AND idCliente="+idCliente));
		idTrabajos = datos.getObjeto("SELECT id, numero, orden FROM trabajos WHERE cancelacion = 0 AND estatus!=3 AND estatus<6 AND idCliente="+idCliente, filas, 3);
		ArrayList<String> debe = datos.getArray("SELECT id, estatus, subtotal, total FROM trabajos WHERE idCLiente="+idCliente+" AND id= (SELECT MAX(id) FROM trabajos WHERE estatus<6 AND idCliente="+idCliente+")", 4);
		try{
			int index=-1;
			String trabajo ="";
			for(int x=0; x<filas; x++){
				if(Integer.parseInt(String.valueOf(idTrabajos[x][2]))==0){
					trabajo="Nota - "+String.valueOf(idTrabajos[x][1]);
				}
				else{
					trabajo="Orden - "+String.valueOf(idTrabajos[x][1]);
				}
				if(String.valueOf(idTrabajos[x][0]).equals(debe.get(0))){
					index = x;
				}
				cbTrabajos.addItem(trabajo);
			}
			cbTrabajos.setSelectedIndex(index);
			if(debe.get(1).equals("3")){
				habilitarAlta();
				mostrado=true;
			}	
			else{
				if(Integer.parseInt(debe.get(1).toString())==2 || Integer.parseInt(debe.get(1).toString())==5){
					habilitarAlta();
					mostrado=true;
				}
				idTrabajo = Integer.parseInt(debe.get(0));
				observaciones(Integer.parseInt(debe.get(0)));
				mostrado=true;
				this.lblPesosAbonadoEntrega.setText(debe.get(3));
				this.lblPesosEntrega.setText(debe.get(2));
				llenarTablaEntregar(Integer.parseInt(debe.get(0)));
			}
		}
		catch(Exception x){
			habilitarAlta();
			mostrado=true;
		}
		llenarTablaHisorial(idCliente);
		tabs.setEnabled(true);
		btnRegistrar.setEnabled(false);
		btnModificar.setEnabled(true);
		btnBuscar.setEnabled(false);
	}
	private void mostrarClaves(){
		Info window = new Info("Claves");
		window.setMensaje("Clave de color 1: "+datos.getDato("SELECT color FROM clientes WHERE id="+idCliente));
		window.setMensaje("Clave de color 2: "+datos.getDato("SELECT color1 FROM clientes WHERE id="+idCliente));
		window.setMensaje("\nClave de blanco y negro 1: "+datos.getDato("SELECT bn FROM clientes WHERE id="+idCliente));
		window.setMensaje("\nClave de blanco y negro 2: "+datos.getDato("SELECT bn1 FROM clientes WHERE id="+idCliente));
		window.showDialog();
		llenarDatos();
		llenarDatosBusqueda();
		txtNombre.setEnabled(false);
		txtApellidosM.setEnabled(false);
		txtApellidosP.setEnabled(false);
	}
}

