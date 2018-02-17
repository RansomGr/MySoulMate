///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package VIEWS.Matching;
//
//import java.net.URL;
//import java.util.ResourceBundle;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Button;
//import javafx.scene.control.CheckBox;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.RadioButton;
//import javafx.scene.control.Spinner;
//import javafx.scene.control.TextField;
//import java.sql.SQLException;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//
//
///**
// * FXML Controller class
// *
// * @author Nadia
// */
//public class Ui_FO_AjouterPreferenceController implements Initializable {
//
//    @FXML
//    private ComboBox<Integer> age_min_cb;
//    @FXML
//    private ComboBox<Integer> age_max_cb;
//    @FXML
//    private ComboBox<String> silouhette_cb;
//    @FXML
//    private ComboBox<String> pilosite_cb;
//    @FXML
//    private ComboBox<String> origine_cb;
//    @FXML
//    private TextField profession_tf;
//    @FXML
//    private ComboBox<String> caractere_cb;
//    @FXML
//    private Spinner<String> taille_min_sp;
//    @FXML
//    private ComboBox<?> cheveux_cb;
//    @FXML
//    private ComboBox<?> yeux_cb;
//    @FXML
//    private ComboBox<?> statut_cb;
//    @FXML
//    private ComboBox<?> cuisine_cb;
//    @FXML
//    private Button valider_btn;
//    @FXML
//    private Button reset_btn;
//    @FXML
//    private Spinner<?> taille_max_sb;
//    @FXML
//    private ComboBox<?> ville_cb;
//    @FXML
//    private RadioButton tabac_oui_rb;
//    @FXML
//    private RadioButton tabac_non_rb;
//    @FXML
//    private RadioButton tabac_indifferent_rb;
//    @FXML
//    private RadioButton alcool_oui_rb;
//    @FXML
//    private RadioButton alcool_non_rb;
//    @FXML
//    private RadioButton alcool_indifferent_rb;
//    @FXML
//    private CheckBox horoscope_cb;
//    
//   String Action;
//   String Message;
//   private Alert InformationWindow  = new Alert(Alert.AlertType.INFORMATION);
//   private Alert ErrorWindow = new Alert(Alert.AlertType.ERROR);
//   private Alert WarningWindow = new Alert(Alert.AlertType.WARNING);
//   
//   Map< TextField ,String> Fields1;
//   Map< TextArea ,String> Fields2;
//   Map< Spinner ,String> Fields3;
//   
//   
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        String Action = "Ajouter";
//       
//        ErrorWindow.setContentText("La base de données n'est pas accesible !");
//        ErrorWindow.setHeaderText("Gestion Packagings");
//        ErrorWindow.setTitle("MySoulMate");
//        
//        WarningWindow.setContentText(Message);
//        WarningWindow.setHeaderText("Gestion Packagings");
//        WarningWindow.setTitle("MySoulMate");
//        
//        InformationWindow.setContentText("Packaging ajouté avec succès !");
//        InformationWindow.setHeaderText("Gestion Packagings");
//        InformationWindow.setTitle("MySoulMate");
//        
//        SpinnerValueFactory <Integer> duree_spinner_factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1825,7);
//        
//        this.duree_s.setValueFactory(duree_spinner_factory);
//        
//        
//       
//        fill_Nodes();
//        
//    }    
//
//    
//            private void fill_Nodes()
//        {
//            Fields1= new HashMap<>();
//            Fields2= new HashMap<>();
//            Fields3= new HashMap<>();
//       
//            
//            Fields1.put(nom_tf,"nom");
//            Fields1.put(prix_tf,"prix");
//            Fields2.put(contenu_ta,"contenu");
//            Fields3.put(duree_s,"durée");
//            
//        }
//      
//    @FXML
//    private void ajouter_packaging(ActionEvent event) throws SQLException {
//        
////       if(validate_form()==true)
////        {
////        if(Action.equals("Ajouter"))
////        {
//        GestionnairePackaging gp = new GestionnairePackaging();
//        int duree = (Integer) duree_s.getValue();
//        float prix =  Float.parseFloat(prix_tf.getText());
//       if( gp.create(new Packaging(nom_tf.getText(),contenu_ta.getText(),duree ,prix))==1)
//       
//       {
//           InformationWindow.show();
//           clear_tf();
//       }
////       else
////          ErrorWindow.show();
////        }
////        else
////        {
////            Action="Modifier";
////                        
////            GestionnairePackaging gp = new GestionnairePackaging();
////            
////        int duree = (Integer) duree_s.getValue();
////        float prix =  Float.parseFloat(prix_tf.getText());
////        gp.update(new Packaging(nom_tf.getText(),contenu_ta.getText(),duree ,prix));
////        
////        }
////    }
////    else
////    {
////        WarningWindow.show();
////    }
//    }
//
//   @FXML
//
//    private boolean validate_form()
//    {
//        Message="Les champs suivants posent des problèmes : \n";
//        if(nom_tf.getText().isEmpty())
//        {Message+="Le champ nom est vide !\n";}
//        if(contenu_ta.getText().isEmpty())
//        {Message+="Le champ contenu est vide !\n";}
//        if(prix_tf.getText().isEmpty())
//        {Message+="Le champ prix est vide !\n";}
//        
//       if(Message.equals("Les champs suivants posent des problèmes : \n"))
//       {WarningWindow.show();
//
//          return false;}
//        
//      else 
//      {
//        return true;
//      }
//
//     
//    }
//    
//
//    
//    private void clear_tf()
//    {
//         Fields1.keySet().forEach(x->x.clear());
//         Fields2.keySet().forEach(x->x.clear());
//         //Fields3.keySet().forEach(x->x.clear());
//
//    }
//    
//    @FXML
//    private void reset(ActionEvent event) {
//    clear_tf();
//    //this.duree_s.setValue(7);
//    }
//
//}
