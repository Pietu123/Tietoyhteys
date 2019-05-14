/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package käyttöliittymä;


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
public class JFrHaeKurssinOpiskelijat extends JFrame {
    

    private JPanel paPohja = new JPanel(new BorderLayout());
    private JPanel paNapit = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    private JPanel paYla = new JPanel(new GridLayout(1, 1));
    
    private JLabel lbKID = new JLabel("KurssiID");
    protected JTextField tfKID = new JTextField();
    protected JButton btNappi = new JButton("Hae");
    
    protected Tietovarasto rekisteri;
    
    
     public JFrHaeKurssinOpiskelijat(Tietovarasto varasto){
    	this.rekisteri = varasto;
    	asetteleKomponentit();
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	this.setLocation(50, 100);
    	this.setSize(300,190);
    }
     
     
      private void asetteleKomponentit(){
    	
    	paYla.add(lbKID);
    	paYla.add(tfKID);
    	
    	paNapit.add(btNappi);       
 	
                
    	paPohja.add(paYla, BorderLayout.PAGE_START);
    	paPohja.add(paNapit, BorderLayout.PAGE_END);
    	
    	this.add(paPohja);
        
        btNappi.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			kasitteleNappi();
    		}
    	});
}


    protected void kasitteleNappi() {
        JTextArea haeKurssillaOpiskelevat = new JTextArea(10, 20);
        int KurssiID= Integer.parseInt(tfKID.getText());
        try {
            for (Opiskelija alkio : rekisteri.haeKurssinOpiskelijat(KurssiID)) {
                haeKurssillaOpiskelevat.append(alkio.toString());
                haeKurssillaOpiskelevat.append("\n");
            }
            JOptionPane.showMessageDialog(this, new JScrollPane(haeKurssillaOpiskelevat), "Kaikki henkilöt",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Virhe", JOptionPane.ERROR_MESSAGE);
        }
    }

}