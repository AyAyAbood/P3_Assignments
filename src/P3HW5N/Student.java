/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p3hw5n;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author AboOod_shbika99
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query="Select s From Student s"),
    @NamedQuery(name = "Student.findById", query="Select s From Student s Where s.id = :id"),
    @NamedQuery(name = "Student.deleteByID", query = "delete from Student where id = :id"),
    @NamedQuery(name = "Student.updateByID", query = "Update Student Set name = ?1, major = ?2, grade = ?3 where id = ?4"),
    //@NamedQuery(name = "Student.insert", query = "insert into student values(?1, ?2, ?3, ?4)")
})
public class Student {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String major;
    private Double grade;
    
    public Student() {
    }

    public Student(String name, String major, Double grade) {
        this.name = name;
        this.major = major;
        this.grade = grade;
    }

    public Student(int id, String name, String major, Double grade) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.grade = grade;
    }

    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }
    
}
