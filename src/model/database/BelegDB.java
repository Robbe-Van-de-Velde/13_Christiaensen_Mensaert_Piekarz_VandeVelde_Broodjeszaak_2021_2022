package model.database;

import model.Beleg;
import model.database.loadSaveStrategies.BelegExcelLoadSaveStrategy;
import model.database.loadSaveStrategies.BelegTekstLoadSaveStrategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Patryk, Robbe
 */

public class BelegDB {
    private TreeMap<String, Beleg> beleggen;

    public BelegDB() throws IOException {
        File belegbestand = new File("src/bestanden/beleg.txt");
        this.beleggen = new TreeMap<>(new BelegTekstLoadSaveStrategy().load(belegbestand));
    }

    public BelegDB(String fileType) throws IOException {
        File file = null;
        if (fileType.equals("txt")){
            file = new File("src/bestanden/beleg.txt");
            this.beleggen = new TreeMap<>(new BelegTekstLoadSaveStrategy().load(file));
        } else if (fileType.equals("xls")){
            file = new File("src/bestanden/beleg.xls");
            this.beleggen = new TreeMap<>(new BelegExcelLoadSaveStrategy().load(file));
        }
    }

    public List<Beleg> getBeleggen() {
        ArrayList<Beleg> result = new ArrayList<>();
        for (String key : beleggen.keySet()){
            result.add(beleggen.get(key));
        }
        return result;
    }
}
