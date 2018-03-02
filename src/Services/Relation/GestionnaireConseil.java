/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Relation;

import Entites.Relation.Conseil;
import Services.Gestionnaire;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author zhanimm
 */
public class GestionnaireConseil implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
        Conseil c=(Conseil)o;
      String query="insert into Conseil(contenu,niveau) values(?,?)";
      PreparedStatement pst= DB.prepareStatement(query);
      //pst.setInt(1,c.getID());
      pst.setString(1,c.getContenue());
      //pst.setBoolean(2,c.getEtat());
      pst.setInt(2,c.getNiveau());

      return pst.executeUpdate();
    }

  

    @Override
    public int update(Object o) throws SQLException {
        Conseil c=(Conseil)o;
      String query ="update Conseil set ID=?,contenu=?,niveau=? where ID=?";
      
      PreparedStatement pst=DB.prepareStatement(query);
      pst.setInt(1, c.getID());
      pst.setString(2,c.getContenue());
      //pst.setBoolean(3,c.getEtat());
      pst.setInt(3,c.getNiveau());
      pst.setInt(4,c.getID());
   
      return pst.executeUpdate();
    }

    @Override
    public int remove(Object o) throws SQLException {
        Conseil c=(Conseil)o;
    String query=" delete from Conseil where ID=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    
    pst.setInt(1,c.getID());
    
    return pst.executeUpdate();
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
         String query=" select *  from  Conseil "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Conseil>Conseils = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
             Conseils.add(new Conseil(res.getInt("ID"),res.getString("contenu"),/*res.getBoolean("etat"),*/res.getInt("niveau")));
           }
          return Conseils;
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public List<? extends Object> ParNiveau(int n) throws SQLException {
         String query=" select *  from  Conseil "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Conseil>Conseils = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next()) // parcour du result set
          {
       
             Conseils.add(new Conseil(res.getInt("ID"),res.getString("contenu"),res.getInt("niveau")));
           }
          return Conseils.stream().filter(c->c.getNiveau()==n).distinct().collect(Collectors.toList());
    }
}
