/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_MainFrameController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML// in order to use functions in FXML you need to ADD FXML Annotation
    private void handleButtonAction(ActionEvent event) throws IOException
    {
     //   Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/TEST.fxml"));
     //  Scene scene = new Scene(root);
     //   Stage App_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
     //  App_stage.setScene(scene) ;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        // TODO
    }    
    
}
