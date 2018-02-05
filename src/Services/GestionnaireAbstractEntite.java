/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.AbstractEntite;

import static Services.Gestionnaire.DB;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author Ransom
 */
public abstract class GestionnaireAbstractEntite implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
        AbstractEntite e = (AbstractEntite)o;// down Cast
        String query=" insert into Entite (ID,nom) values (?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1, e.getID());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2, e.getNom());//Binding du deuxieme valeur mentionner dans le query "?" 
   
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int update(Object o) throws SQLException {
      
        AbstractEntite e = (AbstractEntite)o;// down Cast du Object => Reclamation 
        String query=" update  Entite set ID=?,nom=? where ID=? "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1, e.getID());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2, e.getNom());//Binding du deuxieme valeur mentionner dans le query "?" 
         
         pst.setInt(3, e.getID());//Binding du valeur de l'id mentionné dans le query "?" 
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int remove(Object o) throws SQLException {
         AbstractEntite e = (AbstractEntite)o;// down Cast du Object => Reclamation 
        String query=" delete from  Entite  where ID=? "; // preparation du query delete 

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1, e.getID());//Binding du valeur de l'id mentionné dans le query "?" 
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

   
    
}
