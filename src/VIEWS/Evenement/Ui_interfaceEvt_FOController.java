/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Evenement;

import Entites.Events.Events;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author dellpro
 */
public class Ui_interfaceEvt_FOController implements Initializable {
    
    private Events evts;

    @FXML
    private Label type_lbel;
    @FXML
    private Label date_lbel;
    @FXML
    private Label heur_lbel;
    @FXML
    private Label dure_lbel;
    @FXML
    private Label description_lbel;
    @FXML
    private Label plan_lbel;
    @FXML
    private Label nbivt_lbel;
    @FXML
    private Label date_don;
    @FXML
    private Label heur_don;
    @FXML
    private Label dure_don;
    @FXML
    private Label type_don;
    @FXML
    private Label description_don;
    @FXML
    private Label plan_don;
    @FXML
    private Label nbivt_don;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        charger_evt();
    }    
    
    public void charger_evt()
    {
        date_don.setText(evts.getDate_evt().toString());
        heur_don.setText(evts.getHeure_evt());
        dure_don.setText(evts.getDuree_evt());
        type_don.setText(evts.getType_evt());
        description_don.setText(evts.getDescription_evt());
        plan_don.setText(evts.getPlan_evt().getNom());
        nbivt_don.setText(evts.getNb_max()+"");
    }
    
}
