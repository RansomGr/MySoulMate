/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Profil;

import Entites.User.Client;
import java.util.Objects;

/**
 *
 * @author Sofiene
 */
public class Interet {
    Client client ;
Centre_interet Centre_interet;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Interet{" + "client=" + client + ", Centre_interet=" + Centre_interet + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.client);
        hash = 37 * hash + Objects.hashCode(this.Centre_interet);
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
        final Interet other = (Interet) obj;
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.Centre_interet, other.Centre_interet)) {
            return false;
        }
        return true;
    }

    public Centre_interet getCentre_interet() {
        return Centre_interet;
    }

    public void setCentre_interet(Centre_interet Centre_interet) {
        this.Centre_interet = Centre_interet;
    }

    public Interet(Client client, Centre_interet Centre_interet) {
        this.client = client;
        this.Centre_interet = Centre_interet;
    }

    
}
