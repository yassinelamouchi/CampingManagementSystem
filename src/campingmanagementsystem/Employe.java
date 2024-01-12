/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campingmanagementsystem;

/**
 *
 * @author user
 */
public final class Employe extends Personne {
    private String role;

    public Employe(int id, String nom, String prenom, int numtel, String role) {
        super(id, nom, prenom, numtel);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void afficherDetails() {
        System.out.println("ID: " + getId());
        System.out.println("Nom: " + getNom());
        System.out.println("Prénom: " + getPrenom());
        System.out.println("Numéro de téléphone: " + getNumtel());
        System.out.println("Rôle: " + role);
    }

    @Override
    public void effectuerAction() {
        System.out.println("L'employé effectue une action spécifique en tant que " + role);
    }
}

