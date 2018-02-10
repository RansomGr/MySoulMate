/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Avis;

import Entites.Plan.Avis;
import Entites.Plan.Plan;
import Entites.User.Client;
import Services.Gestionnaire;
import static Services.Gestionnaire.DB;
import Services.GestionnaireAbstractEntite;
import Services.Plan.GestionnairePlan;
import Services.User.GestionnaireClient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author irou
 */
public class GestionnaireAvis implements Gestionnaire {
    
    
    
   @Override
    public int create(Object o) throws SQLException {
     
    
      Avis a=(Avis)o;
      String query="insert into Avis(Plan,Client,commentaire,note,dateh) values(?,?,?,?,?)";
      PreparedStatement pst= DB.prepareStatement(query);
      pst.setInt(1,a.getPlan().getID());
      pst.setInt(2,a.getClient().getID());
      pst.setString(3,a.getCommentaire());
      pst.setString(4,a.getNote());
      pst.setDate(5,a.getDateh());
    
      
      return pst.executeUpdate();
      
    }

    @Override
    public int update(Object o) throws SQLException {
        
      
      Avis a=(Avis)o;
      String query ="update Avis"
              + " set Plan=?,Client=?,commentaire=?,note=?,dateh=?  where Client=? and Plan=?";
      
      PreparedStatement pst=DB.prepareStatement(query);
       pst.setInt(1,a.getPlan().getID());
      pst.setInt(2,a.getClient().getID());
      pst.setString(3,a.getCommentaire());
      pst.setString(4,a.getNote());
      pst.setDate(5,a.getDateh());
      
     
      return pst.executeUpdate();
    }

    @Override
    public int remove(Object o) throws SQLException {
   
    Avis a=(Avis)o;
    String query=" delete from Avis where Client=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    
    //pst.setInt(1,a.getID());
    
    return pst.executeUpdate();
    
    } 
    
    
    

    @Override
    public List<? extends Object> fetchAll() throws SQLException {//To change body of generated methods, choose Tools | Templates.
        String query=" select *  from  Avis "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Avis>Avises = new ArrayList<>();//  Creation du List Reclamation
          //List<Plan>Plans = new ArrayList<>();
          
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          GestionnaireAvis a =new GestionnaireAvis();
          GestionnaireClient g=new GestionnaireClient();
          GestionnairePlan p=new GestionnairePlan();
           List<Client> Clients=(List<Client>) g.fetchAll();
         List<Plan> Plans=(List<Plan>) a.fetchAll();
          while(res.next())// parcour du result set
          {
             int Client1_ID=res.getInt("client1");
             int Plan1_ID=res.getInt("Plan1");
    
             Client client1=Clients.stream().filter(c->c.getID()==Client1_ID).findFirst().get();
      Plan Plan1= Plans.stream().filter(c->c.getID()==Plan1_ID).findFirst().get();

           Avises.add(new Avis(
                   
                Plan1,
                client1,
               res.getString("commentaire"),
            res.getString("Note"),
                    res.getDate("dateh")
                                     )
           );
           }
          return Avises;    
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
