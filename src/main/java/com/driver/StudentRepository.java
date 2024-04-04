package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<Student>> teacherStudentMapping;


    public StudentRepository(){
        studentMap = new HashMap<String, Student>();
        teacherMap = new HashMap<String, Teacher>();
        teacherStudentMapping = new HashMap<String, List<Student>>();
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

            Teacher teacher1=findTeacher(teacher);
            teacher1.setNumberOfStudents(teacher1.getNumberOfStudents()+1);

            Student student1=findStudent(student);
            if(!teacherStudentMapping.containsKey(teacher1)){
                teacherStudentMapping.put(teacher,new ArrayList<>());
            }
            teacherStudentMapping.get(teacher1).add(student1);

        }
    }

    public Student findStudent(String student1){
        Student student=studentMap.getOrDefault(student1,null);
        return student==null?new Student():student;
    }

    public Teacher findTeacher(String teacher1){
        // your code goes here

        Teacher teacher=teacherMap.getOrDefault(teacher1,null);
        return teacher==null?new Teacher():teacher;
    }

    public List<String> findStudentsFromTeacher(String teacher){
        List<String> students=new ArrayList<>();
        Teacher teacher1=findTeacher(teacher);

        List<Student> list =teacherStudentMapping.getOrDefault(teacher1,new ArrayList<>());
        for(Student student:list){
            students.add(student.getName());
        }
        return students;
    }

    public List<String> findAllStudents(){
        // your code goes here
        return new ArrayList<>(studentMap.keySet());

    }

    public void deleteTeacher(String teacher){

        Teacher teacher1=findTeacher(teacher);
        List<Student> students=teacherStudentMapping.getOrDefault(teacher1,new ArrayList<>());
        for(Student student:students){
            studentMap.remove(student.getName());
        }
        teacherMap.remove(teacher);
        teacherStudentMapping.remove(teacher1);
    }

    public void deleteAllTeachers(){
        // your code goes here
        for(String teacher:teacherMap.keySet()){
            deleteTeacher(teacher);
        }
        teacherStudentMapping.clear();
    }

//    public void printStudentMap() {
//        System.out.println("Printing studentMap:");
//        for (String key : studentMap.keySet()) {
//            System.out.println("Key: " + key + ", Value: " + studentMap.get(key).getName()+ " ,"+studentMap.get(key).getAge()+" "+studentMap.get(key).getAverageScore());
//        }
//    }

}