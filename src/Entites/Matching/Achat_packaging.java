/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Matching;
import java.sql.Date;
import Entites.User.Client;
/**
 *
 * @author Nadia
 */
public class Achat_packaging {
    private Client c;
    private Packaging p;
    private Date date_fin;

    public Achat_packaging(Client c, Packaging p, Date date_fin) {
        this.c = c;
        this.p = p;
        this.date_fin = date_fin;
    }

    public Client getC() {
        return c;
    }

    public void setC(Client c) {
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
    
    
    
}

