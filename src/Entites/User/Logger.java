/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.User;

import java.sql.Date;

/**
 *
 * @author Ransom
 */
public class Logger {
    
    Action action ;
    Admin admin;
    Date DateLogger ;
    public Logger(Action action,Admin admin,Date Datelogger)
    {
        this.action=action;
        this.admin=admin;
        this.DateLogger=Datelogger;
    }
            
    
}
