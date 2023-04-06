package nl.cim.training.springboot.dao;

import nl.cim.training.springboot.exception.EmployeeNotFoundException;
import nl.cim.training.springboot.models.Employee;
import nl.cim.training.springboot.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeDaoTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeDao employeeDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeDao = new EmployeeDao();
        employeeDao.employeeRepository = employeeRepository;
    }

    final Employee e1 = new Employee("F1", "M1", "L1",
            "e1@cim.nl", 4.0, 5000.0,
            LocalDate.of(2000, 1, 1));
    final Employee e2 = new Employee("F2", "M2", "L2",
            "e2@cim.nl", 3.0, 4000.0,
            LocalDate.of(2000, 1, 1));

    @Test
    void testGetAllEmployees() {
    List<Employee> expectedEmployees = new ArrayList<>();
    expectedEmployees.add(this.e1);
    expectedEmployees.add(this.e2);

    when(employeeRepository.findAll()).thenReturn(expectedEmployees);

    List<Employee> actualEmployees = employeeDao.getAllEmployees();

    verify(employeeRepository, times(1)).findAll();
    assertEquals(expectedEmployees, actualEmployees);
    }

    @Test
    void testGetEmployeeSuccess() throws EmployeeNotFoundException, NoSuchFieldException, IllegalAccessException {
        Long employeeId = 1L;
        Employee expectedEmployee = this.e1;
        Field idField = Employee.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(expectedEmployee, employeeId);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(expectedEmployee));

        Employee actualEmployee = employeeDao.getEmployee(employeeId);

        verify(employeeRepository, times(1)).findById(employeeId);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void testGetEmployeeNotFound() {
        Long employeeId = 1L;
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> employeeDao.getEmployee(employeeId));
        verify(employeeRepository, times(1)).findById(employeeId);
    }

//        @Test
//        void testSearchEmployeeByEmailSuccess() throws EmployeeNotFoundException {
//            String email = "e1@example.com";
//            Employee expectedEmployee = this.e1;
//        }

@Test
void itShouldBeAbleToGiveBackOneEmployee() throws EmployeeNotFoundException {
    Long wantedId = 1L;

    when(employeeRepository.findById(wantedId)).thenReturn(Optional.of(this.e1));

    employeeDao.getEmployee(wantedId);

    ArgumentCaptor<Long> empArgCapture = ArgumentCaptor.forClass(Long.class);
    verify(employeeRepository).findById(empArgCapture.capture());
    Long captureID = empArgCapture.getValue();

    assertThat(wantedId).isEqualTo(captureID);

    }
}
//}
