
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Plan;

import Entites.Plan.Plan;


import Services.Plan.GestionnairePlan;
import VIEWS.Ui_MainFrame_BOController;

import java.io.IOException;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * FXML Controller class
 *
 * @author irou
 */
public class Ui_ListePlan_BOController implements Initializable {
      private GestionnairePlan Gp ;
    private ScaleTransition Hide;
    private  ScaleTransition Show;
    private int StartPoint;
    private  int BreakPoint;
    private int pages;
    private  int current_page;
    private String selected_column;
    private  ObservableList<Plan> Plans;
       private Alert NextActionWindow;
    private Alert ConfirmDelete;
    private ButtonType  Modifier ;
    private ButtonType  Supprimer;
    private ButtonType Annuler;
    private ButtonType Oui;
    private ButtonType Non;

    @FXML
    private TextField recherche_dyn_tf;
    @FXML
    private CheckBox operations_plus;
    @FXML
    private Button precedent_pb;
    @FXML
    private TextField current_page_te;
    @FXML
    private Button suivant_pb;
    @FXML
    private TableView<Plan> table_view;
    @FXML
    private TableColumn<Plan, Integer> id_comlun;
    @FXML
    private TableColumn<Plan, String> nom_column;
    @FXML
    private TableColumn<Plan, String> Type_column;
    @FXML
    private TableColumn<Plan, String> SiteWeb_column;
    @FXML
    private TableColumn<Plan, Integer> Telephone_column;
    @FXML
    private TableColumn<Plan, String> Description_column;
    @FXML
    private TableColumn<Plan, String> Photos_column;
     @FXML
    private TableColumn<Plan, String> Email_column;
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
            id_comlun.setCellValueFactory((TableColumn.CellDataFeatures<Plan,Integer> plan) -> new  SimpleIntegerProperty((plan.getValue().getId())).asObject());
            nom_column.setCellValueFactory((TableColumn.CellDataFeatures<Plan, String> plan) -> new SimpleStringProperty(plan.getValue().getNom())   );
            Type_column.setCellValueFactory((TableColumn.CellDataFeatures<Plan,String>plan)-> new SimpleStringProperty(plan.getValue().getType()));
            SiteWeb_column.setCellValueFactory((TableColumn.CellDataFeatures<Plan,String>plan)->new SimpleStringProperty(plan.getValue().getSiteweb()));
            Telephone_column.setCellValueFactory((TableColumn.CellDataFeatures<Plan,Integer>plan)->new SimpleIntegerProperty((plan.getValue().getTelephone())).asObject());
            Description_column.setCellValueFactory((TableColumn.CellDataFeatures<Plan,String>plan)-> new SimpleStringProperty(plan.getValue().getDescription()));
            Photos_column.setCellValueFactory((TableColumn.CellDataFeatures<Plan,String>plan)-> new SimpleStringProperty(plan.getValue().getPhoto()));
            Email_column.setCellValueFactory((TableColumn.CellDataFeatures<Plan,String>plan)-> new SimpleStringProperty(plan.getValue().getEmail()));
            table_view.setRowFactory( tv -> {
            TableRow<Plan> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
             if (e.getClickCount() == 2 && (!row.isEmpty()) ) {
                 Ui_Create_new_Plan_BOController.setPlan_to_be_modified(row.getItem());
                 try {
                      Optional<ButtonType> result = NextActionWindow.showAndWait();
                     if(result.isPresent()&&result.get()==Modifier)
                           this.take_me_to_Update_page();
                     else if(result.isPresent()&&result.get()==Supprimer)
                     {
                            Optional<ButtonType> result_del = ConfirmDelete.showAndWait();
                            if(result_del.isPresent()&&result_del.get()==Oui)
                            {
                                Gp.remove(row.getItem());
                                reload_data();
                            }
                     }
                 } catch (IOException | SQLException ex) {
                     Logger.getLogger(Ui_ListePlan_BOController.class.getName()).log(Level.SEVERE, null, ex);
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
            All_row_size=Gp.fetchAll().size();
          
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
                 Plans= FXCollections.observableArrayList((ArrayList<Plan>)Gp.fetchAll());
            else 
            {
                 Plans= FXCollections.observableArrayList((ArrayList<Plan>)Gp.fetchAll());
            }
    }
    else
    {
             Plans= FXCollections.observableArrayList((ArrayList<Plan>)Gp.fetchAll());
           
    }
      
         } catch (SQLException ex) {
                Logger.getLogger(Ui_ListePlan_BOController.class.getName()).log(Level.SEVERE, null, ex);
            } 
          
           table_view.setItems(Plans);
    }
    private void color_column(String target)
    {
        System.out.println(target);
        switch (target){
            case "id":{
                   id_comlun.setStyle("-fx-background-color:#B5C689 ");
                    nom_column.getStyleClass().clear();
                
            }
                break ;
            case "nom" :{
                     id_comlun.getStyleClass().clear();
                   nom_column.setStyle("-fx-background-color:#B5C689 ");
                 Type_column.getStyleClass().clear();
            }
                break ;
            case "Type" :{
                     id_comlun.getStyleClass().clear();
                    nom_column.getStyleClass().clear();
                    Type_column.setStyle("-fx-background-color:#B5C689 ");
                    
                
              
            }
             
             
                   
                
            
            break ;
            case "All" :{
                   id_comlun.getStyleClass().clear();
                   nom_column.getStyleClass().clear();
                
            } 
            break ;
                
                
        }
                
                
    }
        private void init_node()
    {
            Hide= new ScaleTransition();
            Show= new ScaleTransition();
            Gp= new GestionnairePlan();
            Hide.setNode(operation_grid );
            Show.setNode(operation_grid );            
            Show.setFromY(0);
            Show.setToY(1);
            Hide.setFromY(1);
            Hide.setToY(0);
            Hide.play();
            
            target_column.getItems().addAll("All","id","nom","type"); //,"email","siteweb","telephone","description","photo"
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
        NextActionWindow.setHeaderText("Gestion Plan");
        NextActionWindow.setContentText("Que Voulez vous faire avec cet Plan ?");
        ConfirmDelete.getButtonTypes().clear();
        ConfirmDelete.getButtonTypes().addAll(Oui,Non);
        ConfirmDelete.setTitle("MySoulMate");
        ConfirmDelete.setHeaderText("Gestion Plan");
        ConfirmDelete.setContentText("Voulez Vous Vraiment Supprimer cet Plan ?");
        
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
     Ui_MainFrame_BOController.Update_Plan_request();
    }

    
    
}

