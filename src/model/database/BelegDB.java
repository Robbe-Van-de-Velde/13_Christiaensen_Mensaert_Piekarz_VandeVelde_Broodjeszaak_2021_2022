package model.database;

import model.Beleg;
import util.BelegTekstLoadSave;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Patryk, Robbe
 */

public class BelegDB {
    private TreeMap<String, Beleg> beleggen;

    public BelegDB() throws IOException {
        File belegbestand = new File("src/bestanden/beleg.txt");
        this.beleggen = new TreeMap<>(new BelegTekstLoadSave().load(belegbestand));
    }

    public List<Beleg> getBeleggen() {
        ArrayList<Beleg> result = new ArrayList<>();
        for (String key : beleggen.keySet()){
            result.add(beleggen.get(key));
        }
        return result;
    }
}
