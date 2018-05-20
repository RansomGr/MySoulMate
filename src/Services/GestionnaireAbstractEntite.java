/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.AbstractEntite;
import Entites.Events.Events;
import Entites.Plan.Plan;
import Entites.Plan.Plan.Type;
import Entites.Profil.Adresse;
import Entites.Profil.Profil;
import Entites.User.Client;

import static Services.Gestionnaire.DB;
import Services.Plan.GestionnairePlan;
import Services.Profil.GestionnaireAdresse;
import Services.Profil.GestionnaireProfil;
import Services.User.GestionnaireClient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ransom
 */
public abstract class GestionnaireAbstractEntite implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
        AbstractEntite e = (AbstractEntite) o;// down Cast
        String query;
        PreparedStatement pst;
        GestionnaireAdresse gadd = new GestionnaireAdresse();

        if (e.getAdresse() != null) {
            query = "insert into Entite (nom,adresse) values (?,?) ";
            pst = DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
            e.getAdresse().setID(((List<Adresse>) gadd.fetchAll()).stream().mapToInt(x -> x.getID()).max().getAsInt());
            pst.setInt(2, e.getAdresse().getID());

        } else {
            query = " insert into Entite (nom) values (?) "; // preparation du query
            pst = DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
        }
        pst.setString(1, e.getNom());//Binding du deuxieme valeur mentionner dans le query "?" 

        return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int update(Object o) throws SQLException {

        AbstractEntite e = (AbstractEntite) o;// down Cast du Object => Reclamation 
        String query = " update  Entite set ID=?,nom=? where ID=? "; // preparation du query

        PreparedStatement pst = DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment

        pst.setInt(1, e.getID());// Binding du premier valeur mentionner dans le query "?" 
        pst.setString(2, e.getNom());//Binding du deuxieme valeur mentionner dans le query "?" 

        pst.setInt(3, e.getID());//Binding du valeur de l'id mentionné dans le query "?" 
        return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int remove(Object o) throws SQLException {
        AbstractEntite e = (AbstractEntite) o;// down Cast du Object => Reclamation 
        String query = " delete from  Entite  where ID=? "; // preparation du query delete 

        PreparedStatement pst = DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment

        pst.setInt(1, e.getID());//Binding du valeur de l'id mentionné dans le query "?" 
        return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
        String query = " select * from entite "; // preparation du requete sql
        PreparedStatement pst = DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
        List<AbstractEntite> AbstractEntites = new ArrayList<>();//  Creation du List Reclamation
        ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
        while (res.next())// parcour du result set
        {
            AbstractEntites.add(new AbstractEntite(res.getInt(1), res.getString(2)) {
            });
        }
        return AbstractEntites;
    }

    public static AbstractEntite findDerviedChildbyId(int id) throws SQLException {

        String query = " select * from entite join plan where  entite=?";
        PreparedStatement pst = DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
        pst.setInt(1, id);
        ResultSet res = pst.executeQuery();

        if (res.next()) {
            return new Plan(res.getInt("entite"), res.getString("nom"), Type.getAsType(res.getString("type")), res.getString("email"), res.getString("siteweb"), res.getInt("telephone"), res.getString("description"), res.getString("'photo"));
        }

        query = " select * from entite join Client where  entite=? ";
        pst = DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
        pst.setInt(1, id);
        res = pst.executeQuery();

        if (res.next()) {
            GestionnaireProfil gp = new GestionnaireProfil();
            Profil profil;
            profil = gp.fetchOneByID(id);
            return new Client(res.getInt("entite"), res.getString("nom"), res.getString("prenom"), res.getString("motdepasse"), res.getString("email"), res.getDate("date_naissane"), res.getString("pseudo"), profil, res.getInt("activation"), res.getInt("ban"), res.getString("gender"));
        }
        query = " select * from entite join events where  entite=? ";
        pst = DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
        pst.setInt(1, id);
        res = pst.executeQuery();
        if(res.next())
        {
            GestionnaireClient gc = new GestionnaireClient();
            GestionnairePlan gp= new GestionnairePlan();
            return new Events(res.getString("nom_evt"),res.getDate("date_evt"),res.getString("heure"),res.getString("duree_evt"), res.getString("Type_evt"),gc.fetchOneById(res.getInt("organisateur_evt")), res.getString("description_evt"),gp.fetchOneById(res.getInt("plan")),res.getInt("nb_max"), res.getInt("entite"),res.getString("nom"));
        }
        return null;
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
