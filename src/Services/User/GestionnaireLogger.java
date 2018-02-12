/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.User;

import Entites.User.Action;
import Entites.User.Admin;
import Entites.User.Logger;
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
public class GestionnaireLogger implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
         Logger l = (Logger)o;// down Cast
        String query=" insert into Logger (admin,action) values (?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1,l.getAdmin().getID() );// Binding du premier valeur mentionner dans le query "?" 
         pst.setInt(2, l.getAction().getID());//Binding du deuxieme valeur mentionner dans le query "?" 
    
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int update(Object o) throws SQLException {
           Logger l = (Logger)o;// down Cast
        String query=" update  Logger set admin=?, action=?,dateheure=? where admin=? and action=? "; // preparation du query

           PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
            pst.setInt(1,l.getAdmin().getID() );// Binding du premier valeur mentionner dans le query "?" 
            pst.setInt(2, l.getAction().getID());//Binding du deuxieme valeur mentionner dans le query "?" 
            pst.setDate(3, l.getDateLogger());//Binding du deuxieme valeur mentionner dans le query "?" 
             pst.setInt(4,l.getAdmin().getID() );// Binding du premier valeur mentionner dans le query "?" 
             pst.setInt(5, l.getAction().getID());//Binding du deuxieme valeur mentionner dans le query "?" 
    
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int remove(Object o) throws SQLException {
       Logger l = (Logger)o;// down Cast du Object => Logger 
        String query=" delete from  Logger  where admin=? and action=?  "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         pst.setInt(1, l.getAdmin().getID());//Binding du valeur de l'id mentionné dans le query "?" 
         pst.setInt(2, l.getAction().getID());//Binding du valeur de l'id mentionné dans le query "?" 
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
       
          String query=" select  *  from Logger"
                  + " inner join Admin"
                  + " on Logger.Admin=Admin.id "
                  + "inner join Action "
                  + "on Logger.Action=Action.id  "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Logger>Loggers = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
             Loggers.add(
                     new Logger(
                                     new Action(res.getInt(9),res.getInt(10),res.getString(11)),
                                     new Admin(res.getInt(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8)),
                                      res.getDate(3)
                                      )
                              );
           }
          return Loggers;
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
