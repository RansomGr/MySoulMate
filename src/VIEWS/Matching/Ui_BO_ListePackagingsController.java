/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Matching;

import Entites.Matching.Packaging;
import Services.Matching.GestionnairePackaging;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
 * @author Nadia
 */
public class Ui_BO_ListePackagingsController implements Initializable {
private GestionnairePackaging ga ;
    private ScaleTransition Hide;
    private  ScaleTransition Show;
    private int StartPoint;
    private  int BreakPoint;
    private int pages;
    private  int current_page;
    private String selected_column;
    private  ObservableList<Packaging> Packagings;
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
    private TableView<Packaging> table_view;
    private TableColumn<Packaging, Integer> id_comlun;
    @FXML
    private TableColumn<Packaging, String> nom_column;
    private TableColumn<Packaging, String> prenom_column;
    private TableColumn<Packaging, String> mdp_column;
    private TableColumn<Packaging , String> Login_column;
    @FXML
    private TextField current_page_te;
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
    private Button precedent_btn;
    @FXML
    private Button suivant_btn;
    @FXML
    private TableColumn<?, ?> ID_comlun;
    @FXML
    private TableColumn<?, ?> contenu_column;
    @FXML
    private TableColumn<?, ?> duree_column;
    @FXML
    private TableColumn<?, ?> prix_column;
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
        
            id_comlun.setCellValueFactory((CellDataFeatures<Admin, Integer> Admin) -> new  SimpleIntegerProperty((Admin.getValue().getID())).asObject());
            nom_column.setCellValueFactory((CellDataFeatures<Admin, String> Admin) -> new SimpleStringProperty(Admin.getValue().getNom())   );
            prenom_column.setCellValueFactory((CellDataFeatures<Admin,String>Admin)-> new SimpleStringProperty(Admin.getValue().getPrenom()) );
            mdp_column.setCellValueFactory((CellDataFeatures<Admin,String>Admin)->new SimpleStringProperty(Admin.getValue().getMotdepasse()));
            Login_column.setCellValueFactory((CellDataFeatures<Admin,String>Admin)->new SimpleStringProperty(Admin.getValue().getLogin()));
            table_view.setRowFactory( tv -> {
            TableRow<Admin> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
             if (e.getClickCount() == 2 && (!row.isEmpty()) ) {
                 Ui_Create_new_Admin_BOController.setAdmin_to_be_modified(row.getItem());
                 try {
                      Optional<ButtonType> result = NextActionWindow.showAndWait();
                     if(result.isPresent()&&result.get()==Modifier)
                           this.take_me_to_Update_page();
                     else if(result.isPresent()&&result.get()==Supprimer)
                     {
                            Optional<ButtonType> result_del = ConfirmDelete.showAndWait();
                            if(result_del.isPresent()&&result_del.get()==Oui)
                            {
                                ga.remove(row.getItem());
                                reload_data();
                            }
                     }
                 } catch (IOException | SQLException ex) {
                     Logger.getLogger(Ui_ListeAdmin_BOController.class.getName()).log(Level.SEVERE, null, ex);
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
            All_row_size=ga.fetchAll().size();
          
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
                 Admins= FXCollections.observableArrayList((ArrayList<Admin>)ga.fetchAll(recherche_dyn_tf.getText(),(current_page-1)*BreakPoint,BreakPoint));
            else 
            {
                 Admins= FXCollections.observableArrayList((ArrayList<Admin>)ga.fetchAll(recherche_dyn_tf.getText(),selected_column,(current_page-1)*BreakPoint,BreakPoint));
            }
    }
    else
    {
             Admins= FXCollections.observableArrayList((ArrayList<Admin>)ga.fetchAll(recherche_dyn_tf.getText(),-1,"DESC"));
           
    }
      
         } catch (SQLException ex) {
                Logger.getLogger(Ui_ListeAdmin_BOController.class.getName()).log(Level.SEVERE, null, ex);
            } 
          
           table_view.setItems(Admins);
    }
    private void color_column(String target)
    {
        System.out.println(target);
        switch (target){
            case "id":{
                   id_comlun.setStyle("-fx-background-color:#B5C689 ");
                    nom_column.getStyleClass().clear();
                   prenom_column.getStyleClass().clear();
                   Login_column.getStyleClass().clear();
                   mdp_column.getStyleClass().clear();
            }
                break ;
            case "nom" :{
                     id_comlun.getStyleClass().clear();
                   nom_column.setStyle("-fx-background-color:#B5C689 ");
                   prenom_column.getStyleClass().clear();
                   Login_column.getStyleClass().clear();
                   mdp_column.getStyleClass().clear();
            }
                break ;
            case "prenom" :{
                     id_comlun.getStyleClass().clear();
                    nom_column.getStyleClass().clear();
                   prenom_column.setStyle("-fx-background-color:#B5C689 ");
                   Login_column.getStyleClass().clear();
                   mdp_column.getStyleClass().clear();
              
            }
                break ;
            case "login" :{
                      id_comlun.getStyleClass().clear();
                    nom_column.getStyleClass().clear();
                    prenom_column.getStyleClass().clear();
                   Login_column.setStyle("-fx-background-color:#B5C689 ");
                      mdp_column.getStyleClass().clear();
               
            }
                break ;
            case "motdepasse":{
                   id_comlun.getStyleClass().clear();
                   nom_column.getStyleClass().clear();
                   prenom_column.getStyleClass().clear();
                   Login_column.getStyleClass().clear();
                   mdp_column.setStyle("-fx-background-color:#B5C689 ");
            }
            break ;
            case "All" :{
                   id_comlun.getStyleClass().clear();
                   nom_column.getStyleClass().clear();
                   prenom_column.getStyleClass().clear();
                   Login_column.getStyleClass().clear();
                    mdp_column.getStyleClass().clear();
                    mdp_column.getStyleClass().addAll("table-column");                    
            } 
            break ;
        }
                
                
    }
        private void init_node()
    {
            Hide= new ScaleTransition();
            Show= new ScaleTransition();
            ga= new GestionnaireAdmin();
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
        NextActionWindow.setHeaderText("Gestion Administrateur");
        NextActionWindow.setContentText("Que Voulez vous faire avec cet Administrateur ?");
        ConfirmDelete.getButtonTypes().clear();
        ConfirmDelete.getButtonTypes().addAll(Oui,Non);
        ConfirmDelete.setTitle("MySoulMate");
        ConfirmDelete.setHeaderText("Gestion Administrateur");
        ConfirmDelete.setContentText("Voulez Vous Vraiment Supprimer cet Administrateur ?");
        
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
     Ui_MainFrame_BOController.Update_Admin_request();
    }
}
