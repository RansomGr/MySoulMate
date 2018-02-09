/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestEvt;

import Entites.Evenement.EvenementGroup;
import Entites.Plan.Plan;
import Entites.User.Client;
import Services.Plan.GestionnairePlan;
import Services.User.GestionnaireClient;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dellpro
 */
public class TestGrEvt {
    public static void main(String[] args) {
        //Client organisation, Date heure, String description, Plan plan, String nom
        Client clt;
        List<Client>lstcl;
        GestionnaireClient gcl = new GestionnaireClient();
        Plan pl = new Plan(Plan.Type.culturel, "ami@es.tn", "www.et.tn", 21368452, "cafe", "la1",2 ,"plan");
        GestionnairePlan GP = new GestionnairePlan();
        try {
            GP.create(pl);
        } catch (SQLException ex) {
            Logger.getLogger(TestGrEvt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            lstcl =(List<Client>) gcl.fetchAll();
            
            clt= lstcl.stream().filter(x->x.getID()==11).findFirst().get();
            EvenementGroup evtgr = new EvenementGroup(clt, Date.valueOf("1987-12-03"), "course", pl, "");
       
        } catch (SQLException ex) {
            Logger.getLogger(TestGrEvt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        
    }
}
