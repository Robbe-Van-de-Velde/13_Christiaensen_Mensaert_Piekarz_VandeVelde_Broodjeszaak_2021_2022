package util;

import model.Broodje;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class BroodjesTekstLoadSave extends TekstLoadSaveTemplate{


    /*@Override
    void save(File file, TreeMap<String, Object> data) {
        try {
            PrintWriter writer = new PrintWriter(file);
            String line = null;
            Object object = null;
            for (String s: data.keySet()){
                Broodje broodje = (Broodje) data.get(s);
                line = broodje.getNaam() + ","+broodje.getPrijs()+","+broodje.getVooraad()+","+broodje.getVooraad();
            }
            writer.close();
        }
        catch (FileNotFoundException exc){
            throw new IllegalStateException("File not found");}
    }*/

    @Override
    Object maakObject(String[] tokens) {
        String naam = tokens[0];
        Double prijs = (Double.parseDouble(tokens[1]));
        int vooraad = (Integer.parseInt(tokens[2]));
        int verkocht = (Integer.parseInt(tokens[3]));
        Broodje broodje = new Broodje(naam, prijs, vooraad, verkocht);
        return broodje;
    }

    @Override
    Object getKey(String[] tokens) {
        return tokens[0];
    }
}
