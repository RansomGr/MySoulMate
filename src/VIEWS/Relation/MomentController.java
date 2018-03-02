/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Relation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author zhanimm
 */
public class MomentController implements Initializable {

    @FXML
    private TextArea contenu;
    @FXML
    private Text date;
    @FXML
    private ImageView photo;
    @FXML
    private Text nom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void charger_Moment() {
        Services.Plan.GestionnairePlan gc = new Services.Plan.GestionnairePlan();
        
      /*   text_ta.setText(plan.getDescription()); 
         image_img.setImage(new Image("images/f716.jpg"));*/
        
      
    
    }

}
    /* public void put_values()
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
*/