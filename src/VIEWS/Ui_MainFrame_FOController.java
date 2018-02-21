/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS;

import VIEWS.Profil.Ui_Profil_FOController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_MainFrame_FOController implements Initializable {

    @FXML
    private Hyperlink create_new_account_hl;
    @FXML
    private AnchorPane connected_friends_scroll_pane;
    @FXML
    private StackPane Content_pane;
    @FXML
    private AnchorPane bonplans_and_evennements;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void load_my_profil(ActionEvent event) throws IOException {
         Ui_Profil_FOController.setProfile_owner(MySoulMate.getLogged_in_Client());
         Node root = FXMLLoader.load(getClass().getResource("/VIEWS/Profil/ui_Profil_FO.fxml"));
         Content_pane.getChildren().clear();
         Content_pane.getChildren().add(root);    
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
         MySoulMate.setLogged_in_Client(null);
         Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Login_FO.fxml"));
         Scene sene = new Scene(root);
         MySoulMate.getMainStage().setScene(sene);
    }
    
}
