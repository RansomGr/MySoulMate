/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestPlan;

import Entites.Plan.Plan;
import Entites.Plan.Reservation;
import Entites.User.Client;
import Services.Plan.GestionnairePlan;
import Services.Plan.GestionnaireReservation;
import Services.User.GestionnaireClient;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author irou
 */
public class TestReservation {
    public static void main (String args[]) throws SQLException{
    
     Client c1= new Client(22,"mounir","sabbaghi","falfoul11","falfoul@falfoul@esprit.tn",new Date(1987,12,1),"foufou");
        GestionnaireClient g= new GestionnaireClient();
        Plan p1=  new Plan(2,"hotelx",Plan.Type.culturel,"marwen@hotmail.com", "www.aaa.com",4477722, "c'est un bon plan culturel", "plan1.gif");
        GestionnairePlan Gp= new GestionnairePlan();
        g.create(c1);
        Gp.create(p1);
        Reservation R1= new Reservation(3,p1,c1,new Date(2018,06,27),20);
        GestionnaireReservation Gr= new GestionnaireReservation();
       Gr.create(R1);
       // Gr.remove(R1);
    
        
    //(int ID,Plan plan, Client client, Date date_res, int nb_place)
    
    
    
    
    
    }
}
