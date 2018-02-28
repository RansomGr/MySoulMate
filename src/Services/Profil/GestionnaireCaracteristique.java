/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Profil;
import Entites.Profil.Caracteristique;
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

public class GestionnaireCaracteristique implements Gestionnaire{
 @Override
    public int create(Object o) throws SQLException {
        
        Caracteristique c =(Caracteristique)o;// down Cast
        String query=" insert into Caracteristique (corpulence,pilosite,origine,profession,alcool,tabac,taille,cheveux,yeux ,caractere, statut ,cuisine) values (?,?,?,?,?,?,?,?,?,?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setString(1,c.getCorpulence());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2,c.getPilosite());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(3,c.getOrigine());
         pst.setString(4,c.getProfession());
         pst.setString(5,c.getAlcool());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(6,c.getTabac());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(7,Double.toString(c.getTaille()));//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(8,c.getCheveux());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(9,c.getYeux());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(10,c.getCaractere());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(11,c.getStatut());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(12,c.getCuisine());//Binding du deuxieme valeur mentionner dans le query "?" 

       
         return pst.executeUpdate();} // Execution et retour du resultat du query     }

    @Override
    public int update(Object o) throws SQLException {
    Caracteristique c = (Caracteristique)o;// down Cast du Object => Admin 
    String query=" update  Caracteristique set corpulence=?,pilosite=?,origine=?,profession=?,alcool=?,tabac=?,taille=?,cheveux=?,yeux=? ,caractere=?, statut=? ,cuisine=? where id=?  "; // preparation du query
    PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setString(1,c.getCorpulence());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2,c.getPilosite());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(3,c.getOrigine());
         pst.setString(4,c.getProfession());
         pst.setString(5,c.getAlcool());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(6,c.getTabac());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(7,Double.toString(c.getTaille()));//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(8,c.getCheveux());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(9,c.getYeux());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(10,c.getCaractere());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(11,c.getStatut());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(12,c.getCuisine());//Binding du deuxieme valeur mentionner dans le query "?" 

       
         return pst.executeUpdate();} // Execution et retour du resultat du query     }


    @Override
    public int remove(Object o) throws SQLException {
    
    Caracteristique c=(Caracteristique)o;
    String query="delete  from caracteristique where ID=? ";
        
    PreparedStatement pst=DB.prepareStatement(query);
        
    pst.setInt(1,c.getID());
        
    return pst.executeUpdate();  
    }

    @Override
     public List<? extends Object> fetchAll() throws SQLException {
        String query="select * from caracteristique ";
        PreparedStatement pst=DB.prepareStatement(query);
        ResultSet res=pst.executeQuery();
        List<Caracteristique>Caracteristiques =new ArrayList<>();
        
        
        while(res.next()) 
        {
         Caracteristiques.add(new Caracteristique(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),Float.parseFloat(res.getString(8)),res.getString(9),res.getString(10),res.getString(11),res.getString(12),res.getString(13)));
        }
        return Caracteristiques;
    }


    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public Caracteristique fetchOneById(int id) throws SQLException
    {
          Caracteristique c ;
          String query="select * from caracteristique where id=? ";
          PreparedStatement pst=DB.prepareStatement(query);
          pst.setInt(1, id);
          ResultSet res=pst.executeQuery();
           res.next();
           c =new Caracteristique(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),Float.parseFloat(res.getString(8)),res.getString(9),res.getString(10),res.getString(11),res.getString(12),res.getString(13));

          return c;
    }
    

   
}
