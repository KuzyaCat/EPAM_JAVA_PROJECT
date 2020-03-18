package main.java.components;

import main.java.users.stuff.Doctor;

import java.util.Date;

public class Appointment {
    private Doctor doctor;
    private Department department;
    private Date appDate;
    Appointment(Doctor doctor, Department department, Date appDate){
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

    public Department getDepartment() {
        return department;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }
    
}
