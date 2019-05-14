package käyttöliittymä;

import javax.swing.JOptionPane;

import Data.Opiskelija;

import tietokanta.Tietovarasto;
import Data.Opiskelija;


public class JFrLisays extends JFrAbstraktiLisaysJaMuut{

	//konstruktori
	public JFrLisays(Tietovarasto varasto){
		super(varasto);
                btMuuta.setVisible(false);
		btNappi.setText("Talleta");
		this.setTitle("Lisää opiskelija");
	}
	@Override
	protected void kasitteleNappi() {
		try{
			int opiskelijaID = Integer.parseInt(tfId.getText());
			String etunimi = tfEtunimi.getText();
			String sukunimi = tfSukunimi.getText();
			int opintoviikot = Integer.parseInt(tfOpintoviikot.getText());
                        int ika = Integer.parseInt(tfIka.getText());
			
			if(etunimi.isEmpty() || sukunimi.isEmpty() || tfId.getText().isEmpty() || tfOpintoviikot.getText().isEmpty() || tfIka.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "Tiedoissa puutteita", "Tallennus",JOptionPane.INFORMATION_MESSAGE);
			}else{
				Opiskelija tyyppi = new Opiskelija(opiskelijaID, etunimi, sukunimi, opintoviikot,ika );
				rekisteri.lisaaOpiskelija(tyyppi);
				JOptionPane.showMessageDialog(this, "Tiedot tallennettu", "Tallennus",JOptionPane.INFORMATION_MESSAGE);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(this, e, "Virhe",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	

	
	

}
