/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Profil;

import Entites.User.Utilisateur;
import java.util.Objects;

/**
 *
 * @author Ransom
 */
public class Mention_Jaime {
    private Actualite actualite ;
    private Utilisateur owner;
    private String jaime;

    public Mention_Jaime(Actualite actualite, Utilisateur owner, String jaime) {
        this.actualite = actualite;
        this.owner = owner;
        this.jaime = jaime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.actualite);
        hash = 17 * hash + Objects.hashCode(this.owner);
        hash = 17 * hash + Objects.hashCode(this.jaime);
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
        final Mention_Jaime other = (Mention_Jaime) obj;
        if (!Objects.equals(this.jaime, other.jaime)) {
            return false;
        }
        if (!Objects.equals(this.actualite, other.actualite)) {
            return false;
        }
        if (!Objects.equals(this.owner, other.owner)) {
            return false;
        }
        return true;
    }

    public Actualite getActualite() {
        return actualite;
    }

    public void setActualite(Actualite actualite) {
        this.actualite = actualite;
    }

    public Utilisateur getOwner() {
        return owner;
    }

    public void setOwner(Utilisateur owner) {
        this.owner = owner;
    }

    public String getJaime() {
        return jaime;
    }

    public void setJaime(String jaime) {
        this.jaime = jaime;
    }

    @Override
    public String toString() {
        return "Mention_Jaime{" + "actualite=" + actualite + ", owner=" + owner + ", jaime=" + jaime + '}';
    }
    
    
}
