/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package käyttöliittymä;

import Data.Opiskelija;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import tietokanta.Tietovarasto;

/**
 *
 * @author s1900013
 */
public class JFrKaikkiHaku extends JFrAbstraktiLisaysJaMuut {

    public JFrKaikkiHaku(Tietovarasto varasto) {
        super(varasto);
        btMuuta.setVisible(false);
        btNappi.setText("Hae kaikki");
        this.setTitle("Hae kaikki opiskelijat");
        tfId.setVisible(false);
        tfEtunimi.setVisible(false);
        tfSukunimi.setVisible(false);
        tfOpintoviikot.setVisible(false);
        tfIka.setVisible(false);

    }

    @Override
    protected void kasitteleNappi() {
        JTextArea kaikkiHenkilot = new JTextArea(10, 20);
        try {
            for (Opiskelija alkio : rekisteri.haeKaikkiOpiskelijat()) {
                kaikkiHenkilot.append(alkio.toString());
                kaikkiHenkilot.append("\n");
            }
            JOptionPane.showMessageDialog(this, new JScrollPane(kaikkiHenkilot), "Kaikki henkilöt",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Virhe", JOptionPane.ERROR_MESSAGE);
        }
    }
}
