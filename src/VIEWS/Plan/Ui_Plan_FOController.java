/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Plan;

import Entites.Plan.Avis;
import Entites.Plan.Plan;
import Entites.User.Client;
import Services.Plan.GestionnaireAvis;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author irou
 */
public class Ui_Plan_FOController implements Initializable {

    @FXML
    private ImageView imgage_img;
    @FXML
    private Text notet_label;
    @FXML
    private TextArea description_ta;
    @FXML
    private Text nomP_label;
    @FXML
    private Button Reserver_btn;
    private static Plan plan;
    private static Avis avis;
    @FXML
    private VBox avis_pane;
    private static Client Client;

    public static void setAvis(Avis avis) {
        Ui_Plan_FOController.avis = avis;
    }

    public static void setClient(Client Client) {
        Ui_Plan_FOController.Client = Client;
    }

    public static void setPlan(Plan plan) {
        Ui_Plan_FOController.plan = plan;
    }
    @FXML
    private ImageView image_img;
    @FXML
    private Label nomC_l;
    @FXML
    private TextArea com_ta;
    @FXML
    private Button ajouter;
    private int i;
    @FXML
    private Spinner<Integer> note_l;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           GestionnaireAvis gav =new GestionnaireAvis();
        List<Avis>avises =new ArrayList<>();
        try {
            avises=(List<Avis>) gav.fetchAll();
            
        } catch (SQLException ex) {
            Logger.getLogger(Ui_Plan_FOController.class.getName()).log(Level.SEVERE, null, ex);
        }
       nomP_label.setText(plan.getNom());
       notet_label.setText(avises.stream().mapToDouble(e->e.getNote()).average().toString());
       description_ta.setText(plan.getDescription());
    
       List<Avis> avis;
      
        try {
             avis=  ((List<Avis>)gav.fetchall(plan));
          Parent root;
                 
           FXMLLoader FXML;
            
                double i=0;
                int j=0;
        for (Avis avi : avis) {
             FXML=new FXMLLoader(getClass().getResource("/VIEWS/Plan/avis_FO.fxml"));
            root = FXML.load();
            Avis_FOController con =FXML.<Avis_FOController>getController();
           
            con.setAvis(avi);
            con.setClient(Client);
            con.put_values();
            avis_pane.getChildren().add(root);
            
            System.out.println("i="+i);
            avis_pane.getChildren().get(j).relocate(0, i);
         
           i+=160;
             j++;
        }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Ui_Plan_FOController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         }

    @FXML
    private void update_actu(KeyEvent event) {
    }

    @FXML
    private void ajouter_avis(ActionEvent event) throws SQLException, IOException {
   
        GestionnaireAvis a= new GestionnaireAvis();
        Avis avi=new Avis(this.Client,  this.com_ta.getText(),this.note_l.getValue().floatValue());
        a.create(avi);
        Parent root;
        FXMLLoader FXML;
        FXML=new FXMLLoader(getClass().getResource("/VIEWS/Plan/ui_Plan_FO.fxml"));
            root = FXML.load();
            Avis_FOController con =FXML.<Avis_FOController>getController();
            con.setAvis(avi);
            con.setClient(Client);
            con.put_values();
            avis_pane.getChildren().add(root);  
            System.out.println("i="+i);          
        //    actualite_pane.getChildren().get(actualite_pane.getChildren().size()-1).relocate(5, i);     
       //     i+=95;       
    }
    
         
      
    
       
      
    }    
    

