/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Evenement;

import Entites.Events.Events;
import VIEWS.User.*;
import Services.Events.GestionnaireEvent;
import VIEWS.Ui_MainFrame_BOController;
import VIEWS.Ui_MainFrame_FOController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import mysoulmate.MySoulMate;
//import com.way2sms.SMS;

/**
 * FXML Controller class
 *
 * @author dellpro
 */
public class Ui_ListeEvents_FOController implements Initializable {
    private GestionnaireEvent ge;
    private ScaleTransition Hide;
    private  ScaleTransition Show;
    private int StartPoint;
    private  int BreakPoint;
    private int pages;
    private  int current_page;
    private String selected_column;
    private Alert NextActionWindow;
    private Alert ConfirmDelete;
    private ButtonType  Modifier ;
    private ButtonType  Supprimer;
    private ButtonType Annuler;
    private ButtonType Oui;
    private ButtonType Non;
    
    private  ObservableList<Events> evenent;
    @FXML
    private TextField recherche_dyn_tf;
    @FXML
    private CheckBox operations_plus;
    @FXML
    private TableView<Events> table_view;
    
    @FXML
    private Button precedent_pb;
    @FXML
    private TextField current_page_te;
    @FXML
    private Button suivant_pb;
    @FXML
    private GridPane operation_grid;
    @FXML
    private RowConstraints operation_row;
    @FXML
    private ComboBox<String> target_column;
    @FXML
    private ComboBox<Integer> lignes_page_cb;
    @FXML
    private ColumnConstraints header_grid;
    @FXML
    private TableColumn<Events, String> nomevt_cl;
    @FXML
    private TableColumn<Events, String> date_cl;
    @FXML
    private TableColumn<Events, String> heure_cl;
    @FXML
    private TableColumn<Events, String> duree_cl;
    @FXML
    private TableColumn<Events, String> type_cl;
    @FXML
    private TableColumn<Events, String> description_cl;
    @FXML
    private TableColumn<Events, String> plan_cl;
    @FXML
    private TableColumn<Events, Integer> ivt_cl;
    @FXML
    private Button Ajout_btn;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_node();
        init_Actions();
        init_tableView();
        reload_data();
    }    

    @FXML
    private void recherche_dyn_textchanged(KeyEvent event)  {
            reload_data();    
    }

    @FXML
    private void Operation_clicked(ActionEvent event) {
        if(operations_plus.isSelected())
            Show.play();
        else
            Hide.play();   
              reload_data();
    }
    private void init_tableView()
    {
           nomevt_cl.setCellValueFactory((CellDataFeatures<Events, String> Eventss) -> new  SimpleStringProperty((Eventss.getValue().getNom_evt())));
           date_cl.setCellValueFactory((CellDataFeatures<Events, String> Eventss) -> new  SimpleStringProperty(Eventss.getValue().getDate_evt().toString()));
           heure_cl.setCellValueFactory((CellDataFeatures<Events, String> Eventss) -> new  SimpleStringProperty((Eventss.getValue().getHeure_evt())));
           duree_cl.setCellValueFactory((CellDataFeatures<Events, String> Eventss) -> new  SimpleStringProperty((Eventss.getValue().getDuree_evt())));
           type_cl.setCellValueFactory((CellDataFeatures<Events, String> Eventss) -> new  SimpleStringProperty((Eventss.getValue().getType_evt())));
           description_cl.setCellValueFactory((CellDataFeatures<Events, String> Eventss) -> new  SimpleStringProperty((Eventss.getValue().getDescription_evt())));
           plan_cl.setCellValueFactory((CellDataFeatures<Events, String> Eventss) -> new  SimpleStringProperty((Eventss.getValue().getPlan_evt().getNom())));
           ivt_cl.setCellValueFactory((CellDataFeatures<Events, Integer> Eventss) -> new  SimpleIntegerProperty((Eventss.getValue().getNb_max())).asObject());
           table_view.setRowFactory(tv -> {
            TableRow<Events> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
             if (e.getClickCount() == 2 && (!row.isEmpty()) ) {
                // Ui_evenement_BOController.setEvent_to_be_modified(row.getItem());
                Ui_evenement_FOController.setEvent_to_be_modified(row.getItem());
                 try {
                      Optional<ButtonType> result = NextActionWindow.showAndWait();
                     if(result.isPresent()&&result.get()==Modifier)
                           this.take_me_to_Update_page();
                     else if(result.isPresent()&&result.get()==Supprimer)
                     {
                            Optional<ButtonType> result_del = ConfirmDelete.showAndWait();
                            if(result_del.isPresent()&&result_del.get()==Oui)
                            {
                                ge.remove(row.getItem());
                                reload_data();
                            }
                     }
                 } catch (IOException | SQLException ex) {
                     Logger.getLogger(Ui_ListeEvents_FOController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 }
                 });
                return row;
               });
    }
    private void reload_data() 
    {  int All_row_size;
         try {
    if(operations_plus.isSelected())
    { 
            All_row_size=ge.fetchAll().size();
          
               pages=(All_row_size/BreakPoint);
            if((All_row_size%BreakPoint!=0))
                 pages+=1;
           //   System.out.println(pages);
      
           if(current_page>pages)
           {
               current_page=pages;
                System.out.println("current page :"+current_page);
           }
          current_page_te.setText(Integer.toString(current_page)+" / " +Integer.toString(pages));
            if(selected_column.equals("All"))
                 evenent= FXCollections.observableArrayList((ArrayList<Events>)ge.fetchAll(recherche_dyn_tf.getText(),(current_page-1)*BreakPoint,BreakPoint));
            else 
            {
                 evenent= FXCollections.observableArrayList((ArrayList<Events>)ge.fetchAll(recherche_dyn_tf.getText(),selected_column,(current_page-1)*BreakPoint,BreakPoint));
            }
    }
    else
    {
             evenent= FXCollections.observableArrayList((ArrayList<Events>)ge.fetchAll(recherche_dyn_tf.getText(),-1,"DESC"));
           
    }
      
         } catch (SQLException ex) {
                Logger.getLogger(Ui_ListeEvents_FOController.class.getName()).log(Level.SEVERE, null, ex);
            } 
          
           table_view.setItems(evenent);
    }
    private void color_column(String target)
    {
        System.out.println(target);
        switch (target){
            case "nom_evt":{
                   nomevt_cl.setStyle("-fx-background-color:#B5C689 ");
                    date_cl.getStyleClass().clear();
                   heure_cl.getStyleClass().clear();
                   duree_cl.getStyleClass().clear();
                   type_cl.getStyleClass().clear();
                   description_cl.getStyleClass().clear();
                   plan_cl.getStyleClass().clear();
                   ivt_cl.getStyleClass().clear();
            }
                break ;
            case "date_evt" :{
                   nomevt_cl.getStyleClass().clear();
                   date_cl.setStyle("-fx-background-color:#B5C689 ");
                   heure_cl.getStyleClass().clear();
                   duree_cl.getStyleClass().clear();
                   type_cl.getStyleClass().clear();
                   description_cl.getStyleClass().clear();
                   plan_cl.getStyleClass().clear();
                   ivt_cl.getStyleClass().clear();
            }
                break ;
            case "heure" :{
                   nomevt_cl.getStyleClass().clear();
                   date_cl.getStyleClass().clear();
                   heure_cl.setStyle("-fx-background-color:#B5C689 ");
                   duree_cl.getStyleClass().clear();
                   type_cl.getStyleClass().clear();
                   description_cl.getStyleClass().clear();
                   plan_cl.getStyleClass().clear();
                   ivt_cl.getStyleClass().clear();  
                 
            }
                break ;
            case "type_evt" :{
                    nomevt_cl.getStyleClass().clear();
                   date_cl.getStyleClass().clear();
                   heure_cl.getStyleClass().clear();
                   duree_cl.getStyleClass().clear();
                   type_cl.setStyle("-fx-background-color:#B5C689 ");
                   description_cl.getStyleClass().clear();
                   plan_cl.getStyleClass().clear();
                   ivt_cl.getStyleClass().clear();
               
            }
                break ;
            case "plan_evt":{
                   nomevt_cl.getStyleClass().clear();
                   date_cl.getStyleClass().clear();
                   heure_cl.getStyleClass().clear();
                   duree_cl.getStyleClass().clear();
                   type_cl.getStyleClass().clear();
                   description_cl.getStyleClass().clear();
                   plan_cl.setStyle("-fx-background-color:#B5C689 ");
                   ivt_cl.getStyleClass().clear();
            }
            break ;
            case "All" :{
                   nomevt_cl.getStyleClass().clear();
                   date_cl.getStyleClass().clear();
                   heure_cl.getStyleClass().clear();
                   duree_cl.getStyleClass().clear();
                   type_cl.getStyleClass().clear();
                   description_cl.getStyleClass().clear();
                   plan_cl.setStyle("-fx-background-color:#B5C689 ");
                   ivt_cl.getStyleClass().clear();
            } 
            break ;
                
                
        }
                
                
    }
        private void init_node()
    {
            Hide= new ScaleTransition();
            Show= new ScaleTransition();
            ge= new GestionnaireEvent();
            Hide.setNode(operation_grid );
            Show.setNode(operation_grid );            
            Show.setFromY(0);
            Show.setToY(1);
            Hide.setFromY(1);
            Hide.setToY(0);
            Hide.play();
            
            target_column.getItems().addAll("All","nom_evt","date_evt","heure_evt","heure","type_evt");
            target_column.getSelectionModel().select(0);
           
            lignes_page_cb.getItems().addAll(5,10,20,50,100);
             lignes_page_cb.getSelectionModel().select(0);
            StartPoint=0;
            BreakPoint=5;
            current_page=1;
           
        selected_column="All";
        Modifier= new ButtonType("Modifier",ButtonBar.ButtonData.OK_DONE);
        Supprimer=new ButtonType("Supprimer",ButtonBar.ButtonData.OK_DONE);
        Annuler=new ButtonType("Annuler",ButtonBar.ButtonData.CANCEL_CLOSE);
        Oui = new ButtonType("Oui",ButtonBar.ButtonData.OK_DONE);
        Non= new ButtonType("Non",ButtonBar.ButtonData.CANCEL_CLOSE);
        NextActionWindow= new Alert(Alert.AlertType.CONFIRMATION);
        ConfirmDelete=new Alert(Alert.AlertType.CONFIRMATION);
        
        NextActionWindow.getButtonTypes().clear();
        NextActionWindow.getButtonTypes().addAll(Modifier,Supprimer,Annuler);
        NextActionWindow.setTitle("MySoulMate");
        NextActionWindow.setHeaderText("Gestion évènement");
        NextActionWindow.setContentText("Que Voulez vous faire avec cet évènement ?");
        ConfirmDelete.getButtonTypes().clear();
        ConfirmDelete.getButtonTypes().addAll(Oui,Non);
        ConfirmDelete.setTitle("MySoulMate");
        ConfirmDelete.setHeaderText("Gestion évènement");
        ConfirmDelete.setContentText("Voulez Vous Vraiment Supprimer cet évènement ?");
        
    }
        private void init_Actions()
        {
            target_column.valueProperty().addListener(( SelectedValue) -> {   selected_column=((ObservableValue<String>)SelectedValue).getValue();reload_data(); color_column(selected_column);});
            lignes_page_cb.valueProperty().addListener(( SelectedValue) -> {   BreakPoint=(((ObservableValue<Integer>)SelectedValue).getValue());reload_data(); });
        }

    @FXML
    private void previous_page(ActionEvent event) {
        if(current_page>1)
        {
            current_page--;
            reload_data();
        }
    }

    @FXML
    private void next_page(ActionEvent event) {
           if(pages>current_page)
           {
            current_page++;
            reload_data();
           }
    }

    private void take_me_to_Update_page() throws IOException
    { 
        
     MySoulMate.getMainController().Update_Event_request();
    }

    @FXML
    private void load_ajouter_evt(ActionEvent event) {
        try {
             MySoulMate.getMainController().load_ajout_event(event);
        } catch (IOException ex) {
            Logger.getLogger(Ui_ListeEvents_FOController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
//class TestSMS {
//
//public static void main(String[] args) {
//
//SMS smsClient=new SMS();
//smsClient.send( "dellpro", "", 21821220, "salut",null);
//}
//}

