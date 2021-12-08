package model;

import model.bestelling.BestellingEvents;

/**
 * @author Robbe
 */
public interface Subject {
    void addObserver(Observer observer, BestellingEvents event);
    void removeObserver(Observer observer);
    void notifyObservers();
}
