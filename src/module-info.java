module TerraHistoricaFX {

   requires  javafx.fxml;
   requires javafx.controls;


   exports Controller;
   opens Controller to javafx.fxml;
//   opens Controller to javafx.graphics;
   opens View to javafx.graphics;
}