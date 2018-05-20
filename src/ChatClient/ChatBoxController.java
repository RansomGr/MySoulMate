/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChatClient;

import Entites.User.Utilisateur;
import Listner.Listener;
import com.messages.Message;
import com.messages.MessageType;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import mysoulmate.MySoulMate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class ChatBoxController implements Initializable {

    @FXML
    private ImageView partner_image;
    @FXML
    private Label user_name;
    @FXML
    private TextField messageBox;
    @FXML
    private ListView conversation_pane;
    private final Utilisateur user = MySoulMate.getLogged_in_Client();
    private Utilisateur partner;

    private final Image microphoneActiveImage = new Image(getClass().getClassLoader().getResource("images/microphone-active.png").toString());
    private final Image microphoneInactiveImage = new Image(getClass().getClassLoader().getResource("images/microphone.png").toString());
    private Logger logger = LoggerFactory.getLogger(ChatBoxController.class);
    @FXML
    private ImageView microphoneImageView;
    @FXML
    private Button recordBtn;
    @FXML
    private Button resize_btn;
    @FXML
    private StackPane widget;
    private boolean shown;
    private boolean Notified;

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    public Utilisateur getPartner() {
        return partner;
    }

    public Utilisateur getSender() {
        return this.user;
    }

    public void setConversation_pane(ListView conversation_pane) {
        this.conversation_pane.getItems().clear();
        ((List<HBox>) conversation_pane.getItems()).forEach(x -> {
            if (x.getAlignment().equals(Pos.CENTER)) {
                x.setAlignment(Pos.CENTER_LEFT);
            }
        });
        this.conversation_pane.getItems().addAll(conversation_pane.getItems());
    }

    public void setPartner(Utilisateur partner) {
        this.partner = partner;
        this.user_name.setText(partner.getNom());
        this.partner_image.setImage(new Image("images/" + partner.getProfil().getPhoto()));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        shown = true;
        microphoneImageView.setImage(microphoneInactiveImage);
    }

    /*-------------- Data Entry Actions  -------------------*/
    private void sendAction() throws IOException {
        String msg = messageBox.getText();
        if (!messageBox.getText().isEmpty()) {
            Listener.send(msg, partner);
            messageBox.clear();
        }
    }

    @FXML
    public void sendMethod(KeyEvent event) throws IOException {// when user taps enter , sends a message
        if (event.getCode() == KeyCode.ENTER) {
            sendAction();// call for the mentioned method above 
        }
    }

    @FXML
    private void recordVoiceMessage() throws IOException {// records a voice message
        System.out.println("clicked");
        if (VoiceUtil.isRecording()) {
            Platform.runLater(() -> {
                microphoneImageView.setImage(microphoneInactiveImage); // setting microphone image "inactive "
            }
            );
            VoiceUtil.setRecording(false);
        } else {
            Platform.runLater(() -> {
                microphoneImageView.setImage(microphoneActiveImage);// setting microphone image "active"
            }
            );
            VoiceRecorder.captureAudio(this.partner); // starting the recording 
        }
    }

    /*--------------------------------------------------------------------*/
 /*------------------Adding Chat from reciver and sender -------------*/
    public synchronized void addToChat(Message msg) {
        System.out.println("sending");
        Task<HBox> othersMessages = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
                System.out.println("sending voice inside add toChat");
                Image image = new Image(getClass().getClassLoader().getResource("images/" + msg.getSender().getProfil().getPhoto()).toString());
                ImageView profileImage = new ImageView(image);
                profileImage.setFitHeight(32);
                profileImage.setFitWidth(32);
                BubbledLabel bl6 = new BubbledLabel();
                if (msg.getType() == MessageType.VOICE) {
                    ImageView imageview = new ImageView(new Image(getClass().getClassLoader().getResource("images/sound.png").toString()));
                    bl6.setGraphic(imageview);
                    System.out.println("wiring messsage in the lisgtview");
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

                return x;
            }
        };

        othersMessages.setOnSucceeded(event -> {
            conversation_pane.getItems().add(othersMessages.getValue());
        });

        Task<HBox> yourMessages = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
                Image image = new Image("images/" + user.getProfil().getPhoto());
                ImageView profileImage = new ImageView(image);
                profileImage.setFitHeight(32);
                profileImage.setFitWidth(32);

                BubbledLabel bl6 = new BubbledLabel();
                System.out.println("type"+msg.getType());
                if (msg.getType() == MessageType.VOICE) {
                      System.out.println("sending voice inside add toChat");
                    bl6.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResource("images/sound.png").toString())));
                    bl6.setText("Sent a voice message!");
                    VoicePlayback.playAudio(msg.getVoiceMsg());
                } else {
                    bl6.setText(msg.getMsg());
                }
                bl6.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE,
                        null, null)));
                HBox x = new HBox();
                x.setMaxWidth(conversation_pane.getWidth() - 20);
                x.setAlignment(Pos.TOP_RIGHT);
                bl6.setBubbleSpec(BubbleSpec.FACE_RIGHT_CENTER);
                x.getChildren().addAll(bl6, profileImage);

                //        setOnlineLabel(Integer.toString(msg.getOnlineCount()));
                return x;
            }
        };
        yourMessages.setOnSucceeded(event -> conversation_pane.getItems().add(yourMessages.getValue()));

        if (msg.getSender().getId() == user.getId() && msg.getReciver().getId() == partner.getId()) {
            Thread t2 = new Thread(yourMessages);
            t2.setDaemon(true);
            t2.start();
        } else if (msg.getSender().getId() == partner.getId() && msg.getReciver().getId() == user.getId()) {
            Thread t = new Thread(othersMessages);
            t.setDaemon(true);
            t.start();
        }
    }

    /*---------------------------------------------------------------------*/
    public synchronized void addAsServer(Message msg) {
        Task<HBox> task = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
                BubbledLabel bl6 = new BubbledLabel();
                bl6.setText(msg.getMsg());
                bl6.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE,
                        null, null)));
                HBox x = new HBox();
                bl6.setBubbleSpec(BubbleSpec.FACE_BOTTOM);
                x.setAlignment(Pos.CENTER);
                x.getChildren().addAll(bl6);

                return x;
            }
        };
        task.setOnSucceeded(event -> {
            conversation_pane.getItems().add(task.getValue());
        });
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    @FXML
    public void showhide_window(ActionEvent event) {
        int animationDuration = 300;
        if (shown) {
            TranslateTransition hide = new TranslateTransition();
            hide.setNode(widget);
            hide.setFromY(-130);
            hide.setToY(140);
            hide.setDuration(Duration.millis(animationDuration));
            hide.play();
            shown = false;
            Notified = false;
        } else {
            TranslateTransition show = new TranslateTransition();
            show.setNode(widget);
            show.setFromY(140);
            show.setToY(-130);
            show.setDuration(Duration.millis(animationDuration));
            show.play();
            shown = true;
            Notified = true;
            MySoulMate.getMainController().setUnread_msg_count(MySoulMate.getMainController().getUnread_msg_count() - 1);
            MySoulMate.getMainController().update_count();
        }

    }

    public boolean isNotified() {
        return this.Notified;
    }

    public void setNotified(boolean state) {
        this.Notified = state;
    }

    @FXML
    private void close_me(ActionEvent event) {
        MySoulMate.getMainController().send_close_request(this);
    }
}
