package model;

import model.bestelling.Bestellijn;
import model.bestelling.Bestelling;
import model.bestelling.BestellingEvents;
import model.database.BelegDB;
import model.database.BroodjesDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Robbe, Patryk
 */
public class BestelFacade implements Subject {
    private BelegDB belegDB;
    private BroodjesDB broodjesDB;
    private Map<BestellingEvents, List<Observer>> observers;
    private List<Bestelling> bestellingen;

    public BestelFacade() throws IOException {
        Properties properties = new Properties();
        InputStream is = new FileInputStream("src/bestanden/settings.properties");
        properties.load(is);
        String broodjesFileLocatie = properties.getProperty("broodjesFile");
        String belegFileLocatie = properties.getProperty("belegFile");
        File broodjesFile = new File(broodjesFileLocatie);
        File belegFile = new File(belegFileLocatie);
        String fileType = properties.getProperty("fileType");
        is.close();

        this.belegDB = new BelegDB(belegFile, fileType);
        this.broodjesDB = new BroodjesDB(broodjesFile, fileType);
        this.observers = new HashMap<>();
        for (BestellingEvents event : BestellingEvents.values()){
            this.observers.put(event, new ArrayList<>());
        }
        this.bestellingen = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer, BestellingEvents event) {
        this.observers.get(event).add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        for (BestellingEvents events : observers.keySet()){
            this.observers.get(events).remove(observer);
        }
    }

    @Override
    public void notifyObservers(BestellingEvents event) {
        for (Observer observer : this.observers.get(event)){
            observer.update();
        }
    }

    public List<Broodje> getBroodjes(){
        return broodjesDB.getBroodjes();
    }

    public List<Beleg> getBeleggen(){
        return belegDB.getBeleggen();
    }

    public List<Bestelling> getBestellingen() {
        return bestellingen;
    }

    public List<Bestellijn> getLijstBestellijnen(Bestelling bestelling){
        return bestelling.getLijstBestellijnen();
    }

    public void voegBestellijnToe(String broodje, Bestelling bestelling) throws IOException {
        bestelling.voegBestellijnToe(broodje, broodjesDB);

        notifyObservers(BestellingEvents.TOEVOEGEN_BROODJE);
        notifyObservers(BestellingEvents.WIJZIGING_VOORRAAD);
    }

    public Map<String, Integer> getVoorraadlijstBroodjes(){
        return broodjesDB.getVoorraadlijstBroodjes();
    }

    public BelegDB getBelegDB() {
        return belegDB;
    }

    public BroodjesDB getBroodjesDB() {
        return broodjesDB;
    }

    public void voegBelegToeAanBestellijn(Bestellijn bestellijn, String beleg, Bestelling bestelling) {
        bestelling.voegBelegToeAanBestellijn(bestellijn, beleg, belegDB);

        notifyObservers(BestellingEvents.TOEVOEGEN_BELEG);
        notifyObservers(BestellingEvents.WIJZIGING_VOORRAAD);
    }

    public Map<String, Integer> getVoorraadlijstBeleg(){
        return belegDB.getVoorraadlijstBeleg();
    }

    public void verwijderBestellijn(Bestellijn bestellijn, Bestelling bestelling) {
        bestelling.verwijderBestellijn(bestellijn);

        notifyObservers(BestellingEvents.VERWIJDER_BROODJE);
        notifyObservers(BestellingEvents.WIJZIGING_VOORRAAD);
    }

    public void verwijderBestelling(Bestelling bestelling) {
        Iterator<Bestellijn> iterator = getLijstBestellijnen(bestelling).iterator();
        while (iterator.hasNext()){
            Bestellijn bestellijn = iterator.next();
            bestellijn.maakKlaarOmVerwijderdTeWorden();
            iterator.remove();
        }
        notifyObservers(BestellingEvents.VERWIJDER_BROODJE);
        notifyObservers(BestellingEvents.WIJZIGING_VOORRAAD);
    }

    public void voegZelfdeBroodjeToe(Bestellijn bestellijn, Bestelling bestelling) throws IOException {
        bestelling.voegZelfdeBroodjeToe(bestellijn, broodjesDB, belegDB);

        notifyObservers(BestellingEvents.TOEVOEGEN_BELEG);
        notifyObservers(BestellingEvents.WIJZIGING_VOORRAAD);
    }

    public Bestelling getBestellingByVolgnummer(int volgnummer){
        Bestelling bestelling = null;
        for (Bestelling b : bestellingen){
            if (b.getVolgnr() == volgnummer){
                bestelling = b;
            }
        }
        return bestelling;
    }

    public Bestelling voegBestellingToe() {
        Bestelling bestelling = new Bestelling();
        this.bestellingen.add(bestelling);
        return bestelling;
    }

    public double getPrijsBestelling(Bestelling bestelling){
        double prijs = 0;
        List<Bestellijn> bestellijnen = bestelling.getLijstBestellijnen();
        for (Bestellijn bestellijn: bestellijnen){
            prijs += bestellijn.getBroodje().getPrijs();
            for (Beleg beleg: bestellijn.getBeleggen()){
                prijs += beleg.getPrijs();
            }
        }
        return prijs;
    }
}
