package controller;

import model.Beleg;
import model.BestelFacade;
import model.Broodje;
import model.Observer;
import model.database.BelegDB;
import model.database.BroodjesDB;
import view.OrderView;

import java.util.List;

public class OrderViewController implements Observer {
    private OrderView view;
    private BestelFacade model;

    public OrderViewController(BestelFacade facade) {
        this.model = facade;
    }

    public void setView(OrderView view){
        this.view = view;
    }

    public List<Broodje> getBroodjes(){
        return model.getBroodjes();
    }

    public List<Beleg> getBeleggen(){
        return model.getBeleggen();
    }


    @Override
    public void update(BelegDB belegDB, BroodjesDB broodjesDB) {

    }
}
