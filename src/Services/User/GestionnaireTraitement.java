/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.User;


import Entites.User.Admin;
import Entites.User.Client;
import Entites.User.Reclamation_compte;
import Entites.User.Traitement;
import Services.Gestionnaire;
import static Services.Gestionnaire.DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ransom
 */
public class GestionnaireTraitement implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
          Traitement t = (Traitement)o;// down Cast
        String query=" insert into Traitement (admin,rec_compte,reponse) values (?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1, t.getAdmin().getID());// Binding du premier valeur mentionner dans le query "?" 
         pst.setInt(2, t.getRec_compte().getID());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(3, t.getReponce());//Binding du deuxieme valeur mentionner dans le query "?" 
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int update(Object o) throws SQLException {
        Traitement t = (Traitement)o;// down Cast
        String query=" update Traitement set admin=?,rec_compte=?,reponse=?,date_traitement=? where admin=? and rec_compte=? "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1, t.getAdmin().getID());// Binding du premier valeur mentionner dans le query "?" 
         pst.setInt(2, t.getRec_compte().getID());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(3, t.getReponce());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setDate(4, t.getDate_traitement());//Binding du deuxieme valeur mentionner dans le query "?" 
            pst.setInt(5, t.getAdmin().getID());// Binding du premier valeur mentionner dans le query "?" 
         pst.setInt(6, t.getRec_compte().getID());//Binding du deuxieme valeur mentionner dans le query "?" 
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int remove(Object o) throws SQLException {
           Traitement t = (Traitement)o;// down Cast
        String query="delete from Traitement  where admin=? and rec_compte=? "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment

         pst.setInt(1, t.getAdmin().getID());// Binding du premier valeur mentionner dans le query "?" 
         pst.setInt(2, t.getRec_compte().getID());//Binding du deuxieme valeur mentionner dans le query "?" 
         
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
        
          String query=" select  *  from Traitement" +
                             "   inner join Admin " +
                             "   on Traitement.Admin=Admin.id  " +
                             "   inner join reclamation_compte " +
                             "    on Traitement.rec_compte=reclamation_compte.reclamation" +
                             "     inner join reclamation "   + 
                             "     on reclamation.id=reclamation_compte.reclamation" + 
                             "     inner join Client  " +
                             "     on Client.Entite=reclamation_compte.Client "  +
                             "    inner join Entite "   +
                             "    on Entite.ID=Client.Entite ";   // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Traitement>Traitements = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {

             Traitements.add(
                                    new Traitement(
                                                             new Admin(res.getInt(1),res.getString(6),res.getString(7),res.getString(8),res.getString(9)),
                                                             new Reclamation_compte(
                                                                                                     res.getInt(15),res.getString(16),res.getString(17),res.getString(18),
                                                                                                     new Client(res.getInt(25),res.getString(27),res.getString(19),res.getString(20),res.getString(21),res.getDate(22),res.getString(23)),
                                                                                                     res.getDate(3)
                                                                                                      ),
                                                             res.getDate(2),res.getString(13)
                                                            )
                                       );
           }
          return Traitements;
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
