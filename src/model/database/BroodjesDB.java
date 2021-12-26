package model.database;

import model.Broodje;
import model.database.loadSaveStrategies.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Patryk, Robbe
 */

public class BroodjesDB {
    private TreeMap<String, Broodje> broodjes;

    /*public BroodjesDB() throws IOException {
        File belegbestand = new File("src/bestanden/broodjes.txt");
        this.broodjes = new TreeMap<>(new BroodjesTekstLoadSaveStrategy().load(belegbestand));
    }*/

    public BroodjesDB(File file, String fileType) throws IOException {
        String strategyNaam = fileType.toUpperCase() + "BROODJE";
        LoadSaveStrategy loadSaveStrategy = LoadSaveStrategyFactory.getInstance().createLoadSaveStrategy(strategyNaam);
        this.broodjes = new TreeMap<>(loadSaveStrategy.load(file));
    }

    public List<Broodje> getBroodjes() {
        ArrayList<Broodje> result = new ArrayList<>();
        for (String key : broodjes.keySet()){
            result.add(broodjes.get(key));
        }
        return result;
    }

    public void save(File file, String strategy){
        LoadSaveStrategy loadSaveStrategy = LoadSaveStrategyFactory.getInstance().createLoadSaveStrategy(strategy);
        loadSaveStrategy.save(file, this.broodjes);
    }

    public Broodje getBroodje(String broodje){
        return broodjes.get(broodje);
    }

    public Map<String, Integer> getVoorraadlijstBroodjes(){
        HashMap<String, Integer> map = new HashMap<>();
        for (Broodje broodje : getBroodjes()){
            map.put(broodje.getNaam(), broodje.getVoorraad());
        }
        return map;
    }

    public void updateBroodjesVoorraad(Broodje broodje){
        this.broodjes.put(broodje.getNaam(), broodje);
    }
}
