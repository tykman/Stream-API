package prosky.skyprospringdemosethw;

import org.junit.jupiter.api.Test;
import prosky.skyprospringdemosethw.employee.Employee;
import prosky.skyprospringdemosethw.exception.EmployeeAlreadyAddedException;
import prosky.skyprospringdemosethw.exception.EmployeeNotFoundException;
import prosky.skyprospringdemosethw.exception.EmployeeStorageIsFullException;
import prosky.skyprospringdemosethw.generator.EmployeeGenerator;
import prosky.skyprospringdemosethw.service.EmployeeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static prosky.skyprospringdemosethw.generator.EmployeeGenerator.*;

public class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeService();

    @Test
    void add_success() {
//        подготовка входных данных

        String firstName = MASHA_FIRST_NAME;
        String lastName = MASHA_LAST_NAME;
        double salary = EmployeeGenerator.MASHA_SALARY;
        int depId = EmployeeGenerator.FIRST_DEPARTMENT_ID;

//        подготовка ожидаемого результата

        Employee expectedEmployee = EmployeeGenerator.getMashaFirstDep();

//        начало теста

        Employee addedEmployee = employeeService.add(firstName,lastName,salary,depId);
        assertEquals(expectedEmployee,addedEmployee);
    }

    @Test
    void add_withEmployeeStorageIsFullException() {

//        подготовка входных данных

        String firstName = EmployeeGenerator.DASHA_FIRST_NAME;
        String lastName = EmployeeGenerator.DASHA_LAST_NAME;
        double salary = EmployeeGenerator.DASHA_SALARY;
        int depId = EmployeeGenerator.FIRST_DEPARTMENT_ID;

//        подготовка ожидаемого результата

        employeeService.add(MASHA_FIRST_NAME, MASHA_LAST_NAME, MASHA_SALARY, depId);
        String expectedMessage = "400 Массив сотрудников переполнен";

//        начало теста

        Exception exception = assertThrows(EmployeeStorageIsFullException.class,
                () -> employeeService.add(firstName, lastName, salary, depId));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void find_luck() {
//        данные на вход
        String firstName = MASHA_FIRST_NAME;
        String lastName = MASHA_LAST_NAME;
        double salary = MASHA_SALARY;
        int depId = FIRST_DEPARTMENT_ID;

//       данные на ожидаемый результат
        employeeService.add(MASHA_FIRST_NAME,MASHA_LAST_NAME,MASHA_SALARY,depId);
        Employee expectedEmployee = getMashaFirstDep();

//        начало теста
        Employee actualEmployee = employeeService.find(firstName,lastName,salary,depId);
        assertEquals(expectedEmployee,actualEmployee);
    }

    @Test
    void find_withEmployeeNotFoundException() {

//        данные на вход
        String firstName = MASHA_FIRST_NAME;
        String lastName = MASHA_LAST_NAME;
        double salary = MASHA_SALARY;
        int depId = FIRST_DEPARTMENT_ID;


//        подготовка ожидаемого результата

        String expectedMessage = "404 Такого сотрудника нет";

//        начало теста

        Exception exception = assertThrows(EmployeeNotFoundException.class,
                ()-> employeeService.find(firstName,lastName,salary,depId));
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void remove_luck() {
//        данные на вход
        String firstName = MASHA_FIRST_NAME;
        String lastName = MASHA_LAST_NAME;
        double salary = MASHA_SALARY;
        int depId = FIRST_DEPARTMENT_ID;

//       данные на ожидаемый результат
        employeeService.add(MASHA_FIRST_NAME,MASHA_LAST_NAME,MASHA_SALARY,depId);
        Employee expectedEmployee = getMashaFirstDep();

//        начало теста
        Employee actualEmployee = employeeService.remove(firstName,lastName,salary,depId);
        assertEquals(expectedEmployee,actualEmployee);
    }

    @Test
    void remove_withEmployeeAlreadyAddedException() {

//        данные на вход
        String firstName = MASHA_FIRST_NAME;
        String lastName = MASHA_LAST_NAME;
        double salary = MASHA_SALARY;
        int depId = FIRST_DEPARTMENT_ID;


//        подготовка ожидаемого результата

        String expectedMessage = "404 Такого сотрудника нет";

//        начало теста

        Exception exception = assertThrows(EmployeeNotFoundException.class,
                ()-> employeeService.find(firstName,lastName,salary,depId));
        assertEquals(expectedMessage,exception.getMessage());
    }
}
