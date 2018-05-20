/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Profil;

import Entites.Profil.Actualite;
import Entites.Profil.Profil;
import Entites.User.Client;
import Services.Gestionnaire;
import Services.GestionnaireAbstractEntite;
import Services.User.GestionnaireClient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sofiene
 */
public class GestionnaireActualite implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
        Actualite a = (Actualite) o;
        String query = " insert into Actualite (entite,contenu,photo,createur) values (?,?,?,?) "; // preparation du query

        PreparedStatement pst = DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment

        pst.setInt(1, a.getOwner().getID());
        pst.setString(2, a.getContenu());
        pst.setString(3, a.getPhoto());
        pst.setInt(4, a.getWriter().getID());

        return pst.executeUpdate();
    }

    @Override
    public int update(Object o) throws SQLException {
        Actualite a = (Actualite) o;
        String query = " update  Actualite set entite=?,contenu=?,photo=?,createur=? where id=?  ";
        PreparedStatement pst = DB.prepareStatement(query);
        pst.setInt(1, a.getOwner().getID());
        pst.setString(2, a.getContenu());
        pst.setString(3, a.getPhoto());
        pst.setInt(4, a.getWriter().getID());
        pst.setInt(5, a.getID());
        return pst.executeUpdate();
    }

    @Override
    public int remove(Object o) throws SQLException {
        Actualite a = (Actualite) o;
        String query = "delete  from Actualite where ID=? ";
        PreparedStatement pst = DB.prepareStatement(query);
        pst.setInt(1, a.getID());
        return pst.executeUpdate();
    }

    public List<Actualite> fetchAllById(int id) throws SQLException {
        String query = " select * from actualite where entite=? ";
        PreparedStatement pst = DB.prepareStatement(query);
        pst.setInt(1, id);
        ResultSet res = pst.executeQuery();
        GestionnaireClient gc = new GestionnaireClient();
       
        List<Actualite> ListActualites = new ArrayList<>();
        while (res.next()) {
            ListActualites.add(new Actualite(res.getInt(1), GestionnaireAbstractEntite.findDerviedChildbyId(res.getInt(2)), res.getString("contenu"), res.getString("photo"), res.getDate("add_date").toLocalDate(), gc.fetchOneById(res.getInt("createur"))));
        }
        return ListActualites;
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
