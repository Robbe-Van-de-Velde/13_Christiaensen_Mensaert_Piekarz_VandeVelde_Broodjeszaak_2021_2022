package model;

public class Beleg {
    String naam;
    int vooraad, verkocht;
    double prijs;

    public Beleg(String naam, double prijs, int vooraad, int verkocht) {
        this.naam = naam;
        this.vooraad = vooraad;
        this.verkocht = verkocht;
        this.prijs = prijs;
    }

    public String getNaam() {
        return naam;
    }

    public int getVooraad() {
        return vooraad;
    }

    public int getVerkocht() {
        return verkocht;
    }

    public double getPrijs() {
        return prijs;
    }
}
