/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Events;

import Entites.AbstractEntite;
import Entites.Events.Events;
import Entites.Plan.Plan;
import Entites.User.Utilisateur;
import Services.Gestionnaire;
import Services.Plan.GestionnairePlan;
import Services.User.GestionnaireClient;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author dellpro
 */
public class GestionnaireEvent implements Gestionnaire<Events>{
    @Override
    public int create(Events o) throws SQLException {
         String query;
         PreparedStatement pst;
         Events evt=o;
         if(evt.getOrganisateur()!=null)
         {
      query="INSERT INTO events (nom_evt,date_evt,heure,duree_evt,type_evt,description_evt,plan_evt,nb_max,organisateur_evt) VALUES (?,?,?,?,?,?,?,?,?)";
       pst= DB.prepareStatement(query);
       pst.setInt(10,evt.getOrganisateur().getId());
         }
         else {
             query="INSERT INTO events (nom_evt,date_evt,heure,duree_evt,type_evt,description_evt,plan_evt,nb_max) VALUES (?,?,?,?,?,?,?,?)";
          pst= DB.prepareStatement(query);
         }
      
      pst.setString(1,evt.getNom_evt());
      pst.setDate(2, (Date) evt.getDate_evt());
      pst.setString(3,evt.getHeure_evt());
      pst.setString(4,evt.getDuree_evt());
      pst.setString(5,evt.getType_evt()); 
      pst.setString(6,evt.getDescription_evt());
      pst.setInt(7,evt.getPlan_evt().getId());
      pst.setInt(8,evt.getNb_max());
      return pst.executeUpdate();
    }
    
    @Override
    public int update(Events o) throws SQLException {
      //update(o);
      String query;
      PreparedStatement pst;
      Events evt=o;
      if(evt.getOrganisateur()!=null)
         {
      query ="update events set id=?,nom_evt=?,date_evt=?,heure=?,duree_evt=?,type_evt=?,description_evt=?,plan_evt=?,nb_max=?,organisateur_evt=? where Entite=?";
      pst=DB.prepareStatement(query);
      pst.setInt(10,evt.getOrganisateur().getId());
      pst.setInt(11, evt.getId());
      pst.setInt(1, evt.getId());
      pst.setString(2,evt.getNom_evt());
      pst.setDate(3, (Date) evt.getDate_evt());
      pst.setString(4,evt.getHeure_evt());
      pst.setString(5,evt.getDuree_evt());
      pst.setString(6,evt.getType_evt());
      pst.setString(7,evt.getDescription_evt());
      pst.setInt(8,evt.getPlan_evt().getId());
      pst.setInt(9,evt.getNb_max());
         }
      else 
      {
      query ="update events set id=?,nom_evt=?,date_evt=?,heure=?,duree_evt=?,type_evt=?,description_evt=?,plan_evt=?,nb_max=? where Entite=?";
      pst=DB.prepareStatement(query);
      pst.setInt(1, evt.getId());
      System.out.println("id event"+evt.getId());
      pst.setString(2,evt.getNom_evt());
      pst.setDate(3, (Date) evt.getDate_evt());
      pst.setString(4,evt.getHeure_evt());
      pst.setString(5,evt.getDuree_evt());
      pst.setString(6,evt.getType_evt());
     // pst.setInt(7,evt.getOrganisateur().getID());
      pst.setString(7,evt.getDescription_evt());
      pst.setInt(8,evt.getPlan_evt().getId());
      pst.setInt(9,evt.getNb_max());
      pst.setInt(10, evt.getId());
      }
  
      return pst.executeUpdate(); 

    }
    
     @Override
    public int remove(Events o) throws SQLException {
    
    Events evt=o;
    String query=" delete from events where id=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    pst.setInt(1,evt.getId());
    pst.executeUpdate();
    return remove(o);
    
    }

    public List< Events> fetchAll() throws SQLException {
          
        String query=" select id.nom as nom,Events.*  from  events inner join id on Events.id=id.ID "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Events>Evenements = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          GestionnaireClient gclt = new GestionnaireClient();
          List<Utilisateur>clients= (List<Utilisateur>) gclt.fetchAll();
           GestionnairePlan gpl= new GestionnairePlan();
          List<Plan> Plans= (List<Plan>) gpl.fetchAll();
          while(res.next())// parcour du result set
          {
              int Plan_ID_in_question = res.getInt("plan_evt");
              Plan p= Plans.stream().filter(p1->p1.getId()==Plan_ID_in_question).findFirst().get();
              Utilisateur c=null;
              int Client_ID_in_question =res.getInt("organisateur_evt");
              if(Client_ID_in_question!=0)
              {
               c= clients.stream().filter(c1->c1.getId()==Client_ID_in_question).findFirst().get();   
              }
             Evenements.add(new Events(
                     res.getString("nom_evt"), 
                     res.getDate("date_evt"),
                     res.getString("heure"), 
                     res.getString("duree_evt"),
                     res.getString("type_evt"),
                      c,
                     res.getString("description_evt"),
                     p,
                     res.getInt("nb_max")
             )
             );
           }
         return Evenements;
    }

    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
        String query=" select events.*,id.nom from events inner join id on "
                + "id.id=events.entite "
                + " where ( nom_evt like ? or date_evt like ? or heure like ? or type_evt like ? or plan_evt like ? ) "    ; // preparation du requete sql
        GestionnaireClient gclt = new GestionnaireClient();
          List<Utilisateur>clients= (List<Utilisateur>) gclt.fetchAll();
           GestionnairePlan gpl= new GestionnairePlan();  
        PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Events>evenent = new ArrayList<>();//  Creation du List Reclamation
           pst.setString(1, "%"+aux+"%");
           pst.setString(2, "%"+aux+"%");
           pst.setString(3, "%"+aux+"%");
           pst.setString(4, "%"+aux+"%");
            pst.setString(5, "%"+aux+"%");
     
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          List<Plan> Plans= (List<Plan>) gpl.fetchAll();
          while(res.next())// parcour du result set
          {
              
              
          int Plan_ID_in_question = res.getInt("plan_evt");
              Plan p= Plans.stream().filter(p1->p1.getId()==Plan_ID_in_question).findFirst().get();
              Utilisateur c=null;
              int Client_ID_in_question =res.getInt("organisateur_evt");
              if(Client_ID_in_question!=0)
              {
               c= clients.stream().filter(c1->c1.getId()==Client_ID_in_question).findFirst().get();   
              }
             evenent.add(new Events(
             res.getString("nom_evt"), 
                     res.getDate("date_evt"),
                     res.getString("heure"), 
                     res.getString("duree_evt"),
                     res.getString("type_evt"),
                      c,
                     res.getString("description_evt"),
                     p,
                     res.getInt("nb_max")        
             ) );
           }
          return evenent;
    }
    
    public List<? extends Object> fetchAll(String aux,int StartPoint,int BreakPoint) throws SQLException {
        
        GestionnaireClient gclt = new GestionnaireClient();
          List<Utilisateur>clients= (List<Utilisateur>) gclt.fetchAll();
           GestionnairePlan gpl= new GestionnairePlan();
           
           
          String query=" select * from (select events.*,entite.nom from events inner join entite on entite.id=events.entite  limit  "+StartPoint+","+BreakPoint+" ) event_l "
                  + "where ( nom_evt like ? or date_evt like ? or heure like ? or type_evt like ? or plan_evt like ? )"   ; // preparation du requete sql
              
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Events>Evenent = new ArrayList<>();//  Creation du List Reclamation
          pst.setString(1, "%"+aux+"%");
          pst.setString(2, "%"+aux+"%");
          pst.setString(3, "%"+aux+"%");
          pst.setString(4, "%"+aux+"%");
          pst.setString(5, "%"+aux+"%");
     
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          List<Plan> Plans= (List<Plan>) gpl.fetchAll();
          while(res.next())// parcour du result set
          {
              
          int Plan_ID_in_question = res.getInt("plan_evt");
              Plan p= Plans.stream().filter(p1->p1.getId()==Plan_ID_in_question).findFirst().get();
              Utilisateur c=null;
              int Client_ID_in_question =res.getInt("organisateur_evt");
              if(Client_ID_in_question!=0)
              {
               c= clients.stream().filter(c1->c1.getId()==Client_ID_in_question).findFirst().get();   
              }
             Evenent.add(new Events(
                     res.getString("nom_evt"), 
                     res.getDate("date_evt"),
                     res.getString("heure"), 
                     res.getString("duree_evt"),
                     res.getString("type_evt"),
                      c,
                     res.getString("description_evt"),
                     p,
                     res.getInt("nb_max")
                      
             ));
           }
          return Evenent;
    }
  
    public List<? extends Object> fetchAll(String aux, String target_column ,int StartPoint,int BreakPoint) throws SQLException {
          String query=" select * from (select events.*,id.nom from events inner join id on id.id=events.entite limit  "+StartPoint+","+BreakPoint+" ) event_l "
                  + " where ( "+target_column+" like ?  )  " ;
                 // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          GestionnaireClient gclt = new GestionnaireClient();
          List<Utilisateur>clients= (List<Utilisateur>) gclt.fetchAll();
          GestionnairePlan gpl= new GestionnairePlan();
          List<Events>evenent = new ArrayList<>();//  Creation du List Reclamation
          pst.setString(1, "%"+aux+"%");
         
           ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          List<Plan> Plans= (List<Plan>) gpl.fetchAll();
          while(res.next())// parcour du result set
          {
              
          int Plan_ID_in_question = res.getInt("plan_evt");
              Plan p= Plans.stream().filter(p1->p1.getId()==Plan_ID_in_question).findFirst().get();
              Utilisateur c=null;
              int Client_ID_in_question =res.getInt("organisateur_evt");
              if(Client_ID_in_question!=0)
              {
               c= clients.stream().filter(c1->c1.getId()==Client_ID_in_question).findFirst().get();   
              }
             evenent.add(new Events(
                    res.getString("nom_evt"), 
                     res.getDate("date_evt"),
                     res.getString("heure"), 
                     res.getString("duree_evt"),
                     res.getString("type_evt"),
                      c,
                     res.getString("description_evt"),
                     p,
                     res.getInt("nb_max")
             ) );
           }
          return evenent;
    }

    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

//    @Override
//    public Object fetchOneById(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Object fetchOnByCriteria(Map Criteras) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

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
    public Events fetchOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Events fetchOnByCriteria(Map<String, String> Criteras) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
