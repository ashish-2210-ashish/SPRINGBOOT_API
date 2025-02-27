package com.example.JPA_DEMO.Controller;


import com.example.JPA_DEMO.Models.Student;
import com.example.JPA_DEMO.Service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{rollNo}")
    public Optional<Student> getStudentById(@PathVariable int rollNo) {
        return studentService.getStudentById(rollNo);
    }

    @PostMapping
    public String addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return "Successfully added into the database.";
    }

    @PutMapping
    public String updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
        return "Successfully updated into the database.";
    }

    @DeleteMapping("/{rollNo}")
    public String deleteStudent(@PathVariable int rollNo) {
        studentService.deleteStudent(rollNo);
        return "Successfully deleted the record.";
    }

    @DeleteMapping("/clear")
    public String clearStudents() {
        studentService.clearStudents();
        return "Cleared the student table.";
    }
}
