
import Entites.AbstractEntite;
import Entites.Profil.Actualite;
import java.sql.Date;
import java.util.Objects;
/**
 *
 * @author Sofiene
 */
public class Interaction  {
    private Actualite Actualite;
private String commentaire ; 
private String mention_jaime; 
private Date dateheure ;

    public Interaction(Actualite Actualite, String commentaire, String mention_jaime, Date dateheure) {
        this.Actualite = Actualite;
        this.commentaire = commentaire;
        this.mention_jaime = mention_jaime;
        this.dateheure = dateheure;
    }

    public Actualite getActualite() {
        return Actualite;
    }

    public void setActualite(Actualite Actualite) {
        this.Actualite = Actualite;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.Actualite);
        hash = 47 * hash + Objects.hashCode(this.commentaire);
        hash = 47 * hash + Objects.hashCode(this.mention_jaime);
        hash = 47 * hash + Objects.hashCode(this.dateheure);
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
        if (!Objects.equals(this.Actualite, other.Actualite)) {
            return false;
        }
        if (!Objects.equals(this.dateheure, other.dateheure)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Interaction{" + "Actualite=" + Actualite + ", commentaire=" + commentaire + ", mention_jaime=" + mention_jaime + ", dateheure=" + dateheure + '}';
    }
    

    
    }

    
    

