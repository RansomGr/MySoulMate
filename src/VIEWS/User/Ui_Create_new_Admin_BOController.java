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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_Create_new_Admin_BOController implements Initializable {
   String OperationMode;
   String Message_Warning;
   private Alert InformationWindow;
   private Alert ErrorWindow;
   private Alert WarningWindow;
   Map<TextField,String>Fields;
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
        InformationWindow   =new Alert(AlertType.INFORMATION);
        WarningWindow = new Alert(AlertType.WARNING);
        ErrorWindow =new Alert(AlertType.ERROR);
        InformationWindow.setContentText("Admin Ajouter Avec succée !");
        ErrorWindow.setContentText("La base de données n'est pas accesible !");
        ErrorWindow.setHeaderText("Gestion Administrateur");
        ErrorWindow.setTitle("MySoulMate");
        WarningWindow.setContentText(Message_Warning);
        WarningWindow.setHeaderText("Gestion Administrateur");
        WarningWindow.setTitle("MySoulMate");
        InformationWindow.setHeaderText("Gestion Administrateur");
        InformationWindow.setTitle("MySoulMate");
        fill_Nodes();
    }    
        private void fill_Nodes()
        {
            Fields= new HashMap<>();
            Fields.put(mdp_tf,"Mot de passe");
            Fields.put(conf_mdp_tf," Confirmation Mot de passe");
            Fields.put(login_tf,"Login");
            Fields.put(nom_tf,"Nom");
            Fields.put(prenom_tf,"Prenom");
        }
    @FXML
    private void add_new_admin(ActionEvent event) throws SQLException {
        validate_form();
        if(Message_Warning.equals("Les champs suivants posent Des problémes \n"))
        {
        if(OperationMode.equals("Ajouter"))
        {
        GestionnaireAdmin ga= new GestionnaireAdmin();
       if( ga.create(new Admin(nom_tf.getText(),prenom_tf.getText(),login_tf.getText(),mdp_tf.getText()))==1)
       {
           InformationWindow.show();
           clear_tf();
       }
       else
          ErrorWindow.show();
        }
        else
        {
            OperationMode="Modifier";
            GestionnaireAdmin ga= new GestionnaireAdmin();
            ga.update(new Admin(nom_tf.getText(),prenom_tf.getText(),login_tf.getText(),mdp_tf.getText()));
        }
    }
    else
    {
        WarningWindow.show();
    }
    }
    private void clear_tf()
    {
         Fields.keySet().forEach(x->x.clear());
    }
    @FXML
    private void reset_fields(ActionEvent event) {
    clear_tf();
    }
    private void validate_form()
    {
       Message_Warning="Les champs suivants posent Des problémes \n";
        Fields.entrySet().forEach(x->{
            if(x.getKey().getText().isEmpty())
                Message_Warning+="Champ : "+x.getValue()+" est vide !\n";
        });
        if(!(mdp_tf.getText().equals(conf_mdp_tf.getText())))
        Message_Warning+="Les mots des passes doivent etre identique !";
      WarningWindow.setContentText(Message_Warning);
    }
    
}
