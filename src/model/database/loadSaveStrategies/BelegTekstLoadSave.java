package model.database.loadSaveStrategies;

import model.Beleg;
import util.TekstLoadSaveTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class BelegTekstLoadSave extends TekstLoadSaveTemplate {

    @Override
    public Object maakObject(String[] tokens) {
        String naam = tokens[0];
        Double prijs = (Double.parseDouble(tokens[1]));
        int vooraad = (Integer.parseInt(tokens[2]));
        int verkocht = (Integer.parseInt(tokens[3]));

        Beleg beleg = new Beleg(naam, prijs, vooraad, verkocht);
        return beleg;
    }

    @Override
    public Object getKey(String[] tokens) {
        return tokens[0];
    }
}
