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

    /**
     * @param activities the activities to set
     */
    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
    
    
    
}
