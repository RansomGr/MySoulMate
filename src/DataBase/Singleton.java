/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ransom
 */
public class Singleton {
      static String url="jdbc:mysql://127.0.0.1:3306/MySoulMate";
      static String login="root";
      static String password="";
      private static Connection cnx ;
      private Singleton()
      {
          if(cnx==null)
          {
        try {
              cnx=DriverManager.getConnection(url,login,password);
              } 
            catch (SQLException ex) 
            {
               System.out.println("Erreur de Connection a La base de Données MySoulMate");
               Logger.getLogger(Singleton.class.getName()).log(Level.SEVERE, null, ex);
            }  
      }
      }
      public static Connection getInstence()
      {
           Singleton s=new Singleton();
           return cnx;
        
      }
    
}
