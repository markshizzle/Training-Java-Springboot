package nl.cim.training.springboot.services;
import nl.cim.training.springboot.converters.EmployeeConverter;
import nl.cim.training.springboot.dao.EmployeeDao;
import nl.cim.training.springboot.dto.EmployeeResponse;
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


}