/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Relation;

import Entites.AbstractEntite;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author zhanimm 
 * 
 */
//done

public class Contenue_Moment {
    private int ID;
    private String nom;
    private String contenue ; 
    private String photo ;
    private String description ;
    private Date date_moment; 
    public Contenue_Moment(){}
    public Contenue_Moment(int ID, String nom,String contenue,String photo,String description, Date date_moment) {
        this.ID=ID;
        this.nom=nom;
        this.contenue=contenue;
        this.photo=photo;
        this.date_moment=date_moment;
        this.description=description;
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

    public String getContenue() {
        return contenue;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
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

    public Date getDate_moment() {
        return date_moment;
    }

    public void setDate_moment(Date date_moment) {
        this.date_moment = date_moment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.ID;
        hash = 71 * hash + Objects.hashCode(this.nom);
        hash = 71 * hash + Objects.hashCode(this.contenue);
        hash = 71 * hash + Objects.hashCode(this.photo);
        hash = 71 * hash + Objects.hashCode(this.description);
        hash = 71 * hash + Objects.hashCode(this.date_moment);
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
        final Contenue_Moment other = (Contenue_Moment) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.contenue, other.contenue)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.date_moment, other.date_moment)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contenue_Moment{" + "ID=" + ID + ", nom=" + nom + ", contenue=" + contenue + ", photo=" + photo + ", description=" + description + ", date_moment=" + date_moment + '}';
    }

   
}
