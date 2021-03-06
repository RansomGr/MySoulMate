/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Matching;

import java.util.Objects;

/**
 *
 * @author Nadia
 */
public class Caracteristique {
    private int ID;
    private String corpulence ; 
    private String pilosite;
    private String profession;
    private String alcool;
    private String tabac;
    private String taille;
    private String cheveux;
    private String yeux;
    private String caractere;
    private String statut;
    private String cuisine;

        public Caracteristique( String corpulence, String pilosite, String profession, String alcool, String tabac, String taille, String cheveux, String yeux, String caractere, String statut, String cuisine) {
        this.ID = -1;
        this.corpulence = corpulence;
        this.pilosite = pilosite;
        this.profession = profession;
        this.alcool = alcool;
        this.tabac = tabac;
        this.taille = taille;
        this.cheveux = cheveux;
        this.yeux = yeux;
        this.caractere = caractere;
        this.statut = statut;
        this.cuisine = cuisine;
    }

    public Caracteristique(int ID, String corpulence, String pilosite, String profession, String alcool, String tabac, String taille, String cheveux, String yeux, String caractere, String statut, String cuisine) {
        this.ID = ID;
        this.corpulence = corpulence;
        this.pilosite = pilosite;
        this.profession = profession;
        this.alcool = alcool;
        this.tabac = tabac;
        this.taille = taille;
        this.cheveux = cheveux;
        this.yeux = yeux;
        this.caractere = caractere;
        this.statut = statut;
        this.cuisine = cuisine;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCorpulence() {
        return corpulence;
    }

    public void setCorpulence(String corpulence) {
        this.corpulence = corpulence;
    }

    public String getPilosite() {
        return pilosite;
    }

    public void setPilosite(String pilosite) {
        this.pilosite = pilosite;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAlcool() {
        return alcool;
    }

    public void setAlcool(String alcool) {
        this.alcool = alcool;
    }

    public String getTabac() {
        return tabac;
    }

    public void setTabac(String tabac) {
        this.tabac = tabac;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getCheveux() {
        return cheveux;
    }

    public void setCheveux(String cheveux) {
        this.cheveux = cheveux;
    }

    public String getYeux() {
        return yeux;
    }

    public void setYeux(String yeux) {
        this.yeux = yeux;
    }

    public String getCaractere() {
        return caractere;
    }

    public void setCaractere(String caractere) {
        this.caractere = caractere;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    @Override
    public String toString() {
        return "Caracteristique{" + "ID=" + ID + ", corpulence=" + corpulence + ", pilosite=" + pilosite + ", profession=" + profession + ", alcool=" + alcool + ", tabac=" + tabac + ", taille=" + taille + ", cheveux=" + cheveux + ", yeux=" + yeux + ", caractere=" + caractere + ", statut=" + statut + ", cuisine=" + cuisine + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.ID;
        hash = 37 * hash + Objects.hashCode(this.corpulence);
        hash = 37 * hash + Objects.hashCode(this.pilosite);
        hash = 37 * hash + Objects.hashCode(this.profession);
        hash = 37 * hash + Objects.hashCode(this.alcool);
        hash = 37 * hash + Objects.hashCode(this.tabac);
        hash = 37 * hash + Objects.hashCode(this.taille);
        hash = 37 * hash + Objects.hashCode(this.cheveux);
        hash = 37 * hash + Objects.hashCode(this.yeux);
        hash = 37 * hash + Objects.hashCode(this.caractere);
        hash = 37 * hash + Objects.hashCode(this.statut);
        hash = 37 * hash + Objects.hashCode(this.cuisine);
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
        final Caracteristique other = (Caracteristique) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.corpulence, other.corpulence)) {
            return false;
        }
        if (!Objects.equals(this.pilosite, other.pilosite)) {
            return false;
        }
        if (!Objects.equals(this.profession, other.profession)) {
            return false;
        }
        if (!Objects.equals(this.alcool, other.alcool)) {
            return false;
        }
        if (!Objects.equals(this.tabac, other.tabac)) {
            return false;
        }
        if (!Objects.equals(this.taille, other.taille)) {
            return false;
        }
        if (!Objects.equals(this.cheveux, other.cheveux)) {
            return false;
        }
        if (!Objects.equals(this.yeux, other.yeux)) {
            return false;
        }
        if (!Objects.equals(this.caractere, other.caractere)) {
            return false;
        }
        if (!Objects.equals(this.statut, other.statut)) {
            return false;
        }
        if (!Objects.equals(this.cuisine, other.cuisine)) {
            return false;
        }
        return true;
    }

    public String getOrigine() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
           
}
