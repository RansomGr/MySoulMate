/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Plan;

import Entites.AbstractEntite;
import Entites.Plan.Plan;
import Services.Gestionnaire;
import static Services.Gestionnaire.DB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author irou
 */
public class GestionnairePlan implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
        Plan p = (Plan) o;
        String query = "insert into Plan(type,email,siteweb,photo,description,telephone,nom) values(?,?,?,?,?,?,?)"; // preparation du query

        PreparedStatement pst = DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment

        pst.setString(1, (p.getType()));

        pst.setString(2, p.getEmail());
        pst.setString(3, p.getSiteweb());
        pst.setString(4, p.getPhoto());
        pst.setString(5, p.getDescription());
        pst.setInt(6, p.getTelephone());

        pst.setString(7, p.getNom());

//      pst.setString(8,p.getPhoto1());
//      pst.setString(9,p.getPhoto2());
//          pst.setFloat(10,p.getX());
//          pst.setFloat(11,p.getY());
        return pst.executeUpdate(); // Execution et retour du resultat du query 

//      GestionnairePlan ge = new GestionnairePlan() {};
//      List<AbstractEntite> L= (List<AbstractEntite>) ge.fetchAll();
//      int id=L.stream().mapToInt(x->x.getID()).max().getAsInt();
//      p.setID(id);
//      String query="insert into Plan(nom,type,email,siteweb,telephone,description,photo) values(?,?,?,?,?,?,?)";
//      PreparedStatement pst= DB.prepareStatement(query);
//     pst.setInt(1, p.getID());
//      pst.setString(2,Plan.Type.getAsString(p.getType()));
//
//      pst.setString(3,p.getEmail());
//      pst.setString(4,p.getSiteweb());
//      pst.setInt(5,p.getTelephone());
//      pst.setString(6,p.getDescription());
//      pst.setString(7,p.getPhoto());
//      
//
//     
//      
//      return pst.executeUpdate();
    }

    @Override
    public int update(Object o) throws SQLException {

        Plan p = (Plan) o;
        String query = "update Plan" + " type=?,email=?,siteweb=?,photo=?,description=?,telephone=?,nom=?  where id=?";

        PreparedStatement pst = DB.prepareStatement(query);

        pst.setString(1, (p.getType()));

        pst.setString(2, p.getEmail());
        pst.setString(3, p.getSiteweb());
        pst.setString(4, p.getPhoto());
        pst.setString(5, p.getDescription());
        pst.setInt(6, p.getTelephone());

        pst.setString(7, p.getNom());
//      pst.setString(8,p.getPhoto1());
//      pst.setString(9,p.getPhoto2());
//          pst.setFloat(10,p.getX());
//          pst.setFloat(11,p.getY());
//      
        return pst.executeUpdate();
    }

    @Override
    public int remove(Object o) throws SQLException {

        Plan p = (Plan) o;
        String query = " delete from Plan where Id=? ";

        PreparedStatement pst = DB.prepareStatement(query);

        pst.setInt(1, p.getId());

        return pst.executeUpdate();
        //l
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
        String query = " select *  from  Plan  "; // preparation du requete sql
        PreparedStatement pst = DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
        List<Plan> Plans = new ArrayList<>();//  Creation du List Reclamation

        ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
        while (res.next())// parcour du result set
        {
            Plans.add(new Plan(res.getInt("id"), res.getString("type"), res.getString("email"), res.getString("siteweb"), res.getString("photo"),res.getInt("telephone") ,res.getString("description"), res.getString("nom")));
            //Type type, String email, String siteweb, int telephone, String description, String photo, int ID, String nom
        }
        return Plans;
    }
   
    @Override
    public Plan fetchOneById(int id)  {
        Plan Plans = null;//  Creation du List Reclamation
        try {
            String query = " select *  from  Plan where Id=? "; // preparation du requete sql
            PreparedStatement pst = DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
            
            pst.setInt(1, id);
            ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
            
            if (res.next())// parcour du result set
            {
                Plans=new Plan(res.getInt("id"), res.getString("type"), res.getString("email"), res.getString("siteweb"), res.getString("photo"),res.getInt("telephone"), res.getString("description"),  res.getString("nom"));
                //Type type, String email, String siteweb, int telephone, String description, String photo, int ID, String nom
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GestionnairePlan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Plans;
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
