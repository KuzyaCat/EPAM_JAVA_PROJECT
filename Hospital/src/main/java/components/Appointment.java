package main.java.components;

import main.java.users.stuff.Doctor;

import java.util.Date;

public class Appointment {
    private Doctor doctor;
    private main.java.components.Department department;
    private Date appDate;
    public Appointment(Doctor doctor, main.java.components.Department department, Date appDate){
        this.appDate = appDate;
        this.department = department;
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Date getAppDate() {
        return appDate;
    }

    public main.java.components.Department getDepartment() {
        return department;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }
    
}
