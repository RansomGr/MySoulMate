/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestRelation;

import Entites.Relation.Relation;
import Entites.User.Client;
import Services.Relation.GestionnaireRelation;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zhanimm
 */
public class TestRelation1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         Relation r1;
        r1 = new Relation(1,
        new Client ("mounir","sabbaghi","falfoul11","falfoul@falfoul@esprit.tn",new Date(1987,12,1),"foufou"),
        new Client ("manel","naily","maily","maily@esprit.tn",new Date(1957,5,10),"maily"), 
                5,"niveau1",new Date(2018,02,05),new Date(2018,02,07)
        );
         
        
        GestionnaireRelation g= new GestionnaireRelation();
        try {
            g.create(r1);
        } catch (SQLException ex) {
            Logger.getLogger(TestRelation1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
