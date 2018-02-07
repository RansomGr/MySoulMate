/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsof;

import Entites.Profil.Adresse;
import Services.Profil.GestionnaireAdresse;
import java.sql.SQLException;

/**
 *
 * @author Sofiene
 */
public class test {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Adresse a= new Adresse(2,12,"kol",222,"jandouba");
        GestionnaireAdresse ga= new GestionnaireAdresse();
        ga.create(a);
        
    }
    
}
