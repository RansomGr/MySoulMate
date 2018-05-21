/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Relation;

import Entites.Relation.Relation;
import Entites.User.Utilisateur;
import Services.Relation.GestionnaireRelation;
import Services.User.GestionnaireUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author zhanimm
 */
public class ChartsController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private AreaChart<?, ?> areachart;

    @FXML
    private ScatterChart<?, ?> scatterchart;

    
    @FXML
    private PieChart piechart;

    @FXML
    private BarChart<?, ?> barchart;
    
    private GestionnaireRelation gr;

    private List<Relation> Relations;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
            get_allRelations();
            
      
        } catch (SQLException ex) {
            Logger.getLogger(ChartsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList <PieChart.Data> piechartData
                =FXCollections.observableArrayList(
                
                new PieChart.Data("Relation de niveau 1", Relations.stream().filter(r->r.getNiveau().equals("niveau1")).count()),
                new PieChart.Data("Relation de niveau 2", Relations.stream().filter(r->r.getNiveau().equals("niveau2")).count()),
                new PieChart.Data("Realtion de niveau 3", Relations.stream().filter(r->r.getNiveau().equals("niveau3")).count())
                //new PieChart.Data("cycles", 22)
                );
                       
        piechart.setData(piechartData);
        piechart.getData().forEach((Data)->{
             
            Data.nameProperty().bind(
                        Bindings.concat(Data.getName()+" : \n "+ (int)Data.getPieValue() +" Relation(s) ")
            );
        }
        
        
        );
        
         XYChart.Series series= new XYChart.Series();
         series.getData().add(new XYChart.Data("Relation avec points >= 5",Relations.stream().filter(r->r.getPoints_relation()>=5).count()));
         series.getData().add(new XYChart.Data("Relation avec points >15",Relations.stream().filter(r->r.getPoints_relation()>=15).count()));
         series.getData().add(new XYChart.Data("Relation avec points>30",Relations.stream().filter(r->r.getPoints_relation()>=30).count()));
         

                 areachart.getData().add(series);
                 scatterchart.getData().add(series);
                 barchart.getData().add(series);
piechart.getData().forEach((Data)->{
             
            Data.nameProperty().bind(
                Bindings.concat(Data.getName()+" : \n "+ (int)Data.getPieValue() +" Relation(s) ")
            );
        }
        
        );
    }    
      void get_allRelations() throws SQLException
    {
        gr=new GestionnaireRelation();
        Relations =(List<Relation>)gr.fetchAll();
    }
    
}
