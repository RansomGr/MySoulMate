///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package VIEWS.Plan;
//
//import Entites.Plan.Avis;
//import Services.Plan.GestionnaireAvis;
//import VIEWS.Ui_MainFrame_BOController;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Optional;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.animation.ScaleTransition;
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonBar;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.CheckBox;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableRow;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.ColumnConstraints;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.RowConstraints;
//
///**
// * FXML Controller class
// *
// * @author irou
// */
//public class Ui_ListeAvis_BOController implements Initializable {
//    
//      private GestionnaireAvis Ga ;
//    private ScaleTransition Hide;
//    private  ScaleTransition Show;
//    private int StartPoint;
//    private  int BreakPoint;
//    private int pages;
//    private  int current_page;
//    private String selected_column;
//    private  ObservableList<Avis> Avis;
//       private Alert NextActionWindow;
//    private Alert ConfirmDelete;
//   
//    private ButtonType  Supprimer;
//    private ButtonType Annuler;
//    private ButtonType Oui;
//    private ButtonType Non;
//
//    @FXML
//    private RowConstraints operation_row;
//    @FXML
//    private ColumnConstraints header_grid;
//    @FXML
//    private TextField recherche_dyn_tf;
//    @FXML
//    private CheckBox operations_plus;
//    @FXML
//    private GridPane operation_grid;
//    @FXML
//    private ComboBox<String> target_column;
//    @FXML
//    private Button precedent_pb;
//    @FXML
//    private Button suivant_pb;
//    @FXML
//    private TextField current_page_te;
//    @FXML
//    private ComboBox<Integer> lignes_page_cb;
//    @FXML
//    private TableView<Avis> table_view;
//    @FXML
//    private TableColumn<Avis, Integer> id_comlun;
//    @FXML
//    private TableColumn<Avis, String> nomPlan_column;
//    @FXML
//    private TableColumn<Avis, String> nomClient_column;
//    @FXML
//    private TableColumn<Avis, String> Commentaire_column;
//    @FXML
//    private TableColumn<Avis, Integer> Note_column;
//    @FXML
//    private TableColumn<Avis, String> Date_column;
//
//    /**
//     * Initializes the controller class.
//     */
//   @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        init_node();
//        init_Actions();
//        init_tableView();
//        reload_data();
//    }    
//
//    @FXML
//    private void recherche_dyn_textchanged(KeyEvent event)  {
//            reload_data();    
//    }
//
//    @FXML
//    private void Operation_clicked(ActionEvent event) {
//        if(operations_plus.isSelected())
//            Show.play();
//        else
//            Hide.play();   
//              reload_data();
//    }
//    private void init_tableView()
//    {
//           // id_comlun.setCellValueFactory((TableColumn.CellDataFeatures<Avis,Integer> avis) -> new  SimpleIntegerProperty((avis.getValue().getID())).asObject());
//            nomPlan_column.setCellValueFactory((TableColumn.CellDataFeatures<Avis, String> avis) -> new SimpleStringProperty(avis.getValue().getPlan().getNom())   );
//            nomClient_column.setCellValueFactory((TableColumn.CellDataFeatures<Avis,String>avis)-> new SimpleStringProperty(avis.getValue().getClient().getNom()) );
//            Commentaire_column.setCellValueFactory((TableColumn.CellDataFeatures<Avis,String>avis)->new SimpleStringProperty(avis.getValue().getCommentaire()));
//            Note_column.setCellValueFactory((TableColumn.CellDataFeatures<Avis,Integer>avis)->new SimpleIntegerProperty((avis.getValue().getNote())).asObject());
//            Date_column.setCellValueFactory((TableColumn.CellDataFeatures<Avis,String>avis)-> new SimpleStringProperty(avis.getValue().getDateh().toString()));
//            
//            table_view.setRowFactory( tv -> {
//            TableRow<Avis> row = new TableRow<>();
//            row.setOnMouseClicked(e -> {
//             if (e.getClickCount() == 2 && (!row.isEmpty()) ) {
//                 //avis_FO.setAvis_to_be_modified(row.getItem());
//                 try {
//                      Optional<ButtonType> result = NextActionWindow.showAndWait();
//                     
//                      if(result.isPresent()&&result.get()==Supprimer)
//                     {
//                            Optional<ButtonType> result_del = ConfirmDelete.showAndWait();
//                            if(result_del.isPresent()&&result_del.get()==Oui)
//                            {
//                                Ga.remove(row.getItem());
//                                reload_data();
//                            }
//                     }
//                 } catch (SQLException ex) {
//                     Logger.getLogger(Ui_ListePlan_BOController.class.getName()).log(Level.SEVERE, null, ex);
//                 }
//                 }
//                 });
//                return row;
//                });
//    
//                
//  
//    }
//    
//    
//   
//       
//    
//    private void reload_data() 
//    {  int All_row_size;
//         try {
//    if(operations_plus.isSelected())
//    { 
//            All_row_size=Ga.fetchAll().size();
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
//                 Avis= FXCollections.observableArrayList((ArrayList<Avis>)Ga.fetchAll(recherche_dyn_tf.getText(),(current_page-1)*BreakPoint,BreakPoint));
//            else 
//            {
//             //    Avis= FXCollections.observableArrayList((ArrayList<Avis>)Ga.fetchAll(recherche_dyn_tf.getText(),selected_column,(current_page-1)*BreakPoint,BreakPoint));
//            }
//    }
//    else
//    {
//             Avis= FXCollections.observableArrayList((ArrayList<Avis>)Ga.fetchAll(recherche_dyn_tf.getText(),-1,"DESC"));
//           
//    }
//      
//         } catch (SQLException ex) {
//                Logger.getLogger(Ui_ListeAvis_BOController.class.getName()).log(Level.SEVERE, null, ex);
//            } 
//          
//           table_view.setItems(Avis);
//    }
//    private void color_column(String target)
//    {
//        System.out.println(target);
//        switch (target){
//            case "id":{
//                   id_comlun.setStyle("-fx-background-color:#B5C689 ");
//                    nomClient_column.getStyleClass().clear();
//                
//            }
//                break ;
//            case "nom" :{
//                     id_comlun.getStyleClass().clear();
//                   nomClient_column.setStyle("-fx-background-color:#B5C689 ");
//                 nomPlan_column.getStyleClass().clear();
//            }
//                break ;
//            case "Type" :{
//                     id_comlun.getStyleClass().clear();
//                    nomClient_column.getStyleClass().clear();
//                    nomPlan_column.setStyle("-fx-background-color:#B5C689 ");
//                    
//                
//              
//            }
//             
//             
//                   
//                
//            
//            break ;
//            case "All" :{
//                   id_comlun.getStyleClass().clear();
//                   nomClient_column.getStyleClass().clear();
//                   nomPlan_column.getStyleClass().clear();
//                
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
//            Ga= new GestionnaireAvis();
//            Hide.setNode(operation_grid );
//            Show.setNode(operation_grid );            
//            Show.setFromY(0);
//            Show.setToY(1);
//            Hide.setFromY(1);
//            Hide.setToY(0);
//            Hide.play();
//            
//           target_column.getItems().addAll("All","nom Plan","nom client","commentaire","note","date"); 
//            target_column.getSelectionModel().select(0);
//           
//            lignes_page_cb.getItems().addAll(5,10,20,50,100);
//            lignes_page_cb.getSelectionModel().select(0);
//            StartPoint=0;
//            BreakPoint=5;
//            current_page=1;
//           
//            selected_column="All";
//        
//        Supprimer=new ButtonType("Supprimer",ButtonBar.ButtonData.OK_DONE);
//        Annuler=new ButtonType("Annuler",ButtonBar.ButtonData.CANCEL_CLOSE);
//        Oui = new ButtonType("Oui",ButtonBar.ButtonData.OK_DONE);
//        Non= new ButtonType("Non",ButtonBar.ButtonData.CANCEL_CLOSE);
//        NextActionWindow= new Alert(Alert.AlertType.CONFIRMATION);
//        ConfirmDelete=new Alert(Alert.AlertType.CONFIRMATION);
//        
//        NextActionWindow.getButtonTypes().clear();
//        NextActionWindow.getButtonTypes().addAll(Supprimer,Annuler);
//        NextActionWindow.setTitle("MySoulMate");
//        NextActionWindow.setHeaderText("Gestion Avis");
//        NextActionWindow.setContentText("Que Voulez vous faire avec cet Avis");
//        ConfirmDelete.getButtonTypes().clear();
//        ConfirmDelete.getButtonTypes().addAll(Oui,Non);
//        ConfirmDelete.setTitle("MySoulMate");
//        ConfirmDelete.setHeaderText("Gestion Avis");
//        ConfirmDelete.setContentText("Voulez Vous Vraiment Supprimer cet Avis ?");
//        
//    }
//        private void init_Actions()
//        {
//           target_column.valueProperty().addListener(( SelectedValue) -> {   selected_column=((ObservableValue<String>)SelectedValue).getValue();reload_data(); color_column(selected_column);});
//       lignes_page_cb.valueProperty().addListener(( SelectedValue) -> {   BreakPoint=(((ObservableValue<Integer>)SelectedValue).getValue());reload_data(); });
//        }
//
//    @FXML
//    private void previous_page(ActionEvent event) {
//        if(current_page>1)
//        {
//            current_page--;
//            reload_data();
//        }
//    }
//
//    @FXML
//    private void next_page(ActionEvent event) {
//           if(pages>current_page)
//           {
//            current_page++;
//            reload_data();
//           }
//    }
//
//
//}
