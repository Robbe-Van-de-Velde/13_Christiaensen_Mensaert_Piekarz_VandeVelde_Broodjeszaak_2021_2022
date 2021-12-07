package model;

import model.database.BelegDB;
import model.database.BroodjesDB;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Robbe
 */
public class BestelFacade implements Subject {
    private BelegDB belegDB;
    private BroodjesDB broodjesDB;

    public BestelFacade() throws IOException {
        this.belegDB = new BelegDB();
        this.broodjesDB = new BroodjesDB();
    }

    public BestelFacade(String fileType) throws IOException {
        this.belegDB = new BelegDB(fileType);
        this.broodjesDB = new BroodjesDB(fileType);
    }

    @Override
    public void addObserver(Observer observer) {

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
