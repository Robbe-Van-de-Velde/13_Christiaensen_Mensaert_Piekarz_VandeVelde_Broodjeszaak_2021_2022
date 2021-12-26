package model.bestelling;

import model.bestelling.state.BestellingState;
import model.bestelling.state.InBestelling;
import model.bestelling.state.InWacht;
import model.database.BelegDB;
import model.database.BroodjesDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Robbe
 */

public class Bestelling {
    private int volgnr;
    private BestellingState state;
    private BestellingState inWacht = new InWacht();
    private BestellingState inBestelling = new InBestelling();
    private List<Bestellijn> bestellijnen;

    public Bestelling() {
        this.bestellijnen = new ArrayList<>(0);
    }

    public int getVolgnr() {
        return volgnr;
    }

    public List getLijstBestellijnen(){
        return this.bestellijnen;
    }

    public void voegBestellijnToe(String naamBroodje, BroodjesDB broodjesDB) throws IOException {
        Bestellijn bestellijn = new Bestellijn(naamBroodje, broodjesDB);
        this.bestellijnen.add(bestellijn);
    }

    public void voegBelegToeAanBestellijn(Bestellijn bestellijn, String beleg, BelegDB belegDB) {
        bestellijn.voegBelegToe(beleg, belegDB);
    }

    public void verwijderBestellijn(Bestellijn bestellijn) {
        bestellijn.maakKlaarOmVerwijderdTeWorden();
        bestellijnen.remove(bestellijn);
    }
}
