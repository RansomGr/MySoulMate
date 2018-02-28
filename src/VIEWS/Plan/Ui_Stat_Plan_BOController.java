/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.Plan;

import Entites.Plan.Plan;
import Services.Plan.GestionnairePlan;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;



/**
 * FXML Controller class
 *
 * @author irou
 */
public class Ui_Stat_Plan_BOController implements Initializable {

    @FXML
    private PieChart note_PieChart;
    
    private GestionnairePlan gp;
    private ObservableList<PieChart.Data> ObArrL_Plans;
    private List<Plan> Plans;
    /**

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            get_allClients();
//            
//      
//        } catch (SQLException ex) {
//            Logger.getLogger(Ui_Stat_Plan_BOController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        ObArrL_Plans=  FXCollections.observableArrayList(
//                new PieChart.Data(
//                        "Note Entre [0-3] ", 
//                        Plans.stream().filter(
//                        (Plan)->{
//                                    Calendar calendar= new GregorianCalendar();
//                                      Calendar calendar2= new GregorianCalendar();
//                                      calendar2.add(Calendar.YEAR,-25);// 93
//                                      calendar.add(Calendar.YEAR, -18);//99
//                                      return 
//                                              (Plan.getDate_naissance().before(new Date(calendar.getTime().getTime()))&&client.getDate_naissance().after(new Date(calendar2.getTime().getTime())));
//                                     }
//                       ).count()),
//                   new PieChart.Data(
//                        "note Entre [3-5] ", 
//                        Plans.stream().filter(
//                        (client)->{
//                                       Calendar calendar= new GregorianCalendar();
//                                      Calendar calendar2= new GregorianCalendar();
//                                      calendar2.add(Calendar.YEAR,-35);
//                                      calendar.add(Calendar.YEAR, -26);
//                                      return 
//                                              (Plan.getDate_naissance().before(new Date(calendar.getTime().getTime()))&&client.getDate_naissance().after(new Date(calendar2.getTime().getTime())));
//                                     }
//                       ).count()),
//                  new PieChart.Data(
//                        "Age Entre [5-8]", 
//                        Plans.stream().filter(
//                        (Plan)->{
//                                      Calendar calendar= new GregorianCalendar();
//                                      Calendar calendar2= new GregorianCalendar();
//                                      calendar2.add(Calendar.YEAR,-45);
//                                      calendar.add(Calendar.YEAR, -36);
//                                      return 
//                                              (Plan.getDate_naissance().before(new Date(calendar.getTime().getTime()))&&client.getDate_naissance().after(new Date(calendar2.getTime().getTime())));
//                                     }
//                       ).count()),
//                   
//                    new PieChart.Data(
//                        "Note plus que 8", 
//                        Plans.stream().filter(
//                        (Plan)->{
//                                      Calendar calendar= new GregorianCalendar();
//                                      calendar.add(Calendar.YEAR,-56);
//                                  
//                                    return Plan.getDate_naissance().before(new Date(calendar.getTime().getTime()));
//                                     }
//                       ).count())
//        );
//        note_PieChart.setData(ObArrL_Plans);
//        note_PieChart.getData().forEach((Data)->{
//             
//            Data.nameProperty().bind(
//                        Bindings.concat(Data.getName()+" : \n "+ (int)Data.getPieValue() +" Personnes ")
//            );
//        }
//        
//        
//        );
//    }    
//    void get_allClients() throws SQLException
//    {
//        gp=new GestionnairePlan();
//        Plans =(List<Plan>)gp.fetchAll();   
//    }
//   
//        
        
        // TODO
    }    
}
    

