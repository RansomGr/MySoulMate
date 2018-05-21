/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Matching;

import Entites.Matching.Packaging;
import Entites.Profil.Caracteristique;

import Entites.User.Utilisateur;
import Services.Matching.GestionnaireCaracteristique;
import Services.User.GestionnaireUser;
import VIEWS.Profil.Ui_Profil_FOController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class Ui_FO_show_one_MatchingController implements Initializable {

    @FXML
    private ImageView photo_img;
    @FXML
    private Button inviter_btn;
    @FXML
    private Text compatibilite_text;
    @FXML
    private Text prenom_text;
    @FXML
    private Text ville_text;

    private Utilisateur the_matching;
    
    private int compatibilite_totale;

    public int getCompatibilite_totale() {
        return compatibilite_totale;
    }

    public void setCompatibilite_totale(int compatibilite_totale) {
        this.compatibilite_totale = compatibilite_totale;
    }

    public Utilisateur getThe_matching() {
        return the_matching;
    }

    public void setThe_matching(Utilisateur the_matching) {
        this.the_matching = the_matching;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void charger_matchings() {
        Services.Profil.GestionnaireCaracteristique gc = new Services.Profil.GestionnaireCaracteristique();
        GestionnaireUser gcl = new GestionnaireUser();
        photo_img.setImage(new Image("images/"+the_matching.getProfil().getPhoto()) );
        //photo_img.setImage(new Image("/images/" + the_matching.getProfil().getPhoto()));
        prenom_text.setText(the_matching.getPrenom());
        //ville_text.setText(the_matching.(get adresse) ) ;
   
        compatibilite_totale = ( gc.matching_selon_preference( the_matching , MySoulMate.getLogged_in_Client() ) + gcl.matching_selon_horoscope(the_matching , MySoulMate.getLogged_in_Client()) )/2;
        compatibilite_text.setText(compatibilite_totale+"");
    
    }

    @FXML
    private void inviter_ami(ActionEvent event) {
        try {
            MySoulMate.getMainController().open_profile(the_matching);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Ui_FO_show_one_MatchingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
