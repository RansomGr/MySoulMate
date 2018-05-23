/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Profil;

import Entites.Profil.Actualite;
import Entites.Profil.Interaction;
import Entites.User.Utilisateur;

import Services.Profil.GestionnaireInteraction;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_Commentaire_FOController implements Initializable {

    @FXML
    private TextArea commentaire_tea;
    @FXML
    private ImageView owner_image;
    @FXML
    private MenuButton actions;
    /* Non FXML Variables */
    Utilisateur commentaire_owner;
    Actualite parent;
    MenuItem supprimer;
    MenuItem modifier;
    Interaction content;
    GestionnaireInteraction gi;
    Alert ConfirmDelete;
    ButtonType oui;
    ButtonType non;
    Ui_Create_New_Actualite_FOController ParentController;

    public Ui_Create_New_Actualite_FOController getParentController() {
        return ParentController;
    }

    public void setParentController(Ui_Create_New_Actualite_FOController ParentController) {
        this.ParentController = ParentController;
    }

    public Actualite getParent() {
        return parent;
    }

    public void setParent(Actualite parent) {
        this.parent = parent;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          gi = new GestionnaireInteraction();
    }

    public Interaction getContent() {
        return content;
    }

    public void setContent(Interaction content) {
        this.content = content;
        this.commentaire_owner=content.getOwner();
        this.parent=content.getActualite();
    }

      public void init_role() {// this will tell if this interaction is being seen by it's owner or the owner of the feed or a simple user 

        this.actions.getItems().clear();
        if (commentaire_owner.equals(MySoulMate.getLogged_in_Client())) {
            this.actions.getItems().add(modifier);
        }
        if (commentaire_owner.equals(MySoulMate.getLogged_in_Client()) || MySoulMate.getLogged_in_Client().equals((Utilisateur) parent.getCreateur())) {
            this.actions.getItems().add(supprimer);
        }

    }
   public void lets_go()
   {
  
         if (content != null) {
            oui = new ButtonType("Oui");
            non = new ButtonType("Non");
            ConfirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
            ConfirmDelete.setTitle("MysoulMate");
            ConfirmDelete.setContentText("Voulez vous vraiment supprimer ce commentaire ?");
            ConfirmDelete.setHeaderText("Gestion Commentaires");
            ConfirmDelete.getButtonTypes().clear();
            ConfirmDelete.getButtonTypes().addAll(oui, non);
            supprimer = new MenuItem("Supprimer");
            modifier = new MenuItem("Modifier");
           owner_image.setImage(new Image("http://localhost/MysoulMate-Symphony/web/images/"+content.getOwner().getProfil().getPhoto()));
           commentaire_tea.setText(content.getCommentaire());
            modifier.setOnAction(x -> {
                commentaire_tea.setEditable(true);
                commentaire_tea.setOpacity(1);
            });
            supprimer.setOnAction(x -> {
                try {
                    Optional<ButtonType> result_del = ConfirmDelete.showAndWait();
                    if (result_del.isPresent() && result_del.get() == oui) {
                        gi.remove(content);
                        ParentController.remove_commentaire(this);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Ui_Commentaire_FOController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            init_role();
        } else {
            actions.setVisible(false);
            commentaire_tea.setEditable(true);
            commentaire_tea.setOpacity(1);
            owner_image.setImage(new Image("http://localhost/MysoulMate-Symphony/web/images/"+MySoulMate.getLogged_in_Client().getProfil().getPhoto()));
        }
   }
    @FXML
    private void update_interaction(KeyEvent event) throws SQLException {
        if (actions.isVisible()) {
            if (event.getCode().equals(KeyCode.ENTER)) {
                content.setCommentaire(commentaire_tea.getText());
                gi.update(content);
                commentaire_tea.setEditable(false);
                commentaire_tea.setOpacity(0.8);
            } else if (event.getCode().equals(KeyCode.ESCAPE)) {
                commentaire_tea.setEditable(false);
                commentaire_tea.setOpacity(0.8);
            }
        } else {
            if (event.getCode().equals(KeyCode.ENTER)) {
                content =new Interaction(parent, MySoulMate.getLogged_in_Client(), commentaire_tea.getText());
                gi.create(content);// brb
                ParentController.add_new(content);
                commentaire_tea.clear();
                
            }
        }

    }

}
