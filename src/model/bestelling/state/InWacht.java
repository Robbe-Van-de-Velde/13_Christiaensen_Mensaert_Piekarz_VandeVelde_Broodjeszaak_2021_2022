package model.bestelling.state;

import model.bestelling.Bestelling;

/**
 * @author Robbe
 */

public class InWacht implements BestellingState {
    Bestelling bestelling;

    public InWacht(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    @Override
    public void startBestelling() {
        this.bestelling.changeState(bestelling.getInBestelling());
    }

    @Override
    public void toevoegenBroodje() {
        System.out.println("doet niets");
    }

    @Override
    public void verwijderBroodje() {
        System.out.println("doet niets");
    }

    @Override
    public void toevoegenIdentiekBroodje() {
        System.out.println("doet niets");
    }

    @Override
    public void toevoegenBeleg() {
        System.out.println("doet niets");
    }

    @Override
    public void afsluiten() {
        System.out.println("doet niets");
    }

    @Override
    public void annuleren() {
        System.out.println("doet niets");
    }

    @Override
    public void betalen() {
        System.out.println("doet niets");
    }

    @Override
    public void zendNaarKeuken() {
        System.out.println("doet niets");
    }

    @Override
    public void startBereiding() {
        System.out.println("doet niets");
    }

    @Override
    public void afgewerkt() {
        System.out.println("doet niets");
    }
}
