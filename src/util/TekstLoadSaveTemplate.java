package util;

import model.Beleg;
import model.Broodje;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public abstract class TekstLoadSaveTemplate <K,V>{

    public final Map<K,V> load(File file) throws IOException {
        Map<K,V> returnMap = new HashMap<K,V>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line = reader.readLine();
            while (line != null && !line.trim().equals("")) {
                String[] tokens = line.split(",");
                V element = maakObject(tokens);
                K key = getKey(tokens);
                returnMap.put(key,element);
                line = reader.readLine();
            }
        }
        return returnMap;
    }

    /*abstract void save(File file, TreeMap<String, Object> data);*/

    public abstract V maakObject(String[] tokens);

    public abstract K getKey(String[] tokens);
}

