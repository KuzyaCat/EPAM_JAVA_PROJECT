package main.java.users.leadership;

import main.java.users.stuff.Doctor;

public class DepartmentHead extends Doctor {
    DepartmentHead(String name, String surname, int age, String login, String password, String department, boolean isHeadOfDepartment){
        super(name, surname, age, login, password, department, isHeadOfDepartment = true);
    }
}
