package com.example.Noble.Students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentsService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentsService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Students> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Students student) {

       Optional<Students> studentsOptional = studentRepository.findStudentsByEmail(student.getEmail());

       if (studentsOptional.isPresent()){
           throw new IllegalStateException("email already taken");
       }
       studentRepository.save(student);

        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {

        boolean exists = studentRepository.existsById(studentId);

        if (!exists){
            throw new IllegalStateException("Student with id " + studentId + " does not exist");
        }

        studentRepository.deleteById(studentId);
    }



    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email) {
        Students students = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException(
                "Student with id " + studentId + " does not exist"));

        if (name != null && name.length()>0 && !Objects.equals(students.getName(), name)){
            students.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(students.getEmail(), email)){
            Optional<Students> optionalStudents = studentRepository.findStudentsByEmail(email);
            if (optionalStudents.isPresent()){
                throw new IllegalStateException("email already exist");
            }
            students.setEmail(email);
        }
    }


}
