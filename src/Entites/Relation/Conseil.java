/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Relation;

import java.util.Objects;

/**
 *
 * @author zhanimm
 */
public class Conseil {
    private int ID;
    private String contenue;
    //private boolean etat;
    private int niveau;
    
    public Conseil(){}
    
    public Conseil(int ID,String contenue/*,boolean etat*/,int niveau){
        this.ID=ID;
        this.contenue=contenue;
        //this.etat=etat;
        this.niveau=niveau;
    }

    public Conseil(String contenu,int niveau) {
        this.contenue=contenu;
        this.niveau=niveau;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getContenue() {
        return contenue;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

  /*  public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }
    
     public boolean isEtat() {
        return etat;
    }*/

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.ID;
        hash = 53 * hash + Objects.hashCode(this.contenue);
        //hash = 53 * hash + (this.etat ? 1 : 0);
        hash = 53 * hash + Objects.hashCode(this.niveau);
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
        final Conseil other = (Conseil) obj;
        if (this.ID != other.ID) {
            return false;
        }
       /* if (this.etat != other.etat) {
            return false;
        }*/
        if (!Objects.equals(this.contenue, other.contenue)) {
            return false;
        }
        if (!Objects.equals(this.niveau, other.niveau)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Conseil{" + "ID=" + ID + ", contenue=" + contenue +/* ", etat=" + etat + */", niveau=" + niveau + '}';
    }

   
}
