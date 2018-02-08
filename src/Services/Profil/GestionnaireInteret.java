
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Profil;

import Entites.Profil.Interet;
import Services.Gestionnaire;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Sofiene
 */
public class GestionnaireInteret implements Gestionnaire{

    @Override
    public int create(Object o) throws SQLException {
Interet a=(Interet)o;// down Cast
   
        String query=" insert into interet (client,centre_interet) values (?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1,a.getClient().getID());// Binding du premier valeur mentionner dans le query "?" 
         pst.setInt(2,a.getCentre_interet().getID());

       
         return pst.executeUpdate();    }

    @Override
    public int update(Object o) throws SQLException {
Interet a = (Interet)o;// down Cast du Object => Admin 
        String query=" update  Interet set client=?,centre_interet=? where id=?  "; // preparation du query 
    PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1,a.getClient().getID());// Binding du premier valeur mentionner dans le query "?" 
         pst.setInt(2,a.getCentre_interet().getID());

       
         return pst.executeUpdate();}

    @Override
    public int remove(Object o) throws SQLException {
Interet a=(Interet)o;
        
        String query="delete  from interet where client=? and centre_interet=?";
        
        PreparedStatement pst=DB.prepareStatement(query);
        
        pst.setInt(1,a.getClient().getID());
                pst.setInt(1,a.getCentre_interet().getID());

        
        return pst.executeUpdate()  ;  }

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
