package dev.kevinyohe.springprojects.mvcjpathymeleafdemo.controller;

import dev.kevinyohe.springprojects.mvcjpathymeleafdemo.entities.Student;
import dev.kevinyohe.springprojects.mvcjpathymeleafdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Optional;
import java.util.UUID;

@Controller
public class StudentController {

    static final int DEFAULT_PAGE_SIZE = 2;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @Autowired
    private final StudentService service;

    @GetMapping("/students/")
    public String index(){
        return "redirect:list";

    }
    @GetMapping("/students/list")
    public String getStudents(final Model model,
                              @RequestParam(value="page", defaultValue = "0") final int pageNumber,
                              @RequestParam(value="size", defaultValue = DEFAULT_PAGE_SIZE + "") final int pageSize){
        model.addAttribute("message", "Hello World@");
        final Page<Student> page = service.getStudents(pageNumber, pageSize);

        // add data to model
        model.addAttribute("students", page.getContent());

        // for navigation through pages
        final int currentPageNumber  = page.getNumber();
        final int previousPageNumber  = page.hasPrevious() ? currentPageNumber -1 : -1;
        final int nextPageNumber = page.hasNext() ? currentPageNumber + 1 : -1;

        model.addAttribute("currentPageNumber", currentPageNumber);
        model.addAttribute("previousPageNumber", previousPageNumber);
        model.addAttribute("nextPageNumber", nextPageNumber);
        model.addAttribute("pageSize", DEFAULT_PAGE_SIZE);
        return "students/list";
    }

    @GetMapping("/students/add")
            public String add(final Model model)
    {
        model.addAttribute("student", new Student());
        return "students/add";
    }
    @GetMapping("/students/view")
    public String view(final Model model, @RequestParam final UUID id)
    {
        final Optional<Student> student = service.getStudent(id);
        model.addAttribute("student", student.isPresent() ? student.get() : new Student());
        model.addAttribute("id", id);
        return "students/view";
    }
    @GetMapping("/students/delete")
    public String delete(final Model model, @RequestParam final UUID id)
    {
        final Optional<Student> record = service.getStudent(id);
        model.addAttribute("student", record.isPresent() ? record.get() : new Student());
        model.addAttribute("id", id);
        return "students/delete";
    }
    @GetMapping("/students/edit")
    public String edit(final Model model, @RequestParam final UUID id)
    {
        final Optional<Student> student = service.getStudent(id);
        model.addAttribute("student", student.isPresent() ? student.get() : new Student());
        model.addAttribute("id", id);
        return "students/edit";
    }
    @PostMapping("students/save")
    public String save(final Model model, @ModelAttribute final Student student, final BindingResult errors){
        service.save(student);
        return "redirect:list";
    }
    @PostMapping("students/delete")
    public String save(final Model model, @RequestParam final UUID id){
        service.delete(id);
        return "redirect:list";
    }
}
