/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Profil;

import Entites.Profil.Actualite;
import Entites.Profil.Mention_Jaime;
import Entites.User.Client;
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
public class GestionnaireMention_Jaime implements Gestionnaire{

    @Override
    public int create(Object o) throws SQLException {
      Mention_Jaime Mj=(Mention_Jaime)o;
      String query ="insert into mention_jaime(client,actualite,jaime) values(?,?,?)";
      PreparedStatement pst =DB.prepareStatement(query);
      pst.setInt(1, Mj.getOwner().getID());
      pst.setInt(2,Mj.getActualite().getID());
      pst.setString(3,Mj.getJaime());
      return pst.executeUpdate();
    }

    @Override
    public int update(Object o) throws SQLException {
        
      Mention_Jaime Mj=(Mention_Jaime)o;
      String query ="update  mention_jaime set client=?,actualite=?,jaime=? where client=? and actualite=?";
      PreparedStatement pst =DB.prepareStatement(query);
      
      pst.setInt(1, Mj.getOwner().getID());
      pst.setInt(2,Mj.getActualite().getID());
      pst.setString(3,Mj.getJaime());
      pst.setInt(4, Mj.getOwner().getID());
      pst.setInt(5,Mj.getActualite().getID());
      
      return pst.executeUpdate();
    }

    @Override
    public int remove(Object o) throws SQLException {
      
      Mention_Jaime Mj=(Mention_Jaime)o;
      String query ="delete from  mention_jaime  where client=? and actualite=?";
      PreparedStatement pst =DB.prepareStatement(query);   
      pst.setInt(1, Mj.getOwner().getID());
      pst.setInt(2,Mj.getActualite().getID()); 
      return pst.executeUpdate();
    }
    public List<Mention_Jaime> fetchAllByTarget(Actualite a) throws SQLException
    {          
      String query ="select * from  mention_jaime   where  actualite=?";
      PreparedStatement pst =DB.prepareStatement(query);
      List<Mention_Jaime>Mentions = new ArrayList<>();
      GestionnaireClient gc = new GestionnaireClient();
      pst.setInt(1,a.getID());
      ResultSet res = pst.executeQuery();
      while(res.next())
      {
          Mentions.add(new Mention_Jaime(a,gc.fetchOneById(res.getInt("client")),res.getString("jaime")));
      }
      return Mentions;
    }
    public Mention_Jaime fetchOneByOwners(Client c,Actualite a) throws SQLException
    {          
      String query ="select jaime from  mention_jaime  where client=? and actualite=?";
      PreparedStatement pst =DB.prepareStatement(query);
      
      pst.setInt(1, c.getID());
      pst.setInt(2,a.getID());
      ResultSet res = pst.executeQuery();
      if(res.next())
      {
          return new Mention_Jaime(a,c,res.getString("jaime"));
      }
      return null;
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
