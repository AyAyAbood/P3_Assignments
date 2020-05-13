/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3hw5n;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author AboOod_shbika99
 */
public class jpaPane1Controller implements Initializable {

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

    private EntityManagerFactory emf;
    private Student student;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(jpaPane1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        tcID.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcMajor.setCellValueFactory(new PropertyValueFactory("major"));
        tcGrade.setCellValueFactory(new PropertyValueFactory("grade"));
        //tcCName.setCellValueFactory(new PropertyValueFactory("name"));
        //tcRoom.setCellValueFactory(new PropertyValueFactory("room"));
        tcSID.setCellValueFactory(new PropertyValueFactory("student_id"));
        tcCID.setCellValueFactory(new PropertyValueFactory("course_id"));
        tcSemester.setCellValueFactory(new PropertyValueFactory("semester"));
        tableView.getSelectionModel().selectedItemProperty().addListener(event -> showSelectedStudent());
        emf = Persistence.createEntityManagerFactory("P3HW5NPU");
        try {
            showStudents();
            showRegistrations();
        } catch (SQLException ex) {
            Logger.getLogger(jpaPane1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtfieldID.setEditable(false);
    }

    @FXML
    private void buttonAddHandle(ActionEvent event) throws SQLException {
        if (!txtfieldName.getText().equals("") && !txtfieldMajor.getText().equals("") && !txtfieldGrade.getText().equals("")) {
            try {
                String name = txtfieldName.getText();
                String major = txtfieldMajor.getText();
                Double grade = Double.parseDouble(txtfieldGrade.getText());
                Student std = new Student(name, major, grade);
                resetContorls();
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                em.persist(std);
                em.getTransaction().commit();
                em.close();
                showStudents();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("fill the input correctly!");
                alert.showAndWait();
            }finally{
                showStudents();
            }
        }
    }

    @FXML
    private void buttonEditHandle(ActionEvent event) throws SQLException {
        if (!txtfieldID.getText().equals("") && !txtfieldName.getText().equals("") && !txtfieldMajor.getText().equals("") && !txtfieldGrade.getText().equals("")) {
            Integer id = Integer.parseInt(txtfieldID.getText());
            String name = txtfieldName.getText();
            String major = txtfieldMajor.getText();
            Double grade = Double.parseDouble(txtfieldGrade.getText());
            Student std = new Student(name, major, grade);
            resetContorls();
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.createNamedQuery("Student.updateByID").setParameter(1, name).setParameter(2, major).setParameter(3, grade).setParameter(4, id).executeUpdate();
            em.getTransaction().commit();
            em.close();
            showStudents();
        }
    }

    @FXML
    private void buttonDeleteHandle(ActionEvent event) throws SQLException {
        Student student = tableView.getSelectionModel().getSelectedItem();
        if (student != null) {
            resetContorls();
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.createNamedQuery("Student.deleteByID").setParameter("id", student.getId()).executeUpdate();
            em.getTransaction().commit();
            em.close();
            showStudents();
        }
    }

    @FXML
    private void buttonAddCourseHandle(ActionEvent event) throws SQLException {
        if (!txtfieldStudentID.getText().equals("") && !txtfieldCourseID.getText().equals("") && !txtfieldSemester.getText().equals("")) {
            try {
                Integer StudentID = Integer.parseInt(txtfieldStudentID.getText());
                Integer CourseID = Integer.parseInt(txtfieldCourseID.getText());
                Integer Semester = Integer.parseInt(txtfieldSemester.getText());
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                student = (Student) em.createNamedQuery("Student.findById").setParameter("id", StudentID).getSingleResult();
                em.getTransaction().commit();
                em.close();
                EntityManager em2 = emf.createEntityManager();
                Registration reg = new Registration(student, CourseID, Semester);
                em2.getTransaction().begin();
                em2.persist(reg);
                em2.getTransaction().commit();
                em2.close();
                resetContorlsReg();
                showRegistrations();
            } catch (NoResultException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("Student does not exist!");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText("insert only integers!");
                alert.showAndWait();
            }finally{
            showRegistrations();
            }
        }
    }

    private void showStudents() throws SQLException {
        EntityManager em = emf.createEntityManager();
        List<Student> stds = em.createNamedQuery("Student.findAll").getResultList();
        tableView.getItems().clear();
        tableView.getItems().setAll(stds);
        em.close();
    }

    private void showRegistrations() throws SQLException {
        EntityManager em = emf.createEntityManager();
        List<Registration> regs = em.createNamedQuery("Registration.findAll").getResultList();
        tableView2.getItems().clear();
        tableView2.getItems().setAll(regs);
        em.close();
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
}
