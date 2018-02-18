/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.User;

import Entites.AbstractEntite;
import Entites.Profil.Profil;
import Entites.User.Client;
import Entites.User.Reclamation;
import Services.Gestionnaire;
import static Services.Gestionnaire.DB;
import Services.GestionnaireAbstractEntite;
import Services.Profil.GestionnaireProfil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ransom
 */
public class GestionnaireClient extends GestionnaireAbstractEntite implements Gestionnaire {

    @Override
    public int create(Object o) throws SQLException {
      super.create(o);
    
      Client c=(Client)o;
      String query="insert into Client(Entite,prenom,motdepasse,email,date_naissane,pseudo) values(?,?,?,?,?,?)";
      PreparedStatement pst= DB.prepareStatement(query);
      GestionnaireAbstractEntite g= new GestionnaireAbstractEntite() {};
      List<AbstractEntite>Entities =(List<AbstractEntite>) g.fetchAll();
      int last_Added_ID=Entities.stream().mapToInt(x->x.getID()).max().getAsInt();
      pst.setInt(1,last_Added_ID);
      pst.setString(2,c.getPrenom());
      pst.setString(3,c.getMotdepasse());
      pst.setString(4,c.getEmail());
      pst.setDate(5,c.getDate_naissance());
      pst.setString(6,c.getPseudo());
      
      return pst.executeUpdate();
      
    }

    @Override
    public int update(Object o) throws SQLException {
        
      super.update(o);
      Client c=(Client)o;
      String query ="update Client set Entite=?,prenom=?,motdepasse=?,email=?,date_naissance=?,pseudo=? where Entite=?";
      
      PreparedStatement pst=DB.prepareStatement(query);
      
      pst.setInt(1, c.getID());
      pst.setString(2,c.getPrenom());
      pst.setString(3,c.getMotdepasse());
      pst.setString(4,c.getEmail());
      pst.setDate(5,c.getDate_naissance());
      pst.setString(6,c.getPseudo());
      pst.setInt(7, c.getID());
      
      return pst.executeUpdate();
    }

    @Override
    public int remove(Object o) throws SQLException {
  
    Client c=(Client)o;
    String query=" delete from Client where Entite=? ";
    
    PreparedStatement pst=DB.prepareStatement(query);
    
    pst.setInt(1,c.getID());
    
    return  pst.executeUpdate()+super.remove(o);
    
    }

    @Override
    public List<? extends Object> fetchAll() throws SQLException {
           String query=" select Entite.nom as nom,Client.*  from  Client inner join Entite on Client.Entite=Entite.ID  "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Client>Clients = new ArrayList<>();//  Creation du List Reclamation
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
              GestionnaireProfil gp = new GestionnaireProfil();
             int Id_profil=res.getInt(8);
             Profil profil= ((List<Profil>)gp.fetchAll()).stream().filter(x->x.getId()==Id_profil).findFirst().get();
             Clients.add(new Client(res.getInt("entite"),res.getString("nom"),res.getString(3),res.getString(4),res.getString(5),res.getDate(6),res.getString(7),profil));
           }
          return Clients;
    }
      public List<? extends Object> fetchAll(String aux, String target_column ,int StartPoint,int BreakPoint) throws SQLException {
          String query=" select Entite.nom as nom,Client.*  from  Client inner join Entite on Client.Entite=Entite.ID  "
                  + " where ( "+target_column+" like ?  )  "
                  + " limit  "+StartPoint+","+BreakPoint+" "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Client>Clients = new ArrayList<>();//  Creation du List Reclamation
           pst.setString(1, "%"+aux+"%");
         
     
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
                Clients.add(new Client(res.getInt("entite"),res.getString("nom"),res.getString(3),res.getString(4),res.getString(5),res.getDate(6),res.getString(7)));
           }
          return Clients;
    }
           public List<? extends Object> fetchAll(String aux,int StartPoint,int BreakPoint) throws SQLException {
          String query="  select Entite.nom as nom,Client.*  from  Client inner join Entite on Client.Entite=Entite.ID "
                  + "where ( nom like ? or prenom like ? or pseudo like ?  or ID like ?  or email like ? )"
                  + " limit  "+StartPoint+","+BreakPoint+" "    ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Client>Clients = new ArrayList<>();//  Creation du List Reclamation
           pst.setString(1, "%"+aux+"%");
           pst.setString(2, "%"+aux+"%");
           pst.setString(3, "%"+aux+"%");
           pst.setString(4, "%"+aux+"%");
            pst.setString(5, "%"+aux+"%");
     
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
           Clients.add(new Client(res.getInt("entite"),res.getString("nom"),res.getString(3),res.getString(4),res.getString(5),res.getDate(6),res.getString(7)));
           }
          return Clients;
    }
    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy) throws SQLException {
      String query="  select Entite.nom as nom,Client.*  from  Client inner join Entite on Client.Entite=Entite.ID "
                  + "where ( nom like ? or prenom like ? or pseudo like ?  or ID like ?  or email like ? )"  ; // preparation du requete sql
          PreparedStatement pst=DB.prepareStatement(query);// Preparation du requete et  recuperation de l'objet Prepared statment
          List<Client>Clients = new ArrayList<>();//  Creation du List Reclamation
           pst.setString(1, "%"+aux+"%");
           pst.setString(2, "%"+aux+"%");
           pst.setString(3, "%"+aux+"%");
           pst.setString(4, "%"+aux+"%");
            pst.setString(5, "%"+aux+"%");
     
          ResultSet res = pst.executeQuery();// execution du query et recuperation du result set
          while(res.next())// parcour du result set
          {
           Clients.add(new Client(res.getInt("entite"),res.getString("nom"),res.getString(3),res.getString(4),res.getString(5),res.getDate(6),res.getString(7)));
           }
          return Clients;
    }

    @Override
    public List<? extends Object> fetchAll(String aux, int target_column, String OrderBy, int startPoint, int breakPoint) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
