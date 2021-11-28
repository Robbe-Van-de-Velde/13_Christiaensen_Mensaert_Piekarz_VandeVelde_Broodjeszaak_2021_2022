package application;
	
import controller.AdminViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.BestelFacade;
import view.AdminView;
import view.KitchenView;
import view.OrderView;

import java.io.IOException;


public class BroodjeszaakMain extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		BestelFacade facade = new BestelFacade();

		AdminViewController adcontroller = new AdminViewController(facade);
		AdminView adminView = new AdminView(adcontroller);

		OrderView orderView = new OrderView();
		KitchenView kitchenView = new KitchenView();
	}
	
	public static void main(String[] args) {launch(args);}
}
