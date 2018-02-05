/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.User;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Ransom
 */
public class Reclamation_compte extends Reclamation {
protected String categorie;
protected Client client;
protected Date Date_heure;

    public Reclamation_compte(int ID, String sujet, String description,String categorie,Client client,Date Date_heure) {
        super(ID, sujet, description);
        this.categorie=categorie;
        this.client=client;
        this.Date_heure=Date_heure;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDate_heure() {
        return Date_heure;
    }

    public void setDate_heure(Date Date_heure) {
        this.Date_heure = Date_heure;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.categorie);
        hash = 79 * hash + Objects.hashCode(this.client);
        hash = 79 * hash + Objects.hashCode(this.Date_heure);
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
        final Reclamation_compte other = (Reclamation_compte) obj;
        if (!Objects.equals(this.categorie, other.categorie)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.Date_heure, other.Date_heure)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reclamation_compte{" + "ID=" + ID + ", sujet=" + sujet + ", description=" + description  + ",categorie=" + categorie + ", client=" + client + ", Date_heure=" + Date_heure + '}';
    }
    
}
