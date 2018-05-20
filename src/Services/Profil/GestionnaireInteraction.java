/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Profil;

import Entites.Profil.Actualite;
import Entites.Profil.Interaction;
import Services.Gestionnaire;
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
public class GestionnaireInteraction implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
        Interaction i =(Interaction)o;
        String query="insert into interaction(actualite,client,commentaire) values(?,?,?) ";
        PreparedStatement pst = DB.prepareStatement(query);
        pst.setInt(1,i.getActualite().getID());
        pst.setInt(2,i.getOwner().getID());
        pst.setString(3, i.getCommentaire());
        return pst.executeUpdate();
    }

    @Override
    public int update(Object o) throws SQLException {
        Interaction i =(Interaction)o;
        String query="update interaction set actualite=?,client=?,commentaire=? where id=? ";
        PreparedStatement pst = DB.prepareStatement(query);
        pst.setInt(1,i.getActualite().getID());
        pst.setInt(2,i.getOwner().getID());
        pst.setString(3, i.getCommentaire());
        pst.setInt(4,i.getID());
        return pst.executeUpdate();
    }

    @Override
    public int remove(Object o) throws SQLException {
        Interaction i =(Interaction)o;
        String query="delete from interaction  where id=? ";
        PreparedStatement pst = DB.prepareStatement(query);
        pst.setInt(1,i.getID());
        return pst.executeUpdate();
    }
     
    
    public List<Interaction> fetchAllByParent(Actualite a) throws SQLException
    {
   List<Interaction>Interactions ;
        GestionnaireClient gc= new GestionnaireClient();
        Interactions=new ArrayList<>();
        String query ="select * from interaction where actualite=?";
        PreparedStatement pst = DB.prepareStatement(query);
        pst.setInt(1, a.getID());
        ResultSet res =pst.executeQuery();
        while(res.next())
        {
          Interactions.add(new Interaction(res.getInt("id"), a,gc.fetchOneById(res.getInt("Client")),res.getString("commentaire"),res.getDate("dateheure")));
        }
        return Interactions;
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
