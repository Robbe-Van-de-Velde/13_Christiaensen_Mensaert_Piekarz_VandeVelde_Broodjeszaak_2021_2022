package model.korting;

import model.bestelling.Bestelling;

/**
 * @author Patryk
 */

public interface KortingStrategy {
    double berekenPrijs(Bestelling bestelling);
}
