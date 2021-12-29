package controller;

import model.*;
import model.bestelling.BestellingEvents;
import model.database.BelegDB;
import model.database.BroodjesDB;
import view.AdminView;

import java.util.List;

/**
 * @author Robbe
 */

public class AdminViewController implements Observer {
    private AdminView view;
    private BestelFacade model;


    public AdminViewController(BestelFacade model) {
        this.model = model;
        model.addObserver(this, BestellingEvents.WIJZIGING_VOORRAAD);
    }

    public void setView(AdminView view){
        this.view = view;
    }

    public List<Broodje> getBroodjes(){
        return model.getBroodjes();
    }

    public List<Beleg> getBeleggen(){
        return model.getBeleggen();
    }



    @Override
    public void update() {
        this.view.updateVoorraad();
    }

    public void slaVoorraadVeranderingOp() {
        model.slaVoorraadVeranderingOp();
    }
}
