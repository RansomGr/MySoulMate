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
    }
    @Override
    public String toString() {
        return "Client{ ID "+ID+" nom "+nom+ "prenom=" + prenom + ", motdepasse=" + motdepasse + ", email=" + email + ", date_naissance=" + date_naissance + ", pseudo=" + pseudo + '}';
    }

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
 
    
    
    
            
    
    
}
