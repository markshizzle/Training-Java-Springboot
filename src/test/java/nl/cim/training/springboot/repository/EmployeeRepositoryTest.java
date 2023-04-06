package nl.cim.training.springboot.repository;

import nl.cim.training.springboot.models.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {
@Autowired
private EmployeeRepository empRepositoryTest;
    @Test
    void getFunctionGroupVsSalarySlabs() {

        fillUpData();

        int expectedRows = 9;
        List<Object[]> expectedResultList = List.of(
                new Object[] {1.0, 6000.0, 2L},
                new Object[] {2.0, 5000.0, 1L });
        List<Map<String, Object>> result = empRepositoryTest.getFunctionGroupVsSalarySlabs();

        assertNotNull(result);
        assertEquals(expectedRows, result.size());

        Map<String, Object> row1 = result.get(0);
        assertEquals(expectedResultList.get(0)[0], row1.get("funcGroup"));
        assertEquals(expectedResultList.get(0)[1], row1.get("salaryRange"));
        assertEquals(expectedResultList.get(0)[2], row1.get("cnt"));

        Map<String, Object> row2 = result.get(1);
        assertEquals(expectedResultList.get(1)[0], row2.get("funcGroup"));
        assertEquals(expectedResultList.get(1)[1], row2.get("salaryRange"));
        assertEquals(expectedResultList.get(1)[2], row2.get("cnt"));

        }
    @Test
    void testGetFunctionGroupVsSalarySlabsWithEmptyResult() {
        List<Object[]> resultList = Collections.emptyList();
        List<Map<String, Object>> result = empRepositoryTest.getFunctionGroupVsSalarySlabs();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    void fillUpData() {
        empRepositoryTest.save(new Employee("Elton", null, "John", "elton@gmail.com", 8.0, 3000.00, LocalDate.of(1988, 12, 13)));
        empRepositoryTest.save(new Employee("Michael", null, "Jakson", "michael@gmail.com", 7.0, 3500.00, LocalDate.of(1988, 12, 13)));
        empRepositoryTest.save(new Employee("Ed", null, "Sheeran", "ed@gmail.com", 6.0, 2500.00, LocalDate.of(1988, 12, 13)));
        empRepositoryTest.save(new Employee("Elvis", null, "Presley", "elvis@gmail.com", 5.0, 2000.00, LocalDate.of(1988, 12, 13)));
        empRepositoryTest.save(new Employee("John", null, "Lenon", "john@gmail.com", 4.0, 1500.00, LocalDate.of(1988, 12, 13)));
        empRepositoryTest.save(new Employee("George", null, "Harrison", "george@gmail.com", 3.0, 5000.00, LocalDate.of(1988, 12, 13)));
        empRepositoryTest.save(new Employee("Tahsan", "A", "Khan", "tahsan@gmail.com", 2.0, 5500.00, LocalDate.of(1988, 12, 13)));
        empRepositoryTest.save(new Employee("James", "C", "Guru", "james@gmail.com", 1.0, 6000.00, LocalDate.of(1988, 12, 13)));
        empRepositoryTest.save(new Employee("Shafaat", "Sheik", "Giasuddin", "rshafaat@gmail.com", 1.0, 6000.00, LocalDate.of(1999, 12, 13)));
        empRepositoryTest.save(new Employee("Mark", "", "Bokhorst", "m.bokhorst@gmail.com", 5.0, 5000.00, LocalDate.of(1999, 12, 13)));
    }
}