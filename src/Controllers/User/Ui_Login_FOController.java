/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.User;

import Entites.User.Client;
import Services.User.GestionnaireClient;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_Login_FOController implements Initializable {

    @FXML
    private Hyperlink create_new_account_hl;
    @FXML
    private Hyperlink dashboard_scene_hl;
    @FXML
    private TextField login_te;
    @FXML
    private TextField password_te;
    @FXML
    private Button login_pb;
    @FXML
    private Hyperlink forgot_hl;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void Forgot_page(ActionEvent event) throws IOException
    {
     Stage PrimaryStage=MySoulMate.getMainStage();
     Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Forgot_FO.fxml"));
     Scene scene = new Scene(root);
     PrimaryStage.setScene(scene);
     PrimaryStage.show();
    }
        @FXML
    private void DashBoard_page(ActionEvent event)throws IOException
    {
     Stage PrimaryStage=MySoulMate.getMainStage();
     Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Login_BO.fxml"));
     Scene scene = new Scene(root);
     PrimaryStage.setScene(scene);
     PrimaryStage.show();
    }
    @FXML
    private void NewAccount_page(ActionEvent event)throws IOException
    {
     Stage PrimaryStage=MySoulMate.getMainStage();
     Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Create_new_FO.fxml"));
     Scene scene = new Scene(root);
     PrimaryStage.setScene(scene);
     PrimaryStage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void log_me_in(ActionEvent event) throws IOException, SQLException {
        GestionnaireClient gc = new GestionnaireClient();
        Client Logged_in_Client= ((List<Client>)gc.fetchAll()).stream().filter(Client->Client.getPseudo().equals(login_te.getText())&&Client.getMotdepasse().equals(password_te.getText())).findFirst().get();
        if(Logged_in_Client!=null)
        {
            if(Logged_in_Client.getActivation()==0)
            {
              MySoulMate.setLogged_in_Client(Logged_in_Client);
              Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/Profil/ui_FO_AjouterCaracteristique.fxml"));
              Scene scene = new Scene(root);
              MySoulMate.getMainStage().setScene(scene); 
            }
            else
            {
              MySoulMate.setLogged_in_Client(Logged_in_Client);
              Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/ui_MainFrame_FO.fxml"));
              Scene scene = new Scene(root);
              MySoulMate.getMainStage().setScene(scene);
            }
        }
        else
        {
            password_te.clear();
            login_te.clear();
        }
   }
    
}
