/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Profil;

import Entites.Profil.Caracteristique;
import Services.Gestionnaire;
import Services.Profil.GestionnaireCaracteristique;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.omg.CORBA.portable.ValueFactory;

/**
 * FXML Controller class
 *
 * @author Sofiene
 */
public class Ui_FO_AjouterCaracteristiqueController implements Initializable {

    @FXML
    private ComboBox<String> pilosite_cb;
    @FXML
    private ComboBox<String> origine_cb;
    @FXML
    private TextField profession_tf;
    @FXML
    private ComboBox<String> caractere_cb;
    @FXML
    private Spinner<Double> taille_min_sp;
    @FXML
    private ComboBox<String> cheveux_cb;
    @FXML
    private ComboBox<String> yeux_cb;
    @FXML
    private ComboBox<String> statut_cb;
    @FXML
    private ComboBox<String> cuisine_cb;
    @FXML
    private Button valider_btn;
    @FXML
    private Button reset_btn;
    @FXML
    private RadioButton tabac_oui_rb;
    @FXML
    private RadioButton tabac_non_rb;
    @FXML
    private RadioButton tabac_indifferent_rb;
    @FXML
    private RadioButton alcool_oui_rb;
    @FXML
    private RadioButton alcool_non_rb;
    @FXML
    private RadioButton alcool_indifferent_rb;
    @FXML
    private ComboBox<String> corpulence_cb;
    private  ToggleGroup tabas;
    private  ToggleGroup Alcools;
    @FXML
    private Button localisation_btn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {           
        init_node();
        init_actions();
    }    
    

    @FXML
    private void Valider(ActionEvent event) throws SQLException, IOException {
      
      RadioButton choix_alchool ;
      RadioButton choix_tabac ;
      
      choix_alchool=(RadioButton)Alcools.getSelectedToggle();
      choix_tabac=(RadioButton)tabas.getSelectedToggle();
      System.out.println(taille_min_sp.getValue().toString());
      System.out.println("taille:"+taille_min_sp.getValue().toString());
        Caracteristique Caracteristique =
              new Caracteristique(
                      corpulence_cb.getValue(),
                      pilosite_cb.getValue(),
                      origine_cb.getValue(),
                      profession_tf.getText(),
                      choix_alchool.getText(),
                      choix_tabac.getText(),
                      taille_min_sp.getValue().floatValue(),
                      cheveux_cb.getValue(),
                      yeux_cb.getValue(),
                      caractere_cb.getValue(),
                      statut_cb.getValue(),
                      cuisine_cb.getValue()
              );
        Ui_Profile_CreationController.setCarac(Caracteristique);
        Ui_Profile_CreationController.next_step();
    }

    @FXML
    private void Reset(ActionEvent event) {
    }

    private void init_node() {
        cuisine_cb.getItems().addAll("Fast Food","Vegan","Bio","Sushi","Végétarienne");
        pilosite_cb.getItems().addAll("imberbe","poilu");
        caractere_cb.getItems().addAll("Extraverti","Timide","Normal");
        origine_cb.getItems().addAll("Arabe","Europienne","Africaine","Métisse");
        corpulence_cb.getItems().addAll("Normal","Mince","Dodu");
        cheveux_cb.getItems().addAll("Crêpu","Lisse","Rasé","longs","Mi-longs");
        statut_cb.getItems().addAll("Divorcé","Veuf","Célibataire","Marié");
        yeux_cb.getItems().addAll("Noir","Marron","Jaune","Bleu","Vert");
}
    private void init_actions()
    { 
        tabas= new ToggleGroup();
        Alcools=new ToggleGroup();
    tabac_oui_rb.setToggleGroup(tabas);
    tabac_non_rb.setToggleGroup(tabas);
    tabac_indifferent_rb.setToggleGroup(tabas);           
           
               alcool_non_rb.setToggleGroup(Alcools);
               alcool_indifferent_rb.setToggleGroup(Alcools);
               alcool_oui_rb.setToggleGroup(Alcools);
           SpinnerValueFactory ValueFac = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 250.99, 0.00,0.1);
           taille_min_sp.setValueFactory(ValueFac);
    }

    @FXML
    private void Geo_Localistaion(ActionEvent event) throws IOException {
        Stage GMapsStage = new Stage();
        Parent root= FXMLLoader.load(getClass().getResource("/APIS/Plan/GoogleMap.fxml"));
        Scene Gmaps = new Scene(root);
        GMapsStage.setScene(Gmaps);
        GMapsStage.show();   
    }
}