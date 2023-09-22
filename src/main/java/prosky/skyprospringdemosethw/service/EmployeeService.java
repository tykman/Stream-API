package prosky.skyprospringdemosethw.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import prosky.skyprospringdemosethw.employee.Employee;
import prosky.skyprospringdemosethw.exception.BadRequestException;
import prosky.skyprospringdemosethw.exception.EmployeeAlreadyAddedException;
import prosky.skyprospringdemosethw.exception.EmployeeNotFoundException;
import prosky.skyprospringdemosethw.exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public  class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

//    public EmployeeService (){
//        employees.add(new Employee("Лу","На",1000.0,1));
//        employees.add(new Employee("Ве","Нера",2000.2,1));
//        employees.add(new Employee("Зе","Мля",3000.3,1));
//
//        employees.add(new Employee("Сап","Санов",8888.8,2));
//        employees.add(new Employee("Сапа","Санова", 5555.3,2));
//
//        employees.add(new Employee("Гуля","Гулева",9999.9,3));
//        employees.add(new Employee("Зуля","Зулева",4444.5,3));
//    }

    private final static int MAX_SIZE=2;

    public Employee add(String firstName, String lastName,double salary,Integer departmentId) {

        validateFullName(firstName,lastName);
        validateIsAlfa(firstName,lastName);

        Employee newEmployee = new Employee(firstName, lastName,salary,departmentId);
        if (employees.contains(newEmployee)){
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }
        employees.add(newEmployee);
        return newEmployee;
    }

    private void validateFullName(String firstName,String lastName){
        String capitaliseFirstName = StringUtils.capitalize(firstName);
        if (!firstName.equals(capitaliseFirstName))
            throw new BadRequestException("Имя начинается не с заглавной буквы");

        String capitaliseLastName = StringUtils.capitalize(lastName);
        if (!lastName.equals(capitaliseLastName))
            throw new BadRequestException("Фамилия начинается не с заглавной буквы");
    }

    private void validateIsAlfa(String firstName,String lastName){
        if (!StringUtils.isAlpha(firstName)) {
            throw new BadRequestException("Имя должно содержать только буквы");
        }
        if (!StringUtils.isAlpha(lastName)) {
            throw new BadRequestException("Фамилия должна содержать только буквы");
        }
    }
    public Employee find(String firstName,String lastName,double salary,Integer departmentId){
        Employee employeeForFound= new Employee(firstName, lastName,salary,departmentId);
        for (Employee e: employees){
            if (e.equals(employeeForFound)) {
                return e;
            }
        }
        throw new EmployeeNotFoundException("Такого сотрудника нет");
    }

    public Employee remove(String firstName,String lastName,double salary,Integer departmentId){
        Employee employeeForRemove = new Employee(firstName, lastName,salary,departmentId);
        boolean removeResult = employees.remove(employeeForRemove);
        if (removeResult) {
            return employeeForRemove;
        } else {
            throw new EmployeeNotFoundException("Такой сотрудник в отделе не найден");
        }
    }

    public List<Employee> getAll() {
        return (employees);
    }
}
