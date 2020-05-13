package p3hw3part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sun.launcher.resources.launcher;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class Q4 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
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
        ff1.createNewFile();
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
                        Scene alt = new Scene(wha, bounds.getWidth(), bounds.getHeight());
                        alt.getStylesheets().add("styles_HW3Part2.css");
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
            Label LVL1 = new Label("Students sorted by Name: ");
            LVL1.getStyleClass().add("lessThiccNew");
            ListView<String> listView2 = new ListView<>();
            Label LVL2 = new Label("Students mapped to their Names and Grades, sorted by Grade(descending):  ");
            ListView<String> listView3 = new ListView<>();
            LVL2.getStyleClass().add("lessThiccNew");
            Label LVL3 = new Label("Students mapped to their Names and Grades, Grades values in the range 80 to 90, sorted by Grade(descending):  ");
            LVL3.getStyleClass().add("lessThiccNew");
            Label LVL4Title = new Label("The average of Students Grades");
            LVL4Title.getStyleClass().add("thicc");
            Label LVL4 = new Label();
            LVL4.getStyleClass().add("lessThiccNew");
            ListView<String> listView5 = new ListView<>();
            Label LVL5 = new Label("Students Grouped by Major: ");
            LVL5.getStyleClass().add("lessThiccNew");
            listView1.setPrefSize(290, 190);
            listView2.setPrefSize(290, 190);
            listView3.setPrefSize(290, 190);
            listView5.setPrefSize(290, 190);
            listView1.setEditable(true);
            Label sd = new Label("Student Data");
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
            ArrayList<Student> stdsNew = new ArrayList<>();
            Add2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent eventT) {
                    if (eventT.getSource() == Add2 && IDTxt.getText().length() != 0 && NameTxt.getText().length() != 0 && MajorTxt.getText().length() != 0 && GradeTxt.getText().length() != 0) {

                        stdsNew.add(new Student(Integer.parseInt(IDTxt.getText()), NameTxt.getText(), MajorTxt.getText(), Double.parseDouble(GradeTxt.getText())));
                        ArrayList<Student> stdsNewN = stdsNew.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(Student::getId))), ArrayList::new));
                        listView1.getItems().clear();
                        ArrayList<Student> stdsNew1 = (ArrayList<Student>) stdsNewN.stream().sorted(Comparator.comparing(Student::getName)).collect(Collectors.toList());

                        stdsNew1.forEach((val) -> {
                            listView1.getItems().add(val.getId() + "    " + val.getName() + "       " + val.getMajor() + "          " + val.getGrade());
                        });

                        listView2.getItems().clear();
                        List<String> stdsNew2 = stdsNewN.stream().sorted(Comparator.comparing(Student::getGrade).reversed()).map(e -> e.getName() + "     " + e.getGrade()).collect(Collectors.toList());
                        stdsNew2.forEach((val) -> {
                            listView2.getItems().add(val);
                        });

                        listView3.getItems().clear();
                        List<String> stdsNew3 = stdsNewN.stream().filter(e -> e.getGrade() >= 80 && e.getGrade() <= 90).sorted(Comparator.comparing(Student::getGrade).reversed()).map(e -> e.getName() + "     " + e.getGrade()).collect(Collectors.toList());
                        stdsNew3.forEach((val) -> {
                            listView3.getItems().add(val);
                        });

                        double[] grades = new double[stdsNewN.size()];
                        for (int i = 0; i < stdsNewN.size(); i++) {
                            grades[i] = stdsNewN.get(i).getGrade();
                        }

                        double avg = DoubleStream.of(grades).average().getAsDouble();
                        LVL4.setText(String.valueOf(avg));

                        listView5.getItems().clear();
                        List<Student> stdsNew5 = stdsNewN;
                        stdsNew5.stream().collect(Collectors.groupingBy(Student::getMajor)).forEach((major, stdMajor) -> {
                            listView5.getItems().add(major);
                            stdMajor.forEach(e -> listView5.getItems().add(e.getId() + "    " + e.getName() + "       " + e.getMajor() + "          " + e.getGrade()));
                        });
                    }
                }
            });
            Reset.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent eventT) {
                    stdsNew.clear();
                    listView1.getItems().clear();
                    listView2.getItems().clear();
                    listView3.getItems().clear();
                    LVL4.setText(" ");
                    listView5.getItems().clear();
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
            gg2.add(lastLabel, 1, 0);
            gg2.add(ID, 0, 1);
            gg2.add(IDTxt, 1, 1);
            gg2.add(Name, 0, 2);
            gg2.add(NameTxt, 1, 2);
            gg2.add(Major, 0, 3);
            gg2.add(MajorTxt, 1, 3);
            gg2.add(Grade, 0, 4);
            gg2.add(GradeTxt, 1, 4);
            gg2.add(myButtons, 1, 5);
            gg2.add(new VBox(3, LVL1, listView1), 3, 0, 1, 6);
            gg2.add(new VBox(3, LVL2, listView2), 3, 7, 1, 6);
            gg2.add(new VBox(3, LVL3, listView3), 3, 14, 1, 6);
            gg2.add(new VBox(3, LVL4Title, LVL4), 4, 1, 1, 3);
            gg2.add(new VBox(3, LVL5, listView5), 4, 5, 1, 6);
            gg2.setAlignment(Pos.TOP_LEFT);
            gg2.setPadding(new Insets(10, 0, 0, 20));
            gg2.setHgap(10);
            gg2.setVgap(10);
            Scene scene2 = new Scene(gg2, bounds.getWidth(), bounds.getHeight());
            scene2.getStylesheets().add("styles_HW3Part2.css");
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
        Scene scene1 = new Scene(gg, bounds.getWidth(), bounds.getHeight());
        scene1.getStylesheets().add("styles_HW3Part2.css");
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set Stage boundaries to visible bounds of the main screen
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
}