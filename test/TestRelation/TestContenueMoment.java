/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestRelation;

import Entites.Relation.Contenue_Moment;
import Services.Relation.GestionnaireContenue_Moment;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author zhanimm
 */
public class TestContenueMoment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Contenue_Moment cm1 = new Contenue_Moment(1,"meeting","","","cinema",Date.valueOf("2018-12-03"));
        Contenue_Moment cm2 = new Contenue_Moment(2,"rendezvous","","","cafe",Date.valueOf("2018-12-09"));

        GestionnaireContenue_Moment g = new GestionnaireContenue_Moment();
        try {
            //g.create(cm);
            g.create(cm2);
            cm1.setDate_moment(Date.valueOf("2018-12-04"));
            g.update(cm1);
        } catch (SQLException ex) {
            Logger.getLogger(TestContenueMoment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                List<Contenue_Moment>ctmoments ;
try {
            ctmoments = (List<Contenue_Moment>) g.fetchAll();
            ctmoments.forEach(System.out::println);
        } catch (SQLException ex) {
            Logger.getLogger(TestContenueMoment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
