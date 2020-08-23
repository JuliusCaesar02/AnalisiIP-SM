import java.awt.EventQueue;
import java.util.Arrays;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
public class Gui {
	
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	Sub sub = new Sub();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Caesar subnet calculator");
		frame.setBounds(100, 100, 452, 377);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		textField = new JTextField();
		textField.setBounds(10, 29, 135, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField_1 = new JTextField();
		textField_1.setBounds(289, 29, 135, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		JLabel lblNewLabel = new JLabel("Internet Protocol");
		lblNewLabel.setBounds(10, 11, 135, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Subnet Mask");
		lblNewLabel_1.setBounds(289, 11, 135, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Calcola");
		tglbtnNewToggleButton.setBounds(174, 29, 89, 20);
		frame.getContentPane().add(tglbtnNewToggleButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 60, 414, 267);
		frame.getContentPane().add(textArea);
		
		tglbtnNewToggleButton.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e) {
			String auxString;
			auxString = new String();
			String ip = textField.getText();
			sub.setIp (ip);																										
			sub.decon(sub.setIp(ip));																						
		    sub.setClasse();
		    String sm = textField_1.getText();
		    sub.setSm (sm);		
		    sub.decon(sub.setIp(sm));																							//Separa la stringa in un'array intero con una posizione per byte

		    
		    sub.setBinaryBit(sub.decon(sub.setIp(ip)));		
		    textArea.setText("Indirizzo IP binario: " +sub.getBinaryBit(sub.decon((sub.setIp(ip)))) +"\n");
		    
		    sub.setBinaryBit(sub.decon((sub.setIp(sm))));																	
		    textArea.append("Indirizzo SM binario: " +sub.getBinaryBit(sub.decon((sub.setIp(sm)))) +"\n");
		    
		    sub.decon(sub.setIp(ip));
		    textArea.append("1- La classe è " +sub.setClasse() +"\n");
		    
		    if(sub.setByteCrit() != 0) {
		    	 textArea.append("2- Il byte critico è il " +(sub.setByteCrit()+1) +" (" +sub.decon(sm)[sub.setByteCrit()] +")" +"\n");
			}
			else textArea.append("2- Il byte critico non è presente (" +sub.decon(sm)[3] +")");
		    
		    textArea.append("3- Il CIDR è /" +sub.setCidr() +"\n");
		    
	    	textArea.append("4- I bit di rete sono " +sub.setNetBit() +"\n");
	    	
	    	sub.setSubBit();
	    	if(sub.setSubBit() < 0) {
	    		textArea.append("5- ERRORE! I bit di sotto rete sono negativi! " +sub.setSubBit() +"\n");
				System.exit(0);
			}
			else textArea.append("5- I bit di sotto rete sono " +sub.setSubBit() +"\n");
	    	 
	    	sub.setHostBit();	
	    	if(sub.setHostBit() < 0) {
	    		textArea.append("6- ERRORE! I bit degli host sono negativi! " +sub.setHostBit() +"\n");
				System.exit(0);
			}
			else textArea.append("6- I bit degli host sono " +sub.setHostBit() +"\n");
		    
    		textArea.append("7- Ci possono essere " +sub.setHostESubPerNet(sub.setHostBit(), 2) +" host in ogni subnet (" +(sub.setHostESubPerNet(sub.setHostBit(), 0)) +")" +"\n");

    		textArea.append("8- Ci possono essere " +sub.setHostESubPerNet(sub.setSubBit(), 0) +" subnet nella rete" +"\n");

    		textArea.append("9- Il magic number è " +sub.setMn() +"\n");
    		
    		sub.setNetIp();
    		auxString = Arrays.toString(sub.setNetIp()); 
    		textArea.append("10- Indirizzo di rete " +auxString.replace(", ",".").replace("[","").replace("]","") +"\n");

    		sub.setBroadIp();
    		auxString = Arrays.toString(sub.setBroadIp()); 
    		textArea.append("11- Indirizzo di broadcast " +auxString.replace(", ",".").replace("[","").replace("]","") +"\n");

    		textArea.append("12- Primo indirizzo host " +sub.setRange(sub.setNetIp(),1).replace(", ",".").replace("[","").replace("]","") +"\n");
    		textArea.append("       Ultimo indirizzo host " +sub.setRange(sub.setBroadIp(),-1).replace(", ",".").replace("[","").replace("]","") +"\n");

    		textArea.append("13- L'IP fa parte della subnet " +sub.setSubNet() +"\n");


		    //textArea.append(sub.decon((sub.setIp(ip)))[0]);
		    //lblNewLabel_2.appe
		   // aux = sub.decon(sub.setIp(sm));
		    //auxString = Arrays.toString(aux);
		    //lblNewLabel_2.setText(auxString.replace(", ",".").replace("[","").replace("]",""));
		}
		});
	}
}
