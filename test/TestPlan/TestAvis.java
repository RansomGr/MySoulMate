/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestPlan;

import Entites.Plan.Avis;
import Entites.Plan.Plan;
import Entites.User.Client;

import Services.Plan.GestionnaireAvis;
import Services.Plan.GestionnairePlan;
import Services.User.GestionnaireClient;

import java.sql.Date;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author irou
 */
public class TestAvis {
     public static void main (String args[]) throws SQLException
    {
        
        Client c1= new Client(22,"mounir","sabbaghi","falfoul11","falfoul@falfoul@esprit.tn",new Date(1987,12,1),"foufou");
        GestionnaireClient g= new GestionnaireClient();
        Plan p1=  new Plan(1,"hotelx",Plan.Type.culturel,"marwen@hotmail.com", "www.aaa.com",4477722, "c'est un bon plan culturel", "plan1.gif");
        GestionnairePlan Gp= new GestionnairePlan();
        g.create(c1);
        Gp.create(p1);
        
        Client c2= new Client(22,"mouhamed","karoui","moumou","fa@esprit.tn",new Date(1990,12,1),"gggg");
        
        Plan p2=  new Plan(1,"hotely",Plan.Type.culturel,"hotely@hotmail.com", "www.yyyy.com",44442, "c'est un bon plan culturel", "plan3.gif");
    
        g.create(c2);
        Gp.create(p2);
        
        
         Avis A1= new Avis(p1,c1,"aaaa",7.5f,Date.valueOf("1990-12-1"));
         GestionnaireAvis Ga=new GestionnaireAvis();
         Avis A2= new Avis(p2,c2,"bbbb",6.5f,Date.valueOf("1980-12-1"));
        //Ga.create(A1);
        //Ga.create(A2);
        Ga.remove(A2);


    
    }
    
}
