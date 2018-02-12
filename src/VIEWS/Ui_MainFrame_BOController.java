/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition; 
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_MainFrame_BOController implements Initializable {
    boolean Shown;
    private final  Stage MainStage =MySoulMate.getMainStage();
    @FXML
    private MenuButton Home_dl;
    @FXML
    private MenuButton User_dl;
    @FXML
    private MenuButton Event_dl;
    @FXML
    private MenuButton Plan_dl;
    @FXML
    private MenuButton Profile_dl;
    @FXML
    private MenuButton Match_dl;
    @FXML
    private MenuButton Relations_dl;
    @FXML
    private Button Logout_pb;
    @FXML
    private Button Hide_Show_pb;
    @FXML
    private GridPane Side_bar;
    @FXML
    private GridPane Content_grid;
    @FXML
    private Label Label_Module_name;
    @FXML
    private StackPane Page_Viewer;

    /**
     * Initializes the controller class.
     */
    private void init_DashBoard_Actions()
    {
             this.Home_dl.getItems().remove(0, 2);
             MenuItem Dash=new MenuItem("Dashboard");
             MenuItem Stat =new MenuItem("Statistique Global");
             Dash.setOnAction(
                     (a)->{
                               System.out.println("DashClicked");
                              }
               );

                Stat.setOnAction(
                       (a)->{
                                  System.out.println("Stat clicked");
                               }
                );
             
                this.Home_dl.getItems().addAll(Dash,Stat);
     
    }
    private void init_User_Actions()
    {
        MenuItem ListAdmin  = new MenuItem("Liste Administrateurs");
        MenuItem NewAdmin  = new MenuItem("Ajouter Nouveau  Administrateur");
        MenuItem ListClient  = new MenuItem("Liste Clients");
        
        this.User_dl.getItems().remove(0, 2);        
        this.User_dl.getItems().addAll(ListAdmin,NewAdmin,ListClient);
        
        ListAdmin.setOnAction(
                (a)->{
                     try { 
                  Node root;// Making a node
                  root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_ListeAdmin_BO.fxml"));// Getting the View
                  Page_Viewer.getChildren().clear();
                  Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane
                  Label_Module_name.setText(ListAdmin.getText()); // Changing the header text
            } catch (IOException ex) {
                Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
        );
        
        NewAdmin.setOnAction(
        (a)->{
           
            try { 
                  Node root;// Making a node
                  root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Create_new_Admin_BO.fxml"));// Getting the View
                 Page_Viewer.getChildren().clear();
                 Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane
                  
                  Label_Module_name.setText(NewAdmin.getText()); // Changing the header text
            } catch (IOException ex) {
                Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
            }
      
        }         
        );
        ListClient.setOnAction(
        (a)->{
            System.out.println("List Client Clicked");
        }
        );
        
                
                
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Shown=true; // if side bar is shown init it because naturally it's shown
       init_DashBoard_Actions();
       init_User_Actions();
    }    

    @FXML
    private void hide_show_menu_pb(ActionEvent event) {
        int AnimationDuration=400; // ms

        if(Shown)
        {
            
        ScaleTransition Hide = new ScaleTransition();
        TranslateTransition Move_left= new TranslateTransition();
        TranslateTransition Move_leftGrid =new TranslateTransition();
       
        
       
        Move_leftGrid.setDuration(Duration.millis(AnimationDuration));
        Move_left.setDuration(Duration.millis(AnimationDuration));
        Hide.setDuration(Duration.millis(AnimationDuration));
        
        
        
        Move_leftGrid.setNode(Content_grid);// setting Target of animation
        Hide.setNode(Side_bar);
        Move_left.setNode(Side_bar);
        
        Move_leftGrid.setToX(-175);        
        Move_left.setToX(-175);
        Hide.setToX(0); 
        
        ParallelTransition GroupAnimation = new ParallelTransition(Hide,Move_left,Move_leftGrid);
     
     
        GroupAnimation.play();
      GroupAnimation.setOnFinished((ActionEvent ev)->{
            if(Hide_Show_pb.getText().equals("<-"))
            {
                Move_leftGrid.setFromX(0);
                Move_leftGrid.setToX(0);
                GroupAnimation.play();
                 Hide_Show_pb.setText("->");
                 Side_bar.maxWidthProperty().set(0);
                 Shown=false;
            }
           
        });
        
        }
        else// hidden
        {
        ScaleTransition Show = new ScaleTransition();
        TranslateTransition Move_right= new TranslateTransition();
         TranslateTransition Move_rightGrid =new TranslateTransition();
        
       
        Move_rightGrid.setDuration(Duration.millis(AnimationDuration));
        Move_right.setDuration(Duration.millis(AnimationDuration));
        Show.setDuration(Duration.millis(AnimationDuration));
      
        
        Move_rightGrid.setNode(Content_grid);// setting Target of animation
        Show.setNode(Side_bar);
        Move_right.setNode(Side_bar);
       
        Move_rightGrid.setFromX(-175);
        Move_rightGrid.setToX(0);
        Move_right.setFromX(-175);
        Move_right.setToX(0);
        Show.setFromX(0);
        Show.setToX(1);
        ParallelTransition GroupAnimation = new ParallelTransition(Show,Move_right,Move_rightGrid);
   
        GroupAnimation.play();
        Side_bar.setMaxWidth(175);
          GroupAnimation.setOnFinished((ActionEvent ev)->{
            if(Hide_Show_pb.getText().equals("->"))
            {
            Hide_Show_pb.setText("<-");
              Shown=true;
            }
        });
  
      
        }
    
        
    }
    
}
