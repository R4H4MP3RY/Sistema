package sistemaOlvera;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Llamadas extends JFrame implements ActionListener {
	private Conexion datos;
	private String SQL;
	private int filaSeleccionada=-1;
	private int fil=0;
	private String dia="";
	private String[] titulos ={"Nº","Nombre", "Teléfono","Fecha Alta","Días", "Estado"};
	private String[] titulosRealizados ={"Nº", "Nombre", "Obsrvaciones", "Llamada"};
	private JPanel Llamadas = new JPanel();
	private JPanel llamadasRealizadas = new JPanel();
	private DefaultTableModel modelo = new DefaultTableModel(titulos, 0);
	private DefaultTableModel modeloVacio = new DefaultTableModel(titulos, 0);
	private DefaultTableModel modeloRealizado = new DefaultTableModel(titulosRealizados, 0);
	private DefaultTableModel relizadoVacio = new DefaultTableModel(titulosRealizados,0);
	private JTable tabla = new JTable(modelo);
	private JTable tablaRealizados = new JTable(modeloRealizado);
	private JScrollPane scroll = new JScrollPane(tabla);
	private JScrollPane scrollRealizados = new JScrollPane(tablaRealizados);
	private JLabel lblObservaciones = new JLabel("Observaciones:");
	private JTextArea txtObservaciones = new JTextArea();
	private JScrollPane scrollObservaciones = new JScrollPane(txtObservaciones);
	private JButton btnAgregar = new JButton("Notificado");
	private Object[][] oData = {{new Object(), new Object(), ""}};
	private Object[][] oModel = {{new Object(), new Object(), ""}};
	private JTableHeader th = new JTableHeader();
	private JButton btnCancelar = new JButton("Cancelar");
	public Llamadas(Conexion dato, String fecha, int idUsuario, String sql, int filas){
		super("Llamar - Sistema Olvera");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		datos=dato;
		fil=filas;
		SQL = sql;
		dia=fecha;
		txtObservaciones.setFont(new Font("Arial", 0, 16));
		txtObservaciones.setWrapStyleWord(true);
		txtObservaciones.setLineWrap(true);
		btnAgregar.setFont(new Font("Arial", 0, 16));
		btnCancelar.setFont(new Font("Arial", 0, 16));
		btnAgregar.addActionListener(this);
		btnAgregar.setEnabled(false);
		btnCancelar.addActionListener(this);
		btnCancelar.setEnabled(false);
		lblObservaciones.setFont(new Font("Arial", 0, 16));
		tabla.addMouseListener(new MouseAdapter()
					{
						public void mousePressed(MouseEvent a){
							//JTable aux = (JTable)a.getSource();
							//Point p = a.getPoint();
							filaSeleccionada = tabla.getSelectedRow();
							Info mensaje = new Info("Datos del cliente");
							String data = ""+tabla.getValueAt(tabla.getSelectedRow(), 0);
							data+="	"+tabla.getValueAt(tabla.getSelectedRow(), 1);
							data+="\n\nTeléfono: "+tabla.getValueAt(tabla.getSelectedRow(), 2);
							data+="	Fecha de alta:"+tabla.getValueAt(tabla.getSelectedRow(), 3);
							data+="\n\nDías transcurridos: "+tabla.getValueAt(tabla.getSelectedRow(), 4);
							data+="	Estado: "+tabla.getValueAt(tabla.getSelectedRow(), 5);
							mensaje.setMensaje(data);
							mensaje.showDialog();
							btnAgregar.setEnabled(true);
							btnCancelar.setEnabled(true);
						}
					}
				);
		tablaRealizados.addMouseListener(new MouseAdapter(){
						public void mousePressed(MouseEvent a){
							Info window = new Info("Datos de llamada");
							String mensaje="";
							mensaje+=tablaRealizados.getValueAt(tablaRealizados.getSelectedRow(), 0)+"\n\n";
							mensaje+="Nombre del cliente: "+tablaRealizados.getValueAt(tablaRealizados.getSelectedRow(), 1)+"\n\n";
							mensaje+="Obsrvaciones:\n"+tablaRealizados.getValueAt(tablaRealizados.getSelectedRow(), 2)+"\n\n";
							mensaje+="Fecha realizada: "+tablaRealizados.getValueAt(tablaRealizados.getSelectedRow(), 3);
							window.setMensaje(mensaje);
							window.showDialog();
						}
					}
				);
		//-----------------------------relizadas-----------------------------------------------------
		GroupLayout realizadas = new GroupLayout(llamadasRealizadas);
		realizadas.setAutoCreateContainerGaps(true);
		realizadas.setAutoCreateGaps(true);
		realizadas.setHorizontalGroup(
					realizadas.createSequentialGroup()
					.addComponent(scrollRealizados)
				);
		realizadas.setVerticalGroup(
					realizadas.createParallelGroup()
					.addComponent(scrollRealizados)
				);
		llamadasRealizadas.setBorder(new TitledBorder(null,"Notificado",0,0,new Font("Arial", 1, 17)));
		llamadasRealizadas.setLayout(realizadas);
		//---------------------------------llamadas----------------------------------------------------
		GroupLayout llamadas = new GroupLayout(Llamadas);
		llamadas.setAutoCreateContainerGaps(true);
		llamadas.setAutoCreateGaps(true);
		llamadas.setHorizontalGroup(
					llamadas.createSequentialGroup()
					.addComponent(scroll)
				);
		llamadas.setVerticalGroup(
					llamadas.createParallelGroup()
					.addComponent(scroll)
				);
		Llamadas.setBorder(new TitledBorder(null, "Llamar", 0, 0, new Font("Arial",0,17)));
		Llamadas.setLayout(llamadas);
		
		//-----------------------------------------------------------pincipal--------------------------
		GroupLayout layout = new GroupLayout(this.getContentPane());
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(
					layout.createParallelGroup()
					.addGroup(
							layout.createSequentialGroup()
							.addComponent(Llamadas, 850, 850, 850)
							.addGroup(
									layout.createParallelGroup(Alignment.CENTER)
									.addGroup(
											layout.createSequentialGroup()
											.addComponent(lblObservaciones)
											.addComponent(scrollObservaciones,200, 200, 200)
											)
									.addComponent(btnAgregar, 120,120,120)
									//.addComponent(btnCancelar, 120, 120, 120)
									)
							)
					.addComponent(llamadasRealizadas, 850, 850, 850)
					
				);
		layout.setVerticalGroup(
					layout.createSequentialGroup()
					.addGroup(
							layout.createParallelGroup()
							.addComponent(Llamadas, 300, 300, 300)
							.addGroup(
									layout.createSequentialGroup()
									.addGroup(
												layout.createParallelGroup()
												.addComponent(lblObservaciones)
												.addComponent(scrollObservaciones,50,50,50)
											)
									.addComponent(btnAgregar, 50, 50, 50)
									//.addComponent(btnCancelar, 50, 50, 50)
									)
							)
					.addComponent(llamadasRealizadas,300,300,300)
				);
		this.setLayout(layout);
		vistaTabla();
		vistaRealizadas();
		pack();
	}
	private void vistaTabla(){
		tabla.setModel(datos.getModeloLlamadas(SQL, titulos, 6));
		oData = datos.getObjeto(SQL, fil, 6);
		th = tabla.getTableHeader();
		th.setFont(new Font("Arial", 1, 17));
		tabla.setTableHeader(th);
		tabla.getColumnModel().getColumn(0).setMaxWidth(90);
		tabla.getColumnModel().getColumn(0).setMinWidth(90);
		tabla.getColumnModel().getColumn(2).setMaxWidth(100);
		tabla.getColumnModel().getColumn(2).setMinWidth(100);
		tabla.getColumnModel().getColumn(3).setMaxWidth(100);
		tabla.getColumnModel().getColumn(3).setMinWidth(100);
		tabla.getColumnModel().getColumn(4).setMaxWidth(60);
		tabla.getColumnModel().getColumn(4).setMinWidth(60);
		tabla.getColumnModel().getColumn(5).setMaxWidth(150);
		tabla.getColumnModel().getColumn(5).setMinWidth(150);
		tabla.setFont(new Font("Arial", 0, 15));
		scroll.getViewport().add(tabla);
	}
	private void vistaRealizadas(){
		tablaRealizados.setModel(datos.getModeloRealizadas("SELECT t.orden, t.numero, concat(c.apellidoP,' ',c.apellidoM,' ',c.nombres ), ll.observaciones, ll.fecha FROM llamadas ll INNER JOIN trabajos t ON t.id=ll.idTrabajos INNER JOIN clientes c ON t.idCliente = c.id WHERE ll.fecha='"+dia+"'", titulosRealizados, 4));
		th= tablaRealizados.getTableHeader();
		th.setFont(new Font("Arial", 1, 17));
		tablaRealizados.setTableHeader(th);
		tablaRealizados.getColumnModel().getColumn(0).setMaxWidth(90);
		tablaRealizados.getColumnModel().getColumn(0).setMinWidth(90);
		tablaRealizados.getColumnModel().getColumn(1).setMaxWidth(300);
		tablaRealizados.getColumnModel().getColumn(1).setMinWidth(300);
		//tablaRealizados.getColumnModel().getColumn(2).setMaxWidth(250);
		//tablaRealizados.getColumnModel().getColumn(2).setMinWidth(250);
		tablaRealizados.getColumnModel().getColumn(3).setMaxWidth(100);
		tablaRealizados.getColumnModel().getColumn(3).setMinWidth(100);
		tablaRealizados.setFont(new Font("Arial", 0, 15));
		scrollRealizados.getViewport().add(tablaRealizados);
	}
	public void actionPerformed(ActionEvent a) {
		if(a.getSource()==btnAgregar){
			int orden = Integer.parseInt(oData[filaSeleccionada][0].toString());
			int numero = Integer.parseInt(oData[filaSeleccionada][1].toString());
			int idTrabajo = Integer.parseInt(datos.getDato("SELECT id FROM trabajos WHERE numero="+numero+" AND orden="+orden));
			int idUsuario = Integer.parseInt(datos.getDato("SELECT idUsuario FROM trabajos where id="+idTrabajo));
			String observaciones = txtObservaciones.getText();
			if(observaciones.equals("")){
				JOptionPane.showMessageDialog(this, "Debe ingresar alguna observacion", "ATENCENCION", JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				datos.Modificaciones("INSERT INTO llamadas (idTrabajos, observaciones, idUsuarios, fecha) VALUES("+idTrabajo+", '"+observaciones+"', "+idUsuario+", '"+dia+"')");
				vistaRealizadas();
				int iteracion=0, columna=0;
				while(iteracion<oData.length){
					while(columna<6){
						if(iteracion==filaSeleccionada){
							oData[filaSeleccionada+1][columna]=oData[filaSeleccionada][columna];
						}
						columna++;
					}
					columna=0;
					iteracion++;
				}
				tabla.remove(filaSeleccionada);
				tabla.clearSelection();
			}
		}
		else if(a.getSource()==btnCancelar){
			int orden = Integer.parseInt(oData[filaSeleccionada][0].toString());
			int numero = Integer.parseInt(oData[filaSeleccionada][1].toString());
			datos.Modificaciones("UPDATE trabajos SET cancelacion= 1, estatus=9 WHERE orden="+orden+" AND numero="+numero);
			tabla.clearSelection();
			vistaTabla();
		}
	}
}
