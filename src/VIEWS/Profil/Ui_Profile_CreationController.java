/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Profil;

import Entites.Profil.Caracteristique;
import Entites.Profil.Profil;
import Entites.User.Utilisateur;
import Services.Profil.GestionnaireProfil;
import Services.User.GestionnaireUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_Profile_CreationController implements Initializable {

    @FXML
    private  StackPane step_container;
    private static StackPane step_c;
    @FXML
    private  Label title_of_step;
    private static  Label title_s;
    @FXML
    private  ImageView Step_img;
    private  static ImageView Step_ig;
    List<Node>Nodes;
    private static Caracteristique carac ;
    private static Profil prof;
    private static Alert InformationWindow;

    public static Caracteristique getCarac() {
        return carac;
    }

    public static void setCarac(Caracteristique carac) {
        Ui_Profile_CreationController.carac = carac;
    }

    public static Profil getProf() {
        return prof;
    }

    public static void setProf(Profil prof) {
        Ui_Profile_CreationController.prof = prof;
        prof.setCaracteristique_id(carac);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        step_c=step_container;
        title_s=title_of_step;
        Step_ig=Step_img;
        try {
            Nodes = new ArrayList<>();
        Parent ajoutcarac = FXMLLoader.load(getClass().getResource("/VIEWS/Profil/ui_FO_AjouterCaracteristique.fxml"));// first we go to caracs 
        step_container.getChildren().clear();
        step_container.getChildren().add(ajoutcarac);
        } catch (IOException ex) {
            Logger.getLogger(Ui_Profile_CreationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        InformationWindow = new Alert(Alert.AlertType.INFORMATION);
        InformationWindow.setTitle("MySoulMate");
        InformationWindow.setContentText("Votre Profil est Pret vos chances de trouver votre âme soeur ont  augmenté");
        InformationWindow.setHeaderText("Gestion Profil ");
        
        
    }
    public static void next_step() throws IOException, SQLException
    {
        if(title_s.getText().equals("Vos preferences"))
        {
           title_s.setText("Ajouter une photo de profil ");
           Step_ig.setImage(new Image("/images/deux.png"));
           Parent ajoutprofil = FXMLLoader.load(Ui_Profile_CreationController.class.getResource("/VIEWS/Profil/ui_Ajoute_new_Profil_FO.fxml"));
           step_c.getChildren().clear();
           step_c.getChildren().add(ajoutprofil);
        }
        else
        {
        GestionnaireProfil gp= new GestionnaireProfil();
        gp.create(Ui_Profile_CreationController.prof);
        Ui_Profile_CreationController.InformationWindow.show();
            GestionnaireUser gc = new GestionnaireUser();
        Profil p  = new Profil();
        p.setId(((List<Profil>)gp.fetchAll()).stream().mapToInt(w->w.getId()).max().getAsInt());
        MySoulMate.getLogged_in_Client().setProfil(p);
        Utilisateur c =MySoulMate.getLogged_in_Client();
        gc.update(c);
        MySoulMate.setLogged_in_Client(((List<Utilisateur>)gc.fetchAll()).stream().filter(x->x.getId()==c.getId()).findFirst().get());
        }
     }
    
}
