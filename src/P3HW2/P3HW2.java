package p3hw2;

import com.sun.javafx.scene.control.skin.CustomColorDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class P3HW2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private ListView<String> listView1;
    private ListView<String> listView2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button MSL = new Button("Multiple Selection Lists");
        Button TC = new Button("Temperature Converter");
        Button FV = new Button("File View");
        Button LP = new Button("Login Page");
        GridPane ggN = new GridPane();
        ggN.add(MSL, 0, 0);
        ggN.add(TC, 1, 0);
        ggN.add(FV, 0, 1);
        ggN.add(LP, 1, 1);
        ggN.setAlignment(Pos.CENTER);
        ggN.setVgap(10);
        ggN.setHgap(10);
        Scene scene = new Scene(ggN, 400, 200);
        Button GB = new Button("Go Back");
        GB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                primaryStage.setScene(scene);
                primaryStage.setTitle("Main");
            }
        });
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
///////////////////////////////////////////////////////////////////////////////////////////////////////////
        MSL.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                listView1 = new ListView<>();
                listView1.setPrefSize(160, 200);
                listView1.setEditable(true);
                ObservableList<String> items = FXCollections.observableArrayList("Black", "Blue", "Cyan", "Dark Gray", "Gray", "Green", "Yellow", "Red", "Purple");
                listView1.setItems(items);
                Button copy = new Button("copy >>> ");
                Alert a = new Alert(AlertType.NONE);
                listView2 = new ListView<>();
                listView2.setPrefSize(160, 200);
                listView1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                copy.setOnAction(event -> {
                    if (event.getSource() == copy) //Checking the event source
                    {
                        if (!listView1.getItems().isEmpty()) {
                            listView2.getItems().clear();
                            ObservableList<Integer> selectedItems = listView1.getSelectionModel().getSelectedIndices();
                            if (selectedItems.size() != 0) {
                                int arr[] = new int[selectedItems.size()];
                                for (int i = 0; i < arr.length; i++) {
                                    arr[i] = selectedItems.get(i);
                                }
                                for (int i = 0; i < arr.length; i++) {
                                    listView2.getItems().add(listView1.getItems().get(arr[i]));
                                }
                            } else {
                                a.setAlertType(AlertType.WARNING);
                                a.setContentText("No Item Selected!");
                                a.show();
                            }
                        }
                    }
                });
                GridPane g1 = new GridPane();
                g1.add(listView1, 1, 0);
                VBox CGB = new VBox(10, copy, GB);
                CGB.setAlignment(Pos.CENTER);
                g1.add(CGB, 2, 0);
                g1.add(listView2, 3, 0);
                g1.setAlignment(Pos.CENTER);
                g1.setHgap(5);
                //HBox h1 = new HBox(20,listView1,copy,listView2);
                //h1.setAlignment(Pos.CENTER);
                Scene scene1 = new Scene(g1, 460, 245);
                primaryStage.setTitle("Multiple Selection Lists");
                primaryStage.setScene(scene1);

            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        TC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                Label L1 = new Label("Enter Celsius temperature: ");
                TextField t1 = new TextField();
                RadioButton r1 = new RadioButton("Fahrenheit");
                RadioButton r2 = new RadioButton("Kelvin");
                ToggleGroup radioGroup = new ToggleGroup();
                r1.setToggleGroup(radioGroup);
                r2.setToggleGroup(radioGroup);
                Label L2 = new Label("New Temperature in is: ");
                r1.setOnAction((event) -> {
                    if (event.getSource() == r1) {
                        if (!t1.getText().isEmpty()) {
                            double cel = Double.parseDouble(t1.getText());
                            double feh = (cel * 9) / 5 + 32;
                            L2.setText("New Temperature in is: " + feh);
                        }
                    }
                });
                r2.setOnAction((event) -> {
                    if (event.getSource() == r2) {
                        if (!t1.getText().isEmpty()) {
                            double cel2 = Double.parseDouble(t1.getText());
                            double kel = cel2 + 273.15;
                            L2.setText("New Temperature in is: " + kel);
                        }
                    }
                });
                HBox radios = new HBox(20, r1, r2);
                radios.setAlignment(Pos.CENTER);
                VBox temV = new VBox(20, L1, t1, radios, L2, GB);
                temV.setAlignment(Pos.CENTER);
                Scene scene2 = new Scene(temV, 440, 220);
                primaryStage.setTitle("Temperature converter");
                primaryStage.setScene(scene2);
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        FV.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                MenuBar menuBar = new MenuBar();
                Menu fileMenu = new Menu("File");
                MenuItem OpenItem = new MenuItem("Open");
                MenuItem CloseItem = new MenuItem("Close");
                MenuItem exitItem = new MenuItem("Exit");
                fileMenu.getItems().add(OpenItem);
                fileMenu.getItems().add(CloseItem);
                fileMenu.getItems().add(exitItem);
                Menu editMenu = new Menu("Edit");
                MenuItem FontItem = new MenuItem("Font");
                MenuItem ColorItem = new MenuItem("Color");
                editMenu.getItems().add(FontItem);
                editMenu.getItems().add(ColorItem);
                menuBar.getMenus().addAll(fileMenu, editMenu);
                Menu GoBackButton = new Menu("Go Back");
                MenuItem gbb = new MenuItem("Are you Sure?");
                gbb.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent Event) {
                        primaryStage.setTitle("Main");
                        primaryStage.setScene(scene);
                    }
                });
                GoBackButton.getItems().add(gbb);
                menuBar.getMenus().add(GoBackButton);
                TextArea tt1 = new TextArea();
                tt1.setWrapText(true);
                BorderPane borderPane = new BorderPane();
                borderPane.setTop(menuBar);
                borderPane.setCenter(tt1);
                OpenItem.setOnAction((event) -> {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Open Resource File");
                    fileChooser.setInitialDirectory(new File("."));
                    File f = fileChooser.showOpenDialog(primaryStage);
                    String OpenTxt = "";
                    Scanner scanner = null;
                    try {
                        scanner = new Scanner(f);

                    } catch (FileNotFoundException ex) {
                        System.out.println("Something went wrong....");
                        System.exit(0);
                    }
                    while (scanner.hasNextLine()) {
                        OpenTxt += scanner.nextLine();
                    }
                    tt1.appendText(OpenTxt);
                    tt1.setEditable(true);
                    scanner.close();
                });
                CloseItem.setOnAction((event) -> {
                    tt1.clear();
                    tt1.setEditable(false);
                });
                exitItem.setOnAction((event) -> {
                    System.exit(0);
                });
                String color[] = {"Red", "Green", "Blue"};
                ChoiceDialog<String> ComboDialog1 = new ChoiceDialog<>(color[0], color);
                TextInputDialog ComboDialog2 = new TextInputDialog();
                ColorItem.setOnAction((event) -> {
                    ComboDialog1.setTitle("Color Selection");
                    ComboDialog1.setHeaderText("Select the color from the list");
                    ComboDialog1.setContentText("Available Colors");
                    Optional<String> resultOne = ComboDialog1.showAndWait();
                    if (resultOne.isPresent()) {
                        tt1.setStyle(tt1.getStyle()+"-fx-text-fill: " + ComboDialog1.getSelectedItem() + ";");
                    }
                });
                FontItem.setOnAction((event) -> {
                    ComboDialog2.setTitle("Font Size");
                    ComboDialog2.setHeaderText("Change the size of the font");
                    ComboDialog2.setContentText("insert the size here:");
                    Optional<String> resultSec = ComboDialog2.showAndWait();
                    if (resultSec.isPresent()) {
                        tt1.setStyle(tt1.getStyle()+"-fx-font-size: " + resultSec.get() + "pt;");
                    }
                });
                Scene scene3 = new Scene(borderPane, 300, 200);
                primaryStage.setTitle("File view");
                primaryStage.setScene(scene3);
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////////
        LP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent Event) {
                Label welcome = new Label("Welcome");
                welcome.getStyleClass().add("welcome");
                Label username = new Label("User name");
                username.getStyleClass().add("mylabels");
                Label result = new Label("");
                TextField usernameT = new TextField();
                Label password = new Label("Password");
                password.getStyleClass().add("mylabels");
                TextField passwordT = new TextField();
                Button signinButton = new Button("Sign in");
                Button exitButton = new Button("Exit");
                Button AddS = new Button("Add Student");
                Button ViewS = new Button("View Student");
                File ff1 = new File("users.txt");
                String user[] = new String[3];
                String users[][] = new String[3][2];
                Scanner scanner2 = null;
                try {
                    scanner2 = new Scanner(ff1);

                } catch (FileNotFoundException ex) {
                    System.out.println("Something went wrong....");
                    System.exit(0);
                }
                int kkk = 0;
                while (scanner2.hasNextLine()) {
                    user[kkk] = scanner2.nextLine();
                    kkk++;
                }
                scanner2.close();
                for (int p = 0; p < user.length; p++) {
                    users[p][0] = user[p].substring(0, user[p].indexOf(" "));
                    users[p][1] = user[p].substring(user[p].indexOf(" ") + 1);
                }
                signinButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        boolean gtfo = false;
                        if (event.getSource() == signinButton) {
                            for (int g = 0; g < user.length; g++) {
                                if (usernameT.getText().equals(users[g][0])) {
                                    if (passwordT.getText().equals(users[g][1])) {
                                        gtfo = true;
                                        break;
                                    }
                                }
                            }
                            if (gtfo) {
                                result.setText("Success");
                                VBox wha = new VBox(10, AddS, ViewS);
                                wha.setAlignment(Pos.CENTER);
                                Scene alt = new Scene(wha, 270, 270);
                                alt.getStylesheets().add("styles_HW2.css");
                                primaryStage.setScene(alt);
                            } else {
                                result.setText("Login Failed!");
                            }
                        }
                    }
                });
                exitButton.setOnAction((event) -> {
                    System.exit(0);
                });
                GridPane gg = new GridPane();
                gg.add(welcome, 0, 0);
                gg.add(username, 0, 1);
                gg.add(password, 0, 2);
                gg.add(usernameT, 1, 1);
                gg.add(passwordT, 1, 2);
                HBox both = new HBox(10, signinButton, exitButton);
                both.setAlignment(Pos.CENTER_RIGHT);
                gg.add(both, 1, 3);
                gg.add(result, 1, 4);
                gg.add(GB, 1, 5);
                gg.setAlignment(Pos.CENTER);
                gg.setHgap(10);
                gg.setVgap(10);
                Scene scene4 = new Scene(gg, 270, 270);
                scene4.getStylesheets().add("styles_HW2.css");
                primaryStage.setTitle("Login Page");
                primaryStage.setScene(scene4);
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////////////////
        primaryStage.setTitle("Main");
        primaryStage.show();
    }
}