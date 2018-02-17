/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Plan;


import Entites.Plan.Plan;
import Entites.Plan.Reservation;
import Entites.User.Client;
import Services.Gestionnaire;
import static Services.Gestionnaire.DB;
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
public class GestionnaireReservation implements Gestionnaire{

    @Override
    public int create(Object o) throws SQLException {
       
     
    
      Reservation R1=(Reservation)o;
      String query="insert into reservation(ID,Plan,Client,date_res,nb_place) values(?,?,?,?,?)";
      PreparedStatement pst= DB.prepareStatement(query);
       pst.setInt(1,R1.getID());
      pst.setInt(2,R1.getPlan().getID());
      pst.setInt(3,R1.getClient().getID());
      pst.setDate(4,R1.getDate_res());
      pst.setInt(5,R1.getNb_place());
      
    
      
      return pst.executeUpdate();
      //To change body of generated methods, choose Tools | Templates.
    }

   
    @Override
    public int update(Object o) throws SQLException {
        
     //public Reservation(int ID,Plan plan, Client client, Date date_res, int nb_place)
      Reservation R1=(Reservation)o;
      String query ="update reservation" + " set ID=?,Plan=?,Client=?,date_res=?,nb_place=?  where ID=?";
      
      PreparedStatement pst= DB.prepareStatement(query);
      pst.setInt(1, R1.getID());

      pst.setInt(2,R1.getPlan().getID());
      pst.setInt(3,R1.getClient().getID());
      pst.setDate(4,R1.getDate_res());
      pst.setInt(5,R1.getNb_place());
      
      pst.setInt(6, R1.getID());
      
      return pst.executeUpdate();
    } //To change body of generated methods, choose Tools | Templates.

    @Override
    public int remove(Object o) throws SQLException {
         Reservation R1=(Reservation)o;

    String query=" delete from reservation where ID=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    
    pst.setInt(1,R1.getID());
    
    return pst.executeUpdate();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
        String query=" select *  from  reservation"    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Reservation> listR = new ArrayList<>();//  Creation du List Reclamation
          //List<Plan>Plans = new ArrayList<>();
          
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          GestionnaireReservation R =new GestionnaireReservation();
          GestionnaireClient g=new GestionnaireClient();
          GestionnairePlan p=new GestionnairePlan();
           List<Client> Clients=(List<Client>) g.fetchAll();
         List<Plan> Plans=(List<Plan>) R.fetchAll();
          while(res.next())// parcour du result set
          {
             int Client1_ID=res.getInt("client1");
             int Plan1_ID=res.getInt("Plan1");
    
             Client client1=Clients.stream().filter(c->c.getID()==Client1_ID).findFirst().get();
      Plan Plan1= Plans.stream().filter(c->c.getID()==Plan1_ID).findFirst().get();

           listR.add(new Reservation(
                  
                Plan1,
                   client1,
                  res.getDate("date_res"),
                   res.getInt("nb_place")
                                     )
           );
           }
          return listR;
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
