/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.concurs_lab.Classes;

import java.util.HashSet;
import java.util.Set;
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
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 *
 * @author Yurii
 */
@EnableAutoConfiguration
@Entity
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name = "N/A";
//    @Column(name = "group_name")
//    private String group = "N/A";
    @Column(name = "phone")
    private String phone = "N/A";
    @Column(name = "photo")
    private String photo = "N/A";
    @Column(name = "year")
    private int year = 0;
//    @Column(name = "minutes")
//    private int minutes = 0;
//    @Column(name = "poster")
//    private String poster = "N/A";
    
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Grouppp inGroup ;
    
    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private Set<Activity> activities = new HashSet<Activity>();
    
    public Student() {}
    
    public Student(Grouppp inGroup,Set<Activity> activities) {
        
        this.inGroup=inGroup;
        this.activities=activities;
    }
    public Student(Set<Activity> activities) {
        
        this.inGroup=inGroup;
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
        this.name = name;
    }

//    public String getGroup() {
//        return group;
//    }
//
//    public void setGroup(String group) {
//        this.group = group;
//    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    /**
     * @param activities the activities to set
     */
    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    
    
}
