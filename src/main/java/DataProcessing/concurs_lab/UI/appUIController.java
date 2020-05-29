/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.concurs_lab.UI;

import DataProcessing.concurs_lab.Classes.Grouppp;
import DataProcessing.concurs_lab.Classes.Student;
import DataProcessing.concurs_lab.Classes.Subject;
import DataProcessing.concurs_lab.Repositories.ActivityRepository;
import DataProcessing.concurs_lab.Repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import DataProcessing.concurs_lab.Repositories.StudentRepository;
import DataProcessing.concurs_lab.Repositories.SubjectRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author andrii
 */
@Controller
public class appUIController {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;
    private final ActivityRepository activityRepository;
    
    @Autowired
    public appUIController(
            GroupRepository groupRepository,
            SubjectRepository subjectRepository,
            ActivityRepository activityRepository,
            StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.subjectRepository = subjectRepository;
        this.activityRepository = activityRepository;
    }
  
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showStartingPage(Model model) {

//        Iterable<Student> students = studentRepository.findAll();

        Iterable<Grouppp> groups = groupRepository.findAll();
//        List sortedGroups = new ArrayList(groups);
//        Collections.sort(sortedGroups, Grouppp.GroupNameComparator);
        model.addAttribute("groups", groups);
        
        return "index";
    }
    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public String showGroupPage(@PathVariable("id") long id, Model model) {

        Grouppp group = groupRepository.findById(id).orElse(null);
        if(group != null){
            model.addAttribute("group", group);
        }
        
        return "group";
    }
    @RequestMapping(value = "/subject/{id}", method = RequestMethod.GET)
    public String showSubjectPage(@PathVariable("id") long id, Model model) {

        Subject subject = subjectRepository.findById(id).orElse(null);
        if(subject != null){
            model.addAttribute("subject", subject);
        }
        
        return "activities";
    }
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public String showStudentPage(@PathVariable("id") long id, Model model) {

        Student student = studentRepository.findById(id).orElse(null);
        if(student != null){
            model.addAttribute("student", student);
        }
        
        return "student";
    }
    
}
