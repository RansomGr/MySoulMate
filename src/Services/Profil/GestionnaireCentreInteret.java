/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Profil;

import Entites.Profil.Centre_interet;
import Services.Gestionnaire;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Sofiene
 */
public class GestionnaireCentreInteret implements Gestionnaire  {

    @Override
    public int create(Object o) throws SQLException {
Centre_interet a=(Centre_interet)o;// down Cast
   
        String query=" insert into centre_interet (nom) values (?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setString(1,a.getNom());// Binding du premier valeur mentionner dans le query "?" 
       

       
         return pst.executeUpdate(); } // Execution et retour du resultat du query     }

    @Override
    public int update(Object o) throws SQLException {
Centre_interet a = (Centre_interet )o;// down Cast du Object => Admin 
        String query=" update centre_interet set nom=? where id=?  ";    
    PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setString(1,a.getNom());// Binding du premier valeur mentionner dans le query "?" 
       

       
         return pst.executeUpdate();}

    @Override
    public int remove(Object o) throws SQLException {
Centre_interet a=(Centre_interet)o;
        
        String query="delete  from centre_interet where ID=? ";
        PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setString(1,a.getNom());// Binding du premier valeur mentionner dans le query "?" 
       
                    return pst.executeUpdate();    }

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