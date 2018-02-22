package VIEWS.Relation;

import Entites.Relation.Conseil;
import Services.Relation.GestionnaireConseil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class AjoutConseilController implements Initializable {
	
	
    @FXML
    private TextArea con;

    @FXML
    private ComboBox<Integer> niv;
    
  @FXML
    private Button ajbtn;

    @FXML
    private Button annbtn;

	@Override
    public void initialize(URL location, ResourceBundle resources) {
          
        niv.getItems().addAll(1,2,3);
    }
    @FXML
	public void AjouterConseil(ActionEvent e) throws IOException
	{
		
	 GestionnaireConseil g=new GestionnaireConseil();
         Conseil c=new Conseil(con.getText(),niv.getValue());
     
        try {
            g.create(c);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutConseil.fxml"));
            Parent root = loader.load();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        }
    @FXML
	public void annuler()
	{
	    ((Stage) annbtn.getScene().getWindow()).close();

	}

    
	
}
