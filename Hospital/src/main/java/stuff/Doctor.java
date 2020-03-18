package main.java.stuff;

import main.java.User;

public class Doctor extends User {
    String department;
    boolean isHeadOfDepartment;
    Doctor(String name, String surname, int age, String login, String password, String department, boolean isHeadOfDepartment){
        super(name, surname, age, login, password);
        this.department = department;
        this.isHeadOfDepartment = isHeadOfDepartment;
    }

    public String getDepartment() {
        return department;
    }
}
