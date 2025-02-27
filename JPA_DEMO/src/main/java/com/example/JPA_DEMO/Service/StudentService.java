package com.example.JPA_DEMO.Service;



import com.example.JPA_DEMO.Models.Student;
import com.example.JPA_DEMO.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int rollNo) {
        return studentRepository.findById(rollNo);
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public String updateStudent(Student student) {
        if (studentRepository.existsById(student.getRollNo())) {
            studentRepository.save(student);
            return "Student updated successfully.";
        } else {
            return "Error: Student with roll number " + student.getRollNo() + " does not exist.";
        }
    }


    public void deleteStudent(int rollNo) {
        studentRepository.deleteById(rollNo);
    }

    public void clearStudents() {
        studentRepository.deleteAll();
    }
}
