package view.panels;

import controller.AdminViewController;
import controller.OrderViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
    final NumberAxis xAxis2 = new NumberAxis();
    final CategoryAxis yAxis2 = new CategoryAxis();
    final BarChart<Number,String> chart1 =
                new BarChart<Number,String>(xAxis,yAxis);
    private VBox mainPane, records1, records2;
    private XYChart.Series series1 = new XYChart.Series();
    final BarChart<Number,String> chart2 =
            new BarChart<Number,String>(xAxis,yAxis);
    private XYChart.Series series2 = new XYChart.Series();


    public StatistiekPane(AdminViewController controller) {
        this.controller = controller;
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        mainPane = new VBox(20);
        mainPane.setPadding(new Insets(5,15,15,15));
        mainPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        mainPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        mainPane.setMinWidth(650);

        //eerste grafiek
        VBox eerstebox = new VBox();
        //HBox eerstebox = new HBox(20);
        titel1 = new Label("Omzetstatistiek broodjes (in aantal stuks)");
        records1 = new VBox();
        records1.setPadding(new Insets(5,15,5,15));
        records1.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        records1.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        xAxis.setTickLabelRotation(90);
        chart1.setMaxWidth(500);
        chart1.setMaxHeight(150);
        xAxis.setLabel("aantal");
        yAxis.setLabel("Broodjes");

        eerstebox.getChildren().addAll(titel1, records1, chart1);

        //tweede grafiek
        VBox tweedebox = new VBox();
        titel2 = new Label("Omzetstatistiek beleg (in aantal porties)");
        records2 = new VBox();
        records2.setPadding(new Insets(5,15,5,15));
        records2.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        records2.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        xAxis2.setTickLabelRotation(90);
        chart2.setMaxWidth(500);
        chart2.setMaxHeight(300);
        xAxis2.setLabel("aantal");
        yAxis2.setLabel("Belegen");

        tweedebox.getChildren().addAll(titel2, records2, chart2);

        mainPane.getChildren().addAll(eerstebox, tweedebox);

        refreshVerkochteBelegen();
        refreshVerkochteBroodjes();

        this.getChildren().add(mainPane);
    }

    public void refreshVerkochteBroodjes(){
        this.verkochteBroodjes = controller.getVerkochteBroodjes();
        records1.getChildren().removeAll(records1.getChildren());
        for (String key: verkochteBroodjes.keySet()){
            //update grafiek
            int value = verkochteBroodjes.get(key);
            series1.getData().add(new XYChart.Data(value, key));
            chart1.getData().addAll(series1);

            //update voor gegevens
            Label label = new Label(value + ": "+key);
            records1.getChildren().addAll(label);
        }
    }

    public void refreshVerkochteBelegen(){
        this.verkochteBelegen = controller.getVerkochteBelegen();
        records2.getChildren().removeAll(records2.getChildren());
        for (String key: verkochteBelegen.keySet()){
            //update grafiek
            int value = verkochteBelegen.get(key);
            series2.getData().add(new XYChart.Data(value, key));
            chart2.getData().addAll(series2);

            //update voor gegevens
            Label label = new Label(value + ": "+key);
            records2.getChildren().addAll(label);
        }
    }
}
