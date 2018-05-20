/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Matching;
import Entites.Matching.Invitation;
import Entites.User.Utilisateur;

import Services.Gestionnaire;
import Services.User.GestionnaireUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nadia
 */
public class GestionnaireInvitation implements Gestionnaire<Invitation>{

    @Override
    public int create( Invitation I) throws SQLException {
        
        String query=" insert into invitation (client1,client2,string,date) values (?,?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1,I.getClient1().getId() );// Binding du premier valeur mentionner dans le query "?" 
         pst.setInt(2, I.getClient2().getId() );//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(3,I.getStatut());
         pst.setDate(4,I.getDate_amitie());
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    
    }

    @Override
    public int update(Invitation I) throws SQLException {
        
        String query=" update  invitation set client1=?, client2=?, statut=?, date_amitie=? where client1=? and client2=? "; // preparation du query

           PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
            pst.setInt(1,I.getClient1().getId() );// Binding du premier valeur mentionner dans le query "?" 
            pst.setInt(2, I.getClient2().getId());//Binding du deuxieme valeur mentionner dans le query "?" 
            pst.setString(3, I.getStatut());//Binding du deuxieme valeur mentionner dans le query "?" 
             pst.setDate(4, I.getDate_amitie() );// Binding du premier valeur mentionner dans le query "?" 
    
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int remove(Invitation I) throws SQLException {
       
        String query=" delete from  invitation  where client1=? and client2=?  "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         pst.setInt(1, I.getClient1().getId());//Binding du valeur de l'id mentionné dans le query "?" 
         pst.setInt(2, I.getClient2().getId());//Binding du valeur de l'id mentionné dans le query "?" 
         return pst.executeUpdate(); // Execution et retour du resultat du query     
    
    }
    
@Override
    public List<Invitation> fetchAll() throws SQLException {
    String query=" select *  from  invitation "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Invitation>Invitations = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          GestionnaireUser g=new GestionnaireUser();
           List<Utilisateur> Clients=(List<Utilisateur>) g.fetchAll();
          while(res.next())// parcour du result set
          {
             int Client1_ID=res.getInt("client1");
             int Client2_ID=res.getInt("client2");

              Utilisateur client1=Clients.stream().filter(c->c.getId()==Client1_ID).findFirst().get();
              Utilisateur client2=Clients.stream().filter(c->c.getId()==Client2_ID).findFirst().get();

            Invitations.add(new Invitation(
                    client1,
                    client2,
                    res.getString("statut"),
                    res.getDate("date_amitie")      )
            );
           }
          return Invitations;   
    
    }

    @Override
    public Invitation fetchOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Invitation fetchOnByCriteria(Map<String, String> Criteras) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Invitation> fetchSomeBy(String aux, String target_column, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Invitation> fetchSomeBy(String aux, int target_column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Invitation> fetchSomeBy(String aux, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
