package nl.cim.training.springboot.controllers;

import nl.cim.training.springboot.dto.EmployeeRequest;
import nl.cim.training.springboot.dto.EmployeeResponse;
import nl.cim.training.springboot.exception.EmployeeNotFoundException;
import nl.cim.training.springboot.services.EmployeeServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(
            path = "/{id}",
            produces = ("application/json")
    )
    public ResponseEntity<EmployeeResponse> getEmployee(
            @PathVariable Long id) throws EmployeeNotFoundException {
        return new ResponseEntity<>(
                employeeServices.getEmployee(id),
                createHeader("Employee", id.toString()),
                HttpStatus.OK
        );
    }

    @GetMapping(
            path = "/findByEmail/{e}",
            produces = "application/json")
    public ResponseEntity<List<EmployeeResponse>> getEmployeeByEmail(@PathVariable String e){
        return new ResponseEntity<>(
                employeeServices.getEmployeeByEmail(e),
                createHeader("Employees", "ALL"),
                HttpStatus.OK
        );
    }

    @GetMapping(
            path = "/findByFuncGroupGreaterThan/{x}",
            produces = "application/json")
    public ResponseEntity<List<EmployeeResponse>> getFuncGroupGreaterThan(@PathVariable Double x){
        return new ResponseEntity<>(
                employeeServices.getFuncGroupGreaterThan(x),
                createHeader("Employees", "ALL"),
                HttpStatus.OK
        );
    }

    @PutMapping(
            path = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) throws EmployeeNotFoundException {
        logger.info("Employee ID requested: {}", id);

        return new ResponseEntity<>(
                employeeServices.updateEmployee(id, employeeRequest),
                createHeader("Employee", id.toString()),
                HttpStatus.ACCEPTED
        );
    }

    @PostMapping(
            path = "/",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<EmployeeResponse> addEmployee(
            @RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse newEmployee = employeeServices.saveEmployee(employeeRequest);

        return new ResponseEntity<>(
                newEmployee,
                createHeader("Employee", newEmployee.getId().toString()),
                HttpStatus.CREATED
        );
    }
}
