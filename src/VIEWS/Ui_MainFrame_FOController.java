/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS;

import VIEWS.Profil.Ui_Profil_FOController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_MainFrame_FOController implements Initializable {
private int AnimationDuration;
    @FXML
    private Hyperlink create_new_account_hl;
    @FXML
    private AnchorPane connected_friends_scroll_pane;
    @FXML
    private StackPane Content_pane;
    @FXML
    private AnchorPane bonplans_and_evennements;
    private boolean Shown;
    @FXML
    private GridPane menu_bar;
     @FXML
    private Button hide_menu;
    @FXML
    private Button show_menu;
    @FXML
    private Button menu_matching_btn;
    
    private static StackPane Content_pane_static ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
Shown=true;
AnimationDuration=400;//ms
Content_pane_static = Content_pane;
    }    

    @FXML
    private void load_my_profil(ActionEvent event) throws IOException {
         Ui_Profil_FOController.setProfile_owner(MySoulMate.getLogged_in_Client());
         Node root = FXMLLoader.load(getClass().getResource("/VIEWS/Profil/ui_Profil_FO.fxml"));
         Content_pane.getChildren().clear();
         Content_pane.getChildren().add(root);    
    }
    @FXML
    private void logout(ActionEvent event) throws IOException {
         MySoulMate.setLogged_in_Client(null);
         Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Login_FO.fxml"));
         Scene sene = new Scene(root);
         MySoulMate.getMainStage().setScene(sene);
    }



    @FXML
    private void hide_menu(ActionEvent event) {
            if(Shown)
        {
            
        ScaleTransition Hide = new ScaleTransition();
        TranslateTransition Move_Right= new TranslateTransition();
        TranslateTransition Move_RightGrid =new TranslateTransition();
       TranslateTransition GoUp = new TranslateTransition();
       FadeTransition Appear = new FadeTransition();
       //70
        
       
        Move_RightGrid.setDuration(Duration.millis(AnimationDuration));
        Move_Right.setDuration(Duration.millis(AnimationDuration));
        Hide.setDuration(Duration.millis(AnimationDuration));
        GoUp.setDuration(Duration.millis(AnimationDuration));
        Appear.setDuration(Duration.millis(AnimationDuration));
        
        
        Move_RightGrid.setNode(Content_pane);// setting Target of animation
        Hide.setNode(menu_bar);
        Move_Right.setNode(menu_bar);
        GoUp.setNode(show_menu);
        Appear.setNode(show_menu);
        
        Move_RightGrid.setToX(230);        
        Move_Right.setToX(230);
        Hide.setToX(0); 
        GoUp.setFromY(32);
        GoUp.setToY(0);
        Appear.setFromValue(0);
        Appear.setToValue(1);
        
        ParallelTransition GroupAnimation = new ParallelTransition(Hide,Move_Right,Move_RightGrid,GoUp,Appear);
     
     
      GroupAnimation.play();
      GroupAnimation.setOnFinished((ActionEvent ev)->{
            if(hide_menu.getText().equals("Menu>"))
            {
                GroupAnimation.getChildren().remove(GoUp);
                GroupAnimation.getChildren().remove(Appear);
                Move_RightGrid.setFromX(0);
                Move_RightGrid.setToX(0);
                GroupAnimation.play();
                 hide_menu.setText("<Menu");
                 menu_bar.maxWidthProperty().set(0);
                 Shown=false;
            }
           
        });
        
        } 
      
    }

    @FXML
    private void show_menu(ActionEvent event) {
          if(!Shown)// hidden
        {
        ScaleTransition Show = new ScaleTransition();
        TranslateTransition Move_Left= new TranslateTransition();
         TranslateTransition Move_LeftGrid =new TranslateTransition();
        TranslateTransition GoDown = new TranslateTransition();
        FadeTransition Dissapear = new FadeTransition();
        Move_LeftGrid.setDuration(Duration.millis(AnimationDuration));
        Move_Left.setDuration(Duration.millis(AnimationDuration));
        Show.setDuration(Duration.millis(AnimationDuration));
        GoDown.setDuration(Duration.millis(AnimationDuration));
        Dissapear.setDuration(Duration.millis(AnimationDuration));

        Move_LeftGrid.setNode(Content_pane);// setting Target of animation
        Show.setNode(menu_bar);
        Move_Left.setNode(menu_bar);
        GoDown.setNode(show_menu);
        Dissapear.setNode(show_menu);
       
        Move_LeftGrid.setFromX(230);
        Move_LeftGrid.setToX(0);
        Move_Left.setFromX(230);
        Move_Left.setToX(0);
        Show.setFromX(0);
        Show.setToX(1);
        GoDown.setFromY(0);
        GoDown.setToY(32);
        Dissapear.setFromValue(1);
        Dissapear.setToValue(0);
        ParallelTransition GroupAnimation = new ParallelTransition(Show,Move_Left,Move_LeftGrid,GoDown,Dissapear);
   
        GroupAnimation.play();
        menu_bar.setMaxWidth(230);
          GroupAnimation.setOnFinished((ActionEvent ev)->{
            if(hide_menu.getText().equals("<Menu"))
            {
                GroupAnimation.getChildren().remove(GoDown);
                GroupAnimation.getChildren().remove(Dissapear);
            hide_menu.setText("Menu>");
              Shown=true;
            }
        });
  
      
        }
       
    
    }

    @FXML
    private void load_matching_page(ActionEvent event) throws IOException {
         Ui_Profil_FOController.setProfile_owner(MySoulMate.getLogged_in_Client());
         
         Node root = FXMLLoader.load(getClass().getResource("/VIEWS/Matching/ui_FO_RechercheMatchings.fxml"));
         Content_pane.getChildren().clear();
         Content_pane.getChildren().add(root);  
    }
    
    @FXML
    public static void load_preference_page(ActionEvent event) throws IOException {
         Ui_Profil_FOController.setProfile_owner(MySoulMate.getLogged_in_Client());
         Node root = FXMLLoader.load(Ui_MainFrame_FOController.class.getResource("/VIEWS/Matching/ui_FO_AjouterPreference.fxml"));
         Content_pane_static.getChildren().clear();
         Content_pane_static.getChildren().add(root);  
    }
    
}
