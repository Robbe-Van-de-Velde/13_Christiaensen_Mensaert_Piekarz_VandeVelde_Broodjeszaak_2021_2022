package application;

import model.Beleg;
import model.Broodje;
import model.database.BelegDB;
import model.database.BroodjesDB;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws IOException {
        BelegDB t = new BelegDB();
        BroodjesDB v = new BroodjesDB();

        v.getBroodjes().add(new Broodje("geel", 5, 10, 0));
        v.save(new File("test.txt"));
    }
}
