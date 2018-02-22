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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * FXML Controller class
 *
 * @author Sofiene
 */
public class Ui_ListeProfil_BOController implements Initializable {

    @FXML
    private RowConstraints operation_row;
    @FXML
    private ColumnConstraints header_grid;
    @FXML
    private TextField recherche_dyn_tf;
    @FXML
    private CheckBox operations_plus;
    @FXML
    private GridPane operation_grid;
    @FXML
    private ComboBox<?> target_column;
    @FXML
    private Button precedent_pb;
    @FXML
    private Button suivant_pb;
    @FXML
    private TextField current_page_te;
    @FXML
    private ComboBox<?> lignes_page_cb;
    @FXML
    private TableView<?> table_view;
    @FXML
    private TableColumn<?, ?> id_comlun;
    @FXML
    private TableColumn<?, ?> nom_column;
    @FXML
    private TableColumn<?, ?> prenom_column;
    @FXML
    private TableColumn<?, ?> Login_column;
    @FXML
    private TableColumn<?, ?> mdp_column;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void recherche_dyn_textchanged(KeyEvent event) {
    }

    @FXML
    private void Operation_clicked(ActionEvent event) {
    }

    @FXML
    private void previous_page(ActionEvent event) {
    }

    @FXML
    private void next_page(ActionEvent event) {
    }
    
}
