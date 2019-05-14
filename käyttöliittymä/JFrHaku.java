package käyttöliittymä;

import javax.swing.JOptionPane;

import Data.Opiskelija;

import tietokanta.Tietovarasto;
import Data.Opiskelija;


public class JFrHaku extends JFrAbstraktiLisaysJaMuut{

	//konstruktori
		public JFrHaku(Tietovarasto varasto){
			super(varasto);
			btNappi.setText("Hae");
                        btMuuta.setVisible(false);
			this.setTitle("Hae opiskelija");
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
			if(haettu == null){
				JOptionPane.showMessageDialog(this, "Opiskelijaa ei l�ytynyt", "Virhe",JOptionPane.INFORMATION_MESSAGE);
			}else{
				tfEtunimi.setText(haettu.getEtunimi());
				tfSukunimi.setText(haettu.getSukunimi());
				tfOpintoviikot.setText(""+haettu.getOpintoviikot());
                                tfIka.setText(""+haettu.getIka());
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, e, "Virhe",JOptionPane.ERROR_MESSAGE);
		}
	}
        
    

}
