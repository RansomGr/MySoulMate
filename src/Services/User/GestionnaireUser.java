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
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import java.io.CharArrayReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mysoulmate.MySoulMate;

/**
 *
 * @author Ransom
 */
public class GestionnaireUser implements Gestionnaire<Utilisateur> {
    @Override
    public int create(Utilisateur o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Utilisateur fetchOneById(int id) {
     GestionnaireProfil Sp = new GestionnaireProfil();
     GestionnaireAdresse Sa= new GestionnaireAdresse();
        
        try {
            String req ="Select * from utilisateur where id=? ";
                     PreparedStatement pst=this.DB.prepareStatement(req);
                     pst.setInt(1, id);
             ResultSet res =pst.executeQuery();
           return new Utilisateur(
                     res.getInt(1),Sp.fetchOneById(res.getInt(2)), Sa.fetchOneById(res.getInt(3)),
                     res.getString(4),res.getString(5), res.getString(6), res.getString(7), res.getInt(8), 
                     res.getString(9), res.getString(10), res.getDate(11), res.getString(12), res.getDate(13), 
                     res.getString(14), res.getString(15), res.getString(16), res.getString(17), res.getDate(18)
             );
             } catch (SQLException ex) {
            Logger.getLogger(GestionnaireUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Utilisateur fetchOnByCriteria(Map<String, String> Criteras) {
   GestionnaireProfil Sp = new GestionnaireProfil();
    GestionnaireAdresse Sa= new GestionnaireAdresse();
        
        try {
            String req ="Select * from utilisateur where ";
            int i=0;
            for(Map.Entry e:Criteras.entrySet())
            {
                i++;
                req+=e.getKey()+" =?";
                if(i>0&&i<Criteras.size())
                    req+=" and";
            }
            PreparedStatement pst=this.DB.prepareStatement(req);
            i=0;
             for(Map.Entry e:Criteras.entrySet())
            {
                i++;
                pst.setString(i,e.getValue().toString());
            }
             ResultSet res =pst.executeQuery();
           return new Utilisateur(
                     res.getInt(1),Sp.fetchOneById(res.getInt(2)), Sa.fetchOneById(res.getInt(3)),
                     res.getString(4),res.getString(5), res.getString(6), res.getString(7), res.getInt(8), 
                     res.getString(9), res.getString(10), res.getDate(11), res.getString(12), res.getDate(13), 
                     res.getString(14), res.getString(15), res.getString(16), res.getString(17), res.getDate(18)
             );
             } catch (SQLException ex) {
            Logger.getLogger(GestionnaireUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
          

    @Override
    public List<Utilisateur> fetchSomeBy(String aux, String target_column, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Utilisateur> fetchSomeBy(String aux, int target_column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Utilisateur> fetchSomeBy(String aux, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Utilisateur fetchOneBycredentials(String login,String pass)
    {
        String url="http://localhost/MySoulMate-Symphony/web/app_dev/";
       ConnectionRequest con = new ConnectionRequest();
         Utilisateur u =new Utilisateur();
        con.setUrl(url+"User?login="+login+"&password="+pass);
        con.addResponseListener((NetworkEvent evt) -> {
            try {
                String str = new String(con.getResponseData());
                JSONParser jsonp = new JSONParser();
                
                
                Map<String, Object> Users = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                if((int)Float.parseFloat(Users.get("id").toString())!=-1){// getting the user if found -1 not found
                    u.setId((int)Float.parseFloat(Users.get("id").toString()));
                    u.setNom(Users.get("nom").toString());
                    u.setPrenom(Users.get("prenom").toString());
                    u.setGender(Users.get("gender").toString());
                    u.setUsername(Users.get("username").toString());
                    CustomEspritJSONParser cejp = new CustomEspritJSONParser(Users.get("datenaissance").toString());
                    String x=cejp.getNestedItems().get("timestamp").toString();
                    u.setDatanaissance(new java.util.Date((long)Float.parseFloat(x)*1000));
                    cejp =new CustomEspritJSONParser(Users.get("profil").toString());
                    Map<String,Object>Profil=cejp.getNestedItems();
                    cejp = new CustomEspritJSONParser(Profil.get("caracteristique").toString());
                    Map<String,Object>Caracteristique =cejp.getNestedItems();
                    cejp= new   CustomEspritJSONParser(Profil.get("preference").toString());
                    Map<String,Object>Preference = cejp.getNestedItems();
                    Caracteristique C = new Caracteristique(
                            (int)Float.parseFloat(Caracteristique.get("id").toString()),
                            Caracteristique.get("corpulence").toString(),
                            Caracteristique.get("pilosite").toString(),
                            Caracteristique.get("origine").toString(),
                            Caracteristique.get("profession").toString(),
                            Caracteristique.get("alcool").toString(),
                            Caracteristique.get("tabac").toString(),
                            Caracteristique.get("taille").toString(),
                            Caracteristique.get("cheveux").toString(),
                            Caracteristique.get("yeux").toString(),
                            Caracteristique.get("caractere").toString(),
                            Caracteristique.get("statut").toString(),
                            Caracteristique.get("cuisine").toString());
                    
                    Caracteristique pref =new Caracteristique(
                            (int)Float.parseFloat(Preference.get("id").toString()),
                            Preference.get("corpulence").toString(),
                            Preference.get("pilosite").toString(),
                            Preference.get("origine").toString(),
                            Preference.get("profession").toString(),
                            Preference.get("alcool").toString(),
                            Preference.get("tabac").toString(),
                            Preference.get("taille").toString(),
                            Preference.get("cheveux").toString(),
                            Preference.get("yeux").toString(),
                            Preference.get("caractere").toString(),
                            Preference.get("statut").toString(),
                            Preference.get("cuisine").toString());
                    Profil p= new Profil(
                            (int)Float.parseFloat(Profil.get("id").toString()),
                            C,Profil.get("photo").toString(),
                            Profil.get("description").toString(),
                            pref,null);
                    u.setProfil(p);
                    
                    MySoulMate.setLogged_in_Client(u);
                }
                else
                    MySoulMate.setLogged_in_Client(null);        
            } catch (IOException ex) {
                Logger.getLogger(GestionnaireUser.class.getName()).log(Level.SEVERE, null, ex);
            }
             
                    });
        NetworkManager.getInstance().addToQueueAndWait(con);   
        return u;
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
