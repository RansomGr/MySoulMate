/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Profil;

import Entites.Profil.Profil;
import Entites.User.Client;
import Services.Profil.GestionnaireProfil;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private static Client profile_owner;

    public static void setProfile_owner(Client profile_owner) {
        Ui_Profil_FOController.profile_owner = profile_owner;
    }
    private GestionnaireProfil Gp;
    @FXML
    private ImageView photo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Gp= new GestionnaireProfil();
        init_data();
        // TODO 
    }    
    private void init_data()
    {
        int ID_Client=profile_owner.getID();
        corpulence_lb.setText(profile_owner.getProfil().getCaracteristique().getCorpulence());
        caractere_lb.setText(profile_owner.getProfil().getCaracteristique().getCaractere());
        yeux_lb.setText(profile_owner.getProfil().getCaracteristique().getYeux());
        cheveux_lb.setText(profile_owner.getProfil().getCaracteristique().getCheveux());
                alcool_lb.setText(profile_owner.getProfil().getCaracteristique().getAlcool());
                 tabac_lb.setText(profile_owner.getProfil().getCaracteristique().getTabac());
            taille_lb.setText(Float.toString(profile_owner.getProfil().getCaracteristique().getTaille()));
        statut_lb.setText(profile_owner.getProfil().getCaracteristique().getStatut());
                cuisine_lb.setText(profile_owner.getProfil().getCaracteristique().getCuisine());
                        profession_lb.setText(profile_owner.getProfil().getCaracteristique().getProfession());

       origine_lb.setText(profile_owner.getProfil().getCaracteristique().getOrigine());
pilosite_lb.setText(profile_owner.getProfil().getCaracteristique().getPilosite());
photo.setImage(new Image("/images/"+profile_owner.getProfil().getPhoto()));



        
    }
    
}
