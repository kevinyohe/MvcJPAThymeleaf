package dev.kevinyohe.springprojects.mvcjpathymeleafdemo.controller;

import dev.kevinyohe.springprojects.mvcjpathymeleafdemo.entities.Student;
import dev.kevinyohe.springprojects.mvcjpathymeleafdemo.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasProperty;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class StudentControllerTest {
    private MockMvc mvc;

    @MockBean
    private StudentService service;

    private StudentController fixture;

    @BeforeEach
    public void setUp(){
        fixture = new StudentController(service);
        this.mvc = MockMvcBuilders.standaloneSetup(fixture).build();
    }


    @Test
    public void view_ReturnsViewPageWithStudentFromDatabase_WhenStudentId() throws Exception{
        final UUID id = randomUUID();
        final Student student = new Student(id, randomUUID().toString(), randomUUID().toString());

        given(service.getStudent(id)).willReturn(Optional.of(student));
    // @formatter:off
        mvc.perform(
                get("/students/view")
                        .param("id", id.toString())
        )
                .andExpect(status().isOk())
                .andExpect(model().attribute("id", is(id)))
                .andExpect(model().attribute("student", is(notNullValue())))
                .andExpect(model().attribute("student", hasProperty("id", is(id))))
                .andExpect(model().attribute("student", hasProperty("firstName", is(student.getFirstName()))))
                .andExpect(model().attribute("student", hasProperty("lastName", is(student.getLastName()))))
                .andExpect(view().name("students/view"))
        ;
        // @formatter:on

        then(service).should().getStudent(id);
        then(service).shouldHaveNoMoreInteractions();
    }
    @Test
    public void index_RedirectsToListView_WhenStudentHomeIsAccessed() throws Exception {

        // @formatter:off
        mvc.perform(
                get("/students/")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"))
        ;
        // @formatter:on

        then(service).shouldHaveNoInteractions();
    }
}