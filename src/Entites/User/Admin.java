/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.User;

import java.util.Objects;

/**
 *
 * @author Ransom
 */
public class Admin {
    private int ID;
    private String nom;
    private String prenom;
    private String motdepasse;

    
     public Admin(int ID,String nom,String prenom,String motdepasse)
    {
        this.ID=ID;
        this.nom=nom;
        this.prenom=prenom;
        this.motdepasse=motdepasse;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.ID;
        hash = 11 * hash + Objects.hashCode(this.nom);
        hash = 11 * hash + Objects.hashCode(this.prenom);
        hash = 11 * hash + Objects.hashCode(this.motdepasse);
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
        final Admin other = (Admin) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.motdepasse, other.motdepasse)) {
            return false;
        }
        return true;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
}
