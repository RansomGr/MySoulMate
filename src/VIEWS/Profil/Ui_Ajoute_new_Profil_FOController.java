/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Profil;

import Entites.Profil.Caracteristique;
import Entites.Profil.Profil;
import Services.Profil.GestionnaireProfil;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private Button reset_pb;
    @FXML
    private Button add_btn;
    String OperationMode;
    private static Caracteristique cara;
       private Alert ErrorWindow;


    public static void setCara(Caracteristique cara) {
        Ui_Ajoute_new_Profil_FOController.cara = cara;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      OperationMode="Ajouter";
        
    }    


    @FXML
    private void reset_fields(ActionEvent event) {
    }

    @FXML
    private void add_new_Profile(ActionEvent event) throws SQLException {
         if(OperationMode.equals("Ajouter"))
        {
        GestionnaireProfil Gp= new GestionnaireProfil();
       if( Gp.create(new Profil(cara ,photo_tf.getText(),description_tf.getText(), new Caracteristique()))==1)
       {
          // InformationWindow.show();
          // clear_tf();
       }
       else
          ErrorWindow.show();
        }
        else
        {
            OperationMode="Modifier";
            GestionnaireProfil Gp= new GestionnaireProfil();
            
            Gp.update(new Profil(cara ,photo_tf.getText(),description_tf.getText(), new Caracteristique()));
    }}

    @FXML
    private void select_image(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Choisir une Photo de Profil");
//FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Ficheer Images  (*.jpeg)", "*.jpeg");
//fileChooser.getExtensionFilters().add(extFilter);
//fileChooser.setInitialDirectory(new File("images/"));
File img=fileChooser.showOpenDialog(MySoulMate.getMainStage());
photo_tf.setText(img.getAbsolutePath());
    }
    
}


