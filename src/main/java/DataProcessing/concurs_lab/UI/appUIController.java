/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.concurs_lab.UI;

import DataProcessing.concurs_lab.Classes.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import DataProcessing.concurs_lab.Repositories.StudentRepository;

/**
 *
 * @author andrii
 */
@Controller
public class appUIController {

    private final StudentRepository studentRepository;
    
    @Autowired
    public appUIController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
  
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showStartingPage(Model model) {
        
        
        
//        Iterable<Student> movies = movieRepository.findAll();
//        model.addAttribute("movies", movies);
        
//        System.out.println(movies.iterator().next().getPoster());
        
        return "index";
    }
    
}
