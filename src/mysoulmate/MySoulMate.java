/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysoulmate;

import Entites.User.Admin;
import Entites.User.Client;
import Listner.Listener;
import VIEWS.Ui_MainFrame_FOController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Ransom
 */
public class MySoulMate extends Application {
    private static Stage MainStage;
    private static Admin Logged_in_Admin;
    private static Client Logged_in_Client;
    private static Ui_MainFrame_FOController MainController;
    private static Listener listener;
    private static Thread  client_thread;
    public static Listener getListener() {
        return listener;
    }

    public static void setListener(Listener listener) {
        MySoulMate.listener = listener;
            client_thread  =new Thread(listener);
            client_thread.start();
    }

    public static Thread getClient_thread() {
        return client_thread;
    }

    public static void setClient_thread(Thread client_thread) {
        MySoulMate.client_thread = client_thread;
    }
     
    public static Ui_MainFrame_FOController getMainController() {
        return MainController;
    }

    public static void setMainController(Ui_MainFrame_FOController MainController) {
        MySoulMate.MainController = MainController;
    }

    public static void setLogged_in_Admin(Admin Logged_in_Admin) {
        MySoulMate.Logged_in_Admin = Logged_in_Admin;
    }

    public static void setLogged_in_Client(Client Logged_in_Client) {
        MySoulMate.Logged_in_Client = Logged_in_Client;
    }

    public static Admin getLogged_in_Admin() {
        return Logged_in_Admin;
    }

    public static Client getLogged_in_Client() {
        return Logged_in_Client;
    }

    public static Stage getMainStage() {
        return MainStage;
    }
     
    @Override
    public void start(Stage stage) throws Exception {
        MySoulMate.MainStage=stage;
        Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Login_FO.fxml"));
        Scene scene = new Scene(root);
       
       
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest((WindowEvent e)->{
               Platform.exit();
                System.exit(0);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
