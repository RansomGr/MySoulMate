/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Evenement;

import Entites.Evenement.Evenement;
import Services.Gestionnaire;
import Services.GestionnaireAbstractEntite;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dellpro
 */
public class GestionnaireEvenement extends GestionnaireAbstractEntite implements Gestionnaire{

    @Override
    public int create(Object o) throws SQLException {
        super.create(o);
         Evenement evt=(Evenement)o;
      String query="insert into evenement(Entite,date_evenement,heure,type_e) values(?,?,?,?)";
      PreparedStatement pst= DB.prepareStatement(query);
      pst.setInt(1, evt.getID());
      pst.setDate(2,evt.getDate_evenement());
      pst.setDate(3,evt.getHeure());
      pst.setString(4,evt.getType_evenement());
      
      return pst.executeUpdate();
    }

    @Override
    public int update(Object o) throws SQLException {
        super.update(o);
      Evenement evt=(Evenement)o;
      String query ="update evenement set Entite=?,date_evenement=?,heure=?,Type_e=? where Entite=?";
      
      PreparedStatement pst=DB.prepareStatement(query);
      
      pst.setInt(1, evt.getID());
      pst.setDate(2,evt.getDate_evenement());
      pst.setDate(3,evt.getHeure());
      pst.setString(4,evt.getType_evenement());
      
      return pst.executeUpdate(); 
    }

    @Override
    public int remove(Object o) throws SQLException {
        super.remove(o);
    Evenement evt=(Evenement)o;
    String query=" delete from evenement where Entite=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    
    pst.setInt(1,evt.getID());
    
    return pst.executeUpdate();
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
        String query=" select Entite.nom as nom,Evenement.*  from  evenement inner join Entite on Evenement.Entite=Entite.ID "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Evenement>Evenements = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
             Evenements.add(new Evenement(res.getDate("date_evenement"),res.getDate(3),res.getString(4),res.getInt("entite"),res.getString("nom")));
           }
          return Evenements;
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
