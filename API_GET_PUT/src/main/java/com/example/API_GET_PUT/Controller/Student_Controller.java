package com.example.API_GET_PUT.Controller;

import com.example.API_GET_PUT.Model.Student;
import com.example.API_GET_PUT.Service.Student_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Student_Controller {

    @Autowired
    Student_Service service;

    @GetMapping("/Students")
    public List<Student> Dispaly_Students(){
        return service.Student_Details();
    }

    @GetMapping("/Students/{rno}")
    public  Student get_student_by_rno(@PathVariable("rno") int rollno){
        return service.Std_by_rno(rollno);
    }

    @PostMapping("/Students")
    public String post_student(@RequestBody Student student){
        service.post_student(student);
        return "successfully posted";
    }

    @PutMapping("/Students")
    public String put_Student(@RequestBody Student student){
        return service.update_student(student);

    }

    @DeleteMapping("/Students/{rno}")
    public String delete_student_by_rno(@PathVariable("rno") int rollno){
        return  service.delete_student(rollno);
    }

}
