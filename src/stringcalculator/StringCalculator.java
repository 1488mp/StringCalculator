package stringcalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StringCalculator extends Application {    
    @Override
    public void start(Stage stage) throws Exception {
        GuiController.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("Gui.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);                
        
        stage.setScene(scene); 
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();       
    }    
    
    public static void main(String[] args) {
        launch(args);
    }    
}
