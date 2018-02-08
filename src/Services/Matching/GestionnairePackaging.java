/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Matching;
import Entites.Matching.Packaging;
import Services.Gestionnaire;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nadia
 */
public class GestionnairePackaging implements Gestionnaire {
    
     @Override
    public int create(Object o) throws SQLException {
       Packaging p=(Packaging)o;// down Cast
   
        String query=" insert into packaging (nom,contenu,duree,prix) values (?,?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setString(1,p.getNom() );// Binding de la première valeur mentionnée dans le query "?" 
         pst.setString(2, p.getContenu() );//Binding de la deuxieme valeur mentionnée dans le query "?" 
         pst.setInt(3, p.getDuree() );
         pst.setFloat(4, p.getPrix() );
         
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int update(Object o) throws SQLException {
       Packaging p=(Packaging)o;
       String query="update packaging set nom=?, contenu=?, duree=?, prix=? where ID=?";
       PreparedStatement pst=DB.prepareStatement(query);
       pst.setString(1, p.getNom());
       pst.setString(2,p.getContenu());
       pst.setInt(3, p.getDuree());
       pst.setFloat(4, p.getPrix());
       
        return pst.executeUpdate();    }

    @Override
    public int remove(Object o) throws SQLException {
        Packaging p=(Packaging)o;
        String query="delete  from packaging where ID=? ";  
        PreparedStatement pst=DB.prepareStatement(query);
        pst.setInt(1,p.getID());
        return pst.executeUpdate();    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {       
        String query="select * from packaging ";
        PreparedStatement pst=DB.prepareStatement(query);
        ResultSet res=pst.executeQuery();
        List<Packaging>Packagings= new ArrayList<>();
        while(res.next()) 
        {
          Packagings.add(new Packaging(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getFloat(5)));
        }
        return Packagings;
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
