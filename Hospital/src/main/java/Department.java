package main.java;

import main.java.leadership.DepartmentHead;
import main.java.stuff.Doctor;

public class Department {
    private Doctor[] doctors;
    private DepartmentHead departmentHead;
    private String name;

    Department(Doctor[] doctors, DepartmentHead departmentHead, String name){
        this.departmentHead = departmentHead;
        this.doctors = doctors;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public DepartmentHead getDepartmentHead() {
        return departmentHead;
    }

    public Doctor[] getDoctors() {
        return doctors;
    }

    public void setDepartmentHead(DepartmentHead departmentHead) {
        this.departmentHead = departmentHead;
    }

    public void setDoctors(Doctor[] doctors) {
        this.doctors = doctors;
    }
}
