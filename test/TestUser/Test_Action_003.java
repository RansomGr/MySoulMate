/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestUser;

import Entites.User.Action;
import Services.User.GestionnaireAction;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ransom
 */
public class Test_Action_003 {
    public static void main(String args[])
    {
        Action a= new Action(1,2,"fetch post ");
        GestionnaireAction g=new GestionnaireAction ();
        try {
            g.remove(a);
        } catch (SQLException ex) {
            Logger.getLogger(Test_Action_003.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Action>Actions;
        try {
            Actions=(List<Action>) g.fetchAll();
            Actions.forEach(System.out::println);
        } catch (SQLException ex) {
            Logger.getLogger(Test_Action_003.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
