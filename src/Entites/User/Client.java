/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.User;

import Entites.AbstractEntite;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Ransom
 */
public class Client extends AbstractEntite {
    protected String prenom ;
    protected String motdepasse ;
    protected String email;
    protected Date date_naissance;
    protected String pseudo ;
    
       public Client(int ID,String nom,String prenom,String motdepasse,String email,Date date_naissance,String pseudo)
    {
        super(ID,nom);
        this.date_naissance=date_naissance;
        this.prenom=prenom;
        this.motdepasse=motdepasse;
        this.pseudo=pseudo;
        this.email=email;
    }
          public Client(String nom,String prenom,String motdepasse,String email,Date date_naissance,String pseudo)
    {
          super(-1,nom);
          this.date_naissance=date_naissance;
          this.prenom=prenom;
          this.motdepasse=motdepasse;
          this.pseudo=pseudo;
          this.email=email;
    }
    @Override
    public String toString() {
        return "Client{ ID "+ID+" nom "+nom+ "prenom=" + prenom + ", motdepasse=" + motdepasse + ", email=" + email + ", date_naissance=" + date_naissance + ", pseudo=" + pseudo + '}';
    }
//
    @Override
    public int hashCode() {
        int hash = 5;
        hash=hash+super.hashCode();
        hash = 89 * hash + Objects.hashCode(this.prenom);
        hash = 89 * hash + Objects.hashCode(this.motdepasse);
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.date_naissance);
        hash = 89 * hash + Objects.hashCode(this.pseudo);
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
        final Client other = (Client) obj;
           if(!Objects.equals(this.ID,other.ID))
        {
            return false;
        }
        if(!Objects.equals(this.nom,other.nom))
        {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.motdepasse, other.motdepasse)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.pseudo, other.pseudo)) {
            return false;
        }
        if (!Objects.equals(this.date_naissance, other.date_naissance)) {
            return false;
        }
        return true;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
 
    
    
    
            
    
    
}
