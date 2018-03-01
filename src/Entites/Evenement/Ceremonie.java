/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Evenement;

import Entites.Plan.Plan;
import Entites.User.Client;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author dellpro
 */
public class Ceremonie extends EvenementGroup{
  
   public String type;
   public int nb_max;

    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNb_max() {
        return nb_max;
    }

    public void setNb_max(int nb_max) {
        this.nb_max = nb_max;
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
        final Ceremonie other = (Ceremonie) obj;
        if (this.nb_max != other.nb_max) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    public Ceremonie(String type, int nb_max, Client organisation, String heure, String description, Plan plan, int ID, String nom) {
        super(organisation, heure, description, plan, ID, nom);
        this.type = type;
        this.nb_max = nb_max;
    }

  

    @Override
    public String toString() {
        return "Ceremonie{" + "type=" + type + ", nb_max=" + nb_max + '}';
    }
    
    
}
