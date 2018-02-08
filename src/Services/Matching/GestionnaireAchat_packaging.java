/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Matching;
import Entites.Matching.Achat_packaging;
import Entites.Matching.Packaging;
import Entites.User.Client;
import Services.Gestionnaire;
import Services.User.GestionnaireClient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nadia
 */
public class GestionnaireAchat_packaging implements Gestionnaire{

    @Override
    public int create(Object o) throws SQLException {
         Achat_packaging ap = (Achat_packaging)o;
        String query=" insert into achat_packaging (client,packaging,date_fin) values (?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1,ap.getC().getID() );// Binding du premier valeur mentionner dans le query "?" 
         pst.setInt(2, ap.getP().getID());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setDate(3, ap.getDate_fin());
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int update(Object o) throws SQLException {
        Achat_packaging ap = (Achat_packaging)o;
        String query=" update  achat_packaging set client=?, packaging=?,date_fin=? where client=? and packaging=? "; // preparation du query

           PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
          pst.setInt(1,ap.getC().getID() );// Binding du premier valeur mentionner dans le query "?" 
          pst.setInt(2, ap.getP().getID());//Binding du deuxieme valeur mentionner dans le query "?" 
          pst.setDate(3, ap.getDate_fin());
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int remove(Object o) throws SQLException {
          Achat_packaging ap = (Achat_packaging)o;
        String query=" delete from  achat_packaging  where client=? and packaging=?  "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         pst.setInt(1, ap.getC().getID());//Binding du valeur de l'id mentionné dans le query "?" 
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
          GestionnaireClient g= new GestionnaireClient();
          GestionnairePackaging p=new GestionnairePackaging();
          
          List<Client> clients=(List<Client>) g.fetchAll();
          List<Packaging>packagings =(List<Packaging>) p.fetchAll();
          while(res.next())// parcour du result set
          {
              int Client_ID_in_question=res.getInt(1);
              int Packaging_ID_in_question=res.getInt(2);
              Client client=clients.stream().filter(x->x.getID()==Client_ID_in_question).findFirst().get();
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

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
