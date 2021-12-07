package model;

import model.database.BelegDB;
import model.database.BroodjesDB;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Robbe
 */
public class BestelFacade implements Subject {
    private BelegDB belegDB;
    private BroodjesDB broodjesDB;

    public BestelFacade(File broodjesFile, File belegFile, String fileType) throws IOException {
        this.belegDB = new BelegDB(belegFile, fileType);
        this.broodjesDB = new BroodjesDB(broodjesFile, fileType);
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
