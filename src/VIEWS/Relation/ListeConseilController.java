package VIEWS.Relation;

import Entites.Relation.Conseil;
import Entites.Relation.Relation;
import Services.Relation.GestionnaireConseil;
import Services.Relation.GestionnaireRelation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListeConseilController implements Initializable{
@FXML
private TableView liste;
GestionnaireConseil gc;
   /* @FXML
    private TableColumn<Conseil, Integer> id;*/
   /* @FXML
    private TableColumn<Conseil, Integer> c1;*/
    @FXML
    private TableColumn<Conseil, String> c2;
    @FXML
    private TableColumn<Conseil, String> c3;
     @FXML
    private TextField niv;
     
/*public void hide()
{(liste).setVisible(false);

}
public void afficher(ActionEvent e)
{
	liste.setVisible(true);

}*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //c1.setCellValueFactory((CellDataFeatures<Conseil, Integer> Conseil) -> new  SimpleIntegerProperty((Conseil.getValue().getID())).asObject());
        c2.setCellValueFactory((CellDataFeatures<Conseil,String>Conseil)->new SimpleStringProperty(Conseil.getValue().getTitre()));
        c3.setCellValueFactory((CellDataFeatures<Conseil,String>Conseil)->new SimpleStringProperty(Conseil.getValue().getContenue()));
        liste.setVisible(false);
         gc = new GestionnaireConseil();
         
        
    
    }
    
}
