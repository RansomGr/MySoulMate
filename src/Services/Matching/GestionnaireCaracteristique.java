/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Matching;
import Entites.Matching.Caracteristique;
import Services.Gestionnaire;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nadia
 */

public class GestionnaireCaracteristique implements Gestionnaire<Caracteristique>{

    public int create(Caracteristique c) throws SQLException {
        
        String query=" insert into Profil (corpulence,pilosite,origine,profession,alcool,tabac,taille,cheveux,yeux ,caractere, statut ,cuisine) values (?,?,?,?,?,?,?,?,?,?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setString(1,c.getCorpulence());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2,c.getPilosite());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(3,c.getOrigine());
         pst.setString(4,c.getProfession());
         pst.setString(5,c.getAlcool());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(6,c.getTabac());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(7,c.getTaille());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(8,c.getCheveux());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(9,c.getYeux());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(10,c.getCaractere());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(11,c.getStatut());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(12,c.getCuisine());//Binding du deuxieme valeur mentionner dans le query "?" 

       
         return pst.executeUpdate();} // Execution et retour du resultat du query     }

    @Override
    public int update(Caracteristique c) throws SQLException {
    String query=" update  Caracteristique set corpulence=?,pilosite=?,origine=?,profession=?,alcool=?,tabac=?,taille=?,cheveux=?,yeux=? ,caractere=?, statut=? ,cuisine=? where id=?  "; // preparation du query
    PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setString(1,c.getCorpulence());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2,c.getPilosite());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(3,c.getOrigine());
         pst.setString(4,c.getProfession());
         pst.setString(5,c.getAlcool());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(6,c.getTabac());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(7,c.getTaille());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(8,c.getCheveux());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(9,c.getYeux());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(10,c.getCaractere());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(11,c.getStatut());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(12,c.getCuisine());//Binding du deuxieme valeur mentionner dans le query "?" 

       
         return pst.executeUpdate();} // Execution et retour du resultat du query     }


    public int remove(Caracteristique c) throws SQLException {
    
    String query="delete  from caracteristique where ID=? ";
        
    PreparedStatement pst=DB.prepareStatement(query);
        
    pst.setInt(1,c.getID());
        
    return pst.executeUpdate();  
    }

  

    
    public List<? extends Caracteristique > fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<? extends Caracteristique > fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Caracteristique fetchOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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

    @Override
    public List<Caracteristique> fetchAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

   
}
