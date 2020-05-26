package DataProcessing.concurs_lab;

import DataProcessing.concurs_lab.Classes.Movie;
import DataProcessing.concurs_lab.Repositories.MovieRepository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;
import org.apache.commons.csv.*;

@SpringBootApplication
public class ConcursLabApplication implements CommandLineRunner {
    
    @Autowired
    MovieRepository movieRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(ConcursLabApplication.class, args);
    }
    
    public void addMovies(String filename) throws Exception{
        BufferedReader csvReader = new BufferedReader(new FileReader(filename));
        CSVParser csvParser = new CSVParser(csvReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        for (CSVRecord csvRecord : csvParser) {
            
            Movie movie = new Movie();
            
            movie.setId(Integer.parseInt(csvRecord.get("id")));
                
            movie.setTitle(csvRecord.get("title"));
                
            movie.setYear(Integer.parseInt(csvRecord.get("year")));
                
            movie.setCountry(csvRecord.get("country"));
                
            movie.setGenre(csvRecord.get("genre"));
                
            movie.setDirector(csvRecord.get("director"));
                
            movie.setMinutes(Integer.parseInt(csvRecord.get("minutes")));
                
            movie.setPoster(csvRecord.get("poster"));
                
            movieRepository.save(movie);
        }
        csvReader.close();
    }
    
    @Override
    public void run(String... args) throws Exception {
        
        addMovies("ratedmovies_short.csv");// файлы кидать в корень проэкта
        
    }
}
