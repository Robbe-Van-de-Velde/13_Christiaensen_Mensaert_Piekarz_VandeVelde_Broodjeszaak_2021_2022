package model;

public class Broodje {
    String naam;
    int vooraad, verkocht;
    double prijs;


    public Broodje(String naam, double prijs, int vooraad, int verkocht) {
        this.naam = naam;
        this.prijs = prijs;
        this.vooraad = vooraad;
        this.verkocht = verkocht;
    }

    public String getNaam() {
        return naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public int getVooraad() {
        return vooraad;
    }

    public int getVerkocht() {
        return verkocht;
    }
}
