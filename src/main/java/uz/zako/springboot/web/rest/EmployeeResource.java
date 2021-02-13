package uz.zako.springboot.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.zako.springboot.domain.Employee;
import uz.zako.springboot.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeResource {
    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/saveEmployee")
    public ResponseEntity save(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @PutMapping("/saveEmployee")
    public ResponseEntity update(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeService.save(employee));
    }

    @GetMapping("/findAll")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/findByFirstName/{name}")
    public ResponseEntity findByFirstName(@PathVariable String name){
        return ResponseEntity.ok(employeeService.findByQueryNative(name));
    }

    @GetMapping("/findByLike/{name}")
    public ResponseEntity findByFirstNameStartingWith(@PathVariable String name){
        return ResponseEntity.ok(employeeService.findByFirstNameLikeQuery(name));
    }
    @GetMapping("/findByLike")
    public ResponseEntity findByFirstNameEndingWith(@RequestParam String name){
        return ResponseEntity.ok(employeeService.findByFirstNameEndingWith(name));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.ok("Ma'lumot o'chirildi!");
    }
}
