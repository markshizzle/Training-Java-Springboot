package nl.cim.training.springboot.dao;

import nl.cim.training.springboot.exception.EmployeeNotFoundException;
import nl.cim.training.springboot.models.Employee;
import nl.cim.training.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDao {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id) throws EmployeeNotFoundException {
        Optional<Employee> emp = employeeRepository.findById(id);

        if(!emp.isPresent())
            throw new EmployeeNotFoundException("Employee Not Found: " + id);

        return emp.get();
    }

    private static List<Employee> employees = new ArrayList<>();
}