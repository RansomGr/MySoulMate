/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;
import java.io.Serializable;
import java.util.Objects;
/**
 *
 * @author Ransom
 */
public abstract class AbstractEntite implements Serializable // commit to jiléni and irou 
{
    protected int ID ;//    this one is for you Nadia Trabelsi
    protected String nom;

  public int getID() {return ID;}
  public void setID(int ID) { this.ID = ID; }
  public String getNom() {return nom; }
  public void setNom(String nom) { this.nom = nom;  }
      
  public AbstractEntite()
  {
      this.ID=22;
  }
  public AbstractEntite(int ID,String nom)
  {
      this.ID=ID;
      this.nom=nom;
  }

    @Override
    public String toString() {
        return "AbstractEntite{" + "ID=" + ID + ", nom=" + nom + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.ID;
        hash = 61 * hash + Objects.hashCode(this.nom);
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
        final AbstractEntite other = (AbstractEntite) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
}
