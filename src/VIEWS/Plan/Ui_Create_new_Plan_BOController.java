/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Plan;

import Entites.Plan.Plan;
import Entites.Plan.Plan.Type;

import Services.Plan.GestionnairePlan;

import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.regex.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author irou
 */
public class Ui_Create_new_Plan_BOController implements Initializable {
   private String OperationMode;
   private String Message_Warning;
   private ButtonType  Oui ;
   private ButtonType  Non;

   private Alert InformationWindow;
   private Alert ErrorWindow;
   private Alert WarningWindow;
   private Alert ConfirmWindow;
   Map<TextField,String>Fields;
   Map<ComboBox<String>,String>Fields2;
   private static Plan Plan_to_be_modified ;

    public static void setPlan_to_be_modified(Plan Plan_to_be_modified) {
        Ui_Create_new_Plan_BOController.Plan_to_be_modified = Plan_to_be_modified;
        
    }




//public class Ui_Create_new_Plan_BOController implements Initializable {
//String OperationMode;
//   private Alert InformationWindow;
//   private Alert ErrorWindow;
//   List<TextField>Fields;
    @FXML
    private TextField siteweb_tf;
    @FXML
    private TextField nom_tf;
    @FXML
    private TextField email_tf;
    @FXML
    private TextField telephone_tf;
    @FXML
    private TextArea description_ta;
    @FXML
    private ComboBox<String> type_cb;
    @FXML
    private Button parcourir_btn;
    @FXML
    private TextField photo_img;
    @FXML
    private Button add_new_pb;
    @FXML
    private Button reset_pb;
   @FXML
    private Button localisation_btn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type_cb.getItems().addAll("sportif","divertissment","culturel");
      //  type_cb.getValue();// tjib biha el string seletionner 
      OperationMode="Ajouter";
      InformationWindow   =new Alert(Alert.AlertType.INFORMATION);
        WarningWindow = new Alert(Alert.AlertType.WARNING);
        ErrorWindow =new Alert(Alert.AlertType.ERROR);
        ConfirmWindow = new Alert(Alert.AlertType.CONFIRMATION);
        Oui= new ButtonType("Modifier",ButtonBar.ButtonData.OK_DONE);
        Non=new ButtonType("Annuler",ButtonBar.ButtonData.CANCEL_CLOSE);
        ConfirmWindow.getButtonTypes().clear();
        ConfirmWindow.getButtonTypes().addAll(Oui,Non);
        InformationWindow.setContentText("Plan Ajouté Avec succès !");
        ErrorWindow.setContentText("La base de données n'est pas accessible !");
        ErrorWindow.setHeaderText("Gestion Plan");
        ErrorWindow.setTitle("MySoulMate");
        ConfirmWindow.setTitle("MySoulMate");
        ConfirmWindow.setHeaderText("Gestion Plan");
        ConfirmWindow.setContentText("Voulez Vous vraiement modifier cet Plan ?");
        WarningWindow.setContentText(Message_Warning);
        WarningWindow.setHeaderText("Gestion Plan");
        WarningWindow.setTitle("MySoulMate");
        InformationWindow.setHeaderText("Gestion Plan");
        InformationWindow.setTitle("MySoulMate");
        fill_Nodes();
        check_update();
        
    }    
    private void fill_Nodes()
        {
            
            Fields= new HashMap<>();
             Fields2= new HashMap<>();
            Fields.put(nom_tf,"Nom Plan");
            Fields2.put(type_cb," type Plan");
            Fields.put(email_tf,"email");
            Fields.put(siteweb_tf,"siteweb");
            Fields.put(telephone_tf,"telephone"); 
//            Fields.put(description_ta,"description");
            Fields.put(photo_img,"photo");
            
        }
    @FXML
    private void add_new_plan(ActionEvent event) throws SQLException {
        validate_form();
        if(Message_Warning.equals("Les champs suivants posent des problèmes \n"))
        {
             GestionnairePlan Gp= new GestionnairePlan();
        if(OperationMode.equals("Ajouter"))
        {
       if( Gp.create(new Plan(nom_tf.getText(),Plan.Type.getAsType(type_cb.getValue()),email_tf.getText(),siteweb_tf.getText(),Integer.parseInt(telephone_tf.getText()),description_ta.getText(),photo_img.getText()))==1)
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
                       if( Gp.update(new Plan(Plan_to_be_modified.getID(),nom_tf.getText(),Plan.Type.getAsType(type_cb.getValue()),email_tf.getText(),siteweb_tf.getText(),Integer.parseInt(telephone_tf.getText()),description_ta.getText(),photo_img.getText()))==1)
                       {System.out.println("modifiing ....");
                           InformationWindow.setContentText("Plan modifié avec succée !");
                           InformationWindow.show();
                           OperationMode="Ajouter";
                           Plan_to_be_modified=new Plan(Plan_to_be_modified.getID(),nom_tf.getText(),Plan.Type.getAsType(type_cb.getValue()),email_tf.getText(),siteweb_tf.getText(),Integer.parseInt(telephone_tf.getText()),description_ta.getText(),photo_img.getText());
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

    @FXML
    private void SelectPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Choisir Photo Plan");
//FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Ficheer Images  (*.jpeg)", "*.jpeg");
//fileChooser.getExtensionFilters().add(extFilter);
//fileChooser.setInitialDirectory(new File("images/"));
File img=fileChooser.showOpenDialog(MySoulMate.getMainStage());
photo_img.setText(img.getAbsolutePath());
    }

   

     private void clear_tf()
    {
         Fields.keySet().forEach(x->x.clear());
         if(Plan_to_be_modified!=null)
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
//        if(!(mdp_tf.getText().equals(conf_mdp_tf.getText())))
//        Message_Warning+="Les mots des passes doivent être identique !";
//      WarningWindow.setContentText(Message_Warning);
    }

    private void check_update() {
       if(Plan_to_be_modified!=null)
       {
           nom_tf.setText(Plan_to_be_modified.getNom());
           type_cb.getSelectionModel().select(Type.getAsString(Plan_to_be_modified.getType()));
           email_tf.setText(Plan_to_be_modified.getEmail());
           siteweb_tf.setText(Plan_to_be_modified.getSiteweb());
           telephone_tf.setText(""+Plan_to_be_modified.getTelephone());
           description_ta.setText(Plan_to_be_modified.getDescription());
              photo_img.setText(Plan_to_be_modified.getPhoto());
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
    @FXML
    private void Geo_Localistaion(ActionEvent event) throws IOException {
        Stage GMapsStage = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/APIS/Plan/GoogleMap.fxml"));
        Scene Gmaps = new Scene(root);
        GMapsStage.setScene(Gmaps);
        GMapsStage.show();   
    }

  
}
