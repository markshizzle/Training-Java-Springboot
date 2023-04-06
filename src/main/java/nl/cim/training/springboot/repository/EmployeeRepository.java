package nl.cim.training.springboot.repository;

import nl.cim.training.springboot.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByEmail(String e);

    Optional<Employee> findById(Long Id);

    List<Employee> findByFuncGroupGreaterThan(Double x);

    @Query("SELECT " +
    "e.funcGroup AS funcGroup, " +
    "floor(e.salary/1000)*1000 AS salaryRange, " +
    "count(*) AS cnt " +
    "FROM Employee e " +
    "Group by e.funcGroup, floor(e.salary/1000) " +
    "ORDER BY e.funcGroup, floor(e.salary/1000)")
    public List<Map<String, Object>> getFunctionGroupVsSalarySlabs();

}


