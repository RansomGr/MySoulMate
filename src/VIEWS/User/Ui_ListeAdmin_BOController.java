/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.User;

import Entites.User.Admin;
import Services.User.GestionnaireAdmin;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_ListeAdmin_BOController implements Initializable {

    @FXML
    private TextField recherche_dyn_tf;
    @FXML
    private CheckBox operations_plus;
    @FXML
    private TableView<Admin> table_view;
    @FXML
    private TableColumn<Admin, Integer> id_comlun;
    @FXML
    private TableColumn<Admin, String> nom_column;
    @FXML
    private TableColumn<Admin, String> prenom_column;
    @FXML
    private TableColumn<Admin, String> mdp_column;
    @FXML
    private TableColumn<Admin , String> Login_column;
    @FXML
    private Button precedent_pb;
    @FXML
    private TextField current_page_te;
    @FXML
    private Button suivant_pb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        id_comlun.setCellValueFactory((CellDataFeatures<Admin, Integer> Admin) -> new  SimpleIntegerProperty((Admin.getValue().getID())).asObject());
        nom_column.setCellValueFactory((CellDataFeatures<Admin, String> Admin) -> new SimpleStringProperty(Admin.getValue().getNom())   );
        prenom_column.setCellValueFactory((CellDataFeatures<Admin,String>Admin)-> new SimpleStringProperty(Admin.getValue().getPrenom()) );
        mdp_column.setCellValueFactory((CellDataFeatures<Admin,String>Admin)->new SimpleStringProperty(Admin.getValue().getMotdepasse()));
        Login_column.setCellValueFactory((CellDataFeatures<Admin,String>Admin)->new SimpleStringProperty(Admin.getValue().getLogin()));
        GestionnaireAdmin ga = new GestionnaireAdmin();
        try {
            ObservableList<Admin> Admins = FXCollections.observableArrayList((ArrayList<Admin>) ga.fetchAll());
            System.out.println(Admins.size());
             table_view.setItems(Admins);
         }catch (SQLException ex) {
            Logger.getLogger(Ui_ListeAdmin_BOController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }    
    
}
