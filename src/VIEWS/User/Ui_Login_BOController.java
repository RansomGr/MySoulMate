/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.User;

import Entites.User.Admin;
import Services.User.GestionnaireAdmin;
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
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_Login_BOController implements Initializable {

    @FXML
    private Hyperlink dashboard_scene_hl;
    @FXML
    private TextField login_tf;
    @FXML
    private TextField password_tf;
    @FXML
    private Button login_pb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      login_tf.setText("Donec.tincidunt@interdum.ca");
      password_tf.setText("XGD83YVQ5HD");
    }    

    @FXML
    private void take_me_back_to_FO(ActionEvent event) throws IOException {// missing animations
        Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Login_FO.fxml"));
        Scene scene = new Scene(root);
        MySoulMate.getMainStage().setScene(scene);
    }

    @FXML
    private void login_admin(ActionEvent event) throws SQLException, IOException {// missing animations
//        GestionnaireAdmin ga = new GestionnaireAdmin();
//        Admin Logged_in_Admin= ((List<Admin>)ga.fetchAll()).stream().filter(Admin->Admin.getLogin().equals(login_tf.getText())&&Admin.getMotdepasse().equals(password_tf.getText())).findFirst().get();
//        if(Logged_in_Admin!=null)
//        {
       // MySoulMate.setLogged_in_Admin(Logged_in_Admin);
       Admin a = new Admin(0, "7ableu", "7ableu", "chfamma", "*****");
        MySoulMate.setLogged_in_Admin(a);
        Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/ui_MainFrame_BO.fxml"));
        Scene scene = new Scene(root);
        MySoulMate.getMainStage().setScene(scene);
//        }
//        else
//        {
//            password_tf.clear();
//            login_tf.clear();
//        }
    }
    
}
