package com.example.API_GET_PUT.Service;

import com.example.API_GET_PUT.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Student_Service {

    List<Student> student_list= new ArrayList<>(
            Arrays.asList(
                    new Student(101,"ashish",22,"cloud"),
                    new Student(102,"ana",25,"blockchain")
            )
    );
    public List<Student> Student_Details(){
        return student_list;
    }


    public Student Std_by_rno(int rollno) {
        int index=-1;
        for (int i =0;i<student_list.size();i++){
            if (student_list.get(i).getRno()==rollno){
                index=i;
            }
        }
        return student_list.get(index);
    }

    public void post_student(Student student) {
        student_list.add(student);
    }

    public String update_student(Student student) {
        int index=-1;
        Boolean found=false;
        for (int i=0;i<student_list.size();i++){
            if (student_list.get(i).getRno()==student.getRno()){
                index=i;
                found=true;
            }

        }
        if (found) {
            student_list.set(index, student);
            return "updated successfully ";
        }else return "no such thing found" ;

    }

    public String delete_student(int rollno) {
        int index=-1;
        Boolean found=false;
        for (int i=0;i<student_list.size();i++){
            if(student_list.get(i).getRno()==rollno){
                index=i;
                found=true;
            }
        }
        if(found){
            student_list.remove(index);
            return  "successfully deleted";
        } else return  "student not found";

    }
}
