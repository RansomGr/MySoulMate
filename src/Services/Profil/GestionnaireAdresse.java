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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sofiene
 */
public class GestionnaireAdresse implements Gestionnaire<Adresse> {

    @Override
    public int create(Adresse o) throws SQLException {
Adresse a=(Adresse)o;// down Cast
   
        String query=" insert into Adresse (numero,gouvernorat,ville,code_postal) values (?,?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1,a.getNumero());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2,a.getGouvernorat());//Binding du deuxieme valeur mentionner dans le query "?" 
            pst.setString(3,a.getVille());
             pst.setInt(4,a.getCode_postal());

       
         return pst.executeUpdate(); }// Execution et retour du resultat du query     

    @Override
    public int update(Adresse o) throws SQLException {
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
    public int remove(Adresse o) throws SQLException {
Adresse a=(Adresse)o;
        
        String query="delete from adresse where ID=? ";
        
        PreparedStatement pst=DB.prepareStatement(query);
        
        pst.setInt(1,a.getID());
        
        return pst.executeUpdate();     }

    @Override
    public List<Adresse> fetchAll() throws SQLException {
      
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
    public Adresse fetchOneById(int id) {
        try {
            String query=" select * from adresse where id=? "    ; // preparation du requete sql
            
            PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
            pst.setInt(1, id);
           
            ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
            if(res.next())// parcour du result set
            {
               return new Adresse(res.getInt(1),res.getInt(2),res.getString(3),res.getInt(4),res.getString(5));
            }
      
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireAdresse.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Adresse fetchOnByCriteria(Map<String, String> Criteras) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Adresse> fetchSomeBy(String aux, String target_column, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Adresse> fetchSomeBy(String aux, int target_column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Adresse> fetchSomeBy(String aux, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

//done!