/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsof;

import Entites.User.Client;
//import VIEWS.Profil.Ui_ajout_new_actualiteController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Sofiene
 */
public class Test_graphique extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


       Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/Profil/ui_FO_AjouterCaracteristique.fxml"));
       Scene sene = new Scene(root);
       primaryStage.setScene(sene);
       primaryStage.show();
    }
    
}
