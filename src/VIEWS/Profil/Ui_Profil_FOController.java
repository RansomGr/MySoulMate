/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Profil;

import Entites.Profil.Actualite;
import Entites.Profil.Profil;
import Entites.User.Client;
import Services.Profil.GestionnaireActualite;
import Services.Profil.GestionnaireProfil;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
    @FXML
    private VBox actualite_pane;
  
    private static Actualite actualite;

    /**
     * Initializes the controller class.
     * @param profil
     */
   
    @FXML
    private ImageView client_photo;
    @FXML
    private Label user_name;
    @FXML
    private TextArea statut_tx;
    @FXML
    private Button Publier;
    private int i;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Gp= new GestionnaireProfil();
        init_data();
        // TODO 
        
        GestionnaireActualite gact=new GestionnaireActualite();
        List<Actualite> actualites;
        try {
            actualites=  ((List<Actualite>)gact.fetchAllById(profile_owner.getID()));
          Parent root;
                 
           FXMLLoader FXML;
            
              i=0;
             int j=0;
        for (Actualite act : actualites) {
            FXML=new FXMLLoader(getClass().getResource("/VIEWS/Profil/ui_actualite.fxml"));
            root = FXML.load();
            Ui_actualiteController con =FXML.<Ui_actualiteController>getController();
            con.setActualite(act);
            con.setOwner(profile_owner);
            con.put_values();
            actualite_pane.getChildren().add(root);  
            System.out.println("i="+i);
          // actualite_pane.se
           // actualite_pane.getChildren().get(j).relocate(5, i);
         
           //  i+=95;
             j++;
        }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Ui_Profil_FOController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         
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

    @FXML
    private void publier_actualiter(ActionEvent event) throws SQLException, IOException {
        GestionnaireActualite ga= new GestionnaireActualite();
        Actualite act=new Actualite(this.profile_owner,this.statut_tx.getText(),"photo");
        ga.create(act);
        Parent root;
        FXMLLoader FXML;
        FXML=new FXMLLoader(getClass().getResource("/VIEWS/Profil/ui_actualite.fxml"));
            root = FXML.load();
            Ui_actualiteController con =FXML.<Ui_actualiteController>getController();
            con.setActualite(act);
            con.setOwner(profile_owner);
            con.put_values();
            actualite_pane.getChildren().add(root);  
            System.out.println("i="+i);          
        //    actualite_pane.getChildren().get(actualite_pane.getChildren().size()-1).relocate(5, i);     
       //     i+=95;       
    }
   
}
