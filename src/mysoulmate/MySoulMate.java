/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysoulmate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ransom
 */
public class MySoulMate extends Application {
    private static Stage MainStage;

    public static Stage getMainStage() {
        return MainStage;
    }
     
    @Override
    public void start(Stage stage) throws Exception {
        MySoulMate.MainStage=stage;
        Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/ui_MainFrame_BO.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
