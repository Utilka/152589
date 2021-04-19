/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.concurs_lab.Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 *
 * @author Yurii
 */
@EnableAutoConfiguration
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name = "N/A";
    
    // Grouppp    
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Grouppp inGroup ;
    // Students from group list
    // Activities List    
    @OneToMany(
            mappedBy = "ofSubject",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Set<Activity> activities = new HashSet<Activity>();

    
    public Subject() {
        this.name = this.getName();
    }
    
    public Subject(Grouppp inGroup,Set<Activity> activities) {
        
        this.name = this.getName();
        this.inGroup=inGroup;
        this.activities=activities;
    }
    
    public Subject(Set<Activity> activities) {
        
        this.name = this.getName();
        this.activities=activities;
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
        if(this.inGroup == null){
            this.name = name;
        }else{
            this.name = name +" - "+ this.inGroup.getName();
        }
    }

    /**
     * @return the inGroup
     */
    public Grouppp getInGroup() {
        return inGroup;
    }

    /**
     * @param inGroup the inGroup to set
     */
    public void setInGroup(Grouppp inGroup) {
        this.inGroup = inGroup;
    }

    /**
     * @return the activities
     */
    public Set<Activity> getActivities() {
        return activities;
    }
    
    public List getSortedActivitiesList() {
        List<Activity> sortedList = new ArrayList(activities);
        //Collections.sort(sortedList, Activity.ActivityDateComparator);
        //List<String> uniqueStates = sortedList.stream().map(Activity::getName).distinct().collect(toList());
        List<Activity> uniqueList = new ArrayList();
        boolean isIn = false;
        if (sortedList.isEmpty()){
            return null;
        }
        for (Activity activity : sortedList) {
            if(!uniqueList.isEmpty()){
                for(Activity activity2 : uniqueList){
                    if(activity.getName().equals(activity2.getName())){
                        isIn = true;
                    }
                }
                if(isIn){
                    isIn = false;
                }else{
                    uniqueList.add(activity);
                }
            }else{
                uniqueList.add(activity);
            }
        }
        Collections.sort(uniqueList, Activity.ActivityDateComparator);
        return uniqueList;
    }
    
    public String getStudentScore(Student student, String activity_name) {
        String score = "N/F";
        
        for (Activity activity : activities) {
            if(activity.getName().equals(activity_name)){
                if(activity.getStudent().equals(student)){
                    score = activity.getScore();
                }
            }
        }
        
        return score;
    }
    public void setStudentScore(Student student, String activity_name, String score) {
        
        for (Activity activity : activities) {
            if(activity.getName().equals(activity_name)){
                if(activity.getStudent().equals(student)){
                    activity.setScore(score);
                }
            }
        }
    }
    
    /**
     * @param activities the activities to set
     */
    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
    
    public static Comparator<Subject> SubjectNameComparator = new Comparator<Subject>() {
        public int compare(Subject subject1, Subject subject2) {
          String subjectName1 = subject1.getName().toUpperCase();
          String subjectName2 = subject2.getName().toUpperCase();
          return subjectName1.compareTo(subjectName2);
         //descending order
         //return studentName2.compareTo(studentName1);
        }
    };
    
    
}
