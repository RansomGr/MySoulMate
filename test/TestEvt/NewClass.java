/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestEvt;

import Entites.Plan.Plan;
import Entites.User.Client;
import java.sql.Date;
import Entites.Events.Events;
import Services.Events.GestionnaireEvent;
import Services.Events.GestionnaireInviteEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entites.Events.InviteEvents;

/**
 *
 * @author dellpro
 */
public class NewClass {
    public static void main(String[] args) {
        //!!***
        GestionnaireInviteEvent gp= new GestionnaireInviteEvent();
        List<InviteEvents> ivt;
        try
        {
            ivt = (List<InviteEvents>) gp.fetchAll();
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        //***********
   // Client c1= new Client("Admin","Admin","Admin","admin@esprit.tn",new Date(1987,12,1),"Admin");
    Plan p = new Plan("cafe", Plan.Type.culturel, "cafe", "cafe", 0, "cafe", "cafe");
  //  Events evt = new Events("uioo", Date.valueOf("1987-12-03"), "12h", "2h", "conf", c1, "conf", p, 2, -1, "event");
   
//    GestionnaireEvent ge= new GestionnaireEvent();
//        try {
//            ge.create(evt);
//        } catch (SQLException ex) {
//            Logger.getLogger(TestEvt01.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //**************
          List<Events>evts;
          List<Events> eventssss;
//        try {
////         evts =  (List<Evenement>) g.fetchAll();
////         evts.forEach(System.out::println);
//         
//         //eventssss = (List<Events>) ge.fetchAll();
//         eventssss.forEach(System.out::println);
//        } catch (SQLException ex) {
//              Logger.getLogger(TestEvt01.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
}
