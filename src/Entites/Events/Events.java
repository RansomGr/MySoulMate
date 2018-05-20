/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Events;

import Entites.AbstractEntite;
import Entites.Plan.Plan;
import Entites.User.Utilisateur;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author dellpro
 */
public class Events {
    
    protected int id;
    protected String nom_evt;
    protected Date date_evt;
    protected String heure_evt;
    protected String duree_evt;
    protected String Type_evt;
    protected Utilisateur organisateur;
    protected String description_evt;
    protected Plan plan_evt;
    protected int nb_max;
    
    public Events(int id,String nom_evt, Date date_evt, String heure_evt, String duree_evt, String Type_evt, Utilisateur organisateur, String description_evt, Plan plan_evt, int nb_max, int ID, String nom) {
        this.id = id;
        this.nom_evt = nom_evt;
        this.date_evt = date_evt;
        this.heure_evt = heure_evt;
        this.duree_evt = duree_evt;
        this.Type_evt = Type_evt;
        this.organisateur = organisateur;
        this.description_evt = description_evt;
        this.plan_evt = plan_evt;
        this.nb_max = nb_max;
    }

    public Events(int id,String nom_evt, Date date_evt, String heure_evt, String duree_evt, String Type_evt, String description_evt, Plan plan_evt, int nb_max, int ID, String nom) {
        this.id = id;
        this.nom_evt = nom_evt;
        this.date_evt = date_evt;
        this.heure_evt = heure_evt;
        this.duree_evt = duree_evt;
        this.Type_evt = Type_evt;
        this.description_evt = description_evt;
        this.plan_evt = plan_evt;
        this.nb_max = nb_max;
    }
    
    

    public Events() {
       
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
        final Events other = (Events) obj;
        if (this.nb_max != other.nb_max) {
            return false;
        }
        if (!Objects.equals(this.nom_evt, other.nom_evt)) {
            return false;
        }
        if (!Objects.equals(this.heure_evt, other.heure_evt)) {
            return false;
        }
        if (!Objects.equals(this.duree_evt, other.duree_evt)) {
            return false;
        }
        if (!Objects.equals(this.Type_evt, other.Type_evt)) {
            return false;
        }
        if (!Objects.equals(this.description_evt, other.description_evt)) {
            return false;
        }
        if (!Objects.equals(this.date_evt, other.date_evt)) {
            return false;
        }
        if (!Objects.equals(this.organisateur, other.organisateur)) {
            return false;
        }
        if (!Objects.equals(this.plan_evt, other.plan_evt)) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNom_evt() {
        return nom_evt;
    }

    public void setNom_evt(String nom_evt) {
        this.nom_evt = nom_evt;
    }

    public Date getDate_evt() {
        return date_evt;
    }

    public void setDate_evt(Date date_evt) {
        this.date_evt = date_evt;
    }

    public String getHeure_evt() {
        return heure_evt;
    }

    public void setHeure_evt(String heure_evt) {
        this.heure_evt = heure_evt;
    }

    public String getDuree_evt() {
        return duree_evt;
    }

    public void setDuree_evt(String duree_evt) {
        this.duree_evt = duree_evt;
    }

    public String getType_evt() {
        return Type_evt;
    }

    public void setType_evt(String Type_evt) {
        this.Type_evt = Type_evt;
    }

    public Utilisateur getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(Utilisateur organisateur) {
        this.organisateur = organisateur;
    }

    public String getDescription_evt() {
        return description_evt;
    }

    public void setDescription_evt(String description_evt) {
        this.description_evt = description_evt;
    }

    public Plan getPlan_evt() {
        return plan_evt;
    }

    public void setPlan_evt(Plan plan_evt) {
        this.plan_evt = plan_evt;
    }

    public int getNb_max() {
        return nb_max;
    }

    public void setNb_max(int nb_max) {
        this.nb_max = nb_max;
    }

    @Override
    public String toString() {
        return "Events{" + "nom_evt=" + nom_evt + ", date_evt=" + date_evt + ", heure_evt=" + heure_evt + ", duree_evt=" + duree_evt + ", Type_evt=" + Type_evt + ", organisateur=" + organisateur + ", description_evt=" + description_evt + ", plan_evt=" + plan_evt + ", nb_max=" + nb_max + '}';
    }
    
    
}
