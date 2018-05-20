/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Matching;
import java.sql.Date;
import Entites.User.Utilisateur;
import java.util.Objects;
/**
 *
 * @author Nadia
 */
public class Achat_packaging {
    private Utilisateur c;
    private Packaging p;
    private Date date_fin;

    public Achat_packaging(Utilisateur c, Packaging p, Date date_fin) {
        this.c = c;
        this.p = p;
        this.date_fin = date_fin;
    }

    public Utilisateur getC() {
        return c;
    }

    public void setC(Utilisateur c) {
        this.c = c;
    }

    public Packaging getP() {
        return p;
    }

    public void setP(Packaging p) {
        this.p = p;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Achat_packaging{" + "c=" + c + ", p=" + p + ", date_fin=" + date_fin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.c);
        hash = 37 * hash + Objects.hashCode(this.p);
        hash = 37 * hash + Objects.hashCode(this.date_fin);
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
        final Achat_packaging other = (Achat_packaging) obj;
        if (!Objects.equals(this.c, other.c)) {
            return false;
        }
        if (!Objects.equals(this.p, other.p)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        return true;
    }
    
    
    
}

