package application;
	
import controller.AdminViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.BestelFacade;
import view.AdminView;
import view.KitchenView;
import view.OrderView;

import java.io.IOException;

/**
 * @author Robbe
 */

public class BroodjeszaakMain extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		// Als je met excel wilt laten dan voeg je in de constructor "xls" als je tekst wilt dan "txt"
		BestelFacade facade = new BestelFacade("xls");

		AdminViewController adcontroller = new AdminViewController(facade);
		AdminView adminView = new AdminView(adcontroller);

		OrderView orderView = new OrderView();
		KitchenView kitchenView = new KitchenView();
	}
	
	public static void main(String[] args) {launch(args);}
}
