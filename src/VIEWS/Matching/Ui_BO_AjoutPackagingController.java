/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Matching;

import Entites.Matching.Packaging;
import Services.Matching.GestionnairePackaging;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;
import java.util.TreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class Ui_BO_AjoutPackagingController implements Initializable {

    @FXML
    private TextField prix_tf;
    @FXML
    private TextField nom_tf;
    @FXML
    private TextArea contenu_ta;
    @FXML
    private Spinner<Integer> duree_s;
    @FXML
    private Button ajouter_btn;
    @FXML
    private Button reset_btn;
  
    
     private static Packaging Packaging_a_modifier ;
     
    
               public static void set_Packaging_modif(Packaging Packaging_a_modifier) {
        Ui_BO_AjoutPackagingController.Packaging_a_modifier = Packaging_a_modifier;
       }

    
   
   static String Action = "Ajouter";
   String Message;
   private Alert InformationWindow  = new Alert(Alert.AlertType.INFORMATION);
   private Alert ErrorWindow = new Alert(Alert.AlertType.ERROR);
   private Alert WarningWindow = new Alert(Alert.AlertType.WARNING);
   private Alert ConfirmWindow = new Alert(Alert.AlertType.CONFIRMATION);
   
   private ButtonType Oui;
   private ButtonType Non;
   
   Map< TextField ,String> Fields1;
   Map< TextArea ,String> Fields2;
   Map< Spinner ,String> Fields3;
   
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String Action = "Ajouter";
       
        ErrorWindow.setContentText("La base de données n'est pas accesible !");
        ErrorWindow.setHeaderText("Gestion Packagings");
        ErrorWindow.setTitle("MySoulMate");
        
        WarningWindow.setContentText(Message);
        WarningWindow.setHeaderText("Gestion Packagings");
        WarningWindow.setTitle("MySoulMate");
        
        InformationWindow.setContentText("Packaging ajouté avec succès !");
        InformationWindow.setHeaderText("Gestion Packagings");
        InformationWindow.setTitle("MySoulMate");
        
        ConfirmWindow.setTitle("MySoulMate");
        ConfirmWindow.setHeaderText("Gestion Packaging");
        ConfirmWindow.setContentText("Voulez-vous vraiment modifier ce packaging ?");
        
         Oui = new ButtonType("Modifier",ButtonBar.ButtonData.OK_DONE);
         Non = new ButtonType("Annuler",ButtonBar.ButtonData.CANCEL_CLOSE);
        ConfirmWindow.getButtonTypes().clear();
        ConfirmWindow.getButtonTypes().addAll(Oui,Non);
        
        SpinnerValueFactory <Integer> duree_spinner_factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1825,7);
        
        this.duree_s.setValueFactory(duree_spinner_factory);
        
        
       
        fill_Nodes();
        check_update();
        
    }    

    
            private void fill_Nodes()
        {
            Fields1= new HashMap<>();
            Fields2= new HashMap<>();
            Fields3= new HashMap<>();
       
            
            Fields1.put(nom_tf,"nom");
            Fields1.put(prix_tf,"prix");
            Fields2.put(contenu_ta,"contenu");
            Fields3.put(duree_s,"durée");
            
        }
      
            
            
             @FXML
    private void ajouter_packaging(ActionEvent event) throws SQLException {
        validate_form();
        if(Message.equals("Les champs suivants posent des problèmes : \n"))
        {
             GestionnairePackaging gp= new GestionnairePackaging(); 
             int duree = (Integer) duree_s.getValue();
             float prix =  Float.parseFloat(prix_tf.getText());
             
        if(Action.equals("Ajouter"))
        {
            if( gp.create(new Packaging(nom_tf.getText(),contenu_ta.getText(),duree ,prix_tf.getText()))==1)
            {
                InformationWindow.show();
                clear_tf();
            }
            else
               ErrorWindow.show();
        }
        else if (Action.equals("Modifier"))
        {
               Optional<ButtonType> result = ConfirmWindow.showAndWait();
                     if(result.isPresent()&&result.get()==Oui)
                     {   
                       if(gp.update(new Packaging(Packaging_a_modifier.getID(), nom_tf.getText(),contenu_ta.getText(),duree ,prix_tf.getText()))==1)
                       {
                           InformationWindow.setContentText("Packaging modifié avec succès !");
                           InformationWindow.show();
                           Action="Ajouter";
                       }
                       else
                          ErrorWindow.show();   
                     }
        }
    }
    else
    {
        WarningWindow.show();
    }
    }
    
   @FXML

    private boolean validate_form()
    {
        Message="Les champs suivants posent des problèmes : \n";
        if(nom_tf.getText().isEmpty())
        {Message+="Le champ nom est vide !\n";}
        if(contenu_ta.getText().isEmpty())
        {Message+="Le champ contenu est vide !\n";}
        if(prix_tf.getText().isEmpty())
        {Message+="Le champ prix est vide !\n";}
      //  if(prix_tf.getText(). <0 ))
       // {Message+="Le prix doit être un nombre valide !\n";}
       if(!Message.equals("Les champs suivants posent des problèmes : \n"))
        {
              WarningWindow.setContentText(Message);
            WarningWindow.show();
           return false;
        }
        
        else 
        {
          return true;
        }

    }
    

    
    private void clear_tf()
    {
         Fields1.keySet().forEach(x->x.clear());
         Fields2.keySet().forEach(x->x.clear());
         //Fields3.keySet().forEach(x->x.clear());

    }
    
    @FXML
    private void reset(ActionEvent event) {
    clear_tf();
    //this.duree_s.setValue(7);
    }
    


    private void check_update() {
            if(Packaging_a_modifier!=null)
       {
           nom_tf.setText(Packaging_a_modifier.getNom());
           contenu_ta.setText(Packaging_a_modifier.getContenu());
           prix_tf.setText(Packaging_a_modifier.getPrix());
           Action="Modifier";
           update_button();
       }
       else
           clear_tf();
            Action="Ajouter";
            update_button();
    }
    
    
        private void update_button()
    {
        ajouter_btn.setText(Action);
    }

}
