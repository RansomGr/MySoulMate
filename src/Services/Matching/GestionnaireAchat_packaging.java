/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Matching;
import Entites.Matching.Achat_packaging;
import Entites.Matching.Packaging;

import Entites.User.Utilisateur;
import Services.Gestionnaire;
import Services.User.GestionnaireUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author Nadia
 */
public class GestionnaireAchat_packaging implements Gestionnaire{

    public int create(Achat_packaging ap) throws SQLException {
        String query=" insert into achat_packaging (client,packaging,date_fin) values (?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1,ap.getC().getId() );// Binding du premier valeur mentionner dans le query "?" 
         pst.setInt(2, ap.getP().getID());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setDate(3, ap.getDate_fin());
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    public int update(Achat_packaging ap) throws SQLException {

        String query=" update  achat_packaging set client=?, packaging=?,date_fin=? where client=? and packaging=? "; // preparation du query

           PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
          pst.setInt(1,ap.getC().getId() );// Binding de la première valeur mentionner dans le query "?" 
          pst.setInt(2, ap.getP().getID());//Binding du deuxieme valeur mentionner dans le query "?" 
          pst.setDate(3, ap.getDate_fin());
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    public int remove(Achat_packaging ap) throws SQLException {
  
        String query=" delete from  achat_packaging  where client=? and packaging=?  "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         pst.setInt(1, ap.getC().getId());//Binding du valeur de l'id mentionné dans le query "?" 
         pst.setInt(2, ap.getP().getID());//Binding du valeur de l'id mentionné dans le query "?" 
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
       
          String query=" select * from achat_packaging ";
                                            // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Achat_packaging>Loggers = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          GestionnaireUser g= new GestionnaireUser();
          GestionnairePackaging p=new GestionnairePackaging();
          
          List<Utilisateur> clients=(List<Utilisateur>) g.fetchAll();
          List<Packaging>packagings =(List<Packaging>) p.fetchAll();
          while(res.next())// parcour du result set
          {
              int Client_ID_in_question=res.getInt(1);
              int Packaging_ID_in_question=res.getInt(2);
              Utilisateur client=clients.stream().filter(x->x.getId()==Client_ID_in_question).findFirst().get();
              Packaging packaging=packagings.stream().filter(x->x.getID()==Packaging_ID_in_question).findFirst().get();
             Loggers.add(
                     new Achat_packaging(
                                    client,
                                    packaging,
                                   res.getDate(3)
                              ));
           }
          return Loggers;
    }

    public List<? extends Achat_packaging > fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<? extends Achat_packaging> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


  

    @Override
    public Object fetchOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object fetchOnByCriteria(Map Criteras) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List fetchSomeBy(String aux, String target_column, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List fetchSomeBy(String aux, int target_column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List fetchSomeBy(String aux, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int create(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int remove(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
