/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Evenement;

import Entites.Evenement.Evenement;
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
public class GestionnaireEvenementGroup extends GestionnaireEvenement implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
        super.create(o);
    EvenementGroup evtgrp=(EvenementGroup)o;
    String query;
     PreparedStatement pst;
    if(evtgrp.getOrganisateur()==null)
    {
          query="insert into evenement_group(evenement,heure,description,plan) values(?,?,?,?)";
           pst= DB.prepareStatement(query);
    }
    else
    {
       query="insert into evenement_group(evenement,heure,description,plan,organisateur) values(?,?,?,?,?)";
        pst= DB.prepareStatement(query);
        pst.setInt(5,evtgrp.getOrganisateur().getID());
    }
      
      pst.setInt(1, evtgrp.getID());
      pst.setString(2,evtgrp.getHeure());
      pst.setString(3,evtgrp.getDescription());
      pst.setInt(4,evtgrp.getPlan().getID());
      
      return pst.executeUpdate();}

    @Override
    public int update(Object o) throws SQLException {
    EvenementGroup evtgrp=(EvenementGroup)o;
      String query ="update evenement_group set Entite=?,organisateur=?,heure=?,description=?,plan=? where Entite=?";
      
      PreparedStatement pst=DB.prepareStatement(query);
      
      pst.setInt(1, evtgrp.getID());
      pst.setInt(2,evtgrp.getOrganisateur().getID());
      pst.setString(3,evtgrp.getHeure());
      pst.setString(4,evtgrp.getDescription());
      pst.setInt(5,evtgrp.getPlan().getID());
      
      return pst.executeUpdate();     
    }

    @Override
    public int remove(Object o) throws SQLException {
       EvenementGroup evtgrp=(EvenementGroup)o; 
    String query=" delete from evenement_group where Entite=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    pst.setInt(1,evtgrp.getID());
    
    return pst.executeUpdate();
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
    String query=" select Entite.nom as nom,evenement_group.*  from  evenement_group inner join Entite on evenement_group.Entite=Entite.ID "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<EvenementGroup>EvenementGroups = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          GestionnaireClient gclt = new GestionnaireClient();
          List<Client>clients= (List<Client>) gclt.fetchAll();
           GestionnairePlan gpl= new GestionnairePlan();
          List<Plan> Plans= (List<Plan>) gpl.fetchAll();
          while(res.next())// parcour du result set
          {
               int Plan_ID_in_question = res.getInt("plan");
               Plan p= Plans.stream().filter(p1->p1.getID()==Plan_ID_in_question).findFirst().get();
//( Client organisation, Date heure, String description, int plan, int ID, String nom) 
             int Client_ID_in_question =res.getInt("organisateur");
             EvenementGroups.add(new EvenementGroup(
                     clients.stream().filter(x->x.getID()==Client_ID_in_question).findFirst().get(),// fetching client
                     res.getString("heure"),
                     res.getString(3),
                     p,
                     res.getInt("evennement"),
                     res.getString("nom")
             )
             );
           }
          return EvenementGroups;   
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
