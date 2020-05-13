/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3hw5n;

import java.util.Map;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author AboOod_shbika99
 */
public class P3HW5N extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane jpaPane = FXMLLoader.load(getClass().getResource("jpaPane2.fxml"));
        Scene scene = new Scene(jpaPane);
        primaryStage.setTitle("Chapter 5 Homework");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
