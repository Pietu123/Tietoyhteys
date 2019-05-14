/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author s1900013
 */
public class Kurssi implements Comparable<Kurssi> {

    private int kurssiID;
    private int opintopisteet;
    private String aihe;
    private String opettaja;
    private String nimi;
    private ArrayList<Opiskelija> opiskelijat;
    
    
 //konstruktori 1 l�hetet��n kaikki tiedot
    /**
     * @param kurssiID
     * @param opiskelijaID
     * @param opintopisteet
     * @param aihe
     * @param opettaja
     * @param nimi
     */
    
   public Kurssi(int kurssiID, int opintopisteet, String aihe, String opettaja, String nimi  ) {
       this.kurssiID = kurssiID;
       this.opintopisteet=  opintopisteet;
       this.aihe = aihe;
       this.opettaja = opettaja;
       this.nimi=nimi;
       this.opiskelijat = new ArrayList<Opiskelija>();
   }      
   
   
    public Kurssi(int opintopisteet, String aihe, String opettaja, String nimi) {
        this.opintopisteet = 0;
        this.aihe = aihe;
        this.opettaja = opettaja;
        this.nimi= nimi;
        
           
               }

    public void setKurssiID(int kurssiID) {
        this.kurssiID = kurssiID;
    }

    public void setOpintopisteet(int opintopisteet) {
        this.opintopisteet = opintopisteet;
    }

    public void setAihe(String aihe) {
        this.aihe = aihe;
    }

    public void setOpettaja(String opettaja) {
        this.opettaja = opettaja;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setOpiskelijat(ArrayList<Opiskelija> opiskelijat) {
        this.opiskelijat = opiskelijat;
    }

    public int getKurssiID() {
        return kurssiID;
    }

    public int getOpintopisteet() {
        return opintopisteet;
    }

    public String getAihe() {
        return aihe;
    }

    public String getOpettaja() {
        return opettaja;
    }

    public String getNimi() {
        return nimi;
    }

    public ArrayList<Opiskelija> getOpiskelijat() {
        return opiskelijat;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.kurssiID;
        hash = 97 * hash + this.opintopisteet;
        hash = 97 * hash + Objects.hashCode(this.aihe);
        hash = 97 * hash + Objects.hashCode(this.opettaja);
        hash = 97 * hash + Objects.hashCode(this.nimi);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kurssi other = (Kurssi) obj;
        if (this.kurssiID != other.kurssiID) {
            return false;
        }
  
        if (this.opintopisteet != other.opintopisteet) {
            return false;
        }
        if (!Objects.equals(this.aihe, other.aihe)) {
            return false;
        }
        if (!Objects.equals(this.opettaja, other.opettaja)) {
            return false;
        }
        if (!Objects.equals(this.nimi, other.nimi)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Kurssi{" + "kurssiID=" + kurssiID + ", opintopisteet=" + opintopisteet + ", aihe=" + aihe + ", opettaja=" + opettaja + ", nimi=" + nimi + ", opiskelijat=" + opiskelijat + '}';
    }

 
    
 @Override
    public int compareTo(Kurssi olio) {
        Collator apu = Collator.getInstance();
        apu.setStrength(Collator.PRIMARY);
        int tulos = apu.compare(this.nimi, olio.nimi);
        if (tulos != 0) {
            return tulos;
        } else {
            return apu.compare(this.opettaja, olio.opettaja);
        }
    }

 public static String listaMerkkijonona(List<Kurssi> lista) {
        String merkit = "";
        for (Kurssi uusi : lista) {
            merkit += uusi + "\n";
        }
        return merkit;
    }

 public void lisaaOpiskelija(Opiskelija Uusi){
     opiskelijat.add(Uusi);
 }


}