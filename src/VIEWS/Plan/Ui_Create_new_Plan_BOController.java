/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Plan;

import Entites.Plan.Plan;
import Services.Plan.GestionnairePlan;
import java.io.File;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.regex.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author irou
 */
public class Ui_Create_new_Plan_BOController implements Initializable {
String OperationMode;
   private Alert InformationWindow;
   private Alert ErrorWindow;
   List<TextField>Fields;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type_cb.getItems().addAll("sportif","divertissment","culturel");
      //  type_cb.getValue();// tjib biha el string seletionner 
      OperationMode="Ajouter";
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

    @FXML
    private void add_new_plan(ActionEvent event) throws SQLException {
         if(OperationMode.equals("Ajouter"))
        {
        GestionnairePlan Gp= new GestionnairePlan();
       if( Gp.create(new Plan(nom_tf.getText(),Plan.Type.getAsType(type_cb.getValue()),email_tf.getText(),siteweb_tf.getText(),Integer.parseInt(telephone_tf.getText()),description_ta.getText(),photo_img.getText()))==1)
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
            GestionnairePlan Gp= new GestionnairePlan();
            
            Gp.update(new Plan(nom_tf.getText(),Plan.Type.getAsType(type_cb.getValue()),email_tf.getText(),siteweb_tf.getText(),parseInt(telephone_tf.getText()),description_ta.getText(),photo_img.getText()));
        }
    }

    private void clear_tf()
    {
         Fields.forEach(x->x.clear());
    }
    @FXML
    private void reset_fields(ActionEvent event) {
    clear_tf();
    }
}
