package msd;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileWriter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Group;

public class App extends Application{
    
    private static MaterialFilter mat = new MaterialFilter();

    @Override
    public void start(Stage primaryStage){

        try {

            ArrayList<String> selected = new ArrayList<String>();
            ArrayList<String> names = mat.getCharactersList();

            ArrayList<CheckBox> charBtn = new ArrayList<CheckBox>(names.size());

            System.out.println(names.size());
            for (int i=0;i<names.size();i++){
                charBtn.add(new CheckBox());
                CheckBox tmp = charBtn.get(i);
                tmp.setText(names.get(i));
                tmp.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent arg0) {
                        if (tmp.isSelected()){
                            selected.add(tmp.getText());
                        }
                    }
                });

            }
            VBox root = new VBox();
            ArrayList<CheckBox> temp = new ArrayList<CheckBox>();
            for (int i=0;i<names.size();i++){
                temp.add(charBtn.get(i));
            }   
            
            root.getChildren().addAll(temp);
            Button ok = new Button();
            ok.setText("OK");
            ok.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent arg0) {
                    File obj = new File("char.dat");
                    try {
                        FileWriter fw = new FileWriter(obj);
                        for (String item: selected){
                            fw.write(item + '\n');
                        }
                        fw.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            root.getChildren().add(ok);
            Scene scene = new Scene(root, 1200, 800);
            primaryStage.setTitle("Testing");
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		String day = new SimpleDateFormat("EEEE").format(date.getTime());
        final GenshinAPI genshinApi = new GenshinAPI();
        System.out.println(mat.getMaterialListOnDay(day).toString());
        System.out.println(mat.getCharactersOnDay(day).toString());
        System.out.println(mat.getWeaponsOnDay(day).toString());
        System.out.println(mat.getCharactersList());
        launch(args);
    }
}
