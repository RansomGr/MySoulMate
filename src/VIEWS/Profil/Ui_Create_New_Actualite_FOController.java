/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Profil;

import Entites.AbstractEntite;
import Entites.Profil.Actualite;
import Entites.Profil.Interaction;
import Entites.Profil.Mention_Jaime;
import Entites.User.Utilisateur;
import Services.Profil.GestionnaireActualite;
import Services.Profil.GestionnaireInteraction;
import Services.Profil.GestionnaireMention_Jaime;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_Create_New_Actualite_FOController implements Initializable {

    @FXML
    private ImageView publication_owner;
    @FXML
    private TextArea publication_tea;
    @FXML
    private Button thumbs_up;
    @FXML
    private Button thumbs_down;
    @FXML
    private MenuButton actions;
    @FXML
    private VBox commentaires_vb;
    @FXML
    private Button publier_pb;
    @FXML
    private GridPane Commentaire_holder;
    @FXML
    private Label like_count;
    @FXML
    private Label dislike_count;

    /* Non FXML Variables */
    private Actualite content;
    private GestionnaireActualite ga;
    private GestionnaireInteraction gi;
    private GestionnaireMention_Jaime gmj;
    private Utilisateur Parent;
    Alert ConfirmDelete;
    ButtonType oui;
    ButtonType non;
    private MenuItem supprimer;
    private MenuItem modifier;
    long Jaime;
    long Jaimepas;
    List<Interaction> Interactions;
    List<Mention_Jaime> Mentions;
    List<Ui_Commentaire_FOController> CommentControllers;
    Ui_Profil_FOController parentController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ga = new GestionnaireActualite();
        gi = new GestionnaireInteraction();
        gmj = new GestionnaireMention_Jaime();
        oui = new ButtonType("Oui");
        non = new ButtonType("Non");
        ConfirmDelete = new Alert(Alert.AlertType.CONFIRMATION);
        ConfirmDelete.setTitle("MysoulMate");
        ConfirmDelete.setContentText("Voulez vous vraiment supprimer cette publication ?");
        ConfirmDelete.setHeaderText("Gestion Publication");
        ConfirmDelete.getButtonTypes().clear();
        ConfirmDelete.getButtonTypes().addAll(oui, non);
        if (content == null) {// en mode ajout
            publication_owner.setImage(new Image("images/" + MySoulMate.getLogged_in_Client().getProfil().getPhoto()));
            publication_tea.setPromptText(publication_tea.getPromptText() + " " + MySoulMate.getLogged_in_Client().getNom() + "...");
            publier_pb.setVisible(true);
            actions.setVisible(false);
            Commentaire_holder.setMaxHeight(0);
            Commentaire_holder.setVisible(false);
            like_count.setVisible(false);
            dislike_count.setVisible(false);
            thumbs_up.setVisible(false);
            thumbs_down.setVisible(false);
        } else// en mode affichage
        {
            Platform.runLater(() -> {
                publication_tea.setEditable(false);
                publication_tea.setOpacity(0.8);
                like_count.setVisible(true);
                dislike_count.setVisible(true);
                thumbs_up.setVisible(true);
                thumbs_down.setVisible(true);
                Commentaire_holder.setMaxHeight(140);
                Commentaire_holder.setVisible(true);
            });
        }

    }

    public Actualite getContent() {
        return content;
    }

    public void setContent(Actualite content) {
        System.out.println(" cvonetn is :" + content);
        this.content = content;
        publication_owner.setImage(new Image("images/" + content.getCreateur().getProfil().getPhoto()));
        this.Parent = content.getCreateur();
        publication_tea.setText(content.getContenu());
        publication_tea.setEditable(false);
        publication_tea.setOpacity(0.8);
        like_count.setVisible(true);
        dislike_count.setVisible(true);
        thumbs_up.setVisible(true);
        thumbs_down.setVisible(true);
        Commentaire_holder.setMaxHeight(140);
        Commentaire_holder.setVisible(true);
        actions.setVisible(true);
        publier_pb.setVisible(false);
        init_actions();
        CommentControllers= new ArrayList<>();
        fetch_AllInteractions();
    }

    private void fetch_AllInteractions() {
        try {
            Mentions = gmj.fetchAllByTarget(content);
            Interactions = gi.fetchAllByParent(content);

        } catch (SQLException ex) {
            Logger.getLogger(Ui_Create_New_Actualite_FOController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (Mentions.size() > 0) {
            Jaime = Mentions.stream().filter(x -> x.getJaime().equals("yes")).count();
            Jaimepas = Mentions.stream().filter(x -> x.getJaime().equals("no")).count();
            Platform.runLater(() -> {
                like_count.setText(Jaime + "");
                dislike_count.setText(Jaimepas + "");
            });
        }
        Interactions.forEach((i) -> { // all comments that has been saved
            Node ui_commentaire;
         
            FXMLLoader fxml = new FXMLLoader(getClass().getResource("/VIEWS/Profil/ui_Commentaire_FO.fxml"));
            try {
                ui_commentaire = fxml.load();
                Ui_Commentaire_FOController Controller = fxml.<Ui_Commentaire_FOController>getController();
                Controller.setParentController(this);
                Controller.setContent(i);
                Controller.lets_go();
                CommentControllers.add(Controller);
                commentaires_vb.getChildren().add(ui_commentaire);
            } catch (IOException ex) {
                Logger.getLogger(Ui_Create_New_Actualite_FOController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Node ui_Ajouter_commentaire;//widget to add a new comment
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/VIEWS/Profil/ui_Commentaire_FO.fxml"));
        try {
            ui_Ajouter_commentaire = fxml.load();
            Ui_Commentaire_FOController Controller = fxml.<Ui_Commentaire_FOController>getController();
            Controller.setParent(content);
            Controller.setParentController(this);
            Controller.lets_go();
            commentaires_vb.getChildren().add(ui_Ajouter_commentaire);
            commentaires_vb.setAlignment(Pos.CENTER);
        } catch (IOException ex) {
            Logger.getLogger(Ui_Create_New_Actualite_FOController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void init_actions() {
        supprimer = new MenuItem("supprimer");
        modifier = new MenuItem("modifier");
        actions.getItems().clear();
        if (content.getCreateur().getId() == MySoulMate.getLogged_in_Client().getId()) {
            actions.getItems().add(modifier);
        }

        if (content.getCreateur().getId() == MySoulMate.getLogged_in_Client().getId() || content.getCreateur().getId() == MySoulMate.getLogged_in_Client().getId()) {
            actions.getItems().add(supprimer);
        }

        supprimer.setOnAction(x -> {
            try {
                Optional<ButtonType> result_del = ConfirmDelete.showAndWait();
                if (result_del.isPresent() && result_del.get() == oui) {
                    ga.remove(content);
                    parentController.remove_actualite(this);
                }

            } catch (SQLException ex) {
                Logger.getLogger(Ui_Create_New_Actualite_FOController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        modifier.setOnAction(x -> {
            publication_tea.setEditable(true);
            publication_tea.setOpacity(1);
            Platform.runLater(() -> {
                publication_tea.requestFocus();
            });
        });

    }

    public void remove_commentaire(Ui_Commentaire_FOController controller) {
       Platform.runLater(() -> {
            commentaires_vb.getChildren().remove(this.CommentControllers.indexOf(controller));
            CommentControllers.remove(controller);
   });
    }

    @FXML
    private void publier_action(ActionEvent event) {// need to add thread to handle this in real time
        try {

            this.content = new Actualite(Parent, this.publication_tea.getText(), "", MySoulMate.getLogged_in_Client());
            ga.create(this.content);
            parentController.add_new_actualite(content);

        } catch (SQLException ex) {
            Logger.getLogger(Ui_Create_New_Actualite_FOController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void like_this(ActionEvent event) {
        Platform.runLater(() -> {
            Mention_Jaime yep = null;
            try {
                yep = gmj.fetchOneByOwners(MySoulMate.getLogged_in_Client(), content);
            } catch (SQLException ex) {
                Logger.getLogger(Ui_Create_New_Actualite_FOController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (yep == null) {
                Jaime++;
                like_count.setText(Jaime + "");
                yep = new Mention_Jaime(this.content, MySoulMate.getLogged_in_Client(), "yes");
                Mentions.add(yep);
                try {
                    gmj.create(yep);
                } catch (SQLException ex) {
                    Logger.getLogger(Ui_Create_New_Actualite_FOController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (yep.getJaime().equals("no")) {
                    Jaimepas--;
                    dislike_count.setText(Jaimepas + "");
                    Jaime++;
                    like_count.setText(Jaime + "");
                    yep.setJaime("yes");
                    try {
                        gmj.update(yep);
                    } catch (SQLException ex) {
                        Logger.getLogger(Ui_Create_New_Actualite_FOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        gmj.remove(yep);
                        Jaime--;
                        like_count.setText(Jaime + "");
                    } catch (SQLException ex) {
                        Logger.getLogger(Ui_Create_New_Actualite_FOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    @FXML
    private void dislike_this(ActionEvent event) {
       Platform.runLater(() -> {
            Mention_Jaime nop = null;
            try {
                nop = gmj.fetchOneByOwners(MySoulMate.getLogged_in_Client(), content);
            } catch (SQLException ex) {
                Logger.getLogger(Ui_Create_New_Actualite_FOController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (nop == null) {
                Jaimepas++;
                dislike_count.setText(Jaimepas + "");
                nop = new Mention_Jaime(this.content, MySoulMate.getLogged_in_Client(), "no");
                Mentions.add(nop);
                try {
                    gmj.create(nop);
                } catch (SQLException ex) {
                    Logger.getLogger(Ui_Create_New_Actualite_FOController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (nop.getJaime().equals("yes")) {
                    Jaime--;
                    like_count.setText(Jaime + "");
                    Jaimepas++;
                    dislike_count.setText(Jaimepas + "");
                    nop.setJaime("no");
                    try {
                        gmj.update(nop);
                    } catch (SQLException ex) {
                        Logger.getLogger(Ui_Create_New_Actualite_FOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        gmj.remove(nop);
                        Jaimepas--;
                        dislike_count.setText(Jaimepas + "");
                    } catch (SQLException ex) {
                        Logger.getLogger(Ui_Create_New_Actualite_FOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

    }

    public Utilisateur getParent() {
        return Parent;
    }

    public void setParent(Utilisateur Parent) {
        this.Parent = Parent;
    }

    public Ui_Profil_FOController getParentController() {
        return parentController;
    }

    public void setParentController(Ui_Profil_FOController parentController) {
        this.parentController = parentController;
    }

    public void add_new(Interaction commentaire) {
        Interactions.add(commentaire);
        Node ui_commentaire;
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/VIEWS/Profil/ui_Commentaire_FO.fxml"));
        try {
            ui_commentaire = fxml.load();
            Ui_Commentaire_FOController Controller = fxml.<Ui_Commentaire_FOController>getController();
            Controller.setContent(commentaire);
            Controller.lets_go();
            
            CommentControllers.add(Controller);

            commentaires_vb.getChildren().add(commentaires_vb.getChildren().size()-1, ui_commentaire);
        } catch (IOException ex) {
            Logger.getLogger(Ui_Create_New_Actualite_FOController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
