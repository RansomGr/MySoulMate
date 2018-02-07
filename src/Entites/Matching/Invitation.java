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
public class Invitation {
    private Client client1;
    private Client client2;
    private String statut;
    private Date date_amitie;

    public Invitation(Client client1, Client client2, String statut, Date date_amitie) {
        this.client1 = client1;
        this.client2 = client2;
        this.statut = statut;
        this.date_amitie = date_amitie;
    }

    public Client getClient1() {
        return client1;
    }

    public void setClient1(Client client1) {
        this.client1 = client1;
    }

    public Client getClient2() {
        return client2;
    }

    public void setClient2(Client client2) {
        this.client2 = client2;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDate_amitie() {
        return date_amitie;
    }

    public void setDate_amitie(Date date_amitie) {
        this.date_amitie = date_amitie;
    }
    
    
    
    
}
