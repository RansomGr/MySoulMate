/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Profil;

import Entites.Profil.Profil;
import Services.Gestionnaire;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Sofiene
 */
public class GestionnaireProfil implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
       Profil a=(Profil)o;// down Cast
   
        String query=" insert into Profil (caracteristique,photo,description,preference) values (?,?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1,a.getCaracteristique().getID());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2,a.getPhoto());//Binding du deuxieme valeur mentionner dans le query "?" 
            pst.setString(3,a.getDescription());
             pst.setInt(4,a.getPreference().getID());

       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }    

    @Override
    public int update(Object o) throws SQLException {
     Profil a = (Profil)o;// down Cast du Object => Admin 
        String query=" update  Profil set caracteristique=?,photo=?,description=?,preference=? where id=?  "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1,a.getCaracteristique().getID() );// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2,a.getPhoto());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(3,a.getDescription());
         pst.setInt(4, a.getPreference().getID());//Binding du valeur de l'id mentionné dans le query "?" 
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int remove(Object o) throws SQLException {
 Profil a=(Profil)o;
        
        String query="delete  from Profil where ID=? ";
        
        PreparedStatement pst=DB.prepareStatement(query);
        
        pst.setInt(1,a.getId());
        
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
