package model.korting;

/**
 * @author Patryk
 */

public class GeenKortingStrategy implements KortingStrategy {
    @Override
    public double berekenPrijs(double prijs) {
        return prijs;
    }
}
