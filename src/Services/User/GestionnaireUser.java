/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.User;

import Entites.User.Utilisateur;
import Services.Gestionnaire;
import Services.Profil.GestionnaireAdresse;
import Services.Profil.GestionnaireProfil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
}
