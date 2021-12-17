package application;
	
import controller.AdminViewController;
import controller.OrderViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.BestelFacade;
import view.AdminView;
import view.KitchenView;
import view.OrderView;

import java.io.File;
import java.io.IOException;

/**
 * @author Robbe
 */

public class BroodjeszaakMain extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		// om te kiezen tussen txt en excel load geef je de files mee en kies je tekst of excel in het laatste veld
		BestelFacade facade = new BestelFacade(new File("src/bestanden/broodjes.xls"), new File("src/bestanden/beleg.xls"), "excel");

		AdminViewController adcontroller = new AdminViewController(facade);
		AdminView adminView = new AdminView(adcontroller);
		OrderViewController orderViewController = new OrderViewController(facade);
		OrderView orderView = new OrderView(orderViewController);
		KitchenView kitchenView = new KitchenView();
	}
	
	public static void main(String[] args) {launch(args);}
}
