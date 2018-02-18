/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEWS.User;

import Entites.User.Client;
import Services.User.GestionnaireClient;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;


/**
 * FXML Controller class
 *
 * @author Ransom
 */
public class Ui_Stat_Clients_BOController implements Initializable {

    @FXML
    private PieChart Age_PieChart;
    private GestionnaireClient gc;
    private ObservableList<PieChart.Data> ObArrL_Clients;
    private List<Client> Clients;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            get_allClients();
            
      
        } catch (SQLException ex) {
            Logger.getLogger(Ui_Stat_Clients_BOController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObArrL_Clients=  FXCollections.observableArrayList(
                new PieChart.Data(
                        "Age Entre [18-25] ", 
                        Clients.stream().filter(
                        (client)->{
                                    Calendar calendar= new GregorianCalendar();
                                      Calendar calendar2= new GregorianCalendar();
                                      calendar2.add(Calendar.YEAR,-25);// 93
                                      calendar.add(Calendar.YEAR, -18);//99
                                      return 
                                              (client.getDate_naissance().before(new Date(calendar.getTime().getTime()))&&client.getDate_naissance().after(new Date(calendar2.getTime().getTime())));
                                     }
                       ).count()),
                   new PieChart.Data(
                        "Age Entre [26-35] ", 
                        Clients.stream().filter(
                        (client)->{
                                       Calendar calendar= new GregorianCalendar();
                                      Calendar calendar2= new GregorianCalendar();
                                      calendar2.add(Calendar.YEAR,-35);
                                      calendar.add(Calendar.YEAR, -26);
                                      return 
                                              (client.getDate_naissance().before(new Date(calendar.getTime().getTime()))&&client.getDate_naissance().after(new Date(calendar2.getTime().getTime())));
                                     }
                       ).count()),
                  new PieChart.Data(
                        "Age Entre [36-45]", 
                        Clients.stream().filter(
                        (client)->{
                                      Calendar calendar= new GregorianCalendar();
                                      Calendar calendar2= new GregorianCalendar();
                                      calendar2.add(Calendar.YEAR,-45);
                                      calendar.add(Calendar.YEAR, -36);
                                      return 
                                              (client.getDate_naissance().before(new Date(calendar.getTime().getTime()))&&client.getDate_naissance().after(new Date(calendar2.getTime().getTime())));
                                     }
                       ).count()),
                   new PieChart.Data(
                        "Age Entre [46-55] ", 
                        Clients.stream().filter(
                        (client)->{
                                      Calendar calendar= new GregorianCalendar();
                                      Calendar calendar2= new GregorianCalendar();
                                      calendar2.add(Calendar.YEAR,-55);
                                      calendar.add(Calendar.YEAR, -46);
                                      return 
                                              (client.getDate_naissance().before(new Date(calendar.getTime().getTime()))&&client.getDate_naissance().after(new Date(calendar2.getTime().getTime())));
                                     }
                       ).count()),
                    new PieChart.Data(
                        "Age plus que 55", 
                        Clients.stream().filter(
                        (client)->{
                                      Calendar calendar= new GregorianCalendar();
                                      calendar.add(Calendar.YEAR,-56);
                                      return client.getDate_naissance().before(new Date(calendar.getTime().getTime()));
                                     }
                       ).count())
        );
        Age_PieChart.setData(ObArrL_Clients);
        Age_PieChart.getData().forEach((Data)->{
             
            Data.nameProperty().bind(
                        Bindings.concat(Data.getName()+" : \n "+ (int)Data.getPieValue() +" Personnes ")
            );
        }
        
        
        );
    }    
    void get_allClients() throws SQLException
    {
        gc=new GestionnaireClient();
        Clients =(List<Client>)gc.fetchAll();
    }
}
