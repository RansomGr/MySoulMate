/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Profil;

import Entites.Profil.Caracteristique;
import Services.Gestionnaire;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Sofiene
 */
public class GestionnaireCaracteristique implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
Caracteristique a=(Caracteristique)o;// down Cast
   
        String query=" insert into Profil (corpulence,pilosite,origine,profession,alcool,tabac,taille,cheveux,yeux ,caractere, statut ,cuisine) values (?,?,?,?,?,?,?,?,?,?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setString(1,a.getCorpulence());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2,a.getPilosite());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(3,a.getOrigine());
         pst.setString(4,a.getProfession());
         pst.setString(5,a.getAlcool());//Binding du deuxieme valeur mentionner dans le query "?" 

         pst.setString(6,a.getTabac());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setFloat(7,a.getTaille());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(8,a.getCheveux());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(9,a.getYeux());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(10,a.getCaractere());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(11,a.getStatut());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(12,a.getCuisine());//Binding du deuxieme valeur mentionner dans le query "?" 

       
         return pst.executeUpdate();} // Execution et retour du resultat du query     }

    @Override
    public int update(Object o) throws SQLException {
Caracteristique a = (Caracteristique)o;// down Cast du Object => Admin 
        String query=" update  Caracteristique set corpulence=?,pilosite=?,origine=?,profession=?,alcool=?,tabac=?,taille=?,cheveux=?,yeux=? ,caractere=?, statut=? ,cuisine=? where id=?  "; // preparation du query
    PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setString(1,a.getCorpulence());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2,a.getPilosite());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(3,a.getOrigine());
         pst.setString(4,a.getProfession());
         pst.setString(5,a.getAlcool());//Binding du deuxieme valeur mentionner dans le query "?" 

         pst.setString(6,a.getTabac());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setFloat(7,a.getTaille());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(8,a.getCheveux());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(9,a.getYeux());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(10,a.getCaractere());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(11,a.getStatut());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(12,a.getCuisine());//Binding du deuxieme valeur mentionner dans le query "?" 

       
         return pst.executeUpdate();} // Execution et retour du resultat du query     }


    @Override
    public int remove(Object o) throws SQLException {
Caracteristique a=(Caracteristique)o;
        
        String query="delete  from caracteristique where ID=? ";
        
        PreparedStatement pst=DB.prepareStatement(query);
        
        pst.setInt(1,a.getID());
        
        return pst.executeUpdate();    }

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
