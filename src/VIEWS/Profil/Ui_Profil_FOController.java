/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Profil;

import Entites.Profil.Actualite;
import Entites.User.Utilisateur;

import Services.Profil.GestionnaireActualite;
import Services.Profil.GestionnaireProfil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Sofiene
 */
public class Ui_Profil_FOController implements Initializable {

    @FXML
    private Label corpulence_lb;
    @FXML
    private Label origine_lb;
    @FXML
    private Label pilosite_lb;
    @FXML
    private Label profession_lb;
    @FXML
    private Label alcool_lb;
    @FXML
    private Label tabac_lb;
    @FXML
    private Label taille_lb;
    @FXML
    private Label cheveux_lb;
    @FXML
    private Label caractere_lb;
    @FXML
    private Label yeux_lb;
    @FXML
    private Label statut_lb;
    @FXML
    private Label cuisine_lb;
    @FXML
    private ImageView photo;
    @FXML
    private VBox actualites_vb;
    @FXML
    private DatePicker birthdate_dp;
    @FXML
    private Label gender_lab;
    @FXML
    private Label owner_name;
    @FXML
    private MenuButton actions;
    /* non fxml vars */
    private Utilisateur profile_owner;
    private GestionnaireProfil Gp;
    private List<Actualite> actualites;
    private GestionnaireActualite gactu;
    private List<Ui_Create_New_Actualite_FOController> ActualitesControllers;
    /* Owner params */
    MenuItem Parametres;
    MenuItem Profil;
    MenuItem Preferences;
    MenuItem Caracteristiques;
    /* watcher params */
    MenuItem ajouterAmi;
    /*friend params */
    MenuItem send_message;
    MenuItem unfriend;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        gactu = new GestionnaireActualite();
        actions.getItems().clear();
        Parametres = new MenuItem("Parametres");
        Profil = new MenuItem("Profil");
        Preferences = new MenuItem("Preferences");
        Caracteristiques = new MenuItem("Caracteristiques");
        ajouterAmi = new MenuItem("Ajouter Ami");
        send_message= new MenuItem("Message");
        unfriend = new MenuItem("retirer ami");

    }

    private void init_caracteristiques() {
        corpulence_lb.setText(profile_owner.getProfil().getCaracteristique_id().getCorpulence());
        caractere_lb.setText(profile_owner.getProfil().getCaracteristique_id().getCaractere());
        yeux_lb.setText(profile_owner.getProfil().getCaracteristique_id().getYeux());
        cheveux_lb.setText(profile_owner.getProfil().getCaracteristique_id().getCheveux());
        alcool_lb.setText(profile_owner.getProfil().getCaracteristique_id().getAlcool());
        tabac_lb.setText(profile_owner.getProfil().getCaracteristique_id().getTabac());
        taille_lb.setText(profile_owner.getProfil().getCaracteristique_id().getTaille());
        statut_lb.setText(profile_owner.getProfil().getCaracteristique_id().getStatut());
        cuisine_lb.setText(profile_owner.getProfil().getCaracteristique_id().getCuisine());
        profession_lb.setText(profile_owner.getProfil().getCaracteristique_id().getProfession());
        origine_lb.setText(profile_owner.getProfil().getCaracteristique_id().getOrigine());
        pilosite_lb.setText(profile_owner.getProfil().getCaracteristique_id().getPilosite());
    }

    public Utilisateur getProfile_owner() {
        return profile_owner;
    }

    public void setProfile_owner( Utilisateur profile_owner) throws SQLException {
        try {
            this.profile_owner = profile_owner;
            photo.setImage(new Image("/images/" + profile_owner.getProfil().getPhoto()));
            owner_name.setText(this.profile_owner.getNom());
            java.sql.Date d=new java.sql.Date(this.profile_owner.getDatanaissance().getTime());
            birthdate_dp.setValue(d.toLocalDate()) ;
            gender_lab.setText(profile_owner.getGender());
            init_caracteristiques();
            ActualitesControllers = new ArrayList<>();
            actualites = gactu.fetchAllById(this.profile_owner.getId());
            init_actions();
        } catch (IOException ex) {
            Logger.getLogger(Ui_Profil_FOController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void init_actions() throws IOException {

        if (MySoulMate.getLogged_in_Client().equals(profile_owner)) {
            actions.getItems().addAll(Parametres, Profil, Preferences, Caracteristiques);

            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/VIEWS/Profil/ui_Create_New_Actualite_FO.fxml"));
            StackPane actualite_w = fxml.load();
            Ui_Create_New_Actualite_FOController controller = fxml.<Ui_Create_New_Actualite_FOController>getController();
            controller.setParentController(this);
            controller.setParent(profile_owner);
            actualite_w.setMaxHeight(150);
            actualite_w.setMinHeight(150);
            actualites_vb.getChildren().add(actualite_w);
            
            for (Actualite x : actualites) {
                fxml = new FXMLLoader(getClass().getResource("/VIEWS/Profil/ui_Create_New_Actualite_FO.fxml"));
                actualite_w = fxml.load();
                controller = fxml.<Ui_Create_New_Actualite_FOController>getController();
                ActualitesControllers.add(controller);
                controller.setParentController(this);
                controller.setParent(profile_owner);
                controller.setContent(x);

                actualites_vb.getChildren().add(actualite_w);
            }
        }
        else
        {
               actions.getItems().add(ajouterAmi);
        }
    }

    public void remove_actualite(Ui_Create_New_Actualite_FOController controller) {
        actualites_vb.getChildren().remove(this.ActualitesControllers.indexOf(controller) + 1);
        ActualitesControllers.remove(controller);
    }

    public void add_new_actualite(Actualite post) {
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/VIEWS/Profil/ui_Create_New_Actualite_FO.fxml"));
            Node actualite_w = fxml.load();
            Ui_Create_New_Actualite_FOController controller = fxml.<Ui_Create_New_Actualite_FOController>getController();
            controller.setParentController(this);
          
            controller.setParent(profile_owner);
            controller.setContent(post);
            actualites_vb.getChildren().add(actualite_w);
        } catch (IOException ex) {
            Logger.getLogger(Ui_Profil_FOController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
