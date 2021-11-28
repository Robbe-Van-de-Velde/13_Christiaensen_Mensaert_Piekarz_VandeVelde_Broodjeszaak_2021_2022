package controller;

import model.Beleg;
import model.BestelFacade;
import model.Broodje;
import model.Observer;
import view.AdminView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Robbe
 */

public class AdminViewController implements Observer {
    private AdminView view;
    private BestelFacade model;


    public AdminViewController(BestelFacade model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update() {

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

}
