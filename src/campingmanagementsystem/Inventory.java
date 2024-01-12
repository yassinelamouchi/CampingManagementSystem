/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campingmanagementsystem;

/**
 *
 * @author user
 */
public class Inventory {
    private int id;
    private String type;
    private int quantite;
    private double prix;

    public Inventory(int id,String type, int quantite, double prix) {
        this.id=id;
        this.type = type;
        this.quantite = quantite;
        this.prix = prix;
    }

    // Méthode getter pour le type
    public String getType() {
        return type;
    }

    // Méthode setter pour le type
    public void setType(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    // Méthode setter pour le type
    public void setId(String type) {
        this.type = type;
    }
    

    // Méthode getter pour la quantité
    public int getQuantite() {
        return quantite;
    }

    // Méthode setter pour la quantité
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    // Méthode getter pour le prix
    public double getPrix() {
        return prix;
    }

    // Méthode setter pour le prix
    public void setPrix(double prix) {
        this.prix = prix;
    }

    // Méthode pour afficher les détails de l'outil d'inventaire
    public void afficherDetails() {
        System.out.println("Type: " + type);
        System.out.println("Quantité: " + quantite);
        System.out.println("Prix: " + prix);
    }

    // Méthode pour mettre à jour la quantité disponible
    public void mettreAJourQuantite(int nouvelleQuantite) {
        quantite = nouvelleQuantite;
    }
}

