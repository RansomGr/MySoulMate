/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Profil;

import Entites.User.Admin;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import mysoulmate.MySoulMate;

/**
 * FXML Controller class
 *
 * @author Sofiene
 */
public class Ui_reglementController implements Initializable {

    @FXML
    private Button dowload_btn;
    @FXML
    private TextArea text_reg;
    private Alert ConfirmWindow;
    private String Message_Warning;
    private ButtonType Oui;
    private ButtonType Non;
    private Alert InformationWindow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InformationWindow = new Alert(Alert.AlertType.INFORMATION);
        InformationWindow.setContentText("Regelement telecharger !\n Veuillez consulter votre boite email.");
        InformationWindow.setHeaderText("Regelement");
        InformationWindow.setTitle("MySoulMate");
        ConfirmWindow = new Alert(Alert.AlertType.CONFIRMATION);
        Oui = new ButtonType("Accepter", ButtonBar.ButtonData.OK_DONE);
        Non = new ButtonType("Refuser", ButtonBar.ButtonData.CANCEL_CLOSE);
        ConfirmWindow.getButtonTypes().clear();
        ConfirmWindow.getButtonTypes().addAll(Oui, Non);
        ConfirmWindow.setTitle("MySoulMate");
        ConfirmWindow.setHeaderText("Reglement d'utilisation");
        ConfirmWindow.setContentText("En acceptant le reglement d'utilisation \n vous devriez le respecter \n êtes vous sûr?");
        text_reg.setText("Meetic, Attractive World, Adopte un mec,... et 10 autres sites ne respectent pas la loi informatique et libertés et la protection des données personnelles.\n"
                + "\n"
                + "Pour trouver son âme soeur, flirter ou simplement converser,... aujourd'hui des milliers de Français utilisent Internet pour rencontrer quelqu'un. L'offre est grandissante, les sites de rencontres fleurissent et il y en a pour tous les goûts !\n"
                + "\n"
                + "En effet, ces sites de rencontres permettent généralement d'effectuer une recherche par critères : par localisation géographique, communauté sociale, ethnique ou religieuse, en fonction de l'apparence physique, des pratiques sexuelles ou des opinions politiques, y compris pour les personnes sensibles à la compatibilité amoureuse des signes astrologiques !\n"
                + "\n"
                + "Générant de plus en plus d'utilisateurs et donc autant de données à caractère personnel et sensibles (notamment les données relatives à la vie et aux pratiques sexuelles), la Commission Nationale de l'Informatique et des Libertés (CNIL) s'est penchée sur ces sites de rencontres en inscrivant leur contrôle dans son programme annuel pour 2014.\n"
                + "\n"
                + "Les sites \"Meetic\", \"Attractive World\", \"Adopte un mec\", \"Easyflirt\", \"Rencontre obèse\", \"Destidyll\", \"Forcegay\", \"Mektoube\", \"Jdream\", \"Feujworld\", \"Marmite love\", \"Gauche rencontre\", et \"Celibest\" sont passés au crible. Résultat : sur les 13 sites contrôlés, la CNIL constate de nombreux manquements à la loi Informatique et Liberté et met en demeure 8 acteurs du secteur de s'y conformer.\n"
                + "\n"
                + "Parmi les manquements constatés, la CNIL reproche notamment à ces sites de :\n"
                + "\n"
                + "ne pas recueillir le consentement exprès des personnes pour la collecte de données sensibles ;\n"
                + "ne pas procéder à la suppression des données des membres ayant demandé leur désinscription ou ayant cessé d'utiliser leurs comptes depuis une longue durée (droit à l'oubli non respecté) ;\n"
                + "de mettre en oeuvre des fichiers afin d'exclure des personnes de l'accès au service sans avoir procédé à des demandes d'autorisation auprès de la CNIL ;\n"
                + "de ne pas informer correctement les internautes de leurs droits (accès, suppression, rectification) ainsi que des conditions dans lesquelles des cookies sont déposés sur leur ordinateur.\n"
                + "Les sites contrôlés doivent se conformer aux exigences de la loi dans un délai de 3 mois. Le cas échéant, ils encourent des sanctions pouvant aller jusqu'à 300.000 euros.\n"
                + "\n"
                + "Par ailleurs, compte tenu de la sensibilité des données en cause et du nombre de personnes concernées, et surtout consciente du danger d'un éventuel piratage ou d'une divulgation de ce type d'informations, la CNIL publie une série de conseils à destination des utilisateurs afin de protéger leur intimité. Parmi eux, regarder attentivement les conditions générales d'utilisation, utiliser un mot de passe différent de son compte mail ou encore utiliser un pseudonyme.");
    }

    @FXML
    private void dowload(ActionEvent event) throws FileNotFoundException, DocumentException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("RegelemntMySoulMate.pdf"));
        document.open();
        document.add(new Paragraph(text_reg.getText()));
        document.close();
        InformationWindow.show();
    }

    @FXML
    private void je_comprend(ActionEvent event) throws IOException {
        Optional<ButtonType> result = ConfirmWindow.showAndWait();
        if (result.isPresent() && result.get() == Oui) {
            {// take to profile creation
                Parent root = FXMLLoader.load(getClass().getResource("/VIEWS/Profil/ui_Profile_Creation.fxml"));
                Scene scene = new Scene(root);
                MySoulMate.getMainStage().setScene(scene);
            }
            // else stay put
        }

    }

}
