package dev.kevinyohe.springprojects.mvcjpathymeleafdemo.service;

import dev.kevinyohe.springprojects.mvcjpathymeleafdemo.entities.Student;
import dev.kevinyohe.springprojects.mvcjpathymeleafdemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentRepository repository;


    @Autowired
    public StudentService(final StudentRepository repository){
        this.repository = repository;
    }

    public Page<Student> getStudents(final int pageNumber, final int size){
        return repository.findAll(PageRequest.of(pageNumber, size));
    }

    public Optional<Student> getStudent(final UUID id){
        return repository.findById(id);
    }

    public Student save(final Student student){
        return repository.save(student);
    }

    public void delete(final UUID id){
        repository.deleteById(id);
    }
}
