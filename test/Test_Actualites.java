
import Services.Profil.GestionnaireActualite;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ransom
 */
public class Test_Actualites {
   public static void main(String[] args)
   {
       GestionnaireActualite ga = new GestionnaireActualite();
       try {
           ga.fetchAllById(22).forEach(System.out::println);
       } catch (SQLException ex) {
           Logger.getLogger(Test_Actualites.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
}
