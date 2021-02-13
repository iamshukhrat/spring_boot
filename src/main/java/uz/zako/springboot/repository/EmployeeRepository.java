package uz.zako.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.zako.springboot.domain.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public List<Employee> findByFirstName(String firstName);

    @Query("select e from Employee e where e.firstName = :name")
    public List<Employee> findByQuery(@Param("name") String name);

    @Query(value = "select * from employee e where e.first_name = :name", nativeQuery = true)
    public List<Employee> findByQueryNative(@Param("name") String name);

    public List<Employee> findByFirstNameStartingWith(String name);
    public List<Employee> findByFirstNameEndingWith(String name);

    @Query("select e from Employee e where lower( e.firstName) like lower( concat( '%', :name, '%'))")
    public List<Employee> findByFirstNameLikeQuery(@Param("name") String name);

    public void deleteById(Long id);
}
