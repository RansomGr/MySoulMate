/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Evenement;

import Entites.Evenement.Ceremonie;
import Entites.Evenement.EvenementGroup;
import Entites.Plan.Plan;
import Entites.User.Client;
import Services.Gestionnaire;
import Services.Plan.GestionnairePlan;
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
public class GestionnaireCeremonie implements Gestionnaire{

    @Override
    public int create(Object o) throws SQLException {

        Ceremonie cer=(Ceremonie)o;
   
        String query=" insert into ceremonie (evenement_groupe,type,nbr_max) values (?,?,?) "; 

         PreparedStatement pst=DB.prepareStatement(query);
         pst.setInt(1, cer.getID());
         pst.setString(2, cer.getType());
         pst.setInt(3, cer.nb_max);
       
         return pst.executeUpdate();  
    }

    @Override
    public int update(Object o) throws SQLException {
        
        Ceremonie cer=(Ceremonie)o;
      String query ="update ceremonie set evenement_groupe=?,type=?,nb_max=? where evenement_groupe=?";
      
      PreparedStatement pst=DB.prepareStatement(query);
   
      pst.setInt(1, cer.getID());
      pst.setString(2,cer.getType());
      pst.setInt(3,cer.getNb_max());
      pst.setInt(4, cer.getID());
      
      return pst.executeUpdate(); 
    }

    @Override
    public int remove(Object o) throws SQLException {
    Ceremonie cer=(Ceremonie)o;
    String query=" delete from ceremonie where evenement_groupe=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    
    pst.setInt(1,cer.getID());
    
    return pst.executeUpdate();    
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
//String type, int nb_max, Client organisation, Date heure, String description, int plan, int ID, String nom        
    String query=" select evenement.type_e as type ,"
            + "ceremonie.nbr_Max as nmax ,"
            + "evenement_group.organisation as client ,"
            + " evenement.Date_evennement as date_e ,"
            + " evenement_group.description as descr ,"
            + "evenement_group.plan as plan ,"
            + " Entite.ID as id, Entite.nom as nom "
            + "from Entite "
            + "inner join evenement "
            + "on Entite.id = evenement.entite"
            + "inner join evenement_group"
            + " on evenement_group.evenement=evenement.entite"
            + " inner join ceremonie "
            + "on ceremonie.Evenement_Groupe=evenement_group.evenement "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Ceremonie>Ceremonies = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          
          GestionnaireClient gclt = new GestionnaireClient();
          List<Client>clients= (List<Client>) gclt.fetchAll();
          
          GestionnairePlan gpl= new GestionnairePlan();
          List<Plan> Plans= (List<Plan>) gpl.fetchAll();
    //Ceremonie(String type, int nb_max, Client organisation, Date heure, String description, int plan, int ID, String nom)       
          while(res.next())// parcour du result set
          {
              int Plan_ID_in_question = res.getInt("plan");
              int Client_ID_in_question =res.getInt("client");
              Client c= clients.stream().filter(cl->cl.getID()==Client_ID_in_question).findFirst().get();
              Plan p= Plans.stream().filter(p1->p1.getID()==Plan_ID_in_question).findFirst().get();
              Ceremonies.add(new Ceremonie(
                     res.getString(1),
                     res.getInt(2),
                     c,
                     res.getDate(4),
                     res.getString(5),
                     p,
                     res.getInt(7),
                      res.getString(8)           
             ));
           }
          return Ceremonies;    
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
