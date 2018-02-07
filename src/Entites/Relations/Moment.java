/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.relations;

import java.util.Objects;

/**
 *
 * @author zhanimm
 */
public class Moment {
    private Relation relation;
    private Contenue_Moment c_moment;

    public Relation getRelation() {
        return relation;
    }

    public Contenue_Moment getC_moment() {
        return c_moment;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.relation);
        hash = 37 * hash + Objects.hashCode(this.c_moment);
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
        final Moment other = (Moment) obj;
        if (!Objects.equals(this.relation, other.relation)) {
            return false;
        }
        if (!Objects.equals(this.c_moment, other.c_moment)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Moment{" + "relation=" + relation + ", c_moment=" + c_moment + '}';
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public void setC_moment(Contenue_Moment c_moment) {
        this.c_moment = c_moment;
    }

    public Moment(Relation relation, Contenue_Moment c_moment) {
        this.relation = relation;
        this.c_moment = c_moment;
    }
}
