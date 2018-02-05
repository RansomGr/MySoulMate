/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.User;

import Entites.User.Reclamation;
import Services.Gestionnaire;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ransom
 */
public class GestionnaireReclamation implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException
    {
        Reclamation r = (Reclamation)o;// down Cast
        String query=" insert into reclamation (sujet,description) values (?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setString(1, r.getSujet());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2, r.getDescription());//Binding du deuxieme valeur mentionner dans le query "?" 
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int update(Object o) throws SQLException {
 
        Reclamation r = (Reclamation)o;// down Cast du Object => Reclamation 
        String query=" update  reclamation set sujet=?,description=? where id=? "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setString(1, r.getSujet());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2, r.getDescription());//Binding du deuxieme valeur mentionner dans le query "?" 
         
         pst.setInt(3, r.getID());//Binding du valeur de l'id mentionné dans le query "?" 
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int remove(Object o) throws SQLException {
      Reclamation r = (Reclamation)o;// down Cast du Object => Reclamation 
        String query=" delete from  reclamation  where id=? "; // preparation du query delete 

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1, r.getID());//Binding du valeur de l'id mentionné dans le query "?" 
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException
    {
          String query=" select id,sujet,description from Reclamation "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Reclamation>Reclamations = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
             Reclamation r= new Reclamation(res.getInt("id"),res.getString(2),res.getString(3));
             Reclamations.add(r);
           }
          return Reclamations;
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
