/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package käyttöliittymä;

import Data.Kurssi;
import Data.Opiskelija;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import tietokanta.Tietovarasto;

/**
 *
 * @author s1900013
 */
public class JFrHaeKurssi extends JFrame{
	

    private JPanel paPohja = new JPanel(new BorderLayout());
    private JPanel paNapit = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    private JPanel paYla = new JPanel(new GridLayout(5, 1));
    
    private JLabel lbId = new JLabel("KurssiID");
    protected JTextField tfId = new JTextField();
    private JLabel lbOpintopiste = new JLabel("Opintopisteet");
    protected JTextField tfOpintopiste = new JTextField();
    private JLabel lbAihe = new JLabel("Aihe");
    protected JTextField tfAihe = new JTextField();
    private JLabel lbOpettaja = new JLabel("Opettaja");
    protected JTextField tfOpettaja = new JTextField();
    private JLabel lbNimi = new JLabel("Nimi");
    protected JTextField tfNimi = new JTextField();
    protected JButton btNappi = new JButton("Hae");
    protected JButton btPeruuta = new JButton("Tyhjennä");
   // protected JButton btHae = new JButton ("Hae");
    
    
    protected Tietovarasto rekisteri;
	
    //konstruktori
    public JFrHaeKurssi(Tietovarasto varasto){
    	this.rekisteri = varasto;
    	asetteleKomponentit();
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	this.setLocation(50, 100);
    	this.setSize(300,190);
    }
    
    private void asetteleKomponentit(){
    	paYla.add(lbId);
    	paYla.add(tfId);
    	paYla.add(lbOpintopiste);
    	paYla.add(tfOpintopiste);
    	paYla.add(lbAihe);
    	paYla.add(tfAihe);
    	paYla.add(lbOpettaja);
        paYla.add(tfOpettaja);
        paYla.add(lbNimi);
        paYla.add(tfNimi);
    	
    	paNapit.add(btNappi);       
    	paNapit.add(btPeruuta);
    	
                
    	paPohja.add(paYla, BorderLayout.PAGE_START);
    	paPohja.add(paNapit, BorderLayout.PAGE_END);
    	
    	this.add(paPohja);
    	
    	btNappi.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			kasitteleNappi();
    		}
    	});
    	
    	btPeruuta.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			tyhjenna();
    		}
    	});
        
        
   
       
    }//asetteleKomponentit loppu
    
   
	protected void kasitteleNappi() {
		try{
			int KurssiID= Integer.parseInt(tfId.getText());
			Kurssi haettu = rekisteri.haeKurssi(KurssiID);
			if(haettu == null){
				JOptionPane.showMessageDialog(this, "Opiskelijaa ei l�ytynyt", "Virhe",JOptionPane.INFORMATION_MESSAGE);
			}else{
				tfOpintopiste.setText(""+haettu.getOpintopisteet());
				tfAihe.setText(haettu.getAihe());
				tfOpettaja.setText(haettu.getOpettaja());
                                tfNimi.setText(haettu.getNimi());
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, e, "Virhe",JOptionPane.ERROR_MESSAGE);
		}
	}
        
    
  
	
    private void tyhjenna(){
    	tfId.setText("");
    	tfOpintopiste.setText("");
    	tfAihe.setText("");
    	tfOpettaja.setText("");
        tfNimi.setText("");
    }
	/**
	 * @param args
	 */
	

}

    
    
    
    
    
    
    
    
    
    
    
 /*   private JPanel paPohja = new JPanel(new GridLayout(3, 1));
    private JPanel paNapit = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    private JPanel paYla = new JPanel(new GridLayout(3, 1));    		
    
    private JLabel lbId = new JLabel("KurssiID");
    protected JTextField tfId = new JTextField();
    
    protected JButton btNappi = new JButton();
    protected JButton btPeruuta = new JButton("Tyhjennä");
    protected JButton btHae = new JButton ("Hae");
    protected JButton btMuuta = new JButton("Muuta");    
    protected Tietovarasto rekisteri;
    
    public JFrHaeKurssi(Tietovarasto varasto){
    	this.rekisteri = varasto;
    	asetteleKomponentit();
        this.setTitle("Hae kurssin tiedot");
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	this.setLocation(50, 100);
    	this.setSize(320,190);
    }
    
     private void asetteleKomponentit(){
         paYla.add(lbId);
         paYla.add(tfId);
         
        paNapit.add(btNappi);       
    	paNapit.add(btPeruuta);
        btNappi.setText("Hae");
        //this.setTitle("Hae kurssin tiedot");
        paPohja.add(paYla, BorderLayout.PAGE_START);
    	paPohja.add(paNapit, BorderLayout.PAGE_END);
    	
    	this.add(paPohja); 
        
     }

     

	

    protected void kasitteleNappi() {
        JTextArea kaikkiKurssit = new JTextArea(10, 20);
        try {
            for (Kurssi alkio : rekisteri.haeKaikkiKurssit()) {
                kaikkiKurssit.append(alkio.toString());
                kaikkiKurssit.append("\n");
            }
            JOptionPane.showMessageDialog(this, new JScrollPane(kaikkiKurssit), "Kaikki kurssit",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Virhe", JOptionPane.ERROR_MESSAGE);
        }
    }

}
*/