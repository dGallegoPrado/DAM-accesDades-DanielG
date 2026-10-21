package ex41gestiousuaris;

import java.io.Serializable;

public class User implements Serializable{
    private String nom;
    private String pass;
    //constructor 
    public User(String nom, String pass) {
        this.nom = nom;
        this.pass = pass;
    }
    //get 
    public String getNom(){ return  nom;}
    public String getPass(){return  pass;}
    //set
    public void setNom(String nom){this.nom = nom;}
    public void setPass(String pass){this.pass = pass;}
}
