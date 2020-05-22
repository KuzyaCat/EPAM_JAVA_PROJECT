package main.java.components;

import main.java.tasklogs.NurseTaskLog;
import main.java.users.Patient;
import main.java.users.stuff.Doctor;
import main.java.date.GregorianDate;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class Appointment implements Serializable {
    private String department;
    private int id; //appointmentId

    private Date appDate;

    private Patient patient;
    private Doctor doctor;

    private Treatment treatment;
    private Set<NurseTaskLog> nurseTaskLogs;

    public Appointment() {}

    public Appointment(String department, Date appDate) {
        this.department = department;
        this.appDate = appDate;
    }

    public Appointment(Patient patient, Doctor doctor, String department, Date appDate) {
        this.patient = patient;
        this.doctor = doctor;
        this.department = department;
        this.appDate = appDate;
    }

    public Appointment(Patient patient, Doctor doctor) {
        this.patient = patient;
        this.doctor = doctor;
    }

    public String getDepartment() {
        return department;
    }

    public int getId() {
        return id;
    }

    public Date getAppDate() {
        return appDate;
    }

    public GregorianDate getGregorianAppDate() {
        return new GregorianDate(appDate);
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public Set<NurseTaskLog> getNurseTaskLogs() {
        return nurseTaskLogs;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public void setNurseTaskLogs(Set<NurseTaskLog> nurseTaskLogs) {
        this.nurseTaskLogs = nurseTaskLogs;
    }

    @Override
    public String toString() {
        return "[" + this.doctor.toString() + "] " + this.appDate.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
