/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package käyttöliittymä;



import Data.Opiskelija;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import tietokanta.Tietovarasto;

/**
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 *
 * @author s1900013
 */

                      
    
public class JFrPoisto extends JFrAbstraktiLisaysJaMuut    {
    
    public JFrPoisto(Tietovarasto varasto){
			super(varasto);
			btNappi.setText("Poista");
			this.setTitle("Poista opiskelija");
                        btMuuta.setVisible(false);
                        tfId.setEditable(true);
			tfEtunimi.setEditable(false);
			tfSukunimi.setEditable(false);
			tfOpintoviikot.setEditable(false);
                        tfIka.setEditable(false);
    
    }
  @Override
	protected void kasitteleNappi() {
		try{
			int opiskelijaID = Integer.parseInt(tfId.getText());
                        Opiskelija haettu = rekisteri.haeOpiskelija(opiskelijaID);
			if (haettu == null){
				JOptionPane.showMessageDialog(this, "Opiskelijaa ei l�ytynyt", "Virhe",JOptionPane.INFORMATION_MESSAGE);
			}else{
                            
                                tfEtunimi.setText(haettu.getEtunimi());
				tfSukunimi.setText(haettu.getSukunimi());
				tfOpintoviikot.setText(""+haettu.getOpintoviikot());
                                tfIka.setText(""+haettu.getIka());
                                rekisteri.poistaOpiskelija(opiskelijaID); 
                                JOptionPane.showMessageDialog(this, "Opiskelija poistettu", "Poisto",JOptionPane.INFORMATION_MESSAGE);
                        }
                        
                        
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, e, "Virhe",JOptionPane.ERROR_MESSAGE);
		}
    }
          
  }

    
 


