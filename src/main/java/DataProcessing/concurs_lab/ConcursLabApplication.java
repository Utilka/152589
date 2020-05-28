package DataProcessing.concurs_lab;

import DataProcessing.concurs_lab.Classes.Activity;
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

@SpringBootApplication
public class ConcursLabApplication implements CommandLineRunner {
    
    @Autowired
    StudentRepository studentRepository;
    SubjectRepository subjectRepository;
    ActivityRepository activityRepository;
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
            student.setGroup(csvRecord.get("group"));
            student.setPhone(csvRecord.get("phone"));
            student.setPhoto(csvRecord.get("photo"));
            student.setYear(Integer.parseInt(csvRecord.get("year")));
                
            studentRepository.save(student);
        }
        csvReader.close();
    }
    public void addSubjects(String filename) throws Exception{
        BufferedReader csvReader = new BufferedReader(new FileReader(filename));
        CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        for (CSVRecord csvRecord : csvParser) {
            
            Subject subject = new Subject();
                
            subject.setName(csvRecord.get("name")); 
//            subject.setGroup(csvRecord.get("group"));
//            subject.setPhone(csvRecord.get("phone"));
//            subject.setPhoto(csvRecord.get("photo"));
//            subject.setYear(Integer.parseInt(csvRecord.get("year")));
                
            subjectRepository.save(subject);
        }
        csvReader.close();
    }
    public void addActivities(String filename) throws Exception{
        BufferedReader csvReader = new BufferedReader(new FileReader(filename));
        CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        for (CSVRecord csvRecord : csvParser) {
            
            Activity activity = new Activity();
                
//            activity.setName(csvRecord.get("name")); 
//            activity.setGroup(csvRecord.get("group"));
//            activity.setPhone(csvRecord.get("phone"));
//            activity.setPhoto(csvRecord.get("photo"));
//            activity.setYear(Integer.parseInt(csvRecord.get("year")));
                
            activityRepository.save(activity);
        }
        csvReader.close();
    }
    
    @Override
    public void run(String... args) throws Exception {
        
        addStudents("students.csv");// файлы кидать в корень проэкта
        addSubjects("subjects.csv");
        addActivities("activities.csv");
        
        
        // for student in group create activity
    }
}
