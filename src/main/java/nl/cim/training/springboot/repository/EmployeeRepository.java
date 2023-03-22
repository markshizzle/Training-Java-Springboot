package nl.cim.training.springboot.repository;

import nl.cim.training.springboot.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByEmail(String e);

    Optional<Employee> findById(Long Id);

    List<Employee> findByFuncGroupGreaterThan(Double x);
}
