/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Profil;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Sofiene
 */
public class Ui_FO_AjouterCaracteristiqueController implements Initializable {

    @FXML
    private ComboBox<?> silouhette_cb;
    @FXML
    private ComboBox<?> pilosite_cb;
    @FXML
    private ComboBox<?> origine_cb;
    @FXML
    private TextField profession_tf;
    @FXML
    private ComboBox<?> caractere_cb;
    @FXML
    private Spinner<?> taille_min_sp;
    @FXML
    private ComboBox<?> cheveux_cb;
    @FXML
    private ComboBox<?> yeux_cb;
    @FXML
    private ComboBox<?> statut_cb;
    @FXML
    private ComboBox<?> cuisine_cb;
    @FXML
    private Button valider_btn;
    @FXML
    private Button reset_btn;
    @FXML
    private Spinner<?> taille_max_sb;
    @FXML
    private ComboBox<?> ville_cb;
    @FXML
    private RadioButton tabac_oui_rb;
    @FXML
    private RadioButton tabac_non_rb;
    @FXML
    private RadioButton tabac_indifferent_rb;
    @FXML
    private RadioButton alcool_oui_rb;
    @FXML
    private RadioButton alcool_non_rb;
    @FXML
    private RadioButton alcool_indifferent_rb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Valider(ActionEvent event) {
    }

    @FXML
    private void Reset(ActionEvent event) {
    }
    
}
