/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Matching;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class Ui_FO_show_one_MatchingController implements Initializable {

    @FXML
    private ImageView photo_img;
    @FXML
    private Button inviter_btn;
    @FXML
    private Text compatibilite_text;
    @FXML
    private Text prenom_text;
    @FXML
    private Text ville_text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void inviter_ami(ActionEvent event) {
    }
    
}
