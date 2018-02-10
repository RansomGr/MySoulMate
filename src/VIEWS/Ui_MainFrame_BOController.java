/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_MainFrame_BOController implements Initializable {
    boolean Shown;
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
                    System.out.println("List Admin Clicked");
                }
        );
        
        NewAdmin.setOnAction(
        (a)->{
            System.out.println("New Admin Clicked");
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
        
        Move_leftGrid.setToX(-190);
        Move_left.setToX(-190);
        Hide.setToX(0);
        ParallelTransition GroupAnimation = new ParallelTransition(Hide,Move_left,Move_leftGrid);
     
     
        GroupAnimation.play();
        Shown=false;
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
        
        Move_rightGrid.setToX(0);
        Move_right.setToX(0);
        Show.setToX(1);
        ParallelTransition GroupAnimation = new ParallelTransition(Show,Move_right,Move_rightGrid);
     
     
        GroupAnimation.play();
        Shown=true;
        }
    
        
    }
    
}
