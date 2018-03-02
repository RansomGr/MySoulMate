/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Evenement;

import Entites.Events.Events;
import Services.Events.GestionnaireEvent;
import VIEWS.Ui_MainFrame_FOController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author dellpro
 */
public class Ui_even_FOController implements Initializable {
    
    private Events evt;

    @FXML
    private Label date_lb;
    @FXML
    private Label description_lb;
    @FXML
    private Hyperlink detail_lb;

    public Events getEvt() {
        return evt;
    }

    public void setEvt(Events evt) {
        this.evt = evt;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        load_data_evt();
    }    
    public void load_data_evt()
    {
        date_lb.setText(evt.getDate_evt().toString());
        description_lb.setText(evt.getDescription_evt());
        
    }

    @FXML
    private void ajouter(ActionEvent event) {
        try {
             MySoulMate.getMainController().charger_ajout_event(event);
        } catch (IOException ex) {
            Logger.getLogger(Ui_even_FOController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void charger_evt() {
       Services.Events.GestionnaireEvent ge = new Services.Events.GestionnaireEvent();
        date_lb.setText(evt.getDate_evt().toString());
        description_lb.setText(evt.getDescription_evt());       
      
    }
}
