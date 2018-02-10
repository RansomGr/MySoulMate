/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Plan;

import Entites.Plan.Plan;
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
 * @author irou
 */

    public class GestionnairePlan extends GestionnaireAbstractEntite implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
      super.create(o);
    
      Plan p=(Plan)o;
      String query="insert into Plan(Entite,type,email,siteweb,telephone,description,photo) values(?,?,?,?,?,?,?)";
      PreparedStatement pst= DB.prepareStatement(query);
     pst.setInt(1, p.getID());
      pst.setString(2,Plan.Type.getAsString(p.getType()));

      pst.setString(3,p.getEmail());
      pst.setString(4,p.getSiteweb());
      pst.setInt(5,p.getTelephone());
      pst.setString(6,p.getDescription());
      pst.setString(7,p.getPhoto());
      

     
      
      return pst.executeUpdate();
      
    }

    @Override
    public int update(Object o) throws SQLException {
        
      super.update(o);
      Plan p=(Plan)o;
      String query ="update Plan" + " set Entite=?,type=?,email=?,siteweb=?,telephone=?,description=?,photo=?  where Entite=?";
      
      PreparedStatement pst=DB.prepareStatement(query);
      
      pst.setInt(1, p.getID());
      pst.setString(2,Plan.Type.getAsString(p.getType()));
      
      pst.setString(3,p.getEmail());
      pst.setString(4,p.getSiteweb());
      pst.setInt(5,p.getTelephone());
      pst.setString(6,p.getDescription());
      pst.setString(7,p.getPhoto());
      
      pst.setInt(8, p.getID());
      
      return pst.executeUpdate();
    }

    @Override
    public int remove(Object o) throws SQLException {
    super.remove(o);
    Plan p=(Plan)o;
    String query=" delete from Plan where Entite=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    
    pst.setInt(1,p.getID());
    
    return pst.executeUpdate();
    //l
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
           String query=" select Entite.nom as nom,Plan.*  from  Plan inner join Entite on Plan.Entite=Entite.ID "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Plan>Plans = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
              Plans.add(new Plan(res.getInt("entite"),res.getString("nom"),Plan.Type.getAsType(res.getString(1)),res.getString(2),res.getString(3),res.getInt(4),res.getString(5),res.getString(6)));
             //Type type, String email, String siteweb, int telephone, String description, String photo, int ID, String nom
           }
          return Plans;
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

    

