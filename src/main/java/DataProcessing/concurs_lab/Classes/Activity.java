/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.concurs_lab.Classes;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 *
 * @author Yurii
 */
@EnableAutoConfiguration
@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name = "N/A";
    @Column(name = "date")
    private String date = "N/A"; // переписать со строки в обьект времени?
    @Column(name = "score")
    private String score = "N/A";
    @Column(name = "type")
    private String type = "Default";
    
    // Subject
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject ofSubject = new Subject();
    // Student
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student = new Student();
    
    public Activity() {
        this.name = this.getName();
    }
    
    public Activity(Subject ofSubject,Student student) {
        
        this.name = this.getName();
        this.ofSubject=ofSubject;
        this.student=student;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!this.date.equals("N/A")){
            this.name = this.type+" - "+this.date; 
        }else{
            Date currentDate = new Date();
            this.name = this.type+" - "+currentDate;
        }
        
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the ofSubject
     */
    public Subject getOfSubject() {
        return ofSubject;
    }

    /**
     * @param ofSubject the ofSubject to set
     */
    public void setOfSubject(Subject ofSubject) {
        this.ofSubject = ofSubject;
    }

    /**
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }
    
    
    
}
