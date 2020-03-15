module TerraHistoricaSecond {

    requires  javafx.fxml;
    requires javafx.controls;


    exports Controller;
    opens Controller to javafx.graphics;
}