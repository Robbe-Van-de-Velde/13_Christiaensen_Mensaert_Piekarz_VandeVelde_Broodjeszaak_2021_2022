package util;

import model.Broodje;

import java.util.ArrayList;
import java.util.List;

public class BroodjesTekstLoadSave extends TekstLoadSaveTemplate{



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
