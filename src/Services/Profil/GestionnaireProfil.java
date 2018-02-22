/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Profil;

import Entites.Profil.Caracteristique;
import Entites.Profil.Profil;
import Services.Gestionnaire;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mysoulmate.MySoulMate;

/**
 *
 * @author Sofiene
 */
public class GestionnaireProfil implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
       Profil a=(Profil)o;// down Cast
   
        String query=" insert into Profil (caracteristique,photo,description) values (?,?,?) "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         GestionnaireCaracteristique car= new GestionnaireCaracteristique();
         car.create(a.getCaracteristique());
        
         System.out.println("carac id :"+((List<Caracteristique>)car.fetchAll()).stream().mapToInt(x->x.getID()).max().getAsInt());
         System.out.println("client id :"+MySoulMate.getLogged_in_Client().getID());
         a.getCaracteristique().setID(((List<Caracteristique>)car.fetchAll()).stream().mapToInt(x->x.getID()).max().getAsInt());
         pst.setInt(1,a.getCaracteristique().getID());// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2,a.getPhoto());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(3,a.getDescription());

    

       
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }    

    @Override
    public int update(Object o) throws SQLException {
     Profil a = (Profil)o;// down Cast du Object => Admin 
        String query=" update  Profil set caracteristique=?,photo=?,description=?,preference=? where id=?  "; // preparation du query

         PreparedStatement pst=DB.prepareStatement(query);// Recuperation de l'objet PreparedStatment
         
         pst.setInt(1,a.getCaracteristique().getID() );// Binding du premier valeur mentionner dans le query "?" 
         pst.setString(2,a.getPhoto());//Binding du deuxieme valeur mentionner dans le query "?" 
         pst.setString(3,a.getDescription());
         pst.setInt(4, a.getPreference().getID());//Binding du valeur de l'id mentionné dans le query "?" 
         return pst.executeUpdate(); // Execution et retour du resultat du query 
    }

    @Override
    public int remove(Object o) throws SQLException {
 Profil a=(Profil)o;
        
        String query="delete  from Profil where ID=? ";
        
        PreparedStatement pst=DB.prepareStatement(query);
        
        pst.setInt(1,a.getId());
        
        return pst.executeUpdate();    }

    @Override
   
     public List<? extends Object> fetchAll() throws SQLException {
        
          String query=" select id,caracteristique,photo,description,preference from profil "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Profil>Profils = new ArrayList<>();//  Creation du List Reclamation
          //Caracteristique c = new Caracteristique(0, query, query, query, query, query, query, 0, query, query, query, query, query)
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          GestionnaireCaracteristique G= new GestionnaireCaracteristique();
          while(res.next())// parcour du result set
          { 
              Caracteristique carac=null;
              Caracteristique pref=null;
              int id_char=res.getInt(2);
             int id_pref=res.getInt(5);

           carac =((List<Caracteristique>)G.fetchAll()).stream().filter(x->x.getID()==id_char).findFirst().get();
          if(id_pref!=0)
           pref =((List<Caracteristique>)G.fetchAll()).stream().filter(x->x.getID()==id_pref).findFirst().get();

             Profils.add(new Profil(res.getInt(1),carac,res.getString(3),res.getString(4),pref) );
           }
          return Profils;
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
