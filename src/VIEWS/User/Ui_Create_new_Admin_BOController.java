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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_Create_new_Admin_BOController implements Initializable {
   private String OperationMode;
   private String Message_Warning;
   private ButtonType  Oui ;
   private ButtonType  Non;

   private Alert InformationWindow;
   private Alert ErrorWindow;
   private Alert WarningWindow;
   private Alert ConfirmWindow;
   Map<TextField,String>Fields;
   private static Admin Admin_to_be_modified ;

    public static void setAdmin_to_be_modified(Admin Admin_to_be_modified) {
        Ui_Create_new_Admin_BOController.Admin_to_be_modified = Admin_to_be_modified;
        
    }
  
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
        ConfirmWindow = new Alert(AlertType.CONFIRMATION);
        
        Oui= new ButtonType("Modifier",ButtonBar.ButtonData.OK_DONE);
        Non=new ButtonType("Annuler",ButtonBar.ButtonData.CANCEL_CLOSE);
        ConfirmWindow.getButtonTypes().clear();
        ConfirmWindow.getButtonTypes().addAll(Oui,Non);
        InformationWindow.setContentText("Admin Ajouté Avec succès !");
        ErrorWindow.setContentText("La base de données n'est pas accessible !");
        ErrorWindow.setHeaderText("Gestion Administrateur");
        ErrorWindow.setTitle("MySoulMate");
        ConfirmWindow.setTitle("MySoulMate");
        ConfirmWindow.setHeaderText("Gestion Administrateur");
        ConfirmWindow.setContentText("Voulez Vous vraiement modifier cet Administreur ?");
        WarningWindow.setContentText(Message_Warning);
        WarningWindow.setHeaderText("Gestion Administrateur");
        WarningWindow.setTitle("MySoulMate");
        InformationWindow.setHeaderText("Gestion Administrateur");
        InformationWindow.setTitle("MySoulMate");
        fill_Nodes();
        check_update();
        
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
        if(Message_Warning.equals("Les champs suivants posent des problèmes \n"))
        {
             GestionnaireAdmin ga= new GestionnaireAdmin();
        if(OperationMode.equals("Ajouter"))
        {
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
               Optional<ButtonType> result = ConfirmWindow.showAndWait();
                     if(result.isPresent()&&result.get()==Oui)
                     {   
                       if(ga.update(new Admin(nom_tf.getText(),prenom_tf.getText(),login_tf.getText(),mdp_tf.getText()))==1)
                       {
                           InformationWindow.setContentText("Administrateur modifié avec succée !");
                           InformationWindow.show();
                           OperationMode="Ajouter";
                       }
                       else
                          ErrorWindow.show();   
                     }
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
         if(Admin_to_be_modified!=null)
             check_update();
    }
    @FXML
    private void reset_fields(ActionEvent event) {
    clear_tf();
    }
    private void validate_form()
    {
       Message_Warning="Les champs suivants posent des problèmes \n";
        Fields.entrySet().forEach(x->{
            if(x.getKey().getText().isEmpty())
                Message_Warning+="Le champ : "+x.getValue()+" est vide !\n";
        });
        if(!(mdp_tf.getText().equals(conf_mdp_tf.getText())))
        Message_Warning+="Les mots des passes doivent être identique !";
      WarningWindow.setContentText(Message_Warning);
    }

    private void check_update() {
       if(Admin_to_be_modified!=null)
       {
           nom_tf.setText(Admin_to_be_modified.getNom());
           prenom_tf.setText(Admin_to_be_modified.getPrenom());
           login_tf.setText(Admin_to_be_modified.getLogin());
           OperationMode="Modifer";
           update_button();
       }
       else
           clear_tf();
    }
    private void update_button()
    {
        add_new_pb.setText(OperationMode);
    }


    
}
