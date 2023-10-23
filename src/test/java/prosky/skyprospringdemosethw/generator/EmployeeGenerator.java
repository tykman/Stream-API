package prosky.skyprospringdemosethw.generator;

import prosky.skyprospringdemosethw.employee.Employee;

public class EmployeeGenerator {
    public static final String MASHA_FIRST_NAME = "Маша";
    public static final String MASHA_LAST_NAME = "Машина";
    public static final double MASHA_SALARY = 900;


    public static final String DASHA_FIRST_NAME = "Даша";
    public static final String DASHA_LAST_NAME = "Дашина";
    public static final double DASHA_SALARY = 990;

    public static final String SASHA_FIRST_NAME = "Саша";
    public static final String SASHA_LAST_NAME = "Сашина";
    public static final double SASHA_SALARY= 800;

    public static final int FIRST_DEPARTMENT_ID = 1;
    public static final int SECOND_DEPARTMENT_ID = 2;

    public static Employee getMashaFirstDep() {
        return new Employee(MASHA_FIRST_NAME,MASHA_LAST_NAME,MASHA_SALARY,FIRST_DEPARTMENT_ID);
    }

    public static Employee getDashaFirstDep() {
        return new Employee(DASHA_FIRST_NAME,DASHA_LAST_NAME,DASHA_SALARY,FIRST_DEPARTMENT_ID);
    }

    public static Employee getSashaSecondDep() {
        return new Employee(SASHA_FIRST_NAME,SASHA_LAST_NAME,SASHA_SALARY,SECOND_DEPARTMENT_ID);
    }
}
