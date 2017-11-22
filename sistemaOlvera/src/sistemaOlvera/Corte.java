package sistemaOlvera;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Corte extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
	private String[] tQuinientos = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	private String[] tDoscientos = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12","13","14","15","16","17","18","19","20"};
	private String[] tCien = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30", "31", "32","33","34","35","36","37","38","39","40"};
	private String[] tCincuenta = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30", "31", "32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50"};
	private String[] tVeinte = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30", "31", "32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50"};
	private String[] tDiez = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30", "31", "32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60","61","62","63","64","65","66","67","68","69","70","71","72","73","74","75","76","77","78","79","80", "81", "82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100"};
	private String[] tCinco = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30", "31", "32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60","61","62","63","64","65","66","67","68","69","70","71","72","73","74","75","76","77","78","79","80", "81", "82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100"};
	private String[] tDos = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30", "31", "32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60","61","62","63","64","65","66","67","68","69","70","71","72","73","74","75","76","77","78","79","80", "81", "82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100"};
	private String[] tUno = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30", "31", "32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60","61","62","63","64","65","66","67","68","69","70","71","72","73","74","75","76","77","78","79","80", "81", "82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100"};
	private String[] tCentavos = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30", "31", "32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60","61","62","63","64","65","66","67","68","69","70","71","72","73","74","75","76","77","78","79","80", "81", "82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100"};
	private Conexion datos;
	private int  idUsuario=0;
	private boolean aceptar=false;
	private String fecha="";
	private Object[][] notas = {{new Object(), new Object(), ""}};
	private Object[][] ordenes = {{new Object(), new Object(), ""}};
	private Object[][] gastos={{new Object(), new Object(), ""}};
	private String[] titulosN ={"Fecha", "Numero", "Nombre", "Total", "Anticipo", "Debe", "Pagó"};
	private String[] titulosC ={"Fecha", "Numero", "Nombre", "Total", "Anticipo", "Debe", "Pagó"};
	private String[] titulosGastos = {"Fecha", "Concepto", "Monto"};
	private float totalEntregar;
	private float efectivo=0;
	private float ventas;
	private float vales;
	private int $200=0;
	private int $500=0;
	private int $100=0;
	private int $50=0;
	private int $20=0;
	private int $10=0;
	private int $5=0;
	private int $2=0;
	private int $1=0;
	private float $_50=0;
	private JPanel panleNotas = new JPanel();
	private JPanel panelOrdenes = new JPanel();
	private JPanel panelGastos = new JPanel();
	private DefaultTableModel modelNotas = new DefaultTableModel(titulosN, 0);
	private JTable tableNotas = new JTable(modelNotas);
	private JScrollPane scrollNotas = new JScrollPane(tableNotas);
	private DefaultTableModel modelOrden = new DefaultTableModel(titulosC, 0);
	private JTable tableOrden = new JTable(modelOrden);
	private JScrollPane scrollOrden = new JScrollPane(tableOrden);
	private DefaultTableModel modelGastos = new DefaultTableModel(titulosGastos, 0);
	private JTable tableGastos = new JTable(modelGastos);
	private JScrollPane scrollGastos = new JScrollPane(tableGastos);
	private JLabel caja = new JLabel("Efectivo en caja.");
	private JLabel quinietos = new JLabel("500.00");
	private JComboBox txtQuinientos = new JComboBox(tQuinientos);
	private JLabel doscientos = new JLabel("200.00");
	private JComboBox txtDoscientos = new JComboBox(tDoscientos);
	private JLabel cien = new JLabel("100.00");
	private JComboBox txtCien = new JComboBox(tCien);
	private JLabel cincuenta = new JLabel("50.00");
	private JComboBox txtCincuenta = new JComboBox(tCincuenta);
	private JLabel veinte = new JLabel("20.00");
	private JComboBox txtVeinte = new JComboBox(tVeinte);
	private JLabel diez = new JLabel("10.00");
	private JComboBox txtDiez = new JComboBox(tDiez);
	private JLabel cinco = new JLabel("5.00");
	private JComboBox txtCinco = new JComboBox(tCinco);
	private JLabel dos = new JLabel("2.00");
	private JComboBox txtDos = new JComboBox(tDos);
	private JLabel uno = new JLabel("1.00");
	private JComboBox txtUno = new JComboBox(tUno);
	private JLabel centavos = new JLabel(".50");
	private JComboBox txtcentavos = new JComboBox(tCentavos);
	private JLabel lblEfectivo = new JLabel("Efectivo: $");
	private JLabel lblPesosEfectivo = new JLabel("0.00");
	private JLabel lblVentas = new JLabel("Ingresos: $");
	private JLabel lblPesosVentas = new JLabel("0.00");
	private JLabel lblVales = new JLabel("Vales: $");
	private JLabel lblPesosVales = new JLabel("0.00");
	private JLabel lblCaja = new JLabel("Caja: $");
	private JLabel lblPesosCaja = new JLabel("0.00");
	private JLabel lblEntregar = new JLabel("Total a entregar:");
	private JLabel lblSignoEntregar = new JLabel("$");
	private JLabel lblPesosEntregar = new JLabel("0.00");
	private JLabel signo500 = new JLabel("$");
	private JLabel signo200 = new JLabel("$");
	private JLabel signo100 = new JLabel("$");
	private JLabel signo50 = new JLabel("$");
	private JLabel signo20 = new JLabel("$");
	private JLabel signo10 = new JLabel("$");
	private JLabel signo5 = new JLabel("$");
	private JLabel signo2 = new JLabel("$");
	private JLabel signo1 = new JLabel("$");
	private JLabel signo_5 = new JLabel("$");
	private JButton btnAceptar = new JButton("Imprimir");
	private DecimalFormat formatoDecimal = new DecimalFormat("0.00");
	int count500=0, count200=0, count100=0, count50=0, count20=0, count10=0, count5=0, count2=0, count1=0, count_5=0;
	int temp500=0, temp200=0, temp100=0, temp50=0, temp20=0, temp10=0, temp5=0, temp2=0, temp1=0;
	float temp_5=0;
	public Corte(int id, String fechas, Conexion dato){
		super("Corte - Sistema Olvera");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		centro.setHorizontalAlignment(SwingConstants.CENTER);
		this.datos=dato;
		this.fecha=fechas;
		this.idUsuario=id;
		//Diseño de cada objeto--------------------------------------------
		lblCaja.setFont(new Font("Arial", 1, 25));
		signo500.setFont(new Font("Arial", 0, 17));
		signo200.setFont(new Font("Arial", 0, 17));
		signo100.setFont(new Font("Arial", 0, 17));
		signo50.setFont(new Font("Arial", 0, 17));
		signo20.setFont(new Font("Arial", 0, 17));
		signo10.setFont(new Font("Arial", 0, 17));
		signo5.setFont(new Font("Arial", 0, 17));
		signo2.setFont(new Font("Arial", 0, 17));
		signo1.setFont(new Font("Arial", 0, 17));
		signo_5.setFont(new Font("Arial", 0, 17));
		this.quinietos.setFont(new Font("Arial", 0, 17));
		this.txtQuinientos.setFont(new Font("Arial", 0, 17));
		txtQuinientos.setSelectedIndex(0);
		txtQuinientos.addItemListener(new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						$500 = Integer.parseInt(String.valueOf(txtQuinientos.getSelectedItem()))*500;
						if(count500==0){
							count500++;
							if(temp500==0){
								temp500=$500;
								efectivo+=$500;
							}
							else{
								if(temp500!=$500){
									efectivo-=temp500;
									efectivo+=$500;
									temp500=$500;
								}
								else{
									count500=0;
								}
							}
						}
						else{
							count500=0;
						}
						if(efectivo<0){
							lblPesosEntregar.setText(formatoDecimal.format(0));
						}
						else{
							lblPesosEntregar.setText(formatoDecimal.format(efectivo-vales-Float.parseFloat(lblPesosCaja.getText())));
						}
						lblPesosEfectivo.setText(formatoDecimal.format(efectivo));
					}
		});
		this.doscientos.setFont(new Font("Arial", 0, 17));
		this.doscientos.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtDoscientos.setFont(new Font("Arial", 0, 17));
		txtDoscientos.setSelectedIndex(0);
		txtDoscientos.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				$200 = Integer.parseInt(String.valueOf(txtDoscientos.getSelectedItem()))*200;
				if(count200==0){
					count200++;
					if(temp200==0){
						temp200=$200;
						efectivo+=$200;
					}
					else{
						if(temp200!=$200){
							efectivo-=temp200;
							efectivo+=$200;
							temp200=$200;
						}
						else{
							count200=0;
						}
					}
				}
				else{
					count200=0;
				}
				if(efectivo<0){
					lblPesosEntregar.setText(formatoDecimal.format(0));
				}
				else{
					lblPesosEntregar.setText(formatoDecimal.format(efectivo-vales-Float.parseFloat(lblPesosCaja.getText())));
				}
				lblPesosEfectivo.setText(formatoDecimal.format(efectivo));
			}
		});
		this.cien.setFont(new Font("Arial", 0, 17));
		this.cien.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtCien.setFont(new Font("Arial", 0, 17));
		txtCien.setSelectedIndex(0);
		txtCien.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				$100 = Integer.parseInt(String.valueOf(txtCien.getSelectedItem()))*100;
				if(count100==0){
					count100++;
					if(temp100==0){
						temp100=$100;
						efectivo+=$100;
					}
					else{
						if(temp100!=$100){
							efectivo-=temp100;
							efectivo+=$100;
							temp100=$100;
						}
						else{
							count100=0;
						}
					}
				}
				else{
					count100=0;
				}
				if(efectivo<0){
					lblPesosEntregar.setText(formatoDecimal.format(0));
				}
				else{
					lblPesosEntregar.setText(formatoDecimal.format(efectivo-vales-Float.parseFloat(lblPesosCaja.getText())));
				}
				lblPesosEfectivo.setText(formatoDecimal.format(efectivo));
			}
		});
		this.cincuenta.setFont(new Font("Arial", 0, 17));
		this.cincuenta.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtCincuenta.setFont(new Font("Arial", 0, 17));
		txtCincuenta.setSelectedIndex(0);
		txtCincuenta.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				$50 = Integer.parseInt(String.valueOf(txtCincuenta.getSelectedItem()))*50;
				if(count50==0){
					count50++;
					if(temp50==0){
						temp50=$50;
						efectivo+=$50;
					}
					else{
						if(temp50!=$50){
							efectivo-=temp50;
							efectivo+=$50;
							temp50=$50;
						}
						else{
							count50=0;
						}
					}
				}
				else{
					count50=0;
				}
				if(efectivo<0){
					lblPesosEntregar.setText(formatoDecimal.format(0));
				}
				else{
					lblPesosEntregar.setText(formatoDecimal.format(efectivo-vales-Float.parseFloat(lblPesosCaja.getText())));
				}
				lblPesosEfectivo.setText(formatoDecimal.format(efectivo));
			}
		});
		this.veinte.setFont(new Font("Arial", 0, 17));
		this.veinte.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtVeinte.setFont(new Font("Arial", 0, 17));
		txtVeinte.setSelectedIndex(0);
		txtVeinte.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				$20 = Integer.parseInt(String.valueOf(txtVeinte.getSelectedItem()))*20;
				if(count20==0){
					count20++;
					if(temp20==0){
						temp20=$20;
						efectivo+=$20;
					}
					else{
						if(temp20!=$20){
							efectivo-=temp20;
							efectivo+=$20;
							temp20=$20;
						}
						else{
							count20=0;
						}
					}
				}
				else{
					count20=0;
				}
				if(efectivo<0){
					lblPesosEntregar.setText(formatoDecimal.format(0));
				}
				else{
					lblPesosEntregar.setText(formatoDecimal.format(efectivo-vales-Float.parseFloat(lblPesosCaja.getText())));
				}
				lblPesosEfectivo.setText(formatoDecimal.format(efectivo));
			}
		});
		this.diez.setFont(new Font("Arial", 0, 17));
		this.diez.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtDiez.setFont(new Font("Arial", 0, 17));
		txtDiez.setSelectedIndex(0);
		txtDiez.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				$10 = Integer.parseInt(String.valueOf(txtDiez.getSelectedItem()))*10;
				if(count10==0){
					count10++;
					if(temp10==0){
						temp10=$10;
						efectivo+=$10;
					}
					else{
						if(temp10!=$10){
							efectivo-=temp10;
							efectivo+=$10;
							temp10=$10;
						}
						else{
							count10=0;
						}
					}
				}
				else{
					count10=0;
				}
				if(efectivo<0){
					lblPesosEntregar.setText(formatoDecimal.format(0));
				}
				else{
					lblPesosEntregar.setText(formatoDecimal.format(efectivo-vales-Float.parseFloat(lblPesosCaja.getText())));
				}
				lblPesosEfectivo.setText(formatoDecimal.format(efectivo));
			}
		});
		this.cinco.setFont(new Font("Arial", 0, 17));
		this.cinco.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtCinco.setFont(new Font("Arial", 0, 17));
		txtCinco.setSelectedIndex(0);
		txtCinco.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				$5 = Integer.parseInt(String.valueOf(txtCinco.getSelectedItem()))*5;
				if(count5==0){
					count5++;
					if(temp5==0){
						temp5=$5;
						efectivo+=$5;
					}
					else{
						if(temp5!=$5){
							efectivo-=temp5;
							efectivo+=$5;
							temp5=$5;
						}
						else{
							count5=0;
						}
					}
				}
				else{
					count5=0;
				}
				if(efectivo<0){
					lblPesosEntregar.setText(formatoDecimal.format(0));
				}
				else{
					lblPesosEntregar.setText(formatoDecimal.format(efectivo-vales-Float.parseFloat(lblPesosCaja.getText())));
				}
				lblPesosEfectivo.setText(formatoDecimal.format(efectivo));
			}
		});
		this.dos.setFont(new Font("Arial", 0, 17));
		this.dos.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtDos.setFont(new Font("Arial", 0, 17));
		txtDos.setSelectedIndex(0);
		txtDos.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				$2 = Integer.parseInt(String.valueOf(txtDos.getSelectedItem()))*2;
				if(count2==0){
					count2++;
					if(temp2==0){
						temp2=$2;
						efectivo+=$2;
						lblPesosEfectivo.setText(formatoDecimal.format(efectivo));
						lblPesosEntregar.setText(formatoDecimal.format(efectivo-vales-Float.parseFloat(lblPesosCaja.getText())));
					}
					else{
						if(temp2!=$2){
							efectivo-=temp2;
							efectivo+=$2;
							lblPesosEfectivo.setText(formatoDecimal.format(efectivo));
							lblPesosEntregar.setText(formatoDecimal.format(efectivo-vales-Float.parseFloat(lblPesosCaja.getText())));
							temp2=$2;
						}
						else{
							count2=0;
						}
					}
				}
				else{
					count2=0;
				}
				if(efectivo<0){
					lblPesosEntregar.setText(formatoDecimal.format(0));
				}
				else{
					lblPesosEntregar.setText(formatoDecimal.format(efectivo-vales-Float.parseFloat(lblPesosCaja.getText())));
				}
				lblPesosEfectivo.setText(formatoDecimal.format(efectivo));
			}
		});
		this.uno.setFont(new Font("Arial", 0, 17));
		this.uno.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtUno.setFont(new Font("Arial", 0, 17));
		txtUno.setSelectedIndex(0);
		txtUno.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				$1 = Integer.parseInt(String.valueOf(txtUno.getSelectedItem()))*1;
				if(count1==0){
					count1++;
					if(temp1==0){
						temp1=$1;
						efectivo+=$1;
						lblPesosEfectivo.setText(formatoDecimal.format(efectivo));
						lblPesosEntregar.setText(formatoDecimal.format(efectivo-vales-Float.parseFloat(lblPesosCaja.getText())));
					}
					else{
						if(temp1!=$1){
							efectivo-=temp1;
							efectivo+=$1;
							lblPesosEfectivo.setText(formatoDecimal.format(efectivo));
							lblPesosEntregar.setText(formatoDecimal.format(efectivo-vales-Float.parseFloat(lblPesosCaja.getText())));
							temp1=$1;
						}
						else{
							count1=0;
						}
					}
				}
				else{
					count1=0;
				}
				if(efectivo<0){
					lblPesosEntregar.setText(formatoDecimal.format(0));
				}
				else{
					lblPesosEntregar.setText(formatoDecimal.format(efectivo-vales-Float.parseFloat(lblPesosCaja.getText())));
				}
				lblPesosEfectivo.setText(formatoDecimal.format(efectivo));
			}
		});
		this.centavos.setFont(new Font("Arial", 0, 17));
		this.centavos.setHorizontalAlignment(SwingConstants.RIGHT);
		this.txtcentavos.setFont(new Font("Arial", 0, 17));
		txtcentavos.setSelectedIndex(0);
		txtcentavos.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				$_50 =  (float) (Float.parseFloat(String.valueOf(txtcentavos.getSelectedItem()))*.50);
				if(count_5==0){
					count_5++;
					if(temp_5==0){
						temp_5=$_50;
						efectivo+=$_50;
						lblPesosEfectivo.setText(formatoDecimal.format(efectivo));
						lblPesosEntregar.setText(formatoDecimal.format(efectivo-vales-Float.parseFloat(lblPesosCaja.getText())));
					}
					else{
						if(temp_5!=$_50){
							efectivo-=temp_5;
							efectivo+=$_50;
							lblPesosEfectivo.setText(formatoDecimal.format(efectivo));
							lblPesosEntregar.setText(formatoDecimal.format(efectivo-vales-Float.parseFloat(lblPesosCaja.getText())));
							temp_5=$_50;
						}
						else{
							count_5=0;
						}
					}
				}
				else{
					count_5=0;
				}
				if(efectivo<0){
					lblPesosEntregar.setText(formatoDecimal.format(0));
				}
				else{
					lblPesosEntregar.setText(formatoDecimal.format(efectivo-vales-Float.parseFloat(lblPesosCaja.getText())));
				}
				lblPesosEfectivo.setText(formatoDecimal.format(efectivo));
			}
		});
		this.lblEfectivo.setFont(new Font("Arial", 1, 20));
		this.lblPesosEfectivo.setFont(new Font("Arial", 1, 20));
		this.lblPesosEfectivo.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblVentas.setFont(new Font("Arial", 1, 20));
		this.lblPesosVentas.setFont(new Font("Arial", 1, 20));
		this.lblPesosVentas.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblVales.setFont(new Font("Arial", 1, 20));
		this.lblPesosVales.setFont(new Font("Arial", 1, 20));
		this.lblPesosVales.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblCaja.setFont(new Font("Arial", 1, 20));
		this.lblPesosCaja.setFont(new Font("Arial", 1, 20));
		this.lblPesosCaja.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblEntregar.setFont(new Font("Arial", 1, 25));
		this.lblSignoEntregar.setFont(new Font("Arial", 1, 25));
		this.lblPesosEntregar.setFont(new Font("Arial", 1, 25));
		this.lblPesosEntregar.setHorizontalAlignment(SwingConstants.RIGHT);
		this.btnAceptar.setFont(new Font("Arial", 1, 18));
		this.btnAceptar.addMouseListener(new MouseAdapter()
				{
					@SuppressWarnings("deprecation")
					public void mousePressed(MouseEvent m){
							try{
								int fila=1;
								int columna=4;
								String fCorte =fecha();
								File guardar = new File(System.getProperty("user.home") + "\\Desktop");
								//File src = new File("src");
								File archivo = new File("CORTE.xlsx");
								FileInputStream archivoEntrada = new FileInputStream(archivo);
								Workbook libro = WorkbookFactory.create(archivoEntrada);
								org.apache.poi.ss.usermodel.CellStyle derecha = libro.createCellStyle();
								derecha.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
								org.apache.poi.ss.usermodel.CellStyle centro = libro.createCellStyle();
								centro.setAlignment(XSSFCellStyle.ALIGN_CENTER);
								Sheet hoja = libro.getSheetAt(0);
								Row filas = hoja.getRow((short) fila);
								org.apache.poi.ss.usermodel.CellStyle style = libro.createCellStyle();
								filas.getCell(columna).setCellValue("CORTE DEL DIA:");
								columna++;
								filas.createCell(columna).setCellValue(fCorte);
								filas.getCell(columna).setCellStyle(centro);
								//style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
								//filas.setCellValue(fCorte);
								if(ordenes.length>0){
									columna+=3;
									filas.getCell(columna).setCellValue(ordenes[0][1].toString());
									columna+=2;
									filas.getCell(columna).setCellValue(ordenes[tableOrden.getRowCount()-1][1].toString());
								}
								fila++;
								columna=8;
								if(notas.length>0){
									filas = hoja.getRow((short) fila);
									filas.getCell(columna).setCellValue(notas[0][1].toString());
									columna+=2;
									filas.getCell(columna).setCellValue(notas[tableNotas.getRowCount()-1][1].toString());
								}
								int count=0, count1=0, row=0;
								fila=5;
								columna=0;
								while(row<tableOrden.getRowCount()){
									count1=0;
									filas=hoja.createRow((short) fila);
									while(count1<8){
										if(columna==1){
											columna++;
										}
										if(columna==3){
											columna++;
										}
										if(columna==8){
											filas.createCell(columna).setCellStyle(derecha);
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
								while(row<tableNotas.getRowCount()){
									filas=hoja.createRow((short) fila);
									count1=0;
									while(count1<8){
										if(columna==2){	
											columna+=2;
										}
										if(columna==8){
											filas.createCell(columna).setCellStyle(derecha);
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
								//-----------------AQUI TERMINA DE ESCRIBIR LAS NOTAS Y ORDENES-----------------------------------
								//------------------COMIENZA A ESCRIBIR GASTOS Y DENOMINACIONES-----------------------------------
								columna=7;
								filas = hoja.createRow((short) fila);
								filas.createCell(columna).setCellValue("Total-General:");
								columna+=2;
								filas.createCell(columna).setCellValue(lblPesosVentas.getText());
								filas.getCell(columna).setCellStyle(derecha);
								fila++;
								int filaDinero = fila;
								columna=4;
								filas = hoja.createRow((short) fila);
								filas.createCell(columna).setCellValue("GASTOS");
								fila++;
								row=0;
								while(row<tableGastos.getRowCount()){
									filas = hoja.createRow((short) fila);
									filas.createCell(columna).setCellValue(gastos[row][0].toString());
									columna++;
									filas.createCell(columna).setCellStyle(derecha);
									filas.getCell(columna).setCellValue(gastos[row][1].toString());
									row++;
									columna=4;
									fila++;
								}
								//---------------------------COMIENZAN A ESCRIBIR LOS GASTOS-----------------------------------------------
								filas = hoja.createRow((short) fila);
								filas.createCell(columna).setCellValue("Total de Gastos");
								//fila++;
								//filas = hoja.getRow((short) fila);
								columna++;
								filas.createCell(columna).setCellValue(formatoDecimal.format(vales));
								filas.getCell(columna).setCellStyle(derecha);
								int filaFinal = fila;
								//--------------------------------TERMINA LOS GASTOS---------------------------------------------------------
								//----------------------------------COMIENZA A ESCRIBIR EL DINERO--------------------------------------------
								filaDinero++;
								if(filaDinero<=filaFinal){
									filas = hoja.getRow((short) filaDinero);
								}
								else{
									filas = hoja.createRow((short) filaDinero);
								}
								columna=6;
								filas.createCell(columna).setCellStyle(derecha);
								filas.getCell(columna).setCellValue(500.00);
								columna++;
								filas.createCell(columna).setCellValue("x");
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue(String.valueOf(txtQuinientos.getSelectedItem()));
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue($500);
								filas.getCell(columna).setCellStyle(centro);
								filaDinero++;
								if(filaDinero<=filaFinal){
									filas = hoja.getRow((short) filaDinero);
								}
								else{
									filas = hoja.createRow((short) filaDinero);
								}
								columna=6;
								filas.createCell(columna).setCellStyle(derecha);
								filas.getCell(columna).setCellValue(200.00);
								columna++;
								filas.createCell(columna).setCellValue("x");
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue(String.valueOf(txtDoscientos.getSelectedItem()));
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue($200);
								filas.getCell(columna).setCellStyle(centro);
								filaDinero++;
								if(filaDinero<=filaFinal){
									filas = hoja.getRow((short) filaDinero);
								}
								else{
									filas = hoja.createRow((short) filaDinero);
								}
								columna=6;
								filas.createCell(columna).setCellStyle(derecha);
								filas.getCell(columna).setCellValue(100.00);
								columna++;
								filas.createCell(columna).setCellValue("x");
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue(String.valueOf(txtCien.getSelectedItem()));
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue($100);
								filas.getCell(columna).setCellStyle(centro);
								filaDinero++;
								if(filaDinero<=filaFinal){
									filas = hoja.getRow((short) filaDinero);
								}
								else{
									filas = hoja.createRow((short) filaDinero);
								}
								columna=6;
								filas.createCell(columna).setCellStyle(derecha);
								filas.getCell(columna).setCellValue(50.00);
								columna++;
								filas.createCell(columna).setCellValue("x");
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue(String.valueOf(txtCincuenta.getSelectedItem()));
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue($50);
								filas.getCell(columna).setCellStyle(centro);
								filaDinero++;
								if(filaDinero<=filaFinal){
									filas = hoja.getRow((short) filaDinero);
								}
								else{
									filas = hoja.createRow((short) filaDinero);
								}
								columna=6;
								filas.createCell(columna).setCellStyle(derecha);
								filas.getCell(columna).setCellValue(20.00);
								columna++;
								filas.createCell(columna).setCellValue("x");
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue(String.valueOf(txtVeinte.getSelectedItem()));
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue($20);
								filas.getCell(columna).setCellStyle(centro);
								filaDinero++;
								if(filaDinero<=filaFinal){
									filas = hoja.getRow((short) filaDinero);
								}
								else{
									filas = hoja.createRow((short) filaDinero);
								}
								columna=6;
								filas.createCell(columna).setCellStyle(derecha);
								filas.getCell(columna).setCellValue(10.00);
								columna++;
								filas.createCell(columna).setCellValue("x");
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue(String.valueOf(txtDiez.getSelectedItem()));
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue($10);
								filas.getCell(columna).setCellStyle(centro);
								filaDinero++;
								if(filaDinero<=filaFinal){
									filas = hoja.getRow((short) filaDinero);
								}
								else{
									filas = hoja.createRow((short) filaDinero);
								}
								columna=6;
								filas.createCell(columna).setCellStyle(derecha);
								filas.getCell(columna).setCellValue(5.00);
								columna++;
								filas.createCell(columna).setCellValue("x");
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue(String.valueOf(txtCinco.getSelectedItem()));
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue($5);
								filas.getCell(columna).setCellStyle(centro);
								filaDinero++;
								if(filaDinero<=filaFinal){
									filas = hoja.getRow((short) filaDinero);
								}
								else{
									filas = hoja.createRow((short) filaDinero);
								}
								columna=6;
								filas.createCell(columna).setCellStyle(derecha);
								filas.getCell(columna).setCellValue(2.00);
								columna++;
								filas.createCell(columna).setCellValue("x");
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue(String.valueOf(txtDos.getSelectedItem()));
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue($2);
								filas.getCell(columna).setCellStyle(centro);
								filaDinero++;
								if(filaDinero<=filaFinal){
									filas = hoja.getRow((short) filaDinero);
								}
								else{
									filas = hoja.createRow((short) filaDinero);
								}
								columna=6;
								filas.createCell(columna).setCellStyle(derecha);
								filas.getCell(columna).setCellValue(1.00);
								columna++;
								filas.createCell(columna).setCellValue("x");
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue(String.valueOf(txtUno.getSelectedItem()));
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue($1);
								filas.getCell(columna).setCellStyle(centro);
								filaDinero++;
								if(filaDinero<=filaFinal){
									filas = hoja.getRow((short) filaDinero);
								}
								else{
									filas = hoja.createRow((short) filaDinero);
								}
								columna=6;
								filas.createCell(columna).setCellStyle(derecha);
								filas.getCell(columna).setCellValue(0.50);
								columna++;
								filas.createCell(columna).setCellValue("x");
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue(String.valueOf(txtcentavos.getSelectedItem()));
								filas.getCell(columna).setCellStyle(centro);
								columna++;
								filas.createCell(columna).setCellValue($_50);
								filas.getCell(columna).setCellStyle(centro);
								filaDinero++;
								if(filaDinero<=filaFinal){
									filas = hoja.getRow((short) filaDinero);
								}
								else{
									filas = hoja.createRow((short) filaDinero);
								}
								columna-=2;
								filas.createCell(columna).setCellValue("Total en efectivo");
								columna+=2;
								filas.createCell(columna).setCellValue(lblPesosEfectivo.getText());
								filas.getCell(columna).setCellStyle(derecha);
								filaDinero++;
								if(filaDinero<=filaFinal){
									filas = hoja.getRow((short) filaDinero);
								}
								else{
									filas = hoja.createRow((short) filaDinero);
								}
								columna-=2;
								filas.createCell(columna).setCellValue("Vales");
								columna+=2;
								filas.createCell(columna).setCellValue(formatoDecimal.format(vales));
								filas.getCell(columna).setCellStyle(derecha);
								filaDinero++;
								if(filaDinero<=filaFinal){
									filas = hoja.getRow((short) filaDinero);
								}
								else{
									filas = hoja.createRow((short) filaDinero);
								}
								columna-=2;
								filas.createCell(columna).setCellValue("Total venta del día");
								columna+=2;
								filas.createCell(columna).setCellValue(lblPesosVentas.getText());
								filas.getCell(columna).setCellStyle(derecha);
								filaDinero++;
								if(filaDinero<=filaFinal){
									filas = hoja.getRow((short) filaDinero);
								}
								else{
									filas = hoja.createRow((short) filaDinero);
								}
								columna-=2;
								filas.createCell(columna).setCellValue("Caja chia");
								columna+=2;
								filas.createCell(columna).setCellValue(lblPesosCaja.getText());
								filas.getCell(columna).setCellStyle(derecha);
								filaDinero++;
								if(filaDinero<=filaFinal){
									filas = hoja.getRow((short) filaDinero);
								}
								else{
									filas = hoja.createRow((short) filaDinero);
								}
								columna-=2;
								filas.createCell(columna).setCellValue("Menos Vales");
								columna+=2;
								filas.createCell(columna).setCellValue(lblPesosVales.getText());
								filas.getCell(columna).setCellStyle(derecha);
								filaDinero++;
								if(filaDinero<=filaFinal){
									filas = hoja.getRow((short) filaDinero);
								}
								else{
									filas = hoja.createRow((short) filaDinero);
								}
								columna-=2;
								filas.createCell(columna).setCellValue("Sumas iguales");
								columna+=2;
								filas.createCell(columna).setCellValue(formatoDecimal.format(Float.parseFloat(lblPesosEfectivo.getText())-(Float.parseFloat(lblPesosCaja.getText())+Float.parseFloat(lblPesosVales.getText()))));
								filas.getCell(columna).setCellStyle(derecha);
								//-----------------------------TERMINA ESRIBIR EL DINERO-----------------------------------------------------
								
								FileOutputStream salida = new FileOutputStream(new File(guardar+"\\Corte-"+fecha+".xlsx"));
								libro.write(salida);
								salida.close();		
								optionPane("Listo", "LISTO", JOptionPane.INFORMATION_MESSAGE);
								salir();
							}
							catch(Exception e){
								optionPane(e.toString(),"ERROR", JOptionPane.ERROR_MESSAGE);
							}
					}
				}
				);
		/*btnAceptar.addActionListener(ActionListener(){
						
					}
				);*/
		this.panelGastos.setBorder(new TitledBorder(null, "Gastos", 0, 0, new Font("Arial", 1, 25)));
		this.panelOrdenes.setBorder(new TitledBorder(null, "Ordenes de compra", 0, 0, new Font("Arial", 1, 25)));
		this.panleNotas.setBorder(new TitledBorder(null, "Notas", 0, 0, new Font("Arial", 1, 25)));
		llenarNotas();
		llenarOrden();
		llenarGastos();
		try{
			ventas = Float.parseFloat(datos.getDato("SELECT SUM(monto) FROM trabajos t  RIGHT JOIN pagos p ON t.id = p.idTrabajos WHERE p.fechaAlta='"+fecha+"' AND t.cancelacion=0"));
			this.lblPesosVentas.setText(formatoDecimal.format(ventas));
		}catch(Exception e){}
		try{
			vales = Float.parseFloat(datos.getDato("SELECT SUM(monto) FROM gastos WHERE fecha='"+fecha+"'"));
			this.lblPesosVales.setText(formatoDecimal.format(vales));
		}catch(Exception e){}
		lblPesosCaja.setText(datos.getDato("SELECT monto FROM caja WHERE fecha='"+fecha+"'"));
		//Panel notas------------------------------------------------------------------
		GroupLayout layoutNotas = new GroupLayout(panleNotas);
		layoutNotas.setAutoCreateContainerGaps(true);
		layoutNotas.setAutoCreateGaps(true);
		layoutNotas.setHorizontalGroup(
					layoutNotas.createSequentialGroup()
					.addComponent(scrollNotas)
				);
		layoutNotas.setVerticalGroup(
					layoutNotas.createParallelGroup()
					.addComponent(scrollNotas)
				);
		panleNotas.setLayout(layoutNotas);
		//Panel orden-------------------------------------------------------------------
		GroupLayout layoutOrden = new GroupLayout(panelOrdenes);
		layoutOrden.setAutoCreateContainerGaps(true);
		layoutOrden.setAutoCreateGaps(true);
		layoutOrden.setHorizontalGroup(
					layoutOrden.createSequentialGroup()
					.addComponent(scrollOrden)
				);
		layoutOrden.setVerticalGroup(
					layoutOrden.createParallelGroup()
					.addComponent(scrollOrden)
				);
		panelOrdenes.setLayout(layoutOrden);
		//Panel gastos---------------------------------------------------------------------
		GroupLayout layoutGastos = new GroupLayout(panelGastos);
		layoutGastos.setAutoCreateContainerGaps(true);
		layoutGastos.setAutoCreateGaps(true);
		layoutGastos.setHorizontalGroup(
					layoutGastos.createSequentialGroup()
					.addComponent(scrollGastos)
				);
		layoutGastos.setVerticalGroup(
					layoutGastos.createParallelGroup()
					.addComponent(scrollGastos)
				);
		panelGastos.setLayout(layoutGastos);
		//Panel principal----------------------------------------------------------------------
		GroupLayout main = new GroupLayout(this.getContentPane());
		main.setAutoCreateContainerGaps(true);
		main.setAutoCreateGaps(true);
		main.setHorizontalGroup(
					main.createSequentialGroup()
					.addGroup(
								main.createParallelGroup()
								.addComponent(panleNotas, 950, 950, 950)
								.addComponent(panelOrdenes, 950, 950, 950)
								.addComponent(panelGastos, 950, 950, 950)
							)
					.addGroup(
								main.createParallelGroup()
								.addComponent(caja)
								.addGroup(
											main.createSequentialGroup()
											.addComponent(signo500)
											.addComponent(quinietos,60,60,60)
											.addComponent(this.txtQuinientos,80,80,80)
										)
								.addGroup(
										main.createSequentialGroup()
										.addComponent(signo200)
										.addComponent(doscientos,60,60,60)
										.addComponent(this.txtDoscientos,80,80,80)
									)
								.addGroup(
										main.createSequentialGroup()
										.addComponent(signo100)
										.addComponent(cien,60,60,60)
										.addComponent(this.txtCien,80,80,80)
									)
								.addGroup(
										main.createSequentialGroup()
										.addComponent(signo50)
										.addComponent(cincuenta,60,60,60)
										.addComponent(this.txtCincuenta,80,80,80)
									)
								.addGroup(
										main.createSequentialGroup()
										.addComponent(signo20)
										.addComponent(veinte,60,60,60)
										.addComponent(this.txtVeinte,80,80,80)
									)
								.addGroup(
										main.createSequentialGroup()
										.addComponent(signo10)
										.addComponent(diez,60,60,60)
										.addComponent(this.txtDiez,80,80,80)
									)
								.addGroup(
										main.createSequentialGroup()
										.addComponent(signo5)
										.addComponent(cinco,60,60,60)
										.addComponent(this.txtCinco,80,80,80)
									)
								.addGroup(
										main.createSequentialGroup()
										.addComponent(signo2)
										.addComponent(dos,60,60,60)
										.addComponent(this.txtDos,80,80,80)
									)
								.addGroup(
										main.createSequentialGroup()
										.addComponent(signo1)
										.addComponent(uno,60,60,60)
										.addComponent(this.txtUno,80,80,80)
									)
								.addGroup(
										main.createSequentialGroup()
										.addComponent(signo_5)
										.addComponent(centavos,60,60,60)
										.addComponent(this.txtcentavos,80,80,80)
									)
								.addGroup(
											main.createSequentialGroup()
											.addComponent(lblEfectivo, 120, 120, 120)
											.addComponent(lblPesosEfectivo, 100, 100, 100)
										)
								.addGroup(
											main.createSequentialGroup()
											.addComponent(this.lblVentas, 120, 120, 120)
											.addComponent(this.lblPesosVentas, 100, 100, 100)
										)
								.addGroup(
										main.createSequentialGroup()
										.addComponent(this.lblVales, 120, 120, 120)
										.addComponent(this.lblPesosVales, 100, 100, 100)
									)
								.addGroup(
										main.createSequentialGroup()
										.addComponent(this.lblCaja, 120, 120, 120)
										.addComponent(this.lblPesosCaja, 100, 100, 100)
									)
								.addComponent(this.lblEntregar)
								.addGroup(
											main.createSequentialGroup()
											.addComponent(lblSignoEntregar)
											.addComponent(lblPesosEntregar, 130, 130, 130)
										)
								.addComponent(btnAceptar, 150,150,150)
							)
				);
		main.setVerticalGroup(
				main.createParallelGroup()
				.addGroup(
							main.createSequentialGroup()
							.addComponent(panleNotas, 200, 200, 200)
							.addComponent(panelOrdenes, 200, 200, 200)
							.addComponent(panelGastos, 200, 200, 200)
						)
				.addGroup(
							main.createSequentialGroup()
							.addComponent(caja)
							.addGroup(
										main.createParallelGroup()
										.addComponent(signo500)
										.addComponent(quinietos)
										.addComponent(this.txtQuinientos, 25,25,25)
									)
							.addGroup(
									main.createParallelGroup()
									.addComponent(signo200)
									.addComponent(doscientos)
									.addComponent(this.txtDoscientos, 25,25,25)
								)
							.addGroup(
									main.createParallelGroup()
									.addComponent(signo100)
									.addComponent(cien)
									.addComponent(this.txtCien, 25,25,25)
								)
							.addGroup(
									main.createParallelGroup()
									.addComponent(signo50)
									.addComponent(cincuenta)
									.addComponent(this.txtCincuenta, 25,25,25)
								)
							.addGroup(
									main.createParallelGroup()
									.addComponent(signo20)
									.addComponent(veinte)
									.addComponent(this.txtVeinte, 25,25,25)
								)
							.addGroup(
									main.createParallelGroup()
									.addComponent(signo10)
									.addComponent(diez)
									.addComponent(this.txtDiez, 25,25,25)
								)
							.addGroup(
									main.createParallelGroup()
									.addComponent(signo5)
									.addComponent(cinco)
									.addComponent(this.txtCinco, 25,25,25)
								)
							.addGroup(
									main.createParallelGroup()
									.addComponent(signo2)
									.addComponent(dos)
									.addComponent(this.txtDos, 25,25,25)
								)
							.addGroup(
									main.createParallelGroup()
									.addComponent(signo1)
									.addComponent(uno)
									.addComponent(this.txtUno, 25,25,25)
								)
							.addGroup(
									main.createParallelGroup()
									.addComponent(signo_5)
									.addComponent(centavos)
									.addComponent(this.txtcentavos, 25,25,25)
								)
							.addGroup(
										main.createParallelGroup()
										.addComponent(lblEfectivo)
										.addComponent(lblPesosEfectivo)
									)
							.addGroup(
										main.createParallelGroup()
										.addComponent(this.lblVentas)
										.addComponent(this.lblPesosVentas)
									)
							.addGroup(
									main.createParallelGroup()
									.addComponent(this.lblVales)
									.addComponent(this.lblPesosVales)
								)
							.addGroup(
									main.createParallelGroup()
									.addComponent(this.lblCaja)
									.addComponent(this.lblPesosCaja)
								)
							.addComponent(this.lblEntregar)
							.addGroup(
										main.createParallelGroup()
										.addComponent(lblSignoEntregar)
										.addComponent(lblPesosEntregar)
									)
							.addComponent(btnAceptar, 50, 50, 50)
						)
			);
		this.setLayout(main);
		pack();
	}
	private void vistaNotas(){
		//vista tabla notas-----------------------------------------------------------
		tableNotas.getColumnModel().getColumn(0).setMinWidth(90);
		tableNotas.getColumnModel().getColumn(0).setMaxWidth(90);
		tableNotas.getColumnModel().getColumn(0).setCellRenderer(centro);
		tableNotas.getColumnModel().getColumn(1).setMinWidth(90);
		tableNotas.getColumnModel().getColumn(1).setMaxWidth(90);
		tableNotas.getColumnModel().getColumn(1).setCellRenderer(centro);
		tableNotas.getColumnModel().getColumn(3).setMinWidth(90);
		tableNotas.getColumnModel().getColumn(3).setMaxWidth(90);
		tableNotas.getColumnModel().getColumn(3).setCellRenderer(centro);
		tableNotas.getColumnModel().getColumn(4).setMinWidth(90);
		tableNotas.getColumnModel().getColumn(4).setMaxWidth(90);
		tableNotas.getColumnModel().getColumn(4).setCellRenderer(centro);
		tableNotas.getColumnModel().getColumn(5).setMinWidth(90);
		tableNotas.getColumnModel().getColumn(5).setMaxWidth(90);
		tableNotas.getColumnModel().getColumn(5).setCellRenderer(centro);
		tableNotas.getColumnModel().getColumn(6).setMinWidth(90);
		tableNotas.getColumnModel().getColumn(6).setMaxWidth(90);
		tableNotas.getColumnModel().getColumn(6).setCellRenderer(centro);
		tableNotas.setFont(new Font("Arial", 0, 14));
		JTableHeader th = tableNotas.getTableHeader();
		th.setFont(new Font("Arial", 0, 14));
		tableNotas.setTableHeader(th);
		scrollNotas.getViewport().add(tableNotas);
	}
	private void vistaOrdenes(){
		//Vista tabla ordenes------------------------------------------------------------
		tableOrden.getColumnModel().getColumn(0).setMinWidth(90);
		tableOrden.getColumnModel().getColumn(0).setMaxWidth(90);
		tableOrden.getColumnModel().getColumn(0).setCellRenderer(centro);
		tableOrden.getColumnModel().getColumn(1).setMinWidth(90);
		tableOrden.getColumnModel().getColumn(1).setMaxWidth(90);
		tableOrden.getColumnModel().getColumn(1).setCellRenderer(centro);
		tableOrden.getColumnModel().getColumn(3).setMinWidth(90);
		tableOrden.getColumnModel().getColumn(3).setMaxWidth(90);
		tableOrden.getColumnModel().getColumn(3).setCellRenderer(centro);
		tableOrden.getColumnModel().getColumn(4).setMinWidth(90);
		tableOrden.getColumnModel().getColumn(4).setMaxWidth(90);
		tableOrden.getColumnModel().getColumn(4).setCellRenderer(centro);
		tableOrden.getColumnModel().getColumn(5).setMinWidth(90);
		tableOrden.getColumnModel().getColumn(5).setMaxWidth(90);
		tableOrden.getColumnModel().getColumn(5).setCellRenderer(centro);
		tableOrden.getColumnModel().getColumn(6).setMinWidth(90);
		tableOrden.getColumnModel().getColumn(6).setMaxWidth(90);
		tableOrden.getColumnModel().getColumn(6).setCellRenderer(centro);
		tableOrden.setFont(new Font("Arial", 0, 14));
		JTableHeader th1=tableOrden.getTableHeader();
		th1.setFont(new Font("Arial", 0, 14));
		tableOrden.setTableHeader(th1);
		scrollOrden.getViewport().add(tableOrden);
	}
	private void vistaGastos(){
		//vista tabla Gastos------------------------------------------------------------
		tableGastos.getColumnModel().getColumn(0).setMinWidth(110);
		tableGastos.getColumnModel().getColumn(0).setMaxWidth(110);
		tableGastos.getColumnModel().getColumn(0).setCellRenderer(centro);
		tableGastos.getColumnModel().getColumn(1).setCellRenderer(centro);
		tableGastos.getColumnModel().getColumn(2).setCellRenderer(centro);
		tableGastos.getColumnModel().getColumn(2).setMinWidth(130);
		tableGastos.getColumnModel().getColumn(2).setMaxWidth(130);
		tableGastos.setFont(new Font("Arial", 0, 14));
		JTableHeader th2=tableGastos.getTableHeader();
		th2.setFont(new Font("Arial", 0, 14));
		tableGastos.setTableHeader(th2);
		scrollGastos.getViewport().add(tableGastos);
	}
	private void llenarNotas(){
		tableNotas.setModel(datos.getModelo("SELECT t.fechaAlta, t.numero, concat(nombres, ' ', apellidoP, ' ', apellidoM), subtotal, (SELECT monto FROM pagos WHERE id=(SELECT MIN(id) FROM pagos WHERE idTrabajos=t.id)), p.debe, p.monto FROM clientes c INNER JOIN trabajos t ON c.id = t.idCliente RIGHT JOIN pagos p ON t.id = p.idTrabajos WHERE p.fechaAlta='"+fecha+"' AND orden=0 AND t.cancelacion=0 ORDER BY t.numero ASC", titulosN, 7));
		notas = datos.getObjeto("SELECT t.fechaAlta, t.numero, concat(nombres, ' ', apellidoP, ' ', apellidoM), subtotal, (SELECT monto FROM pagos WHERE id=(SELECT MIN(id) FROM pagos WHERE idTrabajos=t.id)), p.debe, p.monto, t.observaciones FROM clientes c INNER JOIN trabajos t ON c.id = t.idCliente RIGHT JOIN pagos p ON t.id = p.idTrabajos WHERE p.fechaAlta='"+fecha+"' AND orden=0 AND t.cancelacion=0 ORDER BY t.numero ASC", tableNotas.getRowCount(), 8);
		vistaNotas();
	}
	private void llenarOrden(){
		tableOrden.setModel(datos.getModelo("SELECT t.fechaAlta, t.numero, concat(nombres, ' ', apellidoP, ' ', apellidoM), subtotal, (SELECT monto FROM pagos WHERE id=(SELECT MIN(id) FROM pagos WHERE idTrabajos=t.id)), p.debe, p.monto FROM clientes c INNER JOIN trabajos t ON c.id = t.idCliente RIGHT JOIN pagos p ON t.id = p.idTrabajos WHERE p.fechaAlta='"+fecha+"' AND orden=1 AND t.cancelacion=0 ORDER BY  t.numero ASC", titulosC, 7));
		ordenes = datos.getObjeto("SELECT t.fechaAlta, t.numero, concat(nombres, ' ', apellidoP, ' ', apellidoM), subtotal, (SELECT monto FROM pagos WHERE id=(SELECT MIN(id) FROM pagos WHERE idTrabajos=t.id)), p.debe, p.monto, t.observaciones FROM clientes c INNER JOIN trabajos t ON c.id = t.idCliente RIGHT JOIN pagos p ON t.id = p.idTrabajos WHERE p.fechaAlta='"+fecha+"' AND orden=1 AND t.cancelacion=0 ORDER BY  t.numero ASC", tableOrden.getRowCount(), 8);
		vistaOrdenes();
	}
	private void llenarGastos(){
		tableGastos.setModel(datos.getModelo("SELECT fecha, descripcion, monto	FROM gastos WHERE fecha='"+fecha+"'", titulosGastos, 3));
		int filas = tableGastos.getRowCount();
		gastos = datos.getObjeto("SELECT descripcion, monto	FROM gastos WHERE fecha='"+fecha+"'", filas, 2);
		vistaGastos();
	}
	private void optionPane(String mensaje, String titulo, int tipo){
		JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
	}
	private void salir(){
		this.dispose();
	}
	public String fecha(){
		String fecha="";
		Calendar calendario = Calendar.getInstance();
		SimpleDateFormat fAno = new SimpleDateFormat("yyyy");
		int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
		int mes = calendario.get(Calendar.MONTH);
		int dia = calendario.get(Calendar.DAY_OF_MONTH);
		String year = fAno.format(calendario.getTime());
		if(diaSemana==0){
			fecha ="DOM "+dia;
		}
		else if(diaSemana==1){
			fecha ="LUN "+dia;
		}
		else if(diaSemana==2){
			fecha ="MAR "+dia;
		}
		else if(diaSemana==3){
			fecha ="MIE "+dia;
		}
		else if(diaSemana==4){
			fecha ="JUE "+dia;
		}
		else if(diaSemana==5){
			fecha ="VIE "+dia;
		}
		else if(diaSemana==6){
			fecha ="SAB "+dia;
		}
		if(mes==0){
			fecha+="-ENE-";
		}
		else if(mes==1){
			fecha+="-FEB-";
		}
		else if(mes==2){
			fecha+="-MAR-";
		}
		else if(mes==3){
			fecha+="-ABR-";
		}
		else if(mes==4){
			fecha+="-MAY-";
		}
		else if(mes==5){
			fecha+="-JUN-";
		}
		else if(mes==6){
			fecha+="-JUL-";
		}
		else if(mes==7){
			fecha+="-AGO-";
		}
		else if(mes==8){
			fecha+="-SEP-";
		}
		else if(mes==9){
			fecha+="-OCT-";
		}
		else if(mes==10){
			fecha+="-NOV-";
		}
		else if(mes==11){
			fecha+="-DIC-";
		}
		fecha+=year;
		
		return fecha;
	}
	public void actionPerformed(ActionEvent arg0) {}
}
