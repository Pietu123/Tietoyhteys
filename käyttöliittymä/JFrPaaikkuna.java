package käyttöliittymä;

import Data.Opiskelija;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import tietokanta.Tietovarasto;

public class JFrPaaikkuna extends JFrame {

    private JPanel paPohja = new JPanel(new GridLayout(8, 1));

    private JButton btLisaa = new JButton("Lisää opiskelija");
    private JButton btHaeOpiskelija = new JButton("Hae opiskelijan tiedot");
    private JButton btPoista = new JButton("Poista opiskelija");
    private JButton btMuuta = new JButton("Muuta opiskelijan tiedot");
    private JButton btHaeKaikki = new JButton("Hae kaikki opiskelijat");
    private JButton btHaeKurssi = new JButton("Hae kurssi");
    private JButton btLisaa2 = new JButton("Lisää opiskelija kurssille");
    private JButton btKurssinOpiskelijat = new JButton("Hae kurssin opiskelijat");

    private Tietovarasto rekisteri = new Tietovarasto();

    public JFrPaaikkuna() {
        this.setTitle("Pääikkuna");
        this.setLocation(100, 300);
        this.setSize(200, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        asetteleKomponentit();
        this.setVisible(true);
    }

    private void asetteleKomponentit() {
        paPohja.add(btLisaa);
        paPohja.add(btHaeOpiskelija);
        paPohja.add(btPoista);
        paPohja.add(btMuuta);
        paPohja.add(btHaeKaikki);
        paPohja.add(btHaeKurssi);
        paPohja.add(btLisaa2);
        paPohja.add(btKurssinOpiskelijat);

        this.add(paPohja);

        btLisaa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lisaaOpiskelija();
            }
        });

        btHaeOpiskelija.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                haeOpiskelija();
            }
        });
        btPoista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                poistaOpiskelija();
            }
        });
        btMuuta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                muutaOpiskelija();
            }
        });

        btHaeKaikki.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                haeKaikkiOpiskelijat();
            }

        });

        btHaeKurssi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                haeKurssi();

            }
        });
    btLisaa2.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            lisaaKurssille();
        }
    
    });
    btKurssinOpiskelijat.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            kurssinOpiskelijat();
        }
    
    });
    }
    //asetteleKomponentit lopetus
    private void lisaaOpiskelija() {
        new JFrLisays(rekisteri).setVisible(true);
    }

    private void haeOpiskelija() {
        new JFrHaku(rekisteri).setVisible(true);
    }

    private void poistaOpiskelija() {
        new JFrPoisto(rekisteri).setVisible(true);
    }

    private void muutaOpiskelija() {
        new JFrMuutos(rekisteri).setVisible(true);
    }

    private void haeKaikkiOpiskelijat() {
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

    private void haeKurssi() {
        new JFrHaeKurssi(rekisteri).setVisible(true);

    }
    
    private void lisaaKurssille() {
        new JFrKurssilleLisays(rekisteri).setVisible(true);
    }
    
    private void kurssinOpiskelijat () {
        new JFrHaeKurssinOpiskelijat(rekisteri).setVisible(true);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        JFrPaaikkuna ikkuna = new JFrPaaikkuna();

    }

}
