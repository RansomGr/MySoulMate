/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.User;

import Entites.User.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Comp_ChatFace_FOController implements Initializable {

    @FXML
    private ImageView connected_image;
    @FXML
    private Label connected_name;
    private static Client ChatFaceOwner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       connected_image.setImage(new Image("/images/"+ChatFaceOwner.getProfil().getPhoto()));
       connected_name.setText(ChatFaceOwner.getPseudo());
    }    
    
}
