/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Evenement;

import Entites.Evenement.Evenement;
import Entites.Evenement.EvenementGroup;
import Entites.Plan.Plan;
import Services.Evenement.GestionnaireEvenement;
import Services.Evenement.GestionnaireEvenementGroup;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author dellpro
 */
public class Ui_evenement_BOController implements Initializable {

    @FXML
    private DatePicker date_dp;
    @FXML
    private Spinner<Integer> heure_sp;
    @FXML
    private Spinner<Integer> min_sp;
    @FXML
    private ComboBox<String> type_cb;
    @FXML
    private Button reset_bt;
    @FXML
    private Button ajout_bt;
    @FXML
    private TextArea descriptin_txa;

   private String MethodeUtilise;
   private String Message_Warning;
   private ButtonType  Oui ;
   private ButtonType  Non;

   private Alert InformationWindow;
   private Alert ErrorWindow;
   private Alert WarningWindow;
   private Alert ConfirmWindow;
   
   
    @FXML
    private TextField Plan_txf;
    @FXML
    private Button Plan_btn;
    @FXML
    private TableColumn<?, ?> invit_tbl;
    @FXML
    private TableColumn<?, ?> user_tbl;
    @FXML
    private Button plus_bt;
    @FXML
    private Button supp_btn;
    
    //***Declaration  Map
   Map<TextField,String>Fields;
   Map<DatePicker,String>DateFields;
   Map<Spinner,String>HeureFields;
   Map<ComboBox,String>TypeFields;
   Map<TextArea,String>TxtaFields;
   
   private static Evenement Evenementmodifier ;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        init_node();
        MethodeUtilise="Ajouter";
        InformationWindow   =new Alert(Alert.AlertType.INFORMATION);
        WarningWindow = new Alert(Alert.AlertType.WARNING);
        ErrorWindow =new Alert(Alert.AlertType.ERROR);
        ConfirmWindow = new Alert(Alert.AlertType.CONFIRMATION);
        
        Oui= new ButtonType("Modifier",ButtonBar.ButtonData.OK_DONE);
        Non=new ButtonType("Annuler",ButtonBar.ButtonData.CANCEL_CLOSE);
        ConfirmWindow.getButtonTypes().clear();
        ConfirmWindow.getButtonTypes().addAll(Oui,Non);
        InformationWindow.setContentText("Evènement Ajouté Avec succès !");
        ErrorWindow.setContentText("La base de données n'est pas accessible !");
        ErrorWindow.setHeaderText("Gestion évènement");
        ErrorWindow.setTitle("MySoulMate");
        ConfirmWindow.setTitle("MySoulMate");
        ConfirmWindow.setHeaderText("Gestion évènement");
        ConfirmWindow.setContentText("Voulez Vous vraiement modifier cet évènement ?");
        WarningWindow.setContentText(Message_Warning);
        WarningWindow.setHeaderText("Gestion évènement");
        WarningWindow.setTitle("MySoulMate");
        InformationWindow.setHeaderText("Gestion évènement");
        InformationWindow.setTitle("MySoulMate");
        Remplir();
       // check_update();
    }    
    
    private void init_node(){
        type_cb.getItems().addAll("Rendez-vous","Conférence","fiancaille","mariage","voyage");
    }
    private void int_actions(){
      SpinnerValueFactory ValueHeur = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 18, 1);
      SpinnerValueFactory ValueMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 30, 1);
      heure_sp.setValueFactory(ValueHeur);
      min_sp.setValueFactory(ValueMin);
    }
    private void Remplir()
        {
            Fields= new HashMap<>();
            DateFields= new HashMap();
            HeureFields= new HashMap();
            TypeFields= new HashMap();
            TxtaFields= new HashMap();
            
           
            DateFields.put(date_dp,"date evenement");
            HeureFields.put(heure_sp,"heure evenement");
            HeureFields.put(min_sp, "Min evenement");
            TypeFields.put(type_cb, "type");
            TxtaFields.put(descriptin_txa, "description");
            Fields.put(Plan_txf, "Plan");
        }

    @FXML
    private void Reset(ActionEvent event) {
        Fields.keySet().forEach(x->x.clear());
        HeureFields.keySet().forEach(x->x.getValueFactory());
        DateFields.keySet().forEach(x->x.setValue(LocalDate.MAX));
        TxtaFields.keySet().forEach(z->z.clear());
        TypeFields.keySet().forEach(p->p.disarm());
    }
    private void valider_form()
    {
       Message_Warning="Les champs suivants posent des problèmes \n";
        Fields.entrySet().forEach(x->{
            if(x.getKey().getText().isEmpty())
                Message_Warning+="Le champ : "+x.getValue()+" est vide !\n";
        });
        TypeFields.entrySet().forEach(x->{
            if(x.getKey().getValue()==null)
                Message_Warning+="Le champ : "+x.getValue()+" est vide !\n";
        });
        DateFields.entrySet().forEach(x->{
            if(x.getKey().getValue()==null)
                Message_Warning+="Le champ : "+x.getValue()+" est vide !\n";
        });
        HeureFields.entrySet().forEach(x->{
            if(x.getKey().getValue()==null)
                Message_Warning+="Le champ : "+x.getValue()+" est vide !\n";
        });
        TxtaFields.entrySet().forEach(x->{
            if(x.getKey().getText().isEmpty())
                Message_Warning+="Le champ : "+x.getValue()+" est vide !\n";
        });
      WarningWindow.setContentText(Message_Warning);
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException{
       valider_form();
//        if(Message_Warning.equals("Les champs suivants posent des problèmes \n"))
//        {
//            GestionnaireEvenementGroup ge= new GestionnaireEvenementGroup();
//        if(MethodeUtilise.equals("Ajouter"))
//        {
//            Plan p;
//     //  if(ge.create(new EvenementGroup(MySoulMate.getLogged_in_Client(), new Date(), descriptin_txa.getText(), p))==1)
//       {
//           InformationWindow.show();
//           Reset(event);
//       }
//       else
//          ErrorWindow.show();
//        }
//        else
//        {
//               Optional<ButtonType> result = ConfirmWindow.showAndWait();
//                     if(result.isPresent()&&result.get()==Oui)
//                     {   
//                       if(ge.update(new Admin(nom_tf.getText(),prenom_tf.getText(),login_tf.getText(),mdp_tf.getText()))==1)
//                       {
//                           InformationWindow.setContentText("Administrateur modifié avec succée !");
//                           InformationWindow.show();
//                           MethodeUtilise="Ajouter";
//                       }
//                       else
//                          ErrorWindow.show();   
//                     }
//        }
//    }
//    else
//    {
//        WarningWindow.show();
//    }
        
    }

    @FXML
    private void TypeEvt(ActionEvent event) {
    }

    @FXML
    private void RechercherPlan(ActionEvent event) {
    }

    
}
