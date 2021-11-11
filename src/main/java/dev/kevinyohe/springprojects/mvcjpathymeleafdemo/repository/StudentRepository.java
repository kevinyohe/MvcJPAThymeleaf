package dev.kevinyohe.springprojects.mvcjpathymeleafdemo.repository;

import dev.kevinyohe.springprojects.mvcjpathymeleafdemo.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {


}
