package sistemaOlvera;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Gastos extends JFrame{
	private static final long serialVersionUID = 1L;
	private String[] titulos = {"Fecha","Descripción","Monto"};
	private JLabel lblGastos = new JLabel("Concepto: ");
	private JLabel lblCantidad = new JLabel("Monto: $");
	private JTextField txtDescripcion = new JTextField();
	private JTextField txtMonto = new JTextField();
	private DefaultTableModel model = new DefaultTableModel(titulos, 0);
	private JTable table = new JTable(model);
	private JScrollPane scroll = new JScrollPane(table);
	private JButton btnAgregar = new JButton("Agregar");
	private int idUsuarios=0;
	private String fechas;
	private Conexion dato;
	private DefaultTableCellRenderer alineaCentro = new DefaultTableCellRenderer();
	public Gastos(int idUsuario, Conexion datos, String fecha){
		super("Gastos - Sistema Olvera");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.idUsuarios=idUsuario;
		this.dato=datos;
		this.fechas=fecha;
		
		//Formato de los objetos----------------------------------------------------
		lblGastos.setFont(new Font("Arial", 0, 17));
		lblCantidad.setFont(new Font("Arial", 0, 17));
		txtDescripcion.setFont(new Font("Arial", 0, 17));
		txtDescripcion.setFont(new Font("Arial", 0, 17));
		txtMonto.getDocument().addDocumentListener( new DocumentListener()
				{
					public void changedUpdate(DocumentEvent arg0) {}
					public void insertUpdate(DocumentEvent arg0) {
						if(!(txtDescripcion.getText().trim().equals(""))){
							btnAgregar.setEnabled(true);
						}						
					}
					public void removeUpdate(DocumentEvent arg0) {
						if(!(txtDescripcion.getText().trim().equals(""))){
							btnAgregar.setEnabled(true);
						}
						else{
							btnAgregar.setEnabled(false);
						}
					}
				}
				);
		txtMonto.setFont(new Font("Arial", 0, 17));
		txtMonto.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAgregar.setFont(new Font("Arial", 0, 20));
		btnAgregar.setEnabled(false);
		btnAgregar.addMouseListener(new MouseAdapter()
				{
					public void mousePressed(MouseEvent e){
						try{
							float monto = Float.parseFloat(txtMonto.getText());
							dato.Modificaciones("INSERT INTO gastos(idUsuarios, descripcion, monto, fecha) VALUES("+idUsuarios+", '"+txtDescripcion.getText().toUpperCase()+"', "+monto+", '"+fechas+"')");
							llenarTabla();
							vistaTabla();
							txtDescripcion.setText("");
							txtMonto.setText("");
						}
						catch(NumberFormatException n){
							mensaje("Debe ingresar sólo cantidades númericas en el campo monto", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				);
		alineaCentro.setHorizontalAlignment(SwingConstants.CENTER);
		llenarTabla();
		vistaTabla();
		
		//Layout----------------------------------------------------------------------
		GroupLayout layout = new GroupLayout(this.getContentPane());
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(
					layout.createParallelGroup()
					.addGroup(
							layout.createSequentialGroup()
							.addComponent(lblGastos)
							.addComponent(txtDescripcion, 350, 350, 350)
							.addComponent(this.lblCantidad)
							.addComponent(txtMonto, 100, 100, 100)
					)
					.addGroup(
							layout.createSequentialGroup()
							.addComponent(scroll,600,600,600)
							.addGroup(
										layout.createParallelGroup()
										.addComponent(btnAgregar, 120, 120, 120)
									)
							)
				);
		layout.setVerticalGroup(
					layout.createSequentialGroup()
					.addGroup(
								layout.createParallelGroup()
								.addComponent(lblGastos)
								.addComponent(txtDescripcion,22,22,22)
								.addComponent(lblCantidad)
								.addComponent(txtMonto,22,22,22)
							)
					.addGroup(
								layout.createParallelGroup()
								.addComponent(scroll,350,350,350)
								.addGroup(
											layout.createSequentialGroup()
											.addComponent(btnAgregar,40,40,40)
										)
							)
				);
		this.setLayout(layout);
		pack();
	}
	private void vistaTabla(){
		JTableHeader th = table.getTableHeader();
		table.getColumnModel().getColumn(0).setMinWidth(90);
		table.getColumnModel().getColumn(0).setMaxWidth(90);
		table.getColumnModel().getColumn(0).setCellRenderer(alineaCentro);
		table.getColumnModel().getColumn(1).setCellRenderer(alineaCentro);
		table.getColumnModel().getColumn(2).setCellRenderer(alineaCentro);
		table.getColumnModel().getColumn(2).setMinWidth(110);
		table.getColumnModel().getColumn(2).setMaxWidth(110);
		table.setFont(new Font("Arial", 0, 14));
		th.setFont(new Font("Arial", 1, 14));
		table.setTableHeader(th);
		scroll.getViewport().add(table);		
	}
	private void mensaje(String mensaje, String titulo, int tipo){
		JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
	}
	private void llenarTabla(){
		table.setModel(dato.getModelo("SELECT fecha, descripcion, monto	FROM gastos WHERE fecha='"+fechas+"'", titulos, 3));
	}
	
}
