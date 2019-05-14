/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package käyttöliittymä;

import Data.Kurssi;
import Data.Opiskelija;
import Data.Osallistuu;
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
import javax.swing.JTextField;
import tietokanta.Tietovarasto;

/**
 *
 * @author s1900013
 */
public class JFrKurssilleLisays extends JFrame{
    private JPanel paPohja = new JPanel(new BorderLayout());
    private JPanel paNapit = new JPanel(new FlowLayout(FlowLayout.TRAILING));
    private JPanel paYla = new JPanel(new GridLayout(2, 1));
    
    private JLabel lbId = new JLabel("OpiskelijaID");
    protected JTextField tfId = new JTextField();
    private JLabel lbKID = new JLabel("KurssiID");
    protected JTextField tfKID = new JTextField();
    protected JButton btPeruuta = new JButton("Tyhjennä");
    protected JButton btNappi = new JButton("Lisää");
    
    protected Tietovarasto rekisteri;
    
    
     public JFrKurssilleLisays(Tietovarasto varasto){
    	this.rekisteri = varasto;
    	asetteleKomponentit();
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	this.setLocation(50, 100);
    	this.setSize(300,190);
    }
     
     
      private void asetteleKomponentit(){
    	paYla.add(lbId);
    	paYla.add(tfId);
    	paYla.add(lbKID);
    	paYla.add(tfKID);
    	
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


      }

	protected void kasitteleNappi() {
		try{
			int opiskelijaID = Integer.parseInt(tfId.getText());
			int KurssiID = Integer.parseInt(tfKID.getText());
			
			
			if(tfId.getText().isEmpty() || tfKID.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "Tiedoissa puutteita", "Tallennus",JOptionPane.INFORMATION_MESSAGE);
			}else{
				Osallistuu tyyppi = new Osallistuu (opiskelijaID, KurssiID);
				rekisteri.lisaaOsallistuminen(tyyppi);
				JOptionPane.showMessageDialog(this, "Tiedot tallennettu", "Tallennus",JOptionPane.INFORMATION_MESSAGE);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(this, e, "Virhe",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
    
  
	
    private void tyhjenna(){
    	tfId.setText("");
        tfKID.setText("");

    }
    
}
        
	/**
´	 * @param args
	 */
	
