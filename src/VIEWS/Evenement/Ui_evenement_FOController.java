/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Evenement;
//
//import Entites.Evenement.EvenementGroup;
//import Entites.Evenement.InviteEvenement;
import Entites.Events.Events;
import Entites.Events.InviteEvents;
import Entites.Plan.Plan;
import Entites.User.Client;
//import Services.Evenement.GestionnaireEvenementGroup;
//import Services.Evenement.GestionnaireInviteEvenement;
import Services.Events.GestionnaireEvent;
import Services.Events.GestionnaireInviteEvent;
import Services.Plan.GestionnairePlan;
import Services.User.GestionnaireClient;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author dellpro
 */
public class Ui_evenement_FOController implements Initializable {
    
    //***Declaration  Map
   Map<TextField,String>Fields;
   Map<DatePicker,String>DateFields;
   Map<Spinner,String>HeureFields;
   Map<ComboBox,String>TypeFields;
   Map<TextArea,String>TxtaFields;
   
   //***declaration des var
    private String MethodeUtilise;
   private String Message_Warning;
   private ButtonType  Oui ;
   private ButtonType  Non;
   
   List<String> lstPlan;
    List<Plan> lstidPl;
    int plan_id;

   private Alert InformationWindow;
   private Alert ErrorWindow;
   private Alert WarningWindow;
   private Alert ConfirmWindow;
   private ObservableList<Client> Clients ;
   private Plan planTrouve;
   private static Events Event_to_be_modified;
  // private static Events Evenementmodifier ;

    @FXML
    private DatePicker date_pk;
    @FXML
    private Spinner<Integer> hour_sp;
    @FXML
    private Spinner<Integer> min_sp;
    @FXML
    private ComboBox<String> type_cmb;
    @FXML
    private TextArea description_txa;
    @FXML
    private TextField recherche_ivt_txf;
    @FXML
    private TableView<Client> table_amis;
//    private TableColumn<Client, Integer> amisID_cl;
    @FXML
    private TableColumn<Client, String> amisNom_cl;
    @FXML
    private TableView<Client> table_invt;
//    private TableColumn<Client, Integer> ivtID_cl;
    @FXML
    private TableColumn<Client, String> ivtNom_cl;
    @FXML
    private Button plus_btn;
    @FXML
    private Button moins_btn;
   // private TextField plan_txf;
    @FXML
    private Button reinitial_btn;
    @FXML
    private Button ajou_btn;
    @FXML
    private ImageView img_vi;
    @FXML
    private Spinner<Integer> duree_sp;
    @FXML
    private ComboBox<String> plan_cmb;
    @FXML
    private Button sms_btn;

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
        InformationWindow.setContentText("Evènement ajouté avec succès !");
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
           Logger.getLogger(Ui_evenement_FOController.class.getName()).log(Level.SEVERE, null, ex);
       }
    } 
    private void init_table_Views() throws SQLException
    {
//        amisID_cl.setCellValueFactory((TableColumn.CellDataFeatures<Client, Integer>Client)->new SimpleIntegerProperty((Client.getValue().getID())).asObject());
//        ivtID_cl.setCellValueFactory((TableColumn.CellDataFeatures<Client, Integer>Client)->new SimpleIntegerProperty((Client.getValue().getID())).asObject());
        amisNom_cl.setCellValueFactory((TableColumn.CellDataFeatures<Client, String>Client)->new SimpleStringProperty((Client.getValue().getNom()+" "+ Client.getValue().getPrenom())));
        ivtNom_cl.setCellValueFactory((TableColumn.CellDataFeatures<Client, String>Client)->new SimpleStringProperty((Client.getValue().getNom()+" "+ Client.getValue().getPrenom())));
        GestionnaireClient gc = new GestionnaireClient();
        Clients = FXCollections.observableArrayList((ArrayList<Client>)gc.fetchAll());
        table_amis.setItems(Clients);
        
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
    
    private void init_node(){
        
         try 
        {
        GestionnairePlan gplan = new GestionnairePlan();
        lstPlan = ((List < Plan >) gplan.fetchAll()).stream().map(x->x.getNom()).collect(Collectors.toList());
        lstidPl = ((List < Plan >) gplan.fetchAll());
        }catch(SQLException ex) {
           Logger.getLogger(Ui_evenement_FOController.class.getName()).log(Level.SEVERE, null, ex);
    }
        type_cmb.getItems().addAll("Rendez-vous","fiancailles","mariage");
        
         plan_cmb.getItems().addAll(lstPlan);
        plan_cmb.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
         if(plan_cmb.getItems().indexOf(newValue)!=-1)  planTrouve= lstidPl.get(plan_cmb.getItems().indexOf(newValue));
         });
    }
                
                
    private void int_actions(){
      SpinnerValueFactory ValueHeur = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 18, 1);
      SpinnerValueFactory ValueMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 30, 1);
      SpinnerValueFactory ValueDuree = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 23, 2, 1);
      hour_sp.setValueFactory(ValueHeur);
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
            
            DateFields.put(date_pk,"date evenement");
            HeureFields.put(hour_sp,"heure evenement");
            HeureFields.put(min_sp, "Minute evenement");
            HeureFields.put(duree_sp, "Duree evenement");
            TypeFields.put(type_cmb, "type");
            TypeFields.put(plan_cmb ,"plan");
            TxtaFields.put(description_txa, "description");           
        }

    @FXML
    private void plusFO(ActionEvent event) {
        if(!table_amis.getSelectionModel().isEmpty())
        {
        Client Clientselected = table_amis.getSelectionModel().getSelectedItem();
        if(!table_invt.getItems().contains(Clientselected))
        table_invt.getItems().add(Clientselected);
        table_amis.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void moinsFO(ActionEvent event) {
        if(!table_invt.getSelectionModel().isEmpty())
        {
            Client Clientselected = table_invt.getSelectionModel().getSelectedItem();
            table_invt.getItems().remove(Clientselected);
            table_invt.getSelectionModel().clearSelection();
        }
    }

//    private void rechercheplanFO(ActionEvent event) throws SQLException {
//         GestionnairePlan gp = new GestionnairePlan();
//       List<Plan> lsplan =(List<Plan>)gp.fetchAll(plan_txf.getText(), 8, "plan ");
//       if(!(lsplan.size()==0)&&!plan_txf.getText().isEmpty())
//       {
//        planTrouve=lsplan.stream().findFirst().get();
//        plan_txf.clear();
//        plan_txf.setText(planTrouve.getNom());
//       }
//    } //plan_cmb

    @FXML
    private void reinitialiseFO(ActionEvent event) {
        Fields.keySet().forEach(x->x.clear());
        HeureFields.keySet().forEach(x->x.getValueFactory().setValue(0));
        DateFields.keySet().forEach(x->x.setValue(LocalDate.MIN));
        TxtaFields.keySet().forEach(z->z.clear());
        TypeFields.keySet().forEach(p->p.disarm());
        table_invt.getItems().clear();
        type_cmb.setValue(null);
        plan_cmb.setValue(null);
    }
    private void filter(KeyEvent event) throws SQLException {
        GestionnaireClient gc =new GestionnaireClient();
        Clients=FXCollections.observableArrayList((List<Client>)gc.fetchAll(recherche_ivt_txf.getText(), 20, " yyyy"));
        table_amis.setItems(Clients);
        
    }

    @FXML
    private void ajouterFO(ActionEvent event) throws SQLException {
        valider_form();
        if(Message_Warning.equals("Les champs suivants posent des problèmes \n"))
        {
           GestionnaireEvent ge= new GestionnaireEvent();
        if(MethodeUtilise.equals("Ajouter"))
        {   //MySoulMate.getLogged_in_Client()
            //invt_cl.getItems().stream().collect(Collectors.toList())
            Plan p;
      if(ge.create(new Events(
                       "eventUser",
              Date.valueOf(date_pk.getValue()), hour_sp.getValue()+":"+min_sp.getValue(), duree_sp.getValue().toString(),
              type_cmb.getValue(),MySoulMate.getLogged_in_Client(),description_txa.getText(), planTrouve, 200, -1, "event"))==1)
       {
           int id_ev=((List<Events>)ge.fetchAll()).stream().mapToInt(x->x.getID()).max().getAsInt();
           System.out.println("id eve"+id_ev);
           Events ev = new Events(id_ev, "nom_evt");
           ev.setID(id_ev);
           GestionnaireInviteEvent gi = new GestionnaireInviteEvent();
           table_invt.getItems().stream().collect(Collectors.toList()).forEach(
           x->{
               try {
                   gi.create(new InviteEvents(ev,x));
               } catch (SQLException ex) {
                   Logger.getLogger(Ui_evenement_FOController.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
           );
                  
           InformationWindow.show();
           reinitialiseFO(event);
       }
       else
          ErrorWindow.show();
        }
        else
        {
               Optional<ButtonType> result = ConfirmWindow.showAndWait();
                     if(result.isPresent()&&result.get()==Oui)
                     {   
                       if(ge.update(new Events(
                       "eventUser",
              Date.valueOf(date_pk.getValue()), hour_sp.getValue()+":"+min_sp.getValue(), duree_sp.getValue().toString(),
              type_cmb.getValue(),MySoulMate.getLogged_in_Client(),description_txa.getText(),
                               planTrouve, 200, Event_to_be_modified.getID(), "event"))==1)
                       {
                           InformationWindow.setContentText("Evènement modifié avec succé !");
                           InformationWindow.show();
                           MethodeUtilise="Ajouter";
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
    
    public static void setEvent_to_be_modified(Events Event_to_be_modified) {
        Ui_evenement_FOController.Event_to_be_modified = Event_to_be_modified;
        
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
          //date_pk.setDayCellFactory((Callback<DatePicker, DateCell>) Event_to_be_modified.getDate_evt());
         //hour_sp.setValueFactory(Event_to_be_modified.getHeure_evt().);
           description_txa.setText(Event_to_be_modified.getDescription_evt());
           type_cmb.setValue(Event_to_be_modified.getType_evt());
           plan_cmb.setValue(Event_to_be_modified.getPlan_evt().toString());
           //duree_sp.setValueFactory(Event_to_be_modified.getDuree_evt());
           MethodeUtilise="Modifer";
           update_button();
       }
       else
           clear_tf();
    }
     private void update_button()
    {
        ajou_btn.setText(MethodeUtilise);
    }
    
//    private void RechercherPlan() throws SQLException {
//        GestionnairePlan gp = new GestionnairePlan();
//       List<Plan> lsplan =(List<Plan>)gp.fetchAll(plan_txf.getText(), 8, "plan ");
//       if(!(lsplan.size()==0)&&!plan_txf.getText().isEmpty())
//       {
//        planTrouve=lsplan.stream().findFirst().get();
//        plan_txf.clear();
//        plan_txf.setText(planTrouve.getNom());
//       }
//    }

    @FXML
    private void envoyersms(ActionEvent event) {
       try {
           MySoulMate.getMainController().load_ajout_sms(event);
       } catch (IOException ex) {
           Logger.getLogger(Ui_evenement_FOController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
}
