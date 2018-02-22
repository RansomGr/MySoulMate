/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.User;

import Entites.User.Client;
import Services.User.GestionnaireClient;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_Login_FOController implements Initializable {

    @FXML
    private Hyperlink create_new_account_hl;
    @FXML
    private Hyperlink dashboard_scene_hl;
    @FXML
    private TextField login_te;
    @FXML
    private TextField password_te;
    @FXML
    private Button login_pb;
    @FXML
    private Hyperlink forgot_hl;
    @FXML
    private BorderPane BorderPane;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void Forgot_page(ActionEvent event) throws IOException
    {
     Stage PrimaryStage=MySoulMate.getMainStage();
     Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Forgot_FO.fxml"));
     Scene scene = new Scene(root);
     PrimaryStage.setScene(scene);
     PrimaryStage.show();
    }
        @FXML
    private void DashBoard_page(ActionEvent event)throws IOException
    {
     Stage PrimaryStage=MySoulMate.getMainStage();
     Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Login_BO.fxml"));
     Scene scene = new Scene(root);
     PrimaryStage.setScene(scene);
     PrimaryStage.show();
    }
    @FXML
    private void NewAccount_page(ActionEvent event)throws IOException
    {
     Stage PrimaryStage=MySoulMate.getMainStage();
     Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Create_new_FO.fxml"));
     Scene scene = new Scene(root);
     PrimaryStage.setScene(scene);
     PrimaryStage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          int numberOfSquares =60;
        while (numberOfSquares > 0){
            generateAnimation();
            numberOfSquares--;
        }
    }    

    @FXML
    private void log_me_in(ActionEvent event) throws IOException, SQLException {
        GestionnaireClient gc = new GestionnaireClient();
        Client Logged_in_Client= ((List<Client>)gc.fetchAll()).stream().filter(Client->Client.getPseudo().equals(login_te.getText())&&Client.getMotdepasse().equals(password_te.getText())).findFirst().get();
        if(Logged_in_Client!=null)
        {
            GestionnaireClient p = new GestionnaireClient();
            
            if(Logged_in_Client.getProfil().getPhoto().equals("no_PROF"))
            {
              MySoulMate.setLogged_in_Client(Logged_in_Client);
              Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/Profil/ui_Profile_Creation.fxml"));
              Scene scene = new Scene(root);
              MySoulMate.getMainStage().setScene(scene); 
            }
            else
            {
              MySoulMate.setLogged_in_Client(Logged_in_Client);
              Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/ui_MainFrame_FO.fxml"));
              Scene scene = new Scene(root);
              MySoulMate.getMainStage().setScene(scene);
            }
        }
        else
        {
            password_te.clear();
            login_te.clear();
        }
   }
    public void generateAnimation(){
        Random rand = new Random();
        int sizeOfSqaure = rand.nextInt(50) + 1;
        int speedOfSqaure = rand.nextInt(10) + 5;
        int startXPoint = rand.nextInt(1024);
        int startYPoint = rand.nextInt(720);
        int direction = rand.nextInt(5) + 1;

        KeyValue moveXAxis = null;
        KeyValue moveYAxis = null;
        ImageView r1 = null;

        switch (direction){
            case 1 :
                // MOVE LEFT TO RIGHT
                r1 = new ImageView(new Image("/images/heart.png"));
                r1.setFitHeight(sizeOfSqaure);
                r1.setFitWidth(sizeOfSqaure);
                r1.setX(startXPoint);
                r1.setY(0);
                moveXAxis = new KeyValue(r1.xProperty(), 720 -  sizeOfSqaure);
                break;
            case 2 :
                // MOVE TOP TO BOTTOM
                 r1 = new ImageView(new Image("/images/heart.png"));
                r1.setFitHeight(sizeOfSqaure);
                r1.setFitWidth(sizeOfSqaure);
                r1.setX(startXPoint);
                r1.setY(0);
                moveYAxis = new KeyValue(r1.yProperty(), 1024 - sizeOfSqaure);
                break;
            case 3 :
                // MOVE LEFT TO RIGHT, TOP TO BOTTOM
                 r1 = new ImageView(new Image("/images/heart.png"));
                r1.setFitHeight(sizeOfSqaure);
                r1.setFitWidth(sizeOfSqaure);
                r1.setX(startXPoint);
                r1.setY(0);
                moveXAxis = new KeyValue(r1.xProperty(), 720 -  sizeOfSqaure);
                moveYAxis = new KeyValue(r1.yProperty(), 1024 - sizeOfSqaure);
                break;
            case 4 :
                // MOVE BOTTOM TO TOP
                r1 = new ImageView(new Image("/images/heart.png"));
                r1.setFitHeight(sizeOfSqaure);
                r1.setFitWidth(sizeOfSqaure);
                r1.setX(startXPoint);
                r1.setY(0);
                moveYAxis = new KeyValue(r1.xProperty(), 0);
                break;
            case 5 :
                // MOVE RIGHT TO LEFT
                r1 = new ImageView(new Image("/images/heart.png"));
                r1.setFitHeight(sizeOfSqaure);
                r1.setFitWidth(sizeOfSqaure);
                r1.setX(startXPoint);
                r1.setY(0);
                moveXAxis = new KeyValue(r1.xProperty(), 0);
                break;
            case 6 :
                //MOVE RIGHT TO LEFT, BOTTOM TO TOP
               r1 = new ImageView(new Image("/images/heart.png"));
                r1.setFitHeight(sizeOfSqaure);
                r1.setFitWidth(sizeOfSqaure);
                r1.setX(startXPoint);
                r1.setY(0);
                moveXAxis = new KeyValue(r1.xProperty(), 720 -  sizeOfSqaure);
                moveYAxis = new KeyValue(r1.yProperty(), 1024 - sizeOfSqaure);
                break;

            default:
                System.out.println("default");
        }

       // r1.setFill(Color.web("#F89406"));
        r1.setOpacity(0.5);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(speedOfSqaure * 1000), moveXAxis, moveYAxis);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        BorderPane.getChildren().add(BorderPane.getChildren().size()-1,r1);
    }
}
