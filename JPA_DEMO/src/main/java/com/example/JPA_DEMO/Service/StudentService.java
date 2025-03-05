package com.example.JPA_DEMO.Service;

import com.example.JPA_DEMO.Models.Student;
import com.example.JPA_DEMO.Repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired StudentRepository studentRepository;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);



    public List<Student> getAllStudents() {
        logger.info("Fetching all student details...");
        List<Student> students = studentRepository.findAll();
        logger.info("Retrieved {} students", students.size());
        return students;
    }


    public Optional<Student> getStudentById(String rollNo) {
        logger.info("Fetching student with roll number: {}", rollNo);
        Optional<Student> student = studentRepository.findById(rollNo);
        if (student.isPresent()) {
            logger.info("Student found: {}", student.get());
        } else {
            logger.warn("No student found with roll number: {}", rollNo);
        }
        return student;
    }


    public void addStudent(Student student) {
        logger.info("Adding new student: {}", student);
        studentRepository.save(student);
        logger.info("Student added successfully.");
    }

    public String updateStudent(Student student) {
        logger.info("Updating student with roll number: {}", student.getRollNo());
        if (studentRepository.existsById(student.getRollNo())) {
            studentRepository.save(student);
            logger.info("Student updated successfully.");
            return "Student updated successfully.";
        } else {
            logger.error("Update failed! Student with roll number {} does not exist.", student.getRollNo());
            return "Error: Student with roll number " + student.getRollNo() + " does not exist.";
        }
    }


    public void deleteStudent(String rollNo) {
        logger.info("Deleting student with roll number: {}", rollNo);
        if (studentRepository.existsById(rollNo)) {
            studentRepository.deleteById(rollNo);
            logger.info("Student deleted successfully.");
        } else {
            logger.warn("Deletion failed! No student found with roll number: {}", rollNo);
        }
    }


    public void clearStudents() {
        logger.info("Deleting all student records...");
        studentRepository.deleteAll();
        logger.info("All student records deleted.");
    }
}
