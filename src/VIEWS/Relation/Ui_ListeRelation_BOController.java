/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Relation;

import Entites.Relation.Relation;
import Services.Relation.GestionnaireRelation;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author zhanimm
 */
public class Ui_ListeRelation_BOController implements Initializable {

    GestionnaireRelation gr ;
    ObservableList<Relation> Relations;
    Comparator<Relation> comparator;
    @FXML
    private TextField recherche_dyn_tf;
    @FXML
    private TableView<Relation> table_view;
     @FXML
    private Button operations_plus;
    @FXML
    private TableColumn<Relation, Integer> id;
    @FXML
    private TableColumn<Relation, String> c1;
    @FXML
    private TableColumn<Relation, String> c2;
    @FXML
    private TableColumn<Relation, Integer> npr;
    @FXML
    private TableColumn<Relation, String> niv;
    @FXML
    private TableColumn<Relation, String> date1;
    @FXML
    private TableColumn<Relation, String> date2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        id.setCellValueFactory((CellDataFeatures<Relation, Integer> Relation) -> new  SimpleIntegerProperty((Relation.getValue().getID())).asObject());
        c1.setCellValueFactory((CellDataFeatures<Relation, String> Relation) -> new SimpleStringProperty((Relation.getValue().getClient1().getPrenom())));
        c2.setCellValueFactory((CellDataFeatures<Relation, String> Relation) -> new SimpleStringProperty((Relation.getValue().getClient2().getPrenom())));
        npr.setCellValueFactory((CellDataFeatures<Relation,Integer>Relation)->new SimpleIntegerProperty((Relation.getValue().getPoints_relation())).asObject());
        niv.setCellValueFactory((CellDataFeatures<Relation,String>Relation)->new SimpleStringProperty(Relation.getValue().getNiveau()));
        date1.setCellValueFactory((CellDataFeatures<Relation,String>Relation)-> new SimpleStringProperty(Relation.getValue().getDate_debut().toString()));
        date2.setCellValueFactory((CellDataFeatures<Relation,String>Relation)-> new SimpleStringProperty(Relation.getValue().getDate_fin().toString()));

        /*date1.setCellValueFactory((CellDataFeatures<Relation,Date>Relation)->new SimpleStringProperty((Relation.getValue().getDate_debut())).asObject());
        date2.setCellValueFactory((CellDataFeatures<Relation,Date>Relation)->new SimpleStringProperty((Relation.getValue().getDate_fin())).asObject());
*/      
         gr = new GestionnaireRelation();
         Relations = FXCollections.observableArrayList();

        try {
           Relations = FXCollections.observableArrayList((ArrayList<Relation>) gr.fetchAll());

      table_view.setItems(Relations);
         }catch (SQLException ex) {
            Logger.getLogger(Ui_ListeRelation_BOController.class.getName()).log(Level.SEVERE, null, ex);
         }
    
//FXCollections.sort(Relations, comparator);
    
    }
    public void action(ActionEvent a){
        comparator = Comparator.comparingInt(Relation::getPoints_relation); 
        FXCollections.sort(Relations, comparator);
        table_view.setItems(Relations);
    }

    

   /* @FXML
    private void recherche_dyn_textchanged(SortEvent<C> event) {
    }*/
    
}
