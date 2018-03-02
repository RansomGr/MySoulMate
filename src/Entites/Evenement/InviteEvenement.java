/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Evenement;

import java.util.Objects;
import Entites.User.Client;
/**
 *
 * @author dellpro
 */
public class InviteEvenement {
    EvenementGroup evtgroup;
    Client clt;

    public InviteEvenement(Events ev, Client x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public EvenementGroup getEvtgroup() {
        return evtgroup;
    }

    public void setEvtgroup(EvenementGroup evtgroup) {
        this.evtgroup = evtgroup;
    }

    public Client getClt() {
        return clt;
    }

    public void setClt(Client clt) {
        this.clt = clt;
    }

    @Override
    public String toString() {
        return "InviteEvenement{" + "evtgroup=" + evtgroup + ", clt=" + clt + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final InviteEvenement other = (InviteEvenement) obj;
        if (!Objects.equals(this.evtgroup, other.evtgroup)) {
            return false;
        }
        if (!Objects.equals(this.clt, other.clt)) {
            return false;
        }
        return true;
    }

    public InviteEvenement() {
    }

    public InviteEvenement(EvenementGroup evtgroup, Client clt) {
        this.evtgroup = evtgroup;
        this.clt = clt;
    }
    
    
    
}
