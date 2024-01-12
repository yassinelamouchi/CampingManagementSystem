/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campingmanagementsystem;

import java.util.Date;

/**
 *
 * @author user
 */
public class facture {

    private int camper_id;
    private String nom;
    private String prenom;
    private double prix;

    public facture(int camper_id, String nom, String prenom, double prix) {
        this.camper_id = camper_id;
        this.nom = nom;
        this.prenom = prenom;
        this.prix = prix;
    }

    public int getCamper_id() {
        return camper_id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public double getPrix() {
        return prix;
    }

}
