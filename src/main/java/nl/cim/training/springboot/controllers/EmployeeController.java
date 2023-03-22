package nl.cim.training.springboot.controllers;

import nl.cim.training.springboot.dto.EmployeeResponse;
import nl.cim.training.springboot.services.EmployeeServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = EmployeeController.employeeControllerPath)
public class EmployeeController {
    static final String employeeControllerPath = "/employees";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private HttpHeaders createHeader(String name, String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(name, value);
        return headers;
    }

    @Autowired
    EmployeeServices employeeServices;

    @GetMapping(
            path = "/",
            produces = "application/json")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){
        return new ResponseEntity<>(
                employeeServices.getAllEmployees(),
                createHeader("Employees", "ALL"),
                HttpStatus.OK
        );
    }
}
