/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Profil;

import Entites.Profil.Actualite;
import Entites.Profil.Interaction;
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
 * @author Ransom
 */
public class GestionnaireInteraction implements Gestionnaire <Interaction> {

    @Override
    public int create( Interaction i) throws SQLException {
        String query="insert into interaction(actualite,client,commentaire) values(?,?,?) ";
        PreparedStatement pst = DB.prepareStatement(query);
        pst.setInt(1,i.getActualite().getID());
        pst.setInt(2,i.getOwner().getId());
        pst.setString(3, i.getCommentaire());
        return pst.executeUpdate();
    }

    @Override
    public int update(Interaction i) throws SQLException {
        String query="update interaction set actualite=?,client=?,commentaire=? where id=? ";
        PreparedStatement pst = DB.prepareStatement(query);
        pst.setInt(1,i.getActualite().getID());
        pst.setInt(2,i.getOwner().getId());
        pst.setString(3, i.getCommentaire());
        pst.setInt(4,i.getID());
        return pst.executeUpdate();
    }

    @Override
    public int remove(Interaction i) throws SQLException {
        String query="delete from interaction  where id=? ";
        PreparedStatement pst = DB.prepareStatement(query);
        pst.setInt(1,i.getID());
        return pst.executeUpdate();
    }
     
    
    public List<Interaction> fetchAllByParent(Actualite a) throws SQLException
    {
   List<Interaction>Interactions ;
        GestionnaireUser gc= new GestionnaireUser();
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
    public List<Interaction > fetchAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public Interaction fetchOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Interaction fetchOnByCriteria(Map<String, String> Criteras) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Interaction> fetchSomeBy(String aux, String target_column, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Interaction> fetchSomeBy(String aux, int target_column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Interaction> fetchSomeBy(String aux, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
