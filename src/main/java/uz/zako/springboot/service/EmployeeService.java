package uz.zako.springboot.service;

import org.springframework.stereotype.Service;
import uz.zako.springboot.domain.Employee;
import uz.zako.springboot.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }
    public List<Employee> findByFirstName(String firstName){
        return employeeRepository.findByFirstName(firstName);
    }
    public List<Employee> findByQuery(String name){
        return employeeRepository.findByQuery(name);
    }
    public List<Employee> findByQueryNative(String name){
        return employeeRepository.findByQueryNative(name);
    }
    public List<Employee> findByFirstNameStartingWith(String name){
        return employeeRepository.findByFirstNameStartingWith(name);
    }
    public List<Employee> findByFirstNameEndingWith(String name){
        return employeeRepository.findByFirstNameEndingWith(name);
    }
    public List<Employee> findByFirstNameLikeQuery(String name){
        return employeeRepository.findByFirstNameLikeQuery(name);
    }
    public void delete(Long id){
        employeeRepository.deleteById(id);
    }
}