/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3hw4;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TableViewPaneController implements Initializable {

    @FXML
    private TextField txtfieldID;
    @FXML
    private TextField txtfieldName;
    @FXML
    private TextField txtfieldMajor;
    @FXML
    private TextField txtfieldGrade;
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
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonEdit;
    @FXML
    private Button buttonDelete;
    @FXML
    private TableView<Registration> tableView2;
    @FXML
    private TextField txtfieldStudentID;
    @FXML
    private TextField txtfieldCourseID;
    @FXML
    private TextField txtfieldSemester;
    @FXML
    private TableColumn<Registration, Integer> tcSID;
    @FXML
    private TableColumn<Registration, Integer> tcCID;
    @FXML
    private TableColumn<Registration, String> tcCName;
    @FXML
    private TableColumn<Registration, Integer> tcRoom;
    @FXML
    private TableColumn<Registration, Integer> tcSemester;
    @FXML
    private Button buttonAddCourse;
    Statement statement;
    Statement statement2;
    Statement statement3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/school?serverTimeZone=UTC", "root", "");
            this.statement = connection.createStatement();
            this.statement2 = connection.createStatement();
            this.statement3 = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        tcID.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcMajor.setCellValueFactory(new PropertyValueFactory("major"));
        tcGrade.setCellValueFactory(new PropertyValueFactory("grade"));
        tableView.getSelectionModel().selectedItemProperty().addListener(event -> showSelectedStudent());
        tcSID.setCellValueFactory(new PropertyValueFactory("studentID"));
        tcCID.setCellValueFactory(new PropertyValueFactory("courseID"));
        tcCName.setCellValueFactory(new PropertyValueFactory("courseName"));
        tcRoom.setCellValueFactory(new PropertyValueFactory("room"));
        tcSemester.setCellValueFactory(new PropertyValueFactory("semester"));
        try {
            showStudents();
            showRegistrations();
        } catch (SQLException ex) {
            Logger.getLogger(TableViewPaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    private void buttonShowHandle(ActionEvent event) throws Exception {
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
     */
    @FXML
    private void buttonAddHandle(ActionEvent event) throws SQLException, Exception {
        if (!txtfieldID.getText().equals("") && !txtfieldName.getText().equals("") && !txtfieldMajor.getText().equals("") && !txtfieldGrade.getText().equals("")) {
            Integer id = Integer.parseInt(txtfieldID.getText());
            String name = txtfieldName.getText();
            String major = txtfieldMajor.getText();
            Double grade = Double.parseDouble(txtfieldGrade.getText());
            String sql = "insert into student values (" + id + ",'" + name + "','" + major + "'," + grade + ");";
            resetContorls();
            this.statement.executeUpdate(sql);
            showStudents();
        }
    }

    @FXML
    private void buttonEditHandle(ActionEvent event) throws SQLException {
        if (!txtfieldID.getText().equals("") && !txtfieldName.getText().equals("") && !txtfieldMajor.getText().equals("") && !txtfieldGrade.getText().equals("")) {
            Integer id = Integer.parseInt(txtfieldID.getText());
            String name = txtfieldName.getText();
            String major = txtfieldMajor.getText();
            Double grade = Double.parseDouble(txtfieldGrade.getText());
            String sql = "Update student Set name='" + name + "', major='" + major + "', grade=" + grade + " Where id=" + id;
            resetContorls();
            this.statement.executeUpdate(sql);
            showStudents();
        }
    }

    @FXML
    private void buttonDeleteHandle(ActionEvent event) throws SQLException {
        Student student = tableView.getSelectionModel().getSelectedItem();
        if (student != null) {
            String sql = "delete from student where id=" + student.getId();
            resetContorls();
            this.statement.executeUpdate(sql);
            showStudents();
        }
    }

    private void resetContorls() {
        txtfieldID.setText("");
        txtfieldName.setText("");
        txtfieldMajor.setText("");
        txtfieldGrade.setText("");
        tableView.getItems().clear();
    }

    private void resetContorlsReg() {
        txtfieldStudentID.setText("");
        txtfieldCourseID.setText("");
        txtfieldSemester.setText("");
        tableView2.getItems().clear();
    }

    @FXML
    private void buttonAddCourseHandle(ActionEvent event) throws SQLException {
        if (!txtfieldStudentID.getText().equals("") && !txtfieldCourseID.getText().equals("") && !txtfieldSemester.getText().equals("")) {
            Integer StudentID = Integer.parseInt(txtfieldStudentID.getText());
            Integer CourseID = Integer.parseInt(txtfieldCourseID.getText());
            Integer Semester = Integer.parseInt(txtfieldSemester.getText());
            /*
            ResultSet rs1 = this.statement.executeQuery("Select name From course where id=" + CourseID);
            if(rs1.next()){
            String CourseName = rs1.getString("name");
            }
            rs1.close();
            ResultSet rs2 = this.statement.executeQuery("select room from course where id=" + CourseID);
            if(rs2.next()){
            String room = rs2.getString("room");
            }
            rs2.close();
            */ 
            String sql = "insert into registration values(" + StudentID + "," + CourseID + "," + Semester + ");";
            resetContorlsReg();
            this.statement.executeUpdate(sql);
            showRegistrations();
        }
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
            tableView.getItems().add(student);
        }
    }

    private void showRegistrations() throws SQLException {
        Integer courID = 0;
        Registration stR = null;
        ResultSet rsR = this.statement.executeQuery("Select * From registration");
        tableView2.getItems().clear();
        while (rsR.next()) {
            stR = new Registration();
            stR.setStudentID(rsR.getInt("studentId"));
            stR.setCourseID(rsR.getInt("courseId"));
            stR.setSemester(rsR.getInt("semester"));
            courID = rsR.getInt("courseId");
            ResultSet rs3 = this.statement2.executeQuery("select * from course where id=" + courID);
            if (rs3.next()) {
                stR.setCourseName(rs3.getString("name"));
            }
            rs3.close();
            ResultSet rs4 = this.statement3.executeQuery("select * from course where id=" + courID);
            if (rs4.next()) {
                stR.setRoom(rs4.getString("room"));
            }
            rs4.close();
            tableView2.getItems().add(stR);
        }

    }

    private void showName(int x) throws SQLException {
        ResultSet RSN = this.statement.executeQuery("select name from course where id=" + x);
    }

    private void showSelectedStudent() {
        Student employee = tableView.getSelectionModel().getSelectedItem();
        if (employee != null) {
            txtfieldID.setText(String.valueOf(employee.getId()));
            txtfieldName.setText(employee.getName());
            txtfieldMajor.setText(employee.getMajor());
            txtfieldGrade.setText(String.valueOf(employee.getGrade()));
        }
    }

}
