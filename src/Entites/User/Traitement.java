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
public class Traitement 
{
private Admin admin;
private Reclamation_compte rec_compte;
private Date date_traitement;
private String reponce;
public Traitement(Admin admin,Reclamation_compte rec_compte ,Date date_traitement,String reponce )
{
    this.admin=admin;
    this.rec_compte=rec_compte;
    this.date_traitement=date_traitement;
    this.reponce=reponce;
}

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Reclamation_compte getRec_compte() {
        return rec_compte;
    }

    public void setRec_compte(Reclamation_compte rec_compte) {
        this.rec_compte = rec_compte;
    }

    public Date getDate_traitement() {
        return date_traitement;
    }

    public void setDate_traitement(Date date_traitement) {
        this.date_traitement = date_traitement;
    }

    public String getReponce() {
        return reponce;
    }

    public void setReponce(String reponce) {
        this.reponce = reponce;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.admin);
        hash = 53 * hash + Objects.hashCode(this.rec_compte);
        hash = 53 * hash + Objects.hashCode(this.date_traitement);
        hash = 53 * hash + Objects.hashCode(this.reponce);
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
        final Traitement other = (Traitement) obj;
        if (!Objects.equals(this.reponce, other.reponce)) {
            return false;
        }
        if (!Objects.equals(this.admin, other.admin)) {
            return false;
        }
        if (!Objects.equals(this.rec_compte, other.rec_compte)) {
            return false;
        }
        if (!Objects.equals(this.date_traitement, other.date_traitement)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Traitement{" + "admin=" + admin + ", rec_compte=" + rec_compte + ", date_traitement=" + date_traitement + ", reponce=" + reponce + '}';
    }


}
