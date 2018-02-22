/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Profil;

import Entites.User.Client;
import Services.Profil.GestionnaireProfil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Sofiene
 */
public class Ui_ajout_new_actualitéController implements Initializable {

    private Label user_lb;
    private static Client profile_owner;

    public static void setProfile_owner(Client profile_owner) {
        Ui_ajout_new_actualitéController.profile_owner = profile_owner;
    }
    private GestionnaireProfil Gp;
    @FXML
    private Label user;
    @FXML
    private ImageView image_view_actualite;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Gp= new GestionnaireProfil();
        init_data_actualite();
        // TODO
    }    
    private void init_data_actualite()
    {        
            user_lb.setText(profile_owner.getNom());
            image_view_actualite.setImage(new Image("/images/"+profile_owner.getProfil().getPhoto()));




    }
    @FXML
    private void Partages(ActionEvent event) {
    }
    
}
