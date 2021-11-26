package model.database;

import model.Beleg;
import util.BelegTekstLoadSave;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class BelegDB {
    private TreeMap<String, Beleg> belegen;

    public BelegDB() throws IOException {
        File belegbestand = new File("src/bestanden/beleg.txt");
        this.belegen = new TreeMap<>(new BelegTekstLoadSave().load(belegbestand));
    }
}
