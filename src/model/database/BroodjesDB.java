package model.database;

import model.Beleg;
import model.Broodje;
import util.BelegTekstLoadSave;
import util.BroodjesTekstLoadSave;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Patryk, Robbe
 */

public class BroodjesDB {
    private TreeMap<String, Broodje> broodjes;

    public BroodjesDB() throws IOException {
        File belegbestand = new File("src/bestanden/broodjes.txt");
        this.broodjes = new TreeMap<>(new BroodjesTekstLoadSave().load(belegbestand));
    }

    public List<Broodje> getBroodjes() {
        ArrayList<Broodje> result = new ArrayList<>();
        for (String key : broodjes.keySet()){
            result.add(broodjes.get(key));
        }
        return result;
    }
}
