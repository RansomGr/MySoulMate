/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.User;

import APIS.User.MySoulMateMail;
import Entites.User.Client;
import Services.User.GestionnaireClient;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.sql.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javax.mail.MessagingException;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_Create_new_FOController implements Initializable {

    private Map<TextField, String> fields;
    private String Message_Warning;
    private Alert InformationWindow;
    private Alert ErrorWindow;
    private Alert WarningWindow;
    @FXML
    private Hyperlink login_hl;
    @FXML
    private TextField prenom_tf;
    @FXML
    private TextField nom_tf;
    @FXML
    private TextField email_tf;
    @FXML
    private TextField pseudo_tf;
    @FXML
    private TextField password_tf;
    @FXML
    private TextField conf_password_tf;
    @FXML
    private DatePicker date_naissance_dp;
    @FXML
    private Button sincrire_pb;
    @FXML
    private Hyperlink forgot_hl;
    @FXML
    private ComboBox<String> genre_cmb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InformationWindow = new Alert(Alert.AlertType.INFORMATION);
        WarningWindow = new Alert(Alert.AlertType.WARNING);
        ErrorWindow = new Alert(Alert.AlertType.ERROR);
        InformationWindow.setContentText("Compte Crée Avec succès !\n Veuillez consulter votre boite email.");
        ErrorWindow.setContentText("La base de données n'est pas accessible !");
        ErrorWindow.setHeaderText("Base de données");
        ErrorWindow.setTitle("MySoulMate");
        WarningWindow.setContentText(Message_Warning);
        WarningWindow.setHeaderText("Inscription");
        WarningWindow.setTitle("MySoulMate");
        InformationWindow.setHeaderText("Bienvenu !");
        InformationWindow.setTitle("MySoulMate");
        init_fields();
        genre_cmb.getItems().clear();
        genre_cmb.getItems().addAll("Homme","Femme");
    }

    @FXML
    private void client_connection_page(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Login_FO.fxml"));
        Scene scene = new Scene(root);
        MySoulMate.getMainStage().setScene(scene);
    }

    @FXML
    private void create_new_account(ActionEvent event) throws SQLException, FileNotFoundException, IOException, MessagingException {
        validate_form();
        if (Message_Warning.equals("Les champs suivants posent des problèmes \n")) {

            GestionnaireClient gc = new GestionnaireClient();
            Client C = new Client(nom_tf.getText(), prenom_tf.getText(), password_tf.getText(), email_tf.getText(), Date.valueOf(date_naissance_dp.getValue()), pseudo_tf.getText(),genre_cmb.getValue());
            if (gc.create(C) == 1) {
                InformationWindow.show();
                String Content;
                BufferedReader br = new BufferedReader(new FileReader("src/Files/welcomeMail.txt"));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                Content = sb.toString();
                C.setID(((List<Client>)gc.fetchAll()).stream().mapToInt(x->x.getID()).max().getAsInt());

                Content = Content.replace("[[Name]]", C.getNom() + " " + C.getPrenom());
                Content =Content.replace("[[ID]]", C.getID()+"");
                Content = Content.replace("[[ID2]]", C.getID()+"");
                MySoulMateMail mail = new MySoulMateMail(email_tf.getText(), Content, "Test mail 01");
                mail.sendMail();
                clear_tf();
            } else {
                ErrorWindow.show();
            }

        } else {
            WarningWindow.show();
            if (Message_Warning.contains("18")) {
                clear_tf();
            }
        }
    }
    @FXML
    private void forgot_page(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/User/ui_Forgot_FO.fxml"));
        Scene scene = new Scene(root);
        MySoulMate.getMainStage().setScene(scene);
    }

    private void init_fields() {
        fields = new HashMap<>();
        fields.put(password_tf, "Mot de passe");
        fields.put(conf_password_tf, "Confirmation mot de passe");
        fields.put(pseudo_tf, " Pseudo");
        fields.put(email_tf, "Adresse email");
        fields.put(prenom_tf, "Prenom");
        fields.put(nom_tf, "Nom");
    }

    private void clear_tf() {
        fields.keySet().forEach(Field -> Field.clear());
    }

    private void validate_form() {
        Message_Warning = "Les champs suivants posent des problèmes \n";
        fields.entrySet().forEach(Field -> {
            if (Field.getKey().getText().isEmpty()) {
                Message_Warning += "Le Champ " + Field.getValue() + " est vide !\n";
            }
        });
        if (!(password_tf.getText().equals(conf_password_tf.getText()))) {
            Message_Warning += "Les mots des passes doivent être identique !";
        }
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.YEAR, -18);// 2000

        if (Date.valueOf(date_naissance_dp.getValue()).after(calendar.getTime())) {
            Message_Warning = "Vous n'avez pas encore 18 ans !";
        }

        WarningWindow.setContentText(Message_Warning);

    }

}
