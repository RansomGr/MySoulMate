/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestEvt;
// amira commit
import Entites.Evenement.Evenement;
import Entites.Evenement.Events;
import Entites.Plan.Plan;
import Entites.Profil.Caracteristique;
import Entites.Profil.Profil;
import Entites.User.Client;
import Services.Evenement.GestionnaireEvenement;
import Services.Evenement.GestionnaireEvents;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dellpro
 */
public class TestEvt01 {
    public static void main(String[] args) {
       /* public Date date_evenement;
    public Date heure;
    public String type_evenement;*/
    
   Evenement e = new Evenement(Date.valueOf("1987-12-03"),("1987-12-03"),"event","course");
        Caracteristique cara = new Caracteristique();
    Profil prof = new Profil(1, cara, "ii", "oo", cara);
    //Client c= new Client(1, "amira1", "amira2", "amira3",Date.valueOf("2000-02-02"),"amira4", prof, 1, 2, 3);
    //Client c= new Client("mp", "mo", "lo", "ki",Date.valueOf("1987-12-03") , "14");
    //Client c1= new Client("mounir","sabbaghi","falfoul11","falfoul@falfoul@esprit.tn",new Date(1987,12,1),"foufou");
    Plan p = new Plan("cafe", Plan.Type.culturel, "cafe", "cafe", 0, "cafe", "cafe");
    //Events evt = new Events("uioo", Date.valueOf("1987-12-03"), "12h", "2h", "conf", c1, "conf", p, 2, -1, "event");
    GestionnaireEvenement g= new  GestionnaireEvenement();
    GestionnaireEvents ge= new GestionnaireEvents();
//        try {
////            g.create(e);
//            ge.create(evt);
//        } catch (SQLException ex) {
//            Logger.getLogger(TestEvt01.class.getName()).log(Level.SEVERE, null, ex);
//        }
          List<Evenement>evts;
          List<Events> eventssss;
        try {
//         evts =  (List<Evenement>) g.fetchAll();
//         evts.forEach(System.out::println);
         
         eventssss = (List<Events>) ge.fetchAll();
         eventssss.forEach(System.out::println);
        } catch (SQLException ex) {
              Logger.getLogger(TestEvt01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
