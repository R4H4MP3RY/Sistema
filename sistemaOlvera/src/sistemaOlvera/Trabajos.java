package sistemaOlvera;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.itextpdf.text.TabStop.Alignment;

public class Trabajos extends JFrame implements ActionListener {
	private Conexion datos;
	private int fila=0, filaAceptada=0;
	private int idTrabajos =0;
	private int Usuario=0;
	private boolean mostrado=false;
	private ArrayList<String> idCont;
	private String fecha="";
	private String descripcion="";
	private String[] titulos = {"Cant.", "Descripción", "Almacen"};
	private JButton btnTerminar = new JButton("Terminar");
	private JButton btnLimpiar = new JButton("Limpiar");
	private JButton btnAdd = new JButton("Agregar");
	private JButton btnAgregar = new JButton("Listo");
	private JLabel lblObservaciones = new JLabel("Osbservaciones:");
	private JTextField txtObservaciones = new JTextField();
	private JLabel lblFolio = new JLabel("Folio: ");
	private JTextField txtFolio = new JTextField();
	private JButton btnBuscar = new JButton("Buscar");
	private JButton btnClaves = new JButton("Claves");
	private JTextField txtClaveBlanco = new JTextField();
	private JTextField txtClaveColor = new JTextField();
	private JTextField txtClaveBlanco1 = new JTextField();
	private JTextField txtClaveColor1 = new JTextField();
	private JLabel lblColor = new JLabel("Clave color 1: ");
	private JLabel lblBlanco = new JLabel("Clave B/N 1: ");
	private JLabel lblBlanco1 = new JLabel("Clave color 2:");
	private JLabel lblColor1 = new JLabel("Clave 2:");
	private DefaultTableModel modeloTabla = new DefaultTableModel(titulos, 0);
	private DefaultTableModel vacio = new DefaultTableModel(titulos,0);
	private JTable tabla = new JTable(modeloTabla);
	private JTableHeader th = new JTableHeader();
	private JScrollPane scrollTabla = new JScrollPane(tabla);
	private DefaultTableCellRenderer alineaCentro = new DefaultTableCellRenderer();
	public Trabajos(String fecha, Conexion datos, int id){
		super("Trabajos - Sistema Olvera");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.fecha=fecha;
		this.datos = datos;
		this.Usuario=id;
		btnTerminar.addActionListener(this);
		btnAgregar.addActionListener(this);
		btnAgregar.setEnabled(false);
		btnAdd.setEnabled(false);
		btnAdd.addActionListener(this);
		btnBuscar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		btnClaves.addActionListener(this);
		btnLimpiar.setFont(new Font("Arial", 0, 20));
		btnClaves.setFont(new Font("Arial", 0, 17));
		btnBuscar.setFont(new Font("Arial", 0, 16));
		btnAgregar.setFont(new Font("Arial", 0, 17));
		btnAdd.setFont(new Font("Arial", 0, 17));
		btnTerminar.setFont(new Font("Arial", 0, 18));
		lblObservaciones.setFont(new Font("Arial", 0, 22));
		lblFolio.setFont(new Font("Arial", 0, 16));
		lblBlanco.setFont(new Font("Arial", 0, 16));
		lblColor.setFont(new Font("Arial", 0, 16));
		txtClaveBlanco.setFont(new Font("Arial", 0, 16));
		txtClaveColor.setFont(new Font("Arial", 0, 16));
		lblBlanco1.setFont(new Font("Arial", 0, 16));
		lblColor1.setFont(new Font("Arial", 0, 16));
		txtClaveBlanco1.setFont(new Font("Arial", 0, 16));
		txtClaveColor1.setFont(new Font("Arial", 0, 16));
		txtFolio.setFont(new Font("Arial", 0, 16));
		txtObservaciones.setFont(new Font("Arial", 0, 16));
		txtObservaciones.getDocument().addDocumentListener(
					new DocumentListener(){

						@Override
						public void changedUpdate(DocumentEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void insertUpdate(DocumentEvent arg0) {
							if(mostrado){
								if(!(txtObservaciones.getText().trim().equals(""))){
									btnAdd.setEnabled(true);
								}
							}
						}

						@Override
						public void removeUpdate(DocumentEvent arg0) {
							if(mostrado){
								if(txtObservaciones.getText().trim().equals("")){
									btnAdd.setEnabled(false);
								}
							}
						}
					}
				);
		tabla.addMouseListener(new MouseAdapter()
				{
					public void mousePressed(MouseEvent m){
						if(m.getClickCount()==1){
							fila = tabla.getSelectedRow();
							descripcion+= tabla.getValueAt(fila, 1);
							if(!(tabla.getValueAt(fila, 2).equals(""))){
								btnAgregar.setEnabled(false);
							}
							else{
								btnAgregar.setEnabled(true);
							}
						}
					}
				}
				);
		GroupLayout layout = new GroupLayout(this.getContentPane());
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.setVerticalGroup(
					layout.createParallelGroup()
						.addComponent(scrollTabla, 400, 400, 400)
								.addGroup(
											layout.createSequentialGroup()
											.addGroup(
														layout.createParallelGroup()
														.addComponent(lblFolio)
														.addComponent(txtFolio, 22,22,22)
														.addComponent(btnBuscar)
													)
											.addComponent(lblObservaciones)
											.addGroup(
													layout.createParallelGroup()
													.addComponent(txtObservaciones, 22, 22, 22)
													//.addComponent(btnAgregar)
											)
											.addComponent(btnAgregar, 50, 50, 50)
											.addComponent(btnAdd, 50, 50, 50)
											.addComponent(btnTerminar,50,50,50)
											.addComponent(btnLimpiar, 50, 50, 50)
											.addGroup(
													layout.createParallelGroup()
													.addComponent(lblBlanco)
													.addComponent(txtClaveBlanco,22,22,22)
													)
											.addGroup(
													layout.createParallelGroup()
													.addComponent(lblBlanco1)
													.addComponent(txtClaveBlanco1,22,22,22)
													)
											.addGroup(
														layout.createParallelGroup()
														.addComponent(lblColor)
														.addComponent(txtClaveColor,22,22,22)
													)
											.addGroup(
													layout.createParallelGroup()
													.addComponent(lblColor1)
													.addComponent(txtClaveColor1,22,22,22)
												)
											.addComponent(btnClaves,50,50,50)
										)
				);
		layout.setHorizontalGroup(
					layout.createSequentialGroup()
						.addComponent(scrollTabla, 600,600,600)
						.addGroup(
								layout.createParallelGroup()
								.addGroup(
										layout.createSequentialGroup()
										.addComponent(lblFolio)
										.addComponent(txtFolio, 200, 200, 200)
										.addComponent(btnBuscar,95,95,95)
										)
								.addComponent(lblObservaciones)
								.addGroup(
											layout.createSequentialGroup()
											.addComponent(txtObservaciones, 270,270,270)
											//.addComponent(btnAgregar,95,95,95)
										)
								.addComponent(btnAdd,105,105,105)
								.addComponent(btnAgregar, 105, 105, 105)
								.addComponent(btnTerminar,110,110,110)
								.addComponent(btnLimpiar, 130,130,130)
								.addGroup(
											layout.createSequentialGroup()
											.addComponent(lblBlanco)
											.addComponent(txtClaveBlanco,90,90,90)
										)
								.addGroup(
										layout.createSequentialGroup()
										.addComponent(lblBlanco1)
										.addComponent(txtClaveBlanco1,90,90,90)
									)
								.addGroup(
										layout.createSequentialGroup()
										.addComponent(lblColor)
										.addComponent(txtClaveColor,90,90,90)
										)
								.addGroup(
										layout.createSequentialGroup()
										.addComponent(lblColor1)
										.addComponent(txtClaveColor1,90,90,90)
										)
								.addComponent(btnClaves,105,105,105)
								)
				);
		this.setLayout(layout);
		vistaTabla();
		pack();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==btnBuscar){
			if(!(txtFolio.getText().equals(""))){
				this.idTrabajos = Integer.parseInt(datos.getDato("SELECT id FROM trabajos WHERE folio='"+txtFolio.getText()+"'"));
				idCont = datos.getDatoArray("SELECT c.id FROM contenido c INNER JOIN trabajos t ON c.idTrabajos=t.id WHERE idTrabajos="+idTrabajos+" AND estatus<4 AND estatus!=3");
				if(idCont.size()!=0){
					modeloTabla = datos.getModelo("SELECT cantidad, descripcion, r.fechaAlta FROM contenido c LEFT JOIN realizados r ON c.id = r.idContenido WHERE idTrabajos="+idTrabajos, titulos, 3);
					tabla.setModel(modeloTabla);
					vistaTabla();
					btnBuscar.setEnabled(false);
					mostrado=true;
				}
				else{
					JOptionPane.showMessageDialog(this, "Este trabajo ya no puede ser modificado.", "ATENCION", JOptionPane.INFORMATION_MESSAGE);
					txtFolio.setText("");
				}
			}
			else{
				JOptionPane.showMessageDialog(this, "No puede dejar el campo vacío.","ERROR" , JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource()==btnAgregar){
			if(fila!=-1){
				if(tabla.getValueAt(fila, 2).equals("")){
					filaAceptada=0;
				}
				else{
					filaAceptada=1;
				}
				if(filaAceptada == 1){
					JOptionPane.showMessageDialog(this,  "Este productos ya se ha dado de alta\n en el almcaen con fecha de: "+tabla.getValueAt(fila, 2), "ATENCION", JOptionPane.WARNING_MESSAGE);
				}
				else{
					if(tabla.getValueAt(fila, 2).toString().equals("0000-00-00")){
						tabla.setModel(datos.getModelo("SELECT cantidad, descripcion, r.fechaAlta FROM contenido c LEFT JOIN realizados r ON c.id = r.idContenido WHERE idTrabajos="+idTrabajos, titulos, 3));
						vistaTabla();
					}
					else{
						datos.Modificaciones("INSERT INTO realizados(idContenido, idUsuario, url, fechaAlta ) VALUES("+Integer.parseInt(idCont.get(fila))+", "+Usuario+", 'null', '"+fecha+"')");
					//	modeloTabla = vacio;
						tabla.setModel(datos.getModelo("SELECT cantidad, descripcion, r.fechaAlta FROM contenido c LEFT JOIN realizados r ON c.id = r.idContenido WHERE idTrabajos="+idTrabajos, titulos, 3));
						vistaTabla();
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(this, "Debe seleccionar un producto de la lista que quiera dar de alta en el almacen.", "ATENCION", JOptionPane.WARNING_MESSAGE);
			}
			txtObservaciones.setText("");
			tabla.clearSelection();
			btnAgregar.setEnabled(false);
			fila=0;
		}
		else if(e.getSource()==btnTerminar){
			int listos=0;
			for(int x=0; x<tabla.getRowCount();x++){
				if(!(tabla.getValueAt(x, 2).equals(""))){
					listos++;
				}
			}
			if(listos==tabla.getRowCount()){
				ArrayList<String> dato = datos.getArray("SELECT id, estatus FROM trabajos WHERE folio='"+txtFolio.getText()+"'", 2);
				if(Integer.parseInt(dato.get(1))==2){
					datos.Modificaciones("UPDATE trabajos SET estatus=5 WHERE id="+Integer.parseInt(dato.get(0)));
				}
				else if(Integer.parseInt(dato.get(1))==1){
					datos.Modificaciones("UPDATE trabajos SET estatus=4 WHERE id="+Integer.parseInt(dato.get(0)));
				}
				
			}
			mostrado=false;
			this.modeloTabla = vacio;
			tabla.setModel(modeloTabla);
			txtObservaciones.setText("");
			txtFolio.setText("");
			txtClaveColor.setText("");
			txtClaveBlanco.setText("");
			btnBuscar.setEnabled(true);
			vistaTabla();
		}
		else if(e.getSource()==btnAdd){
				datos.Modificaciones("INSERT INTO obsrvaciones(idContenido, descripcion, idUsuario, fechaAlta) VALUES("+Integer.parseInt(idCont.get(fila))+", '"+txtObservaciones.getText()+"', "+Usuario+", '"+fecha+"')");
				this.modeloTabla = vacio;
				tabla.clearSelection();
				txtObservaciones.setText("");
				btnBuscar.setEnabled(true);
				btnAgregar.setEnabled(false);
				vistaTabla();
		}
		else if(e.getSource()==btnClaves){
				int idCLiente = Integer.parseInt(datos.getDato("SELECT c.id FROM clientes c INNER JOIN trabajos t ON c.id=t.idCliente WHERE t.folio='"+txtFolio.getText()+"'"));
				if(!(txtClaveColor.getText().equals(""))){
					datos.Modificaciones("UPDATE clientes SET color='"+txtClaveColor.getText().toUpperCase()+"' WHERE id="+idCLiente);
				}
				if(!(txtClaveColor1.getText().equals(""))){
					datos.Modificaciones("UPDATE clientes SET color1='"+txtClaveColor1.getText().toUpperCase()+"' WHERE id="+idCLiente);
				}
				if(!(txtClaveBlanco1.getText().equals(""))){
					datos.Modificaciones("UPDATE clientes SET bn1='"+txtClaveBlanco1.getText().toUpperCase()+"' WHERE id="+idCLiente);
				}
				if(!(txtClaveBlanco.getText().equals(""))){
					datos.Modificaciones("UPDATE clientes SET bn1='"+txtClaveBlanco.getText().toUpperCase()+"' WHERE id="+idCLiente);
				}
				txtClaveColor.setText("");
				txtClaveBlanco.setText("");
				txtClaveColor1.setText("");
				txtClaveBlanco1.setText("");
				btnBuscar.setEnabled(true);
				vistaTabla();
		}
		else if(e.getSource()==btnLimpiar){
			this.modeloTabla = vacio;
			tabla.setModel(modeloTabla);
			txtObservaciones.setText("");
			txtFolio.setText("");
			txtClaveColor.setText("");
			txtClaveBlanco.setText("");
			btnBuscar.setEnabled(true);
			vistaTabla();
		}
	}
	private void vistaTabla(){
		tabla.getColumnModel().getColumn(0).setMaxWidth(70);
		tabla.getColumnModel().getColumn(0).setMinWidth(70);
		tabla.getColumnModel().getColumn(0).setCellRenderer(alineaCentro);
		tabla.getColumnModel().getColumn(2).setMaxWidth(130);
		tabla.getColumnModel().getColumn(2).setMinWidth(130);
		tabla.getColumnModel().getColumn(2).setCellRenderer(alineaCentro);
		tabla.setFont(new Font("Arial", 0, 15));
		th = tabla.getTableHeader();
		th.setFont(new Font("Arial", 1, 18));
		tabla.setTableHeader(th);
		scrollTabla.getViewport().add(tabla);
	}
}
