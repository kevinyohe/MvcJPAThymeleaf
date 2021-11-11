package dev.kevinyohe.springprojects.mvcjpathymeleafdemo;

import dev.kevinyohe.springprojects.mvcjpathymeleafdemo.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import dev.kevinyohe.springprojects.mvcjpathymeleafdemo.repository.StudentRepository;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

import static java.util.UUID.randomUUID;

@SpringBootApplication
public class MvcjpathymeleafdemoApplication {

    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {

        SpringApplication.run(MvcjpathymeleafdemoApplication.class, args);
    }

@Bean
public ApplicationRunner initializeStudents(){
    System.out.println("Test");
    final Student student1 = new Student(randomUUID(), "Kevin", "Yohe");
    System.out.println("CREATED:" + student1.getId() );
    final Student student2 = new Student(randomUUID(), "Dana", "Yohe");
    final Student student3 = new Student(randomUUID(), "Ben", "Yohe");
    final Student student4 = new Student(randomUUID(), "Baby Kevin", "Yohe");
    return args -> studentRepository.saveAll(Arrays.asList(student1, student2, student3, student4));
}
}

