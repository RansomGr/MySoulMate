/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS;

import ChatClient.BubbleSpec;
import ChatClient.BubbledLabel;
import ChatClient.CellRenderer;
import ChatClient.ChatBoxController;
import ChatClient.VoicePlayback;
import Entites.Events.Events;
import Listner.Listener;

import Entites.User.Utilisateur;
import Services.Events.GestionnaireEvent;

import Services.Relation.GestionnaireRelation;
import Services.User.GestionnaireUser;
import VIEWS.Evenement.Ui_even_FOController;
import com.messages.Message;
import com.messages.Status;
import VIEWS.Profil.Ui_Profil_FOController;
import VIEWS.Relation.Ui_InterfaceRE_FOController;
import com.messages.MessageType;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import mysoulmate.MySoulMate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_MainFrame_FOController implements Initializable {

    private int AnimationDuration;
    @FXML
    private Hyperlink create_new_account_hl;
    @FXML
    private StackPane Content_pane;
    @FXML
    private AnchorPane bonplans_and_evennements;
    private boolean Shown;
    @FXML
    private StackPane menu_bar;
    @FXML
    private Button hide_menu;
    @FXML
    private Button show_menu;
    @FXML
    private Label connected_friend_nbr;
    @FXML
    private ComboBox status_combobox;
    @FXML
    private ListView<Utilisateur> connected_friends;
    private Map<Utilisateur, ListView<HBox>> conversations;// who sent me a message , and the conversation tha took place between us 
    private Logger logger = LoggerFactory.getLogger(Ui_MainFrame_FOController.class);
    @FXML
    private HBox chat_windows;
    private static List<ChatBoxController> static_chat_windows;
    @FXML
    private ListView<HBox> messages;
    @FXML
    private Label msg_count;
    private boolean messages_shown;
    @FXML
    private Button menu_matching_btn;
    @FXML
    private Button messages_btn;
    private VBox event_vb;

    private int unread_msg_count;
    @FXML
    private TextField recherche_dyn;

    public int getUnread_msg_count() {
        return unread_msg_count;
    }

    public void setUnread_msg_count(int unread_msg_count) {
        this.unread_msg_count = unread_msg_count;
        if (this.unread_msg_count < 0) {
            this.unread_msg_count = 0;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_combobox();
        conversations = new HashMap();
        Shown = true;
        messages_shown = false;
        msg_count.setText("0");
        AnimationDuration = 400;//ms
        static_chat_windows = new ArrayList<>();

        unread_msg_count = 0;
        messages.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GestionnaireUser gc = new GestionnaireUser();
                Utilisateur partner = null;

                int id = Integer.parseInt(messages.getSelectionModel().getSelectedItem().getId());
                if (id != 0) {
                    partner = gc.fetchOneById(id);
                }

                boolean already_there = false;
                for (int i = 0; i < static_chat_windows.size(); i++) {
                    if (static_chat_windows.get(i).getPartner().equals(partner)) {
                        already_there = true;
                    }
                }
                if (!already_there) {
                    System.out.println("i am about to delete this");
                    try {
                        FXMLLoader fmxlLoader = new FXMLLoader(getClass().getResource("/ChatClient/ChatBox.fxml"));
                        Parent root = fmxlLoader.load();
                        ChatBoxController controller = fmxlLoader.<ChatBoxController>getController();
                        controller.setPartner(partner);
                        messages.getSelectionModel().clearSelection();
                        controller.setConversation_pane(conversations.get(partner));
                        static_chat_windows.add(controller);
                        root.setTranslateY(-130);
                        root.setTranslateX(20);
                        chat_windows.getChildren().add(root);
                    } catch (IOException ex) {
                        java.util.logging.Logger.getLogger(Ui_MainFrame_FOController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    connected_friends.getSelectionModel().clearSelection();
                    for (ChatBoxController x : static_chat_windows) {
                        if (x.getPartner().equals(partner)) {
                            if (!x.isShown()) {
                                x.showhide_window(null);
                            }
                        }
                    }

                }

            }

        });
        connected_friends.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Utilisateur c = connected_friends.getSelectionModel().getSelectedItem();
                    boolean already_there = false;
                    for (int i = 0; i < static_chat_windows.size(); i++) {
                        if (static_chat_windows.get(i).getPartner().equals(c)) {
                            already_there = true;
                        }
                    }
                    if (!already_there) {
                        FXMLLoader fmxlLoader = new FXMLLoader(getClass().getResource("/ChatClient/ChatBox.fxml"));
                        Parent root = fmxlLoader.load();
                        ChatBoxController controller = fmxlLoader.<ChatBoxController>getController();
                        controller.setPartner(c);
                        connected_friends.getSelectionModel().clearSelection();
                        static_chat_windows.add(controller);
                        if (conversations.containsKey(c)) {
                            controller.setConversation_pane(conversations.get(c));
                        }
                        root.setTranslateY(-130);
                        root.setTranslateX(20);
                        chat_windows.getChildren().add(root);
                    } else {
                        connected_friends.getSelectionModel().clearSelection();
                        for (ChatBoxController x : static_chat_windows) {
                            if (x.getPartner().equals(c)) {
                                if (!x.isShown()) {
                                    x.showhide_window(null);
                                }
                            }

                        }

                    }
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(Ui_MainFrame_FOController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    @FXML
    private void load_my_profil(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/VIEWS/Profil/ui_Profil_FO.fxml"));
        Node root = fxml.load();
        Ui_Profil_FOController controller = fxml.<Ui_Profil_FOController>getController();
        controller.setProfile_owner(MySoulMate.getLogged_in_Client());
        Content_pane.getChildren().clear();
        Content_pane.getChildren().add(root);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        MySoulMate.setLogged_in_Client(null);
        Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Login_FO.fxml"));
        Scene sene = new Scene(root);
        MySoulMate.getMainStage().setScene(sene);
        Platform.runLater(() -> {
            try {
                MySoulMate.getListener().stop();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Ui_MainFrame_FOController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    @FXML
    private void hide_menu(ActionEvent event) {
        if (Shown) {
            ScaleTransition Hide = new ScaleTransition();
            TranslateTransition Move_Right = new TranslateTransition();
            TranslateTransition Move_RightGrid = new TranslateTransition();
            TranslateTransition GoUp = new TranslateTransition();
            FadeTransition Appear = new FadeTransition();
            //70   
            Move_RightGrid.setDuration(Duration.millis(AnimationDuration));
            Move_Right.setDuration(Duration.millis(AnimationDuration));
            Hide.setDuration(Duration.millis(AnimationDuration));
            GoUp.setDuration(Duration.millis(AnimationDuration));
            Appear.setDuration(Duration.millis(AnimationDuration));

            Move_RightGrid.setNode(Content_pane);// setting Target of animation
            Hide.setNode(menu_bar);
            Move_Right.setNode(menu_bar);
            GoUp.setNode(show_menu);
            Appear.setNode(show_menu);

            Move_RightGrid.setToX(230);
            Move_Right.setToX(230);
            Hide.setToX(0);
            GoUp.setFromY(32);
            GoUp.setToY(0);
            Appear.setFromValue(0);
            Appear.setToValue(1);

            ParallelTransition GroupAnimation = new ParallelTransition(Hide, Move_Right, Move_RightGrid, GoUp, Appear);

            GroupAnimation.play();
            GroupAnimation.setOnFinished((ActionEvent ev) -> {
                if (Shown) {
                    GroupAnimation.getChildren().remove(GoUp);
                    GroupAnimation.getChildren().remove(Appear);
                    Move_RightGrid.setFromX(0);
                    Move_RightGrid.setToX(0);
                    GroupAnimation.play();
                    hide_menu.setText("MENU");
                    menu_bar.maxWidthProperty().set(0);
                    Shown = false;
                    show_menu.setOpacity(1);
                }

            });

        }

    }

    @FXML
    private void show_menu(ActionEvent event) {
        if (!Shown)// hidden
        {
            ScaleTransition Show = new ScaleTransition();
            TranslateTransition Move_Left = new TranslateTransition();
            TranslateTransition Move_LeftGrid = new TranslateTransition();
            TranslateTransition GoDown = new TranslateTransition();
            FadeTransition Dissapear = new FadeTransition();
            Move_LeftGrid.setDuration(Duration.millis(AnimationDuration));
            Move_Left.setDuration(Duration.millis(AnimationDuration));
            Show.setDuration(Duration.millis(AnimationDuration));
            GoDown.setDuration(Duration.millis(AnimationDuration));
            Dissapear.setDuration(Duration.millis(AnimationDuration));

            Move_LeftGrid.setNode(Content_pane);// setting Target of animation
            Show.setNode(menu_bar);
            Move_Left.setNode(menu_bar);
            GoDown.setNode(show_menu);
            Dissapear.setNode(show_menu);

            Move_LeftGrid.setFromX(230);
            Move_LeftGrid.setToX(0);
            Move_Left.setFromX(230);
            Move_Left.setToX(0);
            Show.setFromX(0);
            Show.setToX(1);
            GoDown.setFromY(0);
            GoDown.setToY(32);
            Dissapear.setFromValue(1);
            Dissapear.setToValue(0);
            ParallelTransition GroupAnimation = new ParallelTransition(Show, Move_Left, Move_LeftGrid, GoDown, Dissapear);

            GroupAnimation.play();
            menu_bar.setMaxWidth(230);
            GroupAnimation.setOnFinished((ActionEvent ev) -> {
                if (!Shown) {
                    GroupAnimation.getChildren().remove(GoDown);
                    GroupAnimation.getChildren().remove(Dissapear);
                    hide_menu.setText("MENU");
                    Shown = true;
                }
            });

        }

    }

    private void init_combobox() {
        status_combobox.getItems().addAll("Online", "Busy", "Away");
        status_combobox.getSelectionModel().select(0);
        status_combobox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    System.out.println("Sofien changed status to " + newValue);
                    Listener.sendStatusUpdate(Status.valueOf(newValue.toUpperCase()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setOnlineLabel(String usercount) {
        Platform.runLater(() -> connected_friend_nbr.setText(Integer.parseInt(usercount) - 1 + ""));
    }

    public void setUserList(Message msg) {
        logger.info("setUserList() method Enter");
        Platform.runLater(() -> {
            ObservableList<Utilisateur> users = FXCollections.observableList(msg.getUsers());
            users.remove(MySoulMate.getLogged_in_Client());
            connected_friends.setItems(users);
            connected_friends.setCellFactory(new CellRenderer());
            setOnlineLabel(String.valueOf(msg.getUserlist().size()));
        });
        logger.info("setUserList() method Exit");
    }

    public void newUserNotification(Message msg) {
        System.out.println("sending a notification to all users");
        System.out.println(msg.getSender());
        if (msg.getID() != MySoulMate.getLogged_in_Client().getId()) {
            Utilisateur c = null;
            GestionnaireUser gc = new GestionnaireUser();

            c = gc.fetchOneById(msg.getID());
            msg.setSender(c);

            Platform.runLater(() -> {
                Image profileImg = new Image("http://localhost/MysoulMate-Symphony/web/images/" + msg.getSender().getProfil().getPhoto(), 50, 50, false, false);
                TrayNotification tray = new TrayNotification();
                tray.setTitle("Un Ami s'est connecter ");
                tray.setMessage(msg.getSender().getNom() + " est maintenant connecter !");
                tray.setRectangleFill(Paint.valueOf("#2C3E50"));
                tray.setAnimationType(AnimationType.POPUP);
                tray.setImage(profileImg);
                tray.showAndDismiss(Duration.seconds(5));
                try {
                    Media hit = new Media(getClass().getClassLoader().getResource("sounds/notification.wav").toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(hit);
                    mediaPlayer.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }
    }

    /*------------------Adding Chat from reciver and sender -------------*/
    public synchronized void addToChat(Message msg) {

        Task<HBox> othersMessages = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {

                Image image = new Image("http://localhost/MysoulMate-Symphony/web/images/" + msg.getSender().getProfil().getPhoto());
                ImageView profileImage = new ImageView(image);
                profileImage.setFitHeight(32);
                profileImage.setFitWidth(32);
                BubbledLabel bl6 = new BubbledLabel();
                if (msg.getType() == MessageType.VOICE) {
                    ImageView imageview = new ImageView(new Image(getClass().getClassLoader().getResource("images/sound.png").toString()));
                    bl6.setGraphic(imageview);
                    bl6.setText("Sent a voice message!");
                    VoicePlayback.playAudio(msg.getVoiceMsg());
                } else {
                    bl6.setText(msg.getSender().getNom() + ": " + msg.getMsg());
                }
                bl6.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, null, null)));
                HBox x = new HBox();
                bl6.setBubbleSpec(BubbleSpec.FACE_LEFT_CENTER);
                x.getChildren().addAll(profileImage, bl6);
                logger.debug("ONLINE USERS: " + Integer.toString(msg.getUserlist().size()));
                //   setOnlineLabel(Integer.toString(msg.getOnlineCount()));
                x.setId(msg.getSender().getId() + "");
                return x;
            }
        };

        othersMessages.setOnSucceeded(event -> {
            if (conversations.containsKey(msg.getSender())) {
                conversations.get(msg.getSender()).getItems().add(othersMessages.getValue());

            } else {
                conversations.put(msg.getSender(), new ListView<>());
                conversations.get(msg.getSender()).getItems().add(othersMessages.getValue());
            }

            if (!messages_shown) {
                boolean found = false;
                System.out.println("messages arent shown entreting for loop");
                for (ChatBoxController x : static_chat_windows) {// if chat widgets have been created 
                    System.out.println("looping");
                    if (x.getPartner().equals(msg.getSender())) {
                        found = true;
                        System.out.println("show :" + x.isShown());
                        if (!x.isShown() && !x.isNotified()) {
                            unread_msg_count++;
                            x.setNotified(true);
                            System.out.println("he ain't got no widget open");
                        }
                    }

                }
                if (!found && conversations.get(msg.getSender()).getItems().size() == 1) {// if widgets haven't been created yet 
                    unread_msg_count++;
                    System.out.println("i am sending notification for first time mesg");
                }
                update_count();

            }

            messages.getItems().clear();
            for (Utilisateur c : conversations.keySet()) {
                HBox b = conversations.get(c).getItems().get(conversations.get(c).getItems().size() - 1);
                b.setAlignment(Pos.CENTER);
                messages.getItems().add(b);

            }
        });

        Task<HBox> yourMessages = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
                System.out.println("writing me message");
                Image image = new Image("http://localhost/MysoulMate-Symphony/web/images/" + MySoulMate.getLogged_in_Client().getProfil().getPhoto());
                ImageView profileImage = new ImageView(image);
                profileImage.setFitHeight(32);
                profileImage.setFitWidth(32);

                BubbledLabel bl6 = new BubbledLabel();
                if (msg.getType() == MessageType.VOICE) {
                    bl6.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResource("images/sound.png").toString())));
                    bl6.setText("Sent a voice message!");
                    VoicePlayback.playAudio(msg.getVoiceMsg());
                } else {
                    bl6.setText(msg.getMsg());
                }
                bl6.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE,
                        null, null)));
                HBox x = new HBox();
                x.setMaxWidth(240);
                x.setAlignment(Pos.TOP_RIGHT);
                bl6.setBubbleSpec(BubbleSpec.FACE_RIGHT_CENTER);
                x.getChildren().addAll(bl6, profileImage);
                x.setId(msg.getReciver().getId() + "");
                return x;
            }
        };
        yourMessages.setOnSucceeded(event -> {
            if (conversations.containsKey(msg.getReciver())) {
                conversations.get(msg.getReciver()).getItems().add(yourMessages.getValue());
            } else {
                conversations.put(msg.getReciver(), new ListView<>());
                conversations.get(msg.getReciver()).getItems().add(yourMessages.getValue());
            }
            messages.getItems().clear();
            for (Utilisateur c : conversations.keySet()) {
                messages.getItems().add(conversations.get(c).getItems().get(conversations.get(c).getItems().size() - 1));
            }
        });

        if (msg.getSender().getId() == MySoulMate.getLogged_in_Client().getId()) {
            Thread t2 = new Thread(yourMessages);
            t2.setDaemon(true);
            t2.start();
        } else if (msg.getReciver().getId() == MySoulMate.getLogged_in_Client().getId()) {
            Thread t = new Thread(othersMessages);
            t.setDaemon(true);
            t.start();
        }

    }

    @FXML
    private void show_messages(ActionEvent event) {
        if (!messages_shown) {
            TranslateTransition dropDown = new TranslateTransition();
            dropDown.setNode(messages);
            dropDown.setDuration(Duration.millis(AnimationDuration));
            dropDown.setFromY(-100);
            dropDown.setToY(270);
            dropDown.play();
            messages_shown = true;
            messages.setVisible(true);
            unread_msg_count = 0;
            update_count();
        } else {
            TranslateTransition Pullup = new TranslateTransition();
            Pullup.setNode(messages);
            Pullup.setDuration(Duration.millis(AnimationDuration));
            Pullup.setFromY(270);
            Pullup.setToY(-100);
            Pullup.play();
            messages_shown = false;
            Pullup.setOnFinished(x -> messages.setVisible(false));
        }
    }

    public static List<ChatBoxController> getStatic_Controllerschat_windows() {
        return static_chat_windows;
    }

    void ChatCloseRequest(ChatBoxController controller) {
        chat_windows.getChildren().remove(chat_windows.getChildren().get(static_chat_windows.indexOf(controller)));
        static_chat_windows.remove(controller);
    }

    public void update_count() {
        msg_count.setText(unread_msg_count + "");
    }

    public void send_close_request(ChatBoxController controller) {
        chat_windows.getChildren().remove(chat_windows.getChildren().get(static_chat_windows.indexOf(controller)));
        static_chat_windows.remove(controller);
    }

// nadia
    @FXML
    private void load_matching_page(ActionEvent event) throws IOException {

        Node root = FXMLLoader.load(getClass().getResource("/VIEWS/Matching/ui_FO_RechercheMatchings.fxml"));
        Content_pane.getChildren().clear();
        Content_pane.getChildren().add(root);
    }

    public void load_preference_page() throws IOException {

        Node root = FXMLLoader.load(Ui_MainFrame_FOController.class.getResource("/VIEWS/Matching/ui_FO_AjouterPreference.fxml"));
        Content_pane.getChildren().clear();
        Content_pane.getChildren().add(root);
    }

// nadia ##
    // jileni
    @FXML
    private void load_my_relation(ActionEvent event) throws IOException, SQLException {
        GestionnaireRelation gr = new GestionnaireRelation();
        Ui_InterfaceRE_FOController.setRelation_owner(MySoulMate.getLogged_in_Client());
        if (gr.ClientValide(MySoulMate.getLogged_in_Client())) {
            Node root = FXMLLoader.load(getClass().getResource("/VIEWS/Relation/ui_InterfaceRE_FO.fxml"));
            Content_pane.getChildren().clear();
            Content_pane.getChildren().add(root);
        } else {
            TrayNotification tray = new TrayNotification(
                    MySoulMate.getLogged_in_Client().getUsername() + ",",
                    "Vous n'etes pas encore en relation !!", NotificationType.WARNING);
            tray.showAndDismiss(javafx.util.Duration.seconds(1));
        }
    }

    //event
    public void load_ajout_sms(ActionEvent event) throws IOException {

        Node root = FXMLLoader.load(Ui_MainFrame_FOController.class.getResource("/VIEWS/Evenement/ui_sms_evt_FO.fxml"));
        Content_pane.getChildren().clear();
        Content_pane.getChildren().add(root);
    }

    public void chercher_les_events() throws SQLException, IOException {
        GestionnaireEvent ge = new GestionnaireEvent();
        List<Events> evts = ((List<Events>) ge.fetchAll()).stream().collect(Collectors.toList());
        event_vb.setSpacing(20);
        for (int i = 0; i < evts.size(); i++) {
            HBox evens_hb = new HBox();
            evens_hb.setSpacing(10);
            evens_hb.setMinSize(120, 160);
            for (int j = 0; j < 4 && i < evts.size(); i++, j++) {
                System.out.println("I is =" + i);
                FXMLLoader fxml = new FXMLLoader(getClass().getResource("/VIEWS/Evenement/ui_even_FO.fxml"));
                Node root = fxml.load();
                Ui_even_FOController con = fxml.<Ui_even_FOController>getController();
                con.setEvt(evts.get(i));
                con.charger_evt();
                evens_hb.getChildren().add(root);
            }
            event_vb.getChildren().add(evens_hb);
        }

    }

    public void load_ajout_event(ActionEvent event) throws IOException {
        Node root = FXMLLoader.load(Ui_MainFrame_FOController.class.getResource("/VIEWS/Evenement/ui_evenement_FO.fxml"));
        Content_pane.getChildren().clear();
        Content_pane.getChildren().add(root);
    }

    public void charger_ajout_event(ActionEvent event) throws IOException {
        //   Ui_Profil_FOController.setProfile_owner(MySoulMate.getLogged_in_Client());
        Node root = FXMLLoader.load(Ui_MainFrame_FOController.class.getResource("/VIEWS/Evenement/ui_interfaceEvt_FO.fxml"));
        Content_pane.getChildren().clear();
        Content_pane.getChildren().add(root);
    }

    public void Update_Event_request() throws IOException {
        Node root;// Making a node
        root = FXMLLoader.load(Ui_MainFrame_BOController.class.getResource("/VIEWS/Evenement/ui_evenement_FO.fxml"));// Getting the View
        Content_pane.getChildren().clear();
        Content_pane.getChildren().add(root);// inserting the Node in the GridPane
        // Label_Module_name_ref.setText("Modifier un évènement existent "); // Changing the header text
    }

    @FXML
    private void load_event(ActionEvent event) throws IOException {
        //    Ui_Profil_FOController.setProfile_owner(MySoulMate.getLogged_in_Client());
        Node root = FXMLLoader.load(getClass().getResource("/VIEWS/Evenement/ui_ListeEvents_FO.fxml"));
        Content_pane.getChildren().clear();
        Content_pane.getChildren().add(root);
    }
    //***event
    @FXML
    private void load_plan(ActionEvent event) throws IOException, SQLException {
        //      Ui_Profil_FOController.setProfile_owner(MySoulMate.getLogged_in_Client());
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/VIEWS/Plan/ui_Plan_FO.fxml"));
        Node root = fxml.load();
        //     Ui_FO_RecherchePlansController con= fxml.<Ui_FO_RecherchePlansController>getController();
        //    con.chercher_les_Plans();
        Content_pane.getChildren().clear();
        Content_pane.getChildren().add(root);
    }

    public StackPane getContent_pane() {
        return Content_pane;
    }

    @FXML
    private void look_up(KeyEvent event) {

    }

    public void open_profile(Utilisateur this_dude) throws IOException, SQLException  {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/VIEWS/Profil/ui_Profil_FO.fxml"));
        Node root = fxml.load();
        Ui_Profil_FOController controller = fxml.<Ui_Profil_FOController>getController();
        
            controller.setProfile_owner(this_dude);
       
       
        Content_pane.getChildren().clear();
        Content_pane.getChildren().add(root);
    }
}
