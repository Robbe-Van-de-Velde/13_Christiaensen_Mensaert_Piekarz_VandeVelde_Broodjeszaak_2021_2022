package util;

import jxl.read.biff.BiffException;
import model.database.loadSaveStrategies.LoadSaveStrategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Robbe
 */

public abstract class ExcelLoadSaveTemplate<K,V> implements LoadSaveStrategy {

    public final Map<K,V> load(File file) throws IOException {
        ExcelPlugin plugin = new ExcelPlugin();
        Map<K, V> returnMap = new HashMap<K,V>();
        ArrayList<ArrayList<String>> input;
        try {
            input = plugin.read(file);
            String[] tokens = null;
            for (ArrayList<String> lijn : input){
                tokens = new String[lijn.size()];
                for (int i = 0; i != tokens.length; i++){
                    tokens[i] = lijn.get(i);
                }
                V element = maakObject(tokens);
                K key = getKey(tokens);
                returnMap.put(key, element);
            }
        } catch (BiffException e){
            throw new IOException(e.getMessage());
        }
        return returnMap;
    }

    public abstract V maakObject(String[] tokens);

    public abstract K getKey(String[] tokens);
}
