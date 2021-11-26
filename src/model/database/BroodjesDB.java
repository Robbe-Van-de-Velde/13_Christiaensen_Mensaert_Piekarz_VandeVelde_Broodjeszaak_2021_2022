package model.database;

import model.Beleg;
import util.BelegTekstLoadSave;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public class BroodjesDB {
    private TreeMap<String, Beleg> broodjes;

    public BroodjesDB() throws IOException {
        File belegbestand = new File("src/bestanden/broodjes.txt");
        this.broodjes = new TreeMap<>(new BelegTekstLoadSave().load(belegbestand));
    }
}
