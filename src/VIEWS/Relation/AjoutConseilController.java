package VIEWS.Relation;

import Entites.Relation.Conseil;
import Services.Relation.GestionnaireConseil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
//import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class AjoutConseilController implements Initializable {
	
  @FXML
    private TextArea con;

    @FXML
    private TextField titre;

    @FXML
    private Button ajbtn;

    @FXML
    private Button annbtn;

     private String OperationMode;
   private String Message_Warning;
    private Alert InformationWindow;
   private Alert ErrorWindow;
   private Alert WarningWindow;
   private Alert ConfirmWindow;
   Map<TextField,String>Field1;
   Map<TextArea,String>Field2;
	@Override
    public void initialize(URL location, ResourceBundle resources) {
          OperationMode="Ajouter";
        InformationWindow   =new Alert(Alert.AlertType.INFORMATION);
        WarningWindow = new Alert(Alert.AlertType.WARNING);
        ErrorWindow =new Alert(Alert.AlertType.ERROR);
        ConfirmWindow = new Alert(Alert.AlertType.CONFIRMATION);  
        InformationWindow.setContentText("Conseil Ajouté Avec succès !");
        InformationWindow.setHeaderText("Gestion Conseil");
        InformationWindow.setTitle("MySoulMate");
        ErrorWindow.setContentText("La base de données n'est pas accessible !");
        ErrorWindow.setHeaderText("Gestion Conseil");
        ErrorWindow.setTitle("MySoulMate");
        WarningWindow.setContentText(Message_Warning);
        WarningWindow.setHeaderText("Gestion Conseil");
        WarningWindow.setTitle("MySoulMate");
        fill_Nodes();
    }
     private void fill_Nodes()
        {
            Field1= new HashMap<>();
            Field1.put(titre,"Titre");
             Field2= new HashMap<>();
            Field2.put(con,"Contenu");
             
        }
    @FXML
	public void AjouterConseil(ActionEvent e) throws IOException, SQLException
	{
            validate_form();
        if(Message_Warning.equals("Les champs suivants posent des problèmes \n"))
        {
             GestionnaireConseil ga= new GestionnaireConseil();
      
       if( ga.create(new Conseil(titre.getText(),con.getText()))==1)
       {
           InformationWindow.show();
           clear_tf();
       }
        } 
       else
    {
        WarningWindow.show();
    }
        
            
            /*
	 GestionnaireConseil g=new GestionnaireConseil();
         Conseil c=new Conseil(con.getText(),niv.getValue());
     
        try {
            g.create(c);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutConseil.fxml"));
            Parent root = loader.load();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }*/
        }
    @FXML
	public void annuler()
	{
	    ((Stage) annbtn.getScene().getWindow()).close();

	}

    
	private void validate_form()
    {
       Message_Warning="Les champs suivants posent des problèmes \n";
        Field1.entrySet().forEach(x->{
            if(x.getKey().getText().isEmpty())
                Message_Warning+="Le champ : "+x.getValue()+" est vide !\n";
        });
                      WarningWindow.setContentText(Message_Warning);
         Field2.entrySet().forEach(x->{
            if(x.getKey().getText().isEmpty())
                Message_Warning+="Le champ : "+x.getValue()+" est vide !\n";
        });
                      WarningWindow.setContentText(Message_Warning);             
        
    }
         private void clear_tf()
    {
         Field1.keySet().forEach(x->x.clear());
         Field2.keySet().forEach(x->x.clear());

    }
    
}
