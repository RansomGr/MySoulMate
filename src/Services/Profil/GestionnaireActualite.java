/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Profil;

import Entites.Profil.Actualite;
import Services.Gestionnaire;
import Services.User.GestionnaireUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sofiene
 */
public class GestionnaireActualite implements Gestionnaire <Actualite> {

     @Override
    public int create(Actualite a ) throws SQLException {
        
        String query = " insert into Actualite (contenu,photo,createur) values (?,?,?) "; // preparation du query

        PreparedStatement pst = DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment

        pst.setString(1, a.getContenu());
        pst.setString(2, a.getPhoto());
        //pst.setString(3, a.getAdd_date().toString());

        pst.setInt(3, a.getCreateur().getId());

        return pst.executeUpdate();
    }
  @Override
    public int update(Actualite a) throws SQLException {
        String query = " update  Actualite set createur=?,contenu=?,photo=?,createur=? where id=?  ";
        PreparedStatement pst = DB.prepareStatement(query);
        pst.setInt(1, a.getCreateur().getId());
        pst.setString(2, a.getContenu());
        pst.setString(3, a.getPhoto());
        pst.setInt(5, a.getID());
        return pst.executeUpdate();
    }

      @Override
    public int remove(Actualite a) throws SQLException {
        String query = "delete  from Actualite where ID=? ";
        PreparedStatement pst = DB.prepareStatement(query);
        pst.setInt(1, a.getID());
        return pst.executeUpdate();
    }
    public List<Actualite> fetchAllById(int id) throws SQLException {
        String query = " select * from actualite where ID=? ";
        PreparedStatement pst = DB.prepareStatement(query);
        pst.setInt(1, id);
        ResultSet res = pst.executeQuery();
        GestionnaireUser gc;
        gc = new GestionnaireUser();
       
        List<Actualite> ListActualites = new ArrayList<>();
        while (res.next()) {
            ListActualites.add(new Actualite(res.getInt(1),gc.fetchOneById(res.getInt("createur")), res.getString("contenu"), res.getString("photo"), res.getDate("add_date").toLocalDate()));
        }
        return ListActualites;
    }
  
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Actualite> fetchAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Actualite fetchOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Actualite fetchOnByCriteria(Map<String, String> Criteras) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Actualite> fetchSomeBy(String aux, String target_column, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Actualite> fetchSomeBy(String aux, int target_column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Actualite> fetchSomeBy(String aux, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
   

}
