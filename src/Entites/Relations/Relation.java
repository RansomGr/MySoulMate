/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.relations;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author zhanimm
 */
public class Relation {
    private int ID;
    private int points_relation;
    private String niveau;
    private Date date_debut;
    private Date date_fin;
    public Relation(){}
    public Relation(int ID, String nom) {
        this.ID=ID;
        this.date_debut=date_debut;
        this.date_fin=date_fin;
        this.niveau=niveau;
        this.points_relation=points_relation;
        
    }

    public int getID() {
        return ID;
    }

    public int getPoints_relation() {
        return points_relation;
    }

    public String getNiveau() {
        return niveau;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setPoints_relation(int points_relation) {
        this.points_relation = points_relation;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.ID;
        hash = 47 * hash + this.points_relation;
        hash = 47 * hash + Objects.hashCode(this.niveau);
        hash = 47 * hash + Objects.hashCode(this.date_debut);
        hash = 47 * hash + Objects.hashCode(this.date_fin);
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
        final Relation other = (Relation) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Relation{" + "ID=" + ID + ", points_relation=" + points_relation + ", niveau=" + niveau + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }
    
    
}
