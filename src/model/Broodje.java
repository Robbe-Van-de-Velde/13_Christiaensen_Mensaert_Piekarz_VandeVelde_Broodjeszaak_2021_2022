package model;

public class Broodje {
    String naam;
    int voorraad, verkocht;
    double prijs;


    public Broodje(String naam, double prijs, int vooraad, int verkocht) {
        this.naam = naam;
        this.prijs = prijs;
        this.voorraad = vooraad;
        this.verkocht = verkocht;
    }

    public String getNaam() {
        return naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public int getVerkocht() {
        return verkocht;
    }

    @Override
    public String toString() {
        return this.naam + " " + prijs + " " + voorraad;
    }
}
