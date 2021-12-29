package view.panels;

import controller.AdminViewController;
import controller.OrderViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.bestelling.Bestellijn;
import model.bestelling.Bestelling;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**@author patryk **/

public class StatistiekPane extends Pane {
    private AdminViewController controller;
    private Label titel1, titel2;
    private List<Bestelling> alleVerkochte;
    private Map<String, Integer> verkochteBelegen, verkochteBroodjes;
    final NumberAxis xAxis = new NumberAxis();
    final CategoryAxis yAxis = new CategoryAxis();
    final BarChart<Number,String> chart =
                new BarChart<Number,String>(xAxis,yAxis);

    public StatistiekPane(AdminViewController controller) {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        VBox mainPane = new VBox(20);
        mainPane.setPadding(new Insets(15,15,15,15));
        mainPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        mainPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        mainPane.setMinWidth(650);
        titel1 = new Label("Omzetstatistiek broodjes (in aantal stuks)");


        titel2 = new Label("Omzetstatistiek beleg (in aantal porties)");




        /*xAxis.setTickLabelRotation(90);
        xAxis.maxWidth(30);
        yAxis.setLabel("Country");
        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data(1, "austria"));
        series1.getData().add(new XYChart.Data(2, "brazil"));
        series1.getData().add(new XYChart.Data(3, "france"));
        series1.getData().add(new XYChart.Data(4, "italy"));
        series1.getData().add(new XYChart.Data(5, "usa"));
        chart.getData().addAll(series1);*/

        mainPane.getChildren().addAll(titel1, titel2);

        this.getChildren().add(mainPane);
    }

    public void refreshVerkochteBroodjes(){
        this.verkochteBroodjes = controller.getVerkochteBroodjes();
        String test = "Omzetstatistiek broodjes (in aantal stuks)\n";
        verkochteBroodjes.forEach((k,v)-> System.out.println(k + v));

    }
    public void refreshVerkochteBelegen(){
        this.verkochteBelegen = controller.getVerkochteBelegen();

    }
}
