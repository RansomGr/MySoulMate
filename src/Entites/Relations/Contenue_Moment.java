/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.relations;

import Entites.AbstractEntite;
import java.util.Date;

/**
 *
 * @author zhanimm
 */
public class Contenue_Moment extends AbstractEntite{
    
    private String contenue ; 
    private String photo ;
    private String description ;
    private Date date_moment; 
    public Contenue_Moment(int ID, String nom,String contenue,String photo,String description, Date date_moment) {
        super(ID, nom);
        this.contenue=contenue;
        this.photo=photo;
        this.date_moment=date_moment;
        this.description=description;
    }
    
}
