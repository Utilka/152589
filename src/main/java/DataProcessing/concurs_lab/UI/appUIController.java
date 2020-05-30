/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.concurs_lab.UI;

import DataProcessing.concurs_lab.Classes.Activity;
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

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
    @RequestMapping(value = "/uploadStudents", method = RequestMethod.GET)
    public String UploadStudents(String message, Model model) {
        if(message!=null){
            model.addAttribute("message", message);
        }
        return "upload_students";
    }
    @RequestMapping(value = "/uploadStudents", method = RequestMethod.POST)
    public String submitStudents(@RequestParam("file") MultipartFile file, Model model)throws Exception {
        model.addAttribute("file", file);
        if(file.getOriginalFilename().endsWith(".csv")){
            File File = new File(file.getOriginalFilename());
            BufferedReader csvReader = new BufferedReader(new FileReader(File));
            CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            for (CSVRecord csvRecord : csvParser) {

                Student student = new Student();

                student.setName(csvRecord.get("name"));
                student.setPhone(csvRecord.get("phone"));
                student.setPhoto(csvRecord.get("photo"));
                student.setYear(Integer.parseInt(csvRecord.get("year")));

                String group_name = csvRecord.get("group");
                Iterable<Grouppp> groups = groupRepository.findByName(group_name);
                if(groups.iterator().hasNext()){
                    Grouppp group = groups.iterator().next();
                    group.addStudent(student);
                    student.setInGroup(group);
                    groupRepository.save(group);
                }else{
                    Grouppp group = new Grouppp();
                    group.addStudent(student);
                    group.setName(group_name);
                    student.setInGroup(group);
                    groupRepository.save(group);
                    studentRepository.save(student);
                }
            }
            csvReader.close();
            return "redirect:/uploadStudents?message='OK'";
        }else{
            return "redirect:/uploadStudents?message='File is not csv file'";
        }
    }
    
    @RequestMapping(value = "/uploadActivities", method = RequestMethod.GET)
    public String UploadActivities(String message, Model model) {
        if(message!=null){
            model.addAttribute("message", message);
        }
        return "upload_activities";
    }
    
    @RequestMapping(value = "/uploadActivities", method = RequestMethod.POST)
    public String submitActivities(@RequestParam("file") MultipartFile file, Model model)throws Exception {
        model.addAttribute("file", file);
        if(file.getOriginalFilename().endsWith(".csv")){
            File File = new File(file.getOriginalFilename());
            BufferedReader csvReader = new BufferedReader(new FileReader(File));
            CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            for (CSVRecord csvRecord : csvParser) {

                Activity activity = new Activity();

                activity.setDate(csvRecord.get("date"));
                activity.setType(csvRecord.get("type"));
                activity.setScore(csvRecord.get("score"));
                String student_name = csvRecord.get("student");
                String subject_name = csvRecord.get("subject");
                activity.setName();

                Iterable<Student> students = studentRepository.findByName(student_name);
                if(students.iterator().hasNext()){
                    Student student = students.iterator().next();
                    activity.setStudent(student);
                }else{
                    System.out.println("No student " + student_name);
                    continue;
                }

                Iterable<Subject> subjects = subjectRepository.findByName(subject_name);
                if(subjects.iterator().hasNext()){
                    Subject subject = subjects.iterator().next();
                    activity.setOfSubject(subject);
                    activityRepository.save(activity);
                }else{
                    System.out.println("No subject " + subject_name);
                }
            }
            csvReader.close();
            return "redirect:/uploadActivities?message='OK'";
        }else{
            return "redirect:/uploadv?message='File is not csv file'";
        }
    }
    
    @RequestMapping(value = "/uploadSubjects", method = RequestMethod.GET)
    public String UploadSubjects(String message, Model model) {
        if(message!=null){
            model.addAttribute("message", message);
        }
        return "upload_subjects";
    }
    
    @RequestMapping(value = "/uploadSubjects", method = RequestMethod.POST)
    public String submitSubjects(@RequestParam("file") MultipartFile file, Model model)throws Exception {
        model.addAttribute("file", file);
        if(file.getOriginalFilename().endsWith(".csv")){
            File File = new File(file.getOriginalFilename());
            BufferedReader csvReader = new BufferedReader(new FileReader(File));
            CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            for (CSVRecord csvRecord : csvParser) {

                Subject subject = new Subject();

                String group_name = csvRecord.get("group");  
                Iterable<Grouppp> groups = groupRepository.findByName(group_name);
                if(groups.iterator().hasNext()){
                    Grouppp group = groups.iterator().next();
                    group.addSubject(subject);
                    subject.setInGroup(group);
                    subject.setName(csvRecord.get("name")); 
                    groupRepository.save(group);
                }else{
                    Grouppp group = new Grouppp();
                    group.addSubject(subject);
                    group.setName(group_name);
                    subject.setInGroup(group);
                    subject.setName(csvRecord.get("name")); 
                    groupRepository.save(group);
                    subjectRepository.save(subject);
                }
            }
            csvReader.close();
            return "redirect:/uploadSubjects?message='OK'";
        }else{
            return "redirect:/uploadSubjects?message='File is not csv file'";
        }
    }
    
    @RequestMapping(value = "/changeScore", method = RequestMethod.POST)
    public String POSTShopPage(
            @RequestParam("new_score") String new_score,
            @RequestParam("student_id") String student_id,
            @RequestParam("activity_name") String activity_name,
            @RequestParam("subject_id") String subject_id,
            Model model) {
        
        Student student = studentRepository.findById(Long.parseLong(student_id)).orElse(null);
        Subject subject = subjectRepository.findById(Long.parseLong(subject_id)).orElse(null);
        
        subject.setStudentScore(student, activity_name, new_score);
        subjectRepository.save(subject);
        
        return "redirect:/subject/"+subject_id;
    }
}
