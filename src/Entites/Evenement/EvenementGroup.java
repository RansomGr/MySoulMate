/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Evenement;

import Entites.Plan.Plan;
import java.sql.Date;
import java.util.Objects;
import Entites.User.Client;

/**
 *
 * @author dellpro
 */
public class EvenementGroup extends Evenement{
  
    private Client organisation;
    public Date heure;
    public String description;
    public Plan plan;

    public EvenementGroup(int ID, String nom) {
        super(ID, nom);
    }

    public EvenementGroup( Client organisation, Date heure, String description, Plan plan, int ID, String nom) {
        super(ID, nom);
     
        this.organisation = organisation;
        this.heure = heure;
        this.description = description;
        this.plan = plan;
    }

    public EvenementGroup( Client organisation, Date heure, String description, Plan plan, String nom) {
        super(-1, nom);
     
        this.organisation = organisation;
        this.heure = heure;
        this.description = description;
        this.plan = plan;
    }
    

  

    public Client getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Client organisation) {
        this.organisation = organisation;
    }

    public Date getHeure() {
        return heure;
    }

    public void setHeure(Date heure) {
        this.heure = heure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final EvenementGroup other = (EvenementGroup) obj;
       
        if (this.organisation != other.organisation) {
            return false;
        }
        if (this.plan != other.plan) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.heure, other.heure)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EvenementGroup{  organisateur=" + organisation + ", heure=" + heure + ", description=" + description + ", plan=" + plan + '}';
    }

     
    
}
