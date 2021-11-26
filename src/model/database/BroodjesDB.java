package model.database;

import model.Beleg;
import model.Broodje;
import util.BelegTekstLoadSave;
import util.BroodjesTekstLoadSave;

import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public class BroodjesDB {
    private TreeMap<String, Broodje> broodjes;

    public BroodjesDB() throws IOException {
        File belegbestand = new File("src/bestanden/broodjes.txt");
        this.broodjes = new TreeMap<>(new BroodjesTekstLoadSave().load(belegbestand));
    }
}
