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
public class PlanEtCeremonie {
     private PlanCeremonie plancer;
    private EvenementGroup evtgroup;

    public PlanEtCeremonie(PlanCeremonie plancer, EvenementGroup evtgroup) {
        this.plancer = plancer;
        this.evtgroup = evtgroup;
    }

    public PlanCeremonie getPlancer() {
        return plancer;
    }

    public void setPlancer(PlanCeremonie plancer) {
        this.plancer = plancer;
    }

    public EvenementGroup getEvtgroup() {
        return evtgroup;
    }

    public void setEvtgroup(EvenementGroup evtgroup) {
        this.evtgroup = evtgroup;
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
        final PlanEtCeremonie other = (PlanEtCeremonie) obj;
        if (!Objects.equals(this.plancer, other.plancer)) {
            return false;
        }
        if (!Objects.equals(this.evtgroup, other.evtgroup)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ceremonie{" + "plancer=" + plancer + ", evtgroup=" + evtgroup + '}';
    }
    
}
