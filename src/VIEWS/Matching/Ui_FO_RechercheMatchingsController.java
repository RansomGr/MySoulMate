/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Matching;

import Entites.Matching.Packaging;
import Entites.Profil.Caracteristique;
import Entites.Profil.Profil;
import Entites.User.Client;
import Entites.User.Logger;
import Services.Matching.GestionnairePackaging;
import Services.User.GestionnaireClient;
import VIEWS.Ui_MainFrame_FOController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mysoulmate.MySoulMate;


/**
 * FXML Controller class
 *
 * @author Nadia
 */
public class Ui_FO_RechercheMatchingsController implements Initializable {

    @FXML
    private Hyperlink achat_packaging_lien;
    @FXML
    private Button preferences_btn;
    @FXML
    private VBox liste_matchings_vb;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            chercher_les_matchings();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Ui_FO_RechercheMatchingsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Ui_FO_RechercheMatchingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void load_achat_packaging(ActionEvent event) {
    }

    @FXML
    private void load_preferences_form(ActionEvent event) throws IOException {
        MySoulMate.getMainController().load_preference_page();
    }

    private void chercher_les_matchings() throws SQLException, IOException
    {
       GestionnaireClient gcl = new GestionnaireClient();
       List <Client> clients =  ( (List <Client>) gcl.fetchAll()).stream().filter(x->x.getActivation()==1 && x.getProfil()!=null&&x.getProfil().getCaracteristique()!=null&&x.getProfil().getPreference()!=null).collect(Collectors.toList());
       clients.remove(MySoulMate.getLogged_in_Client());
       for (int i=0 ; i<clients.size(); )
    {
        HBox matchings_hb = new HBox();
        matchings_hb.setMinSize(170,240);
         
        for(int j=0 ; j<2 ;i++, j++)
        {
            FXMLLoader fxml= new FXMLLoader(getClass().getResource("/VIEWS/Matching/ui_FO_show_one_Matching.fxml"));
            Node root = fxml.load();
            Ui_FO_show_one_MatchingController contr= fxml.<Ui_FO_show_one_MatchingController>getController();
            System.out.println(clients.get(i));
            contr.setThe_matching(clients.get(i));
            contr.charger_matchings();
            matchings_hb.getChildren().add(root);
        }
        liste_matchings_vb.getChildren().add(matchings_hb);
    }
    
    
    }


}
