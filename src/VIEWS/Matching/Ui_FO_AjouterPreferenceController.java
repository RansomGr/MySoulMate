/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Matching;

import Entites.Profil.Caracteristique;
import Services.Matching.GestionnaireCaracteristique;
import Services.Profil.GestionnaireProfil;
import VIEWS.Profil.Ui_Profil_FOController;
import VIEWS.Profil.Ui_Profile_CreationController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class Ui_FO_AjouterPreferenceController implements Initializable {

    @FXML
    private ComboBox<String> silouhette_cb;
    @FXML
    private ComboBox<String> pilosite_cb;
    @FXML
    private ComboBox<String> origine_cb;
    @FXML
    private TextField profession_tf;
    @FXML
    private ComboBox<String> caractere_cb;
    @FXML
    private Spinner<Integer> taille_min_sp;
    private Spinner<Integer> taille_max_sp;
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
    private ComboBox<String> ville_cb;
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
    private CheckBox horoscope_cb;

    ToggleGroup tabas = new ToggleGroup();
    ToggleGroup Alcools = new ToggleGroup();

    String Action;
    String Message;
    private Alert InformationWindow = new Alert(Alert.AlertType.INFORMATION);
    private Alert ErrorWindow = new Alert(Alert.AlertType.ERROR);
    private Alert WarningWindow = new Alert(Alert.AlertType.WARNING);
    @FXML
    private Text Taille_text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_node();
        init_actions();
    }
  private void init_values()
  {
      Caracteristique pref =MySoulMate.getLogged_in_Client().getProfil().getPreference();
      if(pref!=null)
      {
          caractere_cb.setValue(pref.getCaractere());
          tabas.selectToggle(tabac_oui_rb);
      }
  }
    private void init_node() {
        if (MySoulMate.getLogged_in_Client().getGender().equals("Homme")) {
            Taille_text.setText("Taille Maximum");

        } else if (MySoulMate.getLogged_in_Client().getGender().equals("Femme")) {
            Taille_text.setText("Taille Minimum");
        } else {
            Taille_text.setText("Taille Souhaitée");
        }

        ville_cb.getItems().addAll("Tous", "Ariana", "Beja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Kef", "Mahdia", "Mannouba", "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouene");
        cuisine_cb.getItems().addAll("Fast Food", "Vegan", "Bio", "Sushi", "Végétarienne");
        pilosite_cb.getItems().addAll("imberbe", "poilu");
        caractere_cb.getItems().addAll("Extraverti", "Timide", "Normal");
        origine_cb.getItems().addAll("Arabe", "Europienne", "Africaine", "Métisse");
        silouhette_cb.getItems().addAll("Normal", "Mince", "Dodu");
        cheveux_cb.getItems().addAll("Crêpu", "Lisse", "Rasé", "longs", "Mi-longs");
        statut_cb.getItems().addAll("Divorcé", "Veuf", "Célibataire", "Marié");
        yeux_cb.getItems().addAll("Noir", "Marron", "Jaune", "Bleu", "Vert");
    }

    private void init_actions() {
        tabac_oui_rb.setToggleGroup(tabas);
        tabac_non_rb.setToggleGroup(tabas);
        tabac_indifferent_rb.setToggleGroup(tabas);

        alcool_oui_rb.setToggleGroup(Alcools);
        alcool_non_rb.setToggleGroup(Alcools);
        alcool_indifferent_rb.setToggleGroup(Alcools);

        SpinnerValueFactory<Integer> ValueFac = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99, 00, 1);
        taille_min_sp.setValueFactory(ValueFac);

    }

    @FXML
    private void Valider(ActionEvent event) throws SQLException, IOException {

        RadioButton choix_alchool;
        RadioButton choix_tabac;

        choix_alchool = (RadioButton) Alcools.getSelectedToggle();
        choix_tabac = (RadioButton) tabas.getSelectedToggle();
        System.out.println(taille_min_sp.getValue().toString());
        System.out.println("taille:" + taille_min_sp.getValue().toString());
        Caracteristique caracteristique
                = new Caracteristique(
                        silouhette_cb.getValue(),
                        pilosite_cb.getValue(),
                        origine_cb.getValue(),
                        profession_tf.getText(),
                        choix_alchool.getText(),
                        choix_tabac.getText(),
                        taille_min_sp.getValue(),
                        cheveux_cb.getValue(),
                        yeux_cb.getValue(),
                        caractere_cb.getValue(),
                        statut_cb.getValue(),
                        cuisine_cb.getValue()
                );
        GestionnaireCaracteristique gc = new GestionnaireCaracteristique();
        gc.create(caracteristique);
        caracteristique.setID(((List<Caracteristique>) gc.fetchAll()).stream().mapToInt(x -> x.getID()).max().getAsInt());
        GestionnaireProfil gp = new GestionnaireProfil();
        MySoulMate.getLogged_in_Client().getProfil().setPreference(caracteristique);
        gp.update(MySoulMate.getLogged_in_Client().getProfil());
    }

    @FXML
    private void Reset(ActionEvent event) {
    }

}
