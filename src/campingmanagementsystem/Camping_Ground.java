/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package campingmanagementsystem;

/**
 *
 * @author user
 */
class Camping_Ground {

    private int num_Id;
    private String type;
    private int capacite;
    private int espace;
    private double prix;

    public Camping_Ground(int num_Id, String type, int capacite, int espace, double prix) {
        this.num_Id = num_Id;
        this.type = type;
        this.capacite = capacite;
        this.espace = espace;
        this.prix = prix;
    }

    public void set_Client(int num_Id) {
        this.num_Id = num_Id;
    }

    public int getNum_Id() {
        return num_Id;
    }

    public void set_type(String type) {
        this.type = type;
    }

    public String gettype() {
        return type;
    }

    public void set_capacite(int capacite) {
        this.capacite = capacite;
    }

    public int getCapacite() {
        return capacite;
    }

    public void set_espace(int espace) {
        this.espace = espace;
    }

    public int getEspace() {
        return espace;
    }

    public void set_prix(double prix) {
        this.prix = prix;
    }

    public double getPrix() {
        return prix;
    }
}
