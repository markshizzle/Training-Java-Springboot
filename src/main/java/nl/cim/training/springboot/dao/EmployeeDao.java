package nl.cim.training.springboot.dao;

import nl.cim.training.springboot.exception.EmployeeNotFoundException;
import nl.cim.training.springboot.models.Employee;
import nl.cim.training.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Employee> findByEmail(String e) {
        // Add exception
        return employeeRepository.findByEmail(e);
    }

    public List<Employee> getFuncGroupGreaterThan(Double x) {
        return employeeRepository.findByFuncGroupGreaterThan(x);
    }

    public Employee getEmployee(Long id) throws EmployeeNotFoundException {
        Optional<Employee> emp = employeeRepository.findById(id);

        if(!emp.isPresent())
            throw new EmployeeNotFoundException("Employee Not Found: " + id);

        return emp.get();
    }
    private static List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(Employee emp) {
        return employeeRepository.save(emp);
    }

    public Employee updateEmployee(Long id, Employee updateEmpReq) {
        updateEmpReq.setId(id);
        return employeeRepository.save(updateEmpReq);
    }
}