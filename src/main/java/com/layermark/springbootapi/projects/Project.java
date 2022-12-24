package com.layermark.springbootapi.projects;

import com.layermark.springbootapi.student.Student;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table

public class Project {
    @Id
    @SequenceGenerator(name = "project_sequence",
            sequenceName = "project_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "project_sequence"
    )
    private Long pid;
    private String pname;
    private String instructorName;
    private String courseName;
    @OneToMany(targetEntity = Student.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "st_pid", referencedColumnName = "pid")
    private List<Student> students;

    public Project(Long pid, String pname, String instructorName, String courseName, List<Student> students) {
        this.pid = pid;
        this.pname = pname;
        this.instructorName = instructorName;
        this.courseName = courseName;
        this.students = students;
    }

    public Project(String pname, String instructorName, String courseName, List<Student> students) {
        this.pname = pname;
        this.instructorName = instructorName;
        this.courseName = courseName;
        this.students = students;
    }

    public Project() {

    }


    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    public void addStudents(Student students) {
        this.students.add(students);
    }

    public void connectStudent(Student students){
        this.students.add(students);
    }
}
