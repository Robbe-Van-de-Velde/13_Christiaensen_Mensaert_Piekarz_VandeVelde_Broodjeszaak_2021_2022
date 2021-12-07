package model.database.loadSaveStrategies;

/**
 * @author Robbe
 */

public enum LoadSaveStrategyEnum {
    EXCELBROODJE("model.database.loadSaveStrategies.BroodjesExcelLoadSaveStrategy"),
    TEKSTBROODJE("model.database.loadSaveStrategies.BroodjesTekstLoadSaveStrategy"),
    EXCELBELEG("model.database.loadSaveStrategies.BelegExcelLoadSaveStrategy"),
    TEKSTBELEG("model.database.loadSaveStrategies.BelegTekstLoadSaveStrategy");

    private final String path;

    LoadSaveStrategyEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
