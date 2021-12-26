package model;

/**
 * @author Patryk, Robbe
 */

public class Beleg {
    String naam;
    int voorraad, verkocht;
    double prijs;

    public Beleg(String naam, double prijs, int vooraad, int verkocht) {
        this.naam = naam;
        this.voorraad = vooraad;
        this.verkocht = verkocht;
        this.prijs = prijs;
    }

    public String getNaam() {
        return naam;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public int getVerkocht() {
        return verkocht;
    }

    public double getPrijs() {
        return prijs;
    }

    @Override
    public String toString() {
        return this.naam + " " + prijs + " " + voorraad;
    }

    public void aanpassenVoorraad(int aantal){
        this.voorraad = this.voorraad - aantal;
    }
}
