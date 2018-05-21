/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Relation;

import Entites.Relation.Relation;
import Services.Relation.GestionnaireRelation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
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
import Entites.Relation.Conseil;
import Entites.User.Utilisateur;
import Services.Relation.GestionnaireConseil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mysoulmate.MySoulMate;
import VIEWS.Ui_MainFrame_FOController;
import java.util.List;

/**
 * FXML Controller class
 *
 * @author zhanimm
 */
public class Ui_InterfaceRE_FOController implements Initializable {
@FXML
private TableView liste;
GestionnaireConseil gc;
Ui_MainFrame_FOController mf;  
      @FXML
    private Text temps;
    @FXML
    private Text pts;
    @FXML
    private Text niv;
    @FXML
    private Text t1;
    @FXML
    private Text t2;
    private String niveau;
    

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
    private TableColumn<Conseil, String> c2;
    @FXML
    private TableColumn<Conseil, String> c3;
    
    @FXML
    private Button annbtn;
    @FXML
    private Tab infos;
    @FXML
    private Tab moments;
    @FXML
    private Tab conseils;
    
   private String OperationMode;
   private String Message_Warning;
   private Alert InformationWindow;
   private Alert ErrorWindow;
   private Alert WarningWindow;
   private Alert ConfirmWindow;
   
   private static Utilisateur relation_owner;


    @Override
    public void initialize(URL location, ResourceBundle resources) {   
    
        temps.setText("0");
        pts.setText("0");
        niv.setText("0");
        
        InformationWindow   =new Alert(Alert.AlertType.INFORMATION);
        WarningWindow = new Alert(Alert.AlertType.WARNING);
        ErrorWindow =new Alert(Alert.AlertType.ERROR);
        ConfirmWindow = new Alert(Alert.AlertType.CONFIRMATION);  
        InformationWindow.setContentText("Moment Ajouté Avec succès !");
        InformationWindow.setHeaderText("Gestion Moment");
        InformationWindow.setTitle("MySoulMate");
        ErrorWindow.setContentText("La base de données n'est pas accessible !");
        ErrorWindow.setHeaderText("Gestion Moment");
        ErrorWindow.setTitle("MySoulMate");
        WarningWindow.setContentText(Message_Warning);
        WarningWindow.setHeaderText("Gestion Moment");
        WarningWindow.setTitle("MySoulMate");
     
        fill_Nodes();
    try {
        fill_Infos();
    } catch (SQLException ex) {
        Logger.getLogger(Ui_InterfaceRE_FOController.class.getName()).log(Level.SEVERE, null, ex);
    }
       moments.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (moments.isSelected()) {
                   TrayNotification tray = new TrayNotification("Astuce !", "Ajouter des moments pour gagner des points !!", NotificationType.INFORMATION);
                   tray.showAndDismiss(javafx.util.Duration.seconds(1));
                }
            }
        });
        conseils.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (conseils.isSelected()) {
                   TrayNotification tray = new TrayNotification("Astuce !", "Vous pouvez fier aux conseils !!", NotificationType.INFORMATION);
                   tray.showAndDismiss(javafx.util.Duration.seconds(1));
                }
            }
        });
        
        c2.setCellValueFactory((TableColumn.CellDataFeatures<Conseil,String>Conseil)->new SimpleStringProperty(Conseil.getValue().getTitre()));
        c3.setCellValueFactory((TableColumn.CellDataFeatures<Conseil,String>Conseil)-> new SimpleStringProperty(Conseil.getValue().getContenue()));
        liste.setVisible(false);
         gc = new GestionnaireConseil();
    }
      public static void setRelation_owner(Utilisateur relation_owner) {
        Ui_InterfaceRE_FOController.relation_owner = relation_owner;
    }
    
    private void fill_Nodes()
        {
            champs= new HashMap<>();
            champs.put(nom,"Contenu");
            champs.put(des,"Description");
            champs.put(photo,"Photo");
        }
    private void fill_Infos() throws SQLException{
        GestionnaireRelation gr= new GestionnaireRelation();

        temps.setText(gr.Temps());
        pts.setText(gr.NbPoint());
        niv.setText(gr.Niveau());
    }
    private void validate_form()
    {
       Message_Warning="Les champs suivants posent des problèmes \n";
        champs.entrySet().forEach(x->{
            if(x.getKey().getText().isEmpty())
                Message_Warning+="Le champ : "+x.getValue()+" est vide !\n";
        });
              WarningWindow.setContentText(Message_Warning);
    }
          
@FXML
    public void AjouterMoment() throws IOException, SQLException
{
     validate_form();
        if(Message_Warning.equals("Les champs suivants posent des problèmes \n"))
        {
            GestionnaireContenue_Moment g=new GestionnaireContenue_Moment();
      
       if( g.create(new Contenue_Moment(nom.getText() ,des.getText(),Date.valueOf(date.getValue()),photo.getText()))==1)
       {
           InformationWindow.show();
           annuler();
       }
        } 
       else
    {
        WarningWindow.show();
    }
        
}
    
    @FXML
    public void annuler()
{
    champs.keySet().forEach(x->x.clear());
}

 @FXML
    public void AfficherMoment(ActionEvent e){
      try { 
          Node root;// Making a node
           root = FXMLLoader.load(getClass().getResource("ui_ListeMoment_FO.fxml"));// Getting the View
            } catch (IOException ex) {
                Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
              } 
    }
    
    

    
}
