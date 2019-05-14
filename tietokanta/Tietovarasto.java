package tietokanta;

import Data.Kurssi;
import java.sql.*;

import Data.Opiskelija;
import Data.Osallistuu;
import java.util.ArrayList;
import java.util.List;

public class Tietovarasto {

    private String ajuri = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/oppilastietokanta";
    private String kayttajatunnus = "root";
    private String salasana = "";

    private String sqlLisaaOpiskelija = "INSERT INTO Opiskelija(opiskelijaID, etunimi, sukunimi, "
            + "opintoviikot,ika) VALUES (?,?,?,?,?)";

    private String sqlHaeOpiskelija = "SELECT * FROM opiskelija WHERE opiskelijaID = ?";

    private String sqlPoistaOpiskelija = "DELETE  FROM opiskelija WHERE opiskelijaID = ?";

    private String sqlMuutaOpiskelija = "UPDATE opiskelija SET  etunimi=?, sukunimi=?, "
            + "opintoviikot=?,ika=? WHERE opiskelijaID = ?";

    private String sqlHaeKaikki = "SELECT opiskelijaID, etunimi, sukunimi, opintoviikot, ika FROM opiskelija";
    
    private String sqlHaeKurssi = "SELECT KurssiID, opintopisteet, aihe, opettaja, nimi FROM Kurssi WHERE KurssiID = ?";

    private String sqlLisaaOsallistuminen = "INSERT INTO Osallistuu (KurssiID, opiskelijaID) VALUES (?,?) ";
    
    private String sqlHaeKurssinOpiskelijat = "SELECT Opiskelija.opiskelijaID, etunimi, sukunimi,opintoviikot, ika, Kurssi.kurssiID FROM opiskelija "
            + "JOIN osallistuu ON osallistuu.opiskelijaID = opiskelija.opiskelijaID JOIN Kurssi ON Osallistuu.KurssiID = Kurssi.KurssiID WHERE Kurssi.KurssiID = ?";
            
    public Opiskelija haeOpiskelija(int opiskelijaID) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }

        PreparedStatement haeOpiskelija = null;
        ResultSet tulos = null;
        try {
            haeOpiskelija = yhteys.prepareStatement(sqlHaeOpiskelija);
            haeOpiskelija.setInt(1, opiskelijaID);
            tulos = haeOpiskelija.executeQuery();
            if (tulos.next()) {
                return new Opiskelija(tulos.getInt(1), tulos.getString(2), tulos.getString(3), tulos.getInt(4), tulos.getInt(5));
            } else {
                throw new Exception("Opiskelijaa ei löydy");
            }

        } catch (SQLException sqle) {
            throw new Exception("Hakuvirhe", sqle);
        } finally {
            YhteydenHallinta.suljeYhteys(yhteys);
        }

    }

    public void lisaaOpiskelija(Opiskelija uusiOpiskelija) throws Exception {
        int opiskelijaID = uusiOpiskelija.getOpiskelijaID();

        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement opiskelijanLisays = null;
        try {
            opiskelijanLisays = yhteys.prepareStatement(sqlLisaaOpiskelija);
            opiskelijanLisays.setInt(1, uusiOpiskelija.getOpiskelijaID());// ensimmäinen kysymysmerkki
            opiskelijanLisays.setString(2, uusiOpiskelija.getEtunimi());// toinen kysymysmerkki
            opiskelijanLisays.setString(3, uusiOpiskelija.getSukunimi());// jne
            opiskelijanLisays.setInt(4, uusiOpiskelija.getOpintoviikot());// jne
            opiskelijanLisays.setInt(5, uusiOpiskelija.getIka());
            opiskelijanLisays.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new Exception("Opiskelijan lisäys ei onnistu.", sqle);
        }
    }

    public Kurssi haeKurssi(int KurssiID) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        } 
    
      PreparedStatement haeKurssi = null;
        ResultSet tulos = null;
        try {
            haeKurssi = yhteys.prepareStatement(sqlHaeKurssi);
            haeKurssi.setInt(1, KurssiID);
            tulos = haeKurssi.executeQuery();
            if (tulos.next()) {
                return new Kurssi(tulos.getInt(1),tulos.getInt(2), tulos.getString(3), tulos.getString(4), tulos.getString(5));
            } else {
                throw new Exception("Kurssia ei löydy");
            }

        } catch (SQLException sqle) {
            throw new Exception("Hakuvirhe", sqle);
        } finally {
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }
    
    
    public void poistaOpiskelija(int opiskelijaID) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki", e);
        }

        PreparedStatement poistaOpiskelija = null;

        try {
            poistaOpiskelija = yhteys.prepareStatement(sqlPoistaOpiskelija);
            poistaOpiskelija.setInt(1, opiskelijaID);
            poistaOpiskelija.executeUpdate();

        } catch (SQLException sqle) {
            throw new Exception("Opiskelijan poistaminen ei onnistunut", sqle);
        }

    }

    public void muutaOpiskelija(Opiskelija muutettuOpiskelija) throws Exception {
        int opiskelijaID = muutettuOpiskelija.getOpiskelijaID();

        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki", e);
        }
        PreparedStatement muutaOpiskelijanTietoja = null;
        try {
            muutaOpiskelijanTietoja = yhteys.prepareStatement(sqlMuutaOpiskelija);
            muutaOpiskelijanTietoja.setString(1, muutettuOpiskelija.getEtunimi());
            muutaOpiskelijanTietoja.setString(2, muutettuOpiskelija.getSukunimi());
            muutaOpiskelijanTietoja.setInt(3, muutettuOpiskelija.getOpintoviikot());
            muutaOpiskelijanTietoja.setInt(4, muutettuOpiskelija.getIka());
            muutaOpiskelijanTietoja.setInt(5, muutettuOpiskelija.getOpiskelijaID());
            muutaOpiskelijanTietoja.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Henkilön tietojen muuttaminen ei onnistunut", sqle);
        }
    }

    public List<Opiskelija> haeKaikkiOpiskelijat() throws Exception {
        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki", e);
        }
        PreparedStatement haeKaikkiHenkilot
                = null;
        ResultSet tulos
                = null;
        try {
            List<Opiskelija> apulista = new ArrayList<Opiskelija>();
            haeKaikkiHenkilot = yhteys.prepareStatement(sqlHaeKaikki);
            tulos = haeKaikkiHenkilot.executeQuery();
            while (tulos.next()) {
                apulista.add(new Opiskelija(tulos.getInt(1),
                        tulos.getString(2),
                        tulos.getString(3),
                        tulos.getInt(4),
                        tulos.getInt(5)));
            }
            return apulista;
        } catch (SQLException sqle) {
            throw new Exception("Henkilön haku epäonnistui", sqle);

        }
    }
  
   
 /*    public List<Kurssi> haeKaikkiKurssit() throws Exception {
        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki", e);
        }
        PreparedStatement haeKaikkiKurssit
                = null;
        ResultSet tulos
                = null;
        try {
            List<Kurssi> apulista = new ArrayList<Kurssi>();
            haeKaikkiKurssit = yhteys.prepareStatement(sqlHaeKurssi);
            tulos = haeKaikkiKurssit.executeQuery();
            while (tulos.next()) {
                apulista.add(new Kurssi(tulos.getInt(1),
                        tulos.getInt(2),
                        tulos.getString(3),
                        tulos.getString(4),
                        tulos.getString(5)));
                        
                        
            }
            return apulista;
        } catch (SQLException sqle) {
            throw new Exception("Kurssien haku epäonnistui", sqle);
 
        }
    
     
     }
     */
   
      public void lisaaOsallistuminen(Osallistuu uusiOsallistuminen) throws Exception {
        int osallistuuID = uusiOsallistuminen.getOsallistuuID();

        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement lisaaOsallistuminen = null;
        try {
            lisaaOsallistuminen= yhteys.prepareStatement(sqlLisaaOsallistuminen);
            lisaaOsallistuminen.setInt(1, uusiOsallistuminen.getKurssiID());// ensimmäinen kysymysmerkki
            lisaaOsallistuminen.setInt(2, uusiOsallistuminen.getOpiskelijaID());// toinen kysymysmerkki
            
            lisaaOsallistuminen.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new Exception("Opiskelijan lisäys ei onnistu.", sqle);
        }
    }

     public List<Opiskelija> haeKurssinOpiskelijat(int KurssiID) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        } 
    
      PreparedStatement HaeKurssinOpiskelijat = null;
        ResultSet tulos = null;
      try {
            List<Opiskelija> apulista = new ArrayList<Opiskelija>();
            HaeKurssinOpiskelijat = yhteys.prepareStatement(sqlHaeKurssinOpiskelijat);
            HaeKurssinOpiskelijat.setInt(1, KurssiID);
            tulos = HaeKurssinOpiskelijat.executeQuery();
            while (tulos.next()) {
                apulista.add(new Opiskelija(tulos.getInt(1),
                        tulos.getString(2),
                        tulos.getString(3),
                        tulos.getInt(4),
                        tulos.getInt(5)));
            }
            return apulista;
        } catch (SQLException sqle) {
            throw new Exception("Kurssin haku epäonnistui", sqle);
        }
}

}
