package view;

import controller.OrderViewController;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Button;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import model.Beleg;
import model.Broodje;

import java.time.Clock;
import java.util.List;

public class OrderView {
	private Stage stage = new Stage();		
		
	public OrderView(OrderViewController controller){
		stage.setTitle("ORDER VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 650, 650);
		Pane pane = createNodeHierarchy(controller);
		root.getChildren().add(pane);


		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	private Pane createNodeHierarchy(OrderViewController controller){
		// panes maken
		VBox p1 = new VBox(8);
		VBox kolomKeuze = new VBox(8);
		HBox kolomKeuzeBroodjes = new HBox(10);
		HBox kolomKeuzeBeleg = new HBox(10);

		kolomKeuze.setBackground(
				new Background(
						new BackgroundFill(Color.CYAN,
								CornerRadii.EMPTY, Insets.EMPTY)));


		//panes in elkaar zetten
		p1.getChildren().add(kolomKeuze);
		kolomKeuze.getChildren().add(kolomKeuzeBroodjes);
		kolomKeuze.getChildren().add(kolomKeuzeBeleg);


		//Alle broodjes
		List<Broodje> broodjes = controller.getBroodjes();
		for (Broodje broodje : broodjes) {
			if (broodje.getVoorraad() > 0) {
				Button button = new Button(broodje.getNaam());
				kolomKeuzeBroodjes.getChildren().add(button);
			}
		}

		//Alle beleg
		List<Beleg> beleggen = controller.getBeleggen();
		for (Beleg beleg : beleggen) {
			if (beleg.getVoorraad() > 0){
				Button button = new Button(beleg.getNaam());
				kolomKeuzeBeleg.getChildren().add(button);
			}
		}

		return p1;
	}
}



/*BorderPane borderPane = new OrderViewMainPane();
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);*/