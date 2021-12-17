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

import java.awt.*;
import java.time.Clock;

public class OrderView {
	private Stage stage = new Stage();		
		
	public OrderView(OrderViewController controller){
		stage.setTitle("ORDER VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 650, 650);
		Pane pane = createNodeHierarchy();
		root.getChildren().add(pane);


		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	private Pane createNodeHierarchy(){
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

		//test buttons
		Button button1 = new Button("broodje");
		Button button2 = new Button("broodje");
		Button button3 = new Button("broodje");
		Button button4 = new Button("broodje");
		Button button5 = new Button("broodje");

		Button button7 = new Button("beleg");
		Button button8 = new Button("beleg");
		Button button9 = new Button("beleg");

		kolomKeuzeBroodjes.getChildren().add(button1);
		kolomKeuzeBroodjes.getChildren().add(button2);
		kolomKeuzeBroodjes.getChildren().add(button3);
		kolomKeuzeBroodjes.getChildren().add(button4);
		kolomKeuzeBroodjes.getChildren().add(button5);

		kolomKeuzeBeleg.getChildren().add(button7);
		kolomKeuzeBeleg.getChildren().add(button8);
		kolomKeuzeBeleg.getChildren().add(button9);

		return p1;
	}
}



/*BorderPane borderPane = new OrderViewMainPane();
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);*/