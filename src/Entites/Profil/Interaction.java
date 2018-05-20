package Entites.Profil;


import Entites.User.Client;
import java.sql.Date;
import java.util.Objects;
/**
 *
 * @author Sofiene
 */
public class Interaction  {

private int ID;
private Actualite Actualite;
private Client owner ;
private String commentaire ; 
private Date dateheure ;

    public Interaction(int ID, Actualite Actualite, Client owner, String commentaire, Date dateheure) {
        this.ID = ID;
        this.Actualite = Actualite;
        this.owner = owner;
        this.commentaire = commentaire;
        this.dateheure = dateheure;
    }

    public Interaction(Actualite Actualite, Client owner, String commentaire) {
        this.Actualite = Actualite;
        this.owner = owner;
        this.commentaire = commentaire;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Actualite getActualite() {
        return Actualite;
    }

    public void setActualite(Actualite Actualite) {
        this.Actualite = Actualite;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDateheure() {
        return dateheure;
    }

    public void setDateheure(Date dateheure) {
        this.dateheure = dateheure;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.ID;
        hash = 79 * hash + Objects.hashCode(this.Actualite);
        hash = 79 * hash + Objects.hashCode(this.owner);
        hash = 79 * hash + Objects.hashCode(this.commentaire);
        hash = 79 * hash + Objects.hashCode(this.dateheure);
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
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.commentaire, other.commentaire)) {
            return false;
        }
        if (!Objects.equals(this.Actualite, other.Actualite)) {
            return false;
        }
        if (!Objects.equals(this.owner, other.owner)) {
            return false;
        }
        if (!Objects.equals(this.dateheure, other.dateheure)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Interaction{" + "ID=" + ID + ", Actualite=" + Actualite + ", owner=" + owner + ", commentaire=" + commentaire + ", dateheure=" + dateheure + '}';
    }

   
    
    }

    
    

