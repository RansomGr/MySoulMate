/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestEvt;
// amira commit
import Entites.Evenement.Evenement;
import Services.Evenement.GestionnaireEvenement;
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
    
    Evenement e = new Evenement(Date.valueOf("1987-12-03"),Date.valueOf("1987-12-03"),"event","course");
    GestionnaireEvenement g= new  GestionnaireEvenement();
        try {
            g.create(e);
        } catch (SQLException ex) {
            Logger.getLogger(TestEvt01.class.getName()).log(Level.SEVERE, null, ex);
        }
          List<Evenement>evts;
        try {
         evts =  (List<Evenement>) g.fetchAll();
         evts.forEach(System.out::println);
        } catch (SQLException ex) {
              Logger.getLogger(TestEvt01.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
