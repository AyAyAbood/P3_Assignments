
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3hw4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author AboOod_shbika99
 */
public class TableViewPane2Controller implements Initializable {

    @FXML
    private TextArea txtArea;
    @FXML
    private Button buttonSelect1;
    @FXML
    private Button buttonSelect2;
    @FXML
    private Button buttonSelect3;
    @FXML
    private Button buttonSelect4;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, Integer> tcID;
    @FXML
    private TableColumn<Student, String> tcName;
    @FXML
    private TableColumn<Student, String> tcMajor;
    @FXML
    private TableColumn<Student, Double> tcGrade;
    Statement statement;
    List<Student> listStudents = new LinkedList<>();
    @FXML
    private Button executeUpdate;
    @FXML
    private Button executeQuery;
    @FXML
    private Button refresh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/school?serverTimeZone=UTC", "root", "");
            this.statement = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        tcID.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcMajor.setCellValueFactory(new PropertyValueFactory("major"));
        tcGrade.setCellValueFactory(new PropertyValueFactory("grade"));
        try {
            showStudents();
        } catch (SQLException ex) {
            Logger.getLogger(TableViewPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void buttonSelect1Handle(ActionEvent event) {
        List<Student> ListSS1 = listStudents.stream().filter(v -> v.getMajor().equals("Software Engineering")).collect(Collectors.toList());
        resetContorls();
        tableView.getItems().addAll(ListSS1);
    }

    @FXML
    private void buttonSelect2Handle(ActionEvent event) {
        List<Student> ListSS2 = listStudents.stream().filter(v -> v.getGrade() >= 90).collect(Collectors.toList());
        resetContorls();
        tableView.getItems().addAll(ListSS2);
    }

    @FXML
    private void buttonSelect3Handle(ActionEvent event) {
        List<Student> ListSS3 = listStudents.stream().filter(v -> v.getGrade() >= 60).sorted(Comparator.comparing(Student::getName)).collect(Collectors.toList());
        resetContorls();
        tableView.getItems().addAll(ListSS3);
    }

    @FXML
    private void buttonSelect4Handle(ActionEvent event) {
        List<Student> ListSS3 = listStudents.stream().filter(v -> v.getMajor().equals("Computer Science") && v.getGrade() < 70).map(v -> new Student(v.getId(), v.getName(), v.getMajor(), v.getGrade() + 3)).collect(Collectors.toList());
        resetContorls();
        tableView.getItems().addAll(ListSS3);
    }

    private void resetContorls() {
        txtArea.clear();
        tableView.getItems().clear();
    }

    private void showStudents() throws SQLException {
        ResultSet rs = this.statement.executeQuery("Select * From student");
        tableView.getItems().clear();
        while (rs.next()) {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setMajor(rs.getString("major"));
            student.setGrade(rs.getDouble("grade"));
            listStudents.add(student);
            tableView.getItems().add(student);
        }
    }
    
    private void showStudents2() throws SQLException {
        ResultSet rs = this.statement.executeQuery("Select * From student");
        tableView.getItems().clear();
        while (rs.next()) {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setMajor(rs.getString("major"));
            student.setGrade(rs.getDouble("grade"));
            tableView.getItems().add(student);
        }
    }

    @FXML
    private void executeUpdateHandle(ActionEvent event) {
        if (!txtArea.getText().equals("")) {
            try {
                String sql = txtArea.getText();
                resetContorls();
                this.statement.executeUpdate(sql);
                showStudents2();
            } catch (SQLException ex) {
                //Logger.getLogger(TableViewPane2Controller.class.getName()).log(Level.SEVERE, null, ex);
                txtArea.setText("the SQL statement is incorrect!");
                try {
                    showStudents2();
                } catch (SQLException ex1) {
                    Logger.getLogger(TableViewPane2Controller.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }

        }
    }

    @FXML
    private void executeQueryHandle(ActionEvent event) {
        if (!txtArea.getText().equals("")) {
            try {
                String sql = txtArea.getText();
                ResultSet rs = this.statement.executeQuery(sql);
                tableView.getItems().clear();
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setMajor(rs.getString("major"));
                    student.setGrade(rs.getDouble("grade"));
                    //listStudents.add(student);
                    tableView.getItems().add(student);
                }
            } catch (SQLException ex) {
                //Logger.getLogger(TableViewPane2Controller.class.getName()).log(Level.SEVERE, null, ex);
                txtArea.setText("the SQL statement is incorrect!");
                try {
                    showStudents2();
                } catch (SQLException ex1) {
                    Logger.getLogger(TableViewPane2Controller.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }

        }
    }

    @FXML
    private void executeRefreshHandle(ActionEvent event) throws SQLException {
        showStudents2();
    }

}
