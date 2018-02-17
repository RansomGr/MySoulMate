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
public class Packaging {
    
    private int ID;
    private String nom;
    private String contenu;
    private int duree ;
    private String prix ;

    
    public Packaging( String nom, String contenu, int duree, String prix) {
        this.ID = -1;
        this.nom = nom;
        this.contenu = contenu;
        this.duree = duree;
        this.prix = prix;
    }
    
    
    public Packaging(int ID, String nom, String contenu, int duree, String prix) {
        this.ID = ID;
        this.nom = nom;
        this.contenu = contenu;
        this.duree = duree;
        this.prix = prix;
    }
    
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Packaging{" + "ID=" + ID + ", nom=" + nom + ", contenu=" + contenu + ", duree=" + duree + ", prix=" + prix + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.ID;
        hash = 59 * hash + Objects.hashCode(this.nom);
        hash = 59 * hash + Objects.hashCode(this.contenu);
        hash = 59 * hash + this.duree;
        hash = 59 * hash + Objects.hashCode(this.nom);
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
        final Packaging other = (Packaging) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.duree != other.duree) {
            return false;
        }
        if (!Objects.equals(this.prix, other.prix)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.contenu, other.contenu)) {
            return false;
        }
        return true;
    }
    
    
    
}
