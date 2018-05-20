/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Profil;

import Entites.Profil.Caracteristique;
import Entites.Profil.Profil;
import Services.Gestionnaire;
import Services.User.GestionnaireUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mysoulmate.MySoulMate;

/**
 *
 * @author Sofiene
 */
public class GestionnaireProfil implements Gestionnaire <Profil > {

    @Override
    public int create( Profil a) throws SQLException {

        String query = " insert into Profil (photo,description,caracteristique_id,user_id) values (?,?,?) "; // preparation du query

        PreparedStatement pst = DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
        GestionnaireCaracteristique car = new GestionnaireCaracteristique();
        car.create(a.getCaracteristique_id());

        System.out.println("carac id :" + ((List<Caracteristique>) car.fetchAll()).stream().mapToInt(x -> x.getID()).max().getAsInt());
        System.out.println("client id :" + MySoulMate.getLogged_in_Client().getId());
        a.getCaracteristique_id().setID(((List<Caracteristique>) car.fetchAll()).stream().mapToInt(x -> x.getID()).max().getAsInt());
        pst.setString(1, a.getPhoto());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(2, a.getDescription());
        pst.setInt(3, a.getCaracteristique_id().getID());// Binding du premier valeur mentionner dans le query "?" 
        pst.setInt(1, a.getUser_id().getId());


        return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int update(Profil a ) throws SQLException {
        String query = " update  Profil set caracteristique=?,photo=?,description=?,preference=? where id=?  "; // preparation du query

        PreparedStatement pst = DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment

        pst.setInt(1, a.getCaracteristique_id().getID());// Binding du premier valeur mentionner dans le query "?" 
        pst.setString(2, a.getPhoto());//Binding du deuxieme valeur mentionner dans le query "?" 
        pst.setString(3, a.getDescription());
        pst.setInt(4, a.getPreference().getID());//Binding du valeur de l'id mentionné dans le query "?" 
        return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int remove(Profil a ) throws SQLException {

        String query = "delete  from Profil where ID=? ";

        PreparedStatement pst = DB.prepareStatement(query);

        pst.setInt(1, a.getId());

        return pst.executeUpdate();
    }

    @Override
    public List<Profil > fetchAll() throws SQLException {

        String query = " select id,caracteristique,photo,description,preference from profil "; // preparation du requete sql
        PreparedStatement pst = DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
        List<Profil> Profils = new ArrayList<>();//  Creation du List Reclamation
        //Caracteristique c = new Caracteristique(0, query, query, query, query, query, query, 0, query, query, query, query, query)
        ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
        GestionnaireCaracteristique G = new GestionnaireCaracteristique();
        while (res.next())// parcour du result set
        {
            Caracteristique carac = null;
            Caracteristique pref = null;
            int id_char = res.getInt(2);
            int id_pref = res.getInt(5);
             GestionnaireUser gc;
        gc = new GestionnaireUser();
       

            carac = ((List<Caracteristique>) G.fetchAll()).stream().filter(x -> x.getID() == id_char).findFirst().get();
            if (id_pref != 0) {
                pref = ((List<Caracteristique>) G.fetchAll()).stream().filter(x -> x.getID() == id_pref).findFirst().get();
            }

            Profils.add(new Profil(res.getInt(1), carac, res.getString(3), res.getString(4), pref,gc.fetchOneById(res.getInt("createur"))));
        }
        return Profils;
    }

  

    public Profil fetchOneByID(int id) throws SQLException {

        String query = " select id,caracteristique,photo,description,preference from profil  where id=?"; // preparation du requete sql
        PreparedStatement pst = DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
        pst.setInt(1, id);
        Profil Profil = null;
              GestionnaireUser gc;
        gc = new GestionnaireUser();

        ResultSet res = pst.executeQuery();
        GestionnaireCaracteristique G = new GestionnaireCaracteristique();
        if (res.next()) {

            Caracteristique carac = null;
            Caracteristique pref = null;
            int id_char = res.getInt(2);
            int id_pref = res.getInt(5);
            if (id_char != 0) {
                carac = G.fetchOneById(id_char);
            }
            if (id_pref != 0) {
                pref = G.fetchOneById(id_pref);
            }
            System.out.println("bref = " + id + pref);

            Profil = new Profil(res.getInt(1), carac, res.getString(3), res.getString(4), pref,gc.fetchOneById(res.getInt("createur")));
        }
        return Profil;
    }

    @Override
    public Profil fetchOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Profil fetchOnByCriteria(Map<String, String> Criteras) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Profil> fetchSomeBy(String aux, String target_column, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Profil> fetchSomeBy(String aux, int target_column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Profil> fetchSomeBy(String aux, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
