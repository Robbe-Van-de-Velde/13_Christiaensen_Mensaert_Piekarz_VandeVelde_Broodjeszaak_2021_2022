package view;

import controller.KitchenViewController;
import controller.OrderViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import model.bestelling.Bestellijn;
import model.bestelling.Bestelling;
import model.korting.KortingEnum;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class KitchenView {
	
	private Stage stage = new Stage();
	private Button volgendeBestelling, afwerken;
	private Label volgnrLabel, bestellingInhoud;
	private ChoiceBox promoties;
	
	public KitchenView(KitchenViewController controller){
		stage.setTitle("KITCHEN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(680);
		stage.setY(470);
		Group root = new Group();
		Scene scene = new Scene(root, 650, 200);
		Pane pane = createNodeHierarchy(controller);
		root.getChildren().add(pane);

		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	private Pane createNodeHierarchy(KitchenViewController controller){
		VBox mainPane = new VBox(15);
		mainPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

		//wachtrij
		HBox eersteRij = new HBox(100);
		eersteRij.setSpacing(340);
		volgendeBestelling = new Button("Volgende bestelling");
		volgendeBestelling.setOnAction(e -> volgendeBestelling());
		afwerken = new Button("Bestelling afgewerkt");
		afwerken.setOnAction(e -> bestellingAfwerken());

		eersteRij.getChildren().addAll(volgendeBestelling, afwerken);

		//bestelling view
		VBox tweederij = new VBox(50);
		tweederij.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		volgnrLabel = new Label("Bestellingen in wachtrij: 0\n");
		bestellingInhoud = new Label("bestelling...");
		//TODO add bestellingAfwerken

		tweederij.getChildren().addAll(volgnrLabel, bestellingInhoud);


		mainPane.getChildren().addAll(eersteRij, tweederij);
		return mainPane;
	}

	private void volgendeBestelling(){
		//TODO
	}

	private void bestellingAfwerken(){
		//TODO
	}

	private void updateWachtrij(){
		//TODO
	}

	//TODO move to facade
	private String BeschrijvingBestelling(Bestelling bestelling){
		String inhoud = "Volgnummer bestelling: ";
		List<Bestellijn> bestellijnen = bestelling.getLijstBestellijnen();
		inhoud += bestelling.getVolgnr() + " - Aantal broodjes: " + bestellijnen.size() +"\n";
		Map<Bestellijn, Integer> geordendeInhoud;

		//TODO broodjes vergelijken
		for (int i = 0; i <= bestellijnen.size()-1; i++) {
			int aantal = 0;


			for (int j = i + 1; j <= bestellijnen.size() - 1; j++) {


			}
		}
		return inhoud;
	}
}
