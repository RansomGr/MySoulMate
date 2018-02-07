/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Matching;
import Entites.Matching.Centre_interet;
import Services.Gestionnaire;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Nadia
 */
public class GestionnaireCentre_interet implements Gestionnaire {
 @Override
    public int create(Object o) throws SQLException {
        Centre_interet e = (Centre_interet)o;// down Cast
        String query=" insert into centre_interet (ID,nom) values (?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1, e.getID());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2, e.getNom());//Binding du deuxieme valeur mentionner dans le query "?" 
   
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int update(Object o) throws SQLException {
      
        Centre_interet e = (Centre_interet)o;// down Cast du Object => Reclamation 
        String query=" update  Entite set ID=?,nom=? where ID=? "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1, e.getID());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2, e.getNom());//Binding du deuxieme valeur mentionner dans le query "?" 
         
         pst.setInt(3, e.getID());//Binding du valeur de l'id mentionné dans le query "?" 
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int remove(Object o) throws SQLException {
         Centre_interet e = (Centre_interet)o;// down Cast du Object => Reclamation 
        String query=" delete from  Entite  where ID=? "; // preparation du query delete 

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1, e.getID());//Binding du valeur de l'id mentionné dans le query "?" 
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
