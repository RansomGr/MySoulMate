/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Relation;

import Entites.Relation.Relation;
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
 * @author zhanimm
 */
public class GestionnaireRelation implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
       
      Relation r=(Relation)o;
      String query="insert into Relation(ID,points_relation,niveau,date_debut,date_fin) values(?,?,?,?,?,?)";
      PreparedStatement pst= DB.prepareStatement(query);
      
      pst.setInt(1, r.getID());
      pst.setInt(2,r.getPoints_relation());
      pst.setString(3,r.getNiveau());
      pst.setDate(4,r.getDate_debut());
      pst.setDate(5,r.getDate_fin());
      
      return pst.executeUpdate();

    }

    @Override
    public int update(Object o) throws SQLException {
        Relation r=(Relation)o;

        String query ="update Relation set ID=?,Client1=?,Client2=?,points_relation=?,niveau=?,date_debut=?,date_fin=? where ID=?";
        PreparedStatement pst=DB.prepareStatement(query);
        
        pst.setInt(1, r.getID());
        pst.setInt(2,r.getPoints_relation());
        pst.setString(3,r.getNiveau());
        pst.setDate(4,r.getDate_debut());
        pst.setDate(5,r.getDate_fin());
        

        return pst.executeUpdate();

      
    }

    @Override
    public int remove(Object o) throws SQLException {
        
    Relation r=(Relation)o;
    String query=" delete from Relation where ID=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    
    pst.setInt(1,r.getID());
    
    return pst.executeUpdate();
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
String query=" select *  from  Relation "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Relation>Relations = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          GestionnaireClient g=new GestionnaireClient();
           List<Client> Clients=(List<Client>) g.fetchAll();
          while(res.next())// parcour du result set
          {
             int Client1_ID=res.getInt("client1");
             int Client2_ID=res.getInt("client2");

              Client client1=Clients.stream().filter(c->c.getID()==Client1_ID).findFirst().get();
              Client client2=Clients.stream().filter(c->c.getID()==Client2_ID).findFirst().get();

            Relations.add(new Relation(
                    res.getInt("ID"),
                    client1,
                    client2,
                    res.getInt("point_relation"),
                    res.getString("niveau"),
                    res.getDate("date_debut"),
                    res.getDate("date_fin")
                                       )
            );
           }
          return Relations;    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
