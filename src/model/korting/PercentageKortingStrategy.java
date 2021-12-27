package model.korting;

/**
 * @author Patryk
 */

public class PercentageKortingStrategy implements KortingStrategy{
    @Override
    public double berekenPrijs(double prijs) {
        return prijs - (prijs*0.1);
    }
}
