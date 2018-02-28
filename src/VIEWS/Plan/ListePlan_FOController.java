/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Plan;

import Entites.Plan.Plan;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author irou
 */
public class ListePlan_FOController implements Initializable {

    @FXML
    private ImageView image_img;
    @FXML
    private TextArea text_ta;
    private static Plan plan;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        text_ta.setText(plan.getDescription()); 
        image_img.setImage(new Image("/images/"+plan.getPhoto()));
        // TODO
    }    

    public static Plan getPlan() {
        return plan;
    }

    public static void setPlan(Plan plan) {
        ListePlan_FOController.plan = plan;
    }
    
}
