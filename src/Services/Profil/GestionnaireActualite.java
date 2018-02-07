/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Profil;

import Entites.Profil.Actualite;
import Services.Gestionnaire;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Sofiene
 */
public class GestionnaireActualite implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
Actualite a=(Actualite)o;// down Cast
   
        String query=" insert into Actualite (entite,contenu,photo) values (?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1,a.getEntite().getID());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(3,a.getPhoto());//Binding du deuxieme valeur mentionner dans le query "?" 
            pst.setString(2,a.getContenu());

       
         return pst.executeUpdate();} // Execution et retour du resultat du query     }

    @Override
    public int update(Object o) throws SQLException {
Actualite a = (Actualite)o;// down Cast du Object => Admin 
        String query=" update  Actualite set entite=?,contenu=?,photo=? where id=?  "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
             
         pst.setInt(1,a.getEntite().getID());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(3,a.getPhoto());//Binding du deuxieme valeur mentionner dans le query "?" 
            pst.setString(2,a.getContenu());

       
         return pst.executeUpdate(); }

    @Override
    public int remove(Object o) throws SQLException {
Actualite a=(Actualite)o;
        
        String query="delete  from Actualite where ID=? ";
        
        PreparedStatement pst=DB.prepareStatement(query);
        
        pst.setInt(1,a.getID());
        
        return pst.executeUpdate();      }

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
