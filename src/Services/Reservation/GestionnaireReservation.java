/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Reservation;


import Entites.Plan.Reservation;
import Services.Gestionnaire;
import static Services.Gestionnaire.DB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author irou
 */
public class GestionnaireReservation implements Gestionnaire{

    @Override
    public int create(Object o) throws SQLException {
       
     
    
      Reservation R1=(Reservation)o;
      String query="insert into Reservation(ID,Plan,Client,date_res,nb_place) values(?,?,?,?,?)";
      PreparedStatement pst= DB.prepareStatement(query);
       pst.setInt(1, R1.getID());
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
      String query ="update Reservation" + " set ID=?,Plan=?,Client=?,date_res=?,nb_place=?  where ID=?";
      
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

    String query=" delete from Reservation where ID=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    
    pst.setInt(1,R1.getID());
    
    return pst.executeUpdate();//To change body of generated methods, choose Tools | Templates.
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
