/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ratsi
 */
public class Personne {
    public String Matricule;
    public String Nom;
    public String Prenom;
    public String Droit;
    public String Option1;
    public String Option3;

    public Personne(String Matricule, String Nom, String Prenom, String Droit, String Option1, String Option3) {
        this.Matricule = Matricule;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Droit = Droit;
        this.Option1 = Option1;
        this.Option3 = Option3;
    }

    public String getMatricule() {
        return Matricule;
    }

    public void setMatricule(String Matricule) {
        this.Matricule = Matricule;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getDroit() {
        return Droit;
    }

    public void setDroit(String Droit) {
        this.Droit = Droit;
    }

    public String getOption1() {
        return Option1;
    }

    public void setOption1(String Option1) {
        this.Option1 = Option1;
    }

    public String getOption3() {
        return Option3;
    }

    public void setOption3(String Option3) {
        this.Option3 = Option3;
    }

   
    
    
    
}
