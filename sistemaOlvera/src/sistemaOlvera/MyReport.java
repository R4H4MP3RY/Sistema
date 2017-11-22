package sistemaOlvera;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyReport extends JasperViewer {
	private int retorno=0;
	int guardado=0;
	public MyReport(Conexion datos, JasperPrint jasperPrint, JasperPrint imprimir, boolean isExitOnClose, File guardar, String nombre, String numero, int orden, int id, int idCliente, float total, float totalPagar, String entrega, String fecha, int estado, String observaciones, int idTrabajo, ArrayList<String> descripcionesBD, boolean escuela, Object[][] contienePaquetes, float pago, String rows){
		super(jasperPrint, isExitOnClose);
		//this.setSize(500,700);
		try{
			JPanel panel = ((JPanel) this.viewer.getComponent(0));//Este es el panel de los botones
            //panel.setVisible(false);//Lo puedes ocultar
            Component cmpButtonSave = panel.getComponent(0);
            if(cmpButtonSave instanceof javax.swing.JButton){
                JButton btn = (JButton)cmpButtonSave;
                for( ActionListener al : btn.getActionListeners() ) {
                    btn.removeActionListener( al );
                }
                btn.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                    	if(guardado==0){
                    		guardado=1;
                    		if(!(guardar.exists())){
    							guardar.mkdir();
    						}
                        	try{
    						JasperExportManager.exportReportToPdfFile(jasperPrint, guardar+"\\"+nombre+".pdf");
                        	}
                        	catch(Exception e){
                        		mensaje( e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        	}
                    	}
                    	else{
                    		mensaje( "No se puede guardar más de una vez el mismo archivo", "ATENCION", JOptionPane.INFORMATION_MESSAGE);
                    	}
                    }
                    });
            }
            Component cmpButtonPrint = panel.getComponent(1);
            if(cmpButtonPrint instanceof javax.swing.JButton){
                JButton btn = (JButton)cmpButtonPrint;
                for( ActionListener al : btn.getActionListeners() ) {
                    btn.removeActionListener( al );
                }
                btn.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                    		try{
                    			JasperPrintManager.printReport(imprimir, true);
                    			if(guardado==0){
                    				if(!(guardar.exists())){
                    					guardar.mkdir();
                    				}                    			
                    				JasperExportManager.exportReportToPdfFile(jasperPrint, guardar+"\\"+nombre+".pdf"); 
                    				guardado=1;
                    			}
                    		}
                    		catch(Exception e){
                				mensaje(e.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
                			}
                    		if(retorno==0){
                    		datos.Modificaciones("INSERT INTO trabajos (numero, orden, folio, idUsuario, idCliente, subtotal, total, estatus, fechaAlta, fechaEntrega, cancelacion, observaciones, anterior) VALUES('"+numero+"', "+orden+", '"+nombre+"', "+id+", "+idCliente+", "+total+", "+totalPagar+", "+estado+", '"+fecha+"', '"+entrega+"', 0, '"+observaciones+"', 0)");
                    		int idTrabajo = Integer.parseInt(datos.getDato("SELECT id FROM trabajos WHERE folio = '"+nombre+"' AND idCliente = "+idCliente));
                    		if(!escuela){
                    			for (int x = 0; x<descripcionesBD.size(); x++){
                    				if(descripcionesBD.get(x+1).toString().equals("Descuento")){
                    					datos.Modificaciones("INSERT INTO contenido (idTrabajos, cantidad, descripcion, realizado) VALUES ("+idTrabajo+", "+Integer.parseInt(descripcionesBD.get(x))+", '"+descripcionesBD.get(x+1)+"', 'Proceso')");
                    					datos.Modificaciones("INSERT INTO realizados (idContenido, idUsuario, url, fechaAlta) VALUES ((SELECT id FROM contenido WHERE idTrabajos="+idTrabajo+" AND descripcion ='Descuento'), "+id+", 'null', '"+fecha+"')");
                    					datos.Modificaciones("INSERT INTO entrega (idRealizados, idUsuarios, fecha) VALUES((SELECT id FROM realizados WHERE idContenido= (SELECT id from contenido WHERE idTrabajos="+idTrabajo+" AND descripcion ='Descuento')), "+id+", '"+fecha+"')");
                    				}
                    				else{
                    					datos.Modificaciones("INSERT INTO contenido (idTrabajos, cantidad, descripcion, realizado) VALUES ("+idTrabajo+", "+Integer.parseInt(descripcionesBD.get(x))+", '"+descripcionesBD.get(x+1)+"', 'Proceso')");
                    				}
                    				x++;
                    			}
                    		}
                    		else{
                    			for(int x =0; x<Integer.parseInt(rows); x++){
                    				if(contienePaquetes[x][0]=="Descuento"){
                        				datos.Modificaciones("INSERT INTO contenido (idTrabajos, cantidad, descripcion, realizado) VALUES ("+idTrabajo+", 1, 'Descuento', 'Proceso')");
                        				datos.Modificaciones("INSERT INTO realizados (idContenido, idUsuario, url, fechaAlta) VALUES ((SELECT id FROM contenido WHERE idTrabajos="+idTrabajo+" AND descripcion ='Descuento'), "+id+", 'null', '"+fecha+"')");
                        				datos.Modificaciones("INSERT INTO entrega (idRealizados, idUsuarios, fecha) VALUES((SELECT id FROM realizados WHERE idContenido= (SELECT id from contenido WHERE idTrabajos="+idTrabajo+" AND descripcion ='Descuento')), "+id+", '"+fecha+"')");
                        			}
                    				else{
                    					datos.Modificaciones("INSERT INTO contenido (idTrabajos, cantidad, descripcion, realizado) VALUES("+idTrabajo+", 1, '"+"Paquete de "+contienePaquetes[x][0]+"', 'Procesando')");
                    				}
                    			}
                    		}
                    		datos.Modificaciones("INSERT INTO pagos (idTrabajos, idUsuarios, monto, debe, fechaAlta) VALUES ("+idTrabajo+", "+id+", "+pago+", "+totalPagar+", '"+fecha+"')");
                    	}
                    	retorno++;
                    }
                    });
            }
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void mensaje(String mensaje, String titulo, int tipo ){
		JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
	}
	public int getDato(){
		return retorno;
	}
}
