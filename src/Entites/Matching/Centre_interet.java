/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Matching;

import java.util.Objects;

/**
 *
 * @author Nadia
 */
public class Centre_interet {
    private int ID;
    private String nom;

    public Centre_interet(int ID, String nom) {
        this.ID = ID;
        this.nom = nom;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Centre_interet{" + "ID=" + ID + ", nom=" + nom + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.ID;
        hash = 11 * hash + Objects.hashCode(this.nom);
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
        final Centre_interet other = (Centre_interet) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
    
    
    
}
