/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Matching;
import Entites.User.Client;
import java.util.Objects;

/**
 *
 * @author Nadia
 */
public class Interet {
    private Client c;
    private Centre_interet ci;

    public Interet(Client c, Centre_interet ci) {
        this.c = c;
        this.ci = ci;
    }

    public Client getC() {
        return c;
    }

    public void setC(Client c) {
        this.c = c;
    }

    public Centre_interet getCi() {
        return ci;
    }

    public void setCi(Centre_interet ci) {
        this.ci = ci;
    }

    @Override
    public String toString() {
        return "Interet{" + "c=" + c + ", ci=" + ci + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.c);
        hash = 67 * hash + Objects.hashCode(this.ci);
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
        if (!Objects.equals(this.c, other.c)) {
            return false;
        }
        if (!Objects.equals(this.ci, other.ci)) {
            return false;
        }
        return true;
    }
    
    
    
}
