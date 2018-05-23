/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Plan;

import Entites.Plan.Plan;
import VIEWS.Ui_MainFrame_BOController;
import VIEWS.Ui_MainFrame_FOController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author irou
 */
public class Ui_Single_planController implements Initializable {

    @FXML
    private ImageView owner_photo;
    @FXML
    private Label owner_name;
    private Plan p;
    @FXML
    private Button detail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    public void setPlan(Plan p)
    {
        this.p=p;
    this.owner_name.setText(p.getNom());

        System.out.println(p.getPhoto());
    this.owner_photo.setImage(new Image("http://localhost/MySoulMate-Symphony/web/images/"+p.getPhoto()));
        
    }

    @FXML
    private void plan_take_me(ActionEvent event) throws IOException {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/VIEWS/Plan/ui_Single_plan.fxml"));
        Node root = fxml.load();
      
        Ui_MainFrame_FOController.showplan(p);
         
    }
    
}
