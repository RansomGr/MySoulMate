/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.User;

import Entites.User.Admin;
import Services.User.GestionnaireAdmin;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_Create_new_Admin_BOController implements Initializable {
   String OperationMode;
    @FXML
    private TextField prenom_tf;
    @FXML
    private TextField mdp_tf;
    @FXML
    private TextField conf_mdp_tf;
    @FXML
    private TextField nom_tf;
    @FXML
    private Button add_new_pb;
    @FXML
    private Button reset_pb;
    @FXML
    private TextField login_tf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           OperationMode="Ajouter";
    }    

    @FXML
    private void add_new_admin(ActionEvent event) throws SQLException {
        if(OperationMode.equals("Ajouter"))
        {
        GestionnaireAdmin ga= new GestionnaireAdmin();
        ga.create(new Admin(nom_tf.getText(),prenom_tf.getText(),login_tf.getText(),mdp_tf.getText()));
        }
        else
        {
            OperationMode="Modifier";
            GestionnaireAdmin ga= new GestionnaireAdmin();
            ga.update(new Admin(nom_tf.getText(),prenom_tf.getText(),login_tf.getText(),mdp_tf.getText()));
        }
    }

    @FXML
    private void reset_fields(ActionEvent event) {
        nom_tf.clear();
    }
    
}
