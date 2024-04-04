package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("students")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;
    @PostMapping("/add-student")
    public String addStudent(@RequestBody Student student){

        studentService.addStudent(student);
        return "New student added successfully";
    }

    @PostMapping("/add-teacher")
    public String addTeacher(@RequestBody Teacher teacher){

        studentService.addTeacher(teacher);
        return "New teacher added successfully";
    }

    @PutMapping("/add-student-teacher-pair")
    public String addStudentTeacherPair(@RequestParam String student, @RequestParam String teacher){

        studentService.createStudentTeacherPair(student,teacher);
        return "New student-teacher pair added successfully";
    }

    @GetMapping("/get-student-by-name/{name}")
    public Student getStudentByName(@PathVariable String name){
        Student student  = studentService.findStudent(name); // Assign student by calling service layer method

        return student;
    }

    @GetMapping("/get-teacher-by-name/{name}")
    public Teacher getTeacherByName(@PathVariable String name){
        Teacher teacher=studentService.findTeacher(name);// Assign student by calling service layer method

        return teacher;
    }

    @GetMapping("/get-students-by-teacher-name/{teacher}")
    public List<String> getStudentsByTeacherName(@PathVariable String teacher){
        List<String> students = studentService.findStudentsFromTeacher(teacher); // Assign list of student by calling service layer method

        return students;
    }

    @GetMapping("/get-all-students")
    public List<String> getAllStudents(){
        List<String> students = studentService.findAllStudents(); // Assign list of student by calling service layer method

        return students;
    }

    @DeleteMapping("/delete-teacher-by-name")
    public String deleteTeacherByName(@RequestParam String teacher){

        studentService.deleteTeacher(teacher);
        return teacher + " removed successfully";
    }
    @DeleteMapping("/delete-all-teachers")
    public String deleteAllTeachers(){

        studentService.deleteAllTeachers();
        return "All teachers deleted successfully";
    }

//    @GetMapping("/print-student-map")
//    public String printStudentMap() {
//        studentRepository.printStudentMap(); // Print the studentMap
//        return "Student map printed to console";
//    }



}

