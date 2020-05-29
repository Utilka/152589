/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing.concurs_lab.Repositories;

import DataProcessing.concurs_lab.Classes.Subject;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Yurii
 */
public interface SubjectRepository extends CrudRepository<Subject, Long> {
    Iterable<Subject> findByName(String name);
}
