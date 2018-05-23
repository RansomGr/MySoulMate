/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.User;

import APIS.User.CustomEspritJSONParser;
import Entites.Profil.Caracteristique;
import Entites.Profil.Profil;
import Entites.User.Utilisateur;
import Services.Gestionnaire;
import Services.Profil.GestionnaireAdresse;
import Services.Profil.GestionnaireProfil;

import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import java.io.CharArrayReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mysoulmate.MySoulMate;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Ransom
 */
public class GestionnaireUser implements Gestionnaire<Utilisateur> {
    String role;
    public GestionnaireUser(String role)
    {
        this.role=role;
    }
    public GestionnaireUser()
    {
        this.role="a:0:{}";
    }
    @Override
    public int create(Utilisateur o) throws SQLException {
         String query = "insert into Utilisateur (nom,prenom,password,email,date_naissane,username,gender) values(?,?,?,?,?,?,?,?)";
        PreparedStatement pst = DB.prepareStatement(query);
     
        pst.setString(1, o.getNom());
        pst.setString(2, o.getPrenom());
        pst.setString(3,BCrypt.hashpw(o.getPassword(),BCrypt.gensalt(12)).replaceFirst("a", "y"));
        pst.setString(4, o.getEmail());
        pst.setDate(5, new java.sql.Date(o.getDatanaissance().getTime()));
        pst.setString(6, o.getUsername());
        pst.setString(7, o.getGender());
    //    pst.set

        return pst.executeUpdate();
    }

    @Override
    public int update(Utilisateur o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int remove(Utilisateur o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Utilisateur> fetchAll() throws SQLException {
        GestionnaireProfil Sp = new GestionnaireProfil();
        GestionnaireAdresse Sa = new GestionnaireAdresse();
        List<Utilisateur> users = new ArrayList<>();

        try {
            String req = "Select * from utilisateur where roles=? ";
            
            PreparedStatement pst = this.DB.prepareStatement(req);
            pst.setString(1, role);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                users.add(new Utilisateur(
                        res.getInt(1), Sp.fetchOneById(res.getInt(6)), Sa.fetchOneById(res.getInt(18)),
                        res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getInt(11),
                        res.getString(12), res.getString(13), res.getDate(14), res.getString(15), res.getDate(16),
                        res.getString(17), res.getString(2), res.getString(3), res.getString(4), res.getDate(5)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    public Utilisateur fetchOneById(int id) {
        GestionnaireProfil Sp = new GestionnaireProfil();
        GestionnaireAdresse Sa = new GestionnaireAdresse();

        try {
            String req = "Select * from utilisateur where id=? ";
            PreparedStatement pst = this.DB.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                return new Utilisateur(
                        res.getInt(1), Sp.fetchOneById(res.getInt(6)), Sa.fetchOneById(res.getInt(18)),
                        res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getInt(11),
                        res.getString(12), res.getString(13), res.getDate(14), res.getString(15), res.getDate(16),
                        res.getString(17), res.getString(2), res.getString(3), res.getString(4), res.getDate(5)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Utilisateur fetchOnByCriteria(Map<String, String> Criteras) {
        GestionnaireProfil Sp = new GestionnaireProfil();
        GestionnaireAdresse Sa = new GestionnaireAdresse();

        try {
            String req = "Select * from utilisateur where ";
            int i = 0;
            for (Map.Entry e : Criteras.entrySet()) {
                i++;
                req += e.getKey() + " =?";
                if (i > 0 && i < Criteras.size()) {
                    req += " and";
                }
            }
            PreparedStatement pst = this.DB.prepareStatement(req);
            i = 0;
            for (Map.Entry e : Criteras.entrySet()) {
                i++;
                pst.setString(i, e.getValue().toString());
            }
            ResultSet res = pst.executeQuery();
            return new Utilisateur(
                    res.getInt(1), Sp.fetchOneById(res.getInt(6)), Sa.fetchOneById(res.getInt(18)),
                        res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getInt(11),
                        res.getString(12), res.getString(13), res.getDate(14), res.getString(15), res.getDate(16),
                        res.getString(17), res.getString(2), res.getString(3), res.getString(4), res.getDate(5)
            );
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Utilisateur> fetchSomeBy(String aux, String target_column, int StartPoint, int BreakPoint) {
        List<Utilisateur> Admins = new ArrayList<>();//  Creation du List Reclamation
        try {

            GestionnaireProfil Sp = new GestionnaireProfil();
            GestionnaireAdresse Sa = new GestionnaireAdresse();
            String query = " select * from (select * from utilisateur limit  " + StartPoint + "," + BreakPoint + " ) Admin_l "
                    + " where ( " + target_column + " like ?  ) and roles=?   ";
            // preparation du requete sql
            PreparedStatement pst = DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
            pst.setString(1, "%" + aux + "%");
            pst.setString(2, role);
            ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
            while (res.next())// parcour du result set
            {
                Admins.add(new Utilisateur(
                       res.getInt(1), Sp.fetchOneById(res.getInt(6)), Sa.fetchOneById(res.getInt(18)),
                        res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getInt(11),
                        res.getString(12), res.getString(13), res.getDate(14), res.getString(15), res.getDate(16),
                        res.getString(17), res.getString(2), res.getString(3), res.getString(4), res.getDate(5)
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Admins;
    }

    @Override
    public List<Utilisateur> fetchSomeBy(String aux, int target_column) {
        List<Utilisateur> Clients = new ArrayList<>();//  Creation du List Reclamation
        try {
            String query = "  select * from Utilisateur  "
                    + "where ( nom like ? or prenom like ? or username like ?  or ID like ?  or email like ? ) and roles =?"; // preparation du requete sql
            PreparedStatement pst = DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment 
            pst.setString(1, "%" + aux + "%");
            pst.setString(2, "%" + aux + "%");
            pst.setString(3, "%" + aux + "%");
            pst.setString(4, "%" + aux + "%");
            pst.setString(5, "%" + aux + "%");
            pst.setString(6, role);

            ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
            GestionnaireProfil Sp = new GestionnaireProfil();
            GestionnaireAdresse Sa = new GestionnaireAdresse();
            while (res.next())// parcour du result set
            {
                Clients.add(new Utilisateur(res.getInt(1), Sp.fetchOneById(res.getInt(6)), Sa.fetchOneById(res.getInt(18)),
                        res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getInt(11),
                        res.getString(12), res.getString(13), res.getDate(14), res.getString(15), res.getDate(16),
                        res.getString(17), res.getString(2), res.getString(3), res.getString(4), res.getDate(5)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Clients;
    }

    @Override
    public List<Utilisateur> fetchSomeBy(String aux, int StartPoint, int BreakPoint) {
         List<Utilisateur> Clients = new ArrayList<>();//  Creation du List Reclamation
        try {
            String query = "  select * from ( select * from Utilisateur  limit  " + StartPoint + "," + BreakPoint + "  ) Client_l "
                    + " where ( nom like ? or prenom like ? or username like ?  or email like ?  ) and roles=?";
            
            PreparedStatement pst = DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
             GestionnaireProfil Sp = new GestionnaireProfil();
            GestionnaireAdresse Sa = new GestionnaireAdresse();
            pst.setString(1, "%" + aux + "%");
            pst.setString(2, "%" + aux + "%");
            pst.setString(3, "%" + aux + "%");
            pst.setString(4, "%" + aux + "%");
            pst.setString(5, role);
         
            
            ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
            while (res.next())// parcour du result set
            {
                Clients.add(new Utilisateur(res.getInt(1), Sp.fetchOneById(res.getInt(6)), Sa.fetchOneById(res.getInt(18)),
                        res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getInt(11),
                        res.getString(12), res.getString(13), res.getDate(14), res.getString(15), res.getDate(16),
                        res.getString(17), res.getString(2), res.getString(3), res.getString(4), res.getDate(5)));
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireUser.class.getName()).log(Level.SEVERE, null, ex);
        }
          return Clients;
    }

    public Utilisateur fetchOneBycredentials(String login, String pass) {

        GestionnaireProfil Sp = new GestionnaireProfil();
        GestionnaireAdresse Sa = new GestionnaireAdresse();

        try {
            String req = "Select * from utilisateur where username=? and roles =?";

            PreparedStatement pst = this.DB.prepareStatement(req);
            pst.setString(1, login);
            pst.setString(2, role);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                String crypted_pass = res.getString(13);
                String crypted_pass2 = crypted_pass.replaceFirst("y", "a");
                System.out.println(crypted_pass2);
                if (BCrypt.checkpw(pass, crypted_pass2)) {
                    return new Utilisateur(
                            res.getInt(1), Sp.fetchOneById(res.getInt(6)), Sa.fetchOneById(res.getInt(18)),
                            res.getString(7), res.getString(8), res.getString(9), res.getString(10), res.getInt(11),
                            res.getString(12), res.getString(13), res.getDate(14), res.getString(15), res.getDate(16),
                            res.getString(17), res.getString(2), res.getString(3), res.getString(4), res.getDate(5)
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionnaireUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    //////////////////////PARTIE MATCHING //////////////////////////////
    public int matching_selon_horoscope(Utilisateur cl1, Utilisateur cl2) {
        int resultat_matching_horoscope = 0;
        String s1 = signe_astrologique(new java.sql.Date(cl1.getDatanaissance().getTime()));
        String s2 = signe_astrologique(new java.sql.Date(cl2.getDatanaissance().getTime()));
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
