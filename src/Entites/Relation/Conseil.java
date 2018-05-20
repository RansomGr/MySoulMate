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
    private String titre;
    private String contenue;
    private boolean etat;
    private String niveau;
    private String categorie;
    
    public Conseil(){}
    
    public Conseil(int ID,String titre,String contenue,boolean etat,String niveau, String categorie){
        this.ID=ID;
        this.titre=titre;
        this.contenue=contenue;
        this.etat=etat;
        this.niveau=niveau;
    }

    public Conseil(String titre,String contenu) {
        this.titre=titre;
        this.contenue=contenue;
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

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }
    
     public boolean isEtat() {
        return etat;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.ID;
        hash = 71 * hash + Objects.hashCode(this.titre);
        hash = 71 * hash + Objects.hashCode(this.contenue);
        hash = 71 * hash + (this.etat ? 1 : 0);
        hash = 71 * hash + Objects.hashCode(this.niveau);
        hash = 71 * hash + Objects.hashCode(this.categorie);
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
        if (this.etat != other.etat) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.contenue, other.contenue)) {
            return false;
        }
        if (!Objects.equals(this.niveau, other.niveau)) {
            return false;
        }
        if (!Objects.equals(this.categorie, other.categorie)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Conseil{" + "ID=" + ID + ", titre=" + titre + ", contenue=" + contenue + ", etat=" + etat + ", niveau=" + niveau + ", categorie=" + categorie + '}';
    }

    
}
