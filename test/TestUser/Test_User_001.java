/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestUser;

import APIS.Uer.MySoulMateMail;
import Entites.User.Client;
import Services.User.GestionnaireClient;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date; 
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.text.Utilities;

/**
 *
 * @author Ransom
 */
public class Test_User_001 {

    private static String Content;
    public static void main(String args[]) throws MessagingException, FileNotFoundException, IOException
    {
try (//        Client c1= new Client("mounir","sabbaghi","falfoul11","falfoul@falfoul@esprit.tn",new Date(1987,12,1),"foufou");
//        
//        GestionnaireClient g= new GestionnaireClient();
//        try {
//            g.create(c1);
//        } catch (SQLException ex) {
//            Logger.getLogger(Test_User_001.class.getName()).log(Level.SEVERE, null, ex);
//        }
        BufferedReader br = new BufferedReader(new FileReader("src/Files/welcomeMail.txt"))) {
    StringBuilder sb = new StringBuilder();
    String line = br.readLine();
    while (line != null) {
        sb.append(line);
        sb.append(System.lineSeparator());
        line = br.readLine();
    }
  Content = sb.toString();
}
Content=Content.replace("[[Name]]", "Amine Benmimoun");
        MySoulMateMail mail= new MySoulMateMail ("ahmedamine.benmimoun@esprit.tn",Content,"Test mail 01");
        mail.sendMail();
    }
    
}
