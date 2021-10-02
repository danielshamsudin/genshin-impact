
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application{
    
    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException{
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Parent root = loader.load();
        MainPageController controller= loader.<MainPageController>getController();
        controller.setMainApp(this);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Page");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void openGrindOverview() throws IOException {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GrindOverview.fxml"));
            Parent root = loader.load();
            GrindOverviewController controller = loader.<GrindOverviewController>getController();
            controller.setMainApp(this);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Grind Overview");
            primaryStage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}