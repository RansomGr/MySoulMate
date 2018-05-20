/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Plan;

import Entites.Plan.Avis;
import Entites.User.Utilisateur;
import Services.Plan.GestionnaireAvis;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author irou
 */
public class Donner_avis_FOController implements Initializable {

    @FXML
    private ImageView image_img;
    @FXML
    private Label nomC_l;
    @FXML
    private TextArea com_ta;
    @FXML
    private Button ajouter;
    @FXML
    private Spinner<Integer> note_l;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory <Integer> note_spinner_factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10,5);
        
        this.note_l.setValueFactory(note_spinner_factory);
        // TODO
    }    

    @FXML
    private void update_actu(KeyEvent event) {
    }

    @FXML
    private void ajouter_avis(ActionEvent event) throws IOException {
        
        
        
    }
    
    
}
