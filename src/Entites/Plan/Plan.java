/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.Plan;


import java.util.Objects;

/**
 *
 * @author irou
 */
public class Plan {
    private int Id;
     private String nom;
    private String type;
    private String email;
    private String siteweb;
    private String photo;
    

    private int telephone;
    private String Description;
    private String photo1;
    private String photo2;
    private Float X;
    private Float y;

    public Plan(int Id, String nom, String type, String email, String siteweb, String photo, int telephone, String Description, String photo1, String photo2, Float X, Float y) {
        this.Id = Id;
        this.nom = nom;
        this.type = type;
        this.email = email;
        this.siteweb = siteweb;
        this.photo = photo;
        this.telephone = telephone;
        this.Description = Description;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.X = X;
        this.y = y;
    }

    public Plan(int id, String text, String value, String text0, String text1, int parseInt, String text2, String text3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Plan(String text, String string, String text0, String text1, int parseInt, String text2, String text3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public Float getX() {
        return X;
    }

    public void setX(Float X) {
        this.X = X;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.Id;
        hash = 79 * hash + Objects.hashCode(this.nom);
        hash = 79 * hash + Objects.hashCode(this.type);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.siteweb);
        hash = 79 * hash + Objects.hashCode(this.photo);
        hash = 79 * hash + this.telephone;
        hash = 79 * hash + Objects.hashCode(this.Description);
        hash = 79 * hash + Objects.hashCode(this.photo1);
        hash = 79 * hash + Objects.hashCode(this.photo2);
        hash = 79 * hash + Objects.hashCode(this.X);
        hash = 79 * hash + Objects.hashCode(this.y);
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
        if (this.Id != other.Id) {
            return false;
        }
        if (this.telephone != other.telephone) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.siteweb, other.siteweb)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        if (!Objects.equals(this.Description, other.Description)) {
            return false;
        }
        if (!Objects.equals(this.photo1, other.photo1)) {
            return false;
        }
        if (!Objects.equals(this.photo2, other.photo2)) {
            return false;
        }
        if (!Objects.equals(this.X, other.X)) {
            return false;
        }
        if (!Objects.equals(this.y, other.y)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Plan{" + "Id=" + Id + ", nom=" + nom + ", type=" + type + ", email=" + email + ", siteweb=" + siteweb + ", photo=" + photo + ", telephone=" + telephone + ", Description=" + Description + ", photo1=" + photo1 + ", photo2=" + photo2 + ", X=" + X + ", y=" + y + '}';
    }



   

   
    
    
    
    
   
    
}
