/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Evenement;

import Entites.AbstractEntite;
import Entites.Evenement.Events;
import Entites.Plan.Plan;
import Entites.User.Client;
import Services.Gestionnaire;
import Services.GestionnaireAbstractEntite;
import Services.Plan.GestionnairePlan;
import Services.User.GestionnaireClient;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dellpro
 */
public class GestionnaireEvents extends GestionnaireAbstractEntite implements Gestionnaire{
    
        @Override
    public int create(Object o) throws SQLException {
         super.create(o);
         Events evt=(Events)o;
         GestionnaireAbstractEntite ge = new GestionnaireAbstractEntite() {};
         List<AbstractEntite>entities =(List<AbstractEntite>) ge.fetchAll();
         int last_id_inserted=entities.stream().mapToInt(x->x.getID()).max().getAsInt();
         evt.setID(last_id_inserted);
      String query="INSERT INTO events (Entite,nom_evt,date_evt,heure,duree_evt,type_evt,organisateur_evt,description_evt,plan_evt,nb_max) VALUES (?,?,?,?,?,?,?,?,?,?)";
      PreparedStatement pst= DB.prepareStatement(query);
      pst.setInt(1, evt.getID());
      pst.setString(2,evt.getNom());
      pst.setDate(3, (Date) evt.getDate_evt());
      pst.setString(4,evt.getHeure_evt());
      pst.setString(5,evt.getDuree_evt());
      pst.setString(6,evt.getType_evt());
      pst.setString(7,evt.getOrganisateur().getEmail());
      pst.setString(8,evt.getDescription_evt());
      pst.setString(9,evt.getPlan_evt().getNom());
      pst.setInt(10,evt.getNb_max());


//      pst.setInt(1, 1);
//      pst.setString(2,"ebt");
//      pst.setDate(3,Date.valueOf(LocalDate.MAX));
//      pst.setString(4,"12h");
//      pst.setString(5,"2h");
//      pst.setString(6,"conf");
//      pst.setString(7,"@ty");
//      pst.setString(8,"reu");
//      pst.setString(9,"dal");
//      pst.setInt(10,2);
      return pst.executeUpdate();
   
    }

    @Override
    public int update(Object o) throws SQLException {
            
        super.update(o);
     Events evt=(Events)o;
      String query ="update events set Entite=?,nom_evt=?,date_evt=?,heure=?,duree_evt=?,type_evt=?,organisateur_evt=?,description_evt=?,plan_evt=?,nb_max=? where Entite=?";
      
      PreparedStatement pst=DB.prepareStatement(query);
      
      pst.setInt(1, evt.getID());
      pst.setString(2,evt.getNom());
      pst.setDate(3, (Date) evt.getDate_evt());
      pst.setString(4,evt.getHeure_evt());
      pst.setString(5,evt.getDuree_evt());
      pst.setString(6,evt.getType_evt());
      pst.setInt(7,evt.getOrganisateur().getID());
      pst.setString(8,evt.getDescription_evt());
      pst.setString(9,evt.getPlan_evt().getNom());
      pst.setInt(10,evt.getNb_max());
      
      return pst.executeUpdate(); 

    }

    @Override
    public int remove(Object o) throws SQLException {
         
        super.remove(o);
    Events evt=(Events)o;
    String query=" delete from events where Entite=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    
    pst.setInt(1,evt.getID());
    
    return pst.executeUpdate();
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
          
        String query=" select Entite.nom as nom,Events.*  from  events inner join Entite on Events.Entite=Entite.ID "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Events>Evenements = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          GestionnaireClient gclt = new GestionnaireClient();
          List<Client>clients= (List<Client>) gclt.fetchAll();
           GestionnairePlan gpl= new GestionnairePlan();
          List<Plan> Plans= (List<Plan>) gpl.fetchAll();
          while(res.next())// parcour du result set
          {
              int Plan_ID_in_question = res.getInt("plan_evt");
               Plan p= Plans.stream().filter(p1->p1.getID()==Plan_ID_in_question).findFirst().get();
             int Client_ID_in_question =res.getInt("organisateur");
              //res.getDate("date_evennement"),res.getString(3),res.getString(5),res.getInt("entite"),res.getString("nom"))
             Evenements.add(new Events(res.getString(1), res.getDate("date_evennement"),
                     res.getString(3), res.getString(4), res.getString(5),
                     clients.stream().filter(x->x.getID()==Client_ID_in_question).findFirst().get(),
                     res.getString(7), p, res.getInt(9),
                      res.getInt("evennement"),
                     res.getString("nom")
             )
             );
           }
         return Evenements;
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
