/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Plan;

import Entites.Plan.Avis;
import Entites.Plan.Plan;
import Entites.User.Client;
import Services.Plan.GestionnaireAvis;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author irou
 */
public class Avis_FOController implements Initializable {

    @FXML
    private ImageView image_img;
    @FXML
    private Label nomC_l;
    @FXML
    private Label note_l;
    @FXML
    private TextArea com_ta;
  
    
    
//   private Client owner;
//
//    public void setOwner(Client owner) {
//        this.owner = owner;
//    }
    private Client Client;

    public Client getClient() {
        return Client;
    }

    public void setClient(Client Client) {
        this.Client = Client;
    }
   private Avis Avis;

    public Avis getAvis() {
        return Avis;
    }

    public void setAvis(Avis Avis) {
        this.Avis = Avis;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        
       
    }    
    public void put_values()
    {
            note_l.setText(""+Avis.getNote());
            nomC_l.setText(Avis.getClient().getNom());
            com_ta.setText(Avis.getCommentaire());
            
    }
    @FXML
    private void modifier_actu(ActionEvent event) {
        com_ta.setEditable(true);
        com_ta.setOpacity(1);
    }

    @FXML
    private void delete_actu(ActionEvent event) throws SQLException {
          GestionnaireAvis av= new GestionnaireAvis();
            Avis.setCommentaire(com_ta.getText());
            av.remove(Avis);
      
    }

    @FXML
    private void update_actu(KeyEvent event) throws SQLException {
        if(event.getCode().equals(event.getCode().ENTER))
        {
            GestionnaireAvis av = new GestionnaireAvis();
            Avis.setCommentaire(com_ta.getText());
            Avis.setClient(Client);
            av.update(Avis);
            com_ta.setEditable(false);
            com_ta.setOpacity(0.9);
        }
    
}
    

}
