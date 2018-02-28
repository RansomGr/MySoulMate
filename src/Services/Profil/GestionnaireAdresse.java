/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Profil;

import Entites.Profil.Adresse;
import Services.Gestionnaire;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sofiene
 */
public class GestionnaireAdresse implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
Adresse a=(Adresse)o;// down Cast
   
        String query=" insert into Adresse (numero,gouvernorat,ville,code_postal) values (?,?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1,a.getNumero());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2,a.getGouvernorat());//Binding du deuxieme valeur mentionner dans le query "?" 
            pst.setString(3,a.getVille());
             pst.setInt(4,a.getCode_postal());

       
         return pst.executeUpdate(); }// Execution et retour du resultat du query     

    @Override
    public int update(Object o) throws SQLException {
Adresse a = (Adresse)o;// down Cast du Object => Admin 
        String query=" update Adresse set numero=?,gouvernorat=?,ville=?,code_postal=? where id=?  "; // preparation du query

           PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1,a.getNumero());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2,a.getGouvernorat());//Binding du deuxieme valeur mentionner dans le query "?" 
            pst.setString(3,a.getVille());
             pst.setInt(4,a.getCode_postal());
             pst.setInt(5,a.getID());

       
         return pst.executeUpdate();     }

    @Override
    public int remove(Object o) throws SQLException {
Adresse a=(Adresse)o;
        
        String query="delete from adresse where ID=? ";
        
        PreparedStatement pst=DB.prepareStatement(query);
        
        pst.setInt(1,a.getID());
        
        return pst.executeUpdate();     }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
      
          String query=" select * from entite "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Adresse>AbstractEntites = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
             AbstractEntites.add(new Adresse(res.getInt(1),res.getInt(2),res.getString(3),res.getInt(4),res.getString(5)));
          }
          return AbstractEntites;
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

//done!