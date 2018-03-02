/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Evenement;

import Entites.Evenement.Events;
import Entites.Evenement.InviteEvenement;
import Entites.Events.InviteEvents;
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
 * @author dellpro
 */
public class GestionnaireInviteEvents implements Gestionnaire{

    @Override
    public int create(Object o) throws SQLException {
         InviteEvents ivtevt=(InviteEvents)o;
      String query="insert into invite_evenement(evenement_groupe,client) values(?,?)";
      PreparedStatement pst= DB.prepareStatement(query);
      pst.setInt(1, ivtevt.getEvts().getID());
      pst.setInt(2,ivtevt.getInvite().getID());
           
      return pst.executeUpdate();
    }

    @Override
    public int update(Object o) throws SQLException {
        InviteEvenement ivtevt=(InviteEvenement)o;
        String query ="update invite_evenement set evenement_groupe=?,client=?  where evenement_groupe=? and client=? ";
      
      PreparedStatement pst=DB.prepareStatement(query);
      
        pst.setInt(1, ivtevt.getEvtgroup().getID());
        pst.setInt(2,ivtevt.getClt().getID());
        pst.setInt(3, ivtevt.getEvtgroup().getID());
        pst.setInt(4,ivtevt.getClt().getID());
      
      return pst.executeUpdate(); 
    }

    @Override
    public int remove(Object o) throws SQLException {
        InviteEvenement ivtevt=(InviteEvenement)o;
        String query=" delete from invite_evenement where evenement_groupe=? and client=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    
     pst.setInt(1, ivtevt.getEvtgroup().getID());
     pst.setInt(2,ivtevt.getClt().getID());
    
    return pst.executeUpdate();
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
       String query=" select * from invite_evenement  "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<InviteEvenement>InviteEvenements = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          List<Client>Clients ;
          List<Events>events;
          GestionnaireEvenementGroup g = new GestionnaireEvenementGroup();
          GestionnaireClient c= new GestionnaireClient();
          Clients=(List<Client>) c.fetchAll();
          events=(List<Events>) g.fetchAll();
          while(res.next())// parcour du result set
          {
              int Client_ID_in_question=res.getInt(2);
              int Event_ID_in_question=res.getInt(1);
              Client clt =Clients.stream().filter(c1->c1.getID()==Client_ID_in_question).findFirst().get();
              Events evt = events.stream().filter(e1->e1.getID()==Event_ID_in_question).findFirst().get();
             InviteEvenements.add(new InviteEvenement(evt,clt));
           }
          return InviteEvenements;
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
