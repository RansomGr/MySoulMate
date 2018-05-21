/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites.User;

import Entites.Profil.Adresse;
import Entites.Profil.Profil;
import com.messages.Status;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Ransom
 */
public class Utilisateur {
    private int id;
    private Profil profil;
    private Adresse adresse;
    private String username;
    private String username_canonical;
    private String email ;
    private String email_canonical;
    private int enabled;
    private String salt;
    private String password;
    private Date last_login;
    private String confirmation_token;
    private Date password_requested_at;
    private String roles;
    private String nom;
    private String prenom;
    private String gender;
    private Date datanaissance;
    // Chat
    private  Status status;

    // recovery constructor ;
    public Utilisateur(int id, Profil profil, Adresse adresse, String username, String username_canonical, String email, String email_canonical, int enabled, String salt, String password, Date last_login, String confirmation_token, Date password_requested_at, String roles, String nom, String prenom, String gender, Date datanaissance) {
        this.id = id;
        this.profil = profil;
        this.adresse = adresse;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.last_login = last_login;
        this.confirmation_token = confirmation_token;
        this.password_requested_at = password_requested_at;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.gender = gender;
        this.datanaissance = datanaissance;
    }
  // creation constructor
    public Utilisateur(Profil profil, Adresse adresse, String username, String username_canonical, String email, String email_canonical, int enabled, String salt, String password, Date last_login, String confirmation_token, Date password_requested_at, String roles, String nom, String prenom, String gender, Date datanaissance) {
        this.profil = profil;
        this.adresse = adresse;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.last_login = last_login;
        this.confirmation_token = confirmation_token;
        this.password_requested_at = password_requested_at;
        this.roles = roles;
        this.nom = nom;
        this.prenom = prenom;
        this.gender = gender;
        this.datanaissance = datanaissance;
    }
//  Utilisateur C = new Utilisateur(nom_tf.getText(), prenom_tf.getText(), password_tf.getText(), email_tf.getText(), Date.valueOf(date_naissance_dp.getValue()), pseudo_tf.getText(),genre_cmb.getValue());
    public Utilisateur(String nom, String prenom, String password, String email, Date Date_naissance, String username, String genre) {
    this.nom=nom;
    this.prenom=prenom;
    this.password=password;
    this.email=email;
    this.datanaissance=Date_naissance;
    this.username=username;
    this.gender=genre;
    }

    public Utilisateur() {
         this.nom="";
    this.prenom="";
    this.password="";
    this.email="";
    this.datanaissance=null;
    this.username="";
    this.gender="";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public Date getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(Date password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDatanaissance() {
        return datanaissance;
    }

    public void setDatanaissance(Date datanaissance) {
        this.datanaissance = datanaissance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", profil=" + profil + ", adresse=" + adresse + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", last_login=" + last_login + ", confirmation_token=" + confirmation_token + ", password_requested_at=" + password_requested_at + ", roles=" + roles + ", nom=" + nom + ", prenom=" + prenom + ", gender=" + gender + ", datanaissance=" + datanaissance + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.profil);
        hash = 83 * hash + Objects.hashCode(this.adresse);
        hash = 83 * hash + Objects.hashCode(this.username);
        hash = 83 * hash + Objects.hashCode(this.username_canonical);
        hash = 83 * hash + Objects.hashCode(this.email);
        hash = 83 * hash + Objects.hashCode(this.email_canonical);
        hash = 83 * hash + this.enabled;
        hash = 83 * hash + Objects.hashCode(this.salt);
        hash = 83 * hash + Objects.hashCode(this.password);
        hash = 83 * hash + Objects.hashCode(this.last_login);
        hash = 83 * hash + Objects.hashCode(this.confirmation_token);
        hash = 83 * hash + Objects.hashCode(this.password_requested_at);
        hash = 83 * hash + Objects.hashCode(this.roles);
        hash = 83 * hash + Objects.hashCode(this.nom);
        hash = 83 * hash + Objects.hashCode(this.prenom);
        hash = 83 * hash + Objects.hashCode(this.gender);
        hash = 83 * hash + Objects.hashCode(this.datanaissance);
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
        final Utilisateur other = (Utilisateur) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.enabled != other.enabled) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.username_canonical, other.username_canonical)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.email_canonical, other.email_canonical)) {
            return false;
        }
        if (!Objects.equals(this.salt, other.salt)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.confirmation_token, other.confirmation_token)) {
            return false;
        }
        if (!Objects.equals(this.roles, other.roles)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.gender, other.gender)) {
            return false;
        }
        if (!Objects.equals(this.profil, other.profil)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.last_login, other.last_login)) {
            return false;
        }
        if (!Objects.equals(this.password_requested_at, other.password_requested_at)) {
            return false;
        }
        if (!Objects.equals(this.datanaissance, other.datanaissance)) {
            return false;
        }
        return true;
    }
   
}
