/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestUser;

import Entites.User.Admin;
import Entites.User.Client;
import Services.User.GestionnaireAdmin;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ransom
 */
public class Test_Admin_002 {
    public static void main(String agrs[])
    {
        Admin a= new Admin(4,"fakroun","faloun","password");
        GestionnaireAdmin g= new GestionnaireAdmin();
        try {
            g.remove(a);
        } catch (SQLException ex) {
            Logger.getLogger(Test_Admin_002.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Admin>Admins ;
        try {
            Admins= (List<Admin>) g.fetchAll();
            Admins.forEach(System.out::println);
        } catch (SQLException ex) {
            Logger.getLogger(Test_Admin_002.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
