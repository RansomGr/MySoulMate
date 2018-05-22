/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Events;

import Entites.Events.Events;
import Entites.Events.InviteEvents;
import Entites.User.Utilisateur;
import Services.Gestionnaire;
import static Services.Gestionnaire.DB;
import Services.User.GestionnaireUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dellpro
 */
public class GestionnaireInviteEvent implements Gestionnaire{

    public int create(InviteEvents o) throws SQLException {
    InviteEvents ivtevt=(InviteEvents)o;
      String query="insert into invite_evenement(evenement_groupe,client) values(?,?,'non')";
      PreparedStatement pst= DB.prepareStatement(query);
      pst.setInt(1, ivtevt.getEvts().getId());
      pst.setInt(2,ivtevt.getInvite().getId());
           
      return pst.executeUpdate();    
    }

    public int update(InviteEvents o) throws SQLException {
        InviteEvents inevt=o;
        String query ="update invite_evenement set events=?,client=?  where events=? and client=? ";
      
      PreparedStatement pst=DB.prepareStatement(query);
      
        pst.setInt(1, inevt.getEvts().getId());
        pst.setInt(2,inevt.getInvite().getId());
//        pst.setInt(3, inevt.getEvtgroup().getID());
//        pst.setInt(4,inevt.getClt().getID());
       return pst.executeUpdate();  
    }

    @Override
    public int remove(Object o) throws SQLException {
        InviteEvents inevt=(InviteEvents)o;
        String query=" delete from invite_evenement where events=? and client=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    
     pst.setInt(1, inevt.getEvts().getId());
     pst.setInt(2,inevt.getInvite().getId());
    
    return pst.executeUpdate();}

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
          String query=" select * from invite_evenement  "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<InviteEvents>InviteEvent = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          List<Utilisateur>Clients ;
          List<Events>events;
          GestionnaireEvent g = new GestionnaireEvent();
          GestionnaireUser c= new GestionnaireUser();
          Clients=(List<Utilisateur>) c.fetchAll();
          events=(List<Events>) g.fetchAll();
          while(res.next())// parcour du result set
          {
              int Client_ID_in_question=res.getInt(2);
              int Event_ID_in_question=res.getInt(1);
              Utilisateur clt =Clients.stream().filter(c1->c1.getId()==Client_ID_in_question).findFirst().get();
              Events evt = events.stream().filter(e1->e1.getId()==Event_ID_in_question).findFirst().get();
             InviteEvent.add(new InviteEvents(evt,clt));
           }
          return InviteEvent;
    }
    // correct

//    @Override
//    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

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
    
}
