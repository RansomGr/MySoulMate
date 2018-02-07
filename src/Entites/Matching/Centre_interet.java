/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Matching;

/**
 *
 * @author Nadia
 */
public class Centre_interet {
    private int ID;
    private String nom;

    public Centre_interet(int ID, String nom) {
        this.ID = ID;
        this.nom = nom;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    
}
