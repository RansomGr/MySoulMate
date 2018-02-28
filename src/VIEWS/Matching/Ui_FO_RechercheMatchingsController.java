/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Matching;

import Entites.Profil.Caracteristique;
import Entites.Profil.Profil;
import Entites.User.Client;
import VIEWS.Ui_MainFrame_FOController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class Ui_FO_RechercheMatchingsController implements Initializable {

    @FXML
    private Hyperlink achat_packaging_lien;
    @FXML
    private Button preferences_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Caracteristique caracteristique = MySoulMate.getLogged_in_Client().getProfil().getCaracteristique();
        
        
    }    

    @FXML
    private void load_achat_packaging(ActionEvent event) {
    }

    @FXML
    private void load_preferences_form(ActionEvent event) throws IOException {
      //  Ui_MainFrame_FOController.load_preference_page();
    }
    
}
