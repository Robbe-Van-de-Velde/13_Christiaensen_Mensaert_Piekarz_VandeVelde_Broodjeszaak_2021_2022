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
    private BroodjesDB broodjesDB = new BroodjesDB(new File("src/bestanden/broodjes.xls"), "excel");

    public Bestellijn(String naamBroodje) throws IOException {
        this.naamBroodje = naamBroodje;
        this.broodje = broodjesDB.getBroodje(naamBroodje);
        broodje.aanpassenVoorraad(1);
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
