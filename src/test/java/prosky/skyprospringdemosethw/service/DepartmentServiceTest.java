package prosky.skyprospringdemosethw.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import prosky.skyprospringdemosethw.employee.Employee;
import prosky.skyprospringdemosethw.exception.EmployeeNotFoundException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static prosky.skyprospringdemosethw.generator.EmployeeGenerator.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void getEmployeeWithMaxSalary_success() {
//        данные на вход
        int depId = FIRST_DEPARTMENT_ID;

//        подготовка ожидаемого результата
        Employee employeeWithMaxSalary = getDashaFirstDep();
        Employee dashaFirstDep = getDashaFirstDep();
        Employee mashaFirstDep = getMashaFirstDep();
        Employee sashaSecondDep = getSashaSecondDep();

        when(employeeService.getAll()).thenReturn(
                List.of(dashaFirstDep,mashaFirstDep,sashaSecondDep));

//        начало теста

        Employee actualEmployeeWithMaxSalary = departmentService.getEmployeeWithMaxSalary(depId);
        assertEquals(employeeWithMaxSalary,actualEmployeeWithMaxSalary);
        assertEquals(depId,actualEmployeeWithMaxSalary.getDepartmentId());
        assertTrue(dashaFirstDep.getSalary() > mashaFirstDep.getSalary());
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeeWithMaxSalary_withEmployeeNotFoundException() {
//        данные на вход
        int depId = FIRST_DEPARTMENT_ID;

//        подготовка ожидаемого результата

        when(employeeService.getAll()).thenReturn(Collections.singletonList(
                getSashaSecondDep())
        );
        String expectedMessage = "404 Сотрудник с максимальной з/п не найден";

//        начало теста

        Exception exception = assertThrows(EmployeeNotFoundException.class,
                () -> departmentService.getEmployeeWithMaxSalary(depId));
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeeWithMinSalary_success() {
        //        данные на вход
        int depId = FIRST_DEPARTMENT_ID;

//        подготовка ожидаемого результата
        Employee employeeWithMinSalary = getMashaFirstDep();

        Employee dashaFirstDep = getDashaFirstDep();
        Employee mashaFirstDep = getMashaFirstDep();
        Employee sashaSecondDep = getSashaSecondDep();

        when(employeeService.getAll()).thenReturn(
                List.of(dashaFirstDep,mashaFirstDep,sashaSecondDep)
        );

//        начало теста
        Employee actualEmployeeWithMinSalary = departmentService.getEmployeeWithMinSalary(depId);
        assertEquals(employeeWithMinSalary,actualEmployeeWithMinSalary);
        assertEquals(depId,actualEmployeeWithMinSalary.getDepartmentId());
        assertTrue(dashaFirstDep.getSalary() > mashaFirstDep.getSalary());
        verify(employeeService).getAll();

    }

    @Test
    void getEmployeeWithMinSalary_withEmployeeNotFoundException() {
//        данные на вход
        int depId = FIRST_DEPARTMENT_ID;

//        подготовка ожидаемого результата

        when(employeeService.getAll()).thenReturn(Collections.singletonList(
                getSashaSecondDep())
        );
        String expectedMessage = "404 Сотрудник с минимальной з/п не найден";

//        начало теста

        Exception exception = assertThrows(EmployeeNotFoundException.class,
                () -> departmentService.getEmployeeWithMinSalary(depId));
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeesByDepartment_success() {
//        данные на вход
        Integer depId = null;

//        подготовка ожидаемого результата
        Employee dashaFirstDep = getDashaFirstDep();
        Employee mashaFirstDep = getMashaFirstDep();
        Employee sashaSecondDep = getSashaSecondDep();

        when(employeeService.getAll()).thenReturn(List.of(
                dashaFirstDep,mashaFirstDep,sashaSecondDep));
        Map<Integer,List<Employee>> expectedEmployeeMap = new HashMap<>();
        expectedEmployeeMap.put(FIRST_DEPARTMENT_ID,List.of(dashaFirstDep,mashaFirstDep));
        expectedEmployeeMap.put(SECOND_DEPARTMENT_ID,Collections.singletonList(sashaSecondDep));

//        начало теста
        Map<Integer,List<Employee>> actualEmployeeMap = departmentService.getEmployeesByDepartment(depId);
        assertEquals(expectedEmployeeMap,actualEmployeeMap);
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeesByDepartment_whenDepIdIsNull() {
//        данные на вход
        Integer depId = FIRST_DEPARTMENT_ID;

//        подготовка ожидаемого результата
        Employee dashaFirstDep = getDashaFirstDep();
        Employee mashaFirstDep = getMashaFirstDep();
        Employee sashaSecondDep = getSashaSecondDep();

        when(employeeService.getAll()).thenReturn(List.of(
                dashaFirstDep,mashaFirstDep,sashaSecondDep));
        Map<Integer,List<Employee>> expectedEmployeeMap = new HashMap<>();
        expectedEmployeeMap.put(FIRST_DEPARTMENT_ID,List.of(dashaFirstDep,mashaFirstDep));
//        expectedEmployeeMap.put(SECOND_DEPARTMENT_ID,Collections.singletonList(sashaSecondDep));

//        начало теста
        Map<Integer,List<Employee>> actualEmployeeMap = departmentService.getEmployeesByDepartment(depId);
        assertEquals(expectedEmployeeMap,actualEmployeeMap);
        verify(employeeService).getAll();
    }
}