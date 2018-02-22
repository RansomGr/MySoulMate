/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Profil;

import Entites.Profil.Caracteristique;
import Entites.Profil.Profil;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Sofiene
 */
public class Ui_Ajoute_new_Profil_FOController implements Initializable {

    @FXML
    private TextField photo_tf;
    @FXML
    private TextArea description_tf;
    @FXML
    private Button image_btn;
    @FXML
    private Button add_btn;
    String OperationMode;
    private static Caracteristique cara;
    private Alert ErrorWindow;
      @FXML
    private ImageView image;


    public static void setCara(Caracteristique cara) {
        Ui_Ajoute_new_Profil_FOController.cara = cara;
    }
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    }    



    @FXML
    private void add_new_Profile(ActionEvent event) throws SQLException, IOException {
        Profil p = new Profil(new Caracteristique(),photo_tf.getText(),description_tf.getText());
        Ui_Profile_CreationController.setProf(p);
        Ui_Profile_CreationController.next_step();
    }

    @FXML
    private void select_image(ActionEvent event) {
       FileChooser fileChooser = new FileChooser();
       fileChooser.setTitle("Choisir une Photo de Profil");
       File img=fileChooser.showOpenDialog(MySoulMate.getMainStage());
       photo_tf.setText(img.getName());
       image.setImage(new Image("/images/"+photo_tf.getText()));
       
    }
    
}


