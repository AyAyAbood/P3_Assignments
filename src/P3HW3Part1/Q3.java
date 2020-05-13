package p3hw3part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class Q3 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        Label lastLabel = new Label("insert Students with diffrent IDs");
        Label welcome = new Label("Welcome");
        welcome.getStyleClass().add("thicc");
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
        Label prof = new Label("For the professor(or whoever is testing this): \n Username is 1 \n Password is 1");
        prof.getStyleClass().add("prof");
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
                        alt.getStylesheets().add("styles_HW3Part1.css");
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
        AddS.setOnAction((event) -> {
            int numnum = 0;
            ListView<String> listView1 = new ListView<>();
            listView1.setPrefSize(290.0, 320.0);
            listView1.setEditable(true);
            Label sd = new Label("Student Data:");
            sd.getStyleClass().add("thicc");
            Label ID = new Label("id:");
            ID.getStyleClass().add("lessThicc");
            TextField IDTxt = new TextField();
            Label Name = new Label("Name:");
            Name.getStyleClass().add("lessThicc");
            TextField NameTxt = new TextField();
            Label Major = new Label("Major:");
            Major.getStyleClass().add("lessThicc");
            TextField MajorTxt = new TextField();
            Label Grade = new Label("Grade:");
            Grade.getStyleClass().add("lessThicc");
            TextField GradeTxt = new TextField();
            Button Add2 = new Button("Add");
            Button Reset = new Button("Reset");
            Button Exit = new Button("Exit");
            List<Student> stds = new ArrayList();
            Add2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent eventT) {
                    listView1.getItems().clear();
                    String test = IDTxt.getText();
                    if (eventT.getSource() == Add2 && test.length() != 0) {
                        stds.add(new Student(Integer.parseInt(IDTxt.getText()), NameTxt.getText(), MajorTxt.getText(), Double.parseDouble(GradeTxt.getText())));
                        listView1.getItems().clear();
                        List<Student> std1 = stds.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(Student::getId))), ArrayList::new));
                        List<Student> std2 = std1.stream().sorted(Comparator.comparing(Student::getGrade).reversed()).collect(Collectors.toList());
                        std2.forEach((val) -> {
                            listView1.getItems().add(val.getId() + "    " + val.getName() + "       " + val.getMajor() + "          " + val.getGrade());
                        });
                    }
                }
            });
            Reset.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent eventT) {
                    stds.clear();
                    listView1.getItems().clear();
                    IDTxt.clear();
                    NameTxt.clear();
                    MajorTxt.clear();
                    GradeTxt.clear();
                }
            });
            Exit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent eventT) {
                    System.exit(0);
                }
            });
            HBox myButtons = new HBox(7, Add2, Reset, Exit);
            GridPane gg2 = new GridPane();
            gg2.add(sd, 0, 0);
            gg2.add(lastLabel,1,0);
            gg2.add(ID, 0, 1);
            gg2.add(IDTxt, 1, 1);
            gg2.add(Name, 0, 2);
            gg2.add(NameTxt, 1, 2);
            gg2.add(Major, 0, 3);
            gg2.add(MajorTxt, 1, 3);
            gg2.add(Grade, 0, 4);
            gg2.add(GradeTxt, 1, 4);
            gg2.add(myButtons, 1, 5);
            gg2.add(listView1, 3, 1, 1, 6);
            gg2.setAlignment(Pos.CENTER);
            gg2.setHgap(10);
            gg2.setVgap(10);
            Scene scene2 = new Scene(gg2, 620, 470);
            scene2.getStylesheets().add("styles_HW3Part1.css");
            primaryStage.setTitle("Student Entry Page");
            primaryStage.setScene(scene2);
            primaryStage.show();
        });
        GridPane gg = new GridPane();
        gg.add(welcome, 0, 0);
        gg.add(username, 0, 1);
        gg.add(password, 0, 2);
        gg.add(usernameT, 1, 1);
        gg.add(passwordT, 1, 2);
        gg.add(prof, 0, 5, 3, 1);
        HBox both = new HBox(10, signinButton, exitButton);
        both.setAlignment(Pos.CENTER_RIGHT);
        gg.add(both, 1, 3);
        gg.add(result, 1, 4);
        gg.setAlignment(Pos.CENTER);
        gg.setHgap(10);
        gg.setVgap(10);
        Scene scene1 = new Scene(gg, 270, 270);
        scene1.getStylesheets().add("styles_HW3Part1.css");
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
}
