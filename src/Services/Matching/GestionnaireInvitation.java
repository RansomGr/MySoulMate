/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Matching;
import Entites.Matching.Invitation;
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
public class GestionnaireInvitation implements Gestionnaire{

    @Override
    public int create(Object o) throws SQLException {
         Invitation I = (Invitation)o;// down Cast
        String query=" insert into invitation (client1,client2,string,date) values (?,?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1,I.getClient1().getID() );// Binding du premier valeur mentionner dans le query "?" 
         pst.setInt(2, I.getClient2().getID() );//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(3,I.getStatut());
         pst.setDate(4,I.getDate_amitie());
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    
    }

    @Override
    public int update(Object o) throws SQLException {
        Invitation I = (Invitation)o;
        String query=" update  invitation set client1=?, client2=?, statut=?, date_amitie=? where client1=? and client2=? "; // preparation du query

           PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
            pst.setInt(1,I.getClient1().getID() );// Binding du premier valeur mentionner dans le query "?" 
            pst.setInt(2, I.getClient2().getID());//Binding du deuxieme valeur mentionner dans le query "?" 
            pst.setString(3, I.getStatut());//Binding du deuxieme valeur mentionner dans le query "?" 
             pst.setDate(4, I.getDate_amitie() );// Binding du premier valeur mentionner dans le query "?" 
    
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int remove(Object o) throws SQLException {
        Invitation I = (Invitation)o;
        String query=" delete from  invitation  where client1=? and client2=?  "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         pst.setInt(1, I.getClient1().getID());//Binding du valeur de l'id mentionné dans le query "?" 
         pst.setInt(2, I.getClient2().getID());//Binding du valeur de l'id mentionné dans le query "?" 
         return pst.executeUpdate(); // Execution et retour du resultat du query     
    
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
    String query=" select *  from  invitation "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Invitation>Invitations = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          GestionnaireClient g=new GestionnaireClient();
           List<Client> Clients=(List<Client>) g.fetchAll();
          while(res.next())// parcour du result set
          {
             int Client1_ID=res.getInt("client1");
             int Client2_ID=res.getInt("client2");

              Client client1=Clients.stream().filter(c->c.getID()==Client1_ID).findFirst().get();
              Client client2=Clients.stream().filter(c->c.getID()==Client2_ID).findFirst().get();

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
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
