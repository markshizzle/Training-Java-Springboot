package nl.cim.training.springboot.services;
import nl.cim.training.springboot.converters.EmployeeConverter;
import nl.cim.training.springboot.dao.EmployeeDao;
import nl.cim.training.springboot.dto.EmployeeRequest;
import nl.cim.training.springboot.dto.EmployeeResponse;
import nl.cim.training.springboot.exception.EmployeeNotFoundException;
import nl.cim.training.springboot.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServices {
    @Autowired
    EmployeeDao employeeDao;

    public List<EmployeeResponse> getAllEmployees() {
        return EmployeeConverter.convert(employeeDao.getAllEmployees());
    }

    public List<EmployeeResponse> getEmployeeByEmail(String e) {
        return EmployeeConverter.convert(employeeDao.findByEmail(e));
    }

    public List<EmployeeResponse> getFuncGroupGreaterThan(Double x) {
        return EmployeeConverter.convert(employeeDao.getFuncGroupGreaterThan(x));
    }

    public EmployeeResponse getEmployee(Long id) throws EmployeeNotFoundException {
        return EmployeeConverter.convert(employeeDao.getEmployee(id));
    }

    public EmployeeResponse saveEmployee(EmployeeRequest eReq) {
        Employee e = EmployeeConverter.convert(eReq);
        return EmployeeConverter.convert(employeeDao.addEmployee(e));
    }

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest eReq) throws EmployeeNotFoundException {
        Employee e = EmployeeConverter.convert(eReq);
        employeeDao.updateEmployee(id, e);
        return getEmployee(id);
    }
}
