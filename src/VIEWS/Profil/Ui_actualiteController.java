/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Profil;

import Entites.Profil.Actualite;
import Entites.User.Client;
import Services.Profil.GestionnaireActualite;
import Services.User.GestionnaireAction;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Sofiene
 */
public class Ui_actualiteController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private TextArea actu_txt;
    @FXML
    private GridPane actualite_pane;
    @FXML
    private Label owner_name;
    private Client owner;
    private Actualite actualite;

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }
    
    

    public void setActualite(Actualite actualite) {
        this.actualite = actualite;
    }

    public Actualite getActualite() {
        return actualite;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void put_values()
    {
        this.actu_txt.setText(this.actualite.getContenu());
        this.owner_name.setText(this.owner.getNom());
        this.image.setImage(new Image("images/"+owner.getProfil().getPhoto()));   
    }

    @FXML
    private void modifier_actu(ActionEvent event) {
        actu_txt.setEditable(true);
        actu_txt.setOpacity(1);
    }

    @FXML
    private void delete_actu(ActionEvent event) throws SQLException {
          GestionnaireActualite act = new GestionnaireActualite();
            actualite.setContenu(actu_txt.getText());
            act.remove(actualite);
      
    }

    @FXML
    private void update_actu(KeyEvent event) throws SQLException {
        if(event.getCode().equals(event.getCode().ENTER))
        {
            GestionnaireActualite act = new GestionnaireActualite();
            actualite.setContenu(actu_txt.getText());
            actualite.setClient(owner);
            act.update(actualite);
            actu_txt.setEditable(false);
            actu_txt.setOpacity(0.9);
        }
            
    }
}
