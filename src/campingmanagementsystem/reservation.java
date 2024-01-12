/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campingmanagementsystem;

import java.util.Date;

public class reservation {
    private int camper_id;
    private String nom;
    private String prenom;
    private String camptype;
    private String equipementtype;
    private int quantity;
    private Date date;
    public reservation(int camper_id, String nom, String prenom, String camptype, String equipementtype, int quantity, Date date) {
        this.camper_id = camper_id;
        this.nom = nom;
        this.prenom = prenom;
        this.camptype = camptype;
        this.equipementtype = equipementtype;
        this.quantity = quantity;
        this.date = date;
    }
    // Constructors, other methods, etc.

    public int getCamper_id() {
        return camper_id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getCamptype() {
        return camptype;
    }

    public String getEquipementtype() {
        return equipementtype;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getDate() {
        return date;
    }

    // Other methods, if needed
}

