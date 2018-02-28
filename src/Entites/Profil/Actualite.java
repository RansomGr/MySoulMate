/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Profil;

import Entites.AbstractEntite;
import Entites.User.Client;
import java.util.Objects;

/**
 *
 * @author Sofiene
 */
public class Actualite {
private int ID;
private Client client ; 
private String contenu ; 
private String photo;

    public Actualite(int ID, Client client, String contenu, String photo) {
        this.ID = ID;
        this.client = client;
        this.contenu = contenu;
        this.photo = photo;
    }
    public Actualite(int ID ,String contenu ,String photo)
    {
        this.ID=ID;
        this.contenu=contenu;
        this.photo=photo;
    }



    public Actualite(Client client, String contenu, String photo) {
        this.client = client;
        this.contenu = contenu;
        this.photo = photo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Actualite{" + "ID=" + ID + ", client=" + client + ", contenu=" + contenu + ", photo=" + photo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.ID;
        hash = 71 * hash + Objects.hashCode(this.client);
        hash = 71 * hash + Objects.hashCode(this.contenu);
        hash = 71 * hash + Objects.hashCode(this.photo);
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
        final Actualite other = (Actualite) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.contenu, other.contenu)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        return true;
    }


    
    
}
