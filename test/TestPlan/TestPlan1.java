/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestPlan;

import Entites.Plan.Plan;
import Services.Plan.GestionnairePlan;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author irou
 */
public class TestPlan1 {
     public static void main (String args[])
    {
   Plan p1=  new Plan(1,"hotelx",Plan.Type.culturel,"marwen@hotmail.com", "www.aaa.com",4477722, "c'est un bon plan", "plan.gif");
        GestionnairePlan GP = new GestionnairePlan();
         try {
             GP.create(p1);
         } catch (SQLException ex) {
             Logger.getLogger(TestPlan1.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    
    }
    
}
