/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Relation;

import Entites.Relation.Contenue_Moment;
import Services.Gestionnaire;
import static Services.Gestionnaire.DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author zhanimm
 */
public class GestionnaireContenue_Moment implements Gestionnaire<Contenue_Moment> {

  


    @Override
    public List<Contenue_Moment> fetchAll() throws SQLException {
          String query=" select *  from  Contenu_Moment "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Contenue_Moment>Contenues = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
             Contenues.add(new Contenue_Moment(res.getInt("ID"),res.getString("nom"),res.getString("contenu"),res.getString("photo"),res.getString("description"),res.getDate("date_moment")));
           }
          return Contenues;
    }
public void populateListView() {
           
        }


    @Override
    public int create(Contenue_Moment c) throws SQLException {

      //String query="insert into Contenu_Moment(ID,nom,contenu,photo,description,date_moment) values(?,?,?,?,?,?)";
      String query="insert into Contenu_Moment(nom,description,date_moment,photo) values(?,?,?,?)";
      PreparedStatement pst= DB.prepareStatement(query);
     // pst.setInt(1, c.getID());
      
     // pst.setString(2,c.getContenue());
      pst.setString(1,c.getNom());
      pst.setString(2,c.getDescription());
      pst.setDate(3,c.getDate_moment());
      pst.setString(4,c.getPhoto());

      
      return pst.executeUpdate();    }

    @Override
    public int update(Contenue_Moment c) throws SQLException {
 String query ="update Contenu_Moment set ID=?,nom=?,contenu=?,photo=?,description=?,date_moment=? where ID=?";
      
      PreparedStatement pst=DB.prepareStatement(query);
      /*pst.setInt(1, c.getID());
      pst.setString(2,c.getNom());
      pst.setString(3,c.getContenue());
      pst.setString(4,c.getPhoto());
      pst.setString(5,c.getDescription());
      pst.setDate(6,c.getDate_moment());*/
      pst.setString(1,c.getNom());
      pst.setString(2,c.getContenue());
      pst.setString(4,c.getDescription());
      pst.setDate(5,c.getDate_moment());
      pst.setString(3,c.getPhoto());
      
      pst.setInt(6, c.getID());
      
      return pst.executeUpdate();    }

    @Override
    public int remove(Contenue_Moment c) throws SQLException {
String query=" delete from Contenu_Moment where ID=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    
    pst.setInt(1,c.getID());
    
    return pst.executeUpdate();   }

    @Override
    public Contenue_Moment fetchOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Contenue_Moment fetchOnByCriteria(Map<String, String> Criteras) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Contenue_Moment> fetchSomeBy(String aux, String target_column, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Contenue_Moment> fetchSomeBy(String aux, int target_column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Contenue_Moment> fetchSomeBy(String aux, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
