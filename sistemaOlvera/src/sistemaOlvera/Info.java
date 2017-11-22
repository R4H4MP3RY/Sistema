package sistemaOlvera;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Info extends JFrame implements ActionListener{
	private JButton btnAceptar = new JButton("Aceptar");
	private JTextArea lblMensaje;
	private JScrollPane scroll;
	private String mensaje="\n";;
	public Info(String titulo){
		super(titulo);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//this.setLocationRelativeTo(null);
		this.setResizable(false);
		lblMensaje = new JTextArea();
		lblMensaje.setFont(new Font("Arial", 0, 20));
		lblMensaje.setEditable(false);
		btnAceptar.setFont(new Font("Arial", 0, 13));
		btnAceptar.addActionListener(this);
		scroll = new JScrollPane(lblMensaje);
		this.setSize(600, 400);
		GroupLayout layOut = new GroupLayout(this.getContentPane());
		layOut.setAutoCreateContainerGaps(true);
		layOut.setAutoCreateGaps(true);
		layOut.setHorizontalGroup(
					layOut.createParallelGroup(Alignment.CENTER)
					.addComponent(scroll)
					.addComponent(btnAceptar,95,95,95)
				);
		layOut.setVerticalGroup(
					layOut.createParallelGroup(Alignment.CENTER)
					.addGroup(
							layOut.createSequentialGroup()
							.addComponent(scroll)
							.addComponent(btnAceptar)
							)
					
				);
		this.setLayout(layOut);
		this.setVisible(true);
		//pack();
	}
	public void actionPerformed(ActionEvent a){
		this.dispose();
	}
	public void setMensaje(String datos){
		mensaje+=datos+"\n\n";;
	}
	public void showDialog(){
		lblMensaje.setText(mensaje);
		this.setVisible(true);
	}
}
