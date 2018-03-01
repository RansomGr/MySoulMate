/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Evenement;

import Entites.Evenement.EvenementGroup;
import Entites.Evenement.PlanEtCeremonie;
import Entites.Plan.Plan;
import Services.Gestionnaire;
import Services.Plan.GestionnairePlan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dellpro
 */
public class GestionnairePlanEtCeremonie implements Gestionnaire{

    @Override
    public int create(Object o) throws SQLException {
        PlanEtCeremonie plancer=(PlanEtCeremonie)o;
      String query="insert into ceremonie_et_plan(evenement_groupe , plan_ceremonie ) values(?,?)";
      PreparedStatement pst= DB.prepareStatement(query);
      pst.setInt(1, plancer.getEvtgroup().getID());
      pst.setInt(2,plancer.getPlan().getID());
      
      return pst.executeUpdate();
    }

    @Override
    public int update(Object o) throws SQLException {
    PlanEtCeremonie plancer=(PlanEtCeremonie)o;
    String query ="update ceremonie_et_plan set evenement_groupe=?,plan_ceremonie=? where evenement_groupe=?,plan_ceremonie=?";
    PreparedStatement pst=DB.prepareStatement(query);
      pst.setInt(1, plancer.getEvtgroup().getID());
      pst.setInt(2,plancer.getPlan().getID());
      
      return pst.executeUpdate(); 
    }

    @Override
    public int remove(Object o) throws SQLException {
        PlanEtCeremonie plancer=(PlanEtCeremonie)o;
        String query=" delete from ceremonie_et_plan where evenement_groupe=?,plan_ceremonie=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    pst.setInt(1, plancer.getEvtgroup().getID());
    pst.setInt(2,plancer.getPlan().getID());
    
    return pst.executeUpdate();
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
        String query=" select * from ceremonie_et_plan "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<PlanEtCeremonie>PlanEtCeremonies = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          List<EvenementGroup>events;
           GestionnaireEvenementGroup g = new GestionnaireEvenementGroup();
          events=(List<EvenementGroup>) g.fetchAll();
          GestionnairePlan gp= new GestionnairePlan();
          List<Plan>plans= (List<Plan>) gp.fetchAll();
         
          while(res.next())// parcour du result se
          {
              int id_evt=res.getInt(1);
              int id_pla=res.getInt(2);
              Plan pl= plans.stream().filter(x->x.getID()==id_pla).findFirst().get();
              EvenementGroup evtg = events.stream().filter(y->y.getID()==id_evt).findFirst().get();
             PlanEtCeremonies.add(new PlanEtCeremonie(pl,evtg));
           }
          return PlanEtCeremonies;
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
