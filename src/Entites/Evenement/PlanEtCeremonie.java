/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Evenement;

import Entites.Plan.Plan;
import java.util.Objects;

/**
 *
 * @author dellpro
 */
public class PlanEtCeremonie {
     private Plan plan;
     private EvenementGroup evtgroup;

    public PlanEtCeremonie(Plan plan, EvenementGroup evtgroup) {
        this.plan = plan;
        this.evtgroup = evtgroup;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
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
        if (!Objects.equals(this.plan, other.plan)) {
            return false;
        }
        if (!Objects.equals(this.evtgroup, other.evtgroup)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PlanEtCeremonie{" + "plan=" + plan + ", evtgroup=" + evtgroup + '}';
    }

}
