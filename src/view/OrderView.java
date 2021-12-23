package view;

import controller.OrderViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import model.Beleg;
import model.Broodje;
import model.comparators.BelegComparatorByNaam;
import model.comparators.BroodjesComparatorByNaam;

import java.time.Clock;
import java.util.Arrays;
import java.util.List;

public class OrderView {
	private Stage stage = new Stage();
	private Button nieuweBestellingButton, zelfdeBroodje, verwijderBroodje, annuleer, afsluiten, betaal, naarKeuken;
		
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
		VBox mainPane = new VBox(15);

		//eerste rij
		HBox eersteRij = new HBox(100);
		eersteRij.setSpacing(200);
		nieuweBestellingButton = new Button("Nieuwe bestelling");
		//TODO nog implementeren nadat facade in orde is
		Label volgnr = new Label("Volgnr: ");
		ChoiceBox promoties = new ChoiceBox();

		eersteRij.getChildren().addAll(nieuweBestellingButton, volgnr, promoties);

		// kolomkeuze maken
		VBox kolomKeuze = new VBox(15);
		kolomKeuze.setPadding(new Insets(5,5,5,5));
		kolomKeuze.setMinWidth(650);
		HBox kolomKeuzeBroodjes = new HBox(15);
		HBox kolomKeuzeBeleg = new HBox(15);

		kolomKeuze.setBackground(
				new Background(
						new BackgroundFill(Color.LIGHTYELLOW,
								CornerRadii.EMPTY, Insets.EMPTY)));
		kolomKeuze.setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));




		//Alle broodjes
		List<Broodje> broodjes = controller.getBroodjes();
		broodjes.sort(new BroodjesComparatorByNaam());
		for (Broodje broodje : broodjes) {
			if (broodje.getVoorraad() > 0) {
				Button button = new Button(broodje.getNaam());
				kolomKeuzeBroodjes.getChildren().add(button);
			}
		}

		//Alle beleg
		List<Beleg> beleggen = controller.getBeleggen();
		beleggen.sort(new BelegComparatorByNaam());
		for (Beleg beleg : beleggen) {
			if (beleg.getVoorraad() > 0){
				Button button = new Button(beleg.getNaam());
				kolomKeuzeBeleg.getChildren().add(button);
			}
		}
		kolomKeuze.getChildren().add(kolomKeuzeBroodjes);
		kolomKeuze.getChildren().add(kolomKeuzeBeleg);

		//Rij 3
		HBox rij3 = new HBox();
		Label aantalBroodjes = new Label("Aantal broodjes: ");
		rij3.getChildren().add(aantalBroodjes);

		//Rij 4
		HBox rij4 = new HBox(15);
		//Tabel
		TableView broodjeTabel = new TableView();
		broodjeTabel.setMaxHeight(375);
		//Broodje
		TableColumn<Broodje, String> colBroodje = new TableColumn<Broodje, String>("Broodje");
		colBroodje.setMinWidth(150);
		colBroodje.setCellValueFactory(new PropertyValueFactory<Broodje, String>("naam"));
		//Beleg
		TableColumn<Beleg, String> colBeleg = new TableColumn<>("Beleg");
		colBeleg.setMinWidth(250);
		colBeleg.setCellValueFactory(new PropertyValueFactory<Beleg, String>("naam"));

		broodjeTabel.getColumns().addAll(colBroodje, colBeleg);

		//Laatste kolom
		VBox kolom2 = new VBox(15);

		//ButtonBox
		VBox buttonBox = new VBox(15);

		buttonBox.setPadding(new Insets(20,20,20,20));
		Label lijnLijst = new Label("Selecteer een lijn uit de lijst");
		zelfdeBroodje = new Button("Voeg zelfde broodje toe");
		verwijderBroodje = new Button("Verwijder broodje");
		buttonBox.getChildren().addAll(lijnLijst, zelfdeBroodje, verwijderBroodje);
		buttonBox.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		buttonBox.setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		//Annuleer button
		annuleer = new Button("Annuleer bestelling");

		kolom2.getChildren().addAll(buttonBox, annuleer);
		rij4.getChildren().addAll(broodjeTabel, kolom2);

		//Rij 5
		HBox rij5 = new HBox(15);
		rij5.setPadding(new Insets(20,20,20,20));
		afsluiten = new Button("Afsluiten bestelling");
		Label teBetalen = new Label("Te betalen:");
		Label prijs = new Label("5€ (placeholder)");
		betaal = new Button("Betaal");
		naarKeuken = new Button("Naar keuken");
		rij5.getChildren().addAll(afsluiten, teBetalen, prijs, betaal, naarKeuken);
		rij5.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		rij5.setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		//panes in elkaar zetten
		mainPane.getChildren().addAll(eersteRij, kolomKeuze, rij3, rij4, rij5);

		return mainPane;
	}
}



/*BorderPane borderPane = new OrderViewMainPane();
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);*/