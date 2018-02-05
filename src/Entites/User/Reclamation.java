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
public class Reclamation {
    protected  int ID;
    protected String  sujet;
    protected String description;
    
    public Reclamation(int ID,String sujet,String description)
    {
        this.ID=ID;
        this.sujet=sujet;
        this.description=description;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.ID;
        hash = 17 * hash + Objects.hashCode(this.sujet);
        hash = 17 * hash + Objects.hashCode(this.description);
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
        final Reclamation other = (Reclamation) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.sujet, other.sujet)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "ID=" + ID + ", sujet=" + sujet + ", description=" + description + '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
