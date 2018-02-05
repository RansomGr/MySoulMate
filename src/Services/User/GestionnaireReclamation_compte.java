/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.User;

import Entites.User.Reclamation;
import Entites.User.Reclamation_compte;
import Services.Gestionnaire;
import static Services.Gestionnaire.DB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ransom
 */
public class GestionnaireReclamation_compte extends GestionnaireReclamation implements Gestionnaire  {

    @Override
    public int create(Object o) throws SQLException {
        super.create(o);
       Reclamation_compte r = (Reclamation_compte)o;// down Cast
       /*
          this.categorie=categorie;
        this.client=client;
        this.Date_heure=Date_heure;
       */
        String query=" insert into reclamation_compte (reclamation,categorie,client,Date_heure) values (?,?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setString(1, r.getSujet());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2, r.getDescription());//Binding du deuxieme valeur mentionner dans le query "?" 
           pst.setString(3, r.getSujet());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2, r.getDescription());//Binding du deuxieme valeur mentionner dans le query "?" 
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int update(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int remove(Object o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
