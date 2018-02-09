/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Relation;

import Entites.Relation.Contenue_Moment;
import Services.Gestionnaire;
import static Services.Gestionnaire.DB;
import Services.GestionnaireAbstractEntite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhanimm
 */
public class GestionnaireContenue_Moment extends GestionnaireAbstractEntite implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
         super.create(o);
    
      Contenue_Moment c=(Contenue_Moment)o;
      String query="insert into Contenue_Moment(Entite,contenue,photo,description,date_moment) values(?,?,?,?,?)";
      PreparedStatement pst= DB.prepareStatement(query);
      pst.setInt(1, c.getID());
      pst.setString(2,c.getContenue());
      pst.setString(3,c.getPhoto());
      pst.setString(4,c.getDescription());
      pst.setDate(5,c.getDate_moment());
      
      return pst.executeUpdate();
    }

    @Override
    public int update(Object o) throws SQLException {
         super.update(o);
      Contenue_Moment c=(Contenue_Moment)o;
      String query ="update Contenue_Moment set Entite=?,contenue=?,photo=?,description=?,date_moment=? where Entite=?";
      
      PreparedStatement pst=DB.prepareStatement(query);
      pst.setInt(1, c.getID());
      pst.setString(2,c.getContenue());
      pst.setString(3,c.getPhoto());
      pst.setString(4,c.getDescription());
      pst.setDate(5,c.getDate_moment());
      
            return pst.executeUpdate();

    }

    @Override
    public int remove(Object o) throws SQLException {
         super.remove(o);
    Contenue_Moment c=(Contenue_Moment)o;
    String query=" delete from Contenue_Moment where Entite=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    
    pst.setInt(1,c.getID());
    
    return pst.executeUpdate();
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
          String query=" select Entite.nom as nom,Contenue_Moment.*  from  Contenue_Moment inner join Entite on Contneue_Moment.Entite=Entite.ID "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Contenue_Moment>Contenues = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
             Contenues.add(new Contenue_Moment(res.getInt("entite"),res.getString("nom"),res.getString("contenue"),res.getString("photo"),res.getString("description"),res.getDate("date_moment")));
           }
          return Contenues;
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
