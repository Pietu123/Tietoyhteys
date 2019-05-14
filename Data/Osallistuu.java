/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.text.Collator;
import java.util.List;

/**
 *
 * @author s1900013
 */
public class Osallistuu implements Comparable<Osallistuu>  {
    
    private int osallistuuID;        
    private int kurssiID;
    private int opiskelijaID;
    
public Osallistuu (int osallistuuID, int opiskelijaID, int kurssiID) {
    
    this.osallistuuID = osallistuuID;
    this.kurssiID = kurssiID;
    this.opiskelijaID = opiskelijaID;
    
    
}  

public Osallistuu (int opiskelijaID, int kurssiID){
         this.kurssiID = kurssiID;
         this.opiskelijaID = opiskelijaID;
    
    
}




    public int getOsallistuuID() {
        return osallistuuID;
    }

    public void setOsallistuuID(int osallistuuID) {
        this.osallistuuID = osallistuuID;
    }

    public int getKurssiID() {
        return kurssiID;
    }

    public void setKurssiID(int kurssiID) {
        this.kurssiID = kurssiID;
    }

    public int getOpiskelijaID() {
        return opiskelijaID;
    }

    public void setOpiskelijaID(int opiskelijaID) {
        this.opiskelijaID = opiskelijaID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.osallistuuID;
        hash = 17 * hash + this.kurssiID;
        hash = 17 * hash + this.opiskelijaID;
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
        final Osallistuu other = (Osallistuu) obj;
        if (this.osallistuuID != other.osallistuuID) {
            return false;
        }
        if (this.kurssiID != other.kurssiID) {
            return false;
        }
        if (this.opiskelijaID != other.opiskelijaID) {
            return false;
        }
        return true;
    }
    @Override
    public int compareTo(Osallistuu olio) {
        Collator apu = Collator.getInstance();
        apu.setStrength(Collator.PRIMARY);
        int tulos = apu.compare(this.osallistuuID, olio.osallistuuID);
        if (tulos != 0) {
            return tulos;
        } else {
            return apu.compare(this.kurssiID, olio.kurssiID);
        }
    } 
    
     public static String listaMerkkijonona(List<Osallistuu> lista) {
        String merkit = "";
        for (Osallistuu uusi : lista) {
            merkit += uusi + "\n";
        }
        return merkit;
    }

    
    
    
    
}
    
    
    
    
    
    

