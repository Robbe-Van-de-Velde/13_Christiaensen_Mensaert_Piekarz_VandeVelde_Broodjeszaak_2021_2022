package view;


import controller.AdminViewController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import view.panels.InstellingenPane;
import view.panels.SandwichOverviewPane;

/**
 * @author Robbe
 */

public class AdminMainPane extends BorderPane {
    private SandwichOverviewPane sandwichOverviewPane;
    private InstellingenPane instellingenPane;

	public AdminMainPane(AdminViewController controller){
	    TabPane tabPane = new TabPane();
        sandwichOverviewPane = new SandwichOverviewPane(controller);
        Tab broodjesTab = new Tab("Broodjes/Beleg",sandwichOverviewPane);
        instellingenPane = new InstellingenPane(controller);
        Tab instellingTab = new Tab("Instellingen", instellingenPane);
        Tab statistiekTab = new Tab("Statistieken");
        tabPane.getTabs().add(broodjesTab);
        tabPane.getTabs().add(statistiekTab);
        tabPane.getTabs().add(instellingTab);
        this.setCenter(tabPane);
	}

	public void updateVoorraad(){
	    this.sandwichOverviewPane.refreshBroodjes();
	    this.sandwichOverviewPane.refreshBeleggen();
    }
}
