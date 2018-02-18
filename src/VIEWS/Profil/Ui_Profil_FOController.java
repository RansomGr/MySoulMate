/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Profil;

import Entites.User.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Sofiene
 */
public class Ui_Profil_FOController implements Initializable {

    @FXML
    private Label corpulence_lb;
    @FXML
    private Label origine_lb;
    @FXML
    private Label pilosite_lb;
    @FXML
    private Label profession_lb;
    @FXML
    private Label alcool_lb;
    @FXML
    private Label tabac_lb;
    @FXML
    private Label taille_lb;
    @FXML
    private Label cheveux_lb;
    @FXML
    private Label caractere_lb;
    @FXML
    private Label yeux_lb;
    @FXML
    private Label statut_lb;
    @FXML
    private Label cuisine_lb;
    private static Client profile_owner;

    public static void setProfile_owner(Client profile_owner) {
        Ui_Profil_FOController.profile_owner = profile_owner;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
    }    
    public void init_data()
    {
    }
    
}
