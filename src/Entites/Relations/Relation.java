/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.relations;

import Entites.User.Client;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author zhanimm
 */
public class Relation {
    private int ID;
     private Client Client1;
    private Client Client2;
    private int points_relation;
    private String niveau;
    private Date date_debut;
    private Date date_fin;
    public Relation(){}
    public Relation(int ID, Client Client1 , Client Client2, int points_relation, String niveau , Date date_debut, Date date_fin) {
        this.ID=ID;
        this.Client1=Client1;
        this.Client2=Client2;
        this.date_debut=date_debut;
        this.date_fin=date_fin;
        this.niveau=niveau;
        this.points_relation=points_relation;
        
    }

    public int getID() {
        return ID;
    }

    public Client getClient1() {
        return Client1;
    }

    public void setClient1(Client Client1) {
        this.Client1 = Client1;
    }

    public Client getClient2() {
        return Client2;
    }

    public void setClient2(Client Client2) {
        this.Client2 = Client2;
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
        int hash = 7;
        hash = 97 * hash + this.ID;
        hash = 97 * hash + Objects.hashCode(this.Client1);
        hash = 97 * hash + Objects.hashCode(this.Client2);
        hash = 97 * hash + this.points_relation;
        hash = 97 * hash + Objects.hashCode(this.niveau);
        hash = 97 * hash + Objects.hashCode(this.date_debut);
        hash = 97 * hash + Objects.hashCode(this.date_fin);
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
        if (this.ID != other.ID) {
            return false;
        }
        if (this.points_relation != other.points_relation) {
            return false;
        }
        if (!Objects.equals(this.niveau, other.niveau)) {
            return false;
        }
        if (!Objects.equals(this.Client1, other.Client1)) {
            return false;
        }
        if (!Objects.equals(this.Client2, other.Client2)) {
            return false;
        }
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Relation{" + "ID=" + ID + ", Client1=" + Client1 + ", Client2=" + Client2 + ", points_relation=" + points_relation + ", niveau=" + niveau + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }
    
    
}
