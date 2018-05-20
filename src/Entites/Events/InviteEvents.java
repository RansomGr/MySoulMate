/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Events;

import Entites.Events.Events;
import Entites.User.Client;
import java.util.Objects;

/**
 *
 * @author dellpro
 */
public class InviteEvents {
    Events evts;
    Client invite;
    protected String participe;
    public InviteEvents(Events evts, Client invite, String participe) {
        this.evts = evts;
        this.invite = invite;
        this.participe = participe;
    }

    public InviteEvents(Events evt, Client clt) {
          this.evts = evts;
        this.invite = invite;
    }

    @Override
    public String toString() {
        return "InviteEvents{" + "evts=" + evts + ", invite=" + invite + '}';
    }
        
    @Override
    public int hashCode() {
        int hash = 7;
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
        final InviteEvents other = (InviteEvents) obj;
        if (!Objects.equals(this.evts, other.evts)) {
            return false;
        }
        if (!Objects.equals(this.invite, other.invite)) {
            return false;
        }
        return true;
    }

    
    public Events getEvts() {
        return evts;
    }

    public void setEvts(Events evts) {
        this.evts = evts;
    }

    public Client getInvite() {
        return invite;
    }

    public void setInvite(Client invite) {
        this.invite = invite;
    }

    public String getParticipe() {
        return participe;
    }

    public void setParticipe(String participe) {
        this.participe = participe;
    }
    
    
}
