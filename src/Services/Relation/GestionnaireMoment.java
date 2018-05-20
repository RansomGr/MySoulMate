/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Relation;

import Entites.Relation.Contenue_Moment;
import Entites.Relation.Moment;
import Entites.Relation.Relation;
import Services.Gestionnaire;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zhanimm
 */
public class GestionnaireMoment implements Gestionnaire
{

    @Override
    public int create(Object o) throws SQLException {
         Moment m=(Moment)o;
      String query="insert into Moment(relation,c_moment) values(?,?)";
      PreparedStatement pst= DB.prepareStatement(query);
      
      pst.setInt(1, m.getRelation().getID());
      pst.setInt(2,m.getC_moment().getID());
      
      return pst.executeUpdate();
    }

    @Override
    public int update(Object o) throws SQLException {
        Moment m=(Moment)o;

        String query ="update Moment set relation=?,c_moment=? where relation=? and c_moment=?";
        PreparedStatement pst=DB.prepareStatement(query);
        
        pst.setInt(1,m.getRelation().getID());// Binding du premier valeur mentionner dans le query "?" 
        pst.setInt(2,m.getC_moment().getID());
        return pst.executeUpdate();
    }

    @Override
    public int remove(Object o) throws SQLException {
        Moment m=(Moment)o;
    String query=" delete from Mom where relation=? and c_moment=?";
    
    PreparedStatement pst=DB.prepareStatement(query);
    
         pst.setInt(1, m.getRelation().getID());//Binding du valeur de l'id mentionné dans le query "?" 
         pst.setInt(2, m.getC_moment().getID());    
    return pst.executeUpdate();
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
        
          String query=" select  *  from Moment"
                  + " inner join Relation"
                  + " on Moment.Relation=Relation.id "
                  + "inner join Contenue_Moment "
                  + "on Moment.Contenue_Moment=Contenue_Moment.id  "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Moment>Moments = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
               int Relation_ID=res.getInt("relation");
             int Contenue_Moment_ID=res.getInt("c_moment");
             Moments.add(
                     new Moment(
                               new Relation(),
                               new Contenue_Moment()
                                      
                                      )
                              );
           }
          return Moments;

    }

    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object fetchOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object fetchOnByCriteria(Map Criteras) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List fetchSomeBy(String aux, String target_column, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List fetchSomeBy(String aux, int target_column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List fetchSomeBy(String aux, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
