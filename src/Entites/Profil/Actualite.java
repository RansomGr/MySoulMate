/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Profil;

import Entites.AbstractEntite;
import java.util.Objects;

/**
 *
 * @author Sofiene
 */
public class Actualite {
    private int ID;
AbstractEntite entite ; 
private String contenu ; 
private String photo;

    @Override
    public String toString() {
        return "Actualite{" + "ID=" + ID + ", entite=" + entite + ", contenu=" + contenu + ", photo=" + photo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.ID;
        hash = 67 * hash + Objects.hashCode(this.entite);
        hash = 67 * hash + Objects.hashCode(this.contenu);
        hash = 67 * hash + Objects.hashCode(this.photo);
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
        if (!Objects.equals(this.entite, other.entite)) {
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

    public AbstractEntite getEntite() {
        return entite;
    }

    public void setEntite(AbstractEntite entite) {
        this.entite = entite;
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

    public Actualite(int ID, AbstractEntite entite, String contenu, String photo) {
        this.ID = ID;
        this.entite = entite;
        this.contenu = contenu;
        this.photo = photo;
    }
    
}
