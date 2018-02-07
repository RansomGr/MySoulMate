/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Profil;

import Entites.User.Client;
import java.util.Objects;

/**
 *
 * @author Sofiene
 */
public class Profil {
    private int id;
    Client Client;	
    Caracteristique     Caracteristique	;
	
   private String photo;
   private String description;	
   Caracteristique preference;

    public Profil(int id, Client Client, Caracteristique Caracteristique, String photo, String description, Caracteristique preference) {
        this.id = id;
        this.Client = Client;
        this.Caracteristique = Caracteristique;
        this.photo = photo;
        this.description = description;
        this.preference = preference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return Client;
    }

    public void setClient(Client Client) {
        this.Client = Client;
    }

    public Caracteristique getCaracteristique() {
        return Caracteristique;
    }

    public void setCaracteristique(Caracteristique Caracteristique) {
        this.Caracteristique = Caracteristique;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Caracteristique getPreference() {
        return preference;
    }

    public void setPreference(Caracteristique preference) {
        this.preference = preference;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.Client);
        hash = 67 * hash + Objects.hashCode(this.Caracteristique);
        hash = 67 * hash + Objects.hashCode(this.photo);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.preference);
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
        final Profil other = (Profil) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.Client, other.Client)) {
            return false;
        }
        if (!Objects.equals(this.Caracteristique, other.Caracteristique)) {
            return false;
        }
        if (!Objects.equals(this.preference, other.preference)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Profil{" + "id=" + id + ", Client=" + Client + ", Caracteristique=" + Caracteristique + ", photo=" + photo + ", description=" + description + ", preference=" + preference + '}';
    }
   
    
}
