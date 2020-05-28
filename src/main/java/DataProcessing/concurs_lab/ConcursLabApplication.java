package DataProcessing.concurs_lab;

import DataProcessing.concurs_lab.Classes.Student;
import java.io.BufferedReader;
import java.io.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.csv.*;
import DataProcessing.concurs_lab.Repositories.StudentRepository;

@SpringBootApplication
public class ConcursLabApplication implements CommandLineRunner {
    
    @Autowired
    StudentRepository studentRepository;
    
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
    
    @Override
    public void run(String... args) throws Exception {
        
        addStudents("students.csv");// файлы кидать в корень проэкта
        
    }
}
