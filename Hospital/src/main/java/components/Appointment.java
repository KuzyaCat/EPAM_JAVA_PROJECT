package main.java.components;

import main.java.users.stuff.Doctor;
import main.java.date.GregorianDate;
import java.util.Date;

public class Appointment {
    private Doctor doctor;
    private String department;
    private GregorianDate appDate;

    public Appointment(Doctor doctor, String department, GregorianDate appDate){
        this.appDate = appDate;
        this.department = department;
        this.doctor = doctor;
    }

    public Appointment() {
        this(new Doctor(), "", new GregorianDate());
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public GregorianDate getAppDate() {
        return appDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setAppDate(GregorianDate appDate) {
        this.appDate = appDate;
    }

    @Override
    public String toString() {
        return "[" + this.doctor.toString() + "] " + this.department + " " + this.appDate.toString();
    }

    public Appointment parseString(String str) {
        int openingBrIndex = 0;
        int closingBrIndex = str.indexOf(']');

        String doctorString = "";
        for(int i = openingBrIndex + 1; i < closingBrIndex; i ++) {
            doctorString += str.charAt(i);
        }

        Doctor doctor = (new Doctor()).parseString(doctorString);

        String cutString = str.substring(closingBrIndex + 2);
        String[] cutStringArr = cutString.split(" ");

        String department = cutStringArr[0];
        GregorianDate appDate = (new GregorianDate()).parseString(cutStringArr[1]);

        return new Appointment(doctor, department, appDate);
    }
}
