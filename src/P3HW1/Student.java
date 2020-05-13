package p3hw1;

import java.io.Serializable;

public abstract class Student implements Comparable<Student>,Serializable {
    public int id;
    public String name;
    public String major;
    public double grade;

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

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
    
    public int compareTo(Student ss) {
	
		int Quantity = (int)ss.getGrade(); 
		
		//ascending order
		return Quantity - (int)this.grade;
		
		//descending order
		//return compareQuantity - this.quantity;
		
	}	

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", major=" + major + ", grade=" + grade + '}';
    }
    
}
