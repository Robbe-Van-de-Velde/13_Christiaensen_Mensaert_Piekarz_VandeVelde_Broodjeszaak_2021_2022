package model.bestelling.state;

import model.bestelling.Bestelling;

/**
 * @author Robbe
 */

public class InBestelling implements BestellingState {
    Bestelling bestelling;

    public InBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    @Override
    public void startBestelling() {
        System.out.println("doet niets");
    }

    @Override
    public void toevoegenBroodje() {
        //TODO
    }

    @Override
    public void verwijderBroodje() {
        //TODO
    }

    @Override
    public void toevoegenIdentiekBroodje() {
        //TODO
    }

    @Override
    public void toevoegenBeleg() {
        //TODO
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
