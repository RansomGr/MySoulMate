/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Matching;
import Entites.User.Client;

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
    
    
    
}
