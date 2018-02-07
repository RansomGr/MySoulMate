/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Matching;
import java.sql.Date;
import Entites.User.Client;
import java.util.Objects;

/**
 *
 * @author Nadia
 */
public class Invitation {
    private Client client1;
    private Client client2;
    private String statut;
    private Date date_amitie;

    public Invitation(Client client1, Client client2, String statut, Date date_amitie) {
        this.client1 = client1;
        this.client2 = client2;
        this.statut = statut;
        this.date_amitie = date_amitie;
    }

    public Client getClient1() {
        return client1;
    }

    public void setClient1(Client client1) {
        this.client1 = client1;
    }

    public Client getClient2() {
        return client2;
    }

    public void setClient2(Client client2) {
        this.client2 = client2;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDate_amitie() {
        return date_amitie;
    }

    public void setDate_amitie(Date date_amitie) {
        this.date_amitie = date_amitie;
    }

    @Override
    public String toString() {
        return "Invitation{" + "client1=" + client1 + ", client2=" + client2 + ", statut=" + statut + ", date_amitie=" + date_amitie + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.client1);
        hash = 59 * hash + Objects.hashCode(this.client2);
        hash = 59 * hash + Objects.hashCode(this.statut);
        hash = 59 * hash + Objects.hashCode(this.date_amitie);
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
        final Invitation other = (Invitation) obj;
        if (!Objects.equals(this.statut, other.statut)) {
            return false;
        }
        if (!Objects.equals(this.client1, other.client1)) {
            return false;
        }
        if (!Objects.equals(this.client2, other.client2)) {
            return false;
        }
        if (!Objects.equals(this.date_amitie, other.date_amitie)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
