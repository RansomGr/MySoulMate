/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Profil;

import Entites.User.Utilisateur;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Sofiene
 */
public class Actualite {

    private int ID;
    private Utilisateur createur;
    private String contenu;
    private String photo;
    private LocalDate add_date;

    public Actualite(int ID, Utilisateur createur, String contenu, String photo, LocalDate add_date) {
        this.ID = ID;
        this.createur = createur;
        this.contenu = contenu;
        this.photo = photo;
        this.add_date = add_date;
       
    }

    public Actualite(Utilisateur Parent, String text, String string, Utilisateur logged_in_Client) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Utilisateur getCreateur() {
        return createur;
    }

    public void setCreateur(Utilisateur createur) {
        this.createur = createur;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDate getAdd_date() {
        return add_date;
    }

    public void setAdd_date(LocalDate add_date) {
        this.add_date = add_date;
    }

 

    @Override
    public String toString() {
        return "Actualite{" + "ID=" + ID + ", createur=" + createur + ", contenu=" + contenu + ", photo=" + photo + ", add_date=" + add_date + '}';
    }

  

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.ID;
        hash = 59 * hash + Objects.hashCode(this.createur);
        hash = 59 * hash + Objects.hashCode(this.contenu);
        hash = 59 * hash + Objects.hashCode(this.photo);
        hash = 59 * hash + Objects.hashCode(this.add_date);
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
        final Actualite other = (Actualite) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.contenu, other.contenu)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        if (!Objects.equals(this.createur, other.createur)) {
            return false;
        }
        if (!Objects.equals(this.add_date, other.add_date)) {
            return false;
        }
      
        return true;
    }

    

}
