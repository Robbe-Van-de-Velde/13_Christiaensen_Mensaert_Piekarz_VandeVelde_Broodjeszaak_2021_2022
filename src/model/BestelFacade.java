package model;

import model.bestelling.BestellingEvents;
import model.database.BelegDB;
import model.database.BroodjesDB;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Robbe
 */
public class BestelFacade implements Subject {
    private BelegDB belegDB;
    private BroodjesDB broodjesDB;
    private Map<BestellingEvents, List<Observer>> observers;

    public BestelFacade(File broodjesFile, File belegFile, String fileType) throws IOException {
        this.belegDB = new BelegDB(belegFile, fileType);
        this.broodjesDB = new BroodjesDB(broodjesFile, fileType);
        this.observers = new HashMap<>();
        for (BestellingEvents event : BestellingEvents.values()){
            this.observers.put(event, new ArrayList<>());
        }
    }

    @Override
    public void addObserver(Observer observer, BestellingEvents event) {
        this.observers.get(event).add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers() {

    }

    public List<Broodje> getBroodjes(){
        return broodjesDB.getBroodjes();
    }

    public List<Beleg> getBeleggen(){
        return belegDB.getBeleggen();
    }
}
