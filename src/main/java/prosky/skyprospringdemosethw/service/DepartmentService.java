package prosky.skyprospringdemosethw.service;

import org.springframework.stereotype.Service;
import prosky.skyprospringdemosethw.employee.Employee;
import prosky.skyprospringdemosethw.exception.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee getEmployeeWithMaxSalary(Integer departmentId) {

        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .max(Comparator.comparing(employee -> employee.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник с максимальной з/п не найден"));
    }

    public Employee getEmployeeWithMinSalary(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId()==departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(()-> new EmployeeNotFoundException("Сотрудник с минимальной з/п не найден"));
    }

    public Map<Integer,List<Employee>> getEmployeesByDepartment(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(e ->  departmentId == null || e.getDepartmentId() == departmentId)
                .collect(groupingBy(Employee::getDepartmentId,toList()));

    }
}
