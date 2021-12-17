package model;

import model.database.BelegDB;
import model.database.BroodjesDB;

/**
 * @author Robbe
 */

public interface Observer {
    void update(BelegDB belegDB, BroodjesDB broodjesDB);
}
