/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services.User;

import Entites.User.Admin;
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
public class GestionnaireAdmin implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
          Admin a = (Admin)o;// down Cast
        String query=" insert into Admin (nom,prenom,motdepasse,login) values (?,?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setString(1, a.getNom());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2, a.getPrenom());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(3, a.getMotdepasse());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(4, a.getLogin());
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int update(Object o) throws SQLException {
     Admin a = (Admin)o;// down Cast du Object => Admin 
        String query=" update  Admin set nom=?,prenom=?,motdepasse=?,login=? where id=?  "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setString(1,a.getNom() );// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2,a.getPrenom());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(3,a.getMotdepasse());
         pst.setString(4,a.getLogin());
         pst.setInt(5, a.getID());//Binding du valeur de l'id mentionné dans le query "?" 
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int remove(Object o) throws SQLException {
        System.out.println(o);
         Admin a = (Admin)o;// down Cast du Object => Admin 
        String query=" delete from  Admin  where id=?  "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         pst.setInt(1, a.getID());//Binding du valeur de l'id mentionné dans le query "?" 
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
        
          String query=" select * from Admin "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Admin>Admins = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
             Admins.add(new Admin(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5)) );
           }
          return Admins;
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
          String query=" select * from Admin where ( nom like ? or prenom like ? or login like ? or motdepasse like ? or ID like ? ) "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Admin>Admins = new ArrayList<>();//  Creation du List Reclamation
           pst.setString(1, "%"+aux+"%");
           pst.setString(2, "%"+aux+"%");
           pst.setString(3, "%"+aux+"%");
           pst.setString(4, "%"+aux+"%");
            pst.setString(5, "%"+aux+"%");
     
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
             Admins.add(new Admin(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5)) );
           }
          return Admins;
    }
      public List<? extends Object> fetchAll(String aux, String target_column ,int StartPoint,int BreakPoint) throws SQLException {
          String query=" select * from (select * from Admin limit  "+StartPoint+","+BreakPoint+" ) Admin_l "
                  + " where ( "+target_column+" like ?  )  " ;
                 // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Admin>Admins = new ArrayList<>();//  Creation du List Reclamation
           pst.setString(1, "%"+aux+"%");
         
     
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
             Admins.add(new Admin(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5)) );
           }
          return Admins;
    }
            public List<? extends Object> fetchAll(String aux,int StartPoint,int BreakPoint) throws SQLException {
          String query=" select * from (select * from Admin limit  "+StartPoint+","+BreakPoint+" ) Admin_l "
                  + "where ( nom like ? or prenom like ? or login like ? or motdepasse like ? or ID like ? )"   ; // preparation du requete sql
              
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Admin>Admins = new ArrayList<>();//  Creation du List Reclamation
           pst.setString(1, "%"+aux+"%");
          pst.setString(2, "%"+aux+"%");
           pst.setString(3, "%"+aux+"%");
           pst.setString(4, "%"+aux+"%");
            pst.setString(5, "%"+aux+"%");
     
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
             Admins.add(new Admin(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5)) );
           }
          return Admins;
    }
            
    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
