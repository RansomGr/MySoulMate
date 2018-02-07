/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Evenement;

import java.sql.Date;
import java.util.Objects;
import Entites.AbstractEntite;

/**
 *
 * @author dellpro
 */
public class Evenement extends AbstractEntite{
    public int entite;
    public Date date_evenement;
    public Date heure;
    public String type_evenement;
    
     public int getEntite() {
        return entite;
    }

    public void setEntite(int entite) {
        this.entite = entite;
    }

    public Date getDate_evenement() {
        return date_evenement;
    }

    public void setDate_evenement(Date date_evenement) {
        this.date_evenement = date_evenement;
    }

    public Date getHeure() {
        return heure;
    }

    public void setHeure(Date heure) {
        this.heure = heure;
    }

    public String getType_evenement() {
        return type_evenement;
    }

    public void setType_evenement(String type_evenement) {
        this.type_evenement = type_evenement;
    }

    public Evenement(int ID, String nom) {
        super(ID, nom);
    }

    public Evenement(int entite, Date date_evenement, Date heure, String type_evenement, int ID, String nom) {
        super(ID, nom);
        this.entite = entite;
        this.date_evenement = date_evenement;
        this.heure = heure;
        this.type_evenement = type_evenement;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Evenement other = (Evenement) obj;
        if (this.entite != other.entite) {
            return false;
        }
        if (!Objects.equals(this.type_evenement, other.type_evenement)) {
            return false;
        }
        if (!Objects.equals(this.date_evenement, other.date_evenement)) {
            return false;
        }
        if (!Objects.equals(this.heure, other.heure)) {
            return false;
        }
        return true;
    }

}
