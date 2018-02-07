/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Relation;

import Entites.AbstractEntite;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author zhanimm 
 * 
 */
//done

public class Contenue_Moment extends AbstractEntite{
    
    private String contenue ; 
    private String photo ;
    private String description ;
    private Date date_moment; 
    public Contenue_Moment(int ID, String nom,String contenue,String photo,String description, Date date_moment) {
        super(ID, nom);
        this.contenue=contenue;
        this.photo=photo;
        this.date_moment=date_moment;
        this.description=description;
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
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.contenue);
        hash = 13 * hash + Objects.hashCode(this.photo);
        hash = 13 * hash + Objects.hashCode(this.description);
        hash = 13 * hash + Objects.hashCode(this.date_moment);
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
        return "Contenue_Moment{" + "contenue=" + contenue + ", photo=" + photo + ", description=" + description + ", date_moment=" + date_moment + '}';
    }
    
}
