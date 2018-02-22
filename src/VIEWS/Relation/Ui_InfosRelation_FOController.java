/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Relation;

import Entites.Relation.Relation;
import Services.Relation.GestionnaireRelation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author zhanimm
 */
public class Ui_InfosRelation_FOController implements Initializable {

    @FXML
    private Text temps;
    @FXML
    private Text pts;
    @FXML
    private Text niv;
    @FXML
    private Text c1;
    @FXML
    private Text c2;
    private String niveau;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
       // Relation r = new Relation(temps.getText)
        temps.setText("0");
        pts.setText("0");
        niv.setText("0");
        
    }    
    
}
