/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campingmanagementsystem;

/**
 *
 * @author user
 */
public abstract class Personne {
    // Attributs
    private int id;
    private String nom;
    private String prenom;
    private int numtel;

    // Constructeur
    public Personne(int id, String nom, String prenom, int numtel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
    }

    // Méthodes getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    // Méthode abstraite pour afficher les détails de la personne
    public abstract void afficherDetails();

    // Méthode abstraite pour effectuer une action spécifique à la personne
    public abstract void effectuerAction();
}

