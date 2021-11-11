package dev.kevinyohe.springprojects.mvcjpathymeleafdemo.service;

import dev.kevinyohe.springprojects.mvcjpathymeleafdemo.entities.Student;
import dev.kevinyohe.springprojects.mvcjpathymeleafdemo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(SpringExtension.class)
class StudentServiceTest {

    @MockBean
    private StudentRepository repository;

    private StudentService fixture;

    @BeforeEach
    public void setup(){
        fixture = new StudentService(repository);
    }

    @Test
    void getStudents() {
    }

    @Test
    void getStudent() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    public void GetStudent_ReturnStudent_WhenStudentExist(){
        final UUID id = randomUUID();

        final Student student = new Student(randomUUID(), randomUUID().toString(), randomUUID().toString());

        final Optional<Student> expected = Optional.of(student);

        given(repository.findById(id)).willReturn(expected);

        final Optional<Student> actual = fixture.getStudent(id);

        assertThat(actual).isEqualTo(expected);
        then(repository).should().findById(id);
        then(repository).shouldHaveNoMoreInteractions();
    }
    @Test
    public void GetStudent_ReturnStudent_WhenStudentDoesNotExist(){
        final UUID id = randomUUID();

        final Optional<Student> expected = Optional.empty();

        given(repository.findById(id)).willReturn(expected);

        final Optional<Student> actual = fixture.getStudent(id);

        assertThat(actual).isEqualTo(expected);
        then(repository).should().findById(id);
        then(repository).shouldHaveNoMoreInteractions();
    }
    @Test
    public void GetStudent_ReturnStudent_WhenStudentRecordIsCreated(){
        final UUID id = randomUUID();

        final Student expected = new Student();
        expected.setFirstName(randomUUID().toString());
        expected.setLastName(randomUUID().toString());

        given(repository.save(expected)).willAnswer(invocation ->{
            final Student toSave = invocation.getArgument(0);
            toSave.setId(id);
            return toSave;
        });

        final Student actual = fixture.save(expected);

        assertThat(actual).isEqualTo(expected);

        then(repository).should().save(expected);
        then(repository).shouldHaveNoMoreInteractions();
    }
    @Test
    public void delete_DeleteStudent_WhenStudentExists(){
        final UUID id = randomUUID();

        willDoNothing().given(repository).deleteById(id);

        fixture.delete(id);

        then(repository).should().deleteById(id);
        then(repository).shouldHaveNoMoreInteractions();
    }

}
