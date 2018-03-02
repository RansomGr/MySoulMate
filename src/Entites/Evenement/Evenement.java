/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Evenement;

import java.sql.Date;
import java.util.Objects;
import Entites.AbstractEntite;
import Entites.User.Client;
import java.util.List;

/**
 *
 * @author dellpro
 */
public class Evenement extends AbstractEntite{
    
    protected Date date_evenement;
    protected String heure;
    protected String type_evenement;
   
    public Date getDate_evenement() {
        return date_evenement;
    }

    public void setDate_evenement(Date date_evenement) {
        this.date_evenement = date_evenement;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
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

    public Evenement( Date date_evenement, String heure, String type_evenement, int ID, String nom) {
        super(ID, nom);
        this.date_evenement = date_evenement;
        this.heure = heure;
        this.type_evenement = type_evenement;
    }

     public Evenement( Date date_evenement, String heure, String type_evenement, String nom) {
        super(-1, nom);
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

    @Override
    public String toString() {
        return "Evenement{"+" ID "+ ID +", nom "+ nom+ " ,date_evenement=" + date_evenement + ", heure=" + heure + ", type_evenement=" + type_evenement + '}';
    }
}
