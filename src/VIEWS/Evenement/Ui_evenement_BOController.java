/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Evenement;

import Entites.Events.Events;
import Entites.Events.InviteEvents;
import Entites.Plan.Plan;
import Entites.User.Utilisateur;
import Services.Events.GestionnaireEvent;
import Services.Events.GestionnaireInviteEvent;
import Services.Plan.GestionnairePlan;
import Services.User.GestionnaireUser;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author dellpro
 */
public class Ui_evenement_BOController implements Initializable {

    //***Declaration  Map
   Map<TextField,String>Fields;
   Map<DatePicker,String>DateFields;
   Map<Spinner,String>HeureFields;
   Map<ComboBox,String>TypeFields;
   Map<TextArea,String>TxtaFields;
//   Map<List, Integer> maplan;
//   HashMap<List<Plan>, Integer> mapl = new HashMap<>();
    
    List<String> lstPlan;
    List<Plan> lstidPl;
    int plan_id;
   
   private static Events Event_to_be_modified;
   
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
   private ObservableList<Utilisateur> Clients ;
   
    //private TextField Plan_txf;
    @FXML
    private TableColumn<Utilisateur, String> invit_tbl;
    @FXML
    private TableColumn<Utilisateur, String> user_tbl;
    @FXML
    private Button plus_bt;
    @FXML
    private Button supp_btn;
    
  // private static Events Evenementmodifier ;
    @FXML
    private TableView<Utilisateur> invt_cl;
    @FXML
    private TableView<Utilisateur> user_cl;
    @FXML
    private TextField rechIvt_txt;
    private Plan planTrouve;
    @FXML
    private Spinner<Integer> duree_sp;
    @FXML
    private ComboBox<String> plan_cb;
   
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
        int_actions();
       try {
           init_table_Views();
            check_update();
       } catch (SQLException ex) {
           Logger.getLogger(Ui_evenement_BOController.class.getName()).log(Level.SEVERE, null, ex);
       }
       
        
    }
    private void init_table_Views() throws SQLException
    {
        invit_tbl.setCellValueFactory((CellDataFeatures<Utilisateur, String>Client)->new SimpleStringProperty((Client.getValue().getNom()+" "+ Client.getValue().getPrenom())));
        user_tbl.setCellValueFactory((CellDataFeatures<Utilisateur, String>Client)->new SimpleStringProperty((Client.getValue().getNom()+" "+ Client.getValue().getPrenom())));
        GestionnaireUser gc = new GestionnaireUser();
        Clients = FXCollections.observableArrayList((ArrayList<Utilisateur>)gc.fetchAll());
        user_cl.setItems(Clients);
        
    }
    
    private void init_node(){
         try 
        {
        GestionnairePlan gplan = new GestionnairePlan();
        lstPlan = ((List < Plan >) gplan.fetchAll()).stream().map(x->x.getNom()).collect(Collectors.toList());
        lstidPl = ((List < Plan >) gplan.fetchAll());
        }catch(SQLException ex) {
           Logger.getLogger(Ui_evenement_BOController.class.getName()).log(Level.SEVERE, null, ex);
    }
            
        type_cb.getItems().addAll("Sportif","Conférence");
        plan_cb.getItems().addAll(lstPlan);
        plan_cb.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
         if(plan_cb.getItems().indexOf(newValue)!=-1)  planTrouve= lstidPl.get(plan_cb.getItems().indexOf(newValue));
           // System.out.println(planTrouve);
         });
       
    }
    
    private void int_actions(){
      SpinnerValueFactory ValueHeur = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 18, 1);
      SpinnerValueFactory ValueMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 30, 1);
      SpinnerValueFactory ValueDuree = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 23, 2, 1);
      heure_sp.setValueFactory(ValueHeur);
      min_sp.setValueFactory(ValueMin);
      duree_sp.setValueFactory(ValueDuree);
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
            HeureFields.put(min_sp, "Minute evenement");
            HeureFields.put(duree_sp, "Duree evenement");
            TypeFields.put(type_cb, "type");
            TypeFields.put(plan_cb, " plan");
            TxtaFields.put(descriptin_txa, "description");
           // Fields.put(Plan_txf, "Plan");
        }

    @FXML
    private void Reset(ActionEvent event) {
        Fields.keySet().forEach(x->x.clear());
        HeureFields.keySet().forEach(x->x.getValueFactory().setValue(0));
        DateFields.keySet().forEach(x->x.setValue(LocalDate.MIN));
        TxtaFields.keySet().forEach(z->z.clear());
        TypeFields.keySet().forEach(p->p.disarm());
        invt_cl.getItems().clear();
        type_cb.setValue(null);
        plan_cb.setValue(null);
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
       
        if(Message_Warning.equals("Les champs suivants posent des problèmes \n"))
        {
         GestionnaireEvent ge= new GestionnaireEvent();
          if(MethodeUtilise.equals("Ajouter"))
            {   //MySoulMate.getLogged_in_Client()
            //invt_cl.getItems().stream().collect(Collectors.toList())
            Plan p;
                if(ge.create(new Events(
                       "eventAdmin",
              Date.valueOf(date_dp.getValue()),
                        heure_sp.getValue()+":"+min_sp.getValue(),
                        duree_sp.getValue().toString(),
              type_cb.getValue(),
                        null,
                        descriptin_txa.getText(),
                        planTrouve,
                        200))==1)
              
           {
           int id_ev=((List<Events>)ge.fetchAll()).stream().mapToInt(x->x.getId()).max().getAsInt();
           System.out.println("id eve"+id_ev);
           Events ev = new Events();
           ev.setId(id_ev);
           GestionnaireInviteEvent gi = new GestionnaireInviteEvent();
           invt_cl.getItems().stream().collect(Collectors.toList()).forEach(
           x->{
               try {
                   gi.create(new InviteEvents(ev,x));
               } catch (SQLException ex) {
                   Logger.getLogger(Ui_evenement_BOController.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
           );
                  
           InformationWindow.show();
           Reset(event);
       }
       else
          ErrorWindow.show();
        }
        //************
        else
        {
               Optional<ButtonType> result = ConfirmWindow.showAndWait();
                     if(result.isPresent()&&result.get()==Oui)
                     {   
                          Plan p;
                       if( ge.update(new Events(
                               "eventAdmin",
                               Date.valueOf(date_dp.getValue()),
                               heure_sp.getValue()+":"+min_sp.getValue(),
                               duree_sp.getValue().toString(),
                               type_cb.getValue(),
                               null,
                               descriptin_txa.getText(),
                               planTrouve ,
                               200))==1)
                       {
                           InformationWindow.setContentText("Evènement modifié avec succé !");
                           InformationWindow.show();
                           MethodeUtilise="Ajouter";
                       }
                       else
                          ErrorWindow.show();   
                     }
        }
//****
    }
    else
    {
        WarningWindow.show();
    }
        
    }
    
    public static void setEvent_to_be_modified(Events Event_to_be_modified) {
        Ui_evenement_BOController.Event_to_be_modified = Event_to_be_modified;
        
    }

    @FXML
    private void TypeEvt(ActionEvent event) {
    }


    @FXML
    private void filter(KeyEvent event) throws SQLException {
        GestionnaireUser gc =new GestionnaireUser();
        Clients=FXCollections.observableArrayList((List<Utilisateur>)gc.fetchAll());//fetchAll(rechIvt_txt.getText(), 20, " yyyy"));
        user_cl.setItems(Clients);
        
    }

    @FXML
    private void AjouterIvt_btn(ActionEvent event) {
      if(!user_cl.getSelectionModel().isEmpty())
        {
        Utilisateur Clientselected = user_cl.getSelectionModel().getSelectedItem();
        if(!invt_cl.getItems().contains(Clientselected))
        invt_cl.getItems().add(Clientselected);
        user_cl.getSelectionModel().clearSelection();
        }
      
        
    }

    @FXML
    private void SupprIvt_btn(ActionEvent event) {
        
        if(!invt_cl.getSelectionModel().isEmpty())
        {
            Utilisateur Clientselected = invt_cl.getSelectionModel().getSelectedItem();
            invt_cl.getItems().remove(Clientselected);
            invt_cl.getSelectionModel().clearSelection();
        }
    }
    private void clear_tf()
    {
         Fields.keySet().forEach(x->x.clear());
         if(Event_to_be_modified!=null)
             check_update();
    }

    private void check_update() {
       if(Event_to_be_modified!=null)
       {
          // date_dp.setDayCellFactory(Event_to_be_modified.getNom());
         // heure_sp.setValueFactory(Event_to_be_modified.getHeure_evt());
           descriptin_txa.setText(Event_to_be_modified.getDescription_evt());
           type_cb.setValue(Event_to_be_modified.getType_evt());
           plan_cb.setValue(Event_to_be_modified.getPlan_evt().toString());
           plan_cb.setValue(Event_to_be_modified.getPlan_evt().getNom());
           MethodeUtilise="Modifer";
           update_button();
       }
       else
           clear_tf();
    }
     private void update_button()
    {
        ajout_bt.setText(MethodeUtilise);
    }
    
}
