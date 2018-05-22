/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Profil;
import Entites.User.Utilisateur;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Sofiene
 */
public class Profil implements Serializable {
    private int id;
  
   private  Caracteristique Caracteristique_id;
   private String photo;
   private String description;	
   private Caracteristique preference;
   private Utilisateur user_id;

    public Profil(Caracteristique caracteristique, String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Profil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Caracteristique getCaracteristique_id() {
        return Caracteristique_id;
    }

    public void setCaracteristique_id(Caracteristique Caracteristique_id) {
        this.Caracteristique_id = Caracteristique_id;
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

    public Utilisateur getUser_id() {
        return user_id;
    }

    public void setUser_id(Utilisateur user_id) {
        this.user_id = user_id;
    }

    public Profil(int id, Caracteristique Caracteristique_id, String photo, String description, Caracteristique preference){
        this.id = id;
        this.Caracteristique_id = Caracteristique_id;
        this.photo = photo;
        this.description = description;
        this.preference = preference;
     //   this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Profil{" + "id=" + id + ", Caracteristique_id=" + Caracteristique_id + ", photo=" + photo + ", description=" + description + ", preference=" + preference + ", user_id=" + user_id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.Caracteristique_id);
        hash = 89 * hash + Objects.hashCode(this.photo);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + Objects.hashCode(this.preference);
        hash = 89 * hash + Objects.hashCode(this.user_id);
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
        if (!Objects.equals(this.Caracteristique_id, other.Caracteristique_id)) {
            return false;
        }
        if (!Objects.equals(this.preference, other.preference)) {
            return false;
        }
        if (!Objects.equals(this.user_id, other.user_id)) {
            return false;
        }
        return true;
    }
   



    
}
