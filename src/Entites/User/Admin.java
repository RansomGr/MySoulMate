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
    private String login;
    private String motdepasse;

    
     public Admin(int ID,String nom,String prenom,String login,String motdepasse)
    {
        this.ID=ID;
        this.nom=nom;
        this.prenom=prenom;
        this.motdepasse=motdepasse;
        this.login=login;
    }
       public Admin(String nom,String prenom,String login,String motdepasse)
    {
        this.ID=-1;
        this.nom=nom;
        this.prenom=prenom;
        this.motdepasse=motdepasse;
         this.login=login;
    }

    @Override
    public String toString() {
        return "Admin{" + "ID=" + ID + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", motdepasse=" + motdepasse + '}';
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.ID;
        hash = 59 * hash + Objects.hashCode(this.nom);
        hash = 59 * hash + Objects.hashCode(this.prenom);
        hash = 59 * hash + Objects.hashCode(this.login);
        hash = 59 * hash + Objects.hashCode(this.motdepasse);
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
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.motdepasse, other.motdepasse)) {
            return false;
        }
        return true;
    }

  
 
 
  
  

}
