/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS;

import VIEWS.Matching.Ui_BO_AjoutPackagingController;
import VIEWS.User.Ui_Create_new_Admin_BOController;
import VIEWS.User.Ui_ListeAdmin_BOController;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_MainFrame_BOController implements Initializable {

    boolean Shown;
    Alert are_you_sureLogOut;
    ButtonType Oui;
    ButtonType Non;
    private final Stage MainStage = MySoulMate.getMainStage();
    @FXML
    private MenuButton Home_dl;
    @FXML
    private MenuButton User_dl;
    @FXML
    private MenuButton Event_dl;
    @FXML
    private MenuButton Plan_dl;
    @FXML
    private MenuButton Profile_dl;
    @FXML
    private MenuButton Match_dl;
    @FXML
    private MenuButton Relations_dl;
    @FXML
    private Button Logout_pb;
    @FXML
    private Button Hide_Show_pb;
    @FXML
    private GridPane Side_bar;
    @FXML
    private GridPane Content_grid;
    @FXML
    private Label Label_Module_name;
    private static Label Label_Module_name_ref;
    @FXML
    private StackPane Page_Viewer;
    private static StackPane Page_Viewer_ref;
    @FXML
    private Label Logged_in_Admin_name;

    /**
     * Initializes the controller class.
     */
    public static void Update_Admin_request() throws IOException {

        Node root;// Making a node
        root = FXMLLoader.load(Ui_MainFrame_BOController.class.getResource("/VIEWS/User/ui_Create_new_Admin_BO.fxml"));// Getting the View
        Page_Viewer_ref.getChildren().clear();
        Page_Viewer_ref.getChildren().add(root);// inserting the Node in the GridPane
        Label_Module_name_ref.setText("Modifier un Admin existent "); // Changing the header text
    }

    public static void Update_Plan_request() throws IOException {

        Node root;// Making a node
        root = FXMLLoader.load(Ui_MainFrame_BOController.class.getResource("/VIEWS/Plan/ui_Create_new_Plan_BO.fxml"));// Getting the View
        Page_Viewer_ref.getChildren().clear();
        Page_Viewer_ref.getChildren().add(root);// inserting the Node in the GridPane
        Label_Module_name_ref.setText("Modifier un Plan existent "); // Changing the header text
    }

    public static void Modifier_Packaging_request() throws IOException {

        Node root;// Making a node
        root = FXMLLoader.load(Ui_MainFrame_BOController.class.getResource("/VIEWS/Matching/ui_BO_AjoutPackaging.fxml"));// Getting the View
        Page_Viewer_ref.getChildren().clear();
        Page_Viewer_ref.getChildren().add(root);// inserting the Node in the GridPane
        Label_Module_name_ref.setText("Modifier un packaging existant "); // Changing the header text
    }

    private void init_workspaes_variables() {
        Label_Module_name_ref = Label_Module_name;
        Page_Viewer_ref = Page_Viewer;
        Logged_in_Admin_name.setText(MySoulMate.getLogged_in_Admin().getPrenom());

        // definition du fenetre logout 
        Oui = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
        Non = new ButtonType("Non", ButtonBar.ButtonData.OK_DONE);
        are_you_sureLogOut = new Alert(Alert.AlertType.CONFIRMATION, "", Oui, Non);
        are_you_sureLogOut.setHeaderText("Session");
        are_you_sureLogOut.setTitle("MysoulMate");
        are_you_sureLogOut.setContentText("Vous êtes sur le point \n  De se deconnecter \n Continuez ?");
    }

    private void init_DashBoard_Actions() {
        this.Home_dl.getItems().remove(0, 2);
        MenuItem Dash = new MenuItem("Dashboard");
        MenuItem Stat = new MenuItem("Statistique Global");
        Dash.setOnAction(
                (a) -> {
                    System.out.println("DashClicked");
                }
        );

        Stat.setOnAction(
                (a) -> {
                    try {
                        Node root;// Making a node
                        root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Stat_Clients_BO.fxml"));// Getting the View
                        Page_Viewer.getChildren().clear();
                        Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane
                        Label_Module_name.setText("Statisitiques General"); // Changing the header text
                    } catch (IOException ex) {
                        Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );

        this.Home_dl.getItems().addAll(Dash, Stat);

    }

    private void init_User_Actions() {
        MenuItem ListAdmin = new MenuItem("Liste Administrateurs");
        MenuItem NewAdmin = new MenuItem("Ajouter Nouveau  Administrateur");
        MenuItem ListClient = new MenuItem("Liste Clients");

        this.User_dl.getItems().remove(0, 2);
        this.User_dl.getItems().addAll(ListAdmin, NewAdmin, ListClient);

        ListAdmin.setOnAction(
                (a) -> {
                    try {
                        Ui_Create_new_Admin_BOController.setAdmin_to_be_modified(null);
                        Node root;// Making a node
                        root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_ListeAdmin_BO.fxml"));// Getting the View
                        Page_Viewer.getChildren().clear();
                        Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane
                        Label_Module_name.setText(ListAdmin.getText()); // Changing the header text

                    } catch (IOException ex) {
                        Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );

        NewAdmin.setOnAction(
                (a) -> {

                    try {
                        Ui_Create_new_Admin_BOController.setAdmin_to_be_modified(null);
                        Node root;// Making a node
                        root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Create_new_Admin_BO.fxml"));// Getting the View
                        Page_Viewer.getChildren().clear();
                        Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane

                        Label_Module_name.setText(NewAdmin.getText()); // Changing the header text

                    } catch (IOException ex) {
                        Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
        );
        ListClient.setOnAction(
                (a) -> {
                    try {
                        Node root;// Making a node
                        root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_ListeClient_BO.fxml"));// Getting the View
                        Page_Viewer.getChildren().clear();
                        Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane

                        Label_Module_name.setText(ListClient.getText()); // Changing the header text
                    } catch (IOException ex) {
                        Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );

    }

    private void init_Profil_Actions() {
        MenuItem ListProfil = new MenuItem("Liste Profil");
        MenuItem NewProfil = new MenuItem("Ajouter nouveau Profil");
        MenuItem statistique = new MenuItem("Statistique");

        this.Profile_dl.getItems().remove(0, 2);
        this.Profile_dl.getItems().addAll(ListProfil, NewProfil, statistique);

        ListProfil.setOnAction(
                (a) -> {
                    try {
                        Ui_Create_new_Admin_BOController.setAdmin_to_be_modified(null);
                        Node root;// Making a node
                        root = FXMLLoader.load(getClass().getResource("/VIEWS/Profil/ui_ListeProfil.fxml"));// Getting the View
                        Page_Viewer.getChildren().clear();
                        Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane
                        Label_Module_name.setText(ListProfil.getText()); // Changing the header text

                    } catch (IOException ex) {
                        Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );

        NewProfil.setOnAction(
                (a) -> {

                    try {
                        Ui_Create_new_Admin_BOController.setAdmin_to_be_modified(null);
                        Node root;// Making a node
                        root = FXMLLoader.load(getClass().getResource("/VIEWS/Profil/ui_Ajoute_new_Profil_FO.fxml"));// Getting the View
                        Page_Viewer.getChildren().clear();
                        Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane

                        Label_Module_name.setText(NewProfil.getText()); // Changing the header text

                    } catch (IOException ex) {
                        Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
        );
        ListProfil.setOnAction(
                (a) -> {
                    try {
                        Node root;// Making a node
                        root = FXMLLoader.load(getClass().getResource("/VIEWS/Profil/ui_ListeProfil.fxml"));// Getting the View
                        Page_Viewer.getChildren().clear();
                        Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane

                        Label_Module_name.setText(ListProfil.getText()); // Changing the header text
                    } catch (IOException ex) {
                        Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );

    }

    private void init_Matching_Actions() {
        MenuItem ListePackaging = new MenuItem("Liste Packagings");
        MenuItem NewPackaging = new MenuItem("Ajouter Nouveau Packaging");

        this.Match_dl.getItems().remove(0, 2);
        this.Match_dl.getItems().addAll(ListePackaging, NewPackaging);

        NewPackaging.setOnAction(
                (a) -> {

                    try {
                        Node root;// Making a node
                        Ui_BO_AjoutPackagingController.set_Packaging_modif(null);//setting le mode ajour
                        root = FXMLLoader.load(getClass().getResource("/VIEWS/Matching/ui_BO_AjoutPackaging.fxml"));// Getting the View
                        Page_Viewer.getChildren().clear();
                        Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane

                        Label_Module_name.setText(NewPackaging.getText()); // Changing the header text
                    } catch (IOException ex) {
                        Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
        );

        ListePackaging.setOnAction(
                (a) -> {
                    try {
                        Node root;// Making a node
                        root = FXMLLoader.load(getClass().getResource("/VIEWS/Matching/ui_BO_ListePackagings.fxml"));// Getting the View
                        Page_Viewer.getChildren().clear();
                        Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane
                        Label_Module_name.setText(ListePackaging.getText()); // Changing the header text
                    } catch (IOException ex) {
                        Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );

    }

    private void init_Plan_Actions() {
        MenuItem ListPlan = new MenuItem("Liste Plans");
        MenuItem NewPlan = new MenuItem("Ajouter Nouveau Plan");

        this.Plan_dl.getItems().remove(0, 2);
        this.Plan_dl.getItems().addAll(ListPlan, NewPlan);

        ListPlan.setOnAction(
                (a) -> {
                    try {
                        //Ui_Create_new_Plan_BOController.setPlan_to_be_modified(null);
                        Node root;// Making a node
                        root = FXMLLoader.load(getClass().getResource("/VIEWS/Plan/ui_ListePlan_BO.fxml"));// Getting the View
                        Page_Viewer.getChildren().clear();
                        Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane
                        Label_Module_name.setText(ListPlan.getText()); // Changing the header text

                    } catch (IOException ex) {
                        Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );

        NewPlan.setOnAction(
                (a) -> {

                    try {
                        //Ui_Create_new_Admin_BOController.setAdmin_to_be_modified(null);
                        Node root;// Making a node
                        root = FXMLLoader.load(getClass().getResource("/VIEWS/Plan/ui_Create_new_Plan_BO.fxml"));// Getting the View
                        Page_Viewer.getChildren().clear();
                        Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane

                        Label_Module_name.setText(NewPlan.getText()); // Changing the header text

                    } catch (IOException ex) {
                        Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );

    }

    private void init_Event_Actions() {
        MenuItem ListEvent = new MenuItem("Liste des évènement");
        MenuItem NewEvent = new MenuItem("Ajouter un nouvel évènement");
        MenuItem Statistiques = new MenuItem("Statistiques des évènements");

        this.Event_dl.getItems().remove(0, 2);
        this.Event_dl.getItems().addAll(ListEvent, NewEvent, Statistiques);

        ListEvent.setOnAction(
                (a) -> {
                    try {
                        Ui_Create_new_Admin_BOController.setAdmin_to_be_modified(null);
                        Node root;// Making a node
                        root = FXMLLoader.load(getClass().getResource("/VIEWS/Evenement/ui_ListeEvenement.fxml"));// Getting the View
                        Page_Viewer.getChildren().clear();
                        Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane
                        Label_Module_name.setText(ListEvent.getText()); // Changing the header text

                    } catch (IOException ex) {
                        Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );

        NewEvent.setOnAction(
                (a) -> {

                    try {
                        Ui_Create_new_Admin_BOController.setAdmin_to_be_modified(null);
                        Node root;// Making a node
                        root = FXMLLoader.load(getClass().getResource("/VIEWS/Evenement/ui_evenement_BO.fxml"));// Getting the View
                        Page_Viewer.getChildren().clear();
                        Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane

                        Label_Module_name.setText(NewEvent.getText()); // Changing the header text

                    } catch (IOException ex) {
                        Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
        );
        Statistiques.setOnAction(
                (a) -> {
                    try {
                        Node root;// Making a node
                        root = FXMLLoader.load(getClass().getResource("/VIEWS/Evenement/ui_statistiques_BO.fxml"));// Getting the View
                        Page_Viewer.getChildren().clear();
                        Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane

                        Label_Module_name.setText(Statistiques.getText()); // Changing the header text
                    } catch (IOException ex) {
                        Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );

    }

    private void init_Relation_Actions() {
        MenuItem ListeRelation = new MenuItem("Liste Relations");
        MenuItem AjoutMoment = new MenuItem("Ajout Conseil");
        MenuItem StatR = new MenuItem("Statistiques Relations");

        this.Relations_dl.getItems().remove(0, 2);
        this.Relations_dl.getItems().addAll(ListeRelation, AjoutMoment, StatR);

        ListeRelation.setOnAction(
                (a) -> {
                    try {
                        Node root;// Making a node
                        root = FXMLLoader.load(getClass().getResource("/VIEWS/Relation/ui_ListeRelation_BO.fxml"));// Getting the View
                        Page_Viewer.getChildren().clear();
                        Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane
                        Label_Module_name.setText(ListeRelation.getText()); // Changing the header text
                    } catch (IOException ex) {
                        Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );
        AjoutMoment.setOnAction(
                (a) -> {
                    try {
                        Node root;// Making a node
                        root = FXMLLoader.load(getClass().getResource("/VIEWS/Relation/AjoutConseil.fxml"));// Getting the View
                        Page_Viewer.getChildren().clear();
                        Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane
                        Label_Module_name.setText(AjoutMoment.getText()); // Changing the header text
                    } catch (IOException ex) {
                        Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
        StatR.setOnAction(
                (a) -> {
                    try {
                        Node root;// Making a node
                        root = FXMLLoader.load(getClass().getResource("/VIEWS/Relation/charts.fxml"));// Getting the View
                        Page_Viewer.getChildren().clear();
                        Page_Viewer.getChildren().add(root);// inserting the Node in the GridPane
                        Label_Module_name.setText("Statisitiques General"); // Changing the header text
                    } catch (IOException ex) {
                        Logger.getLogger(Ui_MainFrame_BOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Shown = true; // if side bar is shown init it because naturally it's shown
        init_DashBoard_Actions();
        init_User_Actions();
        init_Matching_Actions();
        init_Plan_Actions();
        init_Event_Actions();
        init_Profil_Actions();
        init_Relation_Actions();
        init_workspaes_variables();

    }

    @FXML
    private void hide_show_menu_pb(ActionEvent event) {
        int AnimationDuration = 400; // ms

        if (Shown) {

            ScaleTransition Hide = new ScaleTransition();
            TranslateTransition Move_left = new TranslateTransition();
            TranslateTransition Move_leftGrid = new TranslateTransition();

            Move_leftGrid.setDuration(Duration.millis(AnimationDuration));
            Move_left.setDuration(Duration.millis(AnimationDuration));
            Hide.setDuration(Duration.millis(AnimationDuration));

            Move_leftGrid.setNode(Content_grid);// setting Target of animation
            Hide.setNode(Side_bar);
            Move_left.setNode(Side_bar);

            Move_leftGrid.setToX(-175);
            Move_left.setToX(-175);
            Hide.setToX(0);

            ParallelTransition GroupAnimation = new ParallelTransition(Hide, Move_left, Move_leftGrid);

            GroupAnimation.play();
            GroupAnimation.setOnFinished((ActionEvent ev) -> {
                if (Hide_Show_pb.getText().equals("<-")) {
                    Move_leftGrid.setFromX(0);
                    Move_leftGrid.setToX(0);
                    GroupAnimation.play();
                    Hide_Show_pb.setText("->");
                    Side_bar.maxWidthProperty().set(0);
                    Shown = false;
                }

            });

        } else// hidden
        {
            ScaleTransition Show = new ScaleTransition();
            TranslateTransition Move_right = new TranslateTransition();
            TranslateTransition Move_rightGrid = new TranslateTransition();

            Move_rightGrid.setDuration(Duration.millis(AnimationDuration));
            Move_right.setDuration(Duration.millis(AnimationDuration));
            Show.setDuration(Duration.millis(AnimationDuration));

            Move_rightGrid.setNode(Content_grid);// setting Target of animation
            Show.setNode(Side_bar);
            Move_right.setNode(Side_bar);

            Move_rightGrid.setFromX(-175);
            Move_rightGrid.setToX(0);
            Move_right.setFromX(-175);
            Move_right.setToX(0);
            Show.setFromX(0);
            Show.setToX(1);
            ParallelTransition GroupAnimation = new ParallelTransition(Show, Move_right, Move_rightGrid);

            GroupAnimation.play();
            Side_bar.setMaxWidth(175);
            GroupAnimation.setOnFinished((ActionEvent ev) -> {
                if (Hide_Show_pb.getText().equals("->")) {
                    Hide_Show_pb.setText("<-");
                    Shown = true;
                }
            });

        }

    }

    @FXML
    private void log_me_out(ActionEvent event) throws IOException {

        Optional<ButtonType> result = are_you_sureLogOut.showAndWait();
        if (result.isPresent() && result.get() == Oui) {
            MySoulMate.setLogged_in_Admin(null);
            Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Login_BO.fxml"));
            Scene scene = new Scene(root);
            MySoulMate.getMainStage().setScene(scene);
        }

    }

}
