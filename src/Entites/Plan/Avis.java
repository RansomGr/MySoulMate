/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Plan;

import Entites.User.Client;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author irou
 */
public class Avis {
    private Plan plan;
    private Client client;
    private String commentaire;
    private String note;
    private Date dateh;

    public Avis(Plan plan, Client client, String commentaire, String note, Date dateh) {
        this.plan = plan;
        this.client = client;
        this.commentaire = commentaire;
        this.note = note;
        this.dateh = dateh;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDateh() {
        return dateh;
    }

    public void setDateh(Date dateh) {
        this.dateh = dateh;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.plan);
        hash = 29 * hash + Objects.hashCode(this.client);
        hash = 29 * hash + Objects.hashCode(this.commentaire);
        hash = 29 * hash + Objects.hashCode(this.note);
        hash = 29 * hash + Objects.hashCode(this.dateh);
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
        if (!Objects.equals(this.commentaire, other.commentaire)) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        if (!Objects.equals(this.plan, other.plan)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.dateh, other.dateh)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Avis{" + "plan=" + plan + ", client=" + client + ", commentaire=" + commentaire + ", note=" + note + ", dateh=" + dateh + '}';
    }
    
    
    
    
    
    
    
}
