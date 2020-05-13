
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3hw5n;

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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author AboOod_shbika99
 */
public class jpaPane2Controller implements Initializable {

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
    private EntityManagerFactory emf;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcID.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcMajor.setCellValueFactory(new PropertyValueFactory("major"));
        tcGrade.setCellValueFactory(new PropertyValueFactory("grade"));
        emf = Persistence.createEntityManagerFactory("P3HW5NPU");
        try {
            showStudents();
        } catch (SQLException ex) {
            Logger.getLogger(jpaPane2Controller.class.getName()).log(Level.SEVERE, null, ex);
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
        EntityManager em = emf.createEntityManager();
        listStudents = em.createNamedQuery("Student.findAll").getResultList();
        tableView.getItems().clear();
        tableView.getItems().addAll(listStudents);
        em.close();
    }

    private void showStudents2() throws SQLException {
        EntityManager em = emf.createEntityManager();
        tableView.getItems().clear();
        tableView.getItems().addAll(em.createNamedQuery("Student.findAll").getResultList());
        em.close();
    }


    @FXML
    private void executeUpdateHandle(ActionEvent event) {
         if (!txtArea.getText().equals("")) {
            try {
                EntityManager em = emf.createEntityManager();
                String sql = txtArea.getText();
                //SELECT s FROM Student s where s.grade < 70
                em.getTransaction().begin();
                int query = em.createQuery(sql).executeUpdate();
                em.getTransaction().commit();
                resetContorls();
                showStudents2();
                em.close();
            } catch (Exception ex) {
                Logger.getLogger(jpaPane2Controller.class.getName()).log(Level.SEVERE, null, ex);
                txtArea.setText("the SQL statement is incorrect!");
                try {
                    showStudents2();
                } catch (SQLException ex1) {
                    //Logger.getLogger(jpaPane2Controller.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }

        }
    }

    @FXML
    private void executeQueryHandle(ActionEvent event) {
        if (!txtArea.getText().equals("")) {
            try {
                EntityManager em = emf.createEntityManager();
                String sql = txtArea.getText();
                //SELECT s FROM Student s where s.grade < 70
                Query query = em.createQuery(sql);
                List<Student> stds = query.getResultList();
                tableView.getItems().clear();
                tableView.getItems().addAll(stds);
                em.close();
            } catch (Exception ex) {
                Logger.getLogger(jpaPane2Controller.class.getName()).log(Level.SEVERE, null, ex);
                txtArea.setText("the SQL statement is incorrect!");
                try {
                    showStudents2();
                } catch (SQLException ex1) {
                    //Logger.getLogger(jpaPane2Controller.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }

        }
    }

    @FXML
    private void executeRefreshHandle(ActionEvent event) throws SQLException {
        showStudents2();
    }

}
