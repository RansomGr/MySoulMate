/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.User;


import Entites.User.Client;
import Services.User.GestionnaireAdmin;
import Services.User.GestionnaireClient;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_ListeClient_BOController implements Initializable {
    private GestionnaireClient gc ;
    private ScaleTransition Hide;
    private  ScaleTransition Show;
    private int StartPoint;
    private  int BreakPoint;
    private int pages;
    private  int current_page;
    private String selected_column;
    private  ObservableList<Client> Clients;
    @FXML
    private TextField recherche_dyn_tf;
    @FXML
    private CheckBox operations_plus;
    @FXML
    private TableView<Client> table_view;
    @FXML
    private TableColumn<Client, Integer> id_comlun;
    @FXML
    private TableColumn<Client, String> nom_column;
    @FXML
    private TableColumn<Client, String> prenom_column;
    @FXML
    private TableColumn<Client, String> pseudo_column;
    @FXML
    private TableColumn<Client, String> email_column;
    @FXML
    private TableColumn<Client, String> date_naissance_column;
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
            id_comlun.setCellValueFactory((CellDataFeatures<Client, Integer> Client) -> new  SimpleIntegerProperty((Client.getValue().getID())).asObject());
            nom_column.setCellValueFactory((CellDataFeatures<Client, String> Client) -> new SimpleStringProperty(Client.getValue().getNom())   );
            prenom_column.setCellValueFactory((CellDataFeatures<Client,String>Client)-> new SimpleStringProperty(Client.getValue().getPrenom()) );
            pseudo_column.setCellValueFactory((CellDataFeatures<Client,String>Client)->new SimpleStringProperty(Client.getValue().getPseudo()));
            email_column.setCellValueFactory((CellDataFeatures<Client,String>Client)->new SimpleStringProperty(Client.getValue().getEmail()));
            date_naissance_column.setCellValueFactory((CellDataFeatures<Client,String>Client)-> new SimpleStringProperty(Client.getValue().getDate_naissance().toString()));
    }
    private void reload_data() 
    {  int All_row_size;
         try {
    if(operations_plus.isSelected())
    { 
            All_row_size=gc.fetchAll().size();
          
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
                 Clients= FXCollections.observableArrayList((ArrayList<Client>)gc.fetchAll(recherche_dyn_tf.getText(),(current_page-1)*BreakPoint,BreakPoint));
            else 
            {
                 Clients= FXCollections.observableArrayList((ArrayList<Client>)gc.fetchAll(recherche_dyn_tf.getText(),selected_column,(current_page-1)*BreakPoint,BreakPoint));
            }
    }
    else
    {
             Clients= FXCollections.observableArrayList((ArrayList<Client>)gc.fetchAll(recherche_dyn_tf.getText(),-1,"DESC"));
           
    }
      
         } catch (SQLException ex) {
                Logger.getLogger(Ui_ListeAdmin_BOController.class.getName()).log(Level.SEVERE, null, ex);
            } 
          
           table_view.setItems(Clients);
    }
    private void color_column(String target)
    {
        System.out.println(target);
        switch (target){
            case "id":{
                   id_comlun.setStyle("-fx-background-color:#B5C689 ");
                    nom_column.getStyleClass().clear();
                   prenom_column.getStyleClass().clear();
                //   Login_column.getStyleClass().clear();
                 //  mdp_column.getStyleClass().clear();
            }
                break ;
            case "nom" :{
                     id_comlun.getStyleClass().clear();
                   nom_column.setStyle("-fx-background-color:#B5C689 ");
                   prenom_column.getStyleClass().clear();
             //      Login_column.getStyleClass().clear();
               //    mdp_column.getStyleClass().clear();
            }
                break ;
            case "prenom" :{
                     id_comlun.getStyleClass().clear();
                    nom_column.getStyleClass().clear();
                   prenom_column.setStyle("-fx-background-color:#B5C689 ");
                //   Login_column.getStyleClass().clear();
                 //  mdp_column.getStyleClass().clear();
              
            }
                break ;
            case "login" :{
                      id_comlun.getStyleClass().clear();
                    nom_column.getStyleClass().clear();
                    prenom_column.getStyleClass().clear();
               //    Login_column.setStyle("-fx-background-color:#B5C689 ");
                 //     mdp_column.getStyleClass().clear();
               
            }
                break ;
            case "motdepasse":{
                   id_comlun.getStyleClass().clear();
                   nom_column.getStyleClass().clear();
                   prenom_column.getStyleClass().clear();
                //   Login_column.getStyleClass().clear();
                //   mdp_column.setStyle("-fx-background-color:#B5C689 ");
                
            }
            break ;
            case "All" :{
                   id_comlun.getStyleClass().clear();
                   nom_column.getStyleClass().clear();
                   prenom_column.getStyleClass().clear();
               //    Login_column.getStyleClass().clear();
                  //  mdp_column.getStyleClass().clear();
                   // mdp_column.getStyleClass().addAll("table-column");
            } 
            break ;
                
                
        }
                
                
    }
        private void init_node()
    {
            Hide= new ScaleTransition();
            Show= new ScaleTransition();
            gc= new GestionnaireClient();
            Hide.setNode(operation_grid );
            Show.setNode(operation_grid );            
            Show.setFromY(0);
            Show.setToY(1);
            Hide.setFromY(1);
            Hide.setToY(0);
            Hide.play();
            
            target_column.getItems().addAll("All","id","nom","prenom","login","motdepasse");
            target_column.getSelectionModel().select(0);
           
            lignes_page_cb.getItems().addAll(5,10,20,50,100);
             lignes_page_cb.getSelectionModel().select(0);
            StartPoint=0;
            BreakPoint=5;
            current_page=1;
           
            selected_column="All";
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

    
}
