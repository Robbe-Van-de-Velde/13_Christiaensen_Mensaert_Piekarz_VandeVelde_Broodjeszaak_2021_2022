package view;

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
import model.Beleg;
import model.Broodje;
import model.bestelling.Bestellijn;
import model.bestelling.Bestelling;
import model.comparators.BelegComparatorByNaam;
import model.comparators.BroodjesComparatorByNaam;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Robbe, Patryk
 */

public class OrderView {
	private int volgnr;
	private Stage stage = new Stage();
	private Button nieuweBestellingButton,
			zelfdeBroodje,
			verwijderBroodje,
			annuleer,
			afsluiten,
			betaal,
			naarKeuken;
	private Label volgnrLabel;
	private Pane kolomKeuze, kolomKeuzeBroodjes, kolomKeuzeBeleg;
	private TableColumn<Bestellijn, String> colBroodje, colBeleg;
	private TableView bestellijnTabel;
	private OrderViewController controller;
	private ObservableList<Bestellijn> bestellijnObservableList;
	private Alert alert = new Alert(Alert.AlertType.ERROR);
	private boolean actief = false;

	public OrderView(OrderViewController controller){
		this.controller = controller;
		this.controller.setView(this);
		Bestelling bestelling = controller.voegBestellingToe();
		this.volgnr = bestelling.getVolgnr();
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
		mainPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

		//eerste rij
		HBox eersteRij = new HBox(100);
		eersteRij.setSpacing(200);
		nieuweBestellingButton = new Button("Nieuwe bestelling");
		nieuweBestellingButton.setOnAction(e -> nieuweBestelling());
		volgnrLabel = new Label("Volgnr: " + this.volgnr);
		ChoiceBox promoties = new ChoiceBox();

		eersteRij.getChildren().addAll(nieuweBestellingButton, volgnrLabel, promoties);

		// kolomkeuze maken
		kolomKeuze = new VBox(15);
		kolomKeuze.setPadding(new Insets(5,5,5,5));
		kolomKeuze.setMinWidth(650);
		kolomKeuzeBroodjes = new HBox(15);
		kolomKeuzeBeleg = new HBox(15);

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
				button.setOnAction(e -> {
					try {
						toevoegenBroodje(broodje.getNaam());
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
				});
				button.setDisable(true);
				kolomKeuzeBroodjes.getChildren().add(button);
			}
		}

		//Alle beleg
		List<Beleg> beleggen = controller.getBeleggen();
		beleggen.sort(new BelegComparatorByNaam());
		for (Beleg beleg : beleggen) {
			if (beleg.getVoorraad() > 0){
				Button button = new Button(beleg.getNaam());
				button.setOnAction(e -> addBeleg(beleg.getNaam()));
				button.setDisable(true);
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
		bestellijnTabel = new TableView();
		bestellijnTabel.setMaxHeight(375);
		//Broodje
		colBroodje = new TableColumn<Bestellijn, String>("Broodje");
		colBroodje.setMinWidth(150);
		colBroodje.setCellValueFactory(new PropertyValueFactory<Bestellijn, String>("naamBroodje"));
		//Beleg
		colBeleg = new TableColumn<>("Beleg");
		colBeleg.setMinWidth(250);
		colBeleg.setCellValueFactory(new PropertyValueFactory<Bestellijn, String>("namenBeleg"));

		bestellijnTabel.getColumns().addAll(colBroodje, colBeleg);

		//Laatste kolom
		VBox kolom2 = new VBox(15);

		//ButtonBox
		VBox buttonBox = new VBox(15);

		buttonBox.setPadding(new Insets(20,20,20,20));
		Label lijnLijst = new Label("Selecteer een lijn uit de lijst");

		zelfdeBroodje = new Button("Voeg zelfde broodje toe");
		zelfdeBroodje.setDisable(true);
		zelfdeBroodje.setOnAction(e -> voegZelfdeBroodjeToe());

		verwijderBroodje = new Button("Verwijder broodje");
		verwijderBroodje.setDisable(true);
		verwijderBroodje.setOnAction(e -> verwijderBestellijn());

		buttonBox.getChildren().addAll(lijnLijst, zelfdeBroodje, verwijderBroodje);
		buttonBox.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		buttonBox.setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		//Annuleer button
		annuleer = new Button("Annuleer bestelling");
		annuleer.setDisable(true);
		annuleer.setOnAction(e -> annuleerBestelling());

		kolom2.getChildren().addAll(buttonBox, annuleer);
		rij4.getChildren().addAll(bestellijnTabel, kolom2);

		//Rij 5
		HBox rij5 = new HBox(15);
		rij5.setPadding(new Insets(20,20,20,20));
		afsluiten = new Button("Afsluiten bestelling");
		afsluiten.setDisable(true);
		Label teBetalen = new Label("Te betalen:");
		Label prijs = new Label("5€ (placeholder)");
		betaal = new Button("Betaal");
		betaal.setDisable(true);
		naarKeuken = new Button("Naar keuken");
		naarKeuken.setDisable(true);
		rij5.getChildren().addAll(afsluiten, teBetalen, prijs, betaal, naarKeuken);
		rij5.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
		rij5.setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		//panes in elkaar zetten
		mainPane.getChildren().addAll(eersteRij, kolomKeuze, rij3, rij4, rij5);

		return mainPane;
	}

	private void voegZelfdeBroodjeToe() {
		Bestellijn bestellijn = (Bestellijn) bestellijnTabel.getSelectionModel().getSelectedItem();
		if (bestellijn != null) {
			try {
				controller.voegZelfdeBroodjeToe(bestellijn, controller.getBestelling(volgnr));
			} catch (Exception e){
				alert.setContentText("Er is niet genoeg voorraad om dit broodje terug toe te voegen");
				alert.show();
			}
		}else {
			alert.setContentText("Je moet een bestellijn selecteren voordat je een eenzelfde broodje kan toevoegen");
			alert.show();
		}
	}

	private void verwijderBestellijn() {
		Bestellijn bestellijn = (Bestellijn) bestellijnTabel.getSelectionModel().getSelectedItem();
		if (bestellijn != null) {
			controller.verwijderBestellijn(bestellijn, controller.getBestelling(volgnr));
		} else {
			alert.setContentText("Je moet een bestellijn selecteren voordat je een broodje kan verwijderen");
			alert.show();
		}
	}

	public void toevoegenBroodje(String broodje) throws IOException {
		controller.voegBestellijnToe(broodje, controller.getBestelling(volgnr));
	}

	public void addBeleg(String beleg){
		Bestellijn bestellijn = (Bestellijn) bestellijnTabel.getSelectionModel().getSelectedItem();
		if (bestellijn != null) {
			controller.voegBelegToeAanBestellijn(bestellijn, beleg, controller.getBestelling(volgnr));
		} else {
			alert.setContentText("Je moet een bestellijn selecteren voordat je beleg kan toevoegen");
			alert.show();
		}
	}

	public void nieuweBestelling(){
		actief = true;
		this.volgnrLabel.setText("Volgnr: " + volgnr);
		nieuweBestellingButton.setDisable(true);
		zelfdeBroodje.setDisable(false);
		verwijderBroodje.setDisable(false);
		annuleer.setDisable(false);
		afsluiten.setDisable(false);

		List<Node> broodjesChildren = kolomKeuzeBroodjes.getChildren();
		for (Node broodje : broodjesChildren){
			Button button = (Button) broodje;
			button.setDisable(false);
		}

		List<Node> belegChildren = kolomKeuzeBeleg.getChildren();
		for (Node beleg : belegChildren){
			Button button = (Button) beleg;
			button.setDisable(false);
		}
	}

	public void annuleerBestelling(){
		actief = false;
		this.volgnrLabel.setText("Volgnr: ");
		nieuweBestellingButton.setDisable(false);
		zelfdeBroodje.setDisable(true);
		verwijderBroodje.setDisable(true);
		annuleer.setDisable(true);
		afsluiten.setDisable(true);

		List<Node> broodjesChildren = kolomKeuzeBroodjes.getChildren();
		for (Node broodje : broodjesChildren){
			Button button = (Button) broodje;
			button.setDisable(true);
		}

		List<Node> belegChildren = kolomKeuzeBeleg.getChildren();
		for (Node beleg : belegChildren){
			Button button = (Button) beleg;
			button.setDisable(true);
		}
		controller.verwijderBestelling(controller.getBestelling(volgnr));
		volgnr = controller.voegBestellingToe().getVolgnr();
	}

	public void updateBestellijnen(List<Bestellijn> bestellijnen){
		bestellijnObservableList = FXCollections.observableArrayList(bestellijnen);
		bestellijnTabel.setItems(bestellijnObservableList);
		bestellijnTabel.refresh();
	}

	public void updateBroodjesKnoppen(Map<String, Integer> voorraadLijst){
		List<Node> broodjesChildren = kolomKeuzeBroodjes.getChildren();
		for (Node broodje : broodjesChildren){
			Button button = (Button) broodje;
			int voorraad = voorraadLijst.get(button.getText());
			if (voorraad > 0 && actief) {
				button.setDisable(false);
			} else {
				button.setDisable(true);
			}
		}
	}

	public void updateBelegKnoppen(Map<String, Integer> voorraadLijstBeleg) {
		List<Node> belegChildren = kolomKeuzeBeleg.getChildren();
		for (Node beleg : belegChildren){
			Button button = (Button) beleg;
			int voorraad = voorraadLijstBeleg.get(button.getText());
			if (voorraad > 0 && actief){
				button.setDisable(false);
			}else {
				button.setDisable(true);
			}
		}
	}

	public int getVolgnr() {
		return volgnr;
	}
}



/*BorderPane borderPane = new OrderViewMainPane();
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);*/