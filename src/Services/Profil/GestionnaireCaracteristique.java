/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Profil;

import Entites.Profil.Caracteristique;
import Entites.User.Utilisateur;
import Services.Gestionnaire;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nadia
 */
public class GestionnaireCaracteristique implements Gestionnaire <Caracteristique> {

    @Override
    public int create(Caracteristique c) throws SQLException {

        String query = " insert into Caracteristique (corpulence,pilosite,origine,profession,alcool,tabac,taille,cheveux,yeux ,caractere, statut ,cuisine) values (?,?,?,?,?,?,?,?,?,?,?,?) "; // preparation du query

        PreparedStatement pst = DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment

        pst.setString(1, c.getCorpulence());// Binding du premier valeur mentionner dans le query "?" 
        pst.setString(2, c.getPilosite());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(3, c.getOrigine());
        pst.setString(4, c.getProfession());
        pst.setString(5, c.getAlcool());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(6, c.getTabac());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(7, c.getTaille());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(8, c.getCheveux());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(9, c.getYeux());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(10, c.getCaractere());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(11, c.getStatut());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(12, c.getCuisine());//Binding du deuxieme valeur mentionner dans le query "?" 

        return pst.executeUpdate();
    } // Execution et retour du resultat du query     }

    @Override
    public int update(Caracteristique c) throws SQLException {
        String query = " update  Caracteristique set corpulence=?,pilosite=?,origine=?,profession=?,alcool=?,tabac=?,taille=?,cheveux=?,yeux=? ,caractere=?, statut=? ,cuisine=? where id=?  "; // preparation du query
        PreparedStatement pst = DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment

        pst.setString(1, c.getCorpulence());// Binding du premier valeur mentionner dans le query "?" 
        pst.setString(2, c.getPilosite());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(3, c.getOrigine());
        pst.setString(4, c.getProfession());
        pst.setString(5, c.getAlcool());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(6, c.getTabac());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(7, c.getTaille());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(8, c.getCheveux());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(9, c.getYeux());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(10, c.getCaractere());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(11, c.getStatut());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(12, c.getCuisine());//Binding du deuxieme valeur mentionner dans le query "?" 

        return pst.executeUpdate();
    } // Execution et retour du resultat du query     }

    @Override
    public int remove(Caracteristique c) throws SQLException {

        String query = "delete  from caracteristique where ID=? ";

        PreparedStatement pst = DB.prepareStatement(query);

        pst.setInt(1, c.getID());

        return pst.executeUpdate();
    }

    @Override
    public List<Caracteristique> fetchAll() throws SQLException {
        String query = "select * from caracteristique ";
        PreparedStatement pst = DB.prepareStatement(query);
        ResultSet res = pst.executeQuery();
        List<Caracteristique> Caracteristiques = new ArrayList<>();

        while (res.next()) {
            Caracteristiques.add(new Caracteristique(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getString(11), res.getString(12), res.getString(13)));
        }
        return Caracteristiques;
    }

    
    @Override
    public Caracteristique fetchOneById(int id) {
        Caracteristique c = null;
        String query = "select * from caracteristique where id=? ";
        PreparedStatement pst = null;
        try {
            pst = DB.prepareStatement(query);
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCaracteristique.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            pst.setInt(1, id);
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCaracteristique.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet res = null;
        try {
            res = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCaracteristique.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            res.next();
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCaracteristique.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            c = new Caracteristique(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getString(11), res.getString(12), res.getString(13));
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireCaracteristique.class.getName()).log(Level.SEVERE, null, ex);
        }

        return c;
    }

    public boolean algorithme_de_syntaxe(String str1, String str2) {
        if ((str1.toLowerCase().contains(str2.toLowerCase())) || (str2.toLowerCase().contains(str1.toLowerCase()))) {
            return true;
        } else {
            return false;
        }
    }

    /////////////////////////////////// PARTIE MATCHING /////////////////////////////
    public int matching_selon_preference(Utilisateur cl1, Utilisateur cl2) {
        int resultat_matching_preference = 0;

        System.out.println("client 2:" + cl2.getProfil().getPreference());
        System.out.println("client 1:" + cl1.getProfil().getCaracteristique_id());
      
        if (cl1.getProfil().getCaracteristique_id().getCorpulence() .equals(cl2.getProfil().getPreference().getCorpulence())) {
               
            resultat_matching_preference += 8;
        } else {
        }

        if (cl1.getProfil().getCaracteristique_id().getPilosite() .equals( cl2.getProfil().getPreference().getPilosite())) {
            resultat_matching_preference += 8;
        }

        if (cl1.getProfil().getCaracteristique_id().getAlcool().equals(cl2.getProfil().getPreference().getAlcool())) {
            resultat_matching_preference += 8;
        }

        if (cl1.getProfil().getCaracteristique_id().getAlcool().equals(cl2.getProfil().getPreference().getAlcool()) ){
            resultat_matching_preference += 8;
        }

        if (cl1.getProfil().getCaracteristique_id().getCheveux().equals( cl2.getProfil().getPreference().getCheveux()) ){
            resultat_matching_preference += 8;
        }

        if (cl1.getProfil().getCaracteristique_id().getYeux().equals( cl2.getProfil().getPreference().getYeux())) {
            resultat_matching_preference += 8;
        }

        if (cl1.getProfil().getCaracteristique_id().getCaractere().equals( cl2.getProfil().getPreference().getCaractere())) {
            resultat_matching_preference += 8;
        }

        if (cl1.getProfil().getCaracteristique_id().getStatut() .equals( cl2.getProfil().getPreference().getStatut())) {
            resultat_matching_preference += 8;
        }

        if (cl1.getProfil().getCaracteristique_id().getCuisine() .equals( cl2.getProfil().getPreference().getCuisine()) ){
            resultat_matching_preference += 8;
        }

        if (algorithme_de_syntaxe(cl1.getProfil().getCaracteristique_id().getProfession(), cl2.getProfil().getPreference().getProfession()) == true) {
            resultat_matching_preference += 8;
        }

        //if taille selon H ou F
        return resultat_matching_preference;
    }

    /////////////////////////////////// PARTIE MATCHING /////////////////////////////

    @Override
    public Caracteristique fetchOnByCriteria(Map<String, String> Criteras) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Caracteristique> fetchSomeBy(String aux, String target_column, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Caracteristique> fetchSomeBy(String aux, int target_column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Caracteristique> fetchSomeBy(String aux, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
