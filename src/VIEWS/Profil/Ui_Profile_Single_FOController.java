/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Profil;

import Entites.User.Utilisateur;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_Profile_Single_FOController implements Initializable {

    @FXML
    private ImageView owner_photo;
    @FXML
    private Label owner_age;
    @FXML
    private Label owner_gender;
    @FXML
    private Label owner_name;
    @FXML
    private MenuButton actions;
     /* non fxml variables */
    Utilisateur owner ;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public Utilisateur getOwner() {
        return owner;
    }

    public void setOwner(Utilisateur owner) {
        this.owner = owner;
        java.sql.Date d=new java.sql.Date(owner.getDatanaissance().getTime());
        this.owner_age.setText(Period.between(d.toLocalDate(), LocalDate.now()).getYears()+"");
        this.owner_gender.setText(owner.getGender());
        this.owner_photo.setImage(new Image("images/"+owner.getProfil().getPhoto()));
    }
    
}
