package prosky.skyprospringdemosethw.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import prosky.skyprospringdemosethw.employee.Employee;
import prosky.skyprospringdemosethw.service.EmployeeService;

import java.net.HttpRetryException;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @ExceptionHandler({HttpStatusCodeException.class})
    public String handlerException(HttpStatusCodeException e){
        return "Code: " + e.getStatusCode() +
                ".Error: " + e.getMessage();
    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    @GetMapping(path="/add")
    public Employee add(@RequestParam String firstName,
                        @RequestParam String lastName,
                        @RequestParam double salary,
                        @RequestParam Integer departmentId){
        return employeeService.add(firstName, lastName,salary,departmentId);
    }

    @GetMapping(path = "/find")
    public Employee find(@RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam double salary,
                         @RequestParam Integer departmentId){
        return employeeService.find(firstName, lastName,salary,departmentId);
    }

    @GetMapping(path = "remove")
    public Employee remove(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam double salary,
                           @RequestParam Integer departmentId){
        return employeeService.remove(firstName, lastName,salary,departmentId);
    }

    @GetMapping
    public List<Employee> getAll(){
        return employeeService.getAll();
    }

}
