/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestUser;

import Entites.User.Client;
import Services.User.GestionnaireClient;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ransom
 */
public class Test_User_001 {
    public static void main(String args[])
    {
        Client c1= new Client("mounir","sabbaghi","falfoul11","falfoul@falfoul@esprit.tn",new Date(1987,12,1),"foufou");
        
        GestionnaireClient g= new GestionnaireClient();
        try {
            g.create(c1);
        } catch (SQLException ex) {
            Logger.getLogger(Test_User_001.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
