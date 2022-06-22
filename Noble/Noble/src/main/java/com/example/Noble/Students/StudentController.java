package com.example.Noble.Students;


//API RESOURCES

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    private final StudentsService studentsService;
    @Autowired
    public StudentController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping
    public List<Students> getStudents(){
        return studentsService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Students student){
        studentsService.addNewStudent(student) ;
    }

    @DeleteMapping( path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentsService.deleteStudent(studentId);
    }


    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long  studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){

        studentsService.updateStudent(studentId, name, email);

    }

}
