/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Profil;

import Entites.AbstractEntite;
import Entites.Events.Events;
import Entites.Plan.Plan;
import Entites.User.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_SearchResults_FOController implements Initializable {

    @FXML
    private VBox list_vb;
    /*non fxml vars */
    private List<Utilisateur>Items_found;


    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
        public List<Utilisateur> getItems_found() {
        return Items_found;
    }
    private void load_data()
    {
       int i =0;
       HBox list_hb = new HBox();
        this.Items_found.forEach(x->{
            
        if(x instanceof Utilisateur)
        {
            
            try {
                Utilisateur c =(Utilisateur)x ;
                FXMLLoader fxml  = new FXMLLoader(getClass().getResource("/VIEWS/Profil/ui_Profil_Single_FO.fxml"));
                Node root =fxml.load();
                list_hb.getChildren().add(root);
                        } catch (IOException ex) {
                Logger.getLogger(Ui_SearchResults_FOController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      //  else if(x instanceof Plan)
   /*     {
            
        }
     //   else if(x instanceof Events)
        {
        
        }*/
        if(i==3)
        list_vb.getChildren().add(list_hb);
        
        } );
    }
    public void setItems_found(List<Utilisateur> Items_found) {
        this.Items_found = Items_found;
        
    }
}
