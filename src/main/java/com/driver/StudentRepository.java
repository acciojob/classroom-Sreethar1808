package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;


    public StudentRepository(){
        studentMap = new HashMap<String, Student>();
        teacherMap = new HashMap<String, Teacher>();
        teacherStudentMapping = new HashMap<String, List<String>>();
    }


    public void saveStudent(Student student){
        // your code goes here
        studentMap.put(student.getName(),student);
    }

    public void saveTeacher(Teacher teacher){
        // your code goes here
       teacherMap.put(teacher.getName(), teacher);
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            if(teacherStudentMapping.containsKey(teacher))
            {
                teacherStudentMapping.get(teacher).add(student);
            }
            else {
                List<String> new_Student = new ArrayList<>();
                new_Student.add(student);
               teacherStudentMapping.put(teacher,new_Student);
            }
        }
    }

    public Student findStudent(String student){
        if(studentMap.containsKey(student))
        {
            return studentMap.get(student);
        }
        else {
            return null;
        }
    }

    public Teacher findTeacher(String teacher){
        // your code goes here

        if(teacherMap.containsKey(teacher))
        {
            return teacherMap.get(teacher);
        }
        else {
            return null;
        }
    }

    public List<String> findStudentsFromTeacher(String teacher){
        if(teacherStudentMapping.containsKey(teacher))
        {
            return teacherStudentMapping.get(teacher);
        }
        else {
            return new ArrayList<>();
        }
    }

    public List<String> findAllStudents(){
        // your code goes here
        if(studentMap.size()>0) {
            List<String> studentList = new ArrayList<>(studentMap.keySet());
            return studentList;
        }
        else {
            return new ArrayList<>();
        }
    }

    public void deleteTeacher(String teacher){

        if(teacherMap.containsKey(teacher))
        {
            teacherMap.remove(teacher);

            teacherStudentMapping.remove(teacher);
        }
    }

    public void deleteAllTeachers(){
        // your code goes here
        teacherMap.clear();
        teacherStudentMapping=new HashMap<>();
    }

//    public void printStudentMap() {
//        System.out.println("Printing studentMap:");
//        for (String key : studentMap.keySet()) {
//            System.out.println("Key: " + key + ", Value: " + studentMap.get(key).getName()+ " ,"+studentMap.get(key).getAge()+" "+studentMap.get(key).getAverageScore());
//        }
//    }

}