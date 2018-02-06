/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.User;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Ransom
 */
public class Logger {
    
    private Action action ;
    private Admin admin;
    private Date DateLogger ;
    public Logger(Action action,Admin admin,Date Datelogger)
    {
        this.action=action;
        this.admin=admin;
        this.DateLogger=Datelogger;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Date getDateLogger() {
        return DateLogger;
    }

    public void setDateLogger(Date DateLogger) {
        this.DateLogger = DateLogger;
    }

    @Override
    public String toString() {
        return "Logger{" + "action=" + action + ", admin=" + admin + ", DateLogger=" + DateLogger + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.action);
        hash = 79 * hash + Objects.hashCode(this.admin);
        hash = 79 * hash + Objects.hashCode(this.DateLogger);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Logger other = (Logger) obj;
        if (!Objects.equals(this.action, other.action)) {
            return false;
        }
        if (!Objects.equals(this.admin, other.admin)) {
            return false;
        }
        if (!Objects.equals(this.DateLogger, other.DateLogger)) {
            return false;
        }
        return true;
    }
    
            
    
}
