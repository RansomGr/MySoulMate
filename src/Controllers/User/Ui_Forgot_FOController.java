/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_Forgot_FOController implements Initializable {

    @FXML
    private Hyperlink create_new_account_hl;
    @FXML
    private Hyperlink dashboard_scene_hl;
    @FXML
    private Button login_pb;
    @FXML
    private Hyperlink forgot_hl;

    /**
     * Initializes the controller class.
     */
     @FXML
    private void Login_page(ActionEvent event) throws IOException
    {
     Stage PrimaryStage=MySoulMate.getMainStage();
     Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Login_FO.fxml"));
     Scene scene = new Scene(root);
     PrimaryStage.setScene(scene);
     PrimaryStage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
   
    
}
