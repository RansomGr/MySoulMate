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
import java.util.Map;

/**
 *
 * @author Nadia
 */
public class GestionnairePackaging implements Gestionnaire <Packaging>{
    
     @Override
    public int create(Packaging p) throws SQLException {
    
   
        String query=" insert into packaging (nom,contenu,duree,prix) values (?,?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setString(1,p.getNom() );// Binding de la première valeur mentionnée dans le query "?" 
         pst.setString(2, p.getContenu() );//Binding de la deuxieme valeur mentionnée dans le query "?" 
         pst.setInt(3, p.getDuree() );
         pst.setString(4, p.getPrix() );
         
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int update(Packaging p) throws SQLException {
       
       String query="update packaging set nom=?, contenu=?, duree=?, prix=? where ID=?";
       PreparedStatement pst=DB.prepareStatement(query);
       pst.setString(1, p.getNom());
       pst.setString(2,p.getContenu());
       pst.setInt(3, p.getDuree());
       pst.setString(4, p.getPrix());
       pst.setInt(5, p.getID());
       System.out.println("ID="+p.getID());
        return pst.executeUpdate();    }

    @Override
    public int remove(Packaging p) throws SQLException {
        
        String query="delete  from packaging where ID=? ";  
        PreparedStatement pst=DB.prepareStatement(query);
        pst.setInt(1,p.getID());
        return pst.executeUpdate();    }

    @Override
    public List<Packaging> fetchAll() throws SQLException {       
        String query="select * from packaging ";
        PreparedStatement pst=DB.prepareStatement(query);
        ResultSet res=pst.executeQuery();
        List<Packaging>Packagings= new ArrayList<>();
        while(res.next()) 
        {
          Packagings.add(new Packaging(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getString(5)));
        }
        return Packagings;
    }

    
    public List<Packaging> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
          String query=" select * from Packaging where ( nom like ? or contenu like ? or duree like ? or prix like ? or ID like ? ) "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Packaging>Packagings = new ArrayList<>();//  Creation du List Reclamation
           pst.setString(1, "%"+aux+"%");
           pst.setString(2, "%"+aux+"%");
           pst.setString(3, "%"+aux+"%");
           pst.setString(4, "%"+aux+"%");
            pst.setString(5, "%"+aux+"%");
     
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
             Packagings.add( new Packaging(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getString(5)) );
           }
          return Packagings;
    }
      public List<? extends Object> fetchAll(String aux, String target_column ,int StartPoint,int BreakPoint) throws SQLException {
          String query=" select * from Packaging "
                  + " where ( "+target_column+" like ?  )  "
                  + " limit  "+StartPoint+","+BreakPoint+" "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Packaging>Packagings = new ArrayList<>();//  Creation du List Reclamation
           pst.setString(1, "%"+aux+"%");
         
     
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
             Packagings.add( new Packaging(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getString(5)) );
           }
          return Packagings;
    }
            public List<? extends Object> fetchAll(String aux,int StartPoint,int BreakPoint) throws SQLException {
          String query=" select * from Packaging "
                  + "where ( nom like ? or contenu like ? or duree like ? or prix like ? or ID like ? )"
                  + " limit  "+StartPoint+","+BreakPoint+" "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Packaging>Packagings = new ArrayList<>();//  Creation du List Reclamation
           pst.setString(1, "%"+aux+"%");
          pst.setString(2, "%"+aux+"%");
           pst.setString(3, "%"+aux+"%");
           pst.setString(4, "%"+aux+"%");
            pst.setString(5, "%"+aux+"%");
     
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
             Packagings.add( new Packaging(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getString(5))  );
           }
          return Packagings;
    }

    public List<Packaging> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Packaging fetchOneById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Packaging fetchOnByCriteria(Map<String, String> Criteras) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Packaging> fetchSomeBy(String aux, String target_column, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Packaging> fetchSomeBy(String aux, int target_column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Packaging> fetchSomeBy(String aux, int StartPoint, int BreakPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      

}
