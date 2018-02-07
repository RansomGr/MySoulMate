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
public class Packaging {
    
    private int ID;
    private String nom;
    private String contenu;
    private int duree ;
    private float prix ;

    public Packaging(int ID, String nom, String contenu, int duree, float prix) {
        this.ID = ID;
        this.nom = nom;
        this.contenu = contenu;
        this.duree = duree;
        this.prix = prix;
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

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    
    
    
}
