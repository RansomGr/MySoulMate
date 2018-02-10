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
public class Reservation 
{
    private int ID;
    private Plan plan;
    private Client client;
    private Date date_res;
    private int nb_place;

    public Reservation(int ID,Plan plan, Client client, Date date_res, int nb_place) {
        this.ID=ID;
        this.plan = plan;
        this.client = client;
        this.date_res = date_res;
        this.nb_place = nb_place;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public Date getDate_res() {
        return date_res;
    }

    public void setDate_res(Date date_res) {
        this.date_res = date_res;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.ID;
        hash = 31 * hash + Objects.hashCode(this.plan);
        hash = 31 * hash + Objects.hashCode(this.client);
        hash = 31 * hash + Objects.hashCode(this.date_res);
        hash = 31 * hash + this.nb_place;
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
        final Reservation other = (Reservation) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.nb_place != other.nb_place) {
            return false;
        }
        if (!Objects.equals(this.plan, other.plan)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.date_res, other.date_res)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" + "ID=" + ID + ", plan=" + plan + ", client=" + client + ", date_res=" + date_res + ", nb_place=" + nb_place + '}';
    }

    
    
    
    
    
    
    
    
    
}
