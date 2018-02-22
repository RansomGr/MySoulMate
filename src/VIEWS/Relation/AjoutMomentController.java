package VIEWS.Relation;

import Entites.Relation.Contenue_Moment;
import Services.Relation.GestionnaireContenue_Moment;
import VIEWS.Ui_MainFrame_BOController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import mysoulmate.MySoulMate;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class AjoutMomentController implements Initializable {
            Map<TextField,String>champs;

	@FXML
	private TextField nom;
	@FXML
	private TextField des;
	@FXML
	private DatePicker date;
	@FXML
	private TextField photo;
	@FXML
	private Button annbtn;
	
	
        @Override
    public void initialize(URL location, ResourceBundle resources) {
        TrayNotification tray = new TrayNotification("Notification !", "Ajouter des moments pour gagner des points !!", NotificationType.SUCCESS);
        tray.showAndDismiss(javafx.util.Duration.seconds(2));
    }


            @FXML
            public void AjouterMoment() throws IOException
{
	 GestionnaireContenue_Moment g=new GestionnaireContenue_Moment();
        Contenue_Moment cm=new Contenue_Moment(nom.getText() ,des.getText(),Date.valueOf(date.getValue()),photo.getText());
     
        try {
            g.create(cm);
          /*  FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutMoment.fxml"));
            Parent root = loader.load();*/
   //???
          /*  FXMLResultController frc = loader.getController();
            frc.setResNom(cm.getNom());
            frc.setResPrenom(tfPrenom.getText());
            
            tfNom.getScene().setRoot(root);*/
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
}
 /* public void SelectPhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Choisir Photo Plan");
FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Ficheer Images  (*.jpeg)", "*.jpeg");
fileChooser.getExtensionFilters().add(extFilter);
fileChooser.setInitialDirectory(new File("images/"));
File img=fileChooser.showOpenDialog(MySoulMate.getMainStage());
photo.setText(img.getAbsolutePath());
    
               
}*/
            
            @FXML
            public void annuler(ActionEvent e)
{
    //((Stage) annbtn.getScene().getWindow()).close();
    champs.keySet().forEach(x->x.clear());
}

            public void AfficherMoment(ActionEvent e){
                    try { 
                                   Node root;// Making a node
                  root = FXMLLoader.load(getClass().getResource("ui_ListeMoment_FO.fxml"));// Getting the View
                                   } catch (IOException ex) {
                Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }
