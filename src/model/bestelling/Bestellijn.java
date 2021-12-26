package model.bestelling;

import model.Beleg;
import model.Broodje;
import model.database.BroodjesDB;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Robbe
 */

public class Bestellijn {
    private String naamBroodje, namenBeleg;
    private Broodje broodje;
    private List<Beleg> beleggen;
    private BroodjesDB broodjesDB;

    public Bestellijn(String naamBroodje, BroodjesDB broodjesDB) throws IOException {
        this.naamBroodje = naamBroodje;
        this.broodjesDB = broodjesDB;
        broodjesDB.getBroodje(naamBroodje).aanpassenVoorraad(1);
        this.broodje = broodjesDB.getBroodje(naamBroodje);
        this.beleggen = new ArrayList<>();
    }

    public String getNaamBroodje() {
        return naamBroodje;
    }

    public String getNamenBeleg(){
        String result = "";
        for (Beleg beleg: beleggen) {
            result += beleg.getNaam() + ",";
        }
        this.namenBeleg = result;
        return result;
    }

    public List<Beleg> getBeleggen() {
        return beleggen;
    }

    public void addBelegToBestellijn(Beleg beleg){
        this.beleggen.add(beleg);
    }
}
