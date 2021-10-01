
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
        // Button btn = new Button();
        // btn.setText("Test");
        // btn.setOnAction(new EventHandler<ActionEvent>(){
            
        //     @Override
        //     public void handle(ActionEvent event){
        //         System.out.println("Clicked button");
        //     }
        // });

        // StackPane root = new StackPane();
        // root.getChildren().add(btn);

        // Scene scene = new Scene(root, 300, 250);
        // primaryStage.setTitle("Testing");
        // primaryStage.setScene(scene);
        // primaryStage.show();
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Parent root = loader.load();
        MainPageController controller= loader.<MainPageController>getController();
        controller.setMainApp(this);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}