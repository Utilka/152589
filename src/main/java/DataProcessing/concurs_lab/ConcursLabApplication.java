package DataProcessing.concurs_lab;

import DataProcessing.concurs_lab.Classes.Activity;
import DataProcessing.concurs_lab.Classes.Grouppp;
import DataProcessing.concurs_lab.Classes.Student;
import DataProcessing.concurs_lab.Classes.Subject;
import DataProcessing.concurs_lab.Repositories.ActivityRepository;
import DataProcessing.concurs_lab.Repositories.GroupRepository;
import java.io.BufferedReader;
import java.io.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.csv.*;
import DataProcessing.concurs_lab.Repositories.StudentRepository;
import DataProcessing.concurs_lab.Repositories.SubjectRepository;
import java.util.HashSet;
import java.util.Optional;

@SpringBootApplication
public class ConcursLabApplication implements CommandLineRunner {
    
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    ActivityRepository activityRepository;
    @Autowired
    GroupRepository groupRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(ConcursLabApplication.class, args);
    }
    
    public void addStudents(String filename) throws Exception{
        BufferedReader csvReader = new BufferedReader(new FileReader(filename));
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
    }
    public void addSubjects(String filename) throws Exception{
        BufferedReader csvReader = new BufferedReader(new FileReader(filename));
        CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        for (CSVRecord csvRecord : csvParser) {
            
            Subject subject = new Subject();
            
            String group_name = csvRecord.get("group");  
            Iterable<Grouppp> groups = groupRepository.findByName(group_name);
            if(groups.iterator().hasNext()){
                Grouppp group = groups.iterator().next();
                group.setSubject(subject);
                subject.setInGroup(group);
                subject.setName(csvRecord.get("name")); 
                groupRepository.save(group);
            }else{
                Grouppp group = new Grouppp();
                group.setSubject(subject);
                group.setName(group_name);
                subject.setInGroup(group);
                subject.setName(csvRecord.get("name")); 
                groupRepository.save(group);
                subjectRepository.save(subject);
            }
        }
        csvReader.close();
    }
    public void addActivities(String filename) throws Exception{
        BufferedReader csvReader = new BufferedReader(new FileReader(filename));
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
    }
    
    @Override
    public void run(String... args) throws Exception {
        
        addStudents("students.csv");// файлы кидать в корень проэкта
        addSubjects("subjects.csv");
        addActivities("activities.csv");
        
//        Student student = studentRepository.findById(1l).orElse(null);
//        System.out.println(student.getActivities().size());
        
        // for student in group create activity
        
        System.out.println("finished data init");
    }
    
}
