/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.concurs_lab.UI;

import DataProcessing.concurs_lab.Classes.Student;
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
        
        
        
//        Iterable<Student> movies = movieRepository.findAll();
//        model.addAttribute("movies", movies);
        
//        System.out.println(movies.iterator().next().getPoster());
        
        return "index";
    }
    
}
