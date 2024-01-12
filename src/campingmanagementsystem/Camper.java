/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campingmanagementsystem;

/**
 *
 * @author user
 */
import java.util.Date;

public class Camper extends Personne {
    private Date dateCheckIn;
    private Date dateCheckOut;

    // Constructeur
    public Camper(int id, String nom, String prenom, int numtel, Date dateCheckIn, Date dateCheckOut) {
        super(id, nom, prenom, numtel);
        this.dateCheckIn = dateCheckIn;
        this.dateCheckOut = dateCheckOut;
    }

    // Méthodes getters et setters pour dateCheckIn
    public Date getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn(Date dateCheckIn) {
        this.dateCheckIn = dateCheckIn;
    }

    // Méthodes getters et setters pour dateCheckOut
    public Date getDateCheckOut() {
        return dateCheckOut;
    }

    public void setDateCheckOut(Date dateCheckOut) {
        this.dateCheckOut = dateCheckOut;
    }

    // Implémentation des méthodes abstraites de la classe Personne
    @Override
    public void afficherDetails() {
        System.out.println("ID: " + getId());
        System.out.println("Nom: " + getNom());
        System.out.println("Prénom: " + getPrenom());
        System.out.println("Numéro de téléphone: " + getNumtel());
        System.out.println("Date de check-in: " + dateCheckIn);
        System.out.println("Date de check-out: " + dateCheckOut);
    }

    @Override
    public void effectuerAction() {
        System.out.println("Le campeur effectue une action spécifique.");
    }
}

