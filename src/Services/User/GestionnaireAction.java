/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Done
package Services.User;
import Entites.User.Action;
import Services.Gestionnaire;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ransom
 */
public class GestionnaireAction implements Gestionnaire{

    @Override
    public int create(Object o) throws SQLException {
       Action a=(Action)o;// down Cast
   
        String query=" insert into Action (numero,type) values (?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1,a.getNumero());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2, a.getType());//Binding du deuxieme valeur mentionner dans le query "?" 
       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int update(Object o) throws SQLException {
       Action a=(Action)o;
       String query="update Action set ID=?,numero=?,type=? where ID=?";
       PreparedStatement pst=DB.prepareStatement(query);
       pst.setInt(1, a.getID());
       pst.setString(2,a.getType());
       pst.setInt(3, a.getNumero());
       pst.setInt(4, a.getID());
       
        return pst.executeUpdate();
    }

    @Override
    public int remove(Object o) throws SQLException {
        Action a=(Action)o;
        
        String query="delete  from Action where ID=? ";
        
        PreparedStatement pst=DB.prepareStatement(query);
        
        pst.setInt(1,a.getID());
        
        return pst.executeUpdate();
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
        String query="select * from Action ";
        PreparedStatement pst=DB.prepareStatement(query);
        ResultSet res=pst.executeQuery();
        List<Action>Actions= new ArrayList<>();
        while(res.next()) 
        {
          Actions.add(new Action(res.getInt(1),res.getInt(2),res.getString(3)));
        }
        return Actions;
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
