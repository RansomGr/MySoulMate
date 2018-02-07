/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Evenement;

import java.util.Objects;

/**
 *
 * @author dellpro
 */
public class PlanCeremonie {
    public int id;
    public String nom;
    public String type;
    public int adresse;
    public String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAdresse() {
        return adresse;
    }

    public void setAdresse(int adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        final PlanCeremonie other = (PlanCeremonie) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.adresse != other.adresse) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PlanCeremonie{" + "id=" + id + ", nom=" + nom + ", type=" + type + ", adresse=" + adresse + ", description=" + description + '}';
    }

    public PlanCeremonie() {
    }

    public PlanCeremonie(int id, String nom, String type, int adresse, String description) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.adresse = adresse;
        this.description = description;
    }
    
    
}
