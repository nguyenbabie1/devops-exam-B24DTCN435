package com.nguyen.employee;

import com.nguyen.employee.controller.EmployeeController;
import com.nguyen.employee.entity.Employee;
import com.nguyen.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class EmployeeCountTests {

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void returnsZeroWhenDatabaseIsEmpty() {
        employeeRepository.deleteAll();
        employeeRepository.flush();

        assertEquals(0L, employeeController.count());
    }

    @Test
    void returnsActualEmployeeCount() {
        employeeRepository.deleteAll();

        for (int i = 1; i <= 3; i++) {
            Employee employee = new Employee();
            employee.setFullName("Employee " + i);
            employee.setEmail("employee" + i + "@example.com");
            employeeRepository.save(employee);
        }

        employeeRepository.flush();

        assertEquals(3L, employeeController.count());
    }
}
