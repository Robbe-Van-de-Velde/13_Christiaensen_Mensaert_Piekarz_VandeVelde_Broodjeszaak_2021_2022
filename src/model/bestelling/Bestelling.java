package model.bestelling;

import model.bestelling.state.BestellingState;
import model.bestelling.state.InBestelling;
import model.bestelling.state.InWacht;

import java.util.List;

/**
 * @author Robbe
 */

public class Bestelling {
    private BestellingState state;
    private BestellingState inWacht = new InWacht();
    private BestellingState inBestelling = new InBestelling();
    private List<Bestellijn> bestellijnen;

    public List getLijstBestellijnen(){
        return this.bestellijnen;
    }

    public void voegBestellijnToe(String naamBroodje){

    }
}
