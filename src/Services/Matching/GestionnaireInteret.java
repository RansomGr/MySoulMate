/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Matching;
import Entites.Matching.Centre_interet;
import Entites.Matching.Interet;
import Entites.User.Client;
import Services.Gestionnaire;
import static Services.Gestionnaire.DB;
import Services.User.GestionnaireClient;
import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Nadia
 */
public class GestionnaireInteret implements Gestionnaire {

    
        @Override
    public int create(Object o) throws SQLException {
         Interet i = (Interet)o;// down Cast
        String query=" insert into interet (client,centre_interet) values (?,?) "; // preparation du query
        PreparedStatement pst=DB.prepareStatement(query);
        
         pst.setInt(1,i.getC().getID() );// Binding du premier valeur mentionner dans le query "?" 
         pst.setInt(2, i.getCi().getID());//Binding du deuxieme valeur mentionner dans le query "?" 
    
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int update(Object o) throws SQLException {
           Interet i = (Interet)o;// down Cast
        String query=" update  interet set client=?, centre_interet=? where client=? and centre_interet=? "; // preparation du query

           PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
           
         
            pst.setInt(1,i.getC().getID() );// Binding du premier valeur mentionner dans le query "?" 
            pst.setInt(2, i.getCi().getID());//Binding du deuxieme valeur mentionner dans le query "?" 
             pst.setInt(4,i.getC().getID() );// Binding du premier valeur mentionner dans le query "?" 
             pst.setInt(5, i.getCi().getID());//Binding du deuxieme valeur mentionner dans le query "?" 
             
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int remove(Object o) throws SQLException {
       Interet l = (Interet)o;// down Cast du Object => Logger 
        String query=" delete from  interet  where client=? and centre_interet=?  "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         pst.setInt(1, l.getC().getID());//Binding du valeur de l'id mentionné dans le query "?" 
         pst.setInt(2, l.getCi().getID());//Binding du valeur de l'id mentionné dans le query "?" 
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
       
          String query=" select * from interet ";
                                            // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          
          List<Interet>Interets = new ArrayList<>();//  Creation du List Reclamation
          
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          
          GestionnaireClient g1 = new GestionnaireClient();
          GestionnaireCentre_interet g2 = new GestionnaireCentre_interet();
          
          List <Client> clients = (List<Client>) g1.fetchAll();
          List <Centre_interet> centres_interet = (List<Centre_interet>) g2.fetchAll();
          
          while(res.next())// parcour du result set
          {
              int Client_ID_in_question=res.getInt(1);
              int Centre_interet_ID_in_question=res.getInt(2);
              
              Client client=clients.stream().filter(x->x.getID()==Client_ID_in_question).findFirst().get();
              
              Centre_interet centre_interet =centres_interet.stream().filter(x->x.getID()==Centre_interet_ID_in_question).findFirst().get();
             
              Interets.add(
              
                      new Interet( client, centre_interet ));
           }
          return Interets;
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
