package uz.zako.springboot.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.zako.springboot.model.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentResource {
    @GetMapping("/students")
    public ResponseEntity HelloWorld(){
        Student student = new Student(1l,"Shuxratjon", "Yo'ldashev", 19, "TUIT");
        List<Student> students = new ArrayList<>();
        students.add(student);
        student = new Student(2l, "Yaxyoxon", "Raximov", 19, "TUIT");
        students.add(student);
        student = new Student(3l,"Javoxir", "Abdufattayev", 20, "TTYMI");
        students.add(student);
        return ResponseEntity.ok(students);
    }
    @GetMapping("/getStudent/{id}")
    public ResponseEntity getStudentWithId(@PathVariable Long id){
        Student student = new Student(id,"Shuxratjon", "Yo'ldashev", 19, "TUIT");
        return ResponseEntity.ok(student);
    }
    @PostMapping("/student")
    public ResponseEntity getStudent(@RequestBody Student student){
        return ResponseEntity.ok(student);
    }
    @PutMapping("/updateStudent")
    public ResponseEntity update(@RequestBody Student student){
        Student studentHome = new Student(1l,"Shuxratjon", "Yo'ldashev", 19, "TUIT");
        studentHome.setAge(student.getAge());
        studentHome.setFirstName(student.getFirstName());
        return ResponseEntity.ok(studentHome);
    }
    @GetMapping("/student")
    public ResponseEntity getRequestParam(@RequestParam Long id,
                                          @RequestParam String name,
                                          @RequestParam Integer age,
                                          @RequestParam String university,
                                          @RequestParam String lastName){
        Student student = new Student(id, name, lastName, age, university);
        return ResponseEntity.ok(student);
    }
}
