package controller;

import model.Beleg;
import model.BestelFacade;
import model.Broodje;
import model.Observer;
import model.bestelling.Bestellijn;
import model.bestelling.BestellingEvents;
import view.OrderView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Robbe
 */

public class OrderViewController implements Observer {
    private OrderView view;
    private BestelFacade model;

    public OrderViewController(BestelFacade facade) {
        this.model = facade;
        model.addObserver(this, BestellingEvents.TOEVOEGEN_BROODJE);
        model.addObserver(this, BestellingEvents.TOEVOEGEN_BELEG);
        model.addObserver(this, BestellingEvents.VERWIJDER_BROODJE);
    }

    public void setView(OrderView view){
        this.view = view;
    }

    public List<Broodje> getBroodjes(){
        return model.getBroodjes();
    }

    public List<Beleg> getBeleggen(){
        return model.getBeleggen();
    }

    public void voegBestellijnToe(String broodje) throws IOException {
        model.voegBestellijnToe(broodje);
    }

    @Override
    public void update() {
        List<Bestellijn> bestellijnen = model.getLijstBestellijnen();
        view.updateBestellijnen(bestellijnen);
        Map<String, Integer> voorraadLijstBroodjes = model.getVoorraadlijstBroodjes();
        view.updateBroodjesKnoppen(voorraadLijstBroodjes);
        Map<String, Integer> voorraadLijstBeleg = model.getVoorraadlijstBeleg();
        view.updateBelegKnoppen(voorraadLijstBeleg);
    }

    public void voegBelegToeAanBestellijn(Bestellijn bestellijn, String beleg) {
        model.voegBelegToeAanBestellijn(bestellijn, beleg);
    }

    public void verwijderBestellijn(Bestellijn bestellijn) {
        model.verwijderBestellijn(bestellijn);
    }

    public void verwijderBestelling() {
        model.verwijderBestelling();
    }

    public void voegZelfdeBroodjeToe(Bestellijn bestellijn) throws IOException {
        model.voegZelfdeBroodjeToe(bestellijn);
    }
}
