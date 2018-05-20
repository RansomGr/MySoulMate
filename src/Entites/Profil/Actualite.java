/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Profil;

import Entites.AbstractEntite;
import Entites.User.Client;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Sofiene
 */
public class Actualite {

    private int ID;
    private AbstractEntite Owner;
    private String contenu;
    private String photo;
    private LocalDate add_date;
    private Client writer;

    public Actualite(int ID, AbstractEntite Owner, String contenu, String photo, LocalDate add_date, Client writer) {
        this.ID = ID;
        this.Owner = Owner;
        this.contenu = contenu;
        this.photo = photo;
        this.add_date = add_date;
        this.writer = writer;
    }

    public Actualite(AbstractEntite Owner, String contenu, String photo, Client writer) {
        this.Owner = Owner;
        this.contenu = contenu;
        this.photo = photo;
        this.writer = writer;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public AbstractEntite getOwner() {
        return Owner;
    }

    public void setOwner(AbstractEntite Owner) {
        this.Owner = Owner;
    }

    public LocalDate getAdd_date() {
        return add_date;
    }

    public void setAdd_date(LocalDate add_date) {
        this.add_date = add_date;
    }

    public Client getWriter() {
        return writer;
    }

    public void setWriter(Client writer) {
        this.writer = writer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.ID;
        hash = 53 * hash + Objects.hashCode(this.Owner);
        hash = 53 * hash + Objects.hashCode(this.contenu);
        hash = 53 * hash + Objects.hashCode(this.photo);
        hash = 53 * hash + Objects.hashCode(this.writer);
        
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
        if (!Objects.equals(this.Owner, other.Owner)) {
            return false;
        }
        if (!Objects.equals(this.writer, other.writer)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Actualite{" + "ID=" + ID + ", Owner=" + Owner.getNom() + ", contenu=" + contenu + ", photo=" + photo + "DateTime =" + add_date +"Writer =" +writer+ '}';
    }

}
