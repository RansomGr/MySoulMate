/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Plan;

import Entites.AbstractEntite;

import java.util.Objects;

/**
 *
 * @author irou
 */
public class Plan extends AbstractEntite {
    
    public enum Type{
        culturel,sportif,divertissment;
        public static String getAsString(Type t)
        {
            
            if(null!=t)
                switch (t) {
                case sportif:
                    return "sportif";
                case divertissment:
                    return "divertissement";
                case culturel:
                    return "culturel";
                default:
                    break;
            }
           return "nop";
        }
        public static Type getAsType(String s)
        {
            if(s.equals("sportif"))
                return Type.sportif;
            else if(s.equals("divertissement"))
                return Type.divertissment;
           
                return Type.culturel;
            
        }
    } ;
    private Type type;
    private String email;
    private String siteweb;
    private int telephone ;
    private String description ;
    private String photo;
 

    public Plan(int ID, String nom,Type type, String email, String siteweb, int telephone, String description, String photo ) {
        super(ID, nom);
        this.type = type;
        this.email = email;
        this.siteweb = siteweb;
        this.telephone = telephone;
        this.description = description;
        this.photo = photo;
    }
    public Plan( String nom,Type type, String email, String siteweb, int telephone, String description, String photo ) {
        super(-1,nom);
        this.type = type;
        this.email = email;
        this.siteweb = siteweb;
        this.telephone = telephone;
        this.description = description;
        this.photo = photo;
    }
    public Plan(int Id ,String nom)
    {
        super(Id,nom);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.type);
        hash = 41 * hash + Objects.hashCode(this.email);
        hash = 41 * hash + Objects.hashCode(this.siteweb);
        hash = 41 * hash + this.telephone;
        hash = 41 * hash + Objects.hashCode(this.description);
        hash = 41 * hash + Objects.hashCode(this.photo);
       
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
        final Plan other = (Plan) obj;
        if (this.telephone != other.telephone) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.siteweb, other.siteweb)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        
       
        return true;
    }

    @Override
    public String toString() {
        return super.toString()+"Plan{" + "type=" + type + ", email=" + email + ", siteweb=" + siteweb + ", telephone=" + telephone + ", description=" + description + ", photo=" + photo + '}';
    }

   

    

    
    
    
   
    
}
