/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Relation;

import Entites.Relation.Relation;
import Entites.User.Utilisateur;
import Services.Gestionnaire;
import Services.User.GestionnaireClient;
import Services.User.GestionnaireUser;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mysoulmate.MySoulMate;

/**
 *
 * @author zhanimm
 */
public class GestionnaireRelation implements Gestionnaire<Relation> {

    
  
    @Override
    public int create(Relation r) throws SQLException {
       
      String query="insert into Relation(ID,client1,client2,point_relation,niveau,date_debut,date_fin) values(?,?,?,?,?,?,?)";
      PreparedStatement pst= DB.prepareStatement(query);
      
      pst.setInt(1, r.getID());
      pst.setInt(2,r.getClient1().getId());// Binding du premier valeur mentionner dans le query "?" 
      pst.setInt(3,r.getClient2().getId());
      pst.setInt(4,r.getPoints_relation());
      pst.setString(5,r.getNiveau());
      pst.setDate(6,r.getDate_debut());
      pst.setDate(7,r.getDate_fin());
      
      return pst.executeUpdate();

    }

    @Override
    public int update(Relation r) throws SQLException {

        String query ="update Relation set client1=?,client2=?,point_relation=?,niveau=?,date_debut=?,date_fin=? where ID=? ";
        PreparedStatement pst=DB.prepareStatement(query);
        
        pst.setInt(1,r.getClient1().getId());// Binding du premier valeur mentionner dans le query "?" 
        pst.setInt(2,r.getClient2().getId());
        pst.setInt(3,r.getPoints_relation());
        pst.setString(4,r.getNiveau());
        pst.setDate(5,r.getDate_debut());
        pst.setDate(6,r.getDate_fin());
        
        pst.setInt(7, r.getID());
        /*pst.setInt(8,r.getClient1().getID());
        pst.setInt(9,r.getClient2().getID());*/
        

        

        return pst.executeUpdate();

      
    }

    @Override
    public int remove(Relation r) throws SQLException {
        
    String query=" delete from Relation where ID=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    
    pst.setInt(1,r.getID());
    
    return pst.executeUpdate();
    }

    @Override
    public List<Relation> fetchAll() throws SQLException {
String query=" select *  from  Relation "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Relation>Relations = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          GestionnaireUser g=new GestionnaireUser();
         // List<User> Clients = g.
          while(res.next())// parcour du result set
          {
             int Client1_ID=res.getInt("client1");
             int Client2_ID=res.getInt("client2");

              Utilisateur client1=g.fetchOneById(Client1_ID);
              Utilisateur client2=g.fetchOneById(Client2_ID);

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

    
    public boolean ClientValide(Utilisateur c) throws SQLException
    {
        List<Relation> Relations=(List<Relation>) fetchAll();
        if (Relations.stream().anyMatch(r->r.getClient1().equals(c) || r.getClient2().equals(c))){
            return true;
        }
        return false;
    }
    public String NbPoint() throws SQLException
    {   
         List<Relation> Relations=(List<Relation>) fetchAll();
       return Integer.toString(Relations.stream().filter(r->r.getClient1().getId()==MySoulMate.getLogged_in_Client().getId() || 
             r.getClient2().getId()==MySoulMate.getLogged_in_Client().getId()).mapToInt(r->r.getPoints_relation()).findFirst().getAsInt());
               /* Relations.stream().filter(r->r.getClient1().getID()==MySoulMate.getLogged_in_Client().getID() || 
             r.getClient2().getID()==MySoulMate.getLogged_in_Client().getID()).mapToInt(x->x.getPoints_relation()).findFirst().getAsInt()+"";*/
    }
    public String Niveau() throws SQLException
    {   
         List<Relation> Relations=(List<Relation>) fetchAll();
       return Relations.stream().filter(r->r.getClient1().equals(MySoulMate.getLogged_in_Client()) || 
               r.getClient2().equals(MySoulMate.getLogged_in_Client())).map(r->r.getNiveau()).findFirst().get()+"";
             
    }
    public String Temps() throws SQLException
    {   //ChronolicalDate d = new ChronolicalDate(); 
         List<Relation> Relations=(List<Relation>) fetchAll();
        // LocalDate n = new LocalDate();
         //n.until(d);
         
       return Relations.stream().filter(r->r.getClient1().equals(MySoulMate.getLogged_in_Client()) || 
               r.getClient2().equals(MySoulMate.getLogged_in_Client())).map(r->r.getDate_fin().getTime()-r.getDate_debut().getTime()).findFirst().get()+"";
             
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

    @Override
    public Relation fetchOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Relation fetchOnByCriteria(Map<String, String> Criteras) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
