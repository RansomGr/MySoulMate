/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Plan;

import Entites.Plan.Avis;
import Entites.Plan.Plan;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author irou
 */
public class Ui_Plan_FOController implements Initializable {

    @FXML
    private StackPane actualite_sp;
    @FXML
    private ImageView imgage_img;
    @FXML
    private Text notet_label;
    @FXML
    private TextArea description_ta;
    @FXML
    private Text nomP_label;
    @FXML
    private Button Reserver_btn;
    private static Plan plan;
    private static Avis avis;

    public static Avis getAvis() {
        return avis;
    }

    public static void setAvis(Avis avis) {
        Ui_Plan_FOController.avis = avis;
    }

    public static Plan getPlan() {
        return plan;
    }

    public static void setPlan(Plan plan) {
        Ui_Plan_FOController.plan = plan;
    }
    
    /**
     * 
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
      nomP_label.setText(plan.getNom());
      description_ta.setText(plan.getDescription());
      notet_label.setText(avis.getNote().toString());
        // TODO
    }    
    
}
