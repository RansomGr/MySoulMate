/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Plan;

import Entites.Plan.Avis;
import Entites.Plan.Plan;
import Entites.User.Utilisateur;
import Services.Gestionnaire;
import static Services.Gestionnaire.DB;
import Services.Plan.GestionnairePlan;
import Services.User.GestionnaireClient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author irou
 */
public class GestionnaireAvis implements Gestionnaire {
    
    
    
   @Override
    public int create(Object o) throws SQLException {
     
    
      Avis a=(Avis)o;
      String query="insert into Avis(Plan,Client,commentaire,note) values(?,?,?,?)";
      PreparedStatement pst= DB.prepareStatement(query);
      
      pst.setInt(1,a.getPlan().getId());
      pst.setInt(2,a.getClient().getId());
      pst.setString(3,a.getCommentaire());
      pst.setFloat(4,a.getNote());
    
    
      
      return pst.executeUpdate();
      
    }

    @Override
    public int update(Object o) throws SQLException {
        
      
      Avis a=(Avis)o;
      String query ="update Avis"
              + " Plan=?,Client=?,commentaire=?,note=?  where id=? ";
      
      PreparedStatement pst=DB.prepareStatement(query);
      pst.setInt(1,a.getPlan().getId());
      pst.setInt(2,a.getClient().getId());
      pst.setString(3,a.getCommentaire());
      pst.setFloat(4,a.getNote());
    
      
     
      return pst.executeUpdate();
    }

    @Override
    public int remove(Object o) throws SQLException {
   
    Avis a=(Avis)o;
    String query=" delete from Avis where id=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    
    pst.setInt(1,a.getId());
    
    
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
           List<Utilisateur> Clients=(List<Utilisateur>) g.fetchAll();
         List<Plan> Plans=(List<Plan>) a.fetchAll();
          while(res.next())// parcour du result set
          {
             int Client1_ID=res.getInt("client1");
             int Plan1_ID=res.getInt("Plan1");
    
             Utilisateur client1=Clients.stream().filter(c->c.getId()==Client1_ID).findFirst().get();
      Plan Plan1= Plans.stream().filter(c->c.getId()==Plan1_ID).findFirst().get();

           Avises.add(new Avis(
                   
                Plan1,
                client1,
               res.getString("commentaire"),
            res.getFloat("Note"),
                    
           )
           );
           }
          return Avises;    
    } 
    

//    
//    public List<Avis> fetchall(Plan p) throws SQLException{
//        String query=" select Avis.*,Entite.nom,Client.prenom from avis "
//                + " inner join Client on Client.Entite=Avis.Client "
//                + " inner join Entite on Entite.ID=Client.Entite "
//                + "  where Plan=? "    ; // preparation du requete sql
//          PreparedStatement pst=DB.prepareStatement(query);
//          pst.setInt(1,p.getID());
//          ResultSet res = pst.executeQuery();
//          List<Avis>ListAvises = new ArrayList<>();
//           GestionnaireAvis a =new GestionnaireAvis();
//           GestionnaireClient gp=new GestionnaireClient();
//           List<Client>Clients =(List<Client>) gp.fetchAll();
//           
//           while(res.next()){
//               int Client1_ID=res.getInt("client");
//                Client client1=Clients.stream().filter(c->c.getID()==Client1_ID).findFirst().get();
//    
//             
//                ListAvises.add(new Avis(
//                   
//                p,
//                client1,
//               res.getString("commentaire"),
//            res.getFloat("Note"),
//                    res.getDate("dateh")
//                                     )
//           );
//           }
//          return ListAvises;    
//
//     
//     
//         
//           
//           }

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
    

