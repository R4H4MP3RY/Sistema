package sistemaOlvera;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.itextpdf.text.TabStop.Alignment;

public class Ingresos extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private int numero=0, idUser=0;
	private String dia="";
	private Conexion datos;
	//Datos JLabels-------------------------------------------------------------------
		private JLabel lblNombre = new JLabel("Nombre:");
		private JLabel lblApellidosP = new JLabel("Apellido Paterno:");
		private JLabel lblApellidosM = new JLabel("Apellido Materno:");
		private JLabel lblTelefono = new JLabel("Teléfono: ");
		private JLabel lblObservaciones = new JLabel("Observaciones");
		private JLabel lblTotal = new JLabel("Total:");
		private JLabel lblSaldo = new JLabel("Saldo:");
		private JLabel lblAnticipo = new JLabel("Anticipo:");
		private JLabel lblAbono = new JLabel("Abono:");
		private JLabel lblNumero = new JLabel("Nº");
		//Datos JTextField------------------------------------------------------------------
		private JTextField txtNombre = new JTextField();
		private JTextField txtApellidosP = new JTextField();
		private JTextField txtApellidosM = new JTextField();
		private JTextField txtTelefono = new JTextField();
		private JTextField txtObservaciones = new JTextField();
		private JTextField txtNumero = new JTextField();
		private JTextField txtTotal = new JTextField();
		private JTextField txtAnticipo = new JTextField();
		private JTextField txtSaldo = new JTextField();
		private JTextField txtAbono = new JTextField();
		private JPanel panelDatosText = new JPanel();
		private JRadioButton rdBtnNota = new JRadioButton("Nota");
		private JRadioButton rdBtnOrden = new JRadioButton("Orden");
		private ButtonGroup grupoTipo = new ButtonGroup();
		private JButton btnAgregar = new JButton("Agregar");
		private DecimalFormat decimal = new DecimalFormat("0.00");
	public Ingresos(String fecha, int idUsuario, Conexion data) {
		super("INGRESOS");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dia=fecha;
		datos=data;
		idUser=idUsuario;
		//Diseño-------------------------------------------------------------------
		grupoTipo.add(rdBtnNota);
		grupoTipo.add(rdBtnOrden);
		lblNombre.setFont(new Font("Arial", 0, 14));
		lblApellidosP.setFont(new Font("Arial", 0, 14));
		lblApellidosM.setFont(new Font("Arial", 0, 14));
		lblTelefono.setFont(new Font("Arial", 0, 14));
		lblTotal.setFont(new Font("Arial", 0, 14));
		lblAnticipo.setFont(new Font("Arial", 0, 14));
		lblAbono.setFont(new Font("Arial", 0, 14));
		lblSaldo.setFont(new Font("Arial", 0, 14));
		lblNumero.setFont(new Font("Arial", 0, 14));
		txtNombre.setFont(new Font("Arial", 0, 14));
		txtApellidosP.setFont(new Font("Arial", 0, 14));
		txtApellidosM.setFont(new Font("Arial", 0, 14));
		txtTelefono.setFont(new Font("Arial", 0, 14));
		txtObservaciones.setFont(new Font("Arial", 0, 14));
		lblObservaciones.setFont(new Font("Arial", 0, 14));
		txtTotal.setFont(new Font("Arial", 0, 14));
		txtTotal.setHorizontalAlignment(JTextField.RIGHT);
		txtAnticipo.setFont(new Font("Arial", 0, 14));
		txtAnticipo.setHorizontalAlignment(JTextField.RIGHT);
		txtAnticipo.getDocument().addDocumentListener(new DocumentListener()
		{
			public void changedUpdate(DocumentEvent e){
				
			}
			public void insertUpdate(DocumentEvent e){
				try{
					if(Float.parseFloat(txtTotal.getText())-Float.parseFloat(txtAnticipo.getText())==0){
						txtAbono.setEnabled(false);
						txtAbono.setText("0.00");
					}
					else{
						txtAbono.setEnabled(true);
						txtAbono.setText("");
					}
				}
				catch(NumberFormatException n){
				}
			}
			public void removeUpdate(DocumentEvent e){
					try{
						if(Float.parseFloat(txtTotal.getText())-Float.parseFloat(txtAnticipo.getText())==0){
							txtAbono.setEnabled(false);
							txtAbono.setText("0.00");
						}
						else{
							txtAbono.setEnabled(true);
							txtAbono.setText("");
						}
					}
					catch(NumberFormatException n){
					}
			}
		}
	);
		txtAbono.setFont(new Font("Arial", 0, 14));
		txtAbono.setHorizontalAlignment(JTextField.RIGHT);
		txtAbono.getDocument().addDocumentListener(new DocumentListener()
		{
			public void changedUpdate(DocumentEvent e){
				
			}
			public void insertUpdate(DocumentEvent e){
				try{
					txtSaldo.setText(decimal.format(Float.parseFloat(txtTotal.getText())-Float.parseFloat(txtAnticipo.getText())-Float.parseFloat(txtAbono.getText())));
				}
				catch(NumberFormatException n){
				}
			}
			public void removeUpdate(DocumentEvent e){
					try{
						txtSaldo.setText(decimal.format(Float.parseFloat(txtTotal.getText())-Float.parseFloat(txtAnticipo.getText())-Float.parseFloat(txtAbono.getText())));
					}
					catch(NumberFormatException n){
					}
			}
		}
	);
		txtSaldo.setFont(new Font("Arial", 0, 14));
		txtSaldo.setHorizontalAlignment(JTextField.RIGHT);
		txtSaldo.setEnabled(false);
		txtSaldo.setText(decimal.format(0));
		rdBtnNota.setFont(new Font("Arial", 0, 16));
		rdBtnNota.setSelected(true);
		rdBtnOrden.setFont(new Font("Arial", 0, 16));
		btnAgregar.setFont(new Font("Arial", 0, 14));
		btnAgregar.addActionListener(this);
		
		//Panel datos-------------------------------------------------------------
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
								.addComponent(lblTelefono)
								.addComponent(txtTelefono, 115, 115, 115)
								.addComponent(lblObservaciones)
								.addComponent(txtObservaciones, 300, 300, 300)
								.addComponent(lblNumero)
								.addComponent(txtNumero, 150, 150, 150)
								.addComponent(rdBtnNota)
								.addComponent(rdBtnOrden)
							)
					.addGroup(
								layoutDatos.createSequentialGroup()
								.addComponent(lblTotal)
								.addComponent(txtTotal,130,130,130)
							)
					.addGroup(
							layoutDatos.createSequentialGroup()
							.addComponent(lblAnticipo)
							.addComponent(txtAnticipo,130,130,130)
						)
					.addGroup(
							layoutDatos.createSequentialGroup()
							.addComponent(lblAbono)
							.addComponent(txtAbono,130,130,130)
						)
					.addGroup(
							layoutDatos.createSequentialGroup()
							.addComponent(lblSaldo)
							.addComponent(txtSaldo,130,130,130)
							.addComponent(btnAgregar, 130,130,130)
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
								.addComponent(lblTelefono)
								.addComponent(txtTelefono,22,22,22)
								.addComponent(lblObservaciones)
								.addComponent(txtObservaciones, 22, 22, 22)
								.addComponent(lblNumero)
								.addComponent(txtNumero,22,22,22)
								.addComponent(rdBtnNota)
								.addComponent(rdBtnOrden)
							)
					.addGroup(
							layoutDatos.createParallelGroup()
							.addComponent(lblTotal)
							.addComponent(txtTotal,22,22,22)
						)
					.addGroup(
							layoutDatos.createParallelGroup()
							.addComponent(lblAnticipo)
							.addComponent(txtAnticipo,22,22,22)
						)
					.addGroup(
							layoutDatos.createParallelGroup()
							.addComponent(lblAbono)
							.addComponent(txtAbono,22,22,22)
						)
					.addGroup(
							layoutDatos.createParallelGroup()
							.addComponent(lblSaldo)
							.addComponent(txtSaldo,22,22,22)
							.addComponent(btnAgregar, 30,30,30)
						)
				);
		panelDatosText.setLayout(layoutDatos);
		
		GroupLayout main = new GroupLayout(this.getContentPane());
		main.setHorizontalGroup(
					main.createSequentialGroup()
					.addComponent(panelDatosText)
				);
		main.setVerticalGroup(
					main.createParallelGroup()
					.addComponent(panelDatosText)
				);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==btnAgregar){
			String orden="ORDEN DE COMPRA";
			int tipo = 1;
			if(rdBtnNota.isSelected()){
				orden="NOTA DE VENTA";
				tipo=0;
			}
			int estatus=4;
			if(Float.parseFloat(txtSaldo.getText())==0){
				estatus=5;
			}
			if(JOptionPane.showConfirmDialog(this, "¿Seguro que desea guarda este traabjo como "+orden+"?", "¿SEGURO?", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				if(!(txtTotal.getText().trim().equals(""))){
					if(!(txtAnticipo.getText().trim().equals(""))){
						if(!(txtNombre.getText().trim().equals(""))&&!(txtApellidosP.getText().trim().equals(""))&&!(txtApellidosM.getText().trim().equals(""))&&!(txtTelefono.getText().trim().equals(""))){
							if(!(txtObservaciones.getText().trim().equals(""))){
								if(!(txtNumero.getText().equals(""))){
									String count = datos.getDato("SELECT COUNT(*) FROM trabajos WHERE folio='"+txtNumero.getText()+"' AND orden="+tipo);
									if(count.equals("0")){
										count = datos.getDato("SELECT COUNT(*) FROM clientes WHERE nombres='"+txtNombre.getText().toUpperCase()+"' AND apellidoP='"+txtApellidosP.getText().toUpperCase()+"' AND apellidoM='"+txtApellidosM.getText().toUpperCase()+"'");
										if(count.equals("0")){
											count= datos.getDato("SELECT COUNT(*) FROM clientes WHERE telefono='"+txtTelefono.getText()+"'");
											if(count.equals("0")){
												datos.Modificaciones("INSERT INTO clientes (nombres, apellidoP, apellidoM, direccion, telefono, correo, instituto, rfc, fechaRegistro, carrera, plantel, grupo, turno, calendario, bn, color, bn1, color1) VALUES('"+txtNombre.getText().toUpperCase()+"', '"+txtApellidosP.getText().toUpperCase()+"', '"+txtApellidosM.getText().toUpperCase()+"', ' ', '"+txtTelefono.getText()+"', ' ', ' ', ' ','" +dia+"', ' ', ' ', ' ', ' ', ' ', 0, 0, 0, 0)");
												int idCliente = Integer.parseInt(datos.getDato("SELECT id FROM clientes WHERE telefono='"+txtTelefono.getText()+"'"));
												datos.Modificaciones("INSERT INTO trabajos (numero, orden, folio, idUsuario, idCliente, subTotal, total, estatus, fechaAlta, fechaEntrega, cancelacion, observaciones, anterior) VALUES("+Float.parseFloat(txtNumero.getText())+", "+tipo+", '"+txtNumero.getText()+"', "+this.idUser+", "+idCliente+", "+Float.parseFloat(txtTotal.getText())+", "+Float.parseFloat(txtSaldo.getText())+", "+estatus+", '"+dia+"', '"+dia+"', 0, '"+txtObservaciones.getText()+"', 1)");
												int idTrabajo = Integer.parseInt(datos.getDato("SELECT id FROM trabajos WHERE folio = '"+Integer.parseInt(this.txtNumero.getText())+"' AND idCliente = "+idCliente));
												datos.Modificaciones("INSERT INTO pagos (idTrabajos, idUsuarios, monto, debe, fechaAlta) VALUES ("+idTrabajo+", "+idUser+", "+Float.parseFloat(txtAnticipo.getText())+", "+(Float.parseFloat(txtTotal.getText())-Float.parseFloat(txtAnticipo.getText()))+", '2000-12-20')");
												if((Float.parseFloat(txtTotal.getText())-Float.parseFloat(txtAnticipo.getText()))>0){
													datos.Modificaciones("INSERT INTO pagos (idTrabajos, idUsuarios, monto, debe, fechaAlta) VALUES ("+idTrabajo+", "+idUser+", "+Float.parseFloat(txtAbono.getText())+", "+Float.parseFloat(txtSaldo.getText())+", '"+dia+"')");
												}
												txtNombre.setText("");
												txtApellidosP.setText("");
												txtApellidosM.setText("");
												txtObservaciones.setText("");
												txtNumero.setText("");
												txtTotal.setText("");
												txtAnticipo.setText("");
												rdBtnNota.setSelected(true);
												txtTelefono.setText("");
												txtAbono.setText("");
											}
											else{
												JOptionPane.showMessageDialog(this, "Este teléfono ya esta registrado.", "ATENCION", JOptionPane.INFORMATION_MESSAGE);
											}
										}
										else{
											JOptionPane.showMessageDialog(this, "Este nombre ya esta registrado.", "ATENCION", JOptionPane.INFORMATION_MESSAGE);
										}
									}
									else{
										JOptionPane.showMessageDialog(this, "Este trabajo ya esta registrado.", "ATENCION", JOptionPane.INFORMATION_MESSAGE);
									}
								}
								else{
									JOptionPane.showMessageDialog(this, "Debe ingresa un numero en el campo folio.", "ATENCION", JOptionPane.INFORMATION_MESSAGE);
								}
							}	
							else{
								JOptionPane.showMessageDialog(this, "Debes Ingresar observaciones", "ATENCION", JOptionPane.WARNING_MESSAGE);
							}
						}
						else{
							JOptionPane.showMessageDialog(this, "Por favor revise que todos los campos esten llenos correctamente", "ATENCION", JOptionPane.WARNING_MESSAGE);
						}
					}
					else{
						JOptionPane.showMessageDialog(this, "Debes de ingresar el anticipo de la nota.", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(this, "Debes de ingresar el total de la nota.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
}
