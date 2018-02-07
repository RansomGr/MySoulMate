/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Entites.Profil.Actualite;
import java.sql.Date;
import java.util.Objects;
/**
 *
 * @author Sofiene
 */
public class Interaction {
 Actualite a ;
private String commentaire ; 

    @Override
    public String toString() {
        return "Interaction{" + "a=" + a + ", commentaire=" + commentaire + ", mention_jaime=" + mention_jaime + ", dateheure=" + dateheure + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.a);
        hash = 89 * hash + Objects.hashCode(this.commentaire);
        hash = 89 * hash + Objects.hashCode(this.mention_jaime);
        hash = 89 * hash + Objects.hashCode(this.dateheure);
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
        final Interaction other = (Interaction) obj;
        if (!Objects.equals(this.commentaire, other.commentaire)) {
            return false;
        }
        if (!Objects.equals(this.mention_jaime, other.mention_jaime)) {
            return false;
        }
        if (!Objects.equals(this.a, other.a)) {
            return false;
        }
        if (!Objects.equals(this.dateheure, other.dateheure)) {
            return false;
        }
        return true;
    }
private String mention_jaime; 
private Date dateheure ;

    public Actualite getA() {
        return a;
    }

    public void setA(Actualite a) {
        this.a = a;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getMention_jaime() {
        return mention_jaime;
    }

    public void setMention_jaime(String mention_jaime) {
        this.mention_jaime = mention_jaime;
    }

    public Date getDateheure() {
        return dateheure;
    }

    public void setDateheure(Date dateheure) {
        this.dateheure = dateheure;
    }

    public Interaction(Actualite a, String commentaire, String mention_jaime, Date dateheure) {
        this.a = a;
        this.commentaire = commentaire;
        this.mention_jaime = mention_jaime;
        this.dateheure = dateheure;
    }
    
}
