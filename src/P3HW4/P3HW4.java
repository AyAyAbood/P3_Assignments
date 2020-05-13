package p3hw4;

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
public class P3HW4 extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane paneTableView = FXMLLoader.load(getClass().getResource("TableViewPane.fxml"));
        Pane paneTableView2 =  FXMLLoader.load(getClass().getResource("TableViewPane2.fxml"));
        Map<String, Pane> mapPanes = new TreeMap<>();
        mapPanes.put("TableView1", paneTableView);
        mapPanes.put("TableView2", paneTableView2);
        Scene scene = new Scene(mapPanes.get("TableView1"));
        primaryStage.setTitle("Chapter 4 Homework");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
