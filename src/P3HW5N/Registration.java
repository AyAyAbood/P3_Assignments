/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3hw5n;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author AboOod_shbika99
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Registration.findAll", query = "select r from Registration r"),
    //@NamedQuery(name = "Registration.insertAll", query = "insert into registration values(?1,?2,?3)")
})
public class Registration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Student student;
    private int course_id;
    private int semester;
    
    public Registration() {
    }

    public Registration(Student student, int course_id, int semester) {
        this.student = student;
        this.course_id = course_id;
        this.semester = semester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Integer getStudent_id(){
        return student.getId();
    }
}
