/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Evenement;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination.Modifier;

/**
 * FXML Controller class
 *
 * @author dellpro
 */
public class Ui_ListeEvenementController implements Initializable {
    //private EvenementGroup ge;
     private ScaleTransition Hide;
    private  ScaleTransition Show;
    private int StartPoint;
    private  int BreakPoint;
    private int pages;
    private  int current_page;
    private String selected_column;
    //private  ObservableList<EvenementGroup> events;
    private Alert NextActionWindow;
    private Alert ConfirmDelete;
    
    private ButtonType  Modifier ;
    private ButtonType  Supprimer;
    private ButtonType Annuler;
    private ButtonType Oui;
    private ButtonType Non;

    @FXML
    private TextField recherc_txf;
    @FXML
    private CheckBox optav_chb;
    @FXML
//    private TableColumn<EvenementGroup, String> nomevt_cl;
//    @FXML
//    private TableColumn<EvenementGroup, String> typeevt_cl;
//    @FXML
//    private TableColumn<EvenementGroup, Date> dateevt_cl;
//    @FXML
//    private TableColumn<EvenementGroup, String> ivtevt_cl;
//    @FXML
//    private TableColumn<EvenementGroup, String> plan_cl;
//    @FXML
//    private TableColumn<EvenementGroup, String> descrip_cl;
//    @FXML
//    private TableView<EvenementGroup> lstevt_tbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        init_node();
//        init_Actions();
        init_tableView();
//        reload_data();
    }    
    private void init_tableView()
    {
//            nomevt_cl.setCellValueFactory((CellDataFeatures<EvenementGroup, String> EvenementGroup) -> new  SimpleStringProperty((EvenementGroup.getValue().getNom())));
//            typeevt_cl.setCellValueFactory((CellDataFeatures<EvenementGroup, String> EvenementGroup) -> new SimpleObjectProperty<String>(EvenementGroup.getValue().getNom()));
//            dateevt_cl.setCellValueFactory((CellDataFeatures<EvenementGroup,Date>EvenementGroup)-> new SimpleObjectProperty<Date>(EvenementGroup.getValue().getDate_evenement()) );
//            ivtevt_cl.setCellValueFactory((CellDataFeatures<EvenementGroup,String>EvenementGroup)->new SimpleStringProperty(EvenementGroup.getValue().getNom()));
//            plan_cl.setCellValueFactory((CellDataFeatures<EvenementGroup,String>EvenementGroup)->{
//                return new SimpleStringProperty(EvenementGroup.getValue().getPlan().getNom());
//            });
//            descrip_cl.setCellValueFactory((CellDataFeatures<EvenementGroup,String>EvenementGroup)->new SimpleStringProperty(EvenementGroup.getValue().getDescription()));

//*****************************************************************************
////            lstevt_tbl.setRowFactory( tv -> {
//            TableRow<EvenementGroup> row = new TableRow<>();
 //           }
    
//            row.setOnMouseClicked(e -> {
//             if (e.getClickCount() == 2 && (!row.isEmpty()) ) {
//                 Ui_evenement_BOController.setAdmin_to_be_modified(row.getItem());
//                 try {
//                      Optional<ButtonType> result = NextActionWindow.showAndWait();
//                     if(result.isPresent()&&result.get()==Modifier)
//                           this.take_me_to_Update_page();
//                     else if(result.isPresent()&&result.get()==Supprimer)
//                     {
//                            Optional<ButtonType> result_del = ConfirmDelete.showAndWait();
//                            if(result_del.isPresent()&&result_del.get()==Oui)
//                            {
//                                ge.remove(row.getItem());
//                                reload_data();
//                            }
//                     }
//                 } catch (IOException | SQLException ex) {
//                     Logger.getLogger(Ui_ListeAdmin_BOController.class.getName()).log(Level.SEVERE, null, ex);
//                 }
//                 }
//                 });
//                return row;
//               });
//    }
    
//     private void reload_data() 
//    {  int All_row_size;
//         try {
//    if(operations_plus.isSelected())
//    { 
//            All_row_size=ge.fetchAll().size();
//          
//               pages=(All_row_size/BreakPoint);
//            if((All_row_size%BreakPoint!=0))
//                 pages+=1;
//           //   System.out.println(pages);
//      
//           if(current_page>pages)
//           {
//               current_page=pages;
//                System.out.println("current page :"+current_page);
//           }
//          current_page_te.setText(Integer.toString(current_page)+" / " +Integer.toString(pages));
//            if(selected_column.equals("All"))
//                 Admins= FXCollections.observableArrayList((ArrayList<Admin>)ga.fetchAll(recherche_dyn_tf.getText(),(current_page-1)*BreakPoint,BreakPoint));
//            else 
//            {
//                 Admins= FXCollections.observableArrayList((ArrayList<Admin>)ga.fetchAll(recherche_dyn_tf.getText(),selected_column,(current_page-1)*BreakPoint,BreakPoint));
//            }
//    }
//    else
//    {
//             Admins= FXCollections.observableArrayList((ArrayList<Admin>)ga.fetchAll(recherche_dyn_tf.getText(),-1,"DESC"));
//           
//    }
//      
//         } catch (SQLException ex) {
//                Logger.getLogger(Ui_ListeAdmin_BOController.class.getName()).log(Level.SEVERE, null, ex);
//            } 
//          
//           lstevt_tbl.setItems(Admins);
   }
//    private void color_column(String target)
//    {
//        System.out.println(target);
//        switch (target){
//            case "id":{
//                   id_comlun.setStyle("-fx-background-color:#B5C689 ");
//                    nom_column.getStyleClass().clear();
//                   prenom_column.getStyleClass().clear();
//                   Login_column.getStyleClass().clear();
//                   mdp_column.getStyleClass().clear();
//            }
//                break ;
//            case "nom" :{
//                     id_comlun.getStyleClass().clear();
//                   nom_column.setStyle("-fx-background-color:#B5C689 ");
//                   prenom_column.getStyleClass().clear();
//                   Login_column.getStyleClass().clear();
//                   mdp_column.getStyleClass().clear();
//            }
//                break ;
//            case "prenom" :{
//                     id_comlun.getStyleClass().clear();
//                    nom_column.getStyleClass().clear();
//                   prenom_column.setStyle("-fx-background-color:#B5C689 ");
//                   Login_column.getStyleClass().clear();
//                   mdp_column.getStyleClass().clear();
//              
//            }
//                break ;
//            case "login" :{
//                      id_comlun.getStyleClass().clear();
//                    nom_column.getStyleClass().clear();
//                    prenom_column.getStyleClass().clear();
//                   Login_column.setStyle("-fx-background-color:#B5C689 ");
//                      mdp_column.getStyleClass().clear();
//               
//            }
//                break ;
//            case "motdepasse":{
//                   id_comlun.getStyleClass().clear();
//                   nom_column.getStyleClass().clear();
//                   prenom_column.getStyleClass().clear();
//                   Login_column.getStyleClass().clear();
//                   mdp_column.setStyle("-fx-background-color:#B5C689 ");
//                
//            }
//            break ;
//            case "All" :{
//                   id_comlun.getStyleClass().clear();
//                   nom_column.getStyleClass().clear();
//                   prenom_column.getStyleClass().clear();
//                   Login_column.getStyleClass().clear();
//                    mdp_column.getStyleClass().clear();
//                    mdp_column.getStyleClass().addAll("table-column");
//            } 
//            break ;
//                
//                
//        }
//                
//                
//    }
//        private void init_node()
//    {
//            Hide= new ScaleTransition();
//            Show= new ScaleTransition();
//            ge= new GestionnaireEvenementGroup();
//            Hide.setNode(operation_grid );
//            Show.setNode(operation_grid );            
//            Show.setFromY(0);
//            Show.setToY(1);
//            Hide.setFromY(1);
//            Hide.setToY(0);
//            Hide.play();
//            
//            target_column.getItems().addAll("All","id","nom","prenom","login","motdepasse");
//            target_column.getSelectionModel().select(0);
//           
//            lignes_page_cb.getItems().addAll(5,10,20,50,100);
//             lignes_page_cb.getSelectionModel().select(0);
//            StartPoint=0;
//            BreakPoint=5;
//            current_page=1;
//           
//        selected_column="All";
//        Modifier= new ButtonType("Modifier",ButtonBar.ButtonData.OK_DONE);
//        Supprimer=new ButtonType("Supprimer",ButtonBar.ButtonData.OK_DONE);
//        Annuler=new ButtonType("Annuler",ButtonBar.ButtonData.CANCEL_CLOSE);
//        Oui = new ButtonType("Oui",ButtonBar.ButtonData.OK_DONE);
//        Non= new ButtonType("Non",ButtonBar.ButtonData.CANCEL_CLOSE);
//        NextActionWindow= new Alert(Alert.AlertType.CONFIRMATION);
//        ConfirmDelete=new Alert(Alert.AlertType.CONFIRMATION);
//        
//        NextActionWindow.getButtonTypes().clear();
//        NextActionWindow.getButtonTypes().addAll(Modifier,Supprimer,Annuler);
//        NextActionWindow.setTitle("MySoulMate");
//        NextActionWindow.setHeaderText("Gestion Administrateur");
//        NextActionWindow.setContentText("Que Voulez vous faire avec cet Administrateur ?");
//        ConfirmDelete.getButtonTypes().clear();
//        ConfirmDelete.getButtonTypes().addAll(Oui,Non);
//        ConfirmDelete.setTitle("MySoulMate");
//        ConfirmDelete.setHeaderText("Gestion Administrateur");
//        ConfirmDelete.setContentText("Voulez Vous Vraiment Supprimer cet Administrateur ?");
//        
//    }
//        private void init_Actions()
//        {
//            target_column.valueProperty().addListener(( SelectedValue) -> {   selected_column=((ObservableValue<String>)SelectedValue).getValue();reload_data(); color_column(selected_column);});
//            lignes_page_cb.valueProperty().addListener(( SelectedValue) -> {   BreakPoint=(((ObservableValue<Integer>)SelectedValue).getValue());reload_data(); });
//        }
}
