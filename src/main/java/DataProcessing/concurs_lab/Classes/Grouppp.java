/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.concurs_lab.Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 *
 * @author Yurii
 */
@EnableAutoConfiguration
@Entity
public class Grouppp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @Column(name = "name")
    private String name = "N/A";
    
    // Students list
    @OneToMany(
            mappedBy = "inGroup",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<Student> studentList = new HashSet<Student>();
    
    
    @OneToMany(
            mappedBy = "inGroup",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<Subject> subjectSet = new HashSet<Subject>();
//    @OneToOne(fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL,
//            mappedBy = "inGroup")
//
//    @JoinColumn(name = "id_of_subject", referencedColumnName = "id")
//    private Subject subject ;

    public Grouppp() {}
    
    public Grouppp(Set<Subject> subjectSet,Set<Student> studentList) {
        
        this.subjectSet=subjectSet;
        this.studentList=studentList;
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
        this.name = name;
    }

    /**
     * @return the studentList
     */
    public Set<Student> getStudentList() {
        return studentList;
    }
    
    public List getSortedStudentList() {
        List sortedList = new ArrayList(studentList);
        Collections.sort(sortedList, Student.StudentNameComparator);
        return sortedList;
    }

    /**
     * @param studentList the studentList to set
     */
    public void setStudentList(Set<Student> studentList) {
        this.studentList = studentList;
    }
    
    public void addStudent(Student student){
        this.studentList.add(student);
    }

    /**
     * @return the subjectSet
     */
    public Set<Subject> getSubjectSet() {
        return subjectSet;
    }

    /**
     * @param subjectSet the subjectSet to set
     */
    public void setSubjectSet(Set<Subject> subjectSet) {
        this.subjectSet = subjectSet;
    }
    
 
    
    
}
