package sistemaOlvera;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.GroupLayout;
import javax.swing.JButton;
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

public class SeeClientsAdmin extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	//Variables-----------------------------------------------------------------------------------------------
	private String[] titulosClientes={"Nombres", "Apellidos", "Dirección", "Teléfono", "Correo", "Escuela"};
	private String[] titulosTrabajos={"Número", "Descripción", "Estatus"}; 
	private Conexion datos;
	private String expTelefono = "\\d{8,13}";
	//Components-----------------------------------------------------------------------------------------------
	private JPanel panelClientes = new JPanel();
	private JPanel panelTrabajos = new JPanel();
	private JPanel panelDatos = new JPanel();
	private JLabel lblNombre = new JLabel("Nombres/Apellidos:");
	private JLabel lblCorreo = new JLabel("Teléfono:");
	private JLabel lblRFC = new JLabel("Folio ");
	private JTextField txtNombres = new JTextField();
	private JTextField txtCorreo = new JTextField();
	private JTextField txtRFC = new JTextField();
	private JButton btnBuscar = new JButton("Buscar");
	private JButton btnLimpiar = new JButton("Limpiar");
	JScrollPane scrollClientes;
	JScrollPane scrollTrabajos;
	DefaultTableModel modeloClientes;
	DefaultTableModel modeloTrabajos;
	DefaultTableModel modelEmpty = new DefaultTableModel(titulosTrabajos,0);
	JTable listaClientes;
	JTable listaTrabajos;
	JTableHeader thClientes;
	JTableHeader thTrabajos;
	
	//Construct----------------------------------------------------------------------------------------------------
	public SeeClientsAdmin(Conexion x){
		super("Ver Clientes - Sistema Olvera");
		this.setResizable(false);
		datos=x;
		
		//Configure components----------------------------------------------------
		lblNombre.setFont(new Font("Arial", 0, 14));
		lblCorreo.setFont(new Font("Arial", 0, 14));
		lblRFC.setFont(new Font("Arial", 0, 14));
		txtNombres.setFont(new Font("Arial", 0, 14));
		txtCorreo.setFont(new Font("Arial", 0, 14));
		txtRFC.setFont(new Font("Arial", 0, 14));
		btnBuscar.setFont(new Font("Arial", 0, 14));
		btnBuscar.addActionListener(this);
		btnLimpiar.setFont(new Font("Arial", 0, 14));
		btnLimpiar.addActionListener(this);
		
		//Lists--------------------------------------------------------------------
		modeloClientes = new DefaultTableModel(titulosClientes,0);
		modeloTrabajos = new DefaultTableModel(titulosTrabajos,0);
		listaClientes = new JTable(modeloClientes);
		listaClientes.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					JTable aux = (JTable)e.getSource();
					Point p = e.getPoint();
					int fila = aux.rowAtPoint(p);
					if(e.getClickCount()==1){
						Info windo = new Info("Datos del cliente.");
						String mensaje ="Nombre: "+listaClientes.getValueAt(listaClientes.getSelectedRow(), 0)+" "+listaClientes.getValueAt(listaClientes.getSelectedRow(), 1);
						windo.setMensaje(mensaje);
						mensaje = "Dirección: \n"+listaClientes.getValueAt(listaClientes.getSelectedRow(), 2);
						windo.setMensaje(mensaje);
						mensaje = "Teléfono: "+listaClientes.getValueAt(listaClientes.getSelectedRow(), 3)+"	Escuela: "+listaClientes.getValueAt(listaClientes.getSelectedRow(), 5);
						windo.setMensaje(mensaje);
						mensaje = "Correo: "+listaClientes.getValueAt(listaClientes.getSelectedRow(), 4);
						windo.setMensaje(mensaje);
						String dato = (String) listaClientes.getValueAt(listaClientes.getSelectedRow(), 3);
						String resultado = datos.getDato("SELECT id FROM clientes WHERE telefono ='"+dato+"'");
						if(resultado.trim().equals("")){
							String sql= "SELECT folio, descripcion, debe, t.total, estatus FROM trabajos t INNER JOIN pagos p WHERE t.id = p.idTrabajos AND t.idCliente= "+Integer.parseInt(resultado);
							modeloTrabajos = datos.getModelo(sql, titulosTrabajos, 5);
							listaTrabajos.setModel(modeloTrabajos);
							listaTrabajos.getColumnModel().getColumn(1).setMinWidth(100);
							listaTrabajos.getColumnModel().getColumn(1).setMaxWidth(100);
							scrollTrabajos.getViewport().add(listaTrabajos);
						}
						windo.showDialog();
					}
				}
			}
		);
		thClientes = listaClientes.getTableHeader();
		thClientes.setFont(new Font("Arial", 1, 16));
		listaClientes.setTableHeader(thClientes);
		listaTrabajos = new JTable(modeloTrabajos);
		listaTrabajos.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					JTable aux = (JTable)e.getSource();
					Point p = e.getPoint();
					int fila = aux.rowAtPoint(p);
					if(e.getClickCount()==1){
						Info window = new Info ("Datos del trabajo");
						String mensaje = ""+ listaTrabajos.getValueAt(listaTrabajos.getSelectedRow(), 0);
						window.setMensaje(mensaje);
						mensaje = "Descripción: \n" + listaTrabajos.getValueAt(listaTrabajos.getSelectedRow(), 1);
						window.setMensaje(mensaje);
						mensaje = "Estatus: " + listaTrabajos.getValueAt(listaTrabajos.getSelectedRow(), 2);
						window.setMensaje(mensaje);
						window.showDialog();
					}
				}
			}
		);
		thTrabajos = listaTrabajos.getTableHeader();
		listaTrabajos.getColumnModel().getColumn(0).setMinWidth(80);
		listaTrabajos.getColumnModel().getColumn(0).setMaxWidth(80);
		listaTrabajos.getColumnModel().getColumn(2).setMinWidth(100);
		listaTrabajos.getColumnModel().getColumn(2).setMaxWidth(100);
		thTrabajos.setFont(new Font("Arial", 1, 16));
		listaTrabajos.setTableHeader(thTrabajos);
		scrollClientes = new JScrollPane(listaClientes);
		scrollTrabajos = new JScrollPane(listaTrabajos);
		llenarTablaClientes();
		
		//Panel clients-------------------------------------------------------------
		GroupLayout layoutClientes = new GroupLayout(panelClientes);
		layoutClientes.setAutoCreateContainerGaps(true);
		layoutClientes.setAutoCreateGaps(true);
		layoutClientes.setHorizontalGroup(
				layoutClientes.createSequentialGroup()
				.addComponent(scrollClientes)
		);
		layoutClientes.setVerticalGroup(
				layoutClientes.createParallelGroup()
				.addComponent(scrollClientes)
		);
		panelClientes.setLayout(layoutClientes);
		panelClientes.setBorder(new TitledBorder(null, "Clientes", 0, 0, new Font("Arial", 0, 17)));
		
		//Panel jobs--------------------------------------------------------------------
		
		//Panel Buttons------------------------------------------------
		GroupLayout layoutDatos = new GroupLayout(panelDatos);
		layoutDatos.setAutoCreateContainerGaps(true);
		layoutDatos.setAutoCreateGaps(true);
		layoutDatos.setHorizontalGroup(
				layoutDatos.createSequentialGroup()
				.addGroup(
						layoutDatos.createParallelGroup()
						.addComponent(lblNombre)
						.addComponent(lblCorreo)
						.addComponent(lblRFC)
				)
				.addGroup(
						layoutDatos.createParallelGroup()
						.addComponent(txtNombres,200,200,200)
						.addComponent(txtCorreo,200,200,200)
						.addComponent(txtRFC,200,200,200)
				)
				.addGroup(
						layoutDatos.createParallelGroup()
						.addComponent(btnBuscar,95,95,95)
						.addComponent(btnLimpiar,95,95,95)
				)
		);
		layoutDatos.setVerticalGroup(
				layoutDatos.createParallelGroup()
				.addGroup(
						layoutDatos.createSequentialGroup()
						.addComponent(lblNombre)
						.addComponent(lblCorreo)
						.addComponent(lblRFC)
				)
				.addGroup(
						layoutDatos.createSequentialGroup()
						.addComponent(txtNombres)
						.addComponent(txtCorreo)
						.addComponent(txtRFC)
				)
				.addGroup(
						layoutDatos.createSequentialGroup()
						.addComponent(btnBuscar)
						.addComponent(btnLimpiar)
				)
		);
		panelDatos.setLayout(layoutDatos);
		
		//Principal panel jobs--------------------------------------------------
		GroupLayout layoutTrabajos = new GroupLayout(panelTrabajos);
		layoutTrabajos.setAutoCreateContainerGaps(true);
		layoutTrabajos.setAutoCreateGaps(true);
		layoutTrabajos.setHorizontalGroup(
				layoutTrabajos.createParallelGroup()
				.addComponent(panelDatos)
				.addComponent(scrollTrabajos)
		);
		layoutTrabajos.setVerticalGroup(
				layoutTrabajos.createSequentialGroup()
				.addComponent(panelDatos)
				.addComponent(scrollTrabajos)
		);
		panelTrabajos.setBorder(new TitledBorder(null, "Trabajos", 0, 0, new Font("Arial", 0, 17)));
		panelTrabajos.setLayout(layoutTrabajos);
		
		//Panel principal-----------------------------------------------------------------------------------
		GroupLayout layoutPrincipal = new GroupLayout(this.getContentPane());
		layoutPrincipal.setAutoCreateContainerGaps(true);
		layoutPrincipal.setAutoCreateGaps(true);
		layoutPrincipal.setHorizontalGroup(
				layoutPrincipal.createSequentialGroup()
				.addComponent(panelClientes, 600, 600, 600)
				.addComponent(panelTrabajos)
		);
		layoutPrincipal.setVerticalGroup(
				layoutPrincipal.createParallelGroup()
				.addComponent(panelClientes)
				.addComponent(panelTrabajos)
		);
		this.setLayout(layoutPrincipal);
		pack();
	}
	
	//Method actionAbstract------------------------------------------------------------------------------------
	public void actionPerformed(ActionEvent a){
		if(a.getSource()==btnBuscar){
			String sql="";
			String resultado="";
			if(!(txtNombres.getText().trim().equals(""))){
				sql="SELECT COUNT(*) FROM clientes WHERE nombres='"+txtNombres.getText().toUpperCase()+"'";
				resultado = datos.getDato(sql);
				if(Integer.parseInt(resultado)==1){
					ArrayList<String> llenar = datos.getArray("SELECT concat(nombres, ' ',apellidoP,' ',apellidoM), telefono, id FROM clientes WHERE nombres='"+txtNombres.getText().toUpperCase()+"'", 3);
					this.txtNombres.setText(llenar.get(0));
					this.txtCorreo.setText(llenar.get(1));
					sql= "SELECT numero, c.cantidad, c.descripcion, estatus, idTrabajos, orden  FROM trabajos t INNER JOIN contenido c ON t.id = c.idTrabajos WHERE t.idCliente="+Integer.parseInt(llenar.get(2));
					modeloTrabajos = datos.getModeloHistorial(sql);
					listaTrabajos.setModel(modeloTrabajos);
					listaTrabajos.getColumnModel().getColumn(0).setMinWidth(80);
					listaTrabajos.getColumnModel().getColumn(0).setMaxWidth(80);
					listaTrabajos.getColumnModel().getColumn(2).setMinWidth(100);
					listaTrabajos.getColumnModel().getColumn(2).setMaxWidth(100);
					scrollTrabajos.getViewport().add(listaTrabajos);
				}
				else{
					sql="SELECT COUNT(*) FROM clientes WHERE apellidoP='"+txtNombres.getText().toUpperCase()+"'";
					resultado = datos.getDato(sql);
					if(Integer.parseInt(resultado)==1){
						ArrayList<String> llenar = datos.getArray("SELECT concat(nombres, ' ',apellidoP,' ',apellidoM), telefono, id FROM clientes WHERE apellidoP='"+txtNombres.getText().toUpperCase()+"'", 3);
						this.txtNombres.setText(llenar.get(0));
						this.txtCorreo.setText(llenar.get(1));
						sql= "SELECT numero, c.cantidad, c.descripcion, estatus, idTrabajos, orden  FROM trabajos t INNER JOIN contenido c ON t.id = c.idTrabajos WHERE t.idCliente="+Integer.parseInt(llenar.get(2));
						modeloTrabajos = datos.getModeloHistorial(sql);
						listaTrabajos.setModel(modeloTrabajos);
						listaTrabajos.getColumnModel().getColumn(0).setMinWidth(80);
						listaTrabajos.getColumnModel().getColumn(0).setMaxWidth(80);
						listaTrabajos.getColumnModel().getColumn(2).setMinWidth(100);
						listaTrabajos.getColumnModel().getColumn(2).setMaxWidth(100);
						scrollTrabajos.getViewport().add(listaTrabajos);
					}
					else{
						sql="SELECT COUNT(*) FROM clientes WHERE apellidoM ='"+txtNombres.getText().toUpperCase()+"'";
						resultado=datos.getDato(sql);
						if(Integer.parseInt(resultado)==1){
							ArrayList<String> llenar = datos.getArray("SELECT concat(nombres, ' ',apellidoP,' ',apellidoM), telefono, id FROM clientes WHERE apellidoM='"+txtNombres.getText().toUpperCase()+"'", 3);
							this.txtNombres.setText(llenar.get(0));
							this.txtCorreo.setText(llenar.get(1));
							sql= "SELECT numero, c.cantidad, c.descripcion, estatus, idTrabajos, orden  FROM trabajos t INNER JOIN contenido c ON t.id = c.idTrabajos WHERE t.idCliente="+Integer.parseInt(llenar.get(2));
							modeloTrabajos = datos.getModeloHistorial(sql);
							listaTrabajos.setModel(modeloTrabajos);
							listaTrabajos.getColumnModel().getColumn(0).setMinWidth(80);
							listaTrabajos.getColumnModel().getColumn(0).setMaxWidth(80);
							listaTrabajos.getColumnModel().getColumn(2).setMinWidth(100);
							listaTrabajos.getColumnModel().getColumn(2).setMaxWidth(100);
							scrollTrabajos.getViewport().add(listaTrabajos);
						}
						else{
							JOptionPane.showMessageDialog(this, "Intente con alguno de los otros campos.", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
			else if(!(txtCorreo.getText().trim().equals(""))){
				Pattern p = Pattern.compile(expTelefono);
				Matcher m = p.matcher(txtCorreo.getText());
				if((m.matches())){
					sql="SELECT COUNT(*) FROM clientes WHERE telefono ='"+txtCorreo.getText()+"'";
					resultado=datos.getDato(sql);
					if(Integer.parseInt(resultado)==1){
						ArrayList<String> llenar = datos.getArray("SELECT concat(nombres, ' ',apellidoP,' ',apellidoM) FROM clientes WHERE telefono ='"+txtCorreo.getText()+"'", 1);
						this.txtNombres.setText(llenar.get(0));
						resultado = datos.getDato("SELECT id FROM clientes WHERE telefono='"+txtCorreo.getText()+"'");
						sql= "SELECT numero, c.cantidad, c.descripcion, estatus, idTrabajos, orden  FROM trabajos t INNER JOIN contenido c ON t.id = c.idTrabajos WHERE t.idCliente=  "+Integer.parseInt(resultado);
						modeloTrabajos = datos.getModeloHistorial(sql);
						listaTrabajos.setModel(modeloTrabajos);
						listaTrabajos.getColumnModel().getColumn(0).setMinWidth(80);
						listaTrabajos.getColumnModel().getColumn(0).setMaxWidth(80);
						listaTrabajos.getColumnModel().getColumn(2).setMinWidth(100);
						listaTrabajos.getColumnModel().getColumn(2).setMaxWidth(100);
						scrollTrabajos.getViewport().add(listaTrabajos);
					}
					else{
						JOptionPane.showMessageDialog(this, "Intente con alguno de los otros campos.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
			else if(!(txtRFC.getText().trim().equals(""))){
				sql="SELECT idCliente FROM trabajos WHERE folio='"+txtRFC.getText()+"'";
				resultado = datos.getDato(sql);
				if(Integer.parseInt(resultado)==1){
					ArrayList<String> llenar = datos.getArray("SELECT concat(nombres, ' ',apellidoP,' ',apellidoM), telefono FROM clientes WHERE folio='"+txtRFC.getText()+"'", 2);
					this.txtNombres.setText(llenar.get(0));
					this.txtCorreo.setText(llenar.get(1));
					sql= "SELECT numero, c.cantidad, c.descripcion, estatus, idTrabajos, orden  FROM trabajos t INNER JOIN contenido c ON t.id = c.idTrabajos WHERE t.idCliente=  "+Integer.parseInt(resultado);
					modeloTrabajos = datos.getModeloHistorial(sql);
					listaTrabajos.setModel(modeloTrabajos);
					listaTrabajos.getColumnModel().getColumn(0).setMinWidth(80);
					listaTrabajos.getColumnModel().getColumn(0).setMaxWidth(80);
					listaTrabajos.getColumnModel().getColumn(2).setMinWidth(100);
					listaTrabajos.getColumnModel().getColumn(2).setMaxWidth(100);
					scrollTrabajos.getViewport().add(listaTrabajos);
				}
				else{
					JOptionPane.showMessageDialog(this, "Intente con alguno de los otros campos.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(this, "Debe ingresar información en alguno de los tres campos para buscar los trabajos.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(a.getSource()==btnLimpiar){
			limpiar();
		}
	}
	//Method clear all text--------------------------------------------------------------------------------------
	public void limpiar(){
		listaTrabajos.setModel(modelEmpty);
		listaTrabajos.getColumnModel().getColumn(0).setMinWidth(80);
		listaTrabajos.getColumnModel().getColumn(0).setMaxWidth(80);
		listaTrabajos.getColumnModel().getColumn(2).setMinWidth(100);
		listaTrabajos.getColumnModel().getColumn(2).setMaxWidth(100);
		scrollTrabajos.getViewport().add(listaTrabajos);
		txtNombres.setText("");
		txtCorreo.setText("");
		txtRFC.setText("");
	}
	//Method fill the clients's table-----------------------------------------------------------------------------
	public void llenarTablaClientes(){
		modeloClientes=datos.getModeloClientes("SELECT nombres, apellidoP, apellidoM, direccion, telefono, correo, instituto FROM clientes", titulosClientes, 7);
		listaClientes.setModel(modeloClientes);
		listaClientes.setFont(new Font("Arial", 0, 15));
		scrollClientes.getViewport().add(listaClientes);
	}
}
