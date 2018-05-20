/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.User;
/*
import Entites.AbstractEntite;
import Entites.Profil.Profil;
import Entites.User.Client;
import Services.Gestionnaire;
import static Services.Gestionnaire.DB;
import Services.GestionnaireAbstractEntite;
import Services.Profil.GestionnaireProfil;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ransom
 */
/*
public class GestionnaireClient extends GestionnaireAbstractEntite implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
        super.create(o);

        Client c = (Client) o;
        String query = "insert into Client(Entite,prenom,motdepasse,email,date_naissane,pseudo,gender) values(?,?,?,?,?,?,?)";
        PreparedStatement pst = DB.prepareStatement(query);
        GestionnaireAbstractEntite g = new GestionnaireAbstractEntite() {
        };
        List<AbstractEntite> Entities = (List<AbstractEntite>) g.fetchAll();
        int last_Added_ID = Entities.stream().mapToInt(x -> x.getID()).max().getAsInt();
        pst.setInt(1, last_Added_ID);
        pst.setString(2, c.getPrenom());
        pst.setString(3, c.getMotdepasse());
        pst.setString(4, c.getEmail());
        pst.setDate(5, c.getDate_naissance());
        pst.setString(6, c.getPseudo());
        pst.setString(7, c.getGender());

        return pst.executeUpdate();

    }

    @Override
    public int update(Object o) throws SQLException {

        super.update(o);
        Client c = (Client) o;
        String query = "update Client set Entite=?,prenom=?,motdepasse=?,email=?,date_naissane=?,pseudo=? ,activation=?, ban=?,profil=?,gender=?  where Entite=?";

        PreparedStatement pst = DB.prepareStatement(query);

        pst.setInt(1, c.getID());
        pst.setString(2, c.getPrenom());
        pst.setString(3, c.getMotdepasse());
        pst.setString(4, c.getEmail());
        pst.setDate(5, c.getDate_naissance());
        pst.setString(6, c.getPseudo());
        pst.setInt(7, c.getActivation());
        pst.setInt(8, c.getBan());
        pst.setInt(9, c.getProfil().getId());
        pst.setString(10, c.getGender());
        pst.setInt(11, c.getID());

        return pst.executeUpdate();
    }

    @Override
    public int remove(Object o) throws SQLException {

        Client c = (Client) o;
        String query = " delete from Client where Entite=? ";

        PreparedStatement pst = DB.prepareStatement(query);

        pst.setInt(1, c.getID());

        return pst.executeUpdate() + super.remove(o);

    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
        String query = " select Entite.nom as nom,Client.*  from  Client inner join Entite on Client.Entite=Entite.ID  "; // preparation du requete sql
        PreparedStatement pst = DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
        List<Client> Clients = new ArrayList<>();//  Creation du List Reclamation
        ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
        while (res.next())// parcour du result set
        {
            GestionnaireProfil gp = new GestionnaireProfil();

            int Id_profil = res.getInt(8);
            Profil profil = new Profil();

            if (Id_profil != 0) {
                profil = gp.fetchOneByID(Id_profil);
            }
            int is_activated = res.getInt(9);
            if (is_activated == 1) {
                profil = gp.fetchOneByID(Id_profil);
            }

            Clients.add(
                    new Client(res.getInt("entite"), res.getString("nom"), res.getString(3), res.getString(4), res.getString(5), res.getDate(6), res.getString(7), profil, is_activated, res.getInt(9), res.getString("gender")
                    ));
        }
        return Clients;
    }

    public List<? extends Object> fetchAll(String aux, String target_column, int StartPoint, int BreakPoint) throws SQLException {
        String query = "  select * from (select Entite.nom as nom,Client.*  from  Client inner join Entite on Client.Entite=Entite.ID  limit  " + StartPoint + "," + BreakPoint + "  ) Client_l   "
                + " where ( " + target_column + " like ?  )  "; // preparation du requete sql

        PreparedStatement pst = DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
        List<Client> Clients = new ArrayList<>();//  Creation du List Reclamation
        pst.setString(1, "%" + aux + "%");

        ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
        while (res.next())// parcour du result set
        {
            Clients.add(new Client(res.getInt("entite"), res.getString("nom"), res.getString(3), res.getString(4), res.getString(5), res.getDate(6), res.getString(7)));
        }
        return Clients;
    }

    public List<? extends Object> fetchAll(String aux, int StartPoint, int BreakPoint) throws SQLException {
        String query = "  select * from (select Entite.nom as nom,Client.*  from  Client inner join Entite on Client.Entite=Entite.ID  limit  " + StartPoint + "," + BreakPoint + "  ) Client_l "
                + " where ( nom like ? or prenom like ? or pseudo like ?  or Entite like ?  or email like ? )";

        PreparedStatement pst = DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
        List<Client> Clients = new ArrayList<>();//  Creation du List Reclamation
        pst.setString(1, "%" + aux + "%");
        pst.setString(2, "%" + aux + "%");
        pst.setString(3, "%" + aux + "%");
        pst.setString(4, "%" + aux + "%");
        pst.setString(5, "%" + aux + "%");

        ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
        while (res.next())// parcour du result set
        {
            Clients.add(new Client(res.getInt("entite"), res.getString("nom"), res.getString(3), res.getString(4), res.getString(5), res.getDate(6), res.getString(7)));
        }
        return Clients;
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
        String query = "  select Entite.nom as nom,Client.*  from  Client inner join Entite on Client.Entite=Entite.ID "
                + "where ( nom like ? or prenom like ? or pseudo like ?  or ID like ?  or email like ? )"; // preparation du requete sql
        PreparedStatement pst = DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
        List<Client> Clients = new ArrayList<>();//  Creation du List Reclamation
        pst.setString(1, "%" + aux + "%");
        pst.setString(2, "%" + aux + "%");
        pst.setString(3, "%" + aux + "%");
        pst.setString(4, "%" + aux + "%");
        pst.setString(5, "%" + aux + "%");

        ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
        while (res.next())// parcour du result set
        {
            Clients.add(new Client(res.getInt("entite"), res.getString("nom"), res.getString(3), res.getString(4), res.getString(5), res.getDate(6), res.getString(7)));
        }
        return Clients;
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Client fetchOneByLoginandPass(String login, String pass) throws SQLException {
        Client Client = null;
        String query = " select Entite.nom as nom,Client.*  from  Client inner join Entite on Client.Entite=Entite.ID where pseudo =? and motdepasse =? "; // preparation du requete sql
        PreparedStatement pst = DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
        pst.setString(1, login);
        pst.setString(2, pass);
        Profil profil = null;
        GestionnaireProfil gp = new GestionnaireProfil();
        ResultSet res = pst.executeQuery();// execution du query et recuperation du result set

        if (res.next()) {

            int Id_profil = res.getInt(8);
            int is_activated = res.getInt(9);
            if (is_activated == 1 && Id_profil != 0) {
                profil = gp.fetchOneByID(Id_profil);
            }

            Client = new Client(res.getInt("entite"), res.getString("nom"), res.getString(3), res.getString(4), res.getString(5), res.getDate(6), res.getString(7), profil, is_activated, res.getInt(9), res.getString("gender"));
        }
        return Client;
    }

    public Client fetchOneById(int id) throws SQLException {
        Client Client;
        String query = " select Entite.nom as nom,Client.*  from  Client inner join Entite on Client.Entite=Entite.ID where client.Entite= ? "; // preparation du requete sql
        PreparedStatement pst = DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
        pst.setInt(1, id);
        Profil profil = null;
        GestionnaireProfil gp = new GestionnaireProfil();
        ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
        res.next();

        int Id_profil = res.getInt(8);
        int is_activated = res.getInt(9);
        if (is_activated == 1 && Id_profil != 0) {
            profil = gp.fetchOneByID(Id_profil);
        }

        Client = new Client(res.getInt("entite"), res.getString("nom"), res.getString(3), res.getString(4), res.getString(5), res.getDate(6), res.getString(7), profil, is_activated, res.getInt(9), res.getString("gender"));

        return Client;
    }

    //////////////////////PARTIE MATCHING //////////////////////////////
    public int matching_selon_horoscope(Client cl1, Client cl2) {
        int resultat_matching_horoscope = 0;
        String s1 = signe_astrologique(cl1.getDate_naissance());
        String s2 = signe_astrologique(cl2.getDate_naissance());
        if ((s1 == "Bélier" && s2 == "Taureau")
                || (s1 == "Taureau" && s2 == "Gémeaux")
                || (s1 == "Gémeaux" && s2 == "Cancer")
                || (s1 == "Cancer" && s2 == "Lion")
                || (s1 == "Lion" && s2 == "Vierge")
                || (s1 == "Vierge" && s2 == "Balance")
                || (s1 == "Balance" && s2 == "Scorpion")
                || (s1 == "Scorpion" && s2 == "Sagittaire")
                || (s1 == "Sagittaire" && s2 == "Capricorne")
                || (s1 == "Capricorne" && s2 == "Verseau")
                || (s1 == "Verseau" && s2 == "Poisson")
                || (s1 == "Poisson" && s2 == "Bélier")) {
            resultat_matching_horoscope = 100;
        }

        return resultat_matching_horoscope;
    }

    private String signe_astrologique(Date date_naissance) {
        String signe;

        int day = date_naissance.getDay();
        int month = date_naissance.getMonth();

        if ((month == 12 && day >= 22 && day <= 31) || (month == 1 && day >= 1 && day <= 19)) {
            signe = "Capricorne";
        } else if ((month == 1 && day >= 20 && day <= 31) || (month == 2 && day >= 1 && day <= 17)) {
            signe = "Verseau";
        } else if ((month == 2 && day >= 18 && day <= 29) || (month == 3 && day >= 1 && day <= 19)) {
            signe = "Poisson";
        } else if ((month == 3 && day >= 20 && day <= 31) || (month == 4 && day >= 1 && day <= 19)) {
            signe = "Bélier";
        } else if ((month == 4 && day >= 20 && day <= 30) || (month == 5 && day >= 1 && day <= 20)) {
            signe = "Taureau";
        } else if ((month == 5 && day >= 21 && day <= 31) || (month == 6 && day >= 1 && day <= 20)) {
            signe = "Gémeaux";
        } else if ((month == 6 && day >= 21 && day <= 30) || (month == 7 && day >= 1 && day <= 22)) {
            signe = "Cancer";
        } else if ((month == 7 && day >= 23 && day <= 31) || (month == 8 && day >= 1 && day <= 22)) {
            signe = "Lion";
        } else if ((month == 8 && day >= 23 && day <= 31) || (month == 9 && day >= 1 && day <= 22)) {
            signe = "Vierge";
        } else if ((month == 9 && day >= 23 && day <= 30) || (month == 10 && day >= 1 && day <= 22)) {
            signe = "Balance";
        } else if ((month == 10 && day >= 23 && day <= 31) || (month == 11 && day >= 1 && day <= 21)) {
            signe = "Scorpion";
        } else if ((month == 11 && day >= 22 && day <= 30) || (month == 12 && day >= 1 && day <= 21)) {
            signe = "Sagittaire";
        } else {
            signe = "Incorrect";
        }

        return signe;
    }

    //////////////////////PARTIE MATCHING //////////////////////////////
}
*/