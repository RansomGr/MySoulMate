/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Plan;


import Entites.User.Utilisateur;
import java.util.Objects;

/**
 *
 * @author irou
 */
public class Avis {
    private int Id;
    private Plan plan;
    private Utilisateur client ;
    private String commentaire;
    private Float note;

    public Avis(Plan plan, Utilisateur client, String commentaire, Float note) {
        this.plan = plan;
        this.client = client;
        this.commentaire = commentaire;
        this.note = note;
    }
    

    public Avis(int Id, Plan plan, Utilisateur client, String commentaire, Float note) {
        this.Id = Id;
        this.plan = plan;
        this.client = client;
        this.commentaire = commentaire;
        this.note = note;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Utilisateur getClient() {
        return client;
    }

    public void setClient(Utilisateur client) {
        this.client = client;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.Id;
        hash = 13 * hash + Objects.hashCode(this.plan);
        hash = 13 * hash + Objects.hashCode(this.client);
        hash = 13 * hash + Objects.hashCode(this.commentaire);
        hash = 13 * hash + Objects.hashCode(this.note);
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
        final Avis other = (Avis) obj;
        if (this.Id != other.Id) {
            return false;
        }
        if (!Objects.equals(this.commentaire, other.commentaire)) {
            return false;
        }
        if (!Objects.equals(this.plan, other.plan)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Avis{" + "Id=" + Id + ", plan=" + plan + ", client=" + client + ", commentaire=" + commentaire + ", note=" + note + '}';
    }

   
    
   
}
