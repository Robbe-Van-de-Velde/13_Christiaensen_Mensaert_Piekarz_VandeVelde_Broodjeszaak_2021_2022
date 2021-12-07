package model.database;

import model.Beleg;
import model.Broodje;
import model.database.loadSaveStrategies.BelegExcelLoadSaveStrategy;
import model.database.loadSaveStrategies.BelegTekstLoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;

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

    /*public BelegDB() throws IOException {
        File belegbestand = new File("src/bestanden/beleg.txt");
        this.beleggen = new TreeMap<>(new BelegTekstLoadSaveStrategy().load(belegbestand));
    }*/

    public BelegDB(File file, String fileType) throws IOException {
        String strategyNaam = fileType.toUpperCase() + "BELEG";
        LoadSaveStrategy loadSaveStrategy = LoadSaveStrategyFactory.createLoadSaveStrategy(strategyNaam);
        this.beleggen = new TreeMap<>(loadSaveStrategy.load(file));
    }

    public List<Beleg> getBeleggen() {
        ArrayList<Beleg> result = new ArrayList<>();
        for (String key : beleggen.keySet()){
            result.add(beleggen.get(key));
        }
        return result;
    }

    public void save(File file, String strategy){
        LoadSaveStrategy loadSaveStrategy = LoadSaveStrategyFactory.createLoadSaveStrategy(strategy);
        loadSaveStrategy.save(file, this.beleggen);
    }
}
