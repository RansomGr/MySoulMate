/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Relation;

import Entites.Relation.Contenue_Moment;
import Services.Relation.GestionnaireContenue_Moment;
//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXListView;
import javafx.scene.control.ListView;
//import com.jfoenix.controls.JFXPopup;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author zhanimm
 */
public class Ui_ListeMoment_FOController implements Initializable {

    @FXML
    private VBox cm_vb;

    /**
     * Initializes the controller class.
     */
   /* @FXML
   private ListView<Contenue_Moment> listView;
    GestionnaireContenue_Moment gm;*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
 
    }  
    public void chercher_les_Plans() throws SQLException, IOException
    {
        GestionnaireContenue_Moment gcm = new GestionnaireContenue_Moment();
       List <Contenue_Moment> Moments =  ( (List <Contenue_Moment>) gcm.fetchAll()).stream().collect(Collectors.toList());
       cm_vb.setSpacing(30);
       for (int i=0 ; i<Moments.size(); i++)
    {
        HBox Moments_hb = new HBox();
        Moments_hb.setSpacing(20);
        Moments_hb.setMinSize(120, 160);
        for(int j=0 ; j<4&&i<Moments.size();i++, j++)
        {
            System.out.println("I is ="+i);
            FXMLLoader fxml= new FXMLLoader(getClass().getResource("Moment.fxml"));
            Node root = fxml.load();
            MomentController con= fxml.<MomentController>getController();
            //con.setPlan(Moments.get(i));
            con.charger_Moment();
            Moments_hb.getChildren().add(root);
        }
        cm_vb.getChildren().add(Moments_hb);
    }
    
    
}
    
}
