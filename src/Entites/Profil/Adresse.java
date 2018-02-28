/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Profil;

import java.util.Objects;

/**
 *
 * @author Sofiene
 */
public class Adresse {
private int ID;
private int numero;
private String gouvernorat ;
private int code_postal;
private String ville;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.ID;
        hash = 97 * hash + this.numero;
        hash = 97 * hash + Objects.hashCode(this.gouvernorat);
        hash = 97 * hash + this.code_postal;
        hash = 97 * hash + Objects.hashCode(this.ville);
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
        final Adresse other = (Adresse) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        if (this.code_postal != other.code_postal) {
            return false;
        }
        if (!Objects.equals(this.gouvernorat, other.gouvernorat)) {
            return false;
        }
        if (!Objects.equals(this.ville, other.ville)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Adresse{" + "ID=" + ID + ", numero=" + numero + ", gouvernorat=" + gouvernorat + ", code_postal=" + code_postal + ", ville=" + ville + '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getGouvernorat() {
        return gouvernorat;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Adresse(int ID, int numero, String gouvernorat, int code_postal, String ville) {
        this.ID = ID;
        this.numero = numero;
        this.gouvernorat = gouvernorat;
        this.code_postal = code_postal;
        this.ville = ville;
    }

    public Adresse(int numero, String gouvernorat, int code_postal, String ville) {
        this.ID = -1;
        this.numero = numero;
        this.gouvernorat = gouvernorat;
        this.code_postal = code_postal;
        this.ville = ville;
    }
    
    
}
