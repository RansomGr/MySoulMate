/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Evenement;

import java.util.Objects;

/**
 *
 * @author dellpro
 */
public class Ceremonie {
   EvenementGroup evtgrp;
   public String type;
   public int nb_max;

    public EvenementGroup getEvtgrp() {
        return evtgrp;
    }

    public void setEvtgrp(EvenementGroup evtgrp) {
        this.evtgrp = evtgrp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNb_max() {
        return nb_max;
    }

    public void setNb_max(int nb_max) {
        this.nb_max = nb_max;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Ceremonie other = (Ceremonie) obj;
        if (this.nb_max != other.nb_max) {
            return false;
        }
        if (!Objects.equals(this.evtgrp, other.evtgrp)) {
            return false;
        }
        return true;
    }

    public Ceremonie() {
    }

    public Ceremonie(EvenementGroup evtgrp, String type, int nb_max) {
        this.evtgrp = evtgrp;
        this.type = type;
        this.nb_max = nb_max;
    }

    @Override
    public String toString() {
        return "Ceremonie{" + "evtgrp=" + evtgrp + ", type=" + type + ", nb_max=" + nb_max + '}';
    }
     
    
}
