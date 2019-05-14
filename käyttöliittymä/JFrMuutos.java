/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package käyttöliittymä;

import Data.Opiskelija;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import tietokanta.Tietovarasto;

/**
 *
 * @author s1900013
 */
public class JFrMuutos extends JFrAbstraktiLisaysJaMuut {

    public JFrMuutos(Tietovarasto varasto) {
        super(varasto);
        btNappi.setText("Hae");
        this.setTitle("Hae opiskelijan tiedot");
        btMuuta.setText("Muuta");
        this.setTitle("Muuta opiskelijan tiedot");
        tfId.setEditable(true);
        tfEtunimi.setEditable(true);
        tfSukunimi.setEditable(true);
        tfOpintoviikot.setEditable(true);
        tfIka.setEditable(true);
        
        btMuuta.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			kasitteleMuuta();
                }
        }); 
                 
                 }  
    
        @Override            
        protected void kasitteleNappi() {
        try {
            int opiskelijaID = Integer.parseInt(tfId.getText());
            Opiskelija haettu = rekisteri.haeOpiskelija(opiskelijaID);

            if (haettu == null) {
                JOptionPane.showMessageDialog(this, "Opiskelijaa ei löytynyt", "Virhe", JOptionPane.INFORMATION_MESSAGE);
            } else {

                tfEtunimi.setText(haettu.getEtunimi());
                tfSukunimi.setText(haettu.getSukunimi());
                tfOpintoviikot.setText("" + haettu.getOpintoviikot());
                tfIka.setText("" + haettu.getIka());

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Virhe", JOptionPane.ERROR_MESSAGE);
        }
    }
  
   private void kasitteleMuuta() {
        try {
            int opiskelijaID = Integer.parseInt(tfId.getText());
            String etunimi = tfEtunimi.getText();
            String sukunimi = tfSukunimi.getText();
            int opintoviikot = Integer.parseInt(tfOpintoviikot.getText());
            int ika = Integer.parseInt(tfIka.getText());
            Opiskelija haettu = rekisteri.haeOpiskelija(opiskelijaID);
            if (haettu == null) {
                JOptionPane.showMessageDialog(this, "Opiskelijaa ei löytynyt", "Virhe", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Opiskelija tyyppi = new Opiskelija(opiskelijaID, etunimi, sukunimi, opintoviikot, ika);
                rekisteri.muutaOpiskelija(tyyppi);
                JOptionPane.showMessageDialog(this, "Tiedot tallennettu", "Tallennus", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Virhe", JOptionPane.ERROR_MESSAGE);
        }
    }
}
